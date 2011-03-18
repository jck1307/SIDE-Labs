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


package com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector;

import java.util.Stack;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.rendered.RenderedInput;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * The search input in the selection widgets. Consists of the label, the input and, possibly, the
 * 'launch search' button.
 */
public class RenderableSelectorSearcher extends AbstractRenderableSelectorItem {

	/**
	 * Instantiates a new renderable selector searcher.
	 * 
	 * @param associationBean
	 *            the association bean
	 * @param renderableAssociationSelectionSelector
	 *            the renderable association selection selector
	 */
	public RenderableSelectorSearcher(AssociationBean associationBean,
			RenderableSelector renderableAssociationSelectionSelector) {
		super(associationBean, renderableAssociationSelectionSelector);
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
		RenderedInput rendered = new RenderedInput();
		boolean usingAutoSearch = (bean.isNoAutoSearch() == false);

		Element input = XFormsGenerator.createElement("input", XFormsGenerator.NAMESPACE_XFORMS);
		input.setAttribute("id", XFormsGenerator.getId("searchInput"));
		input.setAttribute("incremental", "true");
		input.setAttribute("ref", getInstancePath() + "query/query");

		Element label = XFormsGenerator.createElement("label", XFormsGenerator.NAMESPACE_XFORMS);
		if (bean.isInFeatureFilterMode()) {
			label.setText(MsgPool.getMsg(MsgId.MSG_SELECT_LIST_FILTER_LABEL));
		}
		if (bean.isInFeatureSearchMode()) {
			label.setText(MsgPool.getMsg(MsgId.MSG_SELECT_LIST_SEARCH_LABEL));
		}
		input.addContent(label);

		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);
		action.setAttribute("event", "xforms-value-changed", XFormsGenerator.NAMESPACE_EVENTS);
		input.addContent(action);

		Element setvalue = XFormsGenerator.createElement("setvalue",
				XFormsGenerator.NAMESPACE_XFORMS);
		setvalue.setAttribute("ref", getInstancePath() + "query/maxResults");
		setvalue.setAttribute("value", getInstancePath() + "SELECTMAX");
		action.addContent(setvalue);

		Element send = XFormsGenerator.createElement("send", XFormsGenerator.NAMESPACE_XFORMS);
		getModelElementUpdater().addLinkedElement(send);

		Element trigger = XFormsGenerator.createTriggerWithLabelImage(XFormsGenerator.IMG_SEARCH,
				"Launch search");

		if (usingAutoSearch) {
			// start searching at 3 characters (if using auto search - condition added wrt #1666)
			action.setAttribute("if", "(string-length(.) > 2) or (. eq '')"); // #1666
			action.addContent(send);
		} else {
			Element actionTrigger = XFormsGenerator.createElement("action",
					XFormsGenerator.NAMESPACE_XFORMS);

			actionTrigger.setAttribute("event", "DOMActivate", XFormsGenerator.NAMESPACE_EVENTS);
			actionTrigger.addContent(send);
			trigger.addContent(actionTrigger);
		}

		Element div = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		// align right side with the right side of the item list
		div.setAttribute("class", "xformstdright");
		div.addContent(input);
		if (!usingAutoSearch) {
			div.addContent(trigger);

			// set style in order to keep the search button on the same horizontal line as the input
			input.setAttribute("class", "xformstdleft");
		}

		Element outerDiv = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		outerDiv.setAttribute("class", MsgId.INT_CSS_SELECT_SEARCH_ZONE.getText());
		outerDiv.addContent(div);

		rendered.setXformsElement(outerDiv);

		return rendered;
	}

}
