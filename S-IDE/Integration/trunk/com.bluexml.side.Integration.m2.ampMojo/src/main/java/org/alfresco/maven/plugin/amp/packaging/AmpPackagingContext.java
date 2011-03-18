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

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.maven.archiver.MavenArchiveConfiguration;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.alfresco.maven.plugin.amp.util.AmpStructure;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.archiver.jar.JarArchiver;
import org.codehaus.plexus.archiver.manager.ArchiverManager;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * The packaging context  of the AMP
 *
 * @author Stephane Nicoll - Modified version for AMP
 * 
 */
public interface AmpPackagingContext
{
    /**
     * Returns the maven project.
     *
     * @return the project
     */
    MavenProject getProject();

    /**
     * Returns the webapp directory. Packaging tasks should use this
     * directory to generate the webapp.
     *
     * @return the webapp directory
     */
    File getAmpDirectory();

     /**
     * Returns the AMP classes + resources folder 
     *
     * @return the webapp source directory
     */
    File getAmpConfigDirectory();

    
    /**
     * Returns the AMP web directory.
     *
     * @return the webapp source directory
     */
    File getAmpWebDirectory();

    /**
     * Returns the webapp source includes.
     *
     * @return the webapp source includes
     */
    String[] getAmpJarIncludes();

    /**
     * Returns the webapp source excludes.
     *
     * @return the webapp source excludes
     */
    String[] getAmpJarExcludes();

    
    /**
     * Returns the AMP web/ includes.
     *
     * @return the AMP web/ includes
     */
    String[] getAmpWebIncludes();
    
    /**
     * Returns the AMP web/ excludes.
     *
     * @return the AMP web/ excludes
     */
    String[] getAmpWebExcludes();
    
    
    /**
     * Returns the directory holding generated classes to be packed in the jar - By default is the same of the AMP configuration
     *
     * @return the classes directory
     */
    File getClassesDirectory();


    /**
     * Returns the logger to use to output logging event.
     *
     * @return the logger
     */
    Log getLog();

    /**
     * Returns the directory to unpack dependent WARs into if needed.
     *
     * @return the overlays work directory
     */
    File getOverlaysWorkDirectory();

    /**
     * Returns the archiver manager to use.
     *
     * @return the archiver manager
     */
    ArchiverManager getArchiverManager();

    /**
     * The maven archive configuration to use.
     *
     * @return the maven archive configuration
     */
    MavenArchiveConfiguration getArchive();

    /**
     * Returns the Jar archiver needed for archiving classes directory into
     * jar file under WEB-INF/lib.
     *
     * @return the jar archiver to user
     */
    JarArchiver getJarArchiver();

    /**
     * Returns the output file name mapping to use, if any. Returns <tt>null</tt>
     * if no file name mapping is set.
     *
     * @return the output file name mapping or <tt>null</tt>
     */
    String getOutputFileNameMapping();

    /**
     * Returns the list of filter files to use.
     *
     * @return a list of filter files
     */
    List getFilters();

    /**
     * Returns the filter properties to use to filter resources.
     * <p/>
     * TODO: this needs to be refactored to use the resource plugin somehow.
     *
     * @return a map of filter properties
     * @throws MojoExecutionException if an error occured while reading a filter file
     */
    Map getFilterProperties()
        throws MojoExecutionException;

    /**
     * Returns the {@link AmpStructure}.
     *
     * @return the webapp structure
     */
    AmpStructure getAmpStructure();

    /**
     * Returns the list of registered overlays for this session. This list might
     * differ from the one returned by the cache; in this case, it means that the
     * project's configuration has changed. The plugin will handle thos cases nicely
     * but it would be better in general to invoke the clean goal.
     *
     * @return the list of registered overlays, including the current project
     */
    List getOwnerIds();

}
