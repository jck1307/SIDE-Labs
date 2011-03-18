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
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
%>

<%script type="common.NamedModelElement" name="getQualifiedName"%>
<%getNamedModelElementQName()%>
<%script type="common.NamedModelElement" name="getPrefixedQualifiedName"%>
<%getPrefixedQName()%>
<%script type="common.NamedModelElement" name="getFolder" description="Get the folder to export" %>
<%if (getRootContainer().name != null && getRootContainer().name.length() > 0){%>
<%getRootContainer().name%><%}else{%>
tmp<%}%>
<%script type="clazz.ClassModelElement" name="getNameSpace"%>
<%getNamespaceURI()%>
<%script type="view.AbstractViewOf" name="getAllSortedAttibutes"%>
<%getFields()[path == null].mapTo.filter("clazz.Attribute").nSort("name")%>
<%script type="view.AbstractViewOf" name="getClassModel"%>
<%viewOf.getRootContainer().filter("clazz.Model")%>

<%script type="clazz.Attribute" name="generateAttributeStatement"%>
<#if (<%args(0)%>.properties["<%getRootContainer().name%>:<%getQualifiedName()%>"]?exists)>
	<#if <%args(0)%>.properties["<%getRootContainer().name%>:<%getQualifiedName()%>"]?is_sequence>
	"<%getQualifiedName()%>":"<#list <%args(0)%>.properties["<%getRootContainer().name%>:<%getQualifiedName()%>"] as key>${key} </#list>"
	<#else/>
	<%if (typ.toString().equalsIgnoreCase("date")){%>
	"<%getQualifiedName()%>":"${<%args(0)%>.properties["<%getRootContainer().name%>:<%getQualifiedName()%>"]?string("yyyy-MM-dd'T'HH:mm:ss.SSSZ")!""}"
	<%}else if (typ.toString().equalsIgnoreCase("datetime")){%>
	"<%getQualifiedName()%>":"${<%args(0)%>.properties["<%getRootContainer().name%>:<%getQualifiedName()%>"]?string("yyyy-MM-dd'T'HH:mm:ss.SSSZ")!""}"
	<%}else{%>
	"<%getQualifiedName()%>":"${<%args(0)%>.properties["<%getRootContainer().name%>:<%getQualifiedName()%>"]?string!""}"
	<%}%>
	</#if>
<#else/>
	"<%getQualifiedName()%>":""
</#if>
