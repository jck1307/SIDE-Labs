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
metamodel http://www.kerblue.org/form/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.form.generator.alfresco34d.FormGenerator
 
%>

  
<%script type="form.ClassFormCollection" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.core.messages")%>/<%getRootPackage().name%>.properties<%}%>

<%script type="form.ClassFormCollection" name="generate" file="<%fileName()%>" %>
<%for (forms.filter("FormClass")){%>
	<%for (getFields()[ref != null && label != null && label != ""]){%>
form.field.label.<%ref.getPrefixedQName("_")%>=<%label%>
<%if (help_text != null && help_text != ""){%>form.field.help.<%ref.getPrefixedQName("_")%>=<%help_text%>
<%}%>
<%if (description != null && description != ""){%>form.field.description.<%ref.getPrefixedQName("_")%>=<%description%>
<%}%>
	<%}%>
	<%for (getAllSubGroups()){%>
form.group.<%id%>=<%label%>
	<%}%>
<%}%>
