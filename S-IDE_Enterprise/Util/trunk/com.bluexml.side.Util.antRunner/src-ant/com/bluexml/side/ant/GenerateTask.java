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


package com.bluexml.side.ant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.eclipse.core.resources.IFile;

import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.GeneratorConfiguration;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.application.ui.action.utils.Generate;
import com.bluexml.side.integration.standalone.ApplicationStarter;
import com.bluexml.side.util.documentation.structure.enumeration.LogType;
import com.bluexml.side.util.generator.AbstractGenerator;

public class GenerateTask extends SideComponantTask {

	private boolean generate = true;
	private boolean pack = true;

	public void setGenerate(boolean generate) {
		this.generate = generate;
	}

	public void setPack(boolean pack) {
		this.pack = pack;
	}

	public boolean isPack() {
		return pack;
	}

	public boolean isGenerate() {
		return generate;
	}

	public void executeImp() throws BuildException {
		Generate gen = ApplicationStarter.getGenerate(getApplicationFile(), getConfigurationName());
		List<Model> models = gen.getModels();
		Configuration configuration = gen.getConfiguration();
		GeneratorConfiguration elem = (GeneratorConfiguration) ApplicationUtil.getComponantConfiguration(configuration, getComponantid());

		HashMap<String, List<IFile>> modelsInfo = null;

		try {
			modelsInfo = (HashMap<String, List<IFile>>) ApplicationUtil.getAssociatedMetaModel(models);
		} catch (Exception e) {
			throw new BuildException(e);
		}

		gen.setHeadless(true);
		gen.setUser(false);
		gen.setSystem(true);
		Map<String, String> configurationParameters = new HashMap<String, String>();
		Map<String, String> generationParameters = new HashMap<String, String>();
		try {
			gen.setParameters(configurationParameters, generationParameters);
		} catch (Exception e1) {
			throw new BuildException(e1);
		}

		// We get the option for this generator
		Map<String, Boolean> generatorOptions = new HashMap<String, Boolean>();
		for (Option option : elem.getOptions()) {
			generatorOptions.put(option.getKey(), true);
		}

		try {
			AbstractGenerator generator = gen.getGenerator(configurationParameters, generationParameters, elem, generatorOptions);
			String fileName = "gen_" + generator.getClass().getName() + ".xml"; //$NON-NLS-1$ //$NON-NLS-2$
			generator.getMonitor().initialize(0, configurationParameters, LogType.GENERATION, fileName);
			
			// the main action generate files
			if (isGenerate()) {
				generator.generate(modelsInfo, elem.getId_metamodel());
			}
			
			// execute final actions may vary for each generator type
			// many of them do :
			// mvn copy-dependencies, package generated files
			if (isPack()) {
				generator.complete(modelsInfo);
			}
			generator.createStampFile();
		} catch (Exception e) {
			throw new BuildException(e);
		}
		
//		gen.saveSideReportAndFeedBack();
		gen.refreshFolders();
	}
}
