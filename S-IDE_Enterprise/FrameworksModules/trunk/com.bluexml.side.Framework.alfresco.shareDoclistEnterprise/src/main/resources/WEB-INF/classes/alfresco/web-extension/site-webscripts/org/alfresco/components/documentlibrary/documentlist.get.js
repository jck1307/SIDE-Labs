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


const PREFERENCES_ROOT = "org.alfresco.share.documentList";

/* Sort the actions by preference order */
function sortByOrder(a, b)
{
   return (a.order - b.order);
}

/**
 * Main entrypoint for component webscript logic
 * 
 * @method main
 */
function main()
{
   var result, preferences = {}, actionSets = {};
   
   // Request the current user's preferences
   var result = remote.call("/api/people/" + stringUtils.urlEncode(user.name) + "/preferences?pf=" + PREFERENCES_ROOT);
   if (result.status == 200 && result != "{}")
   {
      var prefs = eval('(' + result + ')');
      try
      {
         // Populate the preferences object literal for easy look-up later
         preferences = eval('(prefs.' + PREFERENCES_ROOT + ')');
         if (typeof preferences != "object")
         {
            preferences = {};
         }
      }
      catch (e)
      {
      }
   }

   model.preferences = preferences;

   // Actions
   var prefActionSet, order, actionSet, actionSetId, actionId, defaultOrder;
   var myConfig = new XML(config.script),
      prefActions = preferences.actions || {};
   
   for each (var xmlActionSet in myConfig..actionSet)
   {
      actionSet = [];
      actionSetId = xmlActionSet.@id.toString();
      prefActionSet = prefActions[actionSetId] || {};
      defaultOrder = 100;
      
      for each (var xmlAction in xmlActionSet..action)
      {
         defaultOrder++;
         actionId = xmlAction.@id.toString();
         
         actionSet.push(
         {
            order: prefActionSet[actionId] || defaultOrder,
            id: actionId,
            type: xmlAction.@type.toString(),
            permission: xmlAction.@permission.toString(),
            href: xmlAction.@href.toString(),
            label: xmlAction.@label.toString()
         });
      }
      actionSets[actionSetId] = actionSet.sort(sortByOrder);
   }
   
   model.actionSets = actionSets;
   
   // Discover SharePoint (Vti) server port
   var vtiServerJson = "{}";
   
   result = remote.call("/api/vti/serverDetails");
   if (result.status == 200 && result != "")
   {
      vtiServerJson = result;
   }
   
   model.vtiServer = vtiServerJson;
   
   // Repository Url
   var repositoryUrl = null,
      repositoryConfig = config.scoped["DocumentLibrary"]["repository-url"];
   if (repositoryConfig !== null)
   {
      repositoryUrl = repositoryConfig.value;
   }

   model.repositoryUrl = repositoryUrl;
   
   var searchParam = {};
   
   
   if (page.url.args["t"]) {
	   searchParam.fullText=page.url.args["t"];
	   
   } else if (page.url.args["search"]){
	   searchParam=eval('('+page.url.args["search"]+')');
	   //model.searchParam.properties=model.searchParam.properties.toSource();
	   
   }
   
   model.searchParam=searchParam.toSource().replace(/'/g,"\\'");
   
}

main();
