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


package com.bluexml.side.Integration.eclipse.branding.enterprise;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.part.ISetSelectionTarget;

import com.bluexml.side.Integration.eclipse.branding.enterprise.wizard.WizardModelOptionsPage;
import com.bluexml.side.Integration.eclipse.branding.enterprise.wizard.project.ProjectGenerator;
import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.application.ApplicationFactory;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.ClazzFactory;
import com.bluexml.side.form.ClassFormCollection;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.portal.Portal;
import com.bluexml.side.portal.PortalFactory;
import com.bluexml.side.requirements.RequirementsDefinition;
import com.bluexml.side.requirements.RequirementsFactory;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.view.ViewCollection;
import com.bluexml.side.view.ViewFactory;
import com.bluexml.side.workflow.WorkflowFactory;

public class Wizard extends org.eclipse.jface.wizard.Wizard implements INewWizard {

	public static final String DEFAULT_SRC_DIR = "src"; //$NON-NLS-1$
	public static final String DEFAULT_MODELS_DIR = DEFAULT_SRC_DIR + "/models"; //$NON-NLS-1$
	private static final String[] modelTypes = { "application", "data", "form", "portal", "requirement", "view", "workflow" };
	private static final String[] srcDirs = { "models", "modules/mavenProjects", "configs/commonConf", "configs/" + System.getProperty("user.name") + "/webapps/alfresco", "configs/" + System.getProperty("user.name") + "/webapps/share" };
	public static final String DEFAULT_LOG_DIR = "build/logs"; //$NON-NLS-1$
	public static final String DEFAULT_GEN_DIR = "build/generated"; //$NON-NLS-1$
	public static final String DEFAULT_MODEL_NAME = "my"; //$NON-NLS-1$

	// The workbench
	protected IWorkbench currentWorkbench;
	protected WizardNewProjectCreationPage mainPage;
	protected WizardModelOptionsPage optionsPage;
	protected IProject newProject;
	protected List<IFile> createdModels = new ArrayList<IFile>();

