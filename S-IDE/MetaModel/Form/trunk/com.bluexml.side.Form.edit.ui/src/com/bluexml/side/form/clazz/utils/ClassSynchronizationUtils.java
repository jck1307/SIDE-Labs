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


package com.bluexml.side.form.clazz.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.common.OperationComponent;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormAspect;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.common.utils.FieldTransformation;
import com.bluexml.side.form.common.utils.FormDiagramUtils;
import com.bluexml.side.form.common.utils.InternalModification;
import com.bluexml.side.util.libs.ecore.EcoreHelper;

public class ClassSynchronizationUtils {

	protected static CompoundCommand cc;

	public static Command synchronizeClass(FormClass fc, EditingDomain domain) {
		cc = new CompoundCommand();
		InternalModification.dontMoveToDisabled();
		if (fc.getReal_class() != null) {
			Clazz cl = fc.getReal_class();

			Collection<FormElement> cToDelete = new ArrayList<FormElement>();
			Collection<Clazz> listClazz = ClassDiagramUtils.getInheritedClazzs(cl);
			HashMap<String, FormElement> formChild = FormDiagramUtils.getFormChild(fc);
			//			HashMap<String, ModelElement> ClazzChild = ClassDiagramUtils.getClazzChild(listClazz);

			// First we check for Add and Update
			getAddCommand(fc, domain, listClazz, formChild);
			// Then for delete			
			for (FormElement fe : fc.getFields()) {
				// check validity
				if ((fe instanceof Field) && (fe.getRef() == null || !EcoreHelper.validate(fe))) {
					// field not valid
					cToDelete.add(fe);
				} else if (fe.getRef() instanceof Attribute) {
					Attribute att = (Attribute) fe.getRef();
					AbstractClass abstractClass = (AbstractClass) att.eContainer();
					if (abstractClass instanceof Clazz) {
						EList<Attribute> allAttributes = ((Clazz) abstractClass).getAllAttributes();
						if (!allAttributes.contains(att)) {
							// something wrong attribute have been moved ?
							cToDelete.add(fe);
						}
					}
				}

			}
			if (cToDelete.size() > 0) {

				Command delCmd = RemoveCommand.create(domain, cToDelete);
				cc.append(delCmd);
			}

		}
		InternalModification.moveToDisabled();
		return cc;
	}

	protected static Command getAddCommand(FormClass fc, EditingDomain domain, Collection<Clazz> listClazz, HashMap<String, FormElement> formChild) {
		Collection<FormElement> cToAdd = new ArrayList<FormElement>();
		Collection<FormElement> cToDel = new ArrayList<FormElement>();
		for (Clazz clazz : listClazz) {
			// Attributes
			for (Attribute att : clazz.getAttributes()) {
				getCommandForAttribute(domain, formChild, att, fc);
			}

			// Aspects
			for (Aspect asp : clazz.getAspects()) {
				if (formChild.containsKey(asp.getName())) {
					// Modification

					for (Attribute att : asp.getAttributes()) {
						getCommandForAttribute(domain, formChild, att, (FormGroup) formChild.get(asp.getName()));
					}
				} else {
					// Add

					FormAspect fa = FormFactory.eINSTANCE.createFormAspect();
					fa.setId(asp.getName());
					fa.setRef(asp);
					fa.setLabel(asp.getLabel());
					Collection<Field> cf = new ArrayList<Field>();
					for (Attribute att : asp.getAttributes()) {
						Field field = ClassDiagramUtils.getFieldForAttribute(att);
						cf.add(field);
					}
					fa.getChildren().addAll(cf);
					cToAdd.add(fa);
				}

			}

			// Associations :

			for (Association ass : clazz.getSourceAssociations()) {
				getCommandsForAssociation(fc, domain, formChild, clazz, ass);
			}

			// Operations :
			for (OperationComponent op : clazz.getOperations()) {
				if (!formChild.containsKey(op.getName())) {
					Field field = ClassDiagramUtils.getFieldForOperation(op);
					cToAdd.add(field);
				}
			}
		}
		if (cToAdd.size() > 0) {
			cc.append(AddCommand.create(domain, fc, FormPackage.eINSTANCE.getFormGroup_Children(), cToAdd));
		}

		if (cToDel.size() > 0) {
			cc.append(DeleteCommand.create(domain, cToDel));
		}
		return cc;
	}

