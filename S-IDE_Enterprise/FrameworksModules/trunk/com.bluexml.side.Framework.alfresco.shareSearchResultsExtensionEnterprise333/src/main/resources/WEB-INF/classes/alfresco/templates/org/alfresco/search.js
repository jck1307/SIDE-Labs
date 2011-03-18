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


function toRepoType(appType)
{
   var type = "";
   switch (String(appType))
   {
      case "dod5015":
         type = "dod:filePlan";
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
   }
   return type;
}

function getContainerType()
{
   // Need to know what type of node the container is
   var siteId = page.url.templateArgs.site,
      containerId = template.properties.container,
      containerType = "cm:folder",
      appType = "";
   
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
      appType = doclibMeta.type;
   }

   var connector = remote.connect("alfresco");
   result = connector.get("/slingshot/doclib/container/" + siteId + "/" + containerId + "?type=" + toRepoType(appType));
   if (result.status == 200)
   {
      var data = eval('(' + result + ')');
      containerType = data.container.type;
   }

   return containerType;
}

var containerType = getContainerType(),
   doclibType = fromRepoType(containerType);

model.containerType = containerType;
model.doclibType = doclibType == "" ? doclibType : doclibType + "-";
