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
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.portal.generator.alfresco.PortalAlfrescoGenerator
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%-- Templates creation --%>
<%script type="Page" name="createTemplates"%>
<%ID.toLowerCase().nPut("templates_name")%>
<%if (generate){%>
<%getProperty("alf.share.paths.core.templates")%><%nGet("templates_name")%>.ftl
<%}%>
<%script type="Page" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
<%ID.toLowerCase().nPut("templates_name")%>
<%parent().name.nPut("site_name")%>
<#include "../../org/alfresco/include/alfresco-template.ftl" />
<@templateHeader>
  <@link rel="stylesheet" type="text/css" href="${url.context}/templates/<%nGet("templates_name")%>/<%nGet("templates_name")%>.css" />
</@>

<@templateBody>
   <div id="alf-hd">
      <@region id="header" scope="global" protected=true />
	  <@region id="title" scope="template" protected=true />
	  <@region id="navigation" scope="template" protected=true />
   </div>
   <div id="bd">
      <div class="yui-t1">
         <div id="yui-main">
         	<%view()%>         
         </div>
      </div>
   </div>
</@>

<@templateFooter>
   <div id="alf-ft">
      <@region id="footer" scope="global" protected=true />
   </div>
</@>
<%script type="Page" name="view"%>
<%for (portlets){%>
	<%if (associationPortlet != null && associationPage != null){%>
		<%for (associationPortlet){%>
			<%if (isPortletInternal != null && (isPortletInternal.view != null || isPortletInternal.form != null)){%>
				<%for (isPortletInternal.view){%>
						<%if (current("view.FacetMap")){%>
			<@region scope="template" id="<%current("portal.Portlet").name%>" protected=true/>
						<%}%>
				<%}%>
				<%for (isPortletInternal.form){%>
					<%if (current("Portlet").name.toLowerCase() =="advanced-search"){%>
						<%if (current("form.SearchFormCollection")){%>
			<@region scope="template" id="advanced-search" protected=true/>
						<%}%>
					<%}%>
				<%}%>
			<%}else{%>
				<!-- use default share components-->
			<@region 
				<%for (isInstanceOfPortletType.instances){%>
					<%if (instanceOf.name == "scope"){%>
					<%instanceOf.name%>="<%value%>" 
					<%}%>
				<%}%>
					id="<%name%>"
				 	protected=true />
			<%}%>
		<%}%>
	<%}%>
<%}%>