	public Wizard() {
		super();
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setHelpAvailable(false); // TODO have help
		setWindowTitle(Activator.Messages.getString("Wizard.3")); //$NON-NLS-1$
		currentWorkbench = workbench;
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		createNewProject();

		if (newProject == null) {
			return false;
		}

		try {
			// Folders
			for (String dir : srcDirs) {
				prepareFolder(newProject.getFolder(DEFAULT_SRC_DIR).getFolder(dir));
			}
			for (String type : modelTypes) {
				prepareFolder(newProject.getFolder(DEFAULT_MODELS_DIR).getFolder(type));
			}
			prepareFolder(newProject.getFolder(DEFAULT_GEN_DIR));
			prepareFolder(newProject.getFolder(DEFAULT_LOG_DIR));

			// create additional files build.xml etc ...

			ProjectGenerator pg = new ProjectGenerator(newProject);
			pg.go();

			// Create initial model if needed :
			if (optionsPage.isCreateDataModel()) {
				createInitialDataModel();
			}
			if (optionsPage.isCreateFormModel()) {
				createInitialFormModel();
			}
			if (optionsPage.isCreatePortalModel()) {
				createInitialPortalModel();
			}
			if (optionsPage.isCreateRequirementModel()) {
				createInitialRequirementModel();
			}
			if (optionsPage.isCreateViewModel()) {
				createInitialViewModel();
			}
			if (optionsPage.isCreateWorkflowModel()) {
				createInitialWorkflowModel();
			}

			// Create .application file :
			createApplicationFile();
			newProject.refreshLocal(2, null);

		} catch (CoreException coreException) {
			coreException.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		selectAndReveal(newProject);

		return true;
	}

	private IFile createFileForModel(String extension) throws CoreException {
		IFolder folder = newProject.getFolder(DEFAULT_MODELS_DIR);
		if (extension.equalsIgnoreCase(ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.clazz.presentation.ClazzEditorID"))) {
			folder = folder.getFolder("data");
		} else if (extension.equalsIgnoreCase(ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.form.presentation.formEditorID"))) {
			folder = folder.getFolder("form");
		} else if (extension.equalsIgnoreCase(ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.portal.presentation.PortalEditorID"))) {
			folder = folder.getFolder("portal");
		} else if (extension.equalsIgnoreCase(ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.requirements.presentation.RequirementsEditorID"))) {
			folder = folder.getFolder("requirement");
		} else if (extension.equalsIgnoreCase(ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.view.presentation.ViewEditorID"))) {
			folder = folder.getFolder("view");
		} else if (extension.equalsIgnoreCase(ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.workflow.presentation.WorkflowEditorID"))) {
			folder = folder.getFolder("workflow");
		}
		return folder.getFile(optionsPage.getModelNameValue() + extension);
	}

	private void createInitialFormModel() throws CoreException, IOException {
		IFile file = createFileForModel(ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.form.presentation.formEditorID")); //$NON-NLS-1$
		ClassFormCollection formCollection = FormFactory.eINSTANCE.createClassFormCollection();
		ModelInitializationUtils.saveModel(file.getLocation().toFile(), formCollection);
		createdModels.add(file);
	}

	private void createInitialViewModel() throws CoreException, IOException {
		IFile file = createFileForModel(ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.view.presentation.ViewEditorID")); //$NON-NLS-1$
		ViewCollection viewCollection = ViewFactory.eINSTANCE.createViewCollection();
		ModelInitializationUtils.saveModel(file.getLocation().toFile(), viewCollection);
		createdModels.add(file);
	}

	private void createInitialWorkflowModel() throws CoreException, IOException {
		IFile file = createFileForModel(ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.workflow.presentation.WorkflowEditorID")); //$NON-NLS-1$
		com.bluexml.side.workflow.Process process = WorkflowFactory.eINSTANCE.createProcess();
		ModelInitializationUtils.saveModel(file.getLocation().toFile(), process);
		createdModels.add(file);
		createDiagramFile(file, process, "com.bluexml.side.Workflow.modeler.diagram"); //$NON-NLS-1$
	}

	private void createInitialRequirementModel() throws CoreException, IOException {
		IFile file = createFileForModel(ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.requirements.presentation.RequirementsEditorID")); //$NON-NLS-1$
		RequirementsDefinition definition = RequirementsFactory.eINSTANCE.createRequirementsDefinition();
		ModelInitializationUtils.saveModel(file.getLocation().toFile(), definition);
		createdModels.add(file);
		createDiagramFile(file, definition, "com.bluexml.side.Requirements.modeler.goalDiagram"); //$NON-NLS-1$
	}

	private void createInitialPortalModel() throws CoreException, IOException {
		IFile file = createFileForModel(ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.portal.presentation.PortalEditorID")); //$NON-NLS-1$
		Portal portal = PortalFactory.eINSTANCE.createPortal();
		ModelInitializationUtils.saveModel(file.getLocation().toFile(), portal);
		createdModels.add(file);

		createDiagramFile(file, portal, "com.bluexml.side.Portal.modeler.diagram"); //$NON-NLS-1$
	}

	private void createInitialDataModel() throws IOException, CoreException {
		IFile file = createFileForModel(ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.clazz.presentation.ClazzEditorID")); //$NON-NLS-1$
		createdModels.add(file);
		com.bluexml.side.clazz.Model model = ClazzFactory.eINSTANCE.createModel();
		model.setName(optionsPage.getModelNameValue());
		ClassPackage packageRoot = ClazzFactory.eINSTANCE.createClassPackage();
		ClassPackage lastPackage = packageRoot;
		String stringPath = optionsPage.getStringPath();
		if ((stringPath != null) && (stringPath.length() != 0)) {
			String[] segments = stringPath.split("/"); //$NON-NLS-1$
			packageRoot.setName(segments[0]);

			for (int i = 1; i < segments.length; i++) {
				ClassPackage p = ClazzFactory.eINSTANCE.createClassPackage();
				p.setName(segments[i]);
				lastPackage.getPackageSet().add(p);
				lastPackage = p;
			}
		} else {
			packageRoot.setName("root"); //$NON-NLS-1$
		}
		model.getPackageSet().add(packageRoot);
		ModelInitializationUtils.saveModel(file.getLocation().toFile(), model);
		createDiagramFile(file, lastPackage, "com.bluexml.side.Class.modeler.diagram"); //$NON-NLS-1$
	}

	private void createDiagramFile(IFile file, EObject root, String diagramid) throws IOException {
		IFile diagramFile = IFileHelper.getIFile(file.getFullPath().toOSString() + "di"); //$NON-NLS-1$
		ModelInitializationUtils.createDiagramFile(root, diagramid, file.getName() + "di", diagramFile); //$NON-NLS-1$
	}

	private void createApplicationFile() throws IOException {
		IFile file = newProject.getFolder(DEFAULT_MODELS_DIR + "/application").getFile(optionsPage.getModelNameValue() + ".application");
		com.bluexml.side.application.Application app = ApplicationFactory.eINSTANCE.createApplication();
		app.setName(newProject.getName());
		// Add initial configuration
		Configuration conf = ApplicationFactory.eINSTANCE.createConfiguration();
		conf.setName(newProject.getName());
		// Add initial parameters
		ConfigurationParameters logPathParam = ApplicationFactory.eINSTANCE.createConfigurationParameters();
		logPathParam.setKey(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral());
		logPathParam.setValue("/" + newProject.getName() + "/" + DEFAULT_LOG_DIR);
		conf.getParameters().add(logPathParam);

		ConfigurationParameters genPathParam = ApplicationFactory.eINSTANCE.createConfigurationParameters();
		genPathParam.setKey(StaticConfigurationParameters.GENERATIONOPTIONSDESTINATION_PATH.getLiteral());
		genPathParam.setValue("/" + newProject.getName() + "/" + DEFAULT_GEN_DIR);
		conf.getParameters().add(genPathParam);

		ConfigurationParameters cleanOption = ApplicationFactory.eINSTANCE.createConfigurationParameters();
		cleanOption.setKey(StaticConfigurationParameters.GENERATIONOPTIONSCLEAN.getLiteral());
		cleanOption.setValue("true");
		conf.getParameters().add(cleanOption);
		
		
		app.getElements().add(conf);
		// Models :
		for (IFile f : createdModels) {
			Model model = ApplicationFactory.eINSTANCE.createModel();
			model.setName(f.getName());

			String path = "/" + newProject.getName() + "/" + DEFAULT_MODELS_DIR + "/";
			if (".".concat(f.getFileExtension()).equalsIgnoreCase(ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.clazz.presentation.ClazzEditorID"))) {
				path = path.concat("data");
			} else if (".".concat(f.getFileExtension()).equalsIgnoreCase(ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.form.presentation.formEditorID"))) {
				path = path.concat("form");
			} else if (".".concat(f.getFileExtension()).equalsIgnoreCase(ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.portal.presentation.PortalEditorID"))) {
				path = path.concat("portal");
			} else if (".".concat(f.getFileExtension()).equalsIgnoreCase(ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.requirements.presentation.RequirementsEditorID"))) {
				path = path.concat("requirement");
			} else if (".".concat(f.getFileExtension()).equalsIgnoreCase(ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.view.presentation.ViewEditorID"))) {
				path = path.concat("view");
			} else if (".".concat(f.getFileExtension()).equalsIgnoreCase(ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.workflow.presentation.WorkflowEditorID"))) {
				path = path.concat("workflow");
			}
			path = path.concat("/" + f.getName());

			model.setFile(path);
			app.getElements().add(model);
		}
		ModelInitializationUtils.saveModel(file.getLocation().toFile(), app);

	}

	@Override
	public void addPages() {
		super.addPages();

		// Main Page :
		mainPage = new WizardNewProjectCreationPage(Activator.Messages.getString("Wizard.4")); //$NON-NLS-1$
		mainPage.setImageDescriptor(Activator.getImageDescriptor("$nl$/icons/createTopcasedProjectWizard.gif")); //$NON-NLS-1$
		mainPage.setTitle(Activator.Messages.getString("Wizard.6")); //$NON-NLS-1$
		mainPage.setDescription(Activator.Messages.getString("Wizard.7")); //$NON-NLS-1$
		addPage(mainPage);

		// Page on model choice and SIDE options
		optionsPage = new WizardModelOptionsPage(Activator.Messages.getString("Wizard.8")); //$NON-NLS-1$
		optionsPage.setTitle(Activator.Messages.getString("Wizard.9")); //$NON-NLS-1$
		optionsPage.setDescription(Activator.Messages.getString("Wizard.10")); //$NON-NLS-1$
		addPage(optionsPage);
	}

	private IProject createNewProject() {
		if (newProject != null) {
			return newProject;
		}

		// get a project handle
		final IProject newProjectHandle = mainPage.getProjectHandle();

		// get a project descriptor
		URI location = null;
		if (!mainPage.useDefaults()) {
			location = mainPage.getLocationURI();
		}

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		final IProjectDescription description = workspace.newProjectDescription(newProjectHandle.getName());
		description.setLocationURI(location);

		// create the new project operation
		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
			@Override
			protected void execute(IProgressMonitor monitor) throws CoreException {
				createProject(description, newProjectHandle, monitor);
			}
		};

		// run the new project creation operation
		try {
			getContainer().run(true, true, op);
		} catch (InterruptedException e) {
			return null;
		} catch (InvocationTargetException e) {
			// ie.- one of the steps resulted in a core exception
			Throwable t = e.getTargetException();
			if (t instanceof CoreException) {
				if (((CoreException) t).getStatus().getCode() == IResourceStatus.CASE_VARIANT_EXISTS) {
					MessageDialog.openError(getShell(), Activator.Messages.getString("Wizard.11"), //$NON-NLS-1$
							NLS.bind(Activator.Messages.getString("Wizard.12"), //$NON-NLS-1$
									newProjectHandle.getName()));
				} else {
					ErrorDialog.openError(getShell(), Activator.Messages.getString("Wizard.13"), //$NON-NLS-1$
							null, ((CoreException) t).getStatus());
				}
			} else {
				// CoreExceptions are handled above, but unexpected runtime
				// exceptions and errors may still occur.
				Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, t.toString(), t));
				MessageDialog.openError(getShell(), Activator.Messages.getString("Wizard.14"), NLS //$NON-NLS-1$
						.bind(Activator.Messages.getString("Wizard.15"), t.getMessage())); //$NON-NLS-1$
			}
			return null;
		}

		newProject = newProjectHandle;

		//Add the SIDE nature
		try {
			IProjectDescription desc = newProject.getDescription();
			String[] natures = desc.getNatureIds();
			String[] newNatures = new String[natures.length + 1];
			System.arraycopy(natures, 0, newNatures, 0, natures.length);
			newNatures[natures.length] = "com.bluexml.side.integration.eclipse.nature";
			description.setNatureIds(newNatures);
			newProject.setDescription(description, null);
		} catch (CoreException e) {
			//Nothing to do
		}

		return newProject;
	}

	/**
	 * Creates a project resource given the project handle and description.
	 * 
	 * @param description
	 *            the project description to create a project resource for
	 * @param projectHandle
	 *            the project handle to create a project resource for
	 * @param monitor
	 *            the progress monitor to show visual progress with
	 * @exception CoreException
	 *                if the operation fails
	 * @exception OperationCanceledException
	 *                if the operation is canceled
	 */
	void createProject(IProjectDescription description, IProject projectHandle, IProgressMonitor monitor) throws CoreException, OperationCanceledException {
		try {
			monitor.beginTask("", 2000);//$NON-NLS-1$

			projectHandle.create(description, new SubProgressMonitor(monitor, 1000));

			if (monitor.isCanceled()) {
				throw new OperationCanceledException();
			}

			projectHandle.open(IResource.BACKGROUND_REFRESH, new SubProgressMonitor(monitor, 1000));

		} finally {
			monitor.done();
		}
	}

	/**
	 * Selects and reveals the newly added resource in all parts of the active
	 * workbench window's active page.
	 * 
	 * @param newResource
	 *            the Resource to select and show
	 * @see ISetSelectionTarget
	 */
	protected void selectAndReveal(IResource newResource) {
		selectAndReveal(newResource, currentWorkbench.getActiveWorkbenchWindow());
	}

	/**
	 * Attempts to select and reveal the specified resource in all parts within
	 * the supplied workbench window's active page.
	 * <p>
	 * Checks all parts in the active page to see if they implement
	 * <code>ISetSelectionTarget</code>, either directly or as an adapter. If
	 * so, tells the part to select and reveal the specified resource.
	 * </p>
	 * 
	 * @param resource
	 *            the resource to be selected and revealed
	 * @param window
	 *            the workbench window to select and reveal the resource
	 * @see ISetSelectionTarget
	 */
	public static void selectAndReveal(IResource resource, IWorkbenchWindow window) {
		// validate the input
		if ((window == null) || (resource == null)) {
			return;
		}
		IWorkbenchPage page = window.getActivePage();
		if (page == null) {
			return;
		}

		// get all the view and editor parts
		List<IWorkbenchPart> parts = new ArrayList<IWorkbenchPart>();
		IWorkbenchPartReference refs[] = page.getViewReferences();
		for (IWorkbenchPartReference ref : refs) {
			IWorkbenchPart part = ref.getPart(false);
			if (part != null) {
				parts.add(part);
			}
		}
		refs = page.getEditorReferences();
		for (IWorkbenchPartReference ref : refs) {
			if (ref.getPart(false) != null) {
				parts.add(ref.getPart(false));
			}
		}

		final ISelection selection = new StructuredSelection(resource);
		Iterator<IWorkbenchPart> itr = parts.iterator();
		while (itr.hasNext()) {
			IWorkbenchPart part = itr.next();

			// get the part's ISetSelectionTarget implementation
			ISetSelectionTarget target = null;
			if (part instanceof ISetSelectionTarget) {
				target = (ISetSelectionTarget) part;
			} else {
				target = (ISetSelectionTarget) part.getAdapter(ISetSelectionTarget.class);
			}

			if (target != null) {
				// select and reveal resource
				final ISetSelectionTarget finalTarget = target;
				window.getShell().getDisplay().asyncExec(new Runnable() {
					public void run() {
						finalTarget.selectReveal(selection);
					}
				});
			}
		}

	}

	public IFolder prepareFolder(IFolder folder) throws CoreException {
		IContainer parent = folder.getParent();
		if (parent instanceof IFolder) {
			prepareFolder((IFolder) parent);
		}
		if (!folder.exists()) {
			folder.create(true, true, null);
		}
		return folder;
	}

}
