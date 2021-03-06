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
import java.util.Map;

import org.apache.tools.ant.BuildException;

import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.DeployerConfiguration;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.application.ui.action.utils.Generate;
import com.bluexml.side.integration.standalone.ApplicationStarter;
import com.bluexml.side.util.deployer.Deployer;

public class DeployTask extends SideComponantTask {

	public void executeImp() throws BuildException {
		Generate gen = ApplicationStarter.getGenerate(getApplicationFile(), getConfigurationName());
		Configuration configuration = gen.getConfiguration();
		DeployerConfiguration depConf = (DeployerConfiguration) ApplicationUtil.getComponantConfiguration(configuration, getComponantid());

		gen.setHeadless(true);
		gen.setUser(false);
		gen.setSystem(true);
		Map<String, String> configurationParameters = new HashMap<String, String>();
		Map<String, String> generationParameters = new HashMap<String, String>();		

		try {
			gen.setParameters(configurationParameters, generationParameters);
			Deployer dep = gen.getDeployer(depConf, configuration, configurationParameters, generationParameters);

			dep.deploy();
			dep.moveStampFile(gen.getLogPath());
		} catch (Exception e) {
			throw new BuildException(e);
		}
		
		gen.refreshFolders();
	}
}
