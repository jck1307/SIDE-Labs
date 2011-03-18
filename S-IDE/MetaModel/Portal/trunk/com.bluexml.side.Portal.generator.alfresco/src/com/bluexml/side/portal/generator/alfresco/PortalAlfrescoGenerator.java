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


package com.bluexml.side.portal.generator.alfresco;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.util.generator.alfresco.AbstractAlfrescoGenerator;

public class PortalAlfrescoGenerator extends AbstractAlfrescoGenerator {

	protected static String GENERATOR_PARAM_SHAREURL = "alfresco.share.url";
	protected static String GENERATOR_PARAM_FACETMAPURL = "facetMap.url";
	protected static String GENERATOR_PARAM_XFORMURL = "com.bluexml.side.Form.generator.xforms.chiba.webappContext";

	protected static String MMUri = "http://www.kerblue.org/portal/1.0";
	protected static String GENERATOR_OPTIONS_DOCLIST = "com.bluexml.side.Portal.generator.alfresco.doclist";
	protected static String GENERATOR_OPTIONS_FORMS = "com.bluexml.side.Portal.generator.alfresco.forms";
	protected static String GENERATOR_OPTIONS_FACETMAP = "com.bluexml.side.Portal.generator.alfresco.facetmap";
	protected static String GENERATOR_OPTIONS_XFORMS = "com.bluexml.side.Portal.generator.alfresco.xforms";

	
	public PortalAlfrescoGenerator() {
		versionProperty = "com.bluexml.side.Portal.generator.alfresco.module.version"; //$NON-NLS-1$
	}
	
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

	public boolean check() {
		return true;
	}

	@Override
	protected String getMetamodelURI() {
		return MMUri;
	}

	@Override
	protected List<String> getTemplates() {
		
		List<String> result = getCommonsTemplates();

		// shared resources
		// includes of GENERATOR_OPTIONS_FORMS and GENERATOR_OPTIONS_FACETMAP
		// options
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/shared/web-framework-config-custom.mt");
		return result;
	}

	protected List<String> getCommonsTemplates() {
		List<String> result = new ArrayList<String>();
		if (getGeneratorOptionValue(GENERATOR_OPTIONS_DOCLIST)) {
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/documentLibrary/DocumentLibraryPortletView.ftl.mt");
		}
		// if (getGeneratorOptionValue(GENERATOR_OPTIONS_FORMS)) {
		// see web-framework-config-custom.mt
		// }

		// general templates, pages, navigation component
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/presets.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/page.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/css.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/template-instances.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/title.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/navigation.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/shareComponents.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/template.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/template_js.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/messages.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/create-site.get.properties.mt");

		if (getGeneratorOptionValue(GENERATOR_OPTIONS_FACETMAP)) {
			// result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/web-framework-config-custom.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/facetMapIntegration/template.facetMap.xml.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/facetMapIntegration/facetMap/facetMap.get.desc.xml.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/facetMapIntegration/facetMap/facetMap.get.head.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/facetMapIntegration/facetMap/facetMap.get.html.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/facetMapIntegration/facetMap/facetMap.get.js.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/facetMapIntegration/facetMap/facetMap.css.mt");
		}
		if (getGeneratorOptionValue(GENERATOR_OPTIONS_XFORMS)) {
			// XForm portlet
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/Xform/Xform.get.desc.xml.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/Xform/Xform.get.head.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/Xform/Xform.get.html.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/Xform/Xform.get.js.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/Xform/Xform.get.properties.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/Xform/Xform.css.mt");
			// callback page (redirect to share page after editing)
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/edit-metadataCallBack.html.mt");
			// override edit-metadata page template
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/templates-edit-metadataXForm.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/edit-metadata.xml.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/template.edit-metadata-XForm.edit-metadata.xml.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/edit-metadata-mgr.get.html.ftl.mt");

			// searchForms
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/template.XformSearch.advancedSearch.xml.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/searchFormCallBack.html.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/XformSearch/XformSearch.get.desc.xml.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/XformSearch/XformSearch.get.head.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/XformSearch/XformSearch.get.html.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/XformSearch/XformSearch.get.js.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/XformSearch/XformSearch.get.properties.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/XformSearch/XformSearch.css.mt");
		}
		return result;
	}

	public static boolean getGeneratorOptionValue(EObject o, String key) {
		return getGeneratorOptionValue(key);
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
