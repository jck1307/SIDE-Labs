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


package com.bluexml.xforms.generator.forms.renderable.common.association.selection.multiple;

import java.util.Stack;

import org.apache.commons.lang.StringUtils;
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
 * The Class RenderableSMultipleActionsAddRemove. Adds LEFT and RIGHT buttons in the N-N selection
 * widget.
 */
public class RenderableSMultipleActionsAddRemove extends AbstractRenderable {

	/** The selector bind id. */
	private ModelElementBindSimple selectorBindId;

	/** The selector bind label. */
	private ModelElementBindSimple selectorBindLabel;

	/** The selector bind for data type. */
	private ModelElementBindSimple selectorBindType;

	private RenderableSelector selector;

	/**
	 * Instantiates a new renderable s multiple actions add remove.
	 * 
	 * @param associationBean
	 *            the association bean
	 * @param selector
	 *            the selector that will provide the binds
	 */
	public RenderableSMultipleActionsAddRemove(AssociationBean associationBean,
			RenderableSelector selector) {
		super(associationBean);

		this.selector = selector;
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
		String path = "";
		return new Path(PathType.absolutePath, path);
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
		// FIXME root path
		ModelElementBindSimple bindActions = ((RenderableSMultiple) parents.peek())
				.getBindActions();

		this.selectorBindId = selector.getBindId();
		this.selectorBindLabel = selector.getBindLabel();
		this.selectorBindType = selector.getBindType();

		RenderedXMLElement rendered = new RenderedXMLElement();

		if ((getFormGenerator().isInReadOnlyMode() == false) || bean.isDisabled()) { // #1238
			String repeaterId = renderedParents.peek().getOptionalData();
			Element xformsElement = XFormsGenerator.createElement("div",
					XFormsGenerator.NAMESPACE_XHTML);

			String rootPath = getRootPath(renderedParents); // #1218

			xformsElement.addContent(getTriggerAdd(bindActions, path, rootPath));
			xformsElement.addContent(XFormsGenerator.createElement("br",
					XFormsGenerator.NAMESPACE_XHTML));

			xformsElement.addContent(getTriggerRemove(bindActions, repeaterId));
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
	 * Gets the trigger remove.
	 * 
	 * @param bindActions
	 *            the bind actions
	 * @param repeaterId
	 *            the repeater id
	 * 
	 * @return the trigger remove
	 */
	private Element getTriggerRemove(ModelElementBindSimple bindActions, String repeaterId) {
		Element trigger = XFormsGenerator.createTriggerWithLabelImage(XFormsGenerator.IMG_REMOVE,
				"Remove item");
		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);

		action.setAttribute("event", "DOMActivate", XFormsGenerator.NAMESPACE_EVENTS);

		Element deleteAction = XFormsGenerator.createElement("delete",
				XFormsGenerator.NAMESPACE_XFORMS);
		deleteAction.setAttribute("at", "index('" + repeaterId + "')");
		bindActions.addLinkedElement(deleteAction);
		action.addContent(deleteAction);

		trigger.addContent(action);
		return trigger;
	}

	/**
	 * Gets the trigger add.
	 * 
	 * @param bindActions
	 *            the bind actions
	 * @param path
	 *            the path
	 * @param rootPath
	 *            The parent path, in case this object is embedded in an inline form
	 * 
	 * @return the trigger add
	 */
	private Element getTriggerAdd(ModelElementBindSimple bindActions, String path, String rootPath) {

		Element trigger = XFormsGenerator.createTriggerWithLabelImage(XFormsGenerator.IMG_ADD,
				"Add item");
		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);

		action.setAttribute("event", "DOMActivate", XFormsGenerator.NAMESPACE_EVENTS);
		// <xf:action ev:event="DOMActivate"
		// if="(instance('ComBluexmlDataRubriqueListInstance1')/SELECTEDID ne '') and not(parentchildOf[com.bluexml.data.Rubrique/BXDSID = instance('ComBluexmlDataRubriqueListInstance1')/SELECTEDID])">
		String selectorNodeset = selectorBindId.getNodeset();
		String realActionsNodeset = StringUtils.trimToEmpty(rootPath) + bindActions.getNodeset();
		String iftest = "(" + selectorNodeset + " ne '') " + "and not(" + realActionsNodeset + "["
				+ path + MsgId.INT_INSTANCE_SIDEID + " = " + selectorNodeset + "])";
		if (bean.getHiBound() > 1) {
			String maxcount = Integer.toString(bean.getHiBound() + 1);
			iftest = iftest + " and (count(" + realActionsNodeset + ") < " + maxcount + ")";
		}
		action.setAttribute("if", iftest);

		Element insertAction = XFormsGenerator.createElement("insert",
				XFormsGenerator.NAMESPACE_XFORMS);
		bindActions.addLinkedElement(insertAction);
		insertAction.setAttribute("at", "last()");
		insertAction.setAttribute("position", "after");
		action.addContent(insertAction);

		Element setvalueId = XFormsGenerator.createElement("setvalue",
				XFormsGenerator.NAMESPACE_XFORMS);
		String pathSIDE = bindActions.getNodeset() + "[last() - 1]/" + path;
		setvalueId.setAttribute("ref", pathSIDE + MsgId.INT_INSTANCE_SIDEID);
		setvalueId.setAttribute("value", selectorNodeset);
		action.addContent(setvalueId);

		Element setvalueLabel = XFormsGenerator.createElement("setvalue",
				XFormsGenerator.NAMESPACE_XFORMS);
		setvalueLabel.setAttribute("ref", pathSIDE + MsgId.INT_INSTANCE_SIDELABEL);
		setvalueLabel.setAttribute("value", selectorBindLabel.getNodeset());
		action.addContent(setvalueLabel);

		Element setvalueType = XFormsGenerator.createElement("setvalue",
				XFormsGenerator.NAMESPACE_XFORMS);
		setvalueType.setAttribute("ref", pathSIDE + MsgId.INT_INSTANCE_SIDETYPE);
		setvalueType.setAttribute("value", selectorBindType.getNodeset());
		action.addContent(setvalueType);

		trigger.addContent(action);
		return trigger;
	}

}
