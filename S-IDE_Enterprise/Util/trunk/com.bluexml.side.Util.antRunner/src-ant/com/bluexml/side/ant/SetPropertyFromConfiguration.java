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

import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.eclipse.emf.common.util.EList;

import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.integration.standalone.ApplicationStarter;

public class SetPropertyFromConfiguration extends SideTask {
	public static final String CONFIGURATION_KEY = "configuration";

	private String parameterId = "";

	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}

	public String getParameterId() {
		return parameterId;
	}

	private String antProperty = "";

	public void setAntProperty(String antProperty) {
		this.antProperty = antProperty;
	}

	public String getAntProperty() {
		return antProperty;
	}

	@Override
	void executeImp() {
		if (!getApplicationFile().exists()) {
			this.log("Application model not found at :" + getApplicationFile() + " please check", Project.MSG_ERR);
		} else {
			Map<String, Object> conf = ApplicationStarter.loadConfiguration(getApplicationFile(), getConfigurationName());
			Configuration configuration = (Configuration) conf.get(CONFIGURATION_KEY);
			EList<ConfigurationParameters> confParam = configuration.getParameters();
			for (ConfigurationParameters configurationParameters : confParam) {
				if (getParameterId().equals(configurationParameters.getKey())) {
					// parameter founded
					String paramValue = configurationParameters.getValue();
					// try to evaluate against Eclipse variables
					try {
						paramValue = ApplicationUtil.eclipseVariableSubstitution(paramValue);
					} catch (Exception e) {
						new BuildException(e);
					}
					this.getProject().setProperty(getAntProperty(), paramValue);
				}
			}
		}
	}

}
