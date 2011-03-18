<%--
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

--%>


<%
metamodel http://www.kerblue.org/class/1.0
%>

<%--
  -- This file is a patched copy of Alfresco Share's default file upload webscript, but it is kept
  -- separate for ease of maintainability. It should be removed when Alfresco release their own 
  -- "webscript that returns a list of content types (has been asked for on the wiki)"
  --%>

<%script type="clazz.ClassPackage" name="getFileUploadOutputFile"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.core.site-webscripts")%>org/alfresco/components/upload/flash-upload.get.html.ftl<%}%>

<%script type="clazz.ClassPackage" name="addCustomSiteProfile" file="<%getFileUploadOutputFile%>"%>
<div id="${args.htmlid}-dialog" class="flash-upload hidden">
   <div class="hd">
      <span id="${args.htmlid}-title-span"></span>
   </div>
   <div class="bd">
      <div class="browse-wrapper">
         <div class="center">
            <div id="${args.htmlid}-flashuploader-div" class="browse">${msg("label.noFlash")}</div>
            <div class="label">${msg("label.browse")}</div>
         </div>
      </div>
      <div class="tip-wrapper">
         <span id="${args.htmlid}-multiUploadTip-span">${msg("label.multiUploadTip")}</span>
         <span id="${args.htmlid}-singleUpdateTip-span">${msg("label.singleUpdateTip")}</span>
      </div>

      <div id="${args.htmlid}-filelist-table" class="fileUpload-filelist-table"></div>

      <div class="status-wrapper">
         <span id="${args.htmlid}-status-span" class="status"></span>
      </div>

      <div id="${args.htmlid}-versionSection-div"> 
         <div class="yui-g">
            <h2>${msg("section.version")}</h2>
         </div>
         <div class="yui-gd">
            <div class="yui-u first">
               <label for="${args.htmlid}-minorVersion-radioButton">${msg("label.version")}</label>
            </div>
            <div class="yui-u">
               <input id="${args.htmlid}-minorVersion-radioButton" type="radio" name="majorVersion" checked="checked" /> ${msg("label.minorVersion")}
            </div>
         </div>
         <div class="yui-gd">
            <div class="yui-u first">&nbsp;
            </div>
            <div class="yui-u">
               <input id="${args.htmlid}-majorVersion-radioButton" type="radio" name="majorVersion" /> ${msg("label.majorVersion")}
            </div>
         </div>
         <div class="yui-gd">
            <div class="yui-u first">
               <label for="${args.htmlid}-description-textarea">${msg("label.comments")}</label>
            </div>
            <div class="yui-u">
               <textarea id="${args.htmlid}-description-textarea" name="description" cols="80" rows="4"></textarea>
            </div>
         </div>
      </div>

      <!-- Templates for a file row -->
      <div style="display:none">
         <div id="${args.htmlid}-left-div" class="fileupload-left-div">
            <span class="fileupload-percentage-span hidden">&nbsp;</span>
            <!-- SIDE patch-->
            <select class="fileupload-contentType-select <#if (contentTypes?size == 1)>hidden</#if>" style="display:block;">
            <!-- SIDE patch end-->
               <#if (contentTypes?size > 0)>
                  <#list contentTypes as contentType>
                     <option value="${contentType.id}">${msg(contentType.value)}</option>
                  </#list>
               </#if>
            </select>
         </div>
         <div id="${args.htmlid}-center-div" class="fileupload-center-div">
            <span class="fileupload-progressSuccess-span">&nbsp;</span>
            <img src="${url.context}/components/images/generic-file-32.png" class="fileupload-docImage-img" alt="file" />
            <span class="fileupload-progressInfo-span"></span>
         </div>
         <div id="${args.htmlid}-right-div" class="fileupload-right-div">
            <span class="fileupload-fileButton-span">
               <button class="fileupload-file-button" value="Remove">${msg("button.remove")}</button>
            </span>
         </div>
      </div>
         <div class="bdft">
            <input id="${args.htmlid}-upload-button" type="button" value="${msg("button.upload")}" />
            <input id="${args.htmlid}-cancelOk-button" type="button" value="${msg("button.cancel")}" />
         </div>
   </div>
</div>
<script type="text/javascript">//<![CDATA[
new Alfresco.FlashUpload("${args.htmlid}").setMessages(
   ${messages}
);
//]]></script>