	private static void getCommandsForAssociation(FormClass fc, EditingDomain domain, HashMap<String, FormElement> formChild, Clazz clazz, Association ass) {
		// SOURCE
		String associationId = "";
		if (ass.getFirstEnd().getLinkedClass().equals(clazz) && ass.getSecondEnd().isNavigable()) {
			associationId = ClassDiagramUtils.getAssociationName(ass, false);
		}

		// Add
		if ((associationId.length() > 0) && !formChild.containsKey(associationId)) {
			cc.append(AddCommand.create(domain, fc, FormPackage.eINSTANCE.getFormGroup_Children(), ClassDiagramUtils.transformAssociationIntoModelChoiceField(ass, clazz)));
		} else {
			// Modification
			Field mcf = (ModelChoiceField) formChild.get(associationId);
			if (ass.getSecondEnd().isNavigable() && ass.getFirstEnd().getLinkedClass().equals(clazz) && (mcf != null)) {
				if ((((ModelChoiceField) mcf).getMax_bound() > Integer.parseInt(ass.getSecondEnd().getCardMax())) && (Integer.parseInt(ass.getSecondEnd().getCardMax()) != -1)) {
					cc.append(SetCommand.create(domain, mcf, FormPackage.eINSTANCE.getModelChoiceField_Max_bound(), Integer.parseInt(ass.getSecondEnd().getCardMax())));
				}
			}
		}

		// TARGET
		associationId = "";
		if (ass.getSecondEnd().getLinkedClass().equals(clazz) && ass.getFirstEnd().isNavigable()) {
			associationId = ClassDiagramUtils.getAssociationName(ass, true);
		}

		// Add
		if ((associationId.length() > 0) && !formChild.containsKey(associationId)) {
			cc.append(AddCommand.create(domain, fc, FormPackage.eINSTANCE.getFormGroup_Children(), ClassDiagramUtils.transformAssociationIntoModelChoiceField(ass, clazz)));
		} else {
			// Modification
			Field mcf = (ModelChoiceField) formChild.get(associationId);
			if (ass.getFirstEnd().isNavigable() && ass.getFirstEnd().getLinkedClass().equals(clazz) && (mcf != null)) {
				if ((((ModelChoiceField) mcf).getMax_bound() > Integer.parseInt(ass.getFirstEnd().getCardMax())) && (Integer.parseInt(ass.getFirstEnd().getCardMax()) != -1)) {
					cc.append(SetCommand.create(domain, mcf, FormPackage.eINSTANCE.getModelChoiceField_Max_bound(), Integer.parseInt(ass.getFirstEnd().getCardMax())));
				}
			}
		}
	}

	protected static void getCommandForAttribute(EditingDomain domain, HashMap<String, FormElement> formChild, Attribute att, FormGroup fg) {
		FormElement foundFormElement = null;
		if (fg instanceof FormClass) {
			for (FormElement fe : ((FormClass) fg).getFields()) {
				if ((fe.getRef() != null) && fe.getRef().equals(att)) {
					foundFormElement = fe;
				}
			}
		} else {
			for (FormElement fe : fg.getChildren()) {
				if ((fe.getRef() != null) && fe.getRef().equals(att)) {
					foundFormElement = fe;
				}
			}
		}

		// is it here?
		if (foundFormElement != null) {
			Field actualField = (Field) foundFormElement;
			Field transformedAttribute = ClassDiagramUtils.getFieldForAttribute(att);

			// Field Transformation
			if (!transformedAttribute.getClass().equals(actualField.getClass())) {
				// If a field have been transformed we won't change it
				// so we check that the field could be of the actual type
				if (!FieldTransformation.getAvailableTransformation(transformedAttribute).contains(actualField.eClass().getName())) {
					cc.append(AddCommand.create(domain, fg, FormPackage.eINSTANCE.getFormGroup_Children(), transformedAttribute, ((FormGroup) actualField.eContainer()).getChildren().lastIndexOf(actualField))); //
					actualField.setMandatory(false);
					Command rmCmd = RemoveCommand.create(domain, actualField);
					cc.append(rmCmd);
				}
				// Field set to mandatory :
			} else if (transformedAttribute.isMandatory() && !actualField.isMandatory()) {
				// If field is mandatory but put in disabled we must move it to Children
				if (actualField.eContainmentFeature().equals(FormPackage.eINSTANCE.getFormGroup_Disabled())) {
					actualField.setMandatory(false);
					Command addCmd = AddCommand.create(domain, fg, FormPackage.eINSTANCE.getFormGroup_Children(), actualField);
					cc.append(addCmd);
				} else {
					cc.append(SetCommand.create(domain, actualField, FormPackage.eINSTANCE.getField_Mandatory(), true));
				}
				//Update id
			} else if ((transformedAttribute.getId() != null) && !(transformedAttribute.getId().equals(actualField.getId()))) {

				Command c = SetCommand.create(domain, actualField, FormPackage.eINSTANCE.getFormElement_Id(), transformedAttribute.getId());
				Command c2 = SetCommand.create(domain, actualField, FormPackage.eINSTANCE.getFormElement_Label(), transformedAttribute.getLabel());
				cc.append(c);
				cc.append(c2);
			}
		} else {
			// Add
			Field field = null;
			field = ClassDiagramUtils.getFieldForAttribute(att);
			cc.append(AddCommand.create(domain, fg, FormPackage.eINSTANCE.getFormGroup_Children(), field));
		}
	}
}
