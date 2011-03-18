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


package com.bluexml.xforms.generator.forms.renderable.forms;

import java.util.Stack;

import org.apache.commons.lang.StringUtils;

import com.bluexml.side.common.ModelElement;
import com.bluexml.side.form.ActionField;
import com.bluexml.side.workflow.Transition;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementSubmission;
import com.bluexml.xforms.generator.forms.renderable.common.RenderableSubmit;
import com.bluexml.xforms.generator.forms.rendered.RenderedParentGroup;
import com.bluexml.xforms.messages.MsgId;

public class RenderableActionField extends Renderable {

	private ModelElementSubmission submission;
	private String label;

	private final boolean DEFAULT_VALIDATE_FIRST = true;

	public RenderableActionField(XFormsGenerator generationManager, ActionField formElement) {

		super();
		/*
		 * Ds un wrkflw, une transition de name "Relire" et de label
		 * "Envoyer à la relecture" nous donne un id="transition/Relire". En
		 * dehors d'un workflow, l'id du bouton est "Relire". Dans tous les cas,
		 * "Envoyer à la relecture" est le texte affiché sur le bouton.
		 */
		ModelElement mel = formElement.getRef();
		if (mel != null) {
			mel = (ModelElement) generationManager.getFormGenerator().getRealObject(mel);
		}
		String actionName;
		String infixe;

		if ((mel != null) && (mel instanceof Transition)) { // this is a
			// workflow
			// transition button
			actionName = formElement.getId();
			infixe = MsgId.INT_ACT_CODE_WRKFLW_TRANSITION.getText();
		} else if (formElement.getId().equals(MsgId.INT_ACT_CODE_WRKFLW_SAVE.getText())) {
			actionName = "save"; // not used
			infixe = MsgId.INT_ACT_CODE_WRKFLW_SAVE.getText();
		} else { // this is a user-added action button
			actionName = StringUtils.trim(formElement.getAction_handler());
			infixe = MsgId.INT_ACT_CODE_EXECUTE.getText();
		}
		label = formElement.getLabel();
		// submission = new ModelElementSubmission(MsgId.INT_URI_SCHEME_WRITER +
		// infixe + "/"
		// + actionName, label, true, DEFAULT_VALIDATE_FIRST);
		String action = MsgId.INT_URI_SCHEME_WRITER + infixe + "?" + MsgId.INT_ACT_PARAM_EXEC_ACTION + "=" + actionName;
		submission = new ModelElementSubmission(action, label, true, DEFAULT_VALIDATE_FIRST);
		RenderableSubmit button = new RenderableSubmit(submission, label, false);
		add(button);
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents, Stack<Rendered> renderedParents) {

		return ROOT_RELATIVE;
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents, boolean isInIMultRepeater) {

		RenderedParentGroup renderedParentGroup = new RenderedParentGroup(renderedParents);
		renderedParentGroup.getParent().addModelElement(submission);
		return renderedParentGroup;
	}

}
