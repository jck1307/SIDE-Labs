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


package com.bluexml.xforms.generator.forms.renderable.common.association.selection;

import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.jdom.Element;

import com.bluexml.xforms.generator.forms.FormTypeRendered;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindHolder;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementSubmission;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.multiple.RenderableSMultiple;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.multiple.RenderableSMultipleDisplay;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.unique.RenderableSSingle;
import com.bluexml.xforms.generator.forms.rendered.RenderedInput;
import com.bluexml.xforms.generator.tools.ModelTools;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * The Class RenderableSEdit.
 */
public class RenderableSEdit extends AbstractRenderable {

	@SuppressWarnings("unused")
	private boolean isInMultipleWidget;

	/**
	 * Instantiates a new renderable s edit.
	 * 
	 * @param associationBean
	 *            the association bean
	 */
	public RenderableSEdit(AssociationBean associationBean, boolean isInMultipleWidget) {
		super(associationBean);
		this.isInMultipleWidget = isInMultipleWidget;
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
		return new Path(PathType.relativePath, path);
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

		ModelElementSubmission submission = new ModelElementSubmission("", "", true, false);
		submission.setAlwaysActive(false); // #1222
		if (bean.getCreateEditFormType().equals(FormTypeRendered.formForm)) {
			String listForms = StringUtils.join(bean.getCreateEditForms(), ','); // #1510
			if (listForms == null) {
				// listForms = bean.getCreateEditDefaultFormName() + "/"
				// + MsgId.INT_ACT_SUFFIX_GET_FORM_CLASS;
				// #1637
				listForms = MsgId.INT_ACT_PARAM_ANY_DATATYPE + "="
						+ bean.getCreateEditDefaultFormName() + "&" + MsgId.INT_ACT_PARAM_ANY_HINT
						+ "=" + MsgId.INT_ACT_SUFFIX_GET_FORM_CLASS;
			}
			// String action = MsgId.INT_URI_SCHEME_WRITER.getText() + MsgId.INT_ACT_CODE_EDIT_FORM
			// + "/" + bean.getName() + "/" + listForms;
			// #1637
			String action = MsgId.INT_URI_SCHEME_WRITER.getText() + MsgId.INT_ACT_CODE_EDIT_FORM
					+ "?" + MsgId.INT_ACT_PARAM_ANY_ASSOC + "=" + bean.getName() + "&" + listForms;
			submission.setAction(action);
		} else {
			// String action = MsgId.INT_URI_SCHEME_WRITER.getText() + MsgId.INT_ACT_CODE_EDIT_CLASS
			// + "/" + bean.getName() + "/"
			// + ModelTools.getCompleteName(bean.getDestinationClass());
			// #1637
			String action = MsgId.INT_URI_SCHEME_WRITER.getText() + MsgId.INT_ACT_CODE_EDIT_CLASS
					+ "?" + MsgId.INT_ACT_PARAM_ANY_ASSOC + "=" + bean.getName() + "&"
					+ MsgId.INT_ACT_PARAM_ANY_DATATYPE + "="
					+ ModelTools.getCompleteName(bean.getDestinationClass());
			submission.setAction(action);
		}

		if (getFormGenerator().isInReadOnlyMode() == false) { // #1238
			Element trigger = XFormsGenerator.createElementWithLabel("trigger",
					XFormsGenerator.NAMESPACE_XFORMS, MsgPool.getMsg(MsgId.CAPTION_BUTTON_EDIT));
			Element action = XFormsGenerator.createElement("action",
					XFormsGenerator.NAMESPACE_XFORMS);
			action.setAttribute("event", "DOMActivate", XFormsGenerator.NAMESPACE_EVENTS);

			// setvalue.setAttribute("ref", "instance('minstance')/editedid");
			// String rootPath = getRootPath(renderedParents);
			// String instancePathToSIDEID;
			// if (StringUtils.trimToNull(rootPath) != null) { // we are in a nxn widget
			// instancePathToSIDEID = "instance('minstance')/" + rootPath ;
			// } else { // we are in a nx1 widget
			// instancePathToSIDEID = "instance('minstance')/" + path;
			// }
			// setvalue.setAttribute("value", instancePathToSIDEID + MsgId.INT_INSTANCE_SIDEID);

			Renderable parent = parents.peek();
			ModelElementBindSimple bindEdit;
			if (parent instanceof RenderableSMultipleDisplay) {
				RenderableSMultiple grandpa = (RenderableSMultiple) parents.get(parents.size() - 2);
				ModelElementBindHolder bindRepeater = grandpa.getBindRepeater();
				bindEdit = bindRepeater.getSubBind(2);
			} else { // we're in a Nx1 widget
				bindEdit = ((RenderableSSingle) parent).getSelectedBindEdit();
			}
			Element setvalueEdit = XFormsGenerator.createElement("setvalue",
					XFormsGenerator.NAMESPACE_XFORMS);
			bindEdit.addLinkedElement(setvalueEdit);
			setvalueEdit.setAttribute("value", "../" + MsgId.INT_INSTANCE_SIDEID);
			action.addContent(setvalueEdit);

			Element send = XFormsGenerator.createElement("send", XFormsGenerator.NAMESPACE_XFORMS);
			submission.addLinkedElement(send);
			action.addContent(send);

			trigger.addContent(action);
			rendered.setXformsElement(trigger);
		}
		rendered.addModelElement(submission);

		return rendered;
	}

	/* (non-Javadoc)
	 * @see com.bluexml.xforms.generator.forms.Renderable#getDivStyle()
	 */
	@Override
	public String getDivStyle() {
		return MsgId.INT_CSS_SELECT_TRIGGER_BUTTON.getText();
	}

}
