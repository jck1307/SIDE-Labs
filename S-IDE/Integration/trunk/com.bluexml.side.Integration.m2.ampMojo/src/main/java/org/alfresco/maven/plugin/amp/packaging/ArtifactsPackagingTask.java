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


package org.alfresco.maven.plugin.amp.packaging;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.resolver.filter.ScopeArtifactFilter;
import org.apache.maven.plugin.MojoExecutionException;
import org.alfresco.maven.plugin.amp.Overlay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Handles the artifacts that needs to be packaged in the web application.
 * 
 * @author Stephane Nicoll
 */
public class ArtifactsPackagingTask extends AbstractAmpPackagingTask {

	public static final String LIB_PATH = "lib/";

	private final Set artifacts;

	private final String id;

	public ArtifactsPackagingTask(Set artifacts) {
		this.artifacts = artifacts;
		this.id = Overlay.currentProjectInstance().getId();
		
	}

	public void performPackaging(AmpPackagingContext context) throws MojoExecutionException {

		final ScopeArtifactFilter filter = new ScopeArtifactFilter(Artifact.SCOPE_RUNTIME);
		final List duplicates = findDuplicates(context, artifacts);
		for (Iterator iter = artifacts.iterator(); iter.hasNext();) {
			Artifact artifact = (Artifact) iter.next();
			String targetFileName = getArtifactFinalName(context, artifact);
			
			context.getLog().debug("Processing: " + targetFileName);

			if (duplicates.contains(targetFileName)) {
				context.getLog().debug("Duplicate found: " + targetFileName);
				targetFileName = artifact.getGroupId() + "-" + targetFileName;
				context.getLog().debug("Renamed to: " + targetFileName);
			}

			if (!artifact.isOptional() && filter.include(artifact)) {
				try {
					
					String type = artifact.getType();
					context.getLog().info("--------------------------------------------------------------");
					context.getLog().info("DEBUG �� = artifact groupId="+artifact.getGroupId());
					context.getLog().info("DEBUG �� = artifact artifactId="+artifact.getArtifactId());
					context.getLog().info("DEBUG �� = artifact Version="+artifact.getVersion());
					context.getLog().info("DEBUG �� = artifact Type="+type);
					context.getLog().info("DEBUG �� = artifact Scope="+artifact.getScope());
					context.getLog().info("--------------------------------------------------------------");
					if ("tld".equals(type)) {
						// copyFile( id, context, artifact.getFile(), TLD_PATH +
						// targetFileName );
					} else if ("aar".equals(type)) {
						// copyFile( id, context, artifact.getFile(),
						// SERVICES_PATH + targetFileName );
					} else if ("jar".equals(type) || "ejb".equals(type) || "ejb-client".equals(type) || "test-jar".equals(type)) {
						if (artifact.getScope().equals(Artifact.SCOPE_PROVIDED)) {
							context.getLog().info("DEBUG �� = Artifact Scope="+Artifact.SCOPE_PROVIDED+",so associated ressource will not included");
						} else {
							copyFile(id, context, artifact.getFile(), LIB_PATH + targetFileName);
							context.getLog().info("DEBUG �� = Artifact adding  " + artifact.getId() + " to AMP in " + LIB_PATH);
						}
						
					} else if ("par".equals(type)) {
						targetFileName = targetFileName.substring(0, targetFileName.lastIndexOf('.')) + ".jar";
						copyFile(id, context, artifact.getFile(), LIB_PATH + targetFileName);
					} else if ("war".equals(type)) {
						// Nothing to do here, it is an overlay and it's already
						// handled
					} else if ("zip".equals(type)) {
						// Nothing to do here, it is an overlay and it's already
						// handled
					} else if ("amp".equals(type)) {
						context.getLog().debug("skipping " + artifact.getId() + " in artifacts packaging phase. Will be processed in overlay");
					} else {
						context.getLog().debug("Artifact of type[" + type + "] is not supported, ignoring[" + artifact + "]");
					}
				} catch (IOException e) {
					throw new MojoExecutionException("Failed to copy file for artifact[" + artifact + "]", e);
				}
			}
		}
	}

	/**
	 * Searches a set of artifacts for duplicate filenames and returns a list of
	 * duplicates.
	 * 
	 * @param context
	 *            the packaging context
	 * @param artifacts
	 *            set of artifacts
	 * @return List of duplicated artifacts as bundling file names
	 */
	private List findDuplicates(AmpPackagingContext context, Set artifacts) {
		List duplicates = new ArrayList();
		List identifiers = new ArrayList();
		for (Iterator iter = artifacts.iterator(); iter.hasNext();) {
			Artifact artifact = (Artifact) iter.next();
			String candidate = getArtifactFinalName(context, artifact);
			if (identifiers.contains(candidate)) {
				duplicates.add(candidate);
			} else {
				identifiers.add(candidate);
			}
		}
		return duplicates;
	}
}
