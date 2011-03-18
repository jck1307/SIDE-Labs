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
metamodel http://www.kerblue.org/view/1.0

import com.bluexml.side.view.generator.alfresco.templates.services.common

import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>

<%script type="view.AbstractViewOf" name="validatedFilename"%>
<%if (eContainer() == getRootContainer()){%>webapps/alfresco/WEB-INF/classes/alfresco/webscripts/extension/com/bluexml/side/webscript/data/<%name%>/<%name%>.post.js<%}%>
<%script type="view.AbstractViewOf" name="alfrescoGenerator" file="<%validatedFilename%>"%>
var myNode = null;
if (argsM["nodeRef"] != null) {
	var myNodeRef="workspace://SpacesStore/"+argsM["nodeRef"][0];
	myNode = search.findNode(myNodeRef);
	var xpath = "./*[subtypeOf('<%viewOf.getFolder()%>:<%viewOf.getQualifiedName()%>')]";
	model.records = myNode.childrenByXPath(xpath);
} else {
	var lucene="TYPE:\"{<%viewOf.getNameSpace()%>}<%viewOf.getQualifiedName()%>\"";
    model.records = search.luceneSearch(lucene);
}

if (myNode != null) {
  model.<%viewOf.getQualifiedName()%>_list = myNode.childrenByXPath("./*[subtypeOf('<%viewOf.getFolder()%>:<%viewOf.getQualifiedName()%>')]");
} else {
  var lucene="TYPE:\"{<%viewOf.getNameSpace()%>}<%viewOf.getQualifiedName()%>\"";
  model.<%viewOf.getQualifiedName()%>_list = search.luceneSearch(lucene);
}
