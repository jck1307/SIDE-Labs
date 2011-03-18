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
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.form.generator.alfresco34d.FormGenerator
 
%>

  
<%script type="form.ClassFormCollection" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext")%>/<%getModuleIdService(getRootPackage().name)%>/share-forms-config.xml<%}%>

<%script type="form.ClassFormCollection" name="generate" file="<%fileName()%>" %>
<alfresco-config>

<%for (forms.filter("FormClass")){%>
	<config evaluator="node-type" condition="<%real_class.getPrefixedQName()%>">
		<forms>
			<form<%if (label != real_class.title){%> id="form.<%current("ClassFormCollection").name%>.<%id%>"<%}%>>
			<%if (presentation.toString() == "tabbed" || presentation.toString() == "auto"){%>
				<edit-form template="/fdk/templates/tab-edit-form.ftl" />
			<%}%>
			
				<field-visibility>
					<%for (getFields()){%>
					<%if (ref.filter("clazz.Attribute")){%>
					<show id="<%ref.getPrefixedQName()%>" />
					<%}%>
					<%for (ref.filter("clazz.Association")){%>
						<%if (firstEnd.linkedClass == current("Clazz")){%>
 					<show id="<%getPrefixedAssociationQName(secondEnd)%>" />
 						<%}else{%>
 					<show id="<%getPrefixedAssociationQName(firstEnd)%>" />
						<%}%>
					<%}%>
					
					<%}%>
				</field-visibility>
				<appearance>
					<%-- generate sets --%>
					<%for (getAllSubGroups()){%>
						<set id="<%id%>" label-id="form.group.<%id%>" />
						<%for (children[filter("Field") != null]){%>
						<%if (ref.filter("clazz.Attribute")){%>
							<%ref.getPrefixedQName().put("id")%>
						<%}%>
						<%for (ref.filter("clazz.Association")){%>
							<%if (firstEnd.linkedClass == current("Clazz")){%>
	 					<%getPrefixedAssociationQName(secondEnd).put("id")%>
	 						<%}else{%>
	 					<%getPrefixedAssociationQName(firstEnd).put("id")%>
							<%}%>
						<%}%>
						<field id="<%get("id")%>" set="<%current("FormGroup").id%>" 
						label-id="form.field.label.<%ref.getPrefixedQName("_")%>"
						 <%if (help_text != null && help_text != ""){%>
						 help-id="form.field.help.<%ref.getPrefixedQName("_")%>"
						 <%}%>
						 <%if (description != null && description != ""){%>
						 description-id="form.field.description.<%ref.getPrefixedQName("_")%>"
						 <%}%>
						 />
						<%}%>
						
					<%}%>
				</appearance>
			</form>
		</forms>
	</config>
<%}%>

</alfresco-config>
