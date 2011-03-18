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


package com.bluexml.side.forms.generator.alfresco.chiba;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;

import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.util.componentmonitor.ComponentMonitor;
import com.bluexml.side.util.dependencies.DependencesManager;
import com.bluexml.side.util.generator.AbstractGenerator;
import com.bluexml.side.util.generator.packager.WarPatchPackager;
import com.bluexml.xforms.generator.FormGeneratorsManager;
import com.bluexml.xforms.generator.GeneratorInterface;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.mapping.MappingGenerator;
import com.bluexml.xforms.messages.DefaultMessages;
import com.bluexml.xforms.messages.MsgPool;

public class FormGenerator extends AbstractGenerator {

	public static final String COM_BLUEXML_SIDE_FORM_GENERATOR_XFORMS_CHIBA_WEBAPP_CONTEXT = "com.bluexml.side.Form.generator.xforms.chiba.webappContext";
	public static final String CONFIGURATION_PARAMETER_ALFRESCO_URL = "alfresco.url";

	private static final String defaultModelID = "xformsModel";
	private static final String webappName = "xforms";

	private List<File> clazzModels;
	private List<File> formModels;
	private File xformGenerationFolder;
	private File mappingGenerationFolder;
	private File messagesFile;
	private boolean successfulInit;
	private String webappContext;
	private List<GeneratorInterface> generators = new ArrayList<GeneratorInterface>();

	@Override
	public void initialize(Map<String, String> generationParameters_, Map<String, Boolean> generatorOptions_, Map<String, String> configurationParameters_, DependencesManager dm, ComponentMonitor monitor) throws Exception {
		super.initialize(generationParameters_, generatorOptions_, configurationParameters_, dm, monitor);

		this.monitor = monitor;

		successfulInit = false;
		setTEMP_FOLDER("generator_" + getClass().getName() + File.separator + defaultModelID);
		File webappFolder = new File(getTemporarySystemFile(), "webapps" + File.separator + webappName);
		xformGenerationFolder = new File(webappFolder.getAbsolutePath() + File.separator + "forms");
		String classesPathname = webappFolder.getAbsolutePath() + File.separator + "WEB-INF" + File.separator + "classes";
		mappingGenerationFolder = new File(classesPathname);

		FileUtils.forceMkdir(xformGenerationFolder);
		FileUtils.forceMkdir(mappingGenerationFolder);

		String baseDir = xformGenerationFolder.getAbsolutePath();
		String resDir = mappingGenerationFolder.getAbsolutePath();

		File generateMappingFile = new File(resDir + File.separator + "mapping.xml");
		File generateRedirectFile = new File(resDir + File.separator + "redirect.xml");
		File generateCSSFile = new File(resDir + File.separator + "styles.css");

		XFormsGenerator xformsGenerator = new XFormsGenerator();
		MappingGenerator mappingGenerator = new MappingGenerator();
		generators.add(mappingGenerator);
		generators.add(xformsGenerator);

		xformsGenerator.setOutputFolder(baseDir);
		mappingGenerator.setOutputMappingFile(generateMappingFile.getAbsolutePath());
		mappingGenerator.setOutputCSSFile(generateCSSFile.getAbsolutePath());
		mappingGenerator.setOutputRedirectFile(generateRedirectFile.getAbsolutePath());

		// deal with messages.properties file
		String messagesFilePath = generationParameters.get("com.bluexml.side.Form.generator.xforms.chiba.messagesFilePath");
		if (StringUtils.trimToNull(messagesFilePath) != null) {
			File file = new File(messagesFilePath);
			if (file.exists()) {
				setMessagesFilePath(messagesFilePath);
			} else {
				monitor.addWarningText("The specified messages file does not exist. Will generate defaults.");
				messagesFilePath = null;
			}
		}
		if (StringUtils.trimToNull(messagesFilePath) == null) {
			String filePath = classesPathname + File.separator + "messages.properties";
			if (DefaultMessages.generateMessagesFile(filePath)) {
				setMessagesFilePath(filePath);
			} else {
				monitor.addWarningText("Could not generate and set the messages file.");
			}
		}

		// generate the forms.properties file
		String filePath = resDir + File.separator + "forms.properties";

		if (DefaultMessages.generateFormsFile(filePath, generationParameters) == false) {
			monitor.addWarningText("Could not generate and set the 'forms.properties' file.");
		}

		// deal with the webapp address (protocol, host, port, context)
		webappContext = generationParameters.get(COM_BLUEXML_SIDE_FORM_GENERATOR_XFORMS_CHIBA_WEBAPP_CONTEXT);
		if (StringUtils.trimToNull(webappContext) != null) {
			// we check that the context is not 'forms'
			int pos = webappContext.lastIndexOf('/');
			int len = webappContext.length();
			// if there's a trailing "/", remove it
			if (pos == (len - 1)) {
				webappContext = webappContext.substring(0, len - 1);
			}
			len = webappContext.length();
			pos = webappContext.lastIndexOf('/');
			String context = webappContext.substring(pos + 1, len);
			if (context.equals("forms")) {
				throw new Exception("The context of your webapp SHOULD NOT be 'forms'!");				
			}
		}
		successfulInit = true;
	}

