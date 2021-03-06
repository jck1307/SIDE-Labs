/*
    Copyright (C) 2007-2011  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/


<import resource="classpath:/alfresco/templates/webscripts/org/alfresco/slingshot/documentlibrary/evaluator.lib.js">
<import resource="classpath:/alfresco/extension/templates/webscripts/org/alfresco/slingshot/documentlibrary/filters.lib.js">
<import resource="classpath:/alfresco/templates/webscripts/org/alfresco/slingshot/documentlibrary/parse-args.lib.js">

/**
 * Main entry point: Create collection of documents and folders in the given space
 *
 * @method getDoclist
 */
function getDoclist()
{
   // Use helper function to get the arguments
   var parsedArgs = ParseArgs.getParsedArgs();
   if (parsedArgs === null)
   {
      return;
   }

// SIDE
   parsedArgs.args=args;
   
   var filter = args.filter,
      items = [];

   // Try to find a filter query based on the passed-in arguments
   var allNodes = [],
      favourites = Common.getFavourites(),
      filterParams = Filters.getFilterParams(filter, parsedArgs,
      {
         favourites: favourites
      }),
      query = filterParams.query;

   // Query the nodes - passing in sort and result limit parameters
   if (query !== "")
   {
	   //  SIDE override sort parameters using sort parameter (see share config Search)
		if (args.sorting) 
		{
			var sortColumns = [];
			var sort = args.sorting;
			if (sort != null && sort.length != 0)
			{
				var asc = true;
				var separator = sort.indexOf("|");
				if (separator != -1)
				{
					sort = sort.substring(0, separator);
					asc = (sort.substring(separator + 1) == "true");
				}
				var column;
				if (sort.charAt(0) == '.')
				{
					// handle pseudo cm:content fields
					column = "@{http://www.alfresco.org/model/content/1.0}content" + sort;
				}
				else if (sort.indexOf(":") != -1)
				{
					// handle attribute field sort
					column = "@" + utils.longQName(sort);
				}
				else
				{
					// other sort types e.g. TYPE
					column = sort;
				}
				sortColumns.push(
				{
					column: column,
					ascending: asc
				});
			}
			
			filterParams.sort = sortColumns;
		}
		
      allNodes = search.query(
      {
         query: query,
         language: filterParams.language,
         page:
         {
            maxItems: (filterParams.limitResults ? parseInt(filterParams.limitResults, 10) : 0)
         },
         sort: filterParams.sort,
         templates: filterParams.templates,
         namespace: (filterParams.namespace ? filterParams.namespace : null)
      });
   }

   // Ensure folders and folderlinks appear at the top of the list
   var folderNodes = [],
      documentNodes = [];
   
   for each (node in allNodes)
   {
      try
      {
         if (node.isContainer || node.typeShort == "app:folderlink")
         {
            folderNodes.push(node);
         }
         else
         {
            documentNodes.push(node);
         }
      }
      catch (e)
      {
         // Possibly an old indexed node - ignore it
      }
   }
   
   // Node type counts
   var folderNodesCount = folderNodes.length,
      documentNodesCount = documentNodes.length,
      nodes, totalRecords;

   if (parsedArgs.type === "documents")
   {
      nodes = documentNodes;
   }
   else
   {
      nodes = folderNodes.concat(documentNodes);
   }
   totalRecords = nodes.length;
   
   // Pagination
   var pageSize = args.size || nodes.length,
      pagePos = args.pos || "1",
      startIndex = (pagePos - 1) * pageSize;

   // Trim the nodes array down to the page size
   nodes = nodes.slice(startIndex, pagePos * pageSize);

   // Common or variable parent container?
   var parent = null;
   
   if (!filterParams.variablePath)
   {
      // Parent node permissions (and Site role if applicable)
      parent =
      {
         node: parsedArgs.pathNode,
         userAccess: Evaluator.run(parsedArgs.pathNode, true).actionPermissions
      };
   }

   var isThumbnailNameRegistered = thumbnailService.isThumbnailNameRegistered(THUMBNAIL_NAME),
      thumbnail = null,
      locationNode,
      item;
   
   // Loop through and evaluate each node in this result set
   for each (node in nodes)
   {
      // Get evaluated properties.
      item = Evaluator.run(node);
      if (item !== null)
      {
         item.isFavourite = (favourites[item.node.nodeRef] === true);
   
         // Does this collection of nodes have potentially differering paths?
         if (filterParams.variablePath || item.isLink)
         {
            locationNode = item.isLink ? item.linkedNode : item.node;
            location = Common.getLocation(locationNode, parsedArgs.libraryRoot);
         }
         else
         {
            location =
            {
               site: parsedArgs.location.site,
               siteTitle: parsedArgs.location.siteTitle,
               container: parsedArgs.location.container,
               path: parsedArgs.location.path,
               file: node.name
            };
         }
         location.parent = {};
         if (node.parent != null && node.parent.hasPermission("Read"))
         {
            location.parent.nodeRef = String(node.parent.nodeRef.toString());  
         }
         
         // Resolved location
         item.location = location;
         
         // Is our thumbnail type registered?
         if (isThumbnailNameRegistered)
         {
            // Make sure we have a thumbnail.
            thumbnail = item.node.getThumbnail(THUMBNAIL_NAME);
            if (thumbnail === null)
            {
               // No thumbnail, so queue creation
               item.node.createThumbnail(THUMBNAIL_NAME, true);
            }
         }
         
         items.push(item);
      }
      else
      {
         totalRecords -= 1;
      }
   }

   // Array Remove - By John Resig (MIT Licensed)
   var fnArrayRemove = function fnArrayRemove(array, from, to)
   {
     var rest = array.slice((to || from) + 1 || array.length);
     array.length = from < 0 ? array.length + from : from;
     return array.push.apply(array, rest);
   };
   
   /**
    * De-duplicate orignals for any existing working copies.
    * This can't be done in evaluator.lib.js as it has no knowledge of the current filter or UI operation.
    * Note: This may result in pages containing less than the configured amount of items (50 by default).
   */
   for each (item in items)
   {
      if (item.customObj.isWorkingCopy)
      {
         var workingCopyOriginal = String(item.customObj.workingCopyOriginal);
         for (var i = 0, ii = items.length; i < ii; i++)
         {
            if (String(items[i].node.nodeRef) == workingCopyOriginal)
            {
               fnArrayRemove(items, i);
               break;
            }
         }
      }
   }

   return (
   {
      luceneQuery: query,
      paging:
      {
         totalRecords: totalRecords,
         startIndex: startIndex
      },
      container: parsedArgs.rootNode,
      parent: parent,
      onlineEditing: utils.moduleInstalled("org.alfresco.module.vti"),
      itemCount:
      {
         folders: folderNodesCount,
         documents: documentNodesCount
      },
      items: items
   });
}

/**
 * Document List Component: doclist
 */
model.doclist = getDoclist();
