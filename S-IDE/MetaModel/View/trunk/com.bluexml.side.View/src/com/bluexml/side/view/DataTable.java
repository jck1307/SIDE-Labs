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
package com.bluexml.side.view;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.DataTable#getDefaultColSetUp <em>Default Col Set Up</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getDataTable()
 * @model
 * @generated
 */
public interface DataTable extends AbstractDataTable, Actionable {
	/**
	 * Returns the value of the '<em><b>Default Col Set Up</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Col Set Up</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Col Set Up</em>' containment reference.
	 * @see #setDefaultColSetUp(Col)
	 * @see com.bluexml.side.view.ViewPackage#getDataTable_DefaultColSetUp()
	 * @model containment="true"
	 * @generated
	 */
	Col getDefaultColSetUp();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.DataTable#getDefaultColSetUp <em>Default Col Set Up</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Col Set Up</em>' containment reference.
	 * @see #getDefaultColSetUp()
	 * @generated
	 */
	void setDefaultColSetUp(Col value);
		
} // DataTable
