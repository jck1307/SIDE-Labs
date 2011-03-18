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
import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import templates.servicesTemplates.Association

%>

<%script type="clazz.ClassPackage" name="validatedFileName"%>
<%if (eContainer() == null) {%><%getConfModulePath()%>/synchronization-database-mapping.properties<%}%>

<%script type="clazz.ClassPackage" name="package" file="<%validatedFileName%>" %>
# ENUMERATIONS
<%for  (getAllEnumerations().nSort("name")){%><%if (dynamic){%>
class.name.<%getQualifiedName%>=<%name%>
class.attribute.name.<%getQualifiedName%>.<%getQualifiedName%>_code=code
class.attribute.name.<%getQualifiedName%>.<%getQualifiedName%>_label=label
<%}%><%}%> <%-- END for (enumerationSet) --%>

# ASPECTS
<%for (getAllAspects().nSort("name")) {%>
aspect.name.<%getQualifiedName%>=<%name%>
<%}%>

# CLASSES AND ATTRIBUTES
<%for (getAllClasses().nSort("name")){%>
class.name.<%getQualifiedName%>=<%getClassTableName()%>
<%for (getAllSortedAttibutes()){%>
class.attribute.name.<%current("Clazz").getQualifiedName%>.<%getQualifiedName()%>=<%if (eContainer() != current("Clazz")){%><%eContainer().getClassTableName()%>_<%}%><%name%>
<%}%>
class.attribute.name.<%current("Clazz").getQualifiedName%>.node-dbid=id
class.attribute.name.<%current("Clazz").getQualifiedName%>.node-uuid=uuid
<%}%>
# STATIC
class.name.Litteral=Litteral
class.attribute.name.Litteral.Litteral_code=code
class.name.EnumerationType=EnumerationType
class.attribute.name.EnumerationType.EnumerationType_name=name
class.name.LitteralTranslation=LitteralTranslation
class.attribute.name.LitteralTranslation.LitteralTranslation_lang=lang
class.attribute.name.LitteralTranslation.LitteralTranslation_value=value
class.name.VisibilityContext=VisibilityContext
class.attribute.name.VisibilityContext.VisibilityContext_value=value

# ASSOCIATIONS
<%for (getAllAssociations().nSort("name")){%>
#association <%firstEnd.linkedClass.name%>/<%name%>/<%secondEnd.linkedClass.name%>

<%if (secondEnd.navigable){%>
association.name.<%getQualifiedName(firstEnd)%>=<%getAssociationTableName(firstEnd)%>
association.source.<%getQualifiedName(firstEnd)%>=<%firstEnd.linkedClass.name%>
<%if (firstEnd.name != ""){%>
association.source.alias.<%getQualifiedName(firstEnd)%>=<%firstEnd.name%>
<%}%>
association.target.<%getQualifiedName(firstEnd)%>=<%secondEnd.linkedClass.name%>
<%if (secondEnd.name != ""){%>
association.target.alias.<%getQualifiedName(firstEnd)%>=<%secondEnd.name%>
<%}%>
<%}%>

<%if (firstEnd.navigable){%>
association.name.<%getQualifiedName(secondEnd)%>=<%getAssociationTableName(secondEnd)%>
association.source.<%getQualifiedName(secondEnd)%>=<%secondEnd.linkedClass.name%>
<%if (secondEnd.name != ""){%>
association.source.alias.<%getQualifiedName(secondEnd)%>=<%secondEnd.name%>
<%}%>
association.target.<%getQualifiedName(secondEnd)%>=<%firstEnd.linkedClass.name%>
<%if (firstEnd.name != ""){%>
association.target.alias.<%getQualifiedName(secondEnd)%>=<%firstEnd.name%>
<%}%>
<%}%>

<%}%>


<%script type="clazz.Enumeration" name="getEnumerationTableName" post="trim()"%>
<%name%>

<%script type="clazz.AbstractClass" name="getClassTableName" post="trim()"%>
<%if (tags[key == "table_name"]){%>
<%tags[key == "table_name"].nFirst().value%>
<%}else{%>
<%name%>
<%}%>
 
<%-- The first argument is the source association-end of the association--%>
<%script type="clazz.Association" name="getAssociationTableName" post="trim()"%>
<%if (tags[key == "table_name"]){%>
<%tags[key == "table_name"].nFirst().value%>
<%}else{%>
<%-- args(0) is the SOURCE association end --%>
<%args(0).linkedClass.name%>_<%name%><%if (args(0).getOpposite().name != ""){%>_<%args(0).getOpposite().name%><%}%>_<%args(0).getOpposite().linkedClass.name%>
<%}%>
