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


<#import "/org/alfresco/components/form/form.lib.ftl" as formLib />
   
<#if formUI == "true">
   <@formLib.renderFormsRuntime formId=formId />
</#if>

<@formLib.renderFormContainer formId=formId>  
   <div id="${formId}-tabview" class="yui-navset"> 
      <ul class="yui-nav">
         <#list form.structure as item>
            <#if item.kind == "set">
               <li<#if item_index == 0> class="selected"</#if>><a href="#tab_${item_index}"><em>${item.label}</em></a></li>
            </#if>
         </#list>
      </ul>                
      <div class="yui-content">
         <#list form.structure as item>
            <#if item.kind == "set">
               <div id="tab_${item_index}">
                  <@formLib.renderSet set=item />
               </div>      
            </#if>
         </#list>
      </div>
   </div>
</@>

<script type="text/javascript">//<![CDATA[
(function() 
{
   new YAHOO.widget.TabView('${formId}-tabview');
})();
//]]></script>
