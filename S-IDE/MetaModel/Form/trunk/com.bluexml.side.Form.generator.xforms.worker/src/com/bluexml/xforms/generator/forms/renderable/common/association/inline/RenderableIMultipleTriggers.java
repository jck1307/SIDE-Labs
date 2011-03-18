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


package com.bluexml.xforms.generator.forms.renderable.common.association.inline;

import java.util.Stack;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.rendered.RenderedInput;
import com.bluexml.xforms.generator.tools.ModelTools;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * The Class RenderableIMultipleTriggers.
 */
public class RenderableIMultipleTriggers extends AbstractRenderable {

	/**
	 * Instantiates a new renderable i multiple triggers.
	 * 
	 * @param bean
	 *            the bean
	 */
	public RenderableIMultipleTriggers(AssociationBean bean) {
		super(bean);
	}

	/**
	 * Creates the add.
	 * 
	 * @param bind
	 *            the bind
	 * @param repeaterId
	 *            the repeater id
	 * 
	 * @return the element
	 */
	private Element createAdd(ModelElementBindSimple bind) {
		String label = MsgPool.getMsg(MsgId.MSG_TRIGGER_INLINE_MULTIPLE_ADD, ModelTools
				.getTitle(bean.getDestinationClass()));
		Element add = XFormsGenerator.createElementWithLabel("trigger",
				XFormsGenerator.NAMESPACE_XFORMS, label);
		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);
		if (bean.getHiBound() > 1) {
			String maxcount = Integer.toString(bean.getHiBound() + 1);
			action.setAttribute("if", "count(instance('minstance')/" + bind.getNodeset() + ") < "
					+ maxcount);
		}

		Element insertAction = XFormsGenerator.createElement("insert",
				XFormsGenerator.NAMESPACE_XFORMS);
		bind.addLinkedElement(insertAction);
		insertAction.setAttribute("at", "last()");
		insertAction.setAttribute("position", "after");
		action.addContent(insertAction);

		Element sideUpdateAction = XFormsGenerator.createElement("script",
				XFormsGenerator.NAMESPACE_XHTML);
		sideUpdateAction.setAttribute("type", "text/javascript");
		sideUpdateAction.setText("side.updateUI();");
		action.addContent(sideUpdateAction);
		// Applicable when using a more recent version of Dojo than the 0.4 in Chiba 2.3. See the
		// companion comment in RenderedForm.java. It will allow for correct rendering of tabs in 
		// inline forms. What's to be done then is to add dojo.parser.parse() in the script tag.

		add.addContent(action);
		return add;
	}

	/**
	 * Creates the delete.
	 * 
	 * @param bind
	 *            the bind
	 * @param repeaterId
	 *            the repeater id
	 * @param path
	 * 
	 * @return the element
	 */
	private Element createDelete(ModelElementBindSimple bind, String repeaterId, String path) {
		String label = MsgPool.getMsg(MsgId.MSG_TRIGGER_INLINE_MULTIPLE_DELETE, ModelTools
				.getTitle(bean.getDestinationClass()));
		Element delete = XFormsGenerator.createElementWithLabel("trigger",
				XFormsGenerator.NAMESPACE_XFORMS, label);

		//** #1650
		String shortenedPath = path.substring(0, path.length() - 1); // path has a trailing '/'
		String refCondition = "self::node()[" + shortenedPath + "[2]]";
		delete.setAttribute("ref", refCondition); // hides the Delete trigger if no inline form
		//** #1650

		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);

		Element deleteAction = XFormsGenerator.createElement("delete",
				XFormsGenerator.NAMESPACE_XFORMS);
		deleteAction.setAttribute("at", "index('" + repeaterId + "')");
		deleteAction.setAttribute("event", "DOMActivate");
		bind.addLinkedElement(deleteAction);

		action.addContent(deleteAction);

		delete.addContent(action);
		return delete;
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
		RenderedInput input = new RenderedInput();
		ModelElementBindSimple bindActions = ((RenderableIMultiple) parents.peek())
				.getBindActions();
		String repeaterId = renderedParents.peek().getOptionalData();
		Element addButton = createAdd(bindActions);
		Element deleteButton = createDelete(bindActions, repeaterId, path);
		Element divButtons = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		divButtons.setAttribute("class", ModelTools.getCompleteNameJAXB(bean.getDestinationClass())
				+ "_triggers");

		if (getFormGenerator().isInReadOnlyMode() == false) {
			divButtons.addContent(addButton);
			divButtons.addContent(deleteButton);
		}
		input.setXformsElement(divButtons);
		return input;
	}
}
