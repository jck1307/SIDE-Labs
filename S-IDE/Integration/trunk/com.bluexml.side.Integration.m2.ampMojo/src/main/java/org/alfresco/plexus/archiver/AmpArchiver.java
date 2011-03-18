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


package org.alfresco.plexus.archiver;

import java.io.File;
import java.io.IOException;
import org.codehaus.plexus.archiver.*;
import org.codehaus.plexus.archiver.jar.JarArchiver;
import org.codehaus.plexus.archiver.zip.ZipOutputStream;

public class AmpArchiver extends JarArchiver
{

    public AmpArchiver()
    {
        super.archiveType = "amp";
    }

    public void setModuleProperties(File descr)
    throws ArchiverException
    {
//        deploymentDescriptor = descr;
//        if(!deploymentDescriptor.exists())
//        {
//            throw new ArchiverException("Deployment descriptor: " + deploymentDescriptor + " does not exist.");
//        } else
//        {
//            addFile(descr, "config/AMP-INF/module.properties");
//            return;
//        }
    }

    public void addLib(File fileName)
        throws ArchiverException
    {
        addDirectory(fileName.getParentFile(), "lib/", new String[] {
            fileName.getName()
        }, null);
    }

    public void addLibs(File directoryName, String includes[], String excludes[])
        throws ArchiverException
    {
        addDirectory(directoryName, "lib/", includes, excludes);
    }

    public void addClass(File fileName)
        throws ArchiverException
    {
        addDirectory(fileName.getParentFile(), "classes/", new String[] {
            fileName.getName()
        }, null);
    }

    public void addClasses(File directoryName, String includes[], String excludes[])
        throws ArchiverException
    {
        addDirectory(directoryName, "classes/", includes, excludes);
    }


    protected void initZipOutputStream(ZipOutputStream zOut)
        throws IOException, ArchiverException
    {
//        if(deploymentDescriptor == null && !isInUpdateMode())
//        {
//            throw new ArchiverException("module properies attribute is required");
//        } 
//        else
//        {
            super.initZipOutputStream(zOut);
            return;
//        }
    }

    protected void zipFile(ArchiveEntry entry, ZipOutputStream zOut, String vPath, int mode)
        throws IOException, ArchiverException
    {
        if(vPath.equalsIgnoreCase("config/AMP-INF/module.properties"))
        {
            if(deploymentDescriptor == null || !deploymentDescriptor.getCanonicalPath().equals(entry.getFile().getCanonicalPath()) || descriptorAdded)
            {
                getLogger().warn("Warning: selected " + super.archiveType + " files include a config/AMP-INF/module.properites which will be ignored " + "(please use webxml attribute to " + super.archiveType + " task)");
            } 
            else
            {
              super.zipFile(entry, zOut, vPath);
                descriptorAdded = true;
            }
        } 
        else
        {
            super.zipFile(entry, zOut, vPath);
        }
    }

    
    protected void cleanUp()
    {
        descriptorAdded = false;
        super.cleanUp();
    }

    private File deploymentDescriptor;
    private boolean descriptorAdded;
    /**
     * @see org.codehaus.plexus.archiver.AbstractArchiver#addDirectory(java.io.File, java.lang.String, java.lang.String[], java.lang.String[])
     */
    public void addDirectory(File pArg0, String pArg1, String[] pArg2, String[] pArg3)
        throws ArchiverException
    {
        /* */
        getLogger().info("adding directory [ '"+pArg0+"' '"+pArg1+"']");
        super.addDirectory(pArg0, pArg1, pArg2, pArg3);
    }
}
