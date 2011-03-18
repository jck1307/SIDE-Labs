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
package com.bluexml.xforms.generator.forms.renderable.forms.field;

import java.util.Stack;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindHolder;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.rendered.RenderedXMLElement;
import com.bluexml.xforms.messages.MsgId;

/**
 * The repeater output for the "standard text inputs with 'multiple' property" widget.
 * 
 * @author Amenel
 * 
 */
public class RenderableSimpleInputMultipleOutput extends Renderable {

	private RenderableSimpleInputMultiple<?> parent;

	/**
	 * @param parent
	 */
	public RenderableSimpleInputMultipleOutput(RenderableSimpleInputMultiple<?> parent) {
		this.parent = parent;
	}

	/* (non-Javadoc)
	 * @see com.bluexml.xforms.generator.forms.Renderable#getPath(java.lang.String, java.util.Stack, java.util.Stack)
	 */
	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		String path = "";
		return new Path(PathType.absolutePath, path);
	}

	/* (non-Javadoc)
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack, java.util.Stack, boolean)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {

		RenderedXMLElement rendered = new RenderedXMLElement();

		// create the nodes
		Element rootElt = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		rootElt.setAttribute("class", "xformstdleft " + MsgId.INT_CSS_INPUT_MULTIPLE_ITEMS);
		Element repeatElt = XFormsGenerator.createElement("repeat",
				XFormsGenerator.NAMESPACE_XFORMS);
		repeatElt.setAttribute("id", parent.getRepeaterId());
		repeatElt.setAttribute("appearance", "full");
		Element outputElt = XFormsGenerator.createElement("output",
				XFormsGenerator.NAMESPACE_XFORMS);

		// set the links between elements and binds
		ModelElementBindHolder repeatBind = parent.getRepeatBind();
		ModelElementBindSimple itemBind = repeatBind.getSubBind(0);
		itemBind.addLinkedElement(outputElt);
		repeatBind.addLinkedElement(repeatElt);

		// build the correct nesting of elements
		repeatElt.addContent(outputElt);
		rootElt.addContent(repeatElt);
		rendered.setXMLElement(rootElt);

		return rendered;
	}

}
