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


package com.bluexml.side.form.clazz;

import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.clazz.utils.ClassSynchronizationUtils;

public class SynchonizeWithClassDiagramAction  extends Action implements
ISelectionChangedListener {
	protected EObject selectedObject;
	private EditingDomain domain;

	public void selectionChanged(SelectionChangedEvent event) {
		if (event.getSelection() instanceof IStructuredSelection) {
			setEnabled(updateSelection((IStructuredSelection) event
					.getSelection()));
		} else {
			setEnabled(false);
		}
	}

	public boolean updateSelection(IStructuredSelection selection) {
		selectedObject = null;
		for (Iterator<?> objects = selection.iterator(); objects.hasNext();) {
			Object object = objects.next();
			if (object instanceof FormCollection) {
				selectedObject = (EObject) object;
			} else {
				return false;
			}
		}

		return selectedObject != null;
	}

	@Override
	public void run() {
		super.run();
		doAction((FormCollection)selectedObject);
	}
	
	private void doAction(FormCollection fc) {
		// We will iterate on each child on make action for each FormClass
		for(FormContainer form : fc.getForms()) {
			if (form instanceof FormClass) {
				Command c = ClassSynchronizationUtils.synchronizeClass((FormClass)form, domain);
				domain.getCommandStack().execute(c);
			}
		}
	}

	@Override
	public String getText() {
		return "Synchronize with Class Diagram";
	}

	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}
}