	/**
	 * @param filePath
	 * @throws Exception
	 */
	private void setMessagesFilePath(String filePath) throws Exception {
		try {
			messagesFile = new File(filePath);
			MsgPool.setMessagesFile(messagesFile.getAbsolutePath());
		} catch (Exception e) {
			throw new Exception("Problem opening the messages file. caused :" + e.getMessage(), e);
		}
	}

	/**
	 * This method check if the user have the license to use this generator.
	 * 
	 * @return true if the generator can be used.
	 */
	public boolean check() {
		return true;
	}

	public boolean shouldGenerate(HashMap<String, List<IFile>> modelsInfo, String id_metamodel) {
		return modelsInfo.containsKey(ClazzPackage.eNS_URI) || modelsInfo.containsKey(FormPackage.eNS_URI);
	}

	public Collection<IFile> complete(Map<String, List<IFile>> models) throws Exception {
		// must build package
		// build archive from tmp folder

		IFolder root = getIFolder(getTemporaryFolder());
		IFolder techVFolder = ((IFolder) root.getParent().getParent()).getFolder(techVersion);
		WarPatchPackager wpp = new WarPatchPackager(root, buildModuleProperties(defaultModelID).getProperty("module.id"), techVFolder, webappName);

		IFile chibaPackage = wpp.buildPackage();
		ArrayList<IFile> result = new ArrayList<IFile>();
		result.add(chibaPackage);

		// add resources to match with package dependencies
		addDependences();

		return result;
	}

	/**
	 * Return the IFolder with the with the given project relative path.
	 * 
	 * @param path
	 * @return
	 */
	public static IFolder getIFolder(String path) {
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFolder folder = myWorkspaceRoot.getFolder(new Path(path));
		return folder;
	}

	public Collection<IFile> generate(Map<String, List<IFile>> modelsInfo, String id_mm) throws Exception {

		if (successfulInit == false) {
			return null;
		}
		clazzModels = getModels(modelsInfo, ClazzPackage.eNS_URI);
		formModels = getModels(modelsInfo, FormPackage.eNS_URI);

		File[] clazzFiles = clazzModels.toArray(new File[clazzModels.size()]);
		File[] formsFiles = formModels.toArray(new File[formModels.size()]);
		boolean simplifyClasses = true;
		boolean renderDataBeforeWorkflow = true;
		try {
			FormGeneratorsManager formGenerator = new FormGeneratorsManager(clazzFiles, formsFiles, monitor, simplifyClasses, renderDataBeforeWorkflow, false);
			// formGenerator.setDebugMode(true);
			formGenerator.setGenerateReadOnlyForms(true);
			formGenerator.setReadOnlySuffix("RO");
			formGenerator.generate(generators);
		} catch (RuntimeException e) {
			// Fix #1748
			throw new Exception(e);
		}
		return new ArrayList<IFile>();
	}

	/**
	 * Filters a list of models wrt a URI and returns a list of files.
	 * 
	 * @param modelsInfo
	 * @param nsURI
	 * @return the list of files from the models that match the URI
	 */
	private List<File> getModels(Map<String, List<IFile>> modelsInfo, String nsURI) {
		List<File> modelsFiles = new ArrayList<File>();
		List<IFile> modelsIFile = modelsInfo.get(nsURI);
		if (modelsIFile != null) {
			for (IFile file : modelsIFile) {
				String fileLocation = file.getLocation().toFile().getAbsolutePath();
				modelsFiles.add(new File(fileLocation));
			}
		}
		return modelsFiles;
	}

	public Properties buildModuleProperties(String rootPackage) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); //$NON-NLS-1$
		Properties props = new Properties();
		props.put("module.id", "SIDE_xformsExtension_" + rootPackage); //$NON-NLS-1$ //$NON-NLS-2$
		props.put("module.version", ""); //$NON-NLS-1$
		props.put("module.title", ""); //$NON-NLS-1$
		props.put("module.description", "xForm plugin generated at " + sdf.format(now)); //$NON-NLS-1$

		return props;
	}

}
