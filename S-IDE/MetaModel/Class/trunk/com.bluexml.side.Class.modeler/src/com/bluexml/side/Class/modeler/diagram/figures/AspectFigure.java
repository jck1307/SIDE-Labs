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
package com.bluexml.side.Class.modeler.diagram.figures;

import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.Label;

import com.bluexml.side.Class.modeler.diagram.figure.ColorScaleUtil;

/**
 * @generated
 */
public class AspectFigure extends org.topcased.draw2d.figures.ClassFigure {
	/**
	 * Constructor
	 *
	 * @generated
	 */
	public AspectFigure() {
		super();
	}

	@Override
	protected void drawFigure() {
		ToolbarLayout layout = new ToolbarLayout();
		setLayoutManager(layout);

		setBorder(new CompoundBorder(new LineBorder(), new MarginBorder(1)));
		setOpaque(true);

		setLabel(new ComposedLabel(new Label(), new EditableLabel(), new Label(), false));
		add(getLabel());

		Figure fig = new Figure();
		ToolbarLayout l = new ToolbarLayout(true);
		l.setMinorAlignment(ToolbarLayout.ALIGN_CENTER);
		fig.setLayoutManager(l);
		add(fig);

		setContentPane(new Figure());
		getContentPane().setLayoutManager(new ToolbarLayout());
		add(getContentPane());
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		ColorScaleUtil.paintFigure(graphics, this);
	}
}
