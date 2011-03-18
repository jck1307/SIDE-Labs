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
package com.bluexml.side.Requirements.modeler.goalDiagram.figures;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.ILabel;

/**
 * @generated
 */
public class GoalFigure extends
		org.topcased.draw2d.figures.ContainerWithInnerLabel {

	/**
	 * Constructor
	 * 
	 * @generated
	 */
	public GoalFigure() {
		super();
	}

	@Override
	protected IFigure createBackgroundFigure() {
		Ellipse fig = new Ellipse();
		fig.setOpaque(true);
		fig.setFill(true);
		fig.setLineStyle(Graphics.LINE_DOT);
		fig.setForegroundColor(new org.eclipse.swt.graphics.Color(null, 0, 102,
				104));
		return fig;
	}

	@Override
	protected ILabel createLabel() {
		return new EditableLabel(true);
	}

}
