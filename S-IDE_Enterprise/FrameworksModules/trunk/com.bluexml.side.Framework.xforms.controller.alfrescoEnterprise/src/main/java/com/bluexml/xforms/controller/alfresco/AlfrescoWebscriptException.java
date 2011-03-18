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


package com.bluexml.xforms.controller.alfresco;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

public class AlfrescoWebscriptException extends ServletException {

	private static final long serialVersionUID = 1L;

	protected static Log logger = LogFactory.getLog(AlfrescoWebscriptException.class);

	private StringBuilder sb = new StringBuilder("Erreur Alfresco : \n\r");
	private String cause = null;

	private boolean gotCauseMessage = false;
	private AlfrescoTransaction transaction;
	private String errorMessage;

	public AlfrescoWebscriptException(String result) {
		super("");
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(result.getBytes());
			analyzeError(getController().synchronizedParse(bis)); // #1227
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("Failed to parse Exception :");
				logger.error(result);
			}
		}
	}

	/**
	 * @return
	 */
	private AlfrescoController getController() {
		return AlfrescoController.getInstance();
	}

	public AlfrescoWebscriptException(Document result, AlfrescoTransaction transaction) {
		super("Erreur Alfresco");
		this.transaction = transaction;
		analyzeError(result);
	}

	@Override
	public String getMessage() {
		return cause;
	}

	private void analyzeError(Document parse) {
		Element exceptionElement = parse.getDocumentElement();
		analyzeError(exceptionElement);
		if (gotCauseMessage == false) {
			// we don't want the user to see the exception message so use sth more user-friendly
			sb = new StringBuilder(MsgPool.getMsg(MsgId.MSG_ERROR_DEFAULT_MSG));
			if (logger.isErrorEnabled()) {
				logger.error(errorMessage);
			}
		}
		cause = sb.toString();
	}

	private void analyzeError(Element exceptionElement) {
		Set<Entry<String, Element>> elements = getElements(exceptionElement).entrySet();
		for (Entry<String, Element> entry : elements) {
			if (StringUtils.equals(entry.getKey(), "message")) {
				String message = entry.getValue().getTextContent();

				//
				if (StringUtils.containsIgnoreCase(message, "integrity violation")) {
					String assoName = getAssociationName(message, 0);
					// assoName should never trim to null ! if it is, algorithm problem!
					if (StringUtils.trimToNull(assoName) == null) {
						if (logger.isErrorEnabled()) {
							logger.error("Caught an unknown integrity violation: " + message);
						}
						sb = new StringBuilder(MsgPool.getMsg(MsgId.MSG_ERROR_DEFAULT_MSG));
					} else {
						sb = new StringBuilder(MsgPool.getMsg(MsgId.MSG_ERROR_INTEGRITY_VIOLATION,
								assoName));
					}
					gotCauseMessage = true;
					return;
				}

				//
				// if (message.contains("Unicity Checking Error")) {
				// sb = new StringBuilder(MsgPool.getMsg(MsgId.MSG_UNICITY_VIOLATION));
				// gotCauseMessage = true;
				// return;
				// }

				//
				if (StringUtils.containsIgnoreCase(message, "access denied")) {
					sb = new StringBuilder(MsgPool.getMsg(MsgId.MSG_ERROR_ACCESS_DENIED));
					gotCauseMessage = true;
					return;
				}

				errorMessage = message;
				sb.append(message);
				sb.append("\n\r");
			}
			if (StringUtils.equals(entry.getKey(), "cause")) {
				Element exception = getElements(entry.getValue()).get("exception");
				analyzeError(exception);
			}
		}
	}

	// Found 1 integrity violations:
	// The association source multiplicity has been violated:
	// Target Node: workspace://SpacesStore/d087acfa-bcad-11de-abd9-a5ccbb4f0b15
	// Association: Association[
	// class=ClassDef[name={http://www.bluexml.com/model/content/BXSIDE/1.0}modelcyvel_AxeTech],
	// name={http://www.bluexml.com/model/content/BXSIDE/1.0}modelcyvel_AxeTech_rubriqueAxe_modelcyvel_RubriqueTech,
	// target class={http://www.bluexml.com/model/content/BXSIDE/1.0}modelcyvel_RubriqueTech, source
	// role=null, target role=null]
	// Required source Multiplicity: 0..1
	// Actual source Multiplicity: 2
	/**
	 * Returns the full name of the association that caused the integrity violation. This function
	 * is tailored for the message returned by Alfresco 2.9B (as in the example above).
	 * 
	 * @param message
	 * @param startpos
	 *            the position in the string where to start searching for the violated association's
	 *            full name. Needed because the message may reference several constraint violations.
	 * @return the name of the association from the message or empty string if it can't be found
	 */
	private String getAssociationName(String message, int startpos) {
		String result;
		int pos = message.indexOf("Association: Association", startpos);
		if (pos == -1) {
			return "";
		}

		pos = message.indexOf("name={", pos + 1); // skip the class def's name
		if (pos == -1) {
			return "";
		}
		pos = message.indexOf("name={", pos + 1); // this is the association's name
		if (pos == -1) {
			return "";
		}
		int pos1 = message.indexOf('}', pos);
		int pos2 = message.indexOf(',', pos);
		if ((pos1 == -1) || (pos2 == -1)) {
			return "";
		}
		result = message.substring(pos1 + 1, pos2); // we get the complete name
		result = getController().getShortAssociationName(result, transaction.getFormId());
		if (StringUtils.trimToNull(result) == null) {
			return getAssociationName(message, pos2 + 1);
		}
		return result;
	}

	private Map<String, Element> getElements(Element element) {
		Map<String, Element> result = new HashMap<String, Element>();
		NodeList children = element.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child instanceof Element) {
				Element childElement = (Element) child;
				result.put(childElement.getTagName(), childElement);
			}
		}
		return result;
	}

}
