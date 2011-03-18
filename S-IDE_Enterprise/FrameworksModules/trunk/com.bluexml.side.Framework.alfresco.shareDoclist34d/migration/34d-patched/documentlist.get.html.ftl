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


<#include "include/documentlist.lib.ftl" />
<@documentlistTemplate>
<script type="text/javascript">//<![CDATA[
   new Alfresco.DocumentList("${args.htmlid}").setOptions(
   {
      <#if repositoryUrl??>repositoryUrl: "${repositoryUrl}",</#if>
      siteId: "${page.url.templateArgs.site!""}",
      containerId: "${template.properties.container!"documentLibrary"}",
      usePagination: ${(args.pagination!false)?string},
      showFolders: ${(preferences.showFolders!false)?string},
      simpleView: ${(preferences.simpleView!false)?string},
      highlightFile: "${(page.url.args["file"]!"")?js_string}",
      vtiServer: ${vtiServer},
      search:eval('${searchParam}'),
      replicationUrlMapping: ${replicationUrlMappingJSON!"{}"}
   }).setMessages(
      ${messages}
   );
//]]></script>
</@>
