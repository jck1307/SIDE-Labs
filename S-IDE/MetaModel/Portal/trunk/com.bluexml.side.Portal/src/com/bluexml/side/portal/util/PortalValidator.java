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


/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal.util;

import com.bluexml.side.portal.*;

import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;

import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.portal.PortalPackage
 * @generated
 */
public class PortalValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final PortalValidator INSTANCE = new PortalValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "com.bluexml.side.portal";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The parsed OCL expression for the definition of the '<em>portalNameEmpty</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint portal_portalNameEmptyInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>haveType</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint portlet_haveTypeInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>haveIdentifier</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint portletType_haveIdentifierInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>unicID</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint portletType_unicIDInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>haveType</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint portletInternal_haveTypeInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>isvalide</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint havePortlet_isvalideInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>haveType</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint instanciatePortletType_haveTypeInvOCL;
	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();
	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortalValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return PortalPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresonding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case PortalPackage.PORTAL_MODEL_ELEMENT:
				return validatePortalModelElement((PortalModelElement)value, diagnostics, context);
			case PortalPackage.PORTAL:
				return validatePortal((Portal)value, diagnostics, context);
			case PortalPackage.PAGE:
				return validatePage((Page)value, diagnostics, context);
			case PortalPackage.PORTAL_LAYOUT:
				return validatePortalLayout((PortalLayout)value, diagnostics, context);
			case PortalPackage.COLUMN:
				return validateColumn((Column)value, diagnostics, context);
			case PortalPackage.PORTLET:
				return validatePortlet((Portlet)value, diagnostics, context);
			case PortalPackage.PORTLET_TYPE:
				return validatePortletType((PortletType)value, diagnostics, context);
			case PortalPackage.PORTLET_INTERNAL:
				return validatePortletInternal((PortletInternal)value, diagnostics, context);
			case PortalPackage.ABSTRACT_PORTLET_ATTRIBUTES:
				return validateAbstractPortletAttributes((AbstractPortletAttributes)value, diagnostics, context);
			case PortalPackage.GROUP:
				return validateGroup((Group)value, diagnostics, context);
			case PortalPackage.PORTLET_ATTRIBUTE:
				return validatePortletAttribute((PortletAttribute)value, diagnostics, context);
			case PortalPackage.HAVE_PORTLET:
				return validateHavePortlet((HavePortlet)value, diagnostics, context);
			case PortalPackage.POSITION_GROUP:
				return validatePositionGroup((PositionGroup)value, diagnostics, context);
			case PortalPackage.INSTANCIATE_PORTLET_TYPE:
				return validateInstanciatePortletType((InstanciatePortletType)value, diagnostics, context);
			case PortalPackage.PORTLET_ATTRIBUTE_INSTANCE:
				return validatePortletAttributeInstance((PortletAttributeInstance)value, diagnostics, context);
			case PortalPackage.IS_CHILD_PAGE:
				return validateisChildPage((isChildPage)value, diagnostics, context);
			case PortalPackage.WIDTH_UNIT:
				return validatewidthUnit((widthUnit)value, diagnostics, context);
			case PortalPackage.INTERNAL_PORTLET_TYPE:
				return validateInternalPortletType((InternalPortletType)value, diagnostics, context);
			case PortalPackage.PORTLET_TYPE_ATTRIBUTE_TYPE:
				return validatePortletTypeAttributeType((PortletTypeAttributeType)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortalModelElement(PortalModelElement portalModelElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(portalModelElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortal(Portal portal, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(portal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(portal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(portal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(portal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(portal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(portal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(portal, diagnostics, context);
		if (result || diagnostics != null) result &= validatePortal_portalNameEmpty(portal, diagnostics, context);
		return result;
	}

	/**
	 * Validates the portalNameEmpty constraint of '<em>Portal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortal_portalNameEmpty(Portal portal, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (portal_portalNameEmptyInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(PortalPackage.Literals.PORTAL);

			EAnnotation ocl = PortalPackage.Literals.PORTAL.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("portalNameEmpty");

			try {
				portal_portalNameEmptyInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(portal_portalNameEmptyInvOCL);

		if (!query.check(portal)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( PortalPackage.Literals.PORTAL.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"portalNameEmpty")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "portalNameEmpty", getObjectLabel(portal, context) }),
						 new Object[] { portal }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePage(Page page, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(page, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortalLayout(PortalLayout portalLayout, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(portalLayout, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateColumn(Column column, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(column, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortlet(Portlet portlet, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(portlet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(portlet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(portlet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(portlet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(portlet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(portlet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(portlet, diagnostics, context);
		if (result || diagnostics != null) result &= validatePortlet_haveType(portlet, diagnostics, context);
		return result;
	}

	/**
	 * Validates the haveType constraint of '<em>Portlet</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortlet_haveType(Portlet portlet, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (portlet_haveTypeInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(PortalPackage.Literals.PORTLET);

			EAnnotation ocl = PortalPackage.Literals.PORTLET.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("haveType");

			try {
				portlet_haveTypeInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(portlet_haveTypeInvOCL);

		if (!query.check(portlet)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( PortalPackage.Literals.PORTLET.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"haveType")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "haveType", getObjectLabel(portlet, context) }),
						 new Object[] { portlet }));
			}
			return false;
		}
		return true;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortletType(PortletType portletType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(portletType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(portletType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(portletType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(portletType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(portletType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(portletType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(portletType, diagnostics, context);
		if (result || diagnostics != null) result &= validatePortletType_haveIdentifier(portletType, diagnostics, context);
		if (result || diagnostics != null) result &= validatePortletType_unicID(portletType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the haveIdentifier constraint of '<em>Portlet Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortletType_haveIdentifier(PortletType portletType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (portletType_haveIdentifierInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(PortalPackage.Literals.PORTLET_TYPE);

			EAnnotation ocl = PortalPackage.Literals.PORTLET_TYPE.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("haveIdentifier");

			try {
				portletType_haveIdentifierInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(portletType_haveIdentifierInvOCL);

		if (!query.check(portletType)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( PortalPackage.Literals.PORTLET_TYPE.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"haveIdentifier")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "haveIdentifier", getObjectLabel(portletType, context) }),
						 new Object[] { portletType }));
			}
			return false;
		}
		return true;
	}
	/**
	 * Validates the unicID constraint of '<em>Portlet Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortletType_unicID(PortletType portletType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (portletType_unicIDInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(PortalPackage.Literals.PORTLET_TYPE);

			EAnnotation ocl = PortalPackage.Literals.PORTLET_TYPE.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("unicID");

			try {
				portletType_unicIDInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(portletType_unicIDInvOCL);

		if (!query.check(portletType)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( PortalPackage.Literals.PORTLET_TYPE.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"unicID")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "unicID", getObjectLabel(portletType, context) }),
						 new Object[] { portletType }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortletInternal(PortletInternal portletInternal, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(portletInternal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(portletInternal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(portletInternal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(portletInternal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(portletInternal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(portletInternal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(portletInternal, diagnostics, context);
		if (result || diagnostics != null) result &= validatePortletInternal_haveType(portletInternal, diagnostics, context);
		return result;
	}

	/**
	 * Validates the haveType constraint of '<em>Portlet Internal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortletInternal_haveType(PortletInternal portletInternal, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (portletInternal_haveTypeInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(PortalPackage.Literals.PORTLET_INTERNAL);

			EAnnotation ocl = PortalPackage.Literals.PORTLET_INTERNAL.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("haveType");

			try {
				portletInternal_haveTypeInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(portletInternal_haveTypeInvOCL);

		if (!query.check(portletInternal)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( PortalPackage.Literals.PORTLET_INTERNAL.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"haveType")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "haveType", getObjectLabel(portletInternal, context) }),
						 new Object[] { portletInternal }));
			}
			return false;
		}
		return true;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractPortletAttributes(AbstractPortletAttributes abstractPortletAttributes, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(abstractPortletAttributes, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGroup(Group group, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(group, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortletAttribute(PortletAttribute portletAttribute, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(portletAttribute, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHavePortlet(HavePortlet havePortlet, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(havePortlet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(havePortlet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(havePortlet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(havePortlet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(havePortlet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(havePortlet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(havePortlet, diagnostics, context);
		if (result || diagnostics != null) result &= validateHavePortlet_isvalide(havePortlet, diagnostics, context);
		return result;
	}

	/**
	 * Validates the isvalide constraint of '<em>Have Portlet</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHavePortlet_isvalide(HavePortlet havePortlet, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (havePortlet_isvalideInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(PortalPackage.Literals.HAVE_PORTLET);

			EAnnotation ocl = PortalPackage.Literals.HAVE_PORTLET.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("isvalide");

			try {
				havePortlet_isvalideInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(havePortlet_isvalideInvOCL);

		if (!query.check(havePortlet)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( PortalPackage.Literals.HAVE_PORTLET.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"isvalide")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "isvalide", getObjectLabel(havePortlet, context) }),
						 new Object[] { havePortlet }));
			}
			return false;
		}
		return true;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePositionGroup(PositionGroup positionGroup, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(positionGroup, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInstanciatePortletType(InstanciatePortletType instanciatePortletType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(instanciatePortletType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(instanciatePortletType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(instanciatePortletType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(instanciatePortletType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(instanciatePortletType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(instanciatePortletType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(instanciatePortletType, diagnostics, context);
		if (result || diagnostics != null) result &= validateInstanciatePortletType_haveType(instanciatePortletType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the haveType constraint of '<em>Instanciate Portlet Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInstanciatePortletType_haveType(InstanciatePortletType instanciatePortletType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (instanciatePortletType_haveTypeInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(PortalPackage.Literals.INSTANCIATE_PORTLET_TYPE);

			EAnnotation ocl = PortalPackage.Literals.INSTANCIATE_PORTLET_TYPE.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("haveType");

			try {
				instanciatePortletType_haveTypeInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(instanciatePortletType_haveTypeInvOCL);

		if (!query.check(instanciatePortletType)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( PortalPackage.Literals.INSTANCIATE_PORTLET_TYPE.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"haveType")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "haveType", getObjectLabel(instanciatePortletType, context) }),
						 new Object[] { instanciatePortletType }));
			}
			return false;
		}
		return true;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortletAttributeInstance(PortletAttributeInstance portletAttributeInstance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(portletAttributeInstance, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateisChildPage(isChildPage isChildPage, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(isChildPage, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatewidthUnit(widthUnit widthUnit, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInternalPortletType(InternalPortletType internalPortletType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortletTypeAttributeType(PortletTypeAttributeType portletTypeAttributeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	protected boolean doThrowError(EAnnotation ecore, String ruleName) {
		String warningList = ecore.getDetails().get("warning");
		boolean throwError = true;
		if (warningList != null) {
			List<String> list = Arrays.asList(warningList.split(" "));
			if (list.contains(ruleName)) {
				throwError = false;
			}
		}
		return throwError;
	}
} //PortalValidator
