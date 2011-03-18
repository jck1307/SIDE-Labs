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


package com.bluexml.side.integration.buildHudson.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class SVNCommandGenerator {
	private Logger logger = Logger.getLogger(getClass());
	BuilderUtils bu;
	Date launchDate;
	List<String> listeProjetPomsModif = new ArrayList<String>();
	List<String> listePluginModif = new ArrayList<String>();
	List<String> listeFeatureModif = new ArrayList<String>();

	public SVNCommandGenerator(BuilderUtils bu, Date launchDate, List<String> listeProjetPomsModif, List<String> listePluginModif, List<String> listeFeatureModif) throws Exception {
		this.bu = bu;
		this.launchDate = launchDate;
		this.listeFeatureModif = listeFeatureModif;
		this.listePluginModif = listePluginModif;
		this.listeProjetPomsModif = listeProjetPomsModif;
		bu.getProductFile();
	}

	public void createAntFile() throws Exception {
		BuilderUtils.createFile(getCorpsSVN(), bu.getBuildPath(), "buildSVN.xml");
	}

	/**
	 * Retourne le corps du fichier buildSVN.xml
	 * @throws Exception 
	 */
	private String getCorpsSVN() throws Exception {
		String out = "<?xml version=\"1.0\"?>\n";
		out += "<project name=\"build\" default=\"svnCommit\">\n";
		out += "\t<property file=\"build.properties\" />\n";
		out += "\t<property name=\"antLib\" value=\"" + bu.getBuildPath() + File.separator + "lib\" />\n\n";
		out += "\t<!-- load the svn task -->\n";
		out += "\t<path id=\"project.classpath.ant\">\n";
		out += "\t\t<pathelement location=\"${antLib}" + File.separator + "svnant.jar\" />\n";
		out += "\t\t<pathelement location=\"${antLib}" + File.separator + "svnClientAdapter.jar\" />\n";
		out += "\t\t<pathelement location=\"${antLib}" + File.separator + "svnjavahl.jar\" />\n";
		out += "\t</path>\n";
		out += "\t<taskdef resource=\"svntask.properties\" classpathref=\"project.classpath.ant\" />\n";

		out += getTargetSvnCommit();

		out += "</project>\n";
		return out;
	}

	/**
	 * Retourne le corps de la target svnUD
	 * @throws Exception 
	 */
	private String getTargetSvnCommit() throws Exception {

		String out = "\n\t<!-- ================================= \n";
		out += "\t\t\ttarget: svnCommit\n";
		out += "\t================================= -->\n\n";
		out += "\t<target name=\"svnCommit\" depends=\"\" description=\"description\">\n";

		if (listeFeatureModif.size() > 0 || listePluginModif.size() > 0 || listeProjetPomsModif.size() > 0) {

			out += "\t\t<svn username=\"build-auto\" password=\"build.auto\">\n";
			out += "\t\t\t<commit message=\"buildAuto du " + BuilderUtils.getFormatedDate(launchDate) + "\">\n";

			// fichier pom.xml
			for (String pom : this.listeProjetPomsModif) {
				String[] tab = pom.split("/pom.xml");

				out += "\t\t\t<fileset dir=\"" + tab[0] + "\">\n";
				out += "\t\t\t\t<include name=\"pom.xml\" />\n";
				out += "\t\t\t</fileset>\n";
				logger.debug("reccord " + tab[0] + " for commit");
			}

			// plugins
			for (String pluginId : this.listePluginModif) {
				out += "\t\t\t<fileset dir=\"" + bu.getPathToLocalCopy(pluginId) + File.separator + "META-INF\">\n";
				out += "\t\t\t\t<include name=\"MANIFEST.MF\" />\n";
				out += "\t\t\t</fileset>\n";

				String fileFeaturePath = bu.getPathToLocalCopy(pluginId) + File.separator + "plugin.xml";

				boolean exists = (new File(fileFeaturePath)).exists();
				if (exists) {
					logger.debug("reccord " + pluginId + " for commit");
					out += "\t\t\t<fileset dir=\"" + bu.getPathToLocalCopy(pluginId) + "\">\n";
					out += "\t\t\t\t<include name=\"plugin.xml\" />\n";
					if (pluginId.contains("branding")) {
						// product
						out += "\t\t\t\t<include name=\"side.product\" />\n";
						out += "\t\t\t\t<include name=\"category.xml\" />\n";
					}
					out += "\t\t\t</fileset>\n";
				}
			}

			// features
			for (String featureId : this.listeFeatureModif) {
				logger.debug("reccord " + featureId + " for commit");
				out += "\t\t\t<fileset dir=\"" + bu.getPathToLocalCopy(featureId) + "\">\n";
				out += "\t\t\t\t<include name=\"feature.xml\" />\n";
				out += "\t\t\t</fileset>\n";
			}

			out += "\t\t\t</commit>\n";
			out += "\t\t</svn>\n";

		} else {
			out += "\t\t<echo message=\"nothing to commit\" />\n";

		}
		out += "\t</target>\n";
		return out;
	}
}
