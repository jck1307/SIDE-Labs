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
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.RenderableSDisplay;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.RenderableSEdit;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector.RenderableSelector;
import com.bluexml.xforms.generator.forms.rendered.RenderedLine;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class RenderableSSingle.
 */
public class RenderableSSingle extends AbstractRenderable {

	private ModelElementBindSimple selectorBindId; // #1156
	// ** #1310
	private ModelElementBindSimple selectedBindId;
	private ModelElementBindSimple selectedBindLabel;
	private ModelElementBindSimple selectedBindEdit;
	private ModelElementBindSimple selectedBindType; // #1510

	// ** #1310

	/**
	 * Instantiates a new renderable s single.
	 * 
	 * @param associationBean
	 *            the association bean
	 * @param selector
	 *            the selector
	 * @param associationClassBean
	 *            the association class bean
	 */
	public RenderableSSingle(AssociationBean associationBean, RenderableSelector selector) {
		super(associationBean);

		add(selector);
		selector.setParent(this);
		selectorBindId = selector.getBindId(); // #1156

		if (associationBean.isItemSelector()) {
			if (associationBean.isDisabled() == false) {
				add(new RenderableSSingleActions(associationBean, selector));
			}
			add(new RenderableSDisplay(associationBean));
			if (associationBean.isShowingActions()) {
				add(new RenderableSEdit(associationBean, false));
			}
		}

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
		Rendered rendered = new RenderedLine();
		Element div = rendered.getXformsElement();

		div.setAttribute("class", getWidgetStyle());

		ModelElementBindSimple bindId = getSelectedBindId();

		String idPath = path.substring(0, path.length() - 1);
		if (bean.isItemSelector()) {
			idPath += "/" + MsgId.INT_INSTANCE_SIDEID;
		}
		bindId.setNodeset(idPath);

		rendered.addModelElement(bindId);

		if (bean.isItemSelector()) {
			ModelElementBindSimple bindLabel = getSelectedBindLabel();
			ModelElementBindSimple bindEdit = getSelectedBindEdit();
			ModelElementBindSimple bindType = getSelectedBindType();
			bindLabel.setNodeset(path + MsgId.INT_INSTANCE_SIDELABEL);
			bindEdit.setNodeset(path + MsgId.INT_INSTANCE_SIDEEDIT);
			bindType.setNodeset(path + MsgId.INT_INSTANCE_SIDETYPE);

			if (bean.isMandatory()) { // #978
				// no visual cues here, but useful for causing XForms validation to fail if nothing 
				// is selected
				// for plain select widget, this is done in RenderableSelectorList.
				bindId.setRequired(true);
				bindLabel.setRequired(true);
				// ** #1156
				String constraint = "instance('minstance')/" + bindId.getNodeset() + " ne ''";
				selectorBindId.setConstraint(constraint);
				// ** #1156
			}

			rendered.addModelElement(bindLabel);
			if (bean.isForField() == false) {
				rendered.addModelElement(bindEdit);
			}
			rendered.addModelElement(bindType);
		}

		return rendered;
	}

	/**
	 * @return the selectedBindId
	 */
	public ModelElementBindSimple getSelectedBindId() {
		if (selectedBindId == null) {
			selectedBindId = new ModelElementBindSimple("");
		}
		return selectedBindId;
	}

	/**
	 * @return the selectedBindLabel
	 */
	public ModelElementBindSimple getSelectedBindLabel() {
		if (selectedBindLabel == null) {
			selectedBindLabel = new ModelElementBindSimple("");
		}
		return selectedBindLabel;
	}

	/**
	 * @return the selectedBindEdit
	 */
	public ModelElementBindSimple getSelectedBindEdit() {
		if (selectedBindEdit == null) {
			selectedBindEdit = new ModelElementBindSimple("");
		}
		return selectedBindEdit;
	}

	/**
	 * @return the selectedBindType
	 */
	public ModelElementBindSimple getSelectedBindType() {
		if (selectedBindType == null) {
			selectedBindType = new ModelElementBindSimple("");
		}
		return selectedBindType;
	}

	/**
	 * @param selectedBindEdit
	 *            the selectedBindEdit to set
	 */
	public void setSelectedBindEdit(ModelElementBindSimple selectedBindEdit) {
		this.selectedBindEdit = selectedBindEdit;
	}

	/* (non-Javadoc)
	 * @see org.blueXML.xforms.generator.forms.Renderable#renderEnd(org.blueXML.xforms.generator.forms.Rendered)
	 */
	@Override
	public void renderEnd(Rendered rendered) {
		super.renderEnd(rendered);
		selectedBindEdit = null;
		selectedBindId = null;
		selectedBindLabel = null;
	}

}
