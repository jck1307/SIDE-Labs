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


/**
 * 
 */
package com.bluexml.xforms.generator.forms.modelelement;

import java.util.List;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.ModelElement;
import com.bluexml.xforms.generator.forms.XFormsGenerator;

/**
 * @author Amenel
 * @deprecated
 */
@Deprecated
public class ModelElementWorkflowInstanceList extends ModelElement {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.ModelElement#getModelElement()
	 */
	@Override
	public Element getModelElement() {
		Element instance = XFormsGenerator.createElement("instance",
				XFormsGenerator.NAMESPACE_XFORMS);
		// instance.setAttribute("src", MsgId.INT_URI_SCHEME_READER
		// + MsgId.INT_ACT_CODE_WRKFLW_INSTANCE_LIST.getText() + "/");
		// instance.setAttribute("id", MsgId.INT_WKFLW_INSTANCE_INSTANCE_NAME.getText());
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.ModelElement#hasClone(java.util.List)
	 */
	@Override
	public boolean hasClone(List<ModelElement> allModelElementsClean) {
		// TODO Auto-generated method stub
		return false;
	}

}
