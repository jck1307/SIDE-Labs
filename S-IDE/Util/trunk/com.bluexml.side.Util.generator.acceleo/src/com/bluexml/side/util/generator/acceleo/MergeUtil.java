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


package com.bluexml.side.util.generator.acceleo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.common.Package;
import com.bluexml.side.requirements.RequirementsDefinition;
import com.bluexml.side.util.libs.IFileHelper;

public class MergeUtil {

	public static Map<String, List<IFile>> groupByRootPackage(List<IFile> models) throws Exception {
		Map<String, List<IFile>> gb = new HashMap<String, List<IFile>>();
		for (IFile model : models) {
			Resource modelResource = EResourceUtils.openModel(IFileHelper.convertIRessourceToSystemString(model), null);
			EObject root = modelResource.getContents().get(0);
			if (root instanceof Package) {
				
				String eClass = root.eClass().getName();
				for (EGenericType parent : root.eClass().getEAllGenericSuperTypes())
					if (parent.getERawType().getName().equalsIgnoreCase("FormCollection"))
						eClass = "FormCollection";
				
				String pname = ((Package) root).getName()+"_"+eClass;
				if (gb.containsKey(pname)) {
					gb.get(pname).add(model);
				} else {
					ArrayList<IFile> l = new ArrayList<IFile>();
					l.add(model);
					gb.put(pname, l);
				}
			} else if (root instanceof RequirementsDefinition) {
				String pname = ((RequirementsDefinition) root).getName()+"_"+root.eClass().getName();
				if (gb.containsKey(pname)) {
					gb.get(pname).add(model);
				} else {
					ArrayList<IFile> l = new ArrayList<IFile>();
					l.add(model);
					gb.put(pname, l);
				}
			} else if (root instanceof com.bluexml.side.requirements.Process) {
				String pname = ((com.bluexml.side.requirements.Process) root).getName()+"_"+root.eClass().getName();
				if (gb.containsKey(pname)) {
					gb.get(pname).add(model);
				} else {
					ArrayList<IFile> l = new ArrayList<IFile>();
					l.add(model);
					gb.put(pname, l);
				}
			} else {
				System.err.println("No Package element for this models (" + models.toString() + ")");
			}
		}
		return gb;
	}
}
