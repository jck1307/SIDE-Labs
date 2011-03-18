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


package com.bluexml.side.Requirements.modeler.goalDiagram.figures;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.topcased.modeler.figures.IEdgeObjectOffsetFigure;

public class CommentFigure extends org.topcased.draw2d.figures.CommentFigure
		implements IEdgeObjectOffsetFigure {

	/* The owning connection */
    private Connection connection;
    
    /* This label figure offset */
    private Dimension offset;
    
    /**
     * 
     */
    public CommentFigure(Connection connection)
    {
        super();
        this.connection = connection;
        setOffset(new Dimension(0,0));
        setBackgroundColor(new Color(null,152,251,152));
        setForegroundColor(new Color(null,0,136,43));
        Font helvetica14Normal = new Font(null,"Tahoma",9,java.awt.Font.PLAIN);    	
    	setFont(helvetica14Normal);
    }

	
	public Connection getConnection() {
		return connection;
	}

	public boolean isEditable() {
		return false;
	}

	public boolean isEmpty() {
		return getText().length() == 0;
	}

    /**
     * @see org.topcased.modeler.figures.IEdgeObjectFigure#getOffset()
     */
    public Dimension getOffset()
    {
        return offset;
    }

    /**
     * @see org.topcased.modeler.figures.IEdgeObjectFigure#setOffset(org.eclipse.draw2d.geometry.Dimension)
     */
    public void setOffset(Dimension offset)
    {
        this.offset = offset;
        revalidate();
    }

    protected void paintFigure(Graphics graphics)
    {
    	Rectangle rect = getBounds().getCopy();

        graphics.translate(getLocation());

        // fill the note
        PointList outline = new PointList();
        if (rect.width <= 30 || rect.height <= 30) {
        	outline.addPoint(0, 0);
        	outline.addPoint(rect.width-1, 0);
        	outline.addPoint(rect.width-1, rect.height-1);
        	outline.addPoint(0, rect.height-1);
        } else {
        	outline.addPoint(0, 8);
        	outline.addPoint(rect.width - 21, 9);
        	outline.addPoint(rect.width - 21, 0);
        	outline.addPoint(rect.width - 1, 0);
        	outline.addPoint(rect.width - 1, 20);
        	outline.addPoint(rect.width - 10, 20);
        	outline.addPoint(rect.width - 10, rect.height - 1);
        	outline.addPoint(0, rect.height - 1);
        }
        graphics.fillPolygon(outline);
        
        Point p = new Point(rect.width-18,2);
        
        if (rect.width > 16 && rect.height > 16)
        	graphics.drawImage(new Image(null, CommentFigure.class
				.getResourceAsStream("img/strategy.png")), p);

        graphics.drawPolygon(outline);

        graphics.translate(getLocation().getNegated());
    }
}
