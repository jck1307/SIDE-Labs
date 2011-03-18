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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Defines tasks that should be performed after the packaging.
 *
 * @author Stephane Nicoll
 */
public interface AmpPostPackagingTask
{

    /**
     * Executes the post packaging task.
     * <p/>
     * The packaging context hold all information regarding the webapp that
     * has been packaged.
     *
     * @param context the packaging context
     * @throws MojoExecutionException if an error occured
     * @throws MojoFailureException   if a falure occured
     */
    void performPostPackaging( AmpPackagingContext context )
        throws MojoExecutionException, MojoFailureException;

}
