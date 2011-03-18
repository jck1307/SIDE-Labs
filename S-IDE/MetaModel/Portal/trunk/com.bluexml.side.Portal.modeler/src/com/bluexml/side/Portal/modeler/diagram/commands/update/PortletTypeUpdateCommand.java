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

import com.bluexml.side.Portal.modeler.diagram.dialogs.PortletTypeEditDialog;
import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.PortletTypeAttributeDataStructure;
import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.PortletTypeAttributeDataStructure.PortletTypeAttributeObject;
import com.bluexml.side.portal.PortalFactory;
import com.bluexml.side.portal.PortletAttribute;
import com.bluexml.side.portal.PortletType;
import com.bluexml.side.portal.PortletTypeAttributeType;

public class PortletTypeUpdateCommand  extends Command{
protected PortletType portletType;
	
	private Map<String,Object> newData;
	
	public PortletTypeUpdateCommand(PortletType p_portletType, Map<String,Object> p_data) {
		this.portletType = p_portletType;		
		this.newData = p_data;
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
		portletType.setId((String)newData.get(PortletTypeEditDialog.PORTLETYPE_ID));
		portletType.setName((String)newData.get(PortletTypeEditDialog.PORTLETYPE_Name));
		portletType.setInstanceable((Boolean)newData.get(PortletTypeEditDialog.PORTLETYPE_Instanceable));		
		List<PortletAttribute> newAttributes = new ArrayList<PortletAttribute>();				
		// Attributes :		
		PortletTypeAttributeDataStructure attributesDS = (PortletTypeAttributeDataStructure) newData.get(PortletTypeEditDialog.PORTLETYPE_Attributes);
		Iterator<PortletTypeAttributeObject> itAttr = attributesDS.getData().iterator();		
		while (itAttr.hasNext()) {
			PortletTypeAttributeObject attr = itAttr.next();					
			String name = attr.getName();						
						
			int type = attr.getType();			
			boolean isRequired = attr.isRequired();
			boolean isMultivalued = attr.isMultiValued();
			
			PortletAttribute attribute = getAttr(name);
			if (attribute == null) {
				attribute = PortalFactory.eINSTANCE.createPortletAttribute();
				attribute.setName(name);
			}	
			attribute.setRequired(isRequired);
			attribute.setMultiValued(isMultivalued);
			attribute.setType(PortletTypeAttributeType.get(type));						
			newAttributes.add(attribute);
		}
		portletType.getHaveAttribute().clear();
		portletType.getHaveAttribute().addAll(newAttributes);		
		
	}

	

	public PortletAttribute getAttr(String p_name) {
		PortletAttribute result = null;
		for (Object o : portletType.getHaveAttribute()) {
			if (o instanceof PortletAttribute) {
				PortletAttribute p = (PortletAttribute) o;
				if (p.getName().equals(p_name))
					result = p;
			}
		}
		return result;
	}
}
