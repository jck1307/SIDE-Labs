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


<div class="page-title search-title theme-bg-color-1">
   <h1 class="theme-color-3"><span>${msg("header.results")}</span></h1>
   <#if page.url.templateArgs.site??>
   <div>
      <span class="navigation-item backLink">
         <a href="${url.context}/page/site/${page.url.templateArgs.site}/dashboard">${msg("header.backlink", siteTitle?html)}</a>
      </span>
   </div>
   </#if>
   <div class="clear"></div>
</div>
