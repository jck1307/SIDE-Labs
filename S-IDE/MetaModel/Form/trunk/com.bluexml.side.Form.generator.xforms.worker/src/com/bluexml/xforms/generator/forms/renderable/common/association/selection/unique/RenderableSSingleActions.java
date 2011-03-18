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


package com.bluexml.xforms.generator.forms.renderable.common.association.selection.unique;

import java.util.Stack;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector.RenderableSelector;
import com.bluexml.xforms.generator.forms.rendered.RenderedXMLElement;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class RenderableSSingleActions. Adds LEFT and RIGHT buttons in the N-1 selection widget.
 */
public class RenderableSSingleActions extends AbstractRenderable {

	/** The selector bind id. */
	private ModelElementBindSimple selectorBindId;

	/** The selector bind label. */
	private ModelElementBindSimple selectorBindLabel;

	/** The selector bind for the data type. */
	private ModelElementBindSimple selectorBindType;

	private RenderableSelector selector;

	/**
	 * Instantiates a new renderable s single actions.
	 * 
	 * @param associationBean
	 *            the association bean
	 * @param selectorBindId
	 *            the selector bind id
	 * @param selectorBindLabel
	 *            the selector bind label
	 */
	public RenderableSSingleActions(AssociationBean associationBean, RenderableSelector selector) {
		super(associationBean);

		this.selector = selector;
		this.selectorBindId = selector.getBindId();
		this.selectorBindLabel = selector.getBindLabel();
		this.selectorBindType = selector.getBindType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#getPath(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return ROOT_RELATIVE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		RenderedXMLElement rendered = new RenderedXMLElement();

		RenderableSSingle rSingle = (RenderableSSingle) parents.peek();
		ModelElementBindSimple bindId = rSingle.getSelectedBindId();
		ModelElementBindSimple bindLabel = rSingle.getSelectedBindLabel();
		ModelElementBindSimple bindType = rSingle.getSelectedBindType();

		if ((getFormGenerator().isInReadOnlyMode() == false) || bean.isDisabled()) { // #1238
			Element xformsElement = XFormsGenerator.createElement("div",
					XFormsGenerator.NAMESPACE_XHTML);

			xformsElement.addContent(getTriggerSelect(bindId, bindLabel, bindType));
			xformsElement.addContent(XFormsGenerator.createElement("br",
					XFormsGenerator.NAMESPACE_XHTML));
			xformsElement.addContent(getTriggerReset(bindId, bindLabel, bindType));
			rendered.setXformsElement(xformsElement);
		}
		return rendered;
	}

	/* (non-Javadoc)
	 * @see com.bluexml.xforms.generator.forms.Renderable#getDivStyle()
	 */
	@Override
	public String getDivStyle() {
		return MsgId.INT_CSS_SELECT_TRIGGER_IMG.getText();
	}

	/**
	 * Gets the trigger reset.
	 * 
	 * @param bindId
	 *            the bind id
	 * @param bindLabel
	 *            the bind label
	 * 
	 * @return the trigger reset
	 */
	private Element getTriggerReset(ModelElementBindSimple bindId,
			ModelElementBindSimple bindLabel, ModelElementBindSimple bindType) {
		String img = selector.isForField() ? XFormsGenerator.IMG_DESELECT
				: XFormsGenerator.IMG_LEFT;
		Element trigger = XFormsGenerator.createTriggerWithLabelImage(img, "Remove item");
		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);
		action.setAttribute("event", "DOMActivate", XFormsGenerator.NAMESPACE_EVENTS);

		Element setvalueId = XFormsGenerator.createElement("setvalue",
				XFormsGenerator.NAMESPACE_XFORMS);
		bindId.addLinkedElement(setvalueId);
		setvalueId.setText("");
		action.addContent(setvalueId);

		Element setvalueLabel = XFormsGenerator.createElement("setvalue",
				XFormsGenerator.NAMESPACE_XFORMS);
		bindLabel.addLinkedElement(setvalueLabel);
		setvalueLabel.setText("");
		action.addContent(setvalueLabel);

		if (selector.isForField() == false) {
			Element setvalueType = XFormsGenerator.createElement("setvalue",
					XFormsGenerator.NAMESPACE_XFORMS);
			bindType.addLinkedElement(setvalueType);
			setvalueType.setText("");
			action.addContent(setvalueType);
		}

		// if (getBean().getAssociationType() == AssociationType.wkflwProcess) {
		// // for updating the instances list wrt current process definition
		// Element send = XFormsGenerator.createElement("send", XFormsGenerator.NAMESPACE_XFORMS);
		// selector.getModelElementUpdater().addLinkedElement(send);
		// action.addContent(send);
		// }

		trigger.addContent(action);
		return trigger;
	}

	/**
	 * Gets the trigger set.
	 * 
	 * @param renderedParents
	 *            the rendered parents
	 * @param bindId
	 *            the bind id
	 * @param bindLabel
	 *            the bind label
	 * @param bindType
	 * 
	 * @return the trigger set
	 */
	private Element getTriggerSelect(ModelElementBindSimple bindId,
			ModelElementBindSimple bindLabel, ModelElementBindSimple bindType) {
		String img = selector.isForField() ? XFormsGenerator.IMG_SELECT : XFormsGenerator.IMG_RIGHT;
		Element trigger = XFormsGenerator.createTriggerWithLabelImage(img, "Select item");
		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);
		action.setAttribute("event", "DOMActivate", XFormsGenerator.NAMESPACE_EVENTS);

		Element setvalueId = XFormsGenerator.createElement("setvalue",
				XFormsGenerator.NAMESPACE_XFORMS);
		bindId.addLinkedElement(setvalueId);
		setvalueId.setAttribute("value", selectorBindId.getNodeset());
		action.addContent(setvalueId);

		Element setvalueLabel = XFormsGenerator.createElement("setvalue",
				XFormsGenerator.NAMESPACE_XFORMS);
		bindLabel.addLinkedElement(setvalueLabel);
		setvalueLabel.setAttribute("value", selectorBindLabel.getNodeset());
		action.addContent(setvalueLabel);

		if (selector.isForField() == false) {
			Element setvalueType = XFormsGenerator.createElement("setvalue",
					XFormsGenerator.NAMESPACE_XFORMS);
			bindType.addLinkedElement(setvalueType);
			setvalueType.setAttribute("value", selectorBindType.getNodeset());
			action.addContent(setvalueType);

		}
		// if (getBean().getAssociationType() == AssociationType.wkflwProcess) {
		// // for updating the instances list wrt current process definition
		// Element send = XFormsGenerator.createElement("send", XFormsGenerator.NAMESPACE_XFORMS);
		// selector.getModelElementUpdater().addLinkedElement(send);
		// action.addContent(send);
		// }

		trigger.addContent(action);
		return trigger;
	}

}
