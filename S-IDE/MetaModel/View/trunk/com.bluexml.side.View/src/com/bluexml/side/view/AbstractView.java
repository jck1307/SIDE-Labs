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

import com.bluexml.side.common.Container;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.common.OperationComponent;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract View</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.bluexml.side.view.ViewPackage#getAbstractView()
 * @model abstract="true"
 *        annotation="http://www.bluexml.com/OCL noSameName='if (self.getContainer().oclIsTypeOf(ViewCollection)) then\r\tViewCollection.allInstances().views ->union(ViewCollection.allInstances().composedViews) -> select( t : AbstractView | self.name = t.name) -> size() = 1\relse\r\tself.getContainer().oclAsType(FieldContainer).children -> select( t : FieldElement | self.name = t.name) -> size() = 1\rendif'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='noSameName'"
 * @generated
 */
public interface AbstractView extends FieldContainer {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL description='Get all the fields of the AbstractView, excluding the FieldContainers' body='if (self.oclIsKindOf(AbstractDataTable)) then\r\tself.oclAsType(AbstractDataTable).getCols()->children->select(oclIsKindOf(Field))->asSequence()->union(self.getDirectChildFields()->asSequence()).oclAsType(Field)  \relse self.getDirectChildFields().oclAsType(Field) endif'"
	 * @generated
	 */
	EList<Field> getFields();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL description='Get all the fields of the AbstractView, excluding the FieldContainers' body='self.children->select(oclIsKindOf(Field)).oclAsType(Field) ->asSequence()'"
	 * @generated
	 */
	EList<Field> getDirectChildFields();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL description='Get inner AbtractView of the AbstractView' body='self.children->select(oclIsKindOf(AbstractView))'"
	 * @generated
	 */
	EList<AbstractView> getInnerView();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL description='Get all the fields of the AbstractView, including disabled Field, excluding the FieldContainers' body='self.getFields()->union(self.getDisabledFields())'"
	 * @generated
	 */
	EList<Field> getDisabledAndEnabledField();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL description='Get all the disabled Fields' body='if (self.oclIsKindOf(AbstractDataTable)) then self.oclAsType(AbstractDataTable).disabled->select(oclIsKindOf(Col)).oclAsType(Col).children->select(oclIsKindOf(Field))->asSet()->union(self.disabled->select(oclIsKindOf(Field))).oclAsType(Field) else self.disabled->select(oclIsKindOf(Field)).oclAsType(Field) endif'"
	 * @generated
	 */
	EList<Field> getDisabledFields();
		
} // AbstractView
