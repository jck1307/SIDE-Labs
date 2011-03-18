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


package org.alfresco.maven.plugin.amp.util;

import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Serializes {@link AmpStructure} back and forth.
 *
 * @author Stephane Nicoll
 */
public class AmpStructureSerializer
{

    private final XStream xStream;

    /**
     * Creates a new instance of the serializer.
     */
    public AmpStructureSerializer()
    {
        this.xStream = new XStream();

        // Register aliases
        xStream.alias( "webapp-structure", AmpStructure.class );
        xStream.alias( "path-set", PathSet.class );
    }


    /**
     * Reads the {@link AmpStructure} from the specified file.
     *
     * @param file the file containing the webapp structure
     * @return the webapp structure
     * @throws IOException if an error occured while reading the structure
     */
    public AmpStructure fromXml( File file )
        throws IOException
    {
        FileReader reader = null;

        try
        {
            reader = new FileReader( file );
            return (AmpStructure) xStream.fromXML( reader );
        }
        finally
        {
            if ( reader != null )
            {
                reader.close();
            }
        }
    }

    /**
     * Saves the {@link AmpStructure} to the specified file.
     *
     * @param webappStructure the structure to save
     * @param targetFile      the file to use to save the structure
     * @throws IOException if an error occured while saving the webapp structure
     */
    public void toXml( AmpStructure webappStructure, File targetFile )
        throws IOException
    {
        FileWriter writer = null;
        try
        {
            if ( !targetFile.getParentFile().exists() && !targetFile.getParentFile().mkdirs() )
            {
                throw new IOException(
                    "Could not create parent[" + targetFile.getParentFile().getAbsolutePath() + "]" );
            }

            if ( !targetFile.exists() && !targetFile.createNewFile() )
            {
                throw new IOException( "Could not create file[" + targetFile.getAbsolutePath() + "]" );
            }
            writer = new FileWriter( targetFile );
            xStream.toXML( webappStructure, writer );
        }
        finally
        {
            if ( writer != null )
            {
                writer.close();
            }
        }
    }
}
