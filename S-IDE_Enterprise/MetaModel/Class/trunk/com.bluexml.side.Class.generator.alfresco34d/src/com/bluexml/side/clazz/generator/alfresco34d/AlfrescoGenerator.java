/*
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

*/


package com.bluexml.side.clazz.generator.alfresco34d;

import java.util.List;

import com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator;

public class AlfrescoGenerator extends ClassAlfrescoGenerator {

	@Override
	protected List<String> getTemplates() {
		List<String> templates = super.getTemplates();
		// let form processor build default alfresco forms
		templates.remove("/com.bluexml.side.Class.generator.alfresco/templates/alfrescoshare/DefaultEditForms/web-framework-config-defaults.mt"); //$NON-NLS-1$

		// define configuration for advancedSearch
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/custom-share-config.xml.mt"); //$NON-NLS-1$

		// i18n messages
		templates.remove("/com.bluexml.side.Class.generator.alfresco/templates/alfrescoshare/defaultdocListView/documentlist.get.properties.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/doclist/documentlist.get_de.properties.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/doclist/documentlist.get_es.properties.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/doclist/documentlist.get_fr.properties.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/doclist/documentlist.get_it.properties.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/doclist/documentlist.get.properties.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/changetype/change-type.get_de.properties.mt");
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/changetype/change-type.get_es.properties.mt");
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/changetype/change-type.get_fr.properties.mt");
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/changetype/change-type.get_it.properties.mt");
		
		return templates;

	}

}
