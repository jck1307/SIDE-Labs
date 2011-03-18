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
package com.bluexml.side.workflow.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.common.impl.PackageImpl;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.workflow.Decision;
import com.bluexml.side.workflow.EndState;
import com.bluexml.side.workflow.Fork;
import com.bluexml.side.workflow.Join;
import com.bluexml.side.workflow.Node;
import com.bluexml.side.workflow.ProcessState;
import com.bluexml.side.workflow.StartState;
import com.bluexml.side.workflow.Swimlane;
import com.bluexml.side.workflow.TaskNode;
import com.bluexml.side.workflow.WorkflowModelElement;
import com.bluexml.side.workflow.WorkflowPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.impl.ProcessImpl#getSwimlane <em>Swimlane</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.ProcessImpl#getStartstate <em>Startstate</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.ProcessImpl#getEndstate <em>Endstate</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.ProcessImpl#getNode <em>Node</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.ProcessImpl#getTasknode <em>Tasknode</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.ProcessImpl#getProcessstate <em>Processstate</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.ProcessImpl#getFork <em>Fork</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.ProcessImpl#getJoin <em>Join</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.ProcessImpl#getDecision <em>Decision</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.ProcessImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.ProcessImpl#getContentType <em>Content Type</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.ProcessImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.impl.ProcessImpl#getProcessDescription <em>Process Description</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessImpl extends PackageImpl implements com.bluexml.side.workflow.Process {
	/**
	 * The cached value of the '{@link #getSwimlane() <em>Swimlane</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwimlane()
	 * @generated
	 * @ordered
	 */
	protected EList<Swimlane> swimlane;

	/**
	 * The cached value of the '{@link #getStartstate() <em>Startstate</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartstate()
	 * @generated
	 * @ordered
	 */
	protected StartState startstate;

	/**
	 * The cached value of the '{@link #getEndstate() <em>Endstate</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndstate()
	 * @generated
	 * @ordered
	 */
	protected EList<EndState> endstate;

	/**
	 * The cached value of the '{@link #getNode() <em>Node</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNode()
	 * @generated
	 * @ordered
	 */
	protected EList<Node> node;

	/**
	 * The cached value of the '{@link #getTasknode() <em>Tasknode</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTasknode()
	 * @generated
	 * @ordered
	 */
	protected EList<TaskNode> tasknode;

	/**
	 * The cached value of the '{@link #getProcessstate() <em>Processstate</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessstate()
	 * @generated
	 * @ordered
	 */
	protected EList<ProcessState> processstate;

	/**
	 * The cached value of the '{@link #getFork() <em>Fork</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFork()
	 * @generated
	 * @ordered
	 */
	protected EList<Fork> fork;

	/**
	 * The cached value of the '{@link #getJoin() <em>Join</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJoin()
	 * @generated
	 * @ordered
	 */
	protected EList<Join> join;

	/**
	 * The cached value of the '{@link #getDecision() <em>Decision</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDecision()
	 * @generated
	 * @ordered
	 */
	protected EList<Decision> decision;

	/**
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkflowModelElement> elements;

	/**
	 * The cached value of the '{@link #getContentType() <em>Content Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContentType()
	 * @generated
	 * @ordered
	 */
	protected Clazz contentType;

	/**
	 * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String TITLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected String title = TITLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getProcessDescription() <em>Process Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String PROCESS_DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProcessDescription() <em>Process Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessDescription()
	 * @generated
	 * @ordered
	 */
	protected String processDescription = PROCESS_DESCRIPTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WorkflowPackage.Literals.PROCESS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Swimlane> getSwimlane() {
		if (swimlane == null) {
			swimlane = new EObjectContainmentEList<Swimlane>(Swimlane.class, this, WorkflowPackage.PROCESS__SWIMLANE);
		}
		return swimlane;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartState getStartstate() {
		return startstate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStartstate(StartState newStartstate, NotificationChain msgs) {
		StartState oldStartstate = startstate;
		startstate = newStartstate;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WorkflowPackage.PROCESS__STARTSTATE, oldStartstate, newStartstate);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartstate(StartState newStartstate) {
		if (newStartstate != startstate) {
			NotificationChain msgs = null;
			if (startstate != null)
				msgs = ((InternalEObject)startstate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WorkflowPackage.PROCESS__STARTSTATE, null, msgs);
			if (newStartstate != null)
				msgs = ((InternalEObject)newStartstate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WorkflowPackage.PROCESS__STARTSTATE, null, msgs);
			msgs = basicSetStartstate(newStartstate, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.PROCESS__STARTSTATE, newStartstate, newStartstate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EndState> getEndstate() {
		if (endstate == null) {
			endstate = new EObjectContainmentEList<EndState>(EndState.class, this, WorkflowPackage.PROCESS__ENDSTATE);
		}
		return endstate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Node> getNode() {
		if (node == null) {
			node = new EObjectContainmentEList<Node>(Node.class, this, WorkflowPackage.PROCESS__NODE);
		}
		return node;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TaskNode> getTasknode() {
		if (tasknode == null) {
			tasknode = new EObjectContainmentEList<TaskNode>(TaskNode.class, this, WorkflowPackage.PROCESS__TASKNODE);
		}
		return tasknode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProcessState> getProcessstate() {
		if (processstate == null) {
			processstate = new EObjectContainmentEList<ProcessState>(ProcessState.class, this, WorkflowPackage.PROCESS__PROCESSSTATE);
		}
		return processstate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Fork> getFork() {
		if (fork == null) {
			fork = new EObjectContainmentEList<Fork>(Fork.class, this, WorkflowPackage.PROCESS__FORK);
		}
		return fork;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Join> getJoin() {
		if (join == null) {
			join = new EObjectContainmentEList<Join>(Join.class, this, WorkflowPackage.PROCESS__JOIN);
		}
		return join;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Decision> getDecision() {
		if (decision == null) {
			decision = new EObjectContainmentEList<Decision>(Decision.class, this, WorkflowPackage.PROCESS__DECISION);
		}
		return decision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WorkflowModelElement> getElements() {
		if (elements == null) {
			elements = new EObjectContainmentEList<WorkflowModelElement>(WorkflowModelElement.class, this, WorkflowPackage.PROCESS__ELEMENTS);
		}
		return elements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Clazz getContentType() {
		if (contentType != null && contentType.eIsProxy()) {
			InternalEObject oldContentType = (InternalEObject)contentType;
			contentType = (Clazz)eResolveProxy(oldContentType);
			if (contentType != oldContentType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WorkflowPackage.PROCESS__CONTENT_TYPE, oldContentType, contentType));
			}
		}
		return contentType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Clazz basicGetContentType() {
		return contentType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContentType(Clazz newContentType) {
		Clazz oldContentType = contentType;
		contentType = newContentType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.PROCESS__CONTENT_TYPE, oldContentType, contentType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTitle(String newTitle) {
		String oldTitle = title;
		title = newTitle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.PROCESS__TITLE, oldTitle, title));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProcessDescription() {
		return processDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcessDescription(String newProcessDescription) {
		String oldProcessDescription = processDescription;
		processDescription = newProcessDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.PROCESS__PROCESS_DESCRIPTION, oldProcessDescription, processDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WorkflowPackage.PROCESS__SWIMLANE:
				return ((InternalEList<?>)getSwimlane()).basicRemove(otherEnd, msgs);
			case WorkflowPackage.PROCESS__STARTSTATE:
				return basicSetStartstate(null, msgs);
			case WorkflowPackage.PROCESS__ENDSTATE:
				return ((InternalEList<?>)getEndstate()).basicRemove(otherEnd, msgs);
			case WorkflowPackage.PROCESS__NODE:
				return ((InternalEList<?>)getNode()).basicRemove(otherEnd, msgs);
			case WorkflowPackage.PROCESS__TASKNODE:
				return ((InternalEList<?>)getTasknode()).basicRemove(otherEnd, msgs);
			case WorkflowPackage.PROCESS__PROCESSSTATE:
				return ((InternalEList<?>)getProcessstate()).basicRemove(otherEnd, msgs);
			case WorkflowPackage.PROCESS__FORK:
				return ((InternalEList<?>)getFork()).basicRemove(otherEnd, msgs);
			case WorkflowPackage.PROCESS__JOIN:
				return ((InternalEList<?>)getJoin()).basicRemove(otherEnd, msgs);
			case WorkflowPackage.PROCESS__DECISION:
				return ((InternalEList<?>)getDecision()).basicRemove(otherEnd, msgs);
			case WorkflowPackage.PROCESS__ELEMENTS:
				return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WorkflowPackage.PROCESS__SWIMLANE:
				return getSwimlane();
			case WorkflowPackage.PROCESS__STARTSTATE:
				return getStartstate();
			case WorkflowPackage.PROCESS__ENDSTATE:
				return getEndstate();
			case WorkflowPackage.PROCESS__NODE:
				return getNode();
			case WorkflowPackage.PROCESS__TASKNODE:
				return getTasknode();
			case WorkflowPackage.PROCESS__PROCESSSTATE:
				return getProcessstate();
			case WorkflowPackage.PROCESS__FORK:
				return getFork();
			case WorkflowPackage.PROCESS__JOIN:
				return getJoin();
			case WorkflowPackage.PROCESS__DECISION:
				return getDecision();
			case WorkflowPackage.PROCESS__ELEMENTS:
				return getElements();
			case WorkflowPackage.PROCESS__CONTENT_TYPE:
				if (resolve) return getContentType();
				return basicGetContentType();
			case WorkflowPackage.PROCESS__TITLE:
				return getTitle();
			case WorkflowPackage.PROCESS__PROCESS_DESCRIPTION:
				return getProcessDescription();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case WorkflowPackage.PROCESS__SWIMLANE:
				getSwimlane().clear();
				getSwimlane().addAll((Collection<? extends Swimlane>)newValue);
				return;
			case WorkflowPackage.PROCESS__STARTSTATE:
				setStartstate((StartState)newValue);
				return;
			case WorkflowPackage.PROCESS__ENDSTATE:
				getEndstate().clear();
				getEndstate().addAll((Collection<? extends EndState>)newValue);
				return;
			case WorkflowPackage.PROCESS__NODE:
				getNode().clear();
				getNode().addAll((Collection<? extends Node>)newValue);
				return;
			case WorkflowPackage.PROCESS__TASKNODE:
				getTasknode().clear();
				getTasknode().addAll((Collection<? extends TaskNode>)newValue);
				return;
			case WorkflowPackage.PROCESS__PROCESSSTATE:
				getProcessstate().clear();
				getProcessstate().addAll((Collection<? extends ProcessState>)newValue);
				return;
			case WorkflowPackage.PROCESS__FORK:
				getFork().clear();
				getFork().addAll((Collection<? extends Fork>)newValue);
				return;
			case WorkflowPackage.PROCESS__JOIN:
				getJoin().clear();
				getJoin().addAll((Collection<? extends Join>)newValue);
				return;
			case WorkflowPackage.PROCESS__DECISION:
				getDecision().clear();
				getDecision().addAll((Collection<? extends Decision>)newValue);
				return;
			case WorkflowPackage.PROCESS__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection<? extends WorkflowModelElement>)newValue);
				return;
			case WorkflowPackage.PROCESS__CONTENT_TYPE:
				setContentType((Clazz)newValue);
				return;
			case WorkflowPackage.PROCESS__TITLE:
				setTitle((String)newValue);
				return;
			case WorkflowPackage.PROCESS__PROCESS_DESCRIPTION:
				setProcessDescription((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case WorkflowPackage.PROCESS__SWIMLANE:
				getSwimlane().clear();
				return;
			case WorkflowPackage.PROCESS__STARTSTATE:
				setStartstate((StartState)null);
				return;
			case WorkflowPackage.PROCESS__ENDSTATE:
				getEndstate().clear();
				return;
			case WorkflowPackage.PROCESS__NODE:
				getNode().clear();
				return;
			case WorkflowPackage.PROCESS__TASKNODE:
				getTasknode().clear();
				return;
			case WorkflowPackage.PROCESS__PROCESSSTATE:
				getProcessstate().clear();
				return;
			case WorkflowPackage.PROCESS__FORK:
				getFork().clear();
				return;
			case WorkflowPackage.PROCESS__JOIN:
				getJoin().clear();
				return;
			case WorkflowPackage.PROCESS__DECISION:
				getDecision().clear();
				return;
			case WorkflowPackage.PROCESS__ELEMENTS:
				getElements().clear();
				return;
			case WorkflowPackage.PROCESS__CONTENT_TYPE:
				setContentType((Clazz)null);
				return;
			case WorkflowPackage.PROCESS__TITLE:
				setTitle(TITLE_EDEFAULT);
				return;
			case WorkflowPackage.PROCESS__PROCESS_DESCRIPTION:
				setProcessDescription(PROCESS_DESCRIPTION_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case WorkflowPackage.PROCESS__SWIMLANE:
				return swimlane != null && !swimlane.isEmpty();
			case WorkflowPackage.PROCESS__STARTSTATE:
				return startstate != null;
			case WorkflowPackage.PROCESS__ENDSTATE:
				return endstate != null && !endstate.isEmpty();
			case WorkflowPackage.PROCESS__NODE:
				return node != null && !node.isEmpty();
			case WorkflowPackage.PROCESS__TASKNODE:
				return tasknode != null && !tasknode.isEmpty();
			case WorkflowPackage.PROCESS__PROCESSSTATE:
				return processstate != null && !processstate.isEmpty();
			case WorkflowPackage.PROCESS__FORK:
				return fork != null && !fork.isEmpty();
			case WorkflowPackage.PROCESS__JOIN:
				return join != null && !join.isEmpty();
			case WorkflowPackage.PROCESS__DECISION:
				return decision != null && !decision.isEmpty();
			case WorkflowPackage.PROCESS__ELEMENTS:
				return elements != null && !elements.isEmpty();
			case WorkflowPackage.PROCESS__CONTENT_TYPE:
				return contentType != null;
			case WorkflowPackage.PROCESS__TITLE:
				return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
			case WorkflowPackage.PROCESS__PROCESS_DESCRIPTION:
				return PROCESS_DESCRIPTION_EDEFAULT == null ? processDescription != null : !PROCESS_DESCRIPTION_EDEFAULT.equals(processDescription);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (title: ");
		result.append(title);
		result.append(", processDescription: ");
		result.append(processDescription);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //ProcessImpl
