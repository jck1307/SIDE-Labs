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
package com.bluexml.side.common;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Named Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: main elements of model inherits of NameModelElement which allows to declare the name and which provides multiple OCL operations used by the modelers and the generators.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.common.NamedModelElement#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.common.CommonPackage#getNamedModelElement()
 * @model
 * @generated
 */
public interface NamedModelElement extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: the name of the concerned element; this name is used as leaf of the fully qualified name of an element: the other part of the fully qualified name are composed by the tree package names.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.bluexml.side.common.CommonPackage#getNamedModelElement_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.common.NamedModelElement#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.bluexml.com/OCL body='if self.getContainer().oclIsUndefined() then\r\t\'\'\relse\r\tif self.getContainer().oclIsKindOf(NamedModelElement) then\n\t\tlet parent : String = self.getContainer().oclAsType(NamedModelElement).getFullName()\n\t\tin\n\t\t\tif ((not(parent.oclIsUndefined())) and (parent.size() > 0)) then\n\t\t\t\tparent.concat(\'.\').concat(self.name)\n\t\t\telse\n\t\t\t\tself.name\n\t\t\tendif\r\telse\r\t\t\'\'\r\tendif\t\rendif'"
	 * @generated
	 */
	String getFullName();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='if self.documentation.oclIsUndefined() or self.documentation.size() <0 then\r\tself.name\relse\r\tself.documentation\rendif'"
	 * @generated
	 */
	String getDocumentationOrName();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='if self.description.oclIsUndefined() or self.description.size() <0 then\r\tself.name\relse\r\tself.description\rendif'"
	 * @generated
	 */
	String getDescriptionOrName();

} // NamedModelElement
