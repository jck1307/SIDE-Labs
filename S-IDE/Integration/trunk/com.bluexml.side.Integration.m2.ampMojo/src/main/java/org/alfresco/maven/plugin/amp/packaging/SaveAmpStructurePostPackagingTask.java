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
import org.alfresco.maven.plugin.amp.util.AmpStructureSerializer;

import java.io.File;
import java.io.IOException;

/**
 * Saves the webapp structure cache.
 *
 * @author Stephane Nicoll
 */
public class SaveAmpStructurePostPackagingTask
    implements AmpPostPackagingTask
{

    private final File targetFile;

    private final AmpStructureSerializer serialier;


    public SaveAmpStructurePostPackagingTask( File targetFile )
    {
        this.targetFile = targetFile;
        this.serialier = new AmpStructureSerializer();
    }

    public void performPostPackaging( AmpPackagingContext context )
        throws MojoExecutionException, MojoFailureException
    {
        if ( targetFile == null )
        {
            context.getLog().debug( "Cache usage is disabled, not saving webapp structure." );
        }
        else
        {
            try
            {
                serialier.toXml( context.getAmpStructure(), targetFile );
                context.getLog().debug( "Cache saved successfully." );
            }
            catch ( IOException e )
            {
                throw new MojoExecutionException( "Could not save webapp structure", e );
            }
        }
    }
}
