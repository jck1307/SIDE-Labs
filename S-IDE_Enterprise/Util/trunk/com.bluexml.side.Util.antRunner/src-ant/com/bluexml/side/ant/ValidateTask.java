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


/**
 * 
 */
package com.bluexml.side.ant;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.eclipse.core.resources.IFile;

import com.bluexml.side.application.Model;
import com.bluexml.side.application.ui.Activator;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.integration.standalone.ApplicationStarter;

/**
 * @author davidabad
 */
public class ValidateTask extends SideApplicationTask {

	@SuppressWarnings("unchecked")
	public void executeImp() throws BuildException {
		Map<String, Object> conf = ApplicationStarter.loadConfiguration(getApplicationFile(), null);
		List<Model> models = (List<Model>) conf.get(ApplicationStarter.MODELS_KEY);
		HashMap<String, List<IFile>> modelsInfo = null;
		
		try {
			modelsInfo = (HashMap<String, List<IFile>>) ApplicationUtil.getAssociatedMetaModel(models);
		} catch (Exception e) {
			new BuildException(e);
		}
		
		Iterator<List<IFile>> it = modelsInfo.values().iterator();
		List<IFile> listModel;
		while (it.hasNext()) {
			listModel = it.next();
			for (IFile m : listModel) {
				try {
					if (ApplicationUtil.validate(m)) {
						System.out.println("model :" + m.getName() + " validated");
					} else {
						System.out.println(Activator.Messages.getString("Generate.7", m.getName()));
					}
				} catch (Exception e) {
					throw new BuildException(e);
				}
			}
		}
	}
}
