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


<#if form.fields["prop_cm_publisher"]??>
   <#if form.mode == "view">
      <@formLib.renderField field=form.fields["prop_cm_publisher"] />
      <@formLib.renderField field=form.fields["prop_cm_contributor"] />
      <@formLib.renderField field=form.fields["prop_cm_type"] />
      <@formLib.renderField field=form.fields["prop_cm_identifier"] />
      <@formLib.renderField field=form.fields["prop_cm_dcsource"] />
      <@formLib.renderField field=form.fields["prop_cm_coverage"] />
      <@formLib.renderField field=form.fields["prop_cm_rights"] />
      <@formLib.renderField field=form.fields["prop_cm_subject"] />
   <#else>
      <div class="yui-g">
         <div class="yui-u first">
            <@formLib.renderField field=form.fields["prop_cm_publisher"] />
         </div>
         <div class="yui-u">
            <@formLib.renderField field=form.fields["prop_cm_contributor"] />
         </div>
      </div>
      <div class="yui-g">
         <div class="yui-u first">
            <@formLib.renderField field=form.fields["prop_cm_type"] />
         </div>
         <div class="yui-u">
            <@formLib.renderField field=form.fields["prop_cm_identifier"] />
         </div>
      </div>
      <div class="yui-g">
         <div class="yui-u first">
            <@formLib.renderField field=form.fields["prop_cm_dcsource"] />
         </div>
         <div class="yui-u">
            <@formLib.renderField field=form.fields["prop_cm_coverage"] />
         </div>
      </div>
      <div class="yui-g">
         <div class="yui-u first">
            <@formLib.renderField field=form.fields["prop_cm_rights"] />
         </div>
         <div class="yui-u">
            <@formLib.renderField field=form.fields["prop_cm_subject"] />
         </div>
      </div>
        
      <div style="clear: both;"></div>
   </#if>
</#if>
