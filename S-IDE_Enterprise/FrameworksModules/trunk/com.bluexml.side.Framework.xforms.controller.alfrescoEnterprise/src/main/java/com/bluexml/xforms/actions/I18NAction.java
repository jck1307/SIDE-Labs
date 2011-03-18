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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bluexml.xforms.controller.navigation.NavigationSessionListener;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.tools.SortedProperties;

/**
 * The Class I18NAction.<br>
 * @deprecated because another mechanism exists for enabling multi-language feature.
 */
@Deprecated
public class I18NAction extends AbstractReadAction {

	/** The Constant DATA_LANGUAGE. */
	public static final String DATA_LANGUAGE = "language";

	/** The strings. */
	private static Document strings;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {
		return MsgId.INT_ACT_CODE_I18N.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getParamNames()
	 */
	@Override
	protected String[] getParamNames() {
		return new String[] { DATA_LANGUAGE };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#executeResolve()
	 */
	@Override
	public Node resolve() throws ServletException {
		if (strings == null) {
			Properties properties = new SortedProperties();
			try {
				properties.load(NavigationSessionListener.getContext().getResourceAsStream(
						"/WEB-INF/classes/strings.properties"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				String msg = "Failed to load strings.properties";
				logger.error(msg);
				throw new ServletException(msg);
			}
			ArrayList<Object> keys = Collections.list(properties.keys());

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = null;
			try {
				db = dbf.newDocumentBuilder();
			} catch (ParserConfigurationException pce) {
				assert (false);
				return null;
			}
			strings = db.newDocument();
			Element rootElement = (Element) strings
					.appendChild(strings.createElement("properties"));

			for (Object object : keys) {
				String key = (String) object;
				String[] split = key.split("\\.");
				List<String> parts = Arrays.asList(split);
				addValue(parts, rootElement, (String) properties.get(object));
			}
		}
		return strings;
	}

	/**
	 * Adds the value.
	 * 
	 * @param parts
	 *            the parts
	 * @param parent
	 *            the parent
	 * @param value
	 *            the value
	 */
	private void addValue(List<String> parts, Element parent, String value) {
		String tagName = parts.get(0);
		if (parts.size() == 1) {
			Element newElement = (Element) parent.appendChild(strings.createElement(tagName));
			newElement.setTextContent(value);
		} else {
			Element childElement = null;

			NodeList childNodes = parent.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node item = childNodes.item(i);
				if (item instanceof Element) {
					Element element = (Element) item;
					if (element.getTagName().equals(tagName)) {
						childElement = element;
					}
				}
			}

			if (childElement == null) {
				childElement = (Element) parent.appendChild(strings.createElement(tagName));
			}
			List<String> newParts = new ArrayList<String>(parts);
			newParts.remove(0);
			addValue(newParts, childElement, value);
		}
	}

}
