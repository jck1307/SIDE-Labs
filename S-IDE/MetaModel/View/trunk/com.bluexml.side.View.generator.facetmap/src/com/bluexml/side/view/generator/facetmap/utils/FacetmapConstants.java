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


/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2009
 *
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package com.bluexml.side.view.generator.facetmap.utils;

import java.io.File;

/**
 * @author pbertrand
 *
 */
public interface FacetmapConstants {
	public static final String FILESEP = File.separator;
	//Paths of deployment
	public static final String tomcat = "tomcat" + FILESEP;
	public static final String webapps = "webapps" + FILESEP;
	public static final String webinf = "WEB-INF" + FILESEP;
	public static final String display = "display" + FILESEP;
	public static final String includes = "includes" + FILESEP;
	public static final String xsl = "xsl" + FILESEP;
	public static final String alfresco = "alfresco" + FILESEP;
	//Paths of generation
	public static final String common = "common" + FILESEP;
	public static final String facets = "facets" + FILESEP;
	public static final String facetmap = "facetmap" + FILESEP;
	public static final String scriptcmis = "scriptcmis" + FILESEP;
	//Filename common
	public static final String cmis2xfml_filename = "cmis2xfml.xsl";
	public static final String cmisjs_filename = "doclist_user.get.js";
	public static final String cmis2xfml_properties_filename = "cmisTransformProperties.xml";
	public static final String facetmap_configurationproperties_filename = "configuration.properties";
	//Filename common
	public static final String buildProperties_filename = "buildProperties.xml";
	public static final String basicFacets_filename = "basic-Facets.xsl";
	public static final String rightnav_properties_filename = "Rightnav.xsl";
	//Params
	public static final String WEBAPP_FACETS = "facetmap-facets";
	public static final String WEBAPP_CONTENT = "facetmap-content";
	public static final String CONFIGURATION_TOMCAT_INSTALLATION = "CATALINA_HOME";
}
