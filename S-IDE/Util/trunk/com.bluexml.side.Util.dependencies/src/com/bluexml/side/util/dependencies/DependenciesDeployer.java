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


package com.bluexml.side.util.dependencies;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.maven.embedder.MavenEmbedder;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.eclipse.ExtensionPointUtils;
import com.bluexml.side.util.libs.zip.TrueZipHelper;

public class DependenciesDeployer {

	public static final String EXTENSIONPOINT_ID = "com.bluexml.side.Util.dependencies.com_bluexml_side_framework_module_repository";
	public static final String APPLICATION_CONSTRAINTS = "repository";
	public static final String REPOSITORY = "repositoryName";
	public static final String APPLICATION_CONSTRAINTS_PATH = "repositoryPath";
	public static final String APPLICATION_CONSTRAINTS_ACTIVATOR = "activator";
	public static final String APPLICATION_CONSTRAINTS_PLUID = "pluginId";

	public static void deploy() throws Exception {
		// get repositories

		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSIONPOINT_ID);
		// get all module constraints and construct instance of
		// com.bluexml.side.util.dependencies.ModuleConstraint
		for (IConfigurationElement config_exp : contributions) {
			String nodeName = APPLICATION_CONSTRAINTS;
			List<IConfigurationElement> matchs = ExtensionPointUtils.getIConfigurationElementsByName(config_exp, nodeName);
			for (IConfigurationElement configurationElement : matchs) {
				String moduleId = configurationElement.getAttribute(REPOSITORY);
				String path = configurationElement.getAttribute(APPLICATION_CONSTRAINTS_PATH);
				String className = configurationElement.getAttribute(APPLICATION_CONSTRAINTS_ACTIVATOR);
				String bundle = configurationElement.getAttribute(APPLICATION_CONSTRAINTS_PLUID);

				System.out.println("deploy repository :" + moduleId);
				System.out.println("deploy className :" + className);

				Class<?> c = ExtensionPointUtils.getGeneratorInstance(bundle, className);
				System.out.println("DependenciesDeployer.deploy() get Stream from :" + path);
				InputStream stream = c.getResourceAsStream(path);

				deployRepository(stream);
			}
		}

	}

	private static void deployRepository(InputStream stream) throws Exception, IOException {
		File repository = MavenEmbedder.defaultUserLocalRepository;

		if (!repository.exists()) {
			boolean done = repository.mkdirs();
			if (!done) {
				throw new Exception("Error when installing dependencies, please repport this bug");
			}
		}

		File tmpZip = File.createTempFile("side_repo", ".zip");
		tmpZip.deleteOnExit();
		FileHelper.writeStreamInFile(tmpZip, stream);
		System.out.println("file to unzip :" + tmpZip + ":" + tmpZip.length() + " b");
		TrueZipHelper tzh = new TrueZipHelper("zip");
		tzh.copyFiles(tmpZip, repository, true);
	}

}
