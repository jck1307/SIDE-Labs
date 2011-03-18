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


<%--
Copyright (C) BlueXML 2005-2008

This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 --%>
 <%
metamodel http://www.kerblue.org/class/1.0

import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import templates.servicesTemplates.Association
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>

<%script type="clazz.Clazz" name="validatedFilename"%>
<%getProperty("alf.paths.extension.side.webscripts")%>/<%getFolder()%>/<%getQualifiedName().replaceAll("_","/")%>/xml/<%getQualifiedName()%>.ftl
<%script type="clazz.Clazz" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<item>
	<id>${child.id}</id>
	<url>${child.url}</url>
	<downloadUrl>${child.downloadUrl}</downloadUrl>
	<displayPath>${child.displayPath}</displayPath>
	<icon16>${child.icon16}</icon16>
	<icon32>${child.icon32}</icon32>
	<nodeRef>${child.nodeRef}</nodeRef>
	<parent>${child.parent.nodeRef}</parent>
	<size>${child.size}</size>
	<createdDate>${child.properties["cm:created"]?datetime}</createdDate>
	<modifyDate>${child.properties["cm:modified"]?datetime}</modifyDate>
	<%for (getAllSortedAttibutes()){%>
	<#if (child.properties["<%current(1).getFolder()%>:<%getQualifiedName()%>"]?exists)>
		<#if child.properties["<%current(1).getFolder()%>:<%getQualifiedName()%>"]?is_sequence>
	<<%getQualifiedName()%>><#list child.properties["<%current(1).getFolder()%>:<%getQualifiedName()%>"] as key>${key} </#list></<%getQualifiedName()%>>
		<#else/>
		<%if (typ.toString().equalsIgnoreCase("date")){%>
		<<%getQualifiedName()%>>${child.properties["<%current(1).getFolder()%>:<%getQualifiedName()%>"]?string("yyyy-MM-dd")!""}</<%getQualifiedName()%>>
		<%}else if (typ.toString().equalsIgnoreCase("datetime")){%>
		<<%getQualifiedName()%>>${child.properties["<%current(1).getFolder()%>:<%getQualifiedName()%>"]?string("yyyy-MM-dd'T'HH:mm:ss")!""}</<%getQualifiedName()%>>
		<%}else{%>
		<<%getQualifiedName()%>>${child.properties["<%current(1).getFolder()%>:<%getQualifiedName()%>"]?string!""}</<%getQualifiedName()%>>
		<%}%>
		</#if>
	<#else/>
	<<%getQualifiedName()%>/>
	</#if>
	<%}%>
	<%for (getAllSourceAssociationEnds()){%>
		<<%eContainer().getAssociationQName(current("AssociationEnd"))%>>
		<#if child.<%eContainer().getAssociationVariableName()%>["<%eContainer().getPrefixedURIAssociationQName(current("AssociationEnd"))%>"]?exists>
		<#list child.<%eContainer().getAssociationVariableName()%>["<%eContainer().getPrefixedURIAssociationQName(current("AssociationEnd"))%>"] as item>
			<noderef>${item.nodeRef}</noderef>
		</#list>
		</#if>
		</<%eContainer().getAssociationQName(current("AssociationEnd"))%>>
	<%}%>
</item>
