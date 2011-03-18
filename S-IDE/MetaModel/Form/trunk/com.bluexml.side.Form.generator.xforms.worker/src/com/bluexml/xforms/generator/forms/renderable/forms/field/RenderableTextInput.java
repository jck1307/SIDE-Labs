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


package com.bluexml.xforms.generator.forms.renderable.forms.field;

import java.util.Stack;

import org.jdom.Element;

import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.TextField;
import com.bluexml.side.form.TextWidgetType;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableField;

public class RenderableTextInput extends RenderableField<TextField> {

	public RenderableTextInput(XFormsGenerator generationManager, FormElement parent,
			TextField formElement) {
		super(generationManager, parent, formElement);
	}

	@Override
	protected Element getCustomElement(Rendered rendered, ModelElementBindSimple meb,
			String slabel, Stack<Renderable> parents, Stack<Rendered> renderedParents) {
		boolean isRichTextEditor = false;
		if (formElement.getWidget() == TextWidgetType.RICH_TEXT_EDITOR) {
			isRichTextEditor = true;
		}
		return getTextAreaElement(meb, slabel, isRichTextEditor);
	}

	@Override
	protected String getXsdType() {
		return "string";
	}

}
