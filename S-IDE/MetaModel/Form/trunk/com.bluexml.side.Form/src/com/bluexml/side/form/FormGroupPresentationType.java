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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Form Group Presentation Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Definition: The 'FormGroupPresentationType' is an enumeration list with the different layout for a form group.
 * <!-- end-model-doc -->
 * @see com.bluexml.side.form.FormPackage#getFormGroupPresentationType()
 * @model
 * @generated
 */
public enum FormGroupPresentationType implements Enumerator {
	/**
	 * The '<em><b>Auto</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AUTO_VALUE
	 * @generated
	 * @ordered
	 */
	AUTO(0, "auto", "auto"),

	/**
	 * The '<em><b>Horizontal</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HORIZONTAL_VALUE
	 * @generated
	 * @ordered
	 */
	HORIZONTAL(1, "horizontal", "horizontal"),

	/**
	 * The '<em><b>Vertical</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VERTICAL_VALUE
	 * @generated
	 * @ordered
	 */
	VERTICAL(2, "vertical", "vertical"), /**
	 * The '<em><b>Tabbed</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TABBED_VALUE
	 * @generated
	 * @ordered
	 */
	TABBED(3, "tabbed", "tabbed"), /**
	 * The '<em><b>Borderless</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BORDERLESS_VALUE
	 * @generated
	 * @ordered
	 */
	BORDERLESS(4, "borderless", "borderless");

	/**
	 * The '<em><b>Auto</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Auto</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'auto' option displays elements in an horizontal way with a border.
	 * <!-- end-model-doc -->
	 * @see #AUTO
	 * @model name="auto"
	 * @generated
	 * @ordered
	 */
	public static final int AUTO_VALUE = 0;

	/**
	 * The '<em><b>Horizontal</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Horizontal</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'horizontal' option displays elements in an horizontal way. The label of the form group will not appear.
	 * <!-- end-model-doc -->
	 * @see #HORIZONTAL
	 * @model name="horizontal"
	 * @generated
	 * @ordered
	 */
	public static final int HORIZONTAL_VALUE = 1;

	/**
	 * The '<em><b>Vertical</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Vertical</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'vertical' value displays elements in a vertical way. The label of the form group will not appear.
	 * <!-- end-model-doc -->
	 * @see #VERTICAL
	 * @model name="vertical"
	 * @generated
	 * @ordered
	 */
	public static final int VERTICAL_VALUE = 2;

	/**
	 * The '<em><b>Tabbed</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Tabbed</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'Tabbed' option displays elements in a tab.
	 * <!-- end-model-doc -->
	 * @see #TABBED
	 * @model name="tabbed"
	 * @generated
	 * @ordered
	 */
	public static final int TABBED_VALUE = 3;

	/**
	 * The '<em><b>Borderless</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Borderless</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'bordeless' option displays elements border. The label of the form group will not appear.
	 * <!-- end-model-doc -->
	 * @see #BORDERLESS
	 * @model name="borderless"
	 * @generated
	 * @ordered
	 */
	public static final int BORDERLESS_VALUE = 4;

	/**
	 * An array of all the '<em><b>Group Presentation Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final FormGroupPresentationType[] VALUES_ARRAY =
		new FormGroupPresentationType[] {
			AUTO,
			HORIZONTAL,
			VERTICAL,
			TABBED,
			BORDERLESS,
		};

	/**
	 * A public read-only list of all the '<em><b>Group Presentation Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<FormGroupPresentationType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Group Presentation Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FormGroupPresentationType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			FormGroupPresentationType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Group Presentation Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FormGroupPresentationType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			FormGroupPresentationType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Group Presentation Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FormGroupPresentationType get(int value) {
		switch (value) {
			case AUTO_VALUE: return AUTO;
			case HORIZONTAL_VALUE: return HORIZONTAL;
			case VERTICAL_VALUE: return VERTICAL;
			case TABBED_VALUE: return TABBED;
			case BORDERLESS_VALUE: return BORDERLESS;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private FormGroupPresentationType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //FormGroupPresentationType
