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


package com.bluexml.side.portal.generator.alfresco34d;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.portal.generator.alfresco.PortalAlfrescoGenerator;

public class PortalGenerator extends PortalAlfrescoGenerator {

	protected static String GENERATOR_OPTIONS_XFORMS_34d = "com.bluexml.side.Portal.generator.alfresco.xforms34d";

	@Override
	public Properties buildModuleProperties(String modelId) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Properties props = new Properties();
		props.put("module.id", "SIDE_PortalExtension_" + modelId);
		props.put("module.version", getVersioNumber());
		props.put("module.title", "SIDE portal extension");
		props.put("module.description", "this module contains SIDE generated extension to extends Alfresco Share,\n build at " + sdf.format(now));
		return props;
	}

	@Override
	protected List<String> getTemplates() {
		List<String> result = getCommonsTemplates();

		if (getGeneratorOptionValue(GENERATOR_OPTIONS_DOCLIST)) {
			// replace template to be compliant with alfresco community 3.4
			result.remove("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/documentLibrary/DocumentLibraryPortletView.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco34d/com/bluexml/side/portal/generator/alfresco34d/templates/customViews.ftl.mt");
		}

		result.remove("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/create-site.get.properties.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco34d/com/bluexml/side/portal/generator/alfresco34d/templates/create-site.get.properties.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco34d/com/bluexml/side/portal/generator/alfresco34d/templates/create-site.get_de.properties.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco34d/com/bluexml/side/portal/generator/alfresco34d/templates/create-site.get_fr.properties.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco34d/com/bluexml/side/portal/generator/alfresco34d/templates/create-site.get_es.properties.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco34d/com/bluexml/side/portal/generator/alfresco34d/templates/create-site.get_it.properties.mt");

		// override option definition definition 
		if (getGeneratorOptionValue(GENERATOR_OPTIONS_XFORMS_34d)) {
			// searchForms
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/template.XformSearch.advancedSearch.xml.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco34d/com/bluexml/side/portal/generator/alfresco34d/templates/searchFormCallBack.html.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/XformSearch/XformSearch.get.desc.xml.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/XformSearch/XformSearch.get.head.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/XformSearch/XformSearch.get.html.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/XformSearch/XformSearch.get.js.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/XformSearch/XformSearch.get.properties.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/XformSearch/XformSearch.css.mt");
		}

		return result;
	}

	public String getXFORMURL(EObject o) {
		return getGenerationParameter(GENERATOR_PARAM_XFORMURL);
	}

	public String getSHAREURL(EObject o) {
		return getGenerationParameter(GENERATOR_PARAM_SHAREURL);
	}

	public String getFacetMapURL(EObject o) {
		return getGenerationParameter(GENERATOR_PARAM_FACETMAPURL);
	}
}
