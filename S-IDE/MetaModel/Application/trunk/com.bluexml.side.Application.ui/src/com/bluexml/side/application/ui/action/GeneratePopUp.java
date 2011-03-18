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


package com.bluexml.side.application.ui.action;

import java.io.File;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormText;

import com.bluexml.side.application.Application;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.application.ui.Activator;
import com.bluexml.side.application.ui.SWTResourceManager;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.application.ui.action.utils.Generate;
import com.bluexml.side.util.componentmonitor.ComponentMonitor;
import com.bluexml.side.util.componentmonitor.Monitor;
import com.bluexml.side.util.componentmonitor.guiAdapter.FormTextAdapter;
import com.bluexml.side.util.componentmonitor.guiAdapter.LabelAdapter;
import com.bluexml.side.util.componentmonitor.guiAdapter.ProgressBarAdapter;
import com.bluexml.side.util.componentmonitor.guiAdapter.StyledTextAdapter;
import com.bluexml.side.util.componentmonitor.headLessinterface.FormTextInterface;
import com.bluexml.side.util.componentmonitor.headLessinterface.LabelInterface;
import com.bluexml.side.util.componentmonitor.headLessinterface.ProgressBarInterface;
import com.bluexml.side.util.componentmonitor.headLessinterface.StyledTextInterface;
import com.bluexml.side.util.documentation.LogSave;
import com.bluexml.side.util.documentation.structure.enumeration.LogType;
import com.bluexml.side.util.libs.IFileHelper;

public class GeneratePopUp extends Dialog {

	IDialogEventListener listener = null;

	public void addDialogEventListener(IDialogEventListener listener) {
		this.listener = listener;
	}

	// model
	private IFile applicationFile;
	private Application applicationModel;
	private Configuration configuration;

	/**
	 * @return the componentMonitor
	 */
	public ComponentMonitor getComponentMonitor() {
		return componentMonitor;
	}

	/**
	 * @return the generalMonitor
	 */
	public Monitor getGeneralMonitor() {
		return generalMonitor;
	}

	private static boolean inAction = false;

	// view
	private ProgressBarInterface progressBar;
	private LabelInterface label;
	private ProgressBarInterface progressBar2;
	private LabelInterface label2;
	private StyledTextInterface styletext;
	private FormTextInterface logLink;
	private ComponentMonitor componentMonitor;
	private Monitor generalMonitor;

	public GeneratePopUp(Shell parentShell, IFile applicationFile, Application applicationModel, Configuration p_configuration) {
		//		super((Shell) null);
		super(parentShell);
		this.applicationFile = applicationFile;
		this.configuration = p_configuration;
		this.applicationModel = applicationModel;
		// dialog configuration
		setShellStyle(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | getDefaultOrientation());
		setBlockOnOpen(false);

	}

	/**
	 * Create contents of the dialog
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(null);

		// main title
		final Label generalLabel = new Label(container, SWT.NONE);
		generalLabel.setAlignment(SWT.CENTER);
		generalLabel.setBounds(10, 24, 514, 24);
		generalLabel.setFont(SWTResourceManager.getFont("", 12, SWT.BOLD)); //$NON-NLS-1$
		generalLabel.setText(Activator.Messages.getString("GeneratePopUp_1"));//$NON-NLS-1$

		// main proressBar
		final ProgressBar progressBar = new ProgressBar(container, SWT.SMOOTH);
		progressBar.setBounds(10, 48, 514, 17);

		// component title
		final Label subLabel = new Label(container, SWT.NONE);
		subLabel.setForeground(SWTResourceManager.getColor(92, 92, 92));
		subLabel.setAlignment(SWT.CENTER);
		subLabel.setBounds(10, 70, 464, 15);
		subLabel.setText(""); //$NON-NLS-1$

		// component progressBar
		final ProgressBar progressBar2 = new ProgressBar(container, SWT.SMOOTH);
		progressBar2.setBounds(10, 85, 514, 17);

		Display.getDefault().getActiveShell();

		final StyledText styletext = new StyledText(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		styletext.setBounds(10, 120, 514, 224);

		FormText logLink = new FormText(container, SWT.WRAP);
		logLink.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		logLink.setBounds(137, 350, 225, 55);
		logLink.setVisible(false);
		logLink.setText("<form><p>" + Activator.Messages.getString("GeneratePopUp_4") + "<a href=\"log\">" + Activator.Messages.getString("GeneratePopUp_6") + "</a>.</p></form>", true, true); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

		this.progressBar = new ProgressBarAdapter(progressBar);
		this.label = new LabelAdapter(generalLabel);
		this.progressBar2 = new ProgressBarAdapter(progressBar2);
		this.label2 = new LabelAdapter(subLabel);
		this.styletext = new StyledTextAdapter(styletext);
		this.logLink = new FormTextAdapter(logLink);

		String otherLogPath = ""; //$NON-NLS-1$
		for (ConfigurationParameters p : configuration.getParameters()) {
			if (p.getKey().equals(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral())) {
				otherLogPath = p.getValue();
				break;
			}
		}
		// instantiate monitors
		String fileName = "general_" + Generate.class.getName() + ".xml"; //$NON-NLS-1$ //$NON-NLS-2$
		generalMonitor = new Monitor(this.styletext, this.progressBar, this.label, otherLogPath, configuration.getName(), fileName);

		componentMonitor = new ComponentMonitor(this.styletext, this.progressBar2, null, this.label2, generalMonitor, null, LogType.GENERATION, generalMonitor.getConsoleLog(), fileName);

		return container;
	}

	@Override
	public int open() {
		if (!inAction) {
			inAction = true;
			return super.open();
		} else {
			return 0;
		}
	}

	/**
	 * Create contents of the button bar
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, Activator.Messages.getString("GeneratePopUp_3"), true);
		createButton(parent, IDialogConstants.CANCEL_ID, Activator.Messages.getString("GeneratePopUp_2"), false);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#cancelPressed()
	 */
	@Override
	protected void cancelPressed() {
		generalMonitor.addErrorText(Activator.Messages.getString("GeneratePopUp_0"));
		listener.addButtonPressedListener(IDialogConstants.CANCEL_ID);
		getButton(IDialogConstants.CANCEL_ID).setEnabled(false);
		// super.cancelPressed();
	}

