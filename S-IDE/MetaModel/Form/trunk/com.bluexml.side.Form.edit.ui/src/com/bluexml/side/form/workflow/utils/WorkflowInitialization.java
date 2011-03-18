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


package com.bluexml.side.form.workflow.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.form.ActionField;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.FormWorkflow;
import com.bluexml.side.form.WorkflowFormCollection;
import com.bluexml.side.util.libs.ui.UIUtils;
import com.bluexml.side.workflow.Attribute;
import com.bluexml.side.workflow.Process;
import com.bluexml.side.workflow.State;
import com.bluexml.side.workflow.Transition;
import com.bluexml.side.workflow.UserTask;

public class WorkflowInitialization {

	/**
	 * Launch initialization from a Workflow Form Collection
	 * 
	 * @param fc
	 * @param domain
	 * @return
	 */
	public static Command initialize(WorkflowFormCollection fc, EditingDomain domain) {
		CompoundCommand cmd = null;
		Process p = fc.getLinked_process();
		if (p != null) {
			boolean doWork = true;
			if (fc.getForms().size() > 0) {
				doWork = UIUtils.showConfirmation("Workflow already set", "This Workflow Form Collection has already been set. Do you want to overwrite it?");
			}

			if (doWork) {
				cmd = new CompoundCommand();
				// Delete all childs:
				if (fc.getForms().size() > 0) {
					cmd.append(RemoveCommand.create(domain, fc.getForms()));
				}
				// Get All Tasks
				List<State> l = new ArrayList<State>();
				l.add(p.getStartstate());
				l.addAll(p.getTasknode());

				// List of Form
				List<FormWorkflow> lf = new ArrayList<FormWorkflow>();

				// For each task we create a form
				for (State s : l) {
					FormWorkflow fw = createTaskForForm(p, s);
					fw.setRef(s);
					lf.add(fw);
				}
				cmd.append(AddCommand.create(domain, fc, FormPackage.eINSTANCE.getFormCollection_Forms(), lf));
			}
		} else {
			UIUtils.showError("No Process defined", "No Process has been defined. \n" + "Choose one and run Initialize again.");
		}
		return cmd;
	}

	/**
	 * Return a form a Task
	 * 
	 * @param p
	 * @param s
	 * @return
	 */
	public static FormWorkflow createTaskForForm(Process p, State s) {
		FormWorkflow fw = FormFactory.eINSTANCE.createFormWorkflow();
		fw.setId(p.getName() + "_" + s.getName());
		fw.setLabel(s.getName());
		fw.setRef(s);

		if (s instanceof UserTask) {
			UserTask ut = (UserTask) s;
			// For all attribute we get the field :
			for (Attribute a : ut.getAttributes()) {
				//if (a.getAllowedValues().size() == 0) { 
				// @Amenel: ModelChoiceFields are not supported in workflow forms (yet). Fields with
				// an allowed values list must remain a CharField since there's no class associated.
				Field f = WorkflowDiagramUtils.getFieldForAttribute(a);
				if (f != null) {
					fw.getChildren().add(f);
				}
				// } else {
				// ModelChoiceField f = FormFactory.eINSTANCE.createModelChoiceField();
				// f.setId(a.getName());
				// f.setRef(a);
				// if (a.getTitle() != null && a.getTitle().length() > 0) {
				// f.setLabel(a.getTitle());
				// } else {
				// f.setLabel(a.getName());
				// }
				// if (f != null) {
				// fw.getChildren().add(f);
				// }
				// }
			}

			// For attached class :
			if (((UserTask) s).getClazz().size() > 0) {
				for (Clazz c : ((UserTask) s).getClazz()) {
					Field f = WorkflowDiagramUtils.getFieldForClazzLink(c);
					if (f != null) {
						fw.getChildren().add(f);
					}

					// Commented : add the form class instead of model choice field
					/*
					 * FormClass fc = FormFactory.eINSTANCE.createFormClass();
					 * fc.setReal_class(c);
					 * fc.getChildren().addAll(ClassInitialization.
					 * getChildForFormClassFromClazz(fc));
					 * fw.getChildren().add(fc);
					 */
				}
			}

			// Same for Transition
			for (Transition t : ut.getTransition()) {
				ActionField af = WorkflowDiagramUtils.getOperationForTransition(t);
				if (af != null) {
					fw.getChildren().add(af);
				}
			}

			// add save button bug #1787

			ActionField save = FormFactory.eINSTANCE.createActionField();
			save.setId("wrkflw_save");
			save.setLabel("save");
			fw.getChildren().add(save);
			

		}
		return fw;
	}
}
