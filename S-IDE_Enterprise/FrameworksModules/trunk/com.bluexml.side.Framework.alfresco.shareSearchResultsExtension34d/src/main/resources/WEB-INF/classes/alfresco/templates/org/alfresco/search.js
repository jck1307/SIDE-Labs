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


function toRepoType(contentType)
{
   var type = "";
   switch (String(contentType))
   {
      case "dod5015":
         type = "dod:filePlan";
         break;
      case "wcmqs":
         type = "ws:websiteContainer";
         break;
   }
   return type;
}

function fromRepoType(repoType)
{
   var type = "";
   switch (String(repoType))
   {
      case "dod:filePlan":
         type = "dod5015";
         break;
      case "ws:websiteContainer":
         type = "wcmqs";
         break;   
   }
   return type;
}

function getLocationType()
{
   // Need to know what type of node the container is
   var siteId = page.url.templateArgs.site,
      containerId = template.properties.container,
      containerType = "cm:folder",
      contentType = "";

   if (siteId !== null)
   {
      var p = sitedata.getPage("site/" + siteId + "/dashboard");
      if (p != null)
      {
         pageMetadata = eval('(' + p.properties.pageMetadata + ')');
         pageMetadata = pageMetadata != null ? pageMetadata : {};
         doclibMeta = pageMetadata[page.id] || {};
         if (doclibMeta.titleId != null)
         {
            // Save the overridden page title into the request context
            context.setValue("page-titleId", doclibMeta.titleId);
         }
         contentType = doclibMeta.type;
      }

      var connector = remote.connect("alfresco");
      result = connector.get("/slingshot/doclib/container/" + siteId + "/" + containerId + "?type=" + toRepoType(contentType));
      if (result.status == 200)
      {
         var data = eval('(' + result + ')');
         containerType = data.container.type;
      }
   }
   
   return (
   {
      siteId: siteId,
      containerType: containerType
   });
}

var objLocation = getLocationType(),
   doclibType = fromRepoType(objLocation.containerType),
   scopeType = objLocation.siteId !== null ? "" : "repo-";

model.doclibType = doclibType == "" ? scopeType : doclibType + "-";
model.appType = context.attributes.portletHost ? "portlet-" : "";

// Repository Library root node
var rootNode = "alfresco://company/home",
   repoConfig = config.scoped["RepositoryLibrary"]["root-node"];
if (repoConfig !== null)
{
   rootNode = repoConfig.value;
}

model.rootNode = rootNode;
