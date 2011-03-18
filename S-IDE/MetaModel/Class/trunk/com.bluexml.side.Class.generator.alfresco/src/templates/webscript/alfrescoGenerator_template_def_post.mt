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
import com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator
%>

<%script type="clazz.Clazz" name="validatedFilename"%>
<%getProperty("alf.paths.extension.side.webscripts")%>/<%getFolder()%>/<%getQualifiedName().replaceAll("_","/")%>/<%getQualifiedName()%>.post.desc.xml
<%script type="clazz.Clazz" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version="1.0" encoding="ISO-8859-1"?>
<webscript>
  <shortname>All <%getLabel()%>s</shortname>
  <description><![CDATA[
This service is used to get <%getLabel()%>s contents<br/>
originally designed for birt report<br/>
<br/>
Parameters :<br/>
nodeRef : <br/>
years 	:<br/>
months 	:<br/>
days 	:<br/>
hours 	:<br/>
minutes	:<br/>
]]>
  </description>
  <url>/com/bluexml/side/contentType/<%getFolder()%>/<%getQualifiedName()%>?nodeRef={nodeRef}&amp;years={years}&amp;months={months}&amp;days={days}&amp;hours={hours}&amp;minutes={minutes}</url>
  <format default="json">any</format>
  <%if (getRunasforReport() != null && getRunasforReport() != ""){%>
  <authentication runas="<%getRunasforReport()%>">guest</authentication>
  <%}else{%>
  <authentication>user</authentication>
  <%}%>
  <family>SIDE</family>
</webscript>
