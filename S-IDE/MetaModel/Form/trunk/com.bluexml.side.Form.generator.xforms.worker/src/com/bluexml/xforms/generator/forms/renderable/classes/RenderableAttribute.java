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


package com.bluexml.xforms.generator.forms.renderable.classes;

import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;
import org.jdom.Element;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.SelectBean;
import com.bluexml.xforms.generator.forms.renderable.common.field.AbstractRenderableField;
import com.bluexml.xforms.generator.forms.rendered.RenderedXMLElement;
import com.bluexml.xforms.generator.tools.ModelTools;

/**
 * The Class RenderableAttribute.
 */
public class RenderableAttribute extends AbstractRenderableField {

	/** The owner. */
	private AbstractClass owner;

	/** The attribute. */
	private Attribute attribute;

	/**
	 * Instantiates a new renderable attribute.
	 * 
	 * @param owner
	 *            the owner
	 * @param attribute
	 *            the attribute
	 */
	public RenderableAttribute(AbstractClass owner, Attribute attribute) {
		super();
		this.owner = owner;
		this.attribute = attribute;
	}

	/**
	 * Gets the meta info value.
	 * 
	 * @param key
	 *            the key
	 * 
	 * @return the meta info value
	 */
	private String getMetaInfoValue(String key) {

		return ModelTools.getMetaInfoValue(attribute, key);
	}

	/**
	 * Checks if is the attribute refers to a file field.
	 * 
	 * @return true, if is file field
	 */
	private boolean isFileField() {
		return (StringUtils.equals(attribute.getName(), "content") || StringUtils.equals(attribute
				.getName(), "repositoryContent"));
	}

	/**
	 * Checks if is the attribute refers to an image file field.
	 * 
	 * @return true, if is an image file field
	 */
	private boolean isImageFileField() {
		return (StringUtils.equals(attribute.getName(), "content_img") || StringUtils.equals(attribute
				.getName(), "repositoryContent_img"));
	}
	
	private boolean isDateTimeField() {
		return StringUtils.equals(getXsdType(), "dateTime");
	}

	/**
	 * Checks if is text area.
	 * 
	 * @return true, if is text area
	 */
	private boolean isTextArea() {
		return (getMetaInfoValue("rte-rows") != null || getMetaInfoValue("textarea-rows") != null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.renderable.common.field.
	 * AbstractRenderableField#getOwner()
	 */
	@Override
	protected String getOwner() {
		return ModelTools.getCompleteNameJAXB(owner);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.renderable.common.field.
	 * AbstractRenderableField#getName()
	 */
	@Override 
	protected String getName() {
		return attribute.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.renderable.common.field.
	 * AbstractRenderableField#getTitle()
	 */
	@Override
	protected String getTitle() {
		return ModelTools.getTitle(attribute);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.renderable.common.field.
	 * AbstractRenderableField#isRequired()
	 */
	@Override
	protected boolean isRequired() {
		return StringUtils.equalsIgnoreCase(getMetaInfoValue("required"), "true");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.renderable.common.field.
	 * AbstractRenderableField#isReadOnly()
	 */
	@Override
	protected boolean isReadOnly() {
		return getFormGenerator().isInReadOnlyMode()
				|| ModelTools.isProperty(attribute, "read-only");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.renderable.common.field. AbstractRenderableField
	 * #getCustomElement(com.bluexml.xforms.generator.forms.Rendered,
	 * com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple, java.lang.String,
	 * java.util.Stack, java.util.Stack)
	 */
	@Override
	protected Element getCustomElement(Rendered rendered, ModelElementBindSimple meb,
			String slabel, Stack<Renderable> parents, Stack<Rendered> renderedParents) {

		Element element = null;
		boolean isMultiple;

		if (attribute.getValueList() != null) {
			isMultiple = StringUtils.equalsIgnoreCase(getMetaInfoValue("multiple"), "true");
			element = getSelectElement(rendered, new SelectBean(meb, slabel, attribute
					.getValueList(), isMultiple));
		} else if (isTextArea()) {
			boolean richTextEditor = (getMetaInfoValue("rte-rows") != null);
			element = getTextAreaElement(meb, slabel, richTextEditor);
		} else if (isImageFileField()) {
			element = getFileElement(meb, slabel, MsgId.INT_FILEFIELD_PREVIEW_IMAGE);
		} else if (isFileField()) {
			element = getFileElement(meb, slabel, MsgId.INT_FILEFIELD_PREVIEW_NONE);
		} else if (isDateTimeField()) {
			element = getDateTimeElement(meb, slabel);
		} else {
			element = getStandardElement(meb, slabel);
		}

		return element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.renderable.common.field. AbstractRenderableField
	 * #applyConstraints(com.bluexml.xforms.generator.forms .modelelement.ModelElementBindSimple)
	 */
	@Override
	protected void applyConstraints(ModelElementBindSimple meb) {
		if (getMetaInfoValue("email") != null) {
			setConstraintMail(meb);
		} else {
			String regularExpression = getMetaInfoValue("regular-expression");
			if (regularExpression != null) {
				setConstraintRegexp(meb, regularExpression);
			}
		}
		setLength(meb, getMetaInfoValue("min-length"), getMetaInfoValue("max-length"));
		setRequired(meb, StringUtils.equalsIgnoreCase(getMetaInfoValue("required"), "true"));
		setHidden(meb, StringUtils.equalsIgnoreCase(getMetaInfoValue("hidden"), "true"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.renderable.common.field.
	 * AbstractRenderableField#getXsdType()
	 */
	@Override
	protected String getXsdType() {
		String xsdType = "string";
		if (isFileField()) {
			xsdType = "anyURI";
		} else {
			xsdType = ModelTools.toXSDType(attribute.getTyp());
		}
		return xsdType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.renderable.common.field.
	 * AbstractRenderableField#getHint()
	 */
	@Override
	protected String getHint() {
		return attribute.getDescription();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.renderable.common.field.
	 * AbstractRenderableField#getErrorMessage()
	 */
	@Override
	protected String getErrorMessage() {
		String errMsg = "";
		if ((getMinLength() != null) && (getMaxLength() != null)) {
			errMsg = MsgPool.getMsg(MsgId.MSG_LENGTH_BETWEEN, getMinLength(), getMaxLength(), getTitle());
		} else if (getMinLength() != null) {
			errMsg = MsgPool.getMsg(MsgId.MSG_LENGTH_MINIMAL, getMinLength(), getTitle());
		} else if (getMaxLength() != null) {
			errMsg = MsgPool.getMsg(MsgId.MSG_LENGTH_MAXIMAL, getMaxLength(), getTitle());
		}
		return errMsg;
	}

	@Override
	protected void applyStyle(RenderedXMLElement rendered) {
		// nothing to do for attributes. No styles are specified in class diagrams.
	}

}
