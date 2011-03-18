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


/*******************************************************************************
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package com.bluexml.side.Workflow.modeler.diagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.DiagramElement;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Workflow.modeler.diagram.WfConfiguration;
import com.bluexml.side.Workflow.modeler.diagram.WfEditPolicyConstants;
import com.bluexml.side.Workflow.modeler.diagram.commands.TaskNodeRestoreConnectionCommand;
import com.bluexml.side.Workflow.modeler.diagram.figures.TaskNodeFigure;
import com.bluexml.side.Workflow.modeler.diagram.policies.TransitionEdgeCreationEditPolicy;
import com.bluexml.side.Workflow.modeler.diagram.policies.isAssociatedWithEdgeCreationEditPolicy;
import com.bluexml.side.Workflow.modeler.diagram.policies.manageEdgeCreationEditPolicy;
import com.bluexml.side.Workflow.modeler.diagram.preferences.WfDiagramPreferenceConstants;
import com.bluexml.side.workflow.TaskNode;

/**
 * The TaskNode object controller
 *
 * @generated
 */
public class TaskNodeEditPart extends EMFGraphNodeEditPart {
	/**
	 * Constructor
	 *
	 * @param obj the graph node
	 * @generated
	 */
	public TaskNodeEditPart(GraphNode obj) {
		super(obj);
	}

	/**
	 * Creates edit policies and associates these with roles
	 *
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(WfEditPolicyConstants.TRANSITION_EDITPOLICY,
				new TransitionEdgeCreationEditPolicy());

		installEditPolicy(WfEditPolicyConstants.MANAGE_EDITPOLICY,
				new manageEdgeCreationEditPolicy());

		installEditPolicy(WfEditPolicyConstants.ISASSOCIATEDWITH_EDITPOLICY,
				new isAssociatedWithEdgeCreationEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY,
				new RestoreEditPolicy() {
					protected Command getRestoreConnectionsCommand(
							RestoreConnectionsRequest request) {
						return new TaskNodeRestoreConnectionCommand(getHost());
					}
				});

		installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY,
				new ResizableEditPolicy());

		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new LabelDirectEditPolicy());
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 * @_generated
	 */
	protected IFigure createFigure() {
		TaskNode state = (TaskNode) Utils.getElement(getGraphNode());
		WfConfiguration config = new WfConfiguration();

		if (getGraphNode().getContained().size() > 0) {
			GraphNode attributesListNode = (GraphNode) getGraphNode()
					.getContained().get(0);
			EList<DiagramElement> attributesList = attributesListNode
					.getContained();
			while (attributesList.size() > 0)
				attributesList.remove(0);
			for (Object o : state.getAttributes()) {
				GraphElement elt = config.getCreationUtils()
						.createGraphElement((EObject) o);
				attributesList.add(elt);
			}
		}

		if (getGraphNode().getContained().size() > 1) {
			GraphNode eventsListNode = (GraphNode) getGraphNode()
					.getContained().get(1);
			EList<DiagramElement> eventsList = eventsListNode.getContained();
			while (eventsList.size() > 0)
				eventsList.remove(0);
			for (Object o : state.getEvent()) {
				GraphElement elt = config.getCreationUtils()
						.createGraphElement((EObject) o);
				eventsList.add(elt);
			}
		}
		return new TaskNodeFigure();
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultBackgroundColor() {
		String backgroundColor = getPreferenceStore().getString(
				WfDiagramPreferenceConstants.TASKNODE_DEFAULT_BACKGROUND_COLOR);
		if (backgroundColor.length() != 0) {
			return Utils.getColor(backgroundColor);
		}
		return null;
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultForegroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultForegroundColor() {
		String foregroundColor = getPreferenceStore().getString(
				WfDiagramPreferenceConstants.TASKNODE_DEFAULT_FOREGROUND_COLOR);
		if (foregroundColor.length() != 0) {
			return Utils.getColor(foregroundColor);
		}
		return null;
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultFont()
	 * @generated
	 */
	protected Font getPreferenceDefaultFont() {
		String preferenceFont = getPreferenceStore().getString(
				WfDiagramPreferenceConstants.TASKNODE_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;

	}

}
