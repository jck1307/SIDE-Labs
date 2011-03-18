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

import org.apache.commons.lang.StringUtils;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindHolder;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.rendered.RenderedParentGroup;

/**
 * The Class RenderableIMultiple.
 */
public class RenderableIMultiple extends AbstractRenderable {

	ModelElementBindHolder bindRepeater = null; // #1310
	ModelElementBindSimple bindActions = null;

	/**
	 * Instantiates a new renderable i multiple.
	 * 
	 * @param bean
	 *            the bean
	 * @param associationClassBean
	 *            the association class bean
	 */
	public RenderableIMultiple(AssociationBean bean) {
		super(bean);
		add(new RenderableIMultipleRepeater(bean));
		add(new RenderableIMultipleTriggers(bean));
	}

	public ModelElementBindHolder getBindRepeater() {
		if (bindRepeater == null) {
			bindRepeater = new ModelElementBindHolder("");
		}
		return bindRepeater;
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
		// ** #1310
		String finalPath = path;
		String completePath = null;

		Rendered rendered = new RenderedParentGroup(renderedParents);
		if (isInIMultRepeater) {
			completePath = getRootPath(renderedParents);
		}

		if (StringUtils.trimToNull(completePath) != null) {
			finalPath = "instance('minstance')/" + completePath + path;
		}
		// ** #1310

		String nodeSetActions = computeNodeSetActions(finalPath);
		String nodeSetItems = computeNodeSetItems(nodeSetActions);

		// the bind used by actions: e.g. Article1310/field_2/modelcyvel.Paragraphe
		ModelElementBindSimple bindActions = getBindActions();
		bindActions.setNodeset(nodeSetActions);
		bindActions.setRepeaterRootBind(true); // #1241

		ModelElementBindHolder bindRepeater = getBindRepeater(); // we shadow the class field!
		bindRepeater.setNodeset(nodeSetItems);
		rendered.setOptionalData(XFormsGenerator.getId(bean.getName() + "Repeater"));

		rendered.addModelElement(bindRepeater);
		rendered.addModelElement(bindActions);
		rendered.setRepeater(bindRepeater);
		return rendered;
	}

	/**
	 * @return the bindActions
	 */
	public ModelElementBindSimple getBindActions() {
		if (bindActions == null) {
			bindActions = new ModelElementBindSimple("");
		}
		return bindActions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.blueXML.xforms.generator.forms.Renderable#renderEnd(org.blueXML.xforms.generator.forms
	 * .Rendered)
	 */
	@Override
	public void renderEnd(Rendered rendered) {
		super.renderEnd(rendered);
		bindRepeater = null;
	}

}
