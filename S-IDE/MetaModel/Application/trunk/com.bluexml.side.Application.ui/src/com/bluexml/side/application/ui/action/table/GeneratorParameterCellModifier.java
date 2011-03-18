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


package com.bluexml.side.application.ui.action.table;

import java.util.Arrays;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

import com.bluexml.side.application.ApplicationFactory;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.ui.action.ApplicationDialog;

public class GeneratorParameterCellModifier implements ICellModifier {

	private String[] columnNames;
	private GeneratorParameterDataStructure dataStructure;
	private TableViewer generatorParametersViewer;

	public GeneratorParameterCellModifier(GeneratorParameterDataStructure p_dataStructure, String[] p_columnNames, TableViewer p_generatorParametersViewer) {
		columnNames = p_columnNames;
		dataStructure = p_dataStructure;
		generatorParametersViewer = p_generatorParametersViewer;
	}

	public void setDataStructure(GeneratorParameterDataStructure dataStructure) {
		this.dataStructure = dataStructure;
	}

	public boolean canModify(Object element, String property) {
		boolean result = false;
		int index = Arrays.asList(columnNames).indexOf(property);
		if (index == 1) {
			result = true;
		}
		return result;
	}

	public Object getValue(Object element, String property) {
		Object result = null;
		int index = Arrays.asList(columnNames).indexOf(property);
		switch (index) {
		case 0:
			result = dataStructure.getLabel(element);
			break;
		case 1:
			result = dataStructure.getValue(element);
			break;
		default:
			break;
		}
		return result;
	}

	public void modify(Object element, String property, Object value) {
		TableItem item = (TableItem) element;
		int index = Arrays.asList(columnNames).indexOf(property);
		if (item != null) {
			switch (index) {
			case 0:
				dataStructure.setLabel(item.getData(), (String) value);
				break;
			case 1:
				if (value != null) {
					dataStructure.setValue(item.getData(), (String) value);
				}
				break;
			default:
				break;
			}
			ApplicationDialog.modificationMade();
			updateApplication(item);
			generatorParametersViewer.update(item.getData(), null);
		} else {
			throw new RuntimeException("Error on data, selection was null");
		}
	}

	private void updateApplication(TableItem item) {
		if (item.getData() instanceof GeneratorParameter) {
			GeneratorParameter modifiedParam = (GeneratorParameter) item.getData();
			Configuration config = ApplicationDialog.getCurrentConfiguration();
			if (config != null) {
				// Search generator parameter
				boolean modified = false;
				for (ConfigurationParameters param : config.getParameters()) {
					if (param.getKey().equals(modifiedParam.getKey())) {
						param.setValue(modifiedParam.getValue());
						modified = true;
					}
				}
				// If no modification we must create it
				if (!modified) {
					ConfigurationParameters newParam = ApplicationFactory.eINSTANCE.createConfigurationParameters();
					newParam.setKey(modifiedParam.getKey());
					newParam.setValue(modifiedParam.getValue());
					config.getParameters().add(newParam);
				}
			}
		}
	}

	public void applyDirtyValue() {
		if (generatorParametersViewer.isCellEditorActive()) {
			CellEditor ce = generatorParametersViewer.getCellEditors()[1];
			String value = ce.getValue().toString();
			TableItem ti = generatorParametersViewer.getTable().getItem(generatorParametersViewer.getControl().getLocation());
			String property = "Valeur";
			this.modify(ti, property, value);
			generatorParametersViewer.cancelEditing();
		}
	}

}
