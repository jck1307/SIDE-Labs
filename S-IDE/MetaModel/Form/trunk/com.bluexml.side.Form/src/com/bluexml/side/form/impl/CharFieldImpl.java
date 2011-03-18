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
package com.bluexml.side.form.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.form.CharField;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Char Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.form.impl.CharFieldImpl#getMin_length <em>Min length</em>}</li>
 *   <li>{@link com.bluexml.side.form.impl.CharFieldImpl#getMax_length <em>Max length</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CharFieldImpl extends FieldImpl implements CharField {
	/**
	 * The default value of the '{@link #getMin_length() <em>Min length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin_length()
	 * @generated
	 * @ordered
	 */
	protected static final int MIN_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMin_length() <em>Min length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin_length()
	 * @generated
	 * @ordered
	 */
	protected int min_length = MIN_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getMax_length() <em>Max length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax_length()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMax_length() <em>Max length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax_length()
	 * @generated
	 * @ordered
	 */
	protected int max_length = MAX_LENGTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CharFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.CHAR_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMin_length() {
		return min_length;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMin_length(int newMin_length) {
		int oldMin_length = min_length;
		min_length = newMin_length;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.CHAR_FIELD__MIN_LENGTH, oldMin_length, min_length));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMax_length() {
		return max_length;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMax_length(int newMax_length) {
		int oldMax_length = max_length;
		max_length = newMax_length;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.CHAR_FIELD__MAX_LENGTH, oldMax_length, max_length));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.CHAR_FIELD__MIN_LENGTH:
				return getMin_length();
			case FormPackage.CHAR_FIELD__MAX_LENGTH:
				return getMax_length();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case FormPackage.CHAR_FIELD__MIN_LENGTH:
				setMin_length((Integer)newValue);
				return;
			case FormPackage.CHAR_FIELD__MAX_LENGTH:
				setMax_length((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case FormPackage.CHAR_FIELD__MIN_LENGTH:
				setMin_length(MIN_LENGTH_EDEFAULT);
				return;
			case FormPackage.CHAR_FIELD__MAX_LENGTH:
				setMax_length(MAX_LENGTH_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case FormPackage.CHAR_FIELD__MIN_LENGTH:
				return min_length != MIN_LENGTH_EDEFAULT;
			case FormPackage.CHAR_FIELD__MAX_LENGTH:
				return max_length != MAX_LENGTH_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (min_length: ");
		result.append(min_length);
		result.append(", max_length: ");
		result.append(max_length);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //CharFieldImpl
