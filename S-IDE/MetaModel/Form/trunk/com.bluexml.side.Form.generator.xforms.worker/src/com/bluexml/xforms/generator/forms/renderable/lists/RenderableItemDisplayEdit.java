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


package com.bluexml.xforms.generator.forms.renderable.lists;

import java.util.Stack;

import com.bluexml.xforms.messages.MsgId;
import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementSubmission;
import com.bluexml.xforms.generator.forms.rendered.RenderedInput;
import com.bluexml.xforms.generator.tools.ModelTools;

public class RenderableItemDisplayEdit extends Renderable {

	private RenderableClassList renderableClassList;

	public RenderableItemDisplayEdit(RenderableClassList renderableClassList) {
		super();
		this.renderableClassList = renderableClassList;
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return ROOT_RELATIVE;
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		RenderedInput rendered = new RenderedInput();

		ModelElementSubmission submission = new ModelElementSubmission("", "", true, false);
		submission.setAlwaysActive(false); // #1222
		// String action = MsgId.INT_URI_SCHEME_WRITER + "edit//"
		// + ModelTools.getCompleteName(renderableClassList.getClasse());
		String action = MsgId.INT_URI_SCHEME_WRITER + "edit?" + MsgId.INT_ACT_PARAM_ANY_ASSOC + "="
				+ "&" + MsgId.INT_ACT_PARAM_ANY_DATATYPE + "="
				+ ModelTools.getCompleteName(renderableClassList.getClasse()); // #1637
		submission.setAction(action);

		Element trigger = XFormsGenerator.createElementWithLabel("trigger",
				XFormsGenerator.NAMESPACE_XFORMS, "Modifier");
		Element actionElt = XFormsGenerator.createElement("action",
				XFormsGenerator.NAMESPACE_XFORMS);
		actionElt.setAttribute("event", "DOMActivate", XFormsGenerator.NAMESPACE_EVENTS);

		Element setvalue = XFormsGenerator.createElement("setvalue",
				XFormsGenerator.NAMESPACE_XFORMS);
		setvalue.setAttribute("ref", "instance('minstance')/editedid");
		// String rootPath = getRootPath(renderedParents);
		setvalue.setAttribute("value", "instance('instanceList')/item[index('itemRepeater')]/id");
		actionElt.addContent(setvalue);

		Element send = XFormsGenerator.createElement("send", XFormsGenerator.NAMESPACE_XFORMS);
		submission.addLinkedElement(send);
		actionElt.addContent(send);

		trigger.addContent(actionElt);
		rendered.setXformsElement(trigger);

		rendered.addModelElement(submission);

		return rendered;
	}

}
