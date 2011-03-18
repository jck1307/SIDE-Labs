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

import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>
<%--import com.bluexml.side.portal.generator.alfresco.services.ProxieServices--%>
<%-- Must be genereated using Portal generator to choose the view --%>

<%script type="Portal" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("alf.paths.extension.client.webscripts")%>/org/alfresco/slingshot/documentlibrary/customViews.ftl<%}%>

<%script type="Portal" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<%for (portletSet){%>
<%if (name.toLowerCase() == "documentlibrary" && isPortletInternal != null && isPortletInternal.view != null) {%>
<%for (isPortletInternal.view.getInnerView()){%>
<#if node.type == "{<%filter("view.AbstractViewOf").viewOf.filter("clazz.Clazz").getNameSpace()%>}<%filter("view.AbstractViewOf").viewOf.filter("clazz.Clazz").getQualifiedName()%>">
	<#include "doclist_views/doclist_<%filter("view.AbstractViewOf").name%>.ftl">
</#if>
<%}%>
<%}%>
<%}%> 
