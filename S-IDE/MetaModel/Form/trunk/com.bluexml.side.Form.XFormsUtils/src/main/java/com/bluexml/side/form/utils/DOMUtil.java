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
package com.bluexml.side.form.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Amenel
 * 
 */
public class DOMUtil {

	private static Transformer documentTransformer = null;
	private static DocumentBuilder documentBuilder = null;
	private static Log logger = LogFactory.getLog(DOMUtil.class);

	/**
	 * Gets a DocumentTransformer object (in fact, a singleton), which is created if non existent.
	 * 
	 * @return
	 */
	public static Transformer getDocumentTransformer() {
		if (documentTransformer == null) {
			try {
				documentTransformer = TransformerFactory.newInstance().newTransformer();
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
				return null;
			} catch (TransformerFactoryConfigurationError e) {
				e.printStackTrace();
				return null;
			}
			documentTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
		}
		return documentTransformer;
	}

	/**
	 * Gets a namespace aware non-validating DocumentBuilder object. If non existent, the object is
	 * created.
	 * 
	 * @return
	 */
	public static DocumentBuilder getDocumentBuilder() {
		if (documentBuilder == null) {
			try {
				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
				docBuilderFactory.setNamespaceAware(true); // @since 1.0.2
				docBuilderFactory.setValidating(false); // @since 1.0.2

				documentBuilder = docBuilderFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
				return null;
			}
		}
		return documentBuilder;
	}

	/**
	 * Logs a DOM node with indentation.
	 * 
	 * @param node
	 * @param printToSystemOut
	 * @param messages
	 */
	public static String logXML(Node node, boolean printToSystemOut, String... messages) {
		if (node == null) {
			return null;
		}
		Source xmlSource = new DOMSource(node);
		StringWriter sw = new StringWriter();
		Result outputTarget = new StreamResult(sw);
		Transformer docTransf = getDocumentTransformer();
		if (docTransf == null) {
			return null;
		}
		try {
			docTransf.transform(xmlSource, outputTarget);
		} catch (TransformerException e) {
			logger.error(e);
			return null;
		}

		StringBuffer buffer = new StringBuffer();
		for (String message : messages) {
			buffer.append("\n");
			buffer.append(message);
		}
		buffer.append("\n");
		buffer.append(sw.toString());

		logger.debug(buffer.toString());
		if (printToSystemOut) {
			System.out.println("XFormsUtils.DOMUtil: " + buffer.toString());
		}
		return buffer.toString();
	}

	/**
	 * Serializes a document to a string with the document's content indented.
	 * 
	 * @param doc
	 *            the document to serialize
	 * @return the document's content as a string
	 */
	public static String convertDocument2String(Document doc) {
		return convertDocument2String(doc, true);
	}

