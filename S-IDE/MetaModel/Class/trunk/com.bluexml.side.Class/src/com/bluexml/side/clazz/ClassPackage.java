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
package com.bluexml.side.clazz;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: a package allows to organize class/data; this is similar to Java package structure.
 * Constraints: the fully qualified name of a class is compose of the successive names of the containing package and the attribute name of the class. This fully qualified name must be unique in your application. For instance, 'org.bluexml.library.mediaVideo' identifies the class mediaVideo contains in the 'library' package, itself contains in the 'bluexml' package, itself contains in the 'org' package.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.ClassPackage#getClassSet <em>Class Set</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.ClassPackage#getAssociationSet <em>Association Set</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.ClassPackage#getAspectSet <em>Aspect Set</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.ClassPackage#getEnumerationSet <em>Enumeration Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getClassPackage()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='PackageNameNull'"
 *        annotation="http://www.bluexml.com/OCL PackageNameNull='not self.name.oclIsUndefined() and self.name <> \'\''"
 * @generated
 */
public interface ClassPackage extends com.bluexml.side.common.Package {
	/**
	 * Returns the value of the '<em><b>Class Set</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.Clazz}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Set</em>' containment reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getClassPackage_ClassSet()
	 * @model containment="true"
	 * @generated
	 */
	EList<Clazz> getClassSet();

	/**
	 * Returns the value of the '<em><b>Association Set</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.Association}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association Set</em>' containment reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getClassPackage_AssociationSet()
	 * @model containment="true"
	 * @generated
	 */
	EList<Association> getAssociationSet();

	/**
	 * Returns the value of the '<em><b>Aspect Set</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.Aspect}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aspect Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aspect Set</em>' containment reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getClassPackage_AspectSet()
	 * @model containment="true"
	 * @generated
	 */
	EList<Aspect> getAspectSet();

	/**
	 * Returns the value of the '<em><b>Enumeration Set</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.Enumeration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enumeration Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enumeration Set</em>' containment reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getClassPackage_EnumerationSet()
	 * @model containment="true"
	 * @generated
	 */
	EList<Enumeration> getEnumerationSet();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='ClassPackage.allInstances()'"
	 * @generated
	 */
	EList<ClassPackage> getAllPackages();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='Clazz.allInstances()'"
	 * @generated
	 */
	EList<Clazz> getAllClasses();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='Enumeration.allInstances()'"
	 * @generated
	 */
	EList<Enumeration> getAllEnumerations();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='Aspect.allInstances()'"
	 * @generated
	 */
	EList<Aspect> getAllAspects();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='Association.allInstances()'"
	 * @generated
	 */
	EList<Association> getAllAssociations();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='AbstractClass.allInstances()'"
	 * @generated
	 */
	EList<AbstractClass> getAllAbstractClasses();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='Clazz.allInstances()  ->  asSet()  -> iterate(e:Clazz;result :Set(Clazz)= Set{}| result -> including(e) -> union(e.getInheritedClasses()))'"
	 * @generated
	 */
	EList<Clazz> getAllClassesFromEveryWhere();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.getAllClassesFromEveryWhere().aspects -> asSet()'"
	 * @generated
	 */
	EList<Aspect> getAllAspectsFromEveryWhere();
		
} // ClassPackage
