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
package com.bluexml.side.Workflow.modeler.diagram.figures;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.topcased.draw2d.figures.Label;

import com.bluexml.side.Workflow.modeler.diagram.WfImageRegistry;

/**
 * @_generated
 */
public class AttributeFigure extends
		org.topcased.draw2d.figures.GraphicWithLabelFigure {
	/**
	 * Constructor
	 * 
	 * @_generated
	 */
	public AttributeFigure() {
		super(PositionConstants.RIGHT);
	}

	@Override
	protected IFigure createBodyFigure() {
		Label fig = new Label(WfImageRegistry.getImage("ATTRIBUTE"));
		fig.setAlignment(PositionConstants.LEFT);
		return fig;
	}
}
