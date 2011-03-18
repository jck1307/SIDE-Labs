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
package com.bluexml.side.view.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import com.bluexml.side.common.CommonFactory;
import com.bluexml.side.view.Tree;
import com.bluexml.side.view.ViewFactory;
import com.bluexml.side.view.ViewPackage;

/**
 * This is the item provider adapter for a {@link com.bluexml.side.view.Tree} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TreeItemProvider
	extends AbstractViewOfItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TreeItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addEditablePropertyDescriptor(object);
			addMovablePropertyDescriptor(object);
			addNodeOperationsPropertyDescriptor(object);
			addDefaultDepthPropertyDescriptor(object);
			addMaxDepthPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Editable feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEditablePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Editable_editable_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Editable_editable_feature", "_UI_Editable_type"),
				 ViewPackage.Literals.EDITABLE__EDITABLE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Movable feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMovablePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Movable_movable_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Movable_movable_feature", "_UI_Movable_type"),
				 ViewPackage.Literals.MOVABLE__MOVABLE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Node Operations feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNodeOperationsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Tree_nodeOperations_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Tree_nodeOperations_feature", "_UI_Tree_type"),
				 ViewPackage.Literals.TREE__NODE_OPERATIONS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Default Depth feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDefaultDepthPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Tree_defaultDepth_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Tree_defaultDepth_feature", "_UI_Tree_type"),
				 ViewPackage.Literals.TREE__DEFAULT_DEPTH,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Max Depth feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMaxDepthPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Tree_maxDepth_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Tree_maxDepth_feature", "_UI_Tree_type"),
				 ViewPackage.Literals.TREE__MAX_DEPTH,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ViewPackage.Literals.SORTABLE__SORTING);
			childrenFeatures.add(ViewPackage.Literals.FILTERABLE__FILTERING);
			childrenFeatures.add(ViewPackage.Literals.ACTIONABLE__OPERATIONS);
			childrenFeatures.add(ViewPackage.Literals.TREE__NODE_VALUE);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Tree.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Tree"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @_generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Tree)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Tree_type") :
			label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Tree.class)) {
			case ViewPackage.TREE__EDITABLE:
			case ViewPackage.TREE__MOVABLE:
			case ViewPackage.TREE__DEFAULT_DEPTH:
			case ViewPackage.TREE__MAX_DEPTH:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ViewPackage.TREE__SORTING:
			case ViewPackage.TREE__FILTERING:
			case ViewPackage.TREE__OPERATIONS:
			case ViewPackage.TREE__NODE_VALUE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.SORTABLE__SORTING,
				 ViewFactory.eINSTANCE.createSorting()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FILTERABLE__FILTERING,
				 ViewFactory.eINSTANCE.createFiltering()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.ACTIONABLE__OPERATIONS,
				 CommonFactory.eINSTANCE.createOperation()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.ACTIONABLE__OPERATIONS,
				 CommonFactory.eINSTANCE.createOperationGroup()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createCol()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createDataList()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createDataTable()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createFacetMap()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createTree()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createComposedView()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createTextField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createBooleanField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createPasswordField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createFloatField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createActionField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createDateField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createTimeField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createDateTimeField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createPhoneNumberField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createEmailField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createIntegerField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createFileField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createSelectField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createHtmlField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createURLField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createImageField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.TREE__NODE_VALUE,
				 ViewFactory.eINSTANCE.createFieldGroup()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == ViewPackage.Literals.FIELD_CONTAINER__CHILDREN ||
			childFeature == ViewPackage.Literals.FIELD_CONTAINER__DISABLED ||
			childFeature == ViewPackage.Literals.TREE__NODE_VALUE;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
