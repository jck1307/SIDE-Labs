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

import org.eclipse.draw2d.Locator;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.swt.SWT;
import org.topcased.modeler.edit.locators.EdgeObjectOffsetLocator;
import org.topcased.modeler.figures.EdgeObjectOffsetEditableLabel;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.figures.IEdgeObjectOffsetFigure;

/**
 * @generated
 */
public class RelationShipFigure extends PolylineConnectionEx {

	private IEdgeObjectFigure middleNameEdgeObject;

	private Locator middleNameLocator;
	
	/**
	 * The constructor
	 *
	 * @_generated
	 */
	public RelationShipFigure() {
		super();
		setLineStyle(SWT.LINE_SOLID);
		
		middleNameEdgeObject = new EdgeObjectOffsetEditableLabel(this);
		middleNameLocator = new EdgeObjectOffsetLocator((IEdgeObjectOffsetFigure) middleNameEdgeObject);
		add(middleNameEdgeObject, middleNameLocator);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the object figure
	 * @_generated
	 */
	public IEdgeObjectFigure getmiddleNameEdgeObjectFigure() {
		return middleNameEdgeObject;
	}
	
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.modeler.figures.IGraphEdgeFigure#getLocator(org.topcased.modeler.figures.IEdgeObjectFigure)
	 * @_generated
	 */
	public Locator getLocator(IEdgeObjectFigure edgeObjectfigure) {

		if (edgeObjectfigure == middleNameEdgeObject) {
			return middleNameLocator;
		}

		return null;
	}
	
}
