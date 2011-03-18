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
package com.bluexml.xforms.generator.forms.renderable.forms.search;

import com.bluexml.side.form.ChoiceSearchField;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.SearchField;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableSearchField;
import com.bluexml.xforms.messages.MsgId;

/**
 * The widget for ChoiceSearchField's.
 * 
 * @author Amenel
 * 
 */
public class RenderableChoiceSearchField extends RenderableSearchField<ChoiceSearchField> {

	public RenderableChoiceSearchField(XFormsGenerator generationManager, FormElement parent,
			SearchField formElement) {
		super(generationManager, parent, formElement);

		RenderableSearchInput renderable = null;
		// add the elements of the widget
		add(new RenderableSearchOperators(this));
		renderable = new RenderableSearchInput(this, MsgId.INT_INSTANCE_SEARCH_VALUE.getText());
		renderable.setSelect(true); // necessary for rendering as a select list
		add(renderable);
	}

}
