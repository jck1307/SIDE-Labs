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


package com.bluexml.side.Portal.modeler.diagram.commands.update;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.Portal.modeler.diagram.dialogs.PortalLayoutEditDialog;
import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.ColumnDataStructure;
import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.ColumnDataStructure.ColumnObject;
import com.bluexml.side.portal.Column;
import com.bluexml.side.portal.PortalFactory;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.widthUnit;

public class PortalLayoutUpdateCommand extends Command {

	protected PortalLayout portalLayout;
	/** Map containing new association data */
	private Map<String, Object> newAssociationData;

	public PortalLayoutUpdateCommand(PortalLayout p_portalLayout, Map<String, Object> p_data) {
		this.portalLayout = p_portalLayout;
		this.newAssociationData = p_data;
	}

	/**
	 * Get the old values and set the new ones
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		redo();
	}

	/**
	 * Set the new values
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		portalLayout.setName((String) newAssociationData.get(PortalLayoutEditDialog.PAGELAYOUT_Name));
		portalLayout.setColumnMode((Boolean) newAssociationData.get(PortalLayoutEditDialog.PAGELAYOUT_ColumnsKind));

		ColumnDataStructure columnsStruct = (ColumnDataStructure) newAssociationData.get(PortalLayoutEditDialog.PAGELAYOUT_Columns);
		// Perform update for input parameters
		List<Column> newColumns = new ArrayList<Column>();
		Iterator<ColumnObject> iterator = columnsStruct.getData().iterator();
		while (iterator.hasNext()) {
			Object object = iterator.next();
			String name = columnsStruct.getName(object);
			String type = columnsStruct.getType(object);
			int width = columnsStruct.getWidth(object);

			Column column = getColumn(name);
			if (column == null) {
				column = PortalFactory.eINSTANCE.createColumn();
				column.setName(name);
			}
			column.setUnit(widthUnit.get(type));
			column.setWidth(width);
			newColumns.add(column);
		}
		portalLayout.getColumns().clear();
		portalLayout.getColumns().addAll(newColumns);
	}

	public Column getColumn(String displayName) {
		Column result = null;
		for (Object o : portalLayout.getColumns()) {
			if (o instanceof Column) {
				Column p = (Column) o;
				if (p.getName().equals(displayName))
					result = p;
			}
		}
		return result;
	}
}
