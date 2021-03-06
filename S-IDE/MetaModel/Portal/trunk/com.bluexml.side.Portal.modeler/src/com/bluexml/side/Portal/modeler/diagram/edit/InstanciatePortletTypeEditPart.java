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
package com.bluexml.side.Portal.modeler.diagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Portal.modeler.PortalPlugin;
import com.bluexml.side.Portal.modeler.diagram.commands.update.InstanciatePortletTypeUpdateCommand;
import com.bluexml.side.Portal.modeler.diagram.dialogs.InstanciatePortletTypeEditDialog;
import com.bluexml.side.Portal.modeler.diagram.figures.InstanciatePortletTypeFigure;
import com.bluexml.side.Portal.modeler.diagram.preferences.PdDiagramPreferenceConstants;
import com.bluexml.side.portal.InstanciatePortletType;

/**
 * InstanciatePortletType controller
 *
 * @generated
 */
public class InstanciatePortletTypeEditPart extends EMFGraphEdgeEditPart {

	/**
	 * Constructor
	 *
	 * @param model the graph object
	 * @generated
	 */
	public InstanciatePortletTypeEditPart(GraphEdge model) {
		super(model);
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(ModelerEditPolicyConstants.CHANGE_FONT_EDITPOLICY, null);

	}

	/**
	 * @return the Figure
	 * @generated
	 */
	protected IFigure createFigure() {
		InstanciatePortletTypeFigure connection = new InstanciatePortletTypeFigure();

		return connection;
	}

	/**
	 * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultRouter()
	 * 
	 * @generated
	 */
	protected String getPreferenceDefaultRouter() {
		return getPreferenceStore().getString(PdDiagramPreferenceConstants.INSTANCIATEPORTLETTYPE_EDGE_DEFAULT_ROUTER);
	}

	/**
	 * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultForegroundColor()
	 * 
	 * @generated
	 */
	protected Color getPreferenceDefaultForegroundColor() {
		String preferenceForeground = getPreferenceStore().getString(PdDiagramPreferenceConstants.INSTANCIATEPORTLETTYPE_EDGE_DEFAULT_FOREGROUND_COLOR);
		if (preferenceForeground.length() != 0) {
			return Utils.getColor(preferenceForeground);
		}
		return null;

	}

	/**
	 * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultFont()
	 * 
	 * @generated
	 */
	protected Font getPreferenceDefaultFont() {
		String preferenceFont = getPreferenceStore().getString(PdDiagramPreferenceConstants.INSTANCIATEPORTLETTYPE_EDGE_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;
	}

	public void performRequest(Request request) {
		InstanciatePortletType instances = (InstanciatePortletType) Utils.getElement(getGraphEdge());

		if (request.getType() == RequestConstants.REQ_OPEN) {

			InstanciatePortletTypeEditDialog dlg = new InstanciatePortletTypeEditDialog(instances, PortalPlugin.getActiveWorkbenchShell());
			if (dlg.open() == Window.OK) {
				InstanciatePortletTypeUpdateCommand command = new InstanciatePortletTypeUpdateCommand(instances, dlg.getData());
				getViewer().getEditDomain().getCommandStack().execute(command);
				refreshEdgeObjects();
			}
		} else {
			super.performRequest(request);
		}
	}
}
