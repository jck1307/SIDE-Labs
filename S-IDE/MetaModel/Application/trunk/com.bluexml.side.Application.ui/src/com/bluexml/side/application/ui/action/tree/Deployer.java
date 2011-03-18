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


package com.bluexml.side.application.ui.action.tree;

import org.eclipse.core.runtime.IConfigurationElement;

import com.bluexml.side.application.ApplicationFactory;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.DeployerConfiguration;
import com.bluexml.side.application.ModuleConstraint;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.ui.action.ApplicationDialog;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;

public class Deployer extends ImplNode {

	private boolean shared;
	
	public Deployer(IConfigurationElement elt, TechnologyVersion tv, TreeView root) {
		super(elt, tv, root);
		// set additional attibutes
		if (elt.getAttribute("shared") != null)
			shared = Boolean.parseBoolean(elt.getAttribute("shared"));
	}

	public void updateApplication() {
		if (!ApplicationDialog.loadingTree) {
			ApplicationDialog.modificationMade();
			Configuration config = ApplicationDialog.getCurrentConfiguration();
			if (config != null) {
				// Remove element
				ApplicationUtil.deleteDeployerFromConf(config, this);

				// Add the new element
				if (isChecked() && isEnabled()) {
					DeployerConfiguration elt = ApplicationFactory.eINSTANCE.createDeployerConfiguration();
					elt.setId(getId());
					elt.setId_techno_version(parent.getId());
					elt.setImpl_class(getLaunchClass());
					elt.setShared(shared);
					elt.setContributorId(getContributorId());
					elt.setTechnologyName(((Technology) parent.getParent()).getLabel());
					elt.setTechnologyVersionName(((TechnologyVersion) parent).getVersion());
					elt.setDeployerName(this.getVersion());
					
					for (com.bluexml.side.util.dependencies.ModuleConstraint module : integrationModules) {
						ModuleConstraint mc = ApplicationFactory.eINSTANCE.createModuleConstraint();
						mc.setModuleId(module.getModuleId());
						if (module.getVersionMin() != null) {
							mc.setVersionMin(module.getVersionMin().toString());
						}
						if (module.getVersionMax() != null) {
							mc.setVersionMax(module.getVersionMax().toString());
						}
						mc.setModuleType(module.getModuleType());
						mc.setTechnologyVersion(module.getTech_version());
						elt.getModuleContraints().add(mc);
					}
					
					// Launch options
					for (TreeNode tn : options) {
						OptionComponant o = (OptionComponant) tn;
						if (o.isChecked() && o.isEnabled()) {
							Option opt = ApplicationFactory.eINSTANCE.createOption();
							opt.setKey(o.getKey());
							
							elt.getOptions().add(opt);
							
							for (com.bluexml.side.util.dependencies.ModuleConstraint module : o.getIntegrationModules()) {
								ModuleConstraint mc = ApplicationFactory.eINSTANCE.createModuleConstraint();
								mc.setModuleId(module.getModuleId());
								if (module.getVersionMin() != null) {
									mc.setVersionMin(module.getVersionMin().toString());
								}
								if (module.getVersionMax() != null) {
									mc.setVersionMax(module.getVersionMax().toString());
								}
								mc.setModuleType(module.getModuleType());
								mc.setTechnologyVersion(module.getTech_version());
								elt.getModuleContraints().add(mc);
							}
							
						}
					}

					config.getDeployerConfigurations().add(elt);
				}
			}
		}
	}

	@Override
	public void addChildren(TreeNode child) {
		options.add(child);
	}

}
