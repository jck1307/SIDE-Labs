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


package com.bluexml.xforms.generator.forms.rendered;

import java.util.ArrayList;
import java.util.List;

import com.bluexml.xforms.messages.MsgId;
import org.jdom.Element;

import com.bluexml.xforms.generator.forms.FormTypeRendered;
import com.bluexml.xforms.generator.forms.ModelElement;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;

/**
 * The Class RenderedForm.
 */
public class RenderedForm extends Rendered {

	/** The group form. */
	private Element body;

	/** The xmodel. */
	private Element model;

	/**
	 * Instantiates a new rendered form.
	 * 
	 * @param title
	 *            the title
	 * @param formType
	 */
	public RenderedForm(String title, FormTypeRendered formType) {
		setRootContainer(this);
		xformsElement = XFormsGenerator.createElement("html", XFormsGenerator.NAMESPACE_XHTML);
		Element head = XFormsGenerator.createElement("head", XFormsGenerator.NAMESPACE_XHTML);

		Element titleElement = XFormsGenerator.createElementWithContent("title", XFormsGenerator.NAMESPACE_XHTML, title);
		head.addContent(titleElement);

		// add meta to force IE >7 to work with IE8 renderer
		Element ie8Element = XFormsGenerator.createElement("meta", XFormsGenerator.NAMESPACE_XHTML);
		// http-equiv="X-UA-Compatible" content="IE=8"
		ie8Element.setAttribute("http-equiv", "X-UA-Compatible");
		ie8Element.setAttribute("content", "IE=8");
		head.addContent(ie8Element);

		model = XFormsGenerator.createElement("model", XFormsGenerator.NAMESPACE_XFORMS);
		model.setAttribute("id", MsgId.INT_GEN_ID_OBJECTMODEL.getText());
		Element modelInstance = XFormsGenerator.createElement("instance", XFormsGenerator.NAMESPACE_XFORMS);
		modelInstance.setAttribute("id", "minstance");

		// build the URI for the Get action
		MsgId getAction = MsgId.INT_ACT_CODE_GET_FORM;
		String suffix = "";
		if (formType.equals(FormTypeRendered.formWkflw)) {
			suffix = MsgId.INT_ACT_SUFFIX_GET_FORM_WKFLW.getText();
		} else if (formType.equals(FormTypeRendered.formClass)) {
			suffix = MsgId.INT_ACT_SUFFIX_GET_FORM_CLASS.getText();
		} else if (formType.equals(FormTypeRendered.formClassList)) {
			suffix = MsgId.INT_ACT_SUFFIX_GET_FORM_LIST.getText();
		} else if (formType.equals(FormTypeRendered.formClassSubClassSelector)) {
			suffix = MsgId.INT_ACT_SUFFIX_GET_FORM_SELECTOR.getText();
		} else if (formType.equals(FormTypeRendered.formForm)) {
			suffix = MsgId.INT_ACT_SUFFIX_GET_FORM_FORM.getText();
		} else if (formType.equals(FormTypeRendered.formSearch)) {
			suffix = MsgId.INT_ACT_SUFFIX_GET_FORM_SEARCH.getText();
		}
		// String source = MsgId.INT_URI_SCHEME_READER + getAction.getText() + "/" + suffix;
		String source = MsgId.INT_URI_SCHEME_READER + getAction.getText() + "?" + MsgId.INT_ACT_PARAM_GET_FORMTYPE + "=" + suffix; // #1637
		modelInstance.setAttribute("src", source);

		model.addContent(modelInstance);

		head.addContent(model);

		Element tabContainerScript = XFormsGenerator.createElement("script", XFormsGenerator.NAMESPACE_XHTML);
		tabContainerScript.setAttribute("type", "text/javascript");
		StringBuffer stringBuffer = new StringBuffer("");
		stringBuffer.append("dojo.require(\"dojo.widget.LayoutContainer\");");
		stringBuffer.append("dojo.require(\"dojo.widget.TabContainer\");");
		stringBuffer.append("dojo.require(\"dojo.widget.ContentPane\");");
		// uncomment when using a more recent version of Dojo than the 0.4 in Chiba 2.3
		// stringBuffer.append("dojo.require(\"dojo.parser\");");

		tabContainerScript.setText(stringBuffer.toString());
		head.addContent(tabContainerScript);

		addCustomJS(head);

		addCSS(head, MsgId.INT_GEN_PLACEHOLDER_CONTEXT_PATH + "/resources/styles/xforms.generated.css");
		addCSS(head, MsgId.INT_GEN_PLACEHOLDER_CONTEXT_PATH + "/resources/styles/custom.css");

		xformsElement.addContent(head);
		body = XFormsGenerator.createElement("body", XFormsGenerator.NAMESPACE_XHTML);
		xformsElement.addContent(body);

	}