	/**
	 * Serializes a document to a string with the given indentation feature.
	 * 
	 * @param doc
	 *            the document to serialize
	 * @return the document's content as a string
	 */
	public static String convertDocument2String(Document doc, boolean indent) {
		StringBuilder stringBuilder = null;
		try {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			OutputFormat outputformat = new OutputFormat();
			outputformat.setEncoding("UTF-8");
			outputformat.setIndenting(indent);
			outputformat.setPreserveSpace(false);
			outputformat.setOmitXMLDeclaration(true);
			outputformat.setOmitDocumentType(true);
			XMLSerializer serializer = new XMLSerializer();
			serializer.setOutputFormat(outputformat);
			serializer.setOutputByteStream(stream);
			serializer.asDOMSerializer();
			serializer.serialize(doc.getDocumentElement());

			stringBuilder = new StringBuilder(stream.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return stringBuilder.toString();
	}

	/**
	 * Returns a new empty document.
	 */
	public static Document getNewDocument() {
		DocumentBuilder docBuilder = getDocumentBuilder();
		if (docBuilder == null) {
			return null;
		}
		Document doc = docBuilder.newDocument();
		return doc;
	}

	/**
	 * Returns a new document built from the input stream.
	 * 
	 * @param stream
	 *            the stream providing the document's content.
	 * @return
	 * @since 1.0.2
	 */
	public static Document getDocumentFromStream(InputStream stream) {
		Document document;
		try {
			DocumentBuilder docBuilder = getDocumentBuilder();
			document = docBuilder.parse(stream);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return document;
	}

	/**
	 * Returns a new document built from the input stream.
	 * 
	 * @param text
	 *            the document content to be parsed.
	 * @return
	 * @since 1.0.2
	 */
	public static Document getDocumentFromString(String text) {
		Document document;
		try {
			DocumentBuilder docBuilder = getDocumentBuilder();
			InputStream is = new ByteArrayInputStream(text.getBytes("UTF-8"));
			document = docBuilder.parse(is);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return document;
	}

	/**
	 * Returns a new document built from the input stream.
	 * 
	 * @param uri
	 *            a URI to the document's content.
	 * @return
	 * @since 1.0.2
	 */
	public static Document getDocumentFromURI(String uri) {
		Document document;
		try {
			DocumentBuilder docBuilder = getDocumentBuilder();
			document = docBuilder.parse(uri);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return document;
	}

	/**
	 * Gets all immediate children.
	 * 
	 * @param rootElement
	 *            the root element
	 * 
	 * @return the children
	 */
	public static List<Element> getAllChildren(Element rootElement) {
		List<Element> result = new ArrayList<Element>();
		NodeList childNodes = rootElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = childNodes.item(i);
			if (child instanceof Element) {
				Element element = (Element) child;
				result.add(element);
			}
		}
		return result;
	}

	/**
	 * Gets all elements of the list that match the tag name.
	 * 
	 * @param elements
	 *            the elements
	 * @param tagName
	 *            the tag name
	 * 
	 * @return the children by tag name
	 */
	public static List<Element> getElementsByTagName(List<Element> elements, String tagName) {
		List<Element> result = new ArrayList<Element>();
		for (Element element : elements) {
			if (StringUtils.equalsIgnoreCase(element.getTagName(), tagName)) {
				result.add(element);
			}
		}
		return result;
	}

	/**
	 * Retrieves a specific element based on tag name anywhere below a node with the additional
	 * condition that its text content is not empty.
	 * 
	 * @param rootElt
	 *            the root of the tree to search
	 * @param tagName
	 * @return the first Element that matches the tagName
	 */
	public static Element getElementInDescentByNameNonNull(Element rootElt, String tagName) {
		Element res = null;
		if (rootElt != null) {
			List<Element> children = getAllChildren(rootElt);
			res = getOneElementByTagName(children, tagName);
			if (res != null) {
				if (StringUtils.trimToNull(res.getTextContent()) != null) {
					return res;
				}
				return null;
			}
			for (Element elt : children) {
				res = getElementInDescentByNameNonNull(elt, tagName);
				if (res != null) {
					return res;
				}
			}
		}
		return null;
	}

	/**
	 * Gets the immediate children whose tag name matches the given name.
	 * 
	 * @param rootElement
	 *            the root element
	 * @param name
	 *            the required tag name, possibly including namespace prefix and local name
	 * 
	 * @return the list of the relevant children. Never <code>null</code>.
	 */
	public static List<Element> getChildren(Element rootElement, String name) {
		List<Element> result = new ArrayList<Element>();
		NodeList childNodes = rootElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = childNodes.item(i);
			if (child instanceof Element) {
				Element element = (Element) child;
				if (StringUtils.equals(element.getTagName(), name)) {
					result.add(element);
				}
			}
		}
		return result;
	}

	/**
	 * Gets the first element amongst children.
	 * 
	 * @param element
	 *            the element
	 * 
	 * @return the first element
	 */
	public static Element getFirstElement(Node element) {
		NodeList childNodes = element.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node item = childNodes.item(i);
			if (item instanceof Element) {
				return (Element) item;
			}
		}
		return null;
	}

	/**
	 * Gets the first element that matches the tag name.
	 * 
	 * @param elements
	 *            the elements
	 * @param tagName
	 *            the tag name
	 * 
	 * @return the unique children by tag name
	 * @since 1.0.1
	 */
	public static Element getOneElementByTagName(List<Element> elements, String tagName) {
		for (Element element : elements) {
			if (StringUtils.equalsIgnoreCase(element.getTagName(), tagName)) {
				return element;
			}
		}
		return null;
	}

	/**
	 * Gets the node value by tag name.
	 * 
	 * @param node
	 *            the node
	 * @param name
	 *            the name
	 * @param recursive
	 *            the recursive
	 * 
	 * @return the node value by tag name
	 */
	public static String getNodeValueByTagName(Node node, String name, boolean recursive) {
		if (node instanceof Document) {
			return getNodeValueByTagName(((Document) node).getDocumentElement(), name, recursive);
		}

		if (node instanceof Element) {
			Element element = (Element) node;
			if (StringUtils.equals(element.getTagName(), name)) {
				return StringUtils.trimToNull(element.getTextContent());
			}
		}

		NodeList childNodes = node.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node item = childNodes.item(i);
			if (item instanceof Element) {
				Element element = (Element) item;
				if (StringUtils.equals(element.getTagName(), name)) {
					return StringUtils.trimToNull(element.getTextContent());
				}
				if (recursive) {
					String nodeValueByTagName = getNodeValueByTagName(item, name, true);
					if (nodeValueByTagName != null) {
						return nodeValueByTagName;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Gets the specific element among immediate children.
	 * 
	 * @param node
	 *            the node
	 * @param tagName
	 *            the tag name
	 * 
	 * @return the specific element
	 */
	public static Element getChild(Node node, String tagName) {
		Element element = null;
		if (node instanceof Document) {
			element = ((Document) node).getDocumentElement();
		} else {
			if (node instanceof Element) {
				element = (Element) node;
			}
		}
		Element contentElement = null;
		if (element != null) {
			List<Element> children = getAllChildren(element);
			contentElement = getOneElementByTagName(children, tagName);
		}
		return contentElement;
	}

	/**
	 * Retrieves a specific element based on tag name anywhere below a node.
	 * 
	 * @param node
	 *            the root of the tree to search
	 * @param tagName
	 * @return the first Element that matches the tagName
	 */
	public static Element getElementInDescentByName(Element node, String tagName) {
		Element res = null;
		if (node != null) {
			List<Element> children = getAllChildren(node);
			res = getOneElementByTagName(children, tagName);
			if (res != null) {
				return res;
			}
			for (Element elt : children) {
				res = getElementInDescentByName(elt, tagName);
				if (res != null) {
					return res;
				}
			}
		}
		return null;
	}

	/**
	 * Retrieves a specific element based on attribute value anywhere below a node.
	 * 
	 * @param node
	 *            the root of the tree to search
	 * @param tagName
	 * @return the first Element that matches the tagName
	 */
	public static Element getEltInDescentByAttrValue(Element node, String attr, String value) {
		if (node != null) {
			String actualValue = node.getAttribute(attr);
			if (StringUtils.equals(actualValue, value)) {
				return node;
			}
			List<Element> children = getAllChildren(node);
			for (Element elt : children) {
				Element res = getEltInDescentByAttrValue(elt, attr, value);
				if (res != null) {
					return res;
				}
			}
		}
		return null;
	}

	/**
	 * Removes the child element which has a the specified attribute and value.
	 * 
	 * @param node
	 *            the root of the tree to search
	 * @param tagName
	 * @return whether something was removed
	 */
	public static boolean removeEltInDescentByAttrValue(Element node, String attr, String value) {
		boolean res;

		if (node != null) {
			List<Element> children = getAllChildren(node);
			for (Element elt : children) {
				String actualValue = elt.getAttribute(attr);
				if (StringUtils.equals(actualValue, value)) {
					node.removeChild(elt);
					return true;
				}
			}
			// not found yet, try in each child
			for (Element elt : children) {
				res = removeEltInDescentByAttrValue(elt, attr, value);
				if (res == true) {
					return true;
				}
			}
		}
		return false;
	}

}
