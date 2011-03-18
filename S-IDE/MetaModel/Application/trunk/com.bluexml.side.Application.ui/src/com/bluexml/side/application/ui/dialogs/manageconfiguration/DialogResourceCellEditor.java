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


package com.bluexml.side.application.ui.dialogs.manageconfiguration;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.bluexml.side.application.ui.Activator;
import com.bluexml.side.application.ui.action.table.GeneratorParameter;
import com.bluexml.side.application.ui.dialogs.ResourceSelectionListener;
import com.bluexml.side.application.ui.dialogs.RessourcesSelection;

public class DialogResourceCellEditor extends CellEditor {
	private RessourcesSelection resourceSelector;
	private Table table;
	private Text t;

	public DialogResourceCellEditor(Table table) {
		super(table);
		this.table = table;
	}

	@Override
	protected Control createControl(Composite parent) {
		resourceSelector = new RessourcesSelection(parent.getShell());
		t = new Text(parent, getStyle());
		t.setEditable(false);
		t.setFont(parent.getFont());
		t.setBackground(parent.getBackground());
		// bind action on control selection
		t.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {
				handleActivateEditor();
			}

			public void mouseDown(MouseEvent e) {
			}

			public void mouseDoubleClick(MouseEvent e) {

			}
		});

		t.addMouseTrackListener(new MouseTrackListener() {

			public void mouseHover(MouseEvent e) {
			}

			public void mouseExit(MouseEvent e) {
				deactivate();
			}

			public void mouseEnter(MouseEvent e) {
			}
		});

		// fire apply value change on "ok" button
		resourceSelector.addResourceSelectionListener(new ResourceSelectionListener() {
			public void ok() {
				manageOk();
			}

			public void cancel() {
				manageOk();
			}
		});

		return t;
	}

	@Override
	protected Object doGetValue() {
		return resourceSelector.getResourcePath();
	}

	@Override
	protected void doSetFocus() {
		// nothing to do
	}

	@Override
	protected void doSetValue(Object value) {
		if (value == null) {
			// the field is empty and without default value
			value = "";
		}
		t.setText((String) value);
		// initialize ResourceSelection dialog
		initControl();
	}

	/**
	 * @param value
	 */
	private RessourcesSelection initControl() {
		TableItem[] it = table.getSelection();
		String label = Activator.Messages.getString("DialogResourceCellEditor.1");
		String type = "";
		String value = "";
		for (TableItem tableItem : it) {
			Object selected = tableItem.getData();
			if (selected != null && selected instanceof GeneratorParameter) {
				label = ((GeneratorParameter) selected).getLabel();
				type = ((GeneratorParameter) selected).getDataType();
				value = ((GeneratorParameter) selected).getValue();
			}
		}
		resourceSelector.init(label, value, type);

		return resourceSelector;
	}

	protected void manageOk() {
		t.setText(resourceSelector.getResourcePath());
		fireApplyEditorValue();
		deactivate();
	}

	private void handleActivateEditor() {
		resourceSelector.open();
	}
}