	/**
	 * Adds the css.
	 * 
	 * @param head
	 *            the head
	 * @param css
	 *            the css
	 */
	private void addCSS(Element head, String css) {
		Element customCSS = XFormsGenerator.createElement("link", XFormsGenerator.NAMESPACE_XHTML);
		customCSS.setAttribute("rel", "stylesheet");
		customCSS.setAttribute("type", "text/css");
		customCSS.setAttribute("href", css);
		head.addContent(customCSS);
	}

	/**
	 * Adds a link to the custom.js script file.
	 * 
	 * @param head
	 *            the head element of the page
	 */
	private void addCustomJS(Element head) {
		Element linkFile = XFormsGenerator.createElement("script", XFormsGenerator.NAMESPACE_XHTML);
		linkFile.setAttribute("type", "text/javascript");
		linkFile.setAttribute("src", "resources/scripts/custom.js");
		head.addContent(linkFile);

		Element execNow = XFormsGenerator.createElement("script", XFormsGenerator.NAMESPACE_XHTML);
		execNow.setAttribute("type", "text/javascript");
		StringBuffer code = new StringBuffer("\n");
		code.append("if (window.addEventListener) { ");
		code.append("window.addEventListener('load', side.init, false); ");
		code.append("} else if (document.addEventListener) { ");
		code.append("document.addEventListener('load', side.init, false); ");
		code.append("} else if (window.attachEvent) { ");
		code.append("window.attachEvent('onLoad', side.init); ");
		code.append("}");
		execNow.setText(code.toString());
		head.addContent(execNow);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * 
	 * com.bluexml.xforms.generator.forms.Rendered#addRendered(com.bluexml.xforms
	 * .generator.forms.Rendered, com.bluexml.xforms.generator.forms.Renderable)
	 */
	@Override
	public void addRendered(Rendered rendered, Renderable renderable) {
		super.addRendered(rendered, renderable);
		Element renderedElement = rendered.getXformsElement();
		if (renderedElement != null) {
			body.addContent(renderedElement);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.generator.forms.Rendered#isHolder()
	 */
	@Override
	public boolean isHolder() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.bluexml.xforms.generator.forms.Rendered#renderEnd(com.bluexml.xforms
	 * .generator.forms.Renderable)
	 */
	@Override
	public void renderEnd() {
		List<ModelElement> allModelElements = new ArrayList<ModelElement>(getModelElements());
		List<ModelElement> allModelElementsClean = new ArrayList<ModelElement>();
		// filterModelElements(allModelElements);
		List<Element> modelElementsRendered = new ArrayList<Element>();
		for (ModelElement modelElement : allModelElements) {
			if (!modelElement.hasClone(allModelElementsClean)) {
				allModelElementsClean.add(modelElement);
			}
		}
		for (ModelElement modelElement : allModelElementsClean) {
			Element modelEle = modelElement.getModelElement();
			if (modelEle != null) {
				modelElementsRendered.add(modelEle);
				// addModelRendered(modelElementsRendered, modelEle,
				// modelElement);
			}
		}

		for (Element element : modelElementsRendered) {
			model.addContent(element);
		}
		setRootContainer(null);
	}

}
