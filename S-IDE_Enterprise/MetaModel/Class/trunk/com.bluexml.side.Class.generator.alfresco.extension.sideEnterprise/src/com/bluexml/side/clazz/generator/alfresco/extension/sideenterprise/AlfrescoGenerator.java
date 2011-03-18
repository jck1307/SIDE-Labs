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


package com.bluexml.side.clazz.generator.alfresco.extension.sideenterprise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import com.bluexml.side.clazz.generator.alfresco.api.Generator;
import com.bluexml.side.clazz.generator.alfresco.enterprise.AlfrescoEnterprisePackager;
import com.bluexml.side.util.generator.AbstractGenerator;
import com.bluexml.side.util.generator.alfresco.AlfrescoPackager;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;

public class AlfrescoGenerator extends Generator {
	

	public boolean checkOption(String optionID) {
		String key = "CODE_OPT_" + optionID;
		// System.out.println("AlfrescoGenerator.checkOption() code :"+key);
		Boolean check = SecurityHelper.check(key, SidePreferences.getKey());
		if (check == null) {
			// key not in code list, so free to use
			check = true;
		}
		return check;
	}

	@Override
	public Collection<IFile> generate(Map<String, List<IFile>> modelsInfo, String idMetamodel) throws Exception {
		if (!check()) {
			throw new Exception(com.bluexml.side.application.ui.Activator.Messages.getString("Generate.44", this.id));
		}
		Map<String, Boolean> opts = AbstractGenerator.generatorOptions;
		for (Map.Entry<String, Boolean> opt : opts.entrySet()) {
			if (opt.getValue()) {
				String key = opt.getKey();
				key = getId() + "_" + key;
				if (!checkOption(key)) {
					throw new Exception(com.bluexml.side.application.ui.Activator.Messages.getString("Generate.45", key));
				}
			}
		}
		return super.generate(modelsInfo, idMetamodel);
	}

	@Override
	public List<String> getClassTemplates() {
		List<String> result = new ArrayList<String>();
		result.addAll(super.getClassTemplates());
		result.addAll(getEnterpriseTemplates());
		return result;
	}

	public List<String> getEnterpriseTemplates() {
		List<String> result = new ArrayList<String>();
		if (getGeneratorOptionValue(ALFRESCO_WEBSERVICES_CLIENT_API) || getGeneratorOptionValue(ALFRESCO_EMBEDDED_API)) {
			result.addAll(getAlfrescoAPIObjectModel());
		}
		if (getGeneratorOptionValue(ALFRESCO_WEBSERVICES_CLIENT_API)) {
			result.addAll(getAlfrescoAPITemplates());
		}
		if (getGeneratorOptionValue(ALFRESCO_EMBEDDED_API)) {
			result.addAll(getAlfrescoAPIEmbedded());
		}
		if (getGeneratorOptionValue(ALFRESCO_EXTJS_API)) {
			result.addAll(getAlfrescoExtJSTemplates());
		}
		return result;
	}

	@Override
	public Collection<IFile> buildPackages(String modelId) throws Exception {
		// package amp, shareZip and eclipse project archive
		IFolder iFolder = IFileHelper.getIFolder(getTemporaryFolder());
		AlfrescoPackager alfrescoPackager = new AlfrescoEnterprisePackager(iFolder, buildModuleProperties(modelId), techVersion);
		Collection<IFile> pkgs = alfrescoPackager.buildPackages().values();
		return pkgs;
	}
}
