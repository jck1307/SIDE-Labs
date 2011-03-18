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
package com.bluexml.side.requirements.generator.metamodel.WebProject;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#isCanCreate <em>Can Create</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#isCanRead <em>Can Read</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#isCanUpdate <em>Can Update</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#isCanDelete <em>Can Delete</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#getTable <em>Table</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#getProperties <em>Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getComponent()
 * @model
 * @generated
 */
public interface Component extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getComponent_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Can Create</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Can Create</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Can Create</em>' attribute.
	 * @see #setCanCreate(boolean)
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getComponent_CanCreate()
	 * @model
	 * @generated
	 */
	boolean isCanCreate();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#isCanCreate <em>Can Create</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Can Create</em>' attribute.
	 * @see #isCanCreate()
	 * @generated
	 */
	void setCanCreate(boolean value);

	/**
	 * Returns the value of the '<em><b>Can Read</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Can Read</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Can Read</em>' attribute.
	 * @see #setCanRead(boolean)
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getComponent_CanRead()
	 * @model
	 * @generated
	 */
	boolean isCanRead();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#isCanRead <em>Can Read</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Can Read</em>' attribute.
	 * @see #isCanRead()
	 * @generated
	 */
	void setCanRead(boolean value);

	/**
	 * Returns the value of the '<em><b>Can Update</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Can Update</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Can Update</em>' attribute.
	 * @see #setCanUpdate(boolean)
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getComponent_CanUpdate()
	 * @model
	 * @generated
	 */
	boolean isCanUpdate();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#isCanUpdate <em>Can Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Can Update</em>' attribute.
	 * @see #isCanUpdate()
	 * @generated
	 */
	void setCanUpdate(boolean value);

	/**
	 * Returns the value of the '<em><b>Can Delete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Can Delete</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Can Delete</em>' attribute.
	 * @see #setCanDelete(boolean)
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getComponent_CanDelete()
	 * @model
	 * @generated
	 */
	boolean isCanDelete();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#isCanDelete <em>Can Delete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Can Delete</em>' attribute.
	 * @see #isCanDelete()
	 * @generated
	 */
	void setCanDelete(boolean value);

	/**
	 * Returns the value of the '<em><b>Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table</em>' reference.
	 * @see #setTable(Table)
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getComponent_Table()
	 * @model
	 * @generated
	 */
	Table getTable();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#getTable <em>Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table</em>' reference.
	 * @see #getTable()
	 * @generated
	 */
	void setTable(Table value);

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getComponent_Properties()
	 * @model containment="true"
	 * @generated
	 */
	EList<ComponentProperty> getProperties();

} // Component
