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
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.topcased.draw2d.figures.Label;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Requirements.modeler.RequirementsPlugin;
import com.bluexml.side.Requirements.modeler.goalDiagram.commands.PrivilegeRestoreConnectionCommand;
import com.bluexml.side.Requirements.modeler.goalDiagram.figures.PrivilegeFigure;
import com.bluexml.side.Requirements.modeler.goalDiagram.preferences.ReqDiagramPreferenceConstants;
import com.bluexml.side.requirements.Attribute;
import com.bluexml.side.requirements.Entity;
import com.bluexml.side.requirements.Privilege;
import com.bluexml.side.requirements.PrivilegeGroup;
import com.bluexml.side.requirements.PrivilegeNature;
import com.bluexml.side.requirements.RelationShip;

/**
 * The Privilege object controller
 * 
 * @generated
 */
public class PrivilegeEditPart extends EMFGraphNodeEditPart {
	/**
	 * Constructor
	 * 
	 * @param obj
	 *            the graph node
	 * @generated
	 */
	public PrivilegeEditPart(GraphNode obj) {
		super(obj);
	}

	/**
	 * Creates edit policies and associates these with roles
	 * 
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY,
				new RestoreEditPolicy() {
					protected Command getRestoreConnectionsCommand(
							RestoreConnectionsRequest request) {
						return new PrivilegeRestoreConnectionCommand(getHost());
					}
				});

		installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY,
				new ResizableEditPolicy());

		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new LabelDirectEditPolicy());
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 * @generated
	 */
	protected IFigure createFigure() {

		return new PrivilegeFigure();
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultBackgroundColor() {
		String backgroundColor = getPreferenceStore()
				.getString(
						ReqDiagramPreferenceConstants.PRIVILEGE_DEFAULT_BACKGROUND_COLOR);
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
		String foregroundColor = getPreferenceStore()
				.getString(
						ReqDiagramPreferenceConstants.PRIVILEGE_DEFAULT_FOREGROUND_COLOR);
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
				ReqDiagramPreferenceConstants.PRIVILEGE_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;

	}

	@Override
	protected void refreshTextAndFont() {
		super.refreshTextAndFont();
		Privilege p = (Privilege) Utils.getElement(getGraphNode());
		getLabel().setText(createLabel(p));

		if (p.getElement() instanceof Entity) {
			FontData[] fData = Display.getDefault().getSystemFont()
					.getFontData();
			fData[0].setStyle(SWT.BOLD);
			JFaceResources.getFontRegistry().put("font", fData);
			getLabel().setFont(JFaceResources.getFontRegistry().get("font"));
			((Label) getLabel()).setAlignment(Label.LEFT);

			URL url = null;
			Image image;
			try {
				url = new URL(RequirementsPlugin.getDefault().getDescriptor()
						.getInstallURL(), "icons/EntityLittle.png");
				image = ImageDescriptor.createFromURL(url).createImage();
				((Label) getLabel()).setIcon(image);
			} catch (MalformedURLException e) {
			}
		} else {
			FontData[] fData = Display.getDefault().getSystemFont()
					.getFontData();
			fData[0].setStyle(SWT.ITALIC);
			JFaceResources.getFontRegistry().put("font", fData);
			getLabel().setFont(JFaceResources.getFontRegistry().get("font"));
			((Label) getLabel()).setAlignment(Label.LEFT);

			URL url = null;
			Image image;
			try {
				url = new URL(RequirementsPlugin.getDefault().getDescriptor()
						.getInstallURL(), "icons/AttributeLittle.png");
				image = ImageDescriptor.createFromURL(url).createImage();
				((Label) getLabel()).setIcon(image);
			} catch (MalformedURLException e) {
			}
		}
	}

	private String createLabel(Privilege p) {
		String value = "";
		if (p.getElement() instanceof Entity) {
			Entity e = (Entity) p.getElement();
			value = e.getName();
		} else if (p.getElement() instanceof Attribute) {
			Attribute a = (Attribute) p.getElement();
			value = a.getName();
		} else if (p.getElement() instanceof RelationShip) {
			RelationShip r = (RelationShip) p.getElement();
			value = r.getSource().getName() + "<-->" + r.getTarget().getName();
		}

		boolean findC = false, findR = false, findU = false, findD = false;
		if (p.eContainer() != null) {
			for (Privilege p2 : ((PrivilegeGroup) p.eContainer())
					.getPrivileges()) {
				if (p2.getElement() != null
						&& p2.getElement().equals(p.getElement())) {
					if (p2.getCategory() == PrivilegeNature.CREATE)
						findC = true;
					if (p2.getCategory() == PrivilegeNature.READ)
						findR = true;
					if (p2.getCategory() == PrivilegeNature.UPDATE)
						findU = true;
					if (p2.getCategory() == PrivilegeNature.DELETE)
						findD = true;
				}
			}
		}

		// Update the value
		value += " (";
		int nbOfPrivileges = 0;

		// Create
		if (findC) {
			value += "C";
			nbOfPrivileges++;
		}

		// Read
		if (findR) {
			if (nbOfPrivileges > 0)
				value += ",";
			value += "R";
			nbOfPrivileges++;
		}

		// Update
		if (findU) {
			if (nbOfPrivileges > 0)
				value += ",";
			value += "U";
			nbOfPrivileges++;
		}

		// Delete
		if (findD) {
			if (nbOfPrivileges > 0)
				value += ",";
			value += "D";
			nbOfPrivileges++;
		}

		value += ")";

		return value;
	}

}
