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
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram.commands;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.topcased.modeler.commands.AbstractRestoreConnectionCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Requirements.modeler.goalDiagram.ReqSimpleObjectConstants;
import com.bluexml.side.requirements.Agent;
import com.bluexml.side.requirements.Goal;

/**
 * Agent restore connection command
 *
 * @generated
 */
public class AgentRestoreConnectionCommand extends
		AbstractRestoreConnectionCommand {
	/**
	 * @param part the EditPart that is restored
	 * @generated
	 */
	public AgentRestoreConnectionCommand(EditPart part) {
		super(part);
	}

	/**
	 * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
	 * @generated
	 */
	protected void initializeCommands() {

		GraphElement graphElementSrc = getGraphElement();
		EObject eObjectSrc = Utils.getElement(graphElementSrc);

		if (eObjectSrc instanceof Agent) {
			for (GraphElement graphElementTgt : getAllGraphElements()) {
				boolean autoRef = graphElementTgt.equals(graphElementSrc);

				EObject eObjectTgt = Utils.getElement(graphElementTgt);
				if (eObjectTgt instanceof Goal) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createis_responsibleFromAgentToGoal_Responsible(
								graphElementSrc, graphElementTgt);
					}
				}

			}
		}
	}

	/**
	 * @param srcElt the source element
	 * @param targetElt the target element
	 * @generated
	 */
	private void createis_responsibleFromAgentToGoal_Responsible(
			GraphElement srcElt, GraphElement targetElt) {
		Agent sourceObject = (Agent) Utils.getElement(srcElt);
		Goal targetObject = (Goal) Utils.getElement(targetElt);

		if (sourceObject.getIsResponsible().contains(targetObject)
				&& targetObject.getResponsible().contains(sourceObject)) {
			// check if the relation does not exists yet
			if (getExistingEdges(srcElt, targetElt,
					ReqSimpleObjectConstants.SIMPLE_OBJECT_IS_RESPONSIBLE)
					.size() == 0) {
				GraphEdge edge = Utils
						.createGraphEdge(ReqSimpleObjectConstants.SIMPLE_OBJECT_IS_RESPONSIBLE);
				is_responsibleEdgeCreationCommand cmd = new is_responsibleEdgeCreationCommand(
						null, edge, srcElt, false);
				cmd.setTarget(targetElt);
				add(cmd);
			}
		}
	}

}
