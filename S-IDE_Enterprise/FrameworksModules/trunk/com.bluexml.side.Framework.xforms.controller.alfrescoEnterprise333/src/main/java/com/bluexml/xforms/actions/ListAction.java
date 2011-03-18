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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.servlet.ServletException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringUtils;
import org.chiba.processor.XFormsProcessor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bluexml.xforms.controller.beans.ListActionBean;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class ListAction.<br>
 * Get the list of a specified type with a search query. This action is a read and write action
 * hence its not being under {@link AbstractReadAction} or {@link AbstractWriteAction}.
 */
public class ListAction extends AbstractAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#executeResolve()
	 */
	@Override
	public Node resolve() throws ServletException {
		// retrieves elements
		return list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#executeSubmit()
	 */
	/**
	 * Used when performing searches on lists. Invoked via ModelElementListUpdater.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void submit() throws ServletException {
		// update list using search
		Document doc = (Document) node;
		String query = "";
		String maxResults = "";
		Element queryElement = doc.getDocumentElement();
		NodeList childNodes = queryElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = childNodes.item(i);
			if (child instanceof Element) {
				Element element = (Element) child;
				if (StringUtils.equals(element.getTagName(), "query")) {
					query = element.getTextContent();
				}
				if (StringUtils.equals(element.getTagName(), "maxResults")) {
					maxResults = element.getTextContent();
				}
			}
		}

		requestParameters.put(MsgId.INT_ACT_PARAM_LIST_QUERY.getText(), query);
		requestParameters.put(MsgId.INT_ACT_PARAM_LIST_SIZE.getText(), maxResults);

		// retrieves elements
		Node list = list();

		// convert to string
		Source xmlSource = new DOMSource(list);
		ByteArrayOutputStream pos = new ByteArrayOutputStream();
		Result outputTarget = new StreamResult(pos);
		try {
			documentTransformer.transform(xmlSource, outputTarget);
		} catch (TransformerException e) {
			if (logger.isErrorEnabled()) {
				logger.error("Failed to convert the list document into a string", e);
			}
			throw new ServletException(MsgId.MSG_ERROR_DEFAULT_MSG.getText());
		}

		ByteArrayInputStream pis = new ByteArrayInputStream(pos.toByteArray());

		result.put(XFormsProcessor.SUBMISSION_RESPONSE_STREAM, pis);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {
		return MsgId.INT_ACT_CODE_LIST.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getParamNames()
	 */
	@Override
	protected String[] getParamNames() {
		// keep in sync with the URI building rules in com.bluexml.generator.forms.ModelElement
		return new String[] {
				MsgId.INT_ACT_PARAM_ANY_DATATYPE.getText(),
				MsgId.INT_ACT_PARAM_LIST_FORMAT.getText(),
				MsgId.INT_ACT_PARAM_LIST_MAXLENGTH.getText(),
				MsgId.INT_ACT_PARAM_LIST_IDENTIFIER.getText(),
				MsgId.INT_ACT_PARAM_LIST_FILTER_ASSOC.getText(),
				MsgId.INT_ACT_PARAM_LIST_IS_COMPOSITION.getText(),
				MsgId.INT_ACT_PARAM_LIST_IS_SEARCH_MODE.getText(),
				MsgId.INT_ACT_PARAM_LIST_LUCENE_QUERY.getText(),
				MsgId.INT_ACT_PARAM_LIST_DATA_SOURCE_URI.getText() };
	}

	/**
	 * Bridge to the controller to get a list of items from a data type.
	 * 
	 * @return the node
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private Node list() throws ServletException {
		// simply call the controller, after collecting the parameters
		String dataType = requestParameters.get(MsgId.INT_ACT_PARAM_ANY_DATATYPE.getText());
		String query = requestParameters.get(MsgId.INT_ACT_PARAM_LIST_QUERY.getText());
		String maxResults = requestParameters.get(MsgId.INT_ACT_PARAM_LIST_SIZE.getText());
		String format = requestParameters.get(MsgId.INT_ACT_PARAM_LIST_FORMAT.getText());
		String maxLength = requestParameters.get(MsgId.INT_ACT_PARAM_LIST_MAXLENGTH.getText());
		String identifier = requestParameters.get(MsgId.INT_ACT_PARAM_LIST_IDENTIFIER.getText());
		String filterAssoc = requestParameters.get(MsgId.INT_ACT_PARAM_LIST_FILTER_ASSOC.getText());
		String compo = requestParameters.get(MsgId.INT_ACT_PARAM_LIST_IS_COMPOSITION.getText());
		String searchMode = requestParameters.get(MsgId.INT_ACT_PARAM_LIST_IS_SEARCH_MODE.getText());
		String luceneQuery = requestParameters.get(MsgId.INT_ACT_PARAM_LIST_LUCENE_QUERY.getText());
		String dataSourceURI = requestParameters.get(MsgId.INT_ACT_PARAM_LIST_DATA_SOURCE_URI.getText());

		// "format" was partially decoded (spaces are still represented using '+', (and maybe some
		// other characters are still somehow encoded ?)) so we need to re encode the format
		// pattern, since it will be transmitted again via URL to the webscript where it will be
		// decoded again. Update: that decoding/encoding sequence may not be needed; not sure yet
		// Uncomment or tweak the section below as necessary.
		// try {
		// if (StringUtils.trimToNull(format) != null) {
		// format = URLDecoder.decode(format, "UTF-8");
		// format = URLEncoder.encode(format, "UTF-8");
		// }
		// } catch (UnsupportedEncodingException e) {
		// if (logger.isFatalEnabled()) {
		// logger.fatal("Unsupported encoding scheme");
		// }
		// throw new RuntimeException("Unsupported encoding scheme");
		// }

		ListActionBean bean = new ListActionBean(dataType, query, maxResults, format, maxLength,
				identifier, filterAssoc, compo, searchMode, luceneQuery,dataSourceURI);
		return controller.getList(transaction, bean);
	}

}
