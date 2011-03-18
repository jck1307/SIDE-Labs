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
package com.bluexml.side.form;




/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Form Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The Form Class is the container for the form itself. If not empty, this element generates a form. Moreover, the Form Class is the link between a single data class of the loaded Data Model and the customized form.
 * Operations: 
 * -	The operation 'Load Ressources...' enables to select and import Data Models in the Form Model. One of the first steps to modelize form is to connect this form diagram with the data model through this operation: Right click on the editor and select 'Load Resource' to choose a data model (with suffix .dt and not a '.dtdi' data diagram). The Real Class attribute of Form Class may then be set up by selecting the name of the targeted data class of the loaded data model. 
 * -	The operation 'Initialize form class' creates the default form from a selected Data Class. The selected Data Class is set up with the attribute 'Real class'. This action creates all fields corresponding to the Attributes and Associations defined in the data model: this default form may then be refined according to the needs.
 * -	The operation ‘Refresh Outline’ refreshes an outline html view of the form. This view is useful to see the transformations made on the form before generating it and to have a general view of what will be generated. It is important to note that this outline view does not give the final generated form and the shape may be different according to the targeted technology.
 * -	The operation ‘Restore’ allows restoring a field which has been previously deleted.
 * Inherits: ClassReference, FormContainer.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.FormClass#isContent_enabled <em>Content enabled</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getFormClass()
 * @model
 * @generated
 */
public interface FormClass extends FormContainer, ClassReference {

	/**
	 * Returns the value of the '<em><b>Content enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Content enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content enabled</em>' attribute.
	 * @see #setContent_enabled(boolean)
	 * @see com.bluexml.side.form.FormPackage#getFormClass_Content_enabled()
	 * @model
	 * @generated
	 */
	boolean isContent_enabled();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.FormClass#isContent_enabled <em>Content enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content enabled</em>' attribute.
	 * @see #isContent_enabled()
	 * @generated
	 */
	void setContent_enabled(boolean value);
} // FormClass
