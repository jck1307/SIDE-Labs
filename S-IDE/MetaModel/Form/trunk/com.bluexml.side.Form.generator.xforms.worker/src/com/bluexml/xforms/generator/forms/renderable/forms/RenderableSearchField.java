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
package com.bluexml.xforms.generator.forms.renderable.forms;

import java.util.Stack;

import com.bluexml.side.form.BooleanSearchField;
import com.bluexml.side.form.CharSearchField;
import com.bluexml.side.form.ChoiceSearchField;
import com.bluexml.side.form.DateSearchField;
import com.bluexml.side.form.FileSearchField;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.NumericalSearchField;
import com.bluexml.side.form.SearchField;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.forms.search.RenderableBooleanSearchField;
import com.bluexml.xforms.generator.forms.renderable.forms.search.RenderableCharSearchField;
import com.bluexml.xforms.generator.forms.renderable.forms.search.RenderableChoiceSearchField;
import com.bluexml.xforms.generator.forms.renderable.forms.search.RenderableDateSearchField;
import com.bluexml.xforms.generator.forms.renderable.forms.search.RenderableFileSearchField;
import com.bluexml.xforms.generator.forms.renderable.forms.search.RenderableNumericalSearchField;
import com.bluexml.xforms.generator.forms.rendered.search.RenderedSearchField;

/**
 * @author Amenel
 * 
 */
public abstract class RenderableSearchField<F extends SearchField> extends
		RenderableFormElement<SearchField> {

	public static Renderable getRenderable(XFormsGenerator generationManager, FormElement parent,
			SearchField formElement) {
		Renderable renderable = null;
		if (formElement instanceof CharSearchField) {
			return new RenderableCharSearchField(generationManager, parent, formElement);
		} else if (formElement instanceof DateSearchField) {
			return new RenderableDateSearchField(generationManager, parent, formElement);
		} else if (formElement instanceof NumericalSearchField) {
			return new RenderableNumericalSearchField(generationManager, parent, formElement);
		} else if (formElement instanceof ChoiceSearchField) {
			return new RenderableChoiceSearchField(generationManager, parent, formElement);
		} else if (formElement instanceof FileSearchField) {
			return new RenderableFileSearchField(generationManager, parent, formElement);
		} else if (formElement instanceof BooleanSearchField) {
			return new RenderableBooleanSearchField(generationManager, parent, formElement);
		}
		return renderable;
	}

	public RenderableSearchField(XFormsGenerator generationManager, FormElement parent,
			SearchField formElement) {
		super(generationManager, parent, formElement);
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
		return new Path(PathType.relativePath, formElement.getId() + "/");
	}

	
	/* (non-Javadoc)
	 * @see com.bluexml.xforms.generator.forms.renderable.forms.RenderableFormElement#compute()
	 */
	@Override
	public void compute() {
		// nothing to do: search fields are not supposed to have children.
	}

	/* (non-Javadoc)
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack, java.util.Stack, boolean)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		Rendered rendered = new RenderedSearchField();
		applyStyle(rendered);
		return rendered;
	}

	protected void applyStyle(Rendered rendered) {
		applyStyle(rendered, formElement.getStyle());
	}

}
