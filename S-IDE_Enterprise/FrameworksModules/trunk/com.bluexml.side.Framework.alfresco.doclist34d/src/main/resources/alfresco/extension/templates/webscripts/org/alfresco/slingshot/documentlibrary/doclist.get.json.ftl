<#--
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

-->


<#import "item.lib.ftl" as itemLib />
<#escape x as jsonUtils.encodeJSONString(x)>
{
   "totalRecords": ${doclist.paging.totalRecords?c},
   "startIndex": ${doclist.paging.startIndex?c},
   "metadata":
   {
      "repositoryId": "${server.id}",
      <#if doclist.container??>"container": "${doclist.container.nodeRef}",</#if>
      "parent":
      {
      <#if doclist.parent??>
         <#assign parentNode = doclist.parent.node>
         "nodeRef": "${parentNode.nodeRef}",
         "permissions":
         {
            "userAccess":
            {
            <#list doclist.parent.userAccess?keys as perm>
               <#if doclist.parent.userAccess[perm]?is_boolean>
               "${perm?string}": ${doclist.parent.userAccess[perm]?string}<#if perm_has_next>,</#if>
               </#if>
            </#list>
            }
         }
      </#if>
      },
      "onlineEditing": ${doclist.onlineEditing?string},
      "itemCounts":
      {
         "folders": ${(doclist.itemCount.folders!0)?c},
         "documents": ${(doclist.itemCount.documents!0)?c}
      }
   },
   "items":
   [
      <#list doclist.items as item>
      {
         <@itemLib.itemJSON item=item />
      }<#if item_has_next>,</#if>
      </#list>
   ]
}
</#escape>
