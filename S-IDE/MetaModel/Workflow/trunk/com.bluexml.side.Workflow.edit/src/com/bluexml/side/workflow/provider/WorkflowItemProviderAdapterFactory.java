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
package com.bluexml.side.workflow.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import com.bluexml.side.workflow.util.WorkflowAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class WorkflowItemProviderAdapterFactory extends WorkflowAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkflowItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Process} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessItemProvider processItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Process}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createProcessAdapter() {
		if (processItemProvider == null) {
			processItemProvider = new ProcessItemProvider(this);
		}

		return processItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Swimlane} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SwimlaneItemProvider swimlaneItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Swimlane}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSwimlaneAdapter() {
		if (swimlaneItemProvider == null) {
			swimlaneItemProvider = new SwimlaneItemProvider(this);
		}

		return swimlaneItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.StartState} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StartStateItemProvider startStateItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.StartState}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createStartStateAdapter() {
		if (startStateItemProvider == null) {
			startStateItemProvider = new StartStateItemProvider(this);
		}

		return startStateItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.EndState} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EndStateItemProvider endStateItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.EndState}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEndStateAdapter() {
		if (endStateItemProvider == null) {
			endStateItemProvider = new EndStateItemProvider(this);
		}

		return endStateItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Node} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeItemProvider nodeItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Node}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNodeAdapter() {
		if (nodeItemProvider == null) {
			nodeItemProvider = new NodeItemProvider(this);
		}

		return nodeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.TaskNode} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TaskNodeItemProvider taskNodeItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.TaskNode}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTaskNodeAdapter() {
		if (taskNodeItemProvider == null) {
			taskNodeItemProvider = new TaskNodeItemProvider(this);
		}

		return taskNodeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.UserTask} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserTaskItemProvider userTaskItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.UserTask}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUserTaskAdapter() {
		if (userTaskItemProvider == null) {
			userTaskItemProvider = new UserTaskItemProvider(this);
		}

		return userTaskItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.ProcessState} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessStateItemProvider processStateItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.ProcessState}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createProcessStateAdapter() {
		if (processStateItemProvider == null) {
			processStateItemProvider = new ProcessStateItemProvider(this);
		}

		return processStateItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Fork} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ForkItemProvider forkItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Fork}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createForkAdapter() {
		if (forkItemProvider == null) {
			forkItemProvider = new ForkItemProvider(this);
		}

		return forkItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Join} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JoinItemProvider joinItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Join}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createJoinAdapter() {
		if (joinItemProvider == null) {
			joinItemProvider = new JoinItemProvider(this);
		}

		return joinItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Decision} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DecisionItemProvider decisionItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Decision}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDecisionAdapter() {
		if (decisionItemProvider == null) {
			decisionItemProvider = new DecisionItemProvider(this);
		}

		return decisionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Event} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventItemProvider eventItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Event}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEventAdapter() {
		if (eventItemProvider == null) {
			eventItemProvider = new EventItemProvider(this);
		}

		return eventItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Action} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActionItemProvider actionItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Action}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createActionAdapter() {
		if (actionItemProvider == null) {
			actionItemProvider = new ActionItemProvider(this);
		}

		return actionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Script} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScriptItemProvider scriptItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Script}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createScriptAdapter() {
		if (scriptItemProvider == null) {
			scriptItemProvider = new ScriptItemProvider(this);
		}

		return scriptItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Timer} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TimerItemProvider timerItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Timer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTimerAdapter() {
		if (timerItemProvider == null) {
			timerItemProvider = new TimerItemProvider(this);
		}

		return timerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Variable} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariableItemProvider variableItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Variable}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createVariableAdapter() {
		if (variableItemProvider == null) {
			variableItemProvider = new VariableItemProvider(this);
		}

		return variableItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Transition} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransitionItemProvider transitionItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Transition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTransitionAdapter() {
		if (transitionItemProvider == null) {
			transitionItemProvider = new TransitionItemProvider(this);
		}

		return transitionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Attribute} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttributeItemProvider attributeItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Attribute}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAttributeAdapter() {
		if (attributeItemProvider == null) {
			attributeItemProvider = new AttributeItemProvider(this);
		}

		return attributeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.TransitionTask} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransitionTaskItemProvider transitionTaskItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.TransitionTask}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTransitionTaskAdapter() {
		if (transitionTaskItemProvider == null) {
			transitionTaskItemProvider = new TransitionTaskItemProvider(this);
		}

		return transitionTaskItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.WfPackage} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WfPackageItemProvider wfPackageItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.WfPackage}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createWfPackageAdapter() {
		if (wfPackageItemProvider == null) {
			wfPackageItemProvider = new WfPackageItemProvider(this);
		}

		return wfPackageItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (processItemProvider != null) processItemProvider.dispose();
		if (swimlaneItemProvider != null) swimlaneItemProvider.dispose();
		if (startStateItemProvider != null) startStateItemProvider.dispose();
		if (taskNodeItemProvider != null) taskNodeItemProvider.dispose();
		if (userTaskItemProvider != null) userTaskItemProvider.dispose();
		if (endStateItemProvider != null) endStateItemProvider.dispose();
		if (nodeItemProvider != null) nodeItemProvider.dispose();
		if (processStateItemProvider != null) processStateItemProvider.dispose();
		if (forkItemProvider != null) forkItemProvider.dispose();
		if (joinItemProvider != null) joinItemProvider.dispose();
		if (decisionItemProvider != null) decisionItemProvider.dispose();
		if (eventItemProvider != null) eventItemProvider.dispose();
		if (actionItemProvider != null) actionItemProvider.dispose();
		if (scriptItemProvider != null) scriptItemProvider.dispose();
		if (timerItemProvider != null) timerItemProvider.dispose();
		if (variableItemProvider != null) variableItemProvider.dispose();
		if (transitionItemProvider != null) transitionItemProvider.dispose();
		if (attributeItemProvider != null) attributeItemProvider.dispose();
		if (transitionTaskItemProvider != null) transitionTaskItemProvider.dispose();
		if (wfPackageItemProvider != null) wfPackageItemProvider.dispose();
	}

}