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
package com.bluexml.side.application;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Application</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.application.Application#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.application.ApplicationPackage#getApplication()
 * @model
 * @generated
 */
public interface Application extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.application.ModelElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see com.bluexml.side.application.ApplicationPackage#getApplication_Elements()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<ModelElement> getElements();
	
	/**
	 * Return the configuration with the same name
	 * @param name of the configuration
	 * @return the finded configuration or null
	 */
	
	Configuration getConfiguration(String name);

} // Application
