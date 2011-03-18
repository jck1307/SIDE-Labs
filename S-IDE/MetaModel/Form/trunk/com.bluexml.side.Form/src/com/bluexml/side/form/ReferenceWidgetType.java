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
 * A representation of the literals of the enumeration '<em><b>Reference Widget Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Definition: The 'ReferenceWidgetType'  is an enumeration list with the different layout for a model choice field.
 * <!-- end-model-doc -->
 * @see com.bluexml.side.form.FormPackage#getReferenceWidgetType()
 * @model
 * @generated
 */
public enum ReferenceWidgetType implements Enumerator {
	/**
	 * The '<em><b>Association Class Inline</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ASSOCIATION_CLASS_INLINE_VALUE
	 * @generated
	 * @ordered
	 */
	ASSOCIATION_CLASS_INLINE(0, "AssociationClassInline", "Association Class Inline"),

	/**
	 * The '<em><b>Association Class Select</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ASSOCIATION_CLASS_SELECT_VALUE
	 * @generated
	 * @ordered
	 */
	ASSOCIATION_CLASS_SELECT(1, "AssociationClassSelect", "Association Class Select"), /**
	 * The '<em><b>Inline</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INLINE_VALUE
	 * @generated
	 * @ordered
	 */
	INLINE(2, "Inline", "Inline");

	/**
	 * The '<em><b>Association Class Inline</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Association Class Inline</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'AssociationClassInline' option provides for association class a form that will be incluse in the parent form.
	 * <!-- end-model-doc -->
	 * @see #ASSOCIATION_CLASS_INLINE
	 * @model name="AssociationClassInline" literal="Association Class Inline"
	 * @generated
	 * @ordered
	 */
	public static final int ASSOCIATION_CLASS_INLINE_VALUE = 0;

	/**
	 * The '<em><b>Association Class Select</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Association Class Select</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'AssociationClassSelect' option provides a selection composant that allows to choose existent object of the association class.
	 * <!-- end-model-doc -->
	 * @see #ASSOCIATION_CLASS_SELECT
	 * @model name="AssociationClassSelect" literal="Association Class Select"
	 * @generated
	 * @ordered
	 */
	public static final int ASSOCIATION_CLASS_SELECT_VALUE = 1;

	/**
	 * The '<em><b>Inline</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Inline</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'Inline' option creates a form and include it in the parent form.
	 * <!-- end-model-doc -->
	 * @see #INLINE
	 * @model name="Inline"
	 * @generated
	 * @ordered
	 */
	public static final int INLINE_VALUE = 2;

	/**
	 * An array of all the '<em><b>Reference Widget Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ReferenceWidgetType[] VALUES_ARRAY =
		new ReferenceWidgetType[] {
			ASSOCIATION_CLASS_INLINE,
			ASSOCIATION_CLASS_SELECT,
			INLINE,
		};

	/**
	 * A public read-only list of all the '<em><b>Reference Widget Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ReferenceWidgetType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Reference Widget Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReferenceWidgetType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ReferenceWidgetType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Reference Widget Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReferenceWidgetType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ReferenceWidgetType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Reference Widget Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReferenceWidgetType get(int value) {
		switch (value) {
			case ASSOCIATION_CLASS_INLINE_VALUE: return ASSOCIATION_CLASS_INLINE;
			case ASSOCIATION_CLASS_SELECT_VALUE: return ASSOCIATION_CLASS_SELECT;
			case INLINE_VALUE: return INLINE;
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
	private ReferenceWidgetType(int value, String name, String literal) {
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
	
} //ReferenceWidgetType
