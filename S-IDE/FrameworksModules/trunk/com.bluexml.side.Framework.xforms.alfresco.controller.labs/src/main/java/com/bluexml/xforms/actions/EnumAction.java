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


package com.bluexml.xforms.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class EnumAction.<br/>
 * Retrieves a list of items for an enumeration.<br/>
 */
public class EnumAction extends AbstractReadAction {

	/** The Constant enums. */
	private static final Map<String, Document> enums = new HashMap<String, Document>();

	// should be replaced by a double linked hash map
	private static final Map<String, Map<String, String>> enumValues = new HashMap<String, Map<String, String>>();
	private static final Map<String, Map<String, String>> enumKeys = new HashMap<String, Map<String, String>>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {
		return MsgId.INT_ACT_CODE_ENUM.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getParamNames()
	 */
	@Override
	protected String[] getParamNames() {
		return new String[] {
				MsgId.INT_ACT_PARAM_ENUM_RAWTYPE.getText(),
				MsgId.INT_ACT_PARAM_ENUM_FILTER_PARENT.getText(),
				MsgId.INT_ACT_PARAM_ENUM_FILTER_DATA.getText(),
				MsgId.INT_ACT_PARAM_ENUM_LIMITED.getText() };
	}

	/**
	 * Gets the enum.
	 * 
	 * @param type
	 *            the type
	 * @param filterData
	 *            the filter data
	 * @param filterParent
	 *            the filter parent
	 * @param query
	 * @return the enum
	 * @throws ServletException
	 *             the servlet exception
	 * @throws ServletException
	 *             the exception
	 */
	protected Node getEnum(String type, String filterParent, String filterData, String query,
			boolean limited) throws ServletException {
		Node result = null;
		boolean dynamic = controller.isDynamicEnum(type);
		if (dynamic) {
			result = controller.getDynamicEnum(transaction, type, filterParent, filterData, query,
					limited);
		} else {
			Map<String, String> initParams = navigationPath.peekCurrentPage().getInitParams();
			result = getStaticEnum(type, initParams);
		}
		return result;
	}

	/**
	 * Gets the static enum.
	 * 
	 * @param type
	 *            the type
	 * 
	 * @return the static enum
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 */
	private static synchronized Document getStaticEnum(String type, Map<String, String> initParams)
			throws ServletException {
		Document result;
		result = enums.get(type);
		if (result == null) {
			try {
				String baseFolderPath = AlfrescoController.getInstance().getParamBaseFolder(
						initParams) + File.separator + "forms";

				String filename = baseFolderPath + File.separator + MsgId.INT_DIRECTORY_ENUMS
						+ File.separator + type + ".enum.xml";
				InputStream xformsStream = new FileInputStream(filename);
				// InputStream xformsStream =
				// NavigationSessionListener.getContext().getResourceAsStream(
				// "/forms/" + MsgId.INT_DIRECTORY_ENUMS + "/" + type + ".enum.xml");
				result = DOMUtil.getDocumentFromStream(xformsStream);
				fillValues(type, result);
			} catch (Exception e) {
				throw new ServletException(e);
			}
			enums.put(type, result);
		}
		return result;
	}

	private static void fillValues(String type, Document doc) {
		Element root = doc.getDocumentElement();

		Map<String, String> values = new HashMap<String, String>();
		Map<String, String> keys = new HashMap<String, String>();

		NodeList childNodes = root.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = childNodes.item(i);
			if (child instanceof Element) {
				Element element = (Element) child;
				addValue(element, values, keys);
			}
		}

		enumValues.put(type, values);
		enumKeys.put(type, keys);
	}

	private static void addValue(Element element, Map<String, String> values,
			Map<String, String> keys) {
		String id = null;
		String value = null;

		NodeList childNodes = element.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = childNodes.item(i);
			if (child instanceof Element) {
				Element e = (Element) child;
				if (StringUtils.equals(e.getTagName(), "id")) {
					id = e.getTextContent();
				}
				if (StringUtils.equals(e.getTagName(), "value")) {
					value = e.getTextContent();
				}
			}
		}
		if (id != null && value != null) {
			values.put(id, value);
			keys.put(value, id);
		}
	}

	public static synchronized String getEnumValue(String type, String key,
			Map<String, String> initParams) {
		try {
			getStaticEnum(type, initParams);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e);
			}
		}
		return enumValues.get(type).get(key);
	}

	public static synchronized String getEnumKey(String type, String value,
			Map<String, String> initParams) {
		try {
			getStaticEnum(type, initParams);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e);
			}
		}
		return enumKeys.get(type).get(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#executeResolve()
	 */
	@Override
	public Node resolve() throws ServletException {
		String dataType = requestParameters.get(MsgId.INT_ACT_PARAM_ENUM_RAWTYPE.getText());
		String filterPrnt = requestParameters.get(MsgId.INT_ACT_PARAM_ENUM_FILTER_PARENT.getText());
		String filterData = requestParameters.get(MsgId.INT_ACT_PARAM_ENUM_FILTER_DATA.getText());

		return getEnum(dataType, filterPrnt, filterData, null, isLimited());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#executeSubmit()
	 */
	// TODO: remove this function
	// @SuppressWarnings("deprecation")
	// @Override
	// public void submit() throws ServletException {
	//		
	// // update list using search
	// Document doc = (Document) node;
	// String query = doc.getDocumentElement().getTextContent();
	//
	// String dataType = requestParameters.get(RAW_DATA_TYPE);
	// String filterParent = requestParameters.get(FILTER_PARENT);
	// String filterData = requestParameters.get(FILTER_DATA);
	//
	// // retrieves elements
	// Node list = getEnum(dataType, filterParent, filterData, query, isLimited());
	//
	// // convert to string
	// Source xmlSource = new DOMSource(list);
	// ByteArrayOutputStream pos = new ByteArrayOutputStream();
	// Result outputTarget = new StreamResult(pos);
	// documentTransformer.transform(xmlSource, outputTarget);
	//
	// ByteArrayInputStream pis = new ByteArrayInputStream(pos.toByteArray());
	//
	// result.put(XFormsProcessor.SUBMISSION_RESPONSE_STREAM, pis);
	// }

	private boolean isLimited() {
		String limited = requestParameters.get(MsgId.INT_ACT_PARAM_ENUM_LIMITED.getText());
		return StringUtils.equals(limited, "1");
	}

}
