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


package com.bluexml.side.requirements.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;

import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.requirements.AnnotableElement;
import com.bluexml.side.requirements.Annotation;

public class WebProjectGenerator extends RequirementsGenerator {

	private static String MM_URI = "http://www.kerblue.org/requirements/1.0";
	
	@Override
	protected String getMetamodelURI() {
		return MM_URI;
	}

	@Override
	protected List<String> getTemplates(String keyGenerator) {
		List<String> l = new ArrayList<String>();
		l.add("/"+PLUGIN_ID+"/src/interpretation/webproject/generation/json-agents.mt");	
		l.add("/"+PLUGIN_ID+"/src/interpretation/webproject/generation/json-goals.mt");	
		l.add("/"+PLUGIN_ID+"/src/interpretation/webproject/generation/data.mt");	
		l.add("/"+PLUGIN_ID+"/src/interpretation/webproject/generation/data-page.mt");	
		l.add("/"+PLUGIN_ID+"/src/interpretation/webproject/generation/page-info.mt");	
		l.add("/"+PLUGIN_ID+"/src/interpretation/webproject/generation/main_edit.mt");	
		/*l.add("/"+PLUGIN_ID+"/src/interpretation/webproject/generation/main.mt");*/	
		return l;
	}

	public Collection<IFile> complete(Map<String, List<IFile>> models) throws Exception {
		//Nothing to do
		return null;
	}
	
	@Override
	protected Map<String, String> getInputModels(String keyGenerator) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("IN", "RWM");
		return result;
	}

	@Override
	protected Map<String, String> getOutputModels(String keyGenerator) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("OUT", "WebProject");
		return result;
	}

	@Override
	protected List<String> getANTScripts(String keyGenerator) {
		return Collections.singletonList("src/interpretation/commonportal/build-default/");
	}

	@Override
	protected String getASMFile(String keyGenerator) {
		return "src/interpretation/webproject/transformation/RWM2WebProject.asm";
	}

	@Override
	protected String getTargetMetamodel(String keyGenerator) {
		return "src/interpretation/webproject/webproject.ecore";
	}

	@Override
	protected Set<String> getTransformation() {
		return Collections.singleton("webProject generator");
	}

	@Override
	protected String getTargetModelName(String keyGenerator) {
		return "webproject.ecore";
	}
	
	@Override
	public Collection<IFile> generate(IFile model) throws Exception {
		Collection<IFile> result = super.generate(model);

		//Export annotations
		try {
			Map<?,?> m = new HashMap<Object, Object>();
			Resource r  = EResourceUtils.openModel(URI.createFileURI(model.getLocation().toString()), m);
			Set<Annotation> annotations = new HashSet<Annotation>();
			for (Iterator<EObject> iterator = r.getAllContents(); iterator.hasNext();) {
				EObject obj = (EObject) iterator.next();
				if (obj instanceof Annotation) {
					Annotation a = (Annotation) obj;
					annotations.add(a);
				}
			}
			
			IFile sqlSchema = null;
			for (IFile file : result)
				if (file.getName().endsWith(".sql"))
					sqlSchema = file;
			
			if (sqlSchema != null) {
				FileWriter fstream = new FileWriter(sqlSchema.getRawLocation().toString(),true);
	        	BufferedWriter out = new BufferedWriter(fstream);
	        	SimpleDateFormat  simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
				for (Annotation a : annotations) {
					String sql = "";
					
					String annotation = "";
					if (a.getAnnotation() != null)
						annotation = a.getAnnotation().replaceAll("'","\\\\'").replaceAll("\n","\\\\n");
					
					String comment = "";
					if (a.getComment() != null)
						comment = a.getComment().replaceAll("'", "\\\\'").replaceAll("\n","\\\\n");
					
					String date = "";
					if (a.getDate() != null)
						date = simpleFormat.format(a.getDate());
					
					sql = "INSERT IGNORE INTO `annotation` (" +
							"`id` ," +
							"`elementId` ," +
							"`author` ," +
							"`annotation` ," +
							"`comment` ," +
							"`date`" +
							")VALUES (" +
							"	'"+a.getId()+"', " +
							"	'"+((AnnotableElement)a.eContainer()).getId()+"', " +
							"	'"+a.getAuthor()+"', " +
							"	'"+annotation+"', " +
							"	'"+comment+"', " +
							"	'"+date+"');\n";
					out.write(sql);
				}
				out.close();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			MessageDialog.openError(null, "Problem during reading of input model !", e1.getMessage());
		}
		
		return result;
	}

	
	protected void computeServices() throws CoreException {
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFolder targetFolder = myWorkspaceRoot.getFolder(new Path(getTemporaryFolder()));
		if (targetFolder.exists()) {
			for (File f : targetFolder.getRawLocation().toFile().listFiles()) {
				if (f.getName().equalsIgnoreCase("My Web Project")) {
					String url = f.getAbsolutePath();
					url = url.replaceAll(" ", "%20");
					monitor.getLog().addServiceLog("Generated prototype",f.getName(), url);
				}
			}
		}
	}
}
