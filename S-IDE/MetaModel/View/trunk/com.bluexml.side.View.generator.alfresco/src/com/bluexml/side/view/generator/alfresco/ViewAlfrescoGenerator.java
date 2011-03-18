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


package com.bluexml.side.view.generator.alfresco;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.bluexml.side.util.generator.alfresco.AbstractAlfrescoGenerator;

public class ViewAlfrescoGenerator extends AbstractAlfrescoGenerator {
	public static String MMUri = "http://www.kerblue.org/view/1.0";
	public static String GENERATOR_OPTIONS_DOCLIST = "alfresco.view.doclist";

	public ViewAlfrescoGenerator() {
		versionProperty = "com.bluexml.side.View.generator.alfresco.module.version";
	}

	@Override
	protected String getMetamodelURI() {
		return MMUri;
	}

	@Override
	protected List<String> getTemplates() {
		List<String> result = new ArrayList<String>();

		// doclist
		result.add("/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates/doclistView/doclist_ftl.mt");

		// data webscripts
		result.add("/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates/webscripts/alfrescoGenerator_template_def_get.mt");
		result.add("/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates/webscripts/alfrescoGenerator_template_def_post.mt");
		result.add("/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates/webscripts/alfrescoGenerator_template_js_get.mt");
		result.add("/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates/webscripts/alfrescoGenerator_template_js_post.mt");
		result.add("/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates/webscripts/json/alfrescoGenerator_template_json.mt");
		result.add("/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates/webscripts/json/alfrescoGenerator_template_result_get.mt");
		result.add("/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates/webscripts/json/alfrescoGenerator_template_result_post.mt");
		result.add("/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates/webscripts/xml/alfrescoGenerator_template_xml.mt");
		result.add("/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates/webscripts/xml/alfrescoGenerator_template_result_get.mt");
		result.add("/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates/webscripts/xml/alfrescoGenerator_template_result_post.mt");
		// result.add("/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates/webscripts/rss/alfrescoGenerator_template_rss.mt");
		// result.add("/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates/webscripts/rss/alfrescoGenerator_template_result_get.mt");
		// result.add("/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates/webscripts/rss/alfrescoGenerator_template_result_post.mt");

		// services :
		monitor.getLog().addServiceLog("Alfresco Share Views", "Share Document Library", getGenerationParameter(CONFIGURATION_PARAMETER_ALFRESCOSHARE_URL) + "/page/site/yourSiteUrl/documentlibrary");

		return result;
	}

	public boolean check() {
		return true;
	}

	@Override
	public Properties buildModuleProperties(String modelId) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Properties props = new Properties();
		props.put("module.id", "SIDE_ViewExtension_" + modelId);
		props.put("module.version", getVersioNumber());
		props.put("module.title", "SIDE view extension");
		props.put("module.description", "this module contains SIDE generated extension to extends Alfresco view,\n build at " + sdf.format(now));
		return props;
	}

}
