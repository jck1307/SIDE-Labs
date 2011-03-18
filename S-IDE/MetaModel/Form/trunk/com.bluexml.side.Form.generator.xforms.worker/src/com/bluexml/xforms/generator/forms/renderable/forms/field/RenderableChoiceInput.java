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


package com.bluexml.xforms.generator.forms.renderable.forms.field;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.eclipse.emf.common.util.EList;
import org.jdom.Element;

import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.form.ChoiceField;
import com.bluexml.side.form.FormElement;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.SelectBean;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableField;

/**
 * The Class RenderableChoiceInput.
 */
public class RenderableChoiceInput extends RenderableField<ChoiceField> {

	/**
	 * Instantiates a new renderable choice input.
	 * 
	 * @param generationManager
	 *            the generation manager
	 * @param parent
	 *            the parent
	 * @param formElement
	 *            the form element
	 */
	public RenderableChoiceInput(XFormsGenerator generationManager, FormElement parent,
			ChoiceField formElement) {
		super(generationManager, parent, formElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.bluexml.xforms.generator.forms.renderable.common.field.AbstractRenderableField#
	 * getCustomElement(com.bluexml.xforms.generator.forms.Rendered,
	 * com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple, java.lang.String,
	 * java.util.Stack, java.util.Stack)
	 */
	@Override
	protected Element getCustomElement(Rendered rendered, ModelElementBindSimple meb,
			String slabel, Stack<Renderable> parents, Stack<Rendered> renderedParents) {
		Enumeration anEnum = null;
		boolean enumTypeIsWorkflow = false;
		com.bluexml.side.common.ModelElement ref;
		List<String> listOfValues = null;
		String enumContext = null;
		String enumParent = null;

		ref = formElement.getRef();
		ref = (com.bluexml.side.common.ModelElement) generationManager.getFormGenerator()
				.getRealObject(ref);
		if (ref instanceof Attribute) {
			Attribute attribute = (Attribute) ref;
			anEnum = attribute.getValueList();
		}
		// ** #1313
		if (ref instanceof com.bluexml.side.workflow.Attribute) {
			enumTypeIsWorkflow = true;
			com.bluexml.side.workflow.Attribute attribute = (com.bluexml.side.workflow.Attribute) ref;
			EList<String> elist = attribute.getAllowedValues();
			if (elist != null) {
				if (elist.size() == 0) {
					throw new RuntimeException(
							"Invalid list of allowed values for the Workflow Attribute '"
									+ attribute.getTitle() + "'.");
				}
				listOfValues = new ArrayList<String>(elist.size());
				listOfValues.addAll(elist);
			}
		}
		// ** #1313

		if (enumTypeIsWorkflow == false) {
			if (anEnum != null && anEnum.getDynamic()) {
				enumContext = getDynEnumContextString(formElement.getId());
				enumParent = getDynEnumParentString(formElement.getId());
			}
		}
		SelectBean selectBean = new SelectBean();
		selectBean.setAllowedValues(listOfValues);
		selectBean.setEnumeration(anEnum);
		selectBean.setEnumContext(enumContext);
		selectBean.setEnumParent(enumParent);
		selectBean.setLabel(slabel);
		selectBean.setModelElementBindSimple(meb);
		selectBean.setMultiple(formElement.isMultiple());
		selectBean.setLimited(false);
		selectBean.setWidgetType(formElement.getWidget());
		selectBean.setWorkflowEnum(enumTypeIsWorkflow);
		selectBean.setDataSourceUri(getFormGenerator().getXtensionDataSourceUri(formElement));

		return getSelectElement(rendered, selectBean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.xforms.generator.forms.renderable.common.field.AbstractRenderableField#getXsdType
	 * ()
	 */
	@Override
	protected String getXsdType() {
		return "string";
	}

}
