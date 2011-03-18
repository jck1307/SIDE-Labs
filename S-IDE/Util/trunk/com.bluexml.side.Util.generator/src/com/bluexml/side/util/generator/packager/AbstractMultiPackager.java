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


package com.bluexml.side.util.generator.packager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import com.bluexml.side.util.libs.IFileHelper;

public abstract class AbstractMultiPackager {
	private Map<String, AbstractPackager> packagers = new HashMap<String, AbstractPackager>();
	protected String workingdir;
	protected IFolder IworkingDir; // generated folder
	protected Properties moduleProperties;
	protected String technoV;

	public AbstractMultiPackager(IFolder folder, Properties moduleProperties, String technoV) {
		this.IworkingDir = folder;
		this.workingdir = IFileHelper.convertIRessourceToSystemString(folder);
		this.moduleProperties = moduleProperties;
		this.technoV = technoV;
	}

	public Map<String, IFile> buildPackages() throws Exception {
		Map<String, IFile> packageFiles_ = new HashMap<String, IFile>();
		for (Map.Entry<String, AbstractPackager> p : packagers.entrySet()) {
			if (p.getValue().getFolderToPackage().exists()) {
				IFile pp = p.getValue().buildPackage();
				System.out.println("generated package :" + pp);
				packageFiles_.put(p.getKey(), pp);
			} else {
				System.out.println("Warn : package not found " + p.getValue().getFolderToPackage());
				// INFO : packager skipped
				if (p.getValue().getPackageFile().exists()) {
					// delete empty package if exist
					p.getValue().getPackageFile().delete();
				}
			}
		}
		//		if (doClean) {
		//			FileHelper.deleteFile(getWorkingFolder());
		//		}
		return packageFiles_;
	}

	public String getWorkingdir() {
		return workingdir;
	}

	protected File getWorkingFolder() {
		return new File(getWorkingdir());
	}

	protected void addPackager(String key, AbstractPackager pk) throws Exception {
		if (!packagers.containsKey(key)) {
			packagers.put(key, pk);
		} else {
			throw new Exception("Packager with same key exists please used another key :" + key);
		}
	}

}
