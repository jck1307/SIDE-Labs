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


<#include "../../include/alfresco-macros.lib.ftl" />
<#import "header.inc.ftl" as header>
<#assign siteActive = page.url.templateArgs.site??>
<#assign id = args.htmlid>
<#assign jsid = id?replace("-", "_")>
<#assign logo=msg("header.logo")><#if logo="header.logo"><#assign logo="app-logo.png"></#if>
<#if !user.isGuest>
<script type="text/javascript">//<![CDATA[
   var ${jsid} = new Alfresco.component.Header("${id}").setOptions(
   {
      siteId: "${page.url.templateArgs.site!""}",
      siteTitle: "${siteTitle?js_string}",
      minSearchTermLength: ${args.minSearchTermLength!config.scoped["Search"]["search"].getChildValue("min-search-term-length")},
      tokens:
      {
         site: "${page.url.templateArgs.site!""}",
         pageid: "${page.url.templateArgs.pageid!""}",
         userid: "${user.name?js_string}"
      }
   }).setMessages(${messages});
   Alfresco.util.createTwister.collapsed = "${collapsedTwisters?js_string}";
//]]></script>
<div class="header">
   <span class="header-left">
      <span class="logo">
         <a href="#" onclick="${jsid}.showAboutShare(); return false;"><img src="${url.context}/res/themes/${theme}/images/${logo}" alt="Alfresco Share" /></a>
      </span>
      <span class="logo-spacer">&nbsp;</span>
      <span id="${id}-appItems" class="app-items hidden"><@header.renderItems config.global.header.appItems id "app" /></span>
   </span>
<script type="text/javascript">//<![CDATA[
   ${jsid}.setAppItems([${header.js}]);
//]]></script>

   <span id="${id}-userItems" class="user-items">
      <div class="user-items-wrapper">
         <@header.renderItems config.global.header.userItems id "user" />
      </div>
      <div class="search-box">
         <span id="${id}-search_more" class="yui-button yui-menu-button">
            <span class="first-child" style="background-image: url(${url.context}/res/components/images/header/search-menu.png)">
               <button type="button" title="${msg("header.search.description")}" tabindex="0"></button>
            </span>
         </span>
         <input id="${id}-searchText" type="text" maxlength="1024" />
      </div>
      
   </span>
<script type="text/javascript">//<![CDATA[
   ${jsid}.setUserItems([${header.js}]);
//]]></script>
</div>
<#else>
<div class="header">
   <span class="header-left">
      <span class="logo">
         <a href="#" onclick="${jsid}.showAboutShare(); return false;"><img src="${url.context}/res/themes/${theme}/images/${logo}" alt="Alfresco Share" /></a>
      </span>
      <span class="logo-spacer">&nbsp;</span>
   </span>
</div>
</#if>
<div class="clear"></div>