	@Override
	public boolean close() {
		inAction = false;
		return super.close();
	}

	/**
	 * Return the initial size of the dialog
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(560, 470);
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Activator.Messages.getString("GeneratePopUp_10")); //$NON-NLS-1$
	}

	/**
	 * Method to setup dialog when job's done
	 */
	public void displayLink() {
		Display currentDisp = ApplicationUtil.getDisplay();
		currentDisp.syncExec(new Runnable() {
			public void run() {
				final String logPath = getGeneralMonitor().getConsoleLog().getLogDirectory();
				logLink.setVisible(true);
				logLink.addHyperlinkListener(new HyperlinkAdapter() {
					public void linkActivated(HyperlinkEvent event) {
						ApplicationUtil.browseTo("file://" + IFileHelper.getIFolder(logPath).getRawLocation().toFile().getAbsolutePath() + File.separator + LogSave.LOG_HTML_FILE_NAME); //$NON-NLS-1$
					}
				});
				// change button according to the "done" state
				getButton(IDialogConstants.CANCEL_ID).setEnabled(false);
				getButton(IDialogConstants.OK_ID).setText(Activator.Messages.getString("GeneratePopUp_9"));
				setButtonLayoutData(getButton(IDialogConstants.OK_ID));
				setButtonLayoutData(getButton(IDialogConstants.CANCEL_ID));
			}
		});

	}

	public void launch() {
		/*
		 * update .application with plugin extensions
		 */
		try {
			ApplicationUtil.updateApplicationFromExtensionPoint(applicationModel, applicationFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setBlockOnOpen(false);

		this.open();
		Display currentDisp = ApplicationUtil.getDisplay();
		currentDisp.syncExec(new Runnable() {
			public void run() {
				List<Model> models;
				models = ApplicationUtil.getModels((Application) configuration.eContainer());

				// set job to run
				final Generate gen = new Generate(configuration, models, GeneratePopUp.this.getGeneralMonitor(), GeneratePopUp.this.getComponentMonitor());
				// when job's done dialog must display link to open report html page...
				gen.addJobChangeListener(new IJobChangeListener() {
					public void sleeping(IJobChangeEvent event) {
					}

					public void scheduled(IJobChangeEvent event) {
					}

					public void running(IJobChangeEvent event) {
					}

					public void done(IJobChangeEvent event) {
						// display link only if not disposed yet
						if (GeneratePopUp.this != null && GeneratePopUp.this.getShell() != null) {
							GeneratePopUp.this.displayLink();
						} else {
//							System.err.println("GeneratePopup closed");
						}
						
					}

					public void awake(IJobChangeEvent event) {
					}

					public void aboutToRun(IJobChangeEvent event) {
					}
				});

				// manage cancel
				GeneratePopUp.this.addDialogEventListener(new IDialogEventListener() {
					public void addButtonPressedListener(int buttonId) {
						if (buttonId == IDialogConstants.CANCEL_ID) {
							gen.cancel();
							GeneratePopUp.this.displayLink();
						}
					}
				});

				// schedule side job
				gen.schedule();
			}
		});
	}

}
