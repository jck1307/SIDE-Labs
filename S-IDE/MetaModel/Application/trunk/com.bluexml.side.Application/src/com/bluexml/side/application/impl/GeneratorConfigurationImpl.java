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
package com.bluexml.side.application.impl;

import com.bluexml.side.application.ApplicationPackage;
import com.bluexml.side.application.GeneratorConfiguration;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generator Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.application.impl.GeneratorConfigurationImpl#getId_metamodel <em>Id metamodel</em>}</li>
 *   <li>{@link com.bluexml.side.application.impl.GeneratorConfigurationImpl#getGeneratorName <em>Generator Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GeneratorConfigurationImpl extends ComponantConfigurationImpl implements GeneratorConfiguration {
	/**
	 * The default value of the '{@link #getId_metamodel() <em>Id metamodel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId_metamodel()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_METAMODEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId_metamodel() <em>Id metamodel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId_metamodel()
	 * @generated
	 * @ordered
	 */
	protected String id_metamodel = ID_METAMODEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getGeneratorName() <em>Generator Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneratorName()
	 * @generated
	 * @ordered
	 */
	protected static final String GENERATOR_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGeneratorName() <em>Generator Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneratorName()
	 * @generated
	 * @ordered
	 */
	protected String generatorName = GENERATOR_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GeneratorConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ApplicationPackage.Literals.GENERATOR_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId_metamodel() {
		return id_metamodel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId_metamodel(String newId_metamodel) {
		String oldId_metamodel = id_metamodel;
		id_metamodel = newId_metamodel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.GENERATOR_CONFIGURATION__ID_METAMODEL, oldId_metamodel, id_metamodel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGeneratorName() {
		return generatorName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGeneratorName(String newGeneratorName) {
		String oldGeneratorName = generatorName;
		generatorName = newGeneratorName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.GENERATOR_CONFIGURATION__GENERATOR_NAME, oldGeneratorName, generatorName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ApplicationPackage.GENERATOR_CONFIGURATION__ID_METAMODEL:
				return getId_metamodel();
			case ApplicationPackage.GENERATOR_CONFIGURATION__GENERATOR_NAME:
				return getGeneratorName();
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
			case ApplicationPackage.GENERATOR_CONFIGURATION__ID_METAMODEL:
				setId_metamodel((String)newValue);
				return;
			case ApplicationPackage.GENERATOR_CONFIGURATION__GENERATOR_NAME:
				setGeneratorName((String)newValue);
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
			case ApplicationPackage.GENERATOR_CONFIGURATION__ID_METAMODEL:
				setId_metamodel(ID_METAMODEL_EDEFAULT);
				return;
			case ApplicationPackage.GENERATOR_CONFIGURATION__GENERATOR_NAME:
				setGeneratorName(GENERATOR_NAME_EDEFAULT);
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
			case ApplicationPackage.GENERATOR_CONFIGURATION__ID_METAMODEL:
				return ID_METAMODEL_EDEFAULT == null ? id_metamodel != null : !ID_METAMODEL_EDEFAULT.equals(id_metamodel);
			case ApplicationPackage.GENERATOR_CONFIGURATION__GENERATOR_NAME:
				return GENERATOR_NAME_EDEFAULT == null ? generatorName != null : !GENERATOR_NAME_EDEFAULT.equals(generatorName);
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
		result.append(" (id_metamodel: ");
		result.append(id_metamodel);
		result.append(", generatorName: ");
		result.append(generatorName);
		result.append(')');
		return result.toString();
	}

} //GeneratorConfigurationImpl
