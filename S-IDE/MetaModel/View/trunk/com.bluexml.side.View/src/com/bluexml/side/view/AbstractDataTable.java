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
 
import com.bluexml.side.common.OperationComponent;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Data Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.AbstractDataTable#getHaveRowActions <em>Have Row Actions</em>}</li>
 *   <li>{@link com.bluexml.side.view.AbstractDataTable#getHaveSelectActions <em>Have Select Actions</em>}</li>
 *   <li>{@link com.bluexml.side.view.AbstractDataTable#getHaveDefaultColActions <em>Have Default Col Actions</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getAbstractDataTable()
 * @model abstract="true"
 * @generated
 */
public interface AbstractDataTable extends AbstractViewOf, DataTableElement, Paginable {
	/**
	 * Returns the value of the '<em><b>Have Row Actions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Have Row Actions</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Have Row Actions</em>' containment reference.
	 * @see #setHaveRowActions(OperationComponent)
	 * @see com.bluexml.side.view.ViewPackage#getAbstractDataTable_HaveRowActions()
	 * @model containment="true"
	 *        annotation="http://www.topcased.org/documentation documentation='set action list available on each row'"
	 * @generated
	 */
	OperationComponent getHaveRowActions();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.AbstractDataTable#getHaveRowActions <em>Have Row Actions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Have Row Actions</em>' containment reference.
	 * @see #getHaveRowActions()
	 * @generated
	 */
	void setHaveRowActions(OperationComponent value);

	/**
	 * Returns the value of the '<em><b>Have Select Actions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Have Select Actions</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Have Select Actions</em>' containment reference.
	 * @see #setHaveSelectActions(OperationComponent)
	 * @see com.bluexml.side.view.ViewPackage#getAbstractDataTable_HaveSelectActions()
	 * @model containment="true"
	 *        annotation="http://www.topcased.org/documentation documentation='set available actions on selected rows'"
	 * @generated
	 */
	OperationComponent getHaveSelectActions();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.AbstractDataTable#getHaveSelectActions <em>Have Select Actions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Have Select Actions</em>' containment reference.
	 * @see #getHaveSelectActions()
	 * @generated
	 */
	void setHaveSelectActions(OperationComponent value);

	/**
	 * Returns the value of the '<em><b>Have Default Col Actions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Have Default Col Actions</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Have Default Col Actions</em>' containment reference.
	 * @see #setHaveDefaultColActions(OperationComponent)
	 * @see com.bluexml.side.view.ViewPackage#getAbstractDataTable_HaveDefaultColActions()
	 * @model containment="true"
	 *        annotation="http://www.topcased.org/documentation documentation='use this to setup default actions available on each colomn'"
	 * @generated
	 */
	OperationComponent getHaveDefaultColActions();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.AbstractDataTable#getHaveDefaultColActions <em>Have Default Col Actions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Have Default Col Actions</em>' containment reference.
	 * @see #getHaveDefaultColActions()
	 * @generated
	 */
	void setHaveDefaultColActions(OperationComponent value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL description='Get all the Cols of the AbstractView' body='self.children->select(oclIsTypeOf(Col)).oclAsType(Col)'"
	 * @generated
	 */
	EList<Col> getCols();
		
} // AbstractDataTable
