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
package com.bluexml.side.Requirements.modeler.goalDiagram.edit;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.edit.GraphEdgeEditPart;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Requirements.modeler.RequirementsPlugin;
import com.bluexml.side.Requirements.modeler.goalDiagram.edit.decoration.ImageDecoration;
import com.bluexml.side.Requirements.modeler.goalDiagram.figures.is_responsibleFigure;
import com.bluexml.side.Requirements.modeler.goalDiagram.preferences.ReqDiagramPreferenceConstants;

/**
 * is_responsible controller
 * 
 * @generated
 */
public class is_responsibleEditPart extends GraphEdgeEditPart {

	/**
	 * Constructor
	 * 
	 * @param model
	 *            the graph object
	 * @generated
	 */
	public is_responsibleEditPart(GraphEdge model) {
		super(model);
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(ModelerEditPolicyConstants.CHANGE_FONT_EDITPOLICY,
				null);

		installEditPolicy(
				ModelerEditPolicyConstants.CHANGE_FOREGROUND_COLOR_EDITPOLICY,
				null);
	}

	/**
	 * @return the Figure
	 * @generated
	 */
	protected IFigure createFigure() {
		is_responsibleFigure connection = new is_responsibleFigure();

		createTargetDecoration(connection);

		return connection;
	}

	/**
	 * @param connection
	 *            the PolylineConnection
	 * @generated
	 */
	private void createTargetDecoration(PolylineConnection connection) {

		PolylineDecoration decoration = new PolylineDecoration();
		decoration.setScale(10, 5);
		connection.setTargetDecoration(decoration);

	}

	/**
	 * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultRouter()
	 * 
	 * @generated
	 */
	protected String getPreferenceDefaultRouter() {
		return getPreferenceStore()
				.getString(
						ReqDiagramPreferenceConstants.IS_RESPONSIBLE_EDGE_DEFAULT_ROUTER);
	}

	/**
	 * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultForegroundColor()
	 * 
	 * @generated
	 */
	protected Color getPreferenceDefaultForegroundColor() {
		String preferenceForeground = getPreferenceStore()
				.getString(
						ReqDiagramPreferenceConstants.IS_RESPONSIBLE_EDGE_DEFAULT_FOREGROUND_COLOR);
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
		String preferenceFont = getPreferenceStore().getString(
				ReqDiagramPreferenceConstants.IS_RESPONSIBLE_EDGE_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;
	}

	@Override
	protected void refreshEdgeObjects() {
		super.refreshEdgeObjects();

		URL url = null;
		Image image;
		try {
			url = new URL(RequirementsPlugin.getDefault().getDescriptor()
					.getInstallURL(), "icons/ResponsibilityDecoration.png");
			image = ImageDescriptor.createFromURL(url).createImage();
			((PolylineConnection) figure)
					.setTargetDecoration(new ImageDecoration(image));
		} catch (MalformedURLException e) {
		}

	}
}
