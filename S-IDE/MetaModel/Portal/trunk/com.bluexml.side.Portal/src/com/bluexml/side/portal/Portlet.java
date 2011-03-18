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
package com.bluexml.side.portal;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Portlet</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.Portlet#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Portlet#getIsPortletInternal <em>Is Portlet Internal</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Portlet#getIsInstanceOfPortletType <em>Is Instance Of Portlet Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.portal.PortalPackage#getPortlet()
 * @model annotation="http://www.bluexml.com/OCL haveType='not (self.isPortletInternal.oclIsUndefined() and self.isInstanceOfPortletType.oclIsUndefined())'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='haveType'"
 * @generated
 */
public interface Portlet extends PortalModelElement {
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
	 * @see com.bluexml.side.portal.PortalPackage#getPortlet_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.Portlet#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Is Portlet Internal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Portlet Internal</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Portlet Internal</em>' reference.
	 * @see #setIsPortletInternal(PortletInternal)
	 * @see com.bluexml.side.portal.PortalPackage#getPortlet_IsPortletInternal()
	 * @model
	 * @generated
	 */
	PortletInternal getIsPortletInternal();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.Portlet#getIsPortletInternal <em>Is Portlet Internal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Portlet Internal</em>' reference.
	 * @see #getIsPortletInternal()
	 * @generated
	 */
	void setIsPortletInternal(PortletInternal value);

	/**
	 * Returns the value of the '<em><b>Is Instance Of Portlet Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Instance Of Portlet Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Instance Of Portlet Type</em>' containment reference.
	 * @see #setIsInstanceOfPortletType(InstanciatePortletType)
	 * @see com.bluexml.side.portal.PortalPackage#getPortlet_IsInstanceOfPortletType()
	 * @model containment="true"
	 * @generated
	 */
	InstanciatePortletType getIsInstanceOfPortletType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.Portlet#getIsInstanceOfPortletType <em>Is Instance Of Portlet Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Instance Of Portlet Type</em>' containment reference.
	 * @see #getIsInstanceOfPortletType()
	 * @generated
	 */
	void setIsInstanceOfPortletType(InstanciatePortletType value);

} // Portlet
