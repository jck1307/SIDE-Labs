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
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.generate.extract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;
import org.jbpm.graph.exe.ExecutionContext;

import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.AttributeContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.InvalidAssociationException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.InvalidContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.InvalidValueOfParameterException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.language.ConstantsLanguage;

/**
 * @author dchevrier
 * 
 */
public class ExtractDataFromContent {

	public static HashMap<String, Object> extractData(NodeRef content, ServiceRegistry services,
			HashMap<String, String> commands, ExecutionContext executionContext)
			throws InvalidValueOfParameterException, AttributeContentException, InvalidAssociationException,
			InvalidContentException {
		HashMap<String, Object> data = new HashMap<String, Object>();
		Set<String> keysCommands = commands.keySet();
		for (String keyCommand : keysCommands) {
			if (!isFormatCommand(keyCommand, commands)) {
				if (commands.get(keyCommand).contains(ConstantsLanguage.CONSTANT_INDICATOR)) {
					String constant = commands.get(keyCommand).split(ConstantsLanguage.CONSTANT_INDICATOR)[1];
					data.put(keyCommand, constant);
				} else if (commands.get(keyCommand).contains(ConstantsLanguage.NAVIGATION_INDICATOR)) {
					NodeRef finalTarget = null;
					String[] navigation = commands.get(keyCommand).split(
							ConstantsLanguage.NAVIGATION_INDICATOR);
					QName qnameType = services.getNodeService().getType(content);
					List<ChildAssociationRef> associations = services.getNodeService()
							.getChildAssocs(content);
					List<Object> attributesValues = new ArrayList<Object>();
					for (ChildAssociationRef association : associations) {
						finalTarget = followAssociations(services, association, qnameType, navigation, 0);
						if (finalTarget != null) {
							Object value = extractValueFromContent(services, finalTarget,
									navigation[navigation.length - 1]);
							attributesValues.add(value);
						}
					}
					data.put(keyCommand, attributesValues);
				} else if (!commands.get(keyCommand).contains(ConstantsLanguage.CONSTANT_INDICATOR)
						&& !commands.get(keyCommand).contains(ConstantsLanguage.NAVIGATION_INDICATOR)) {
					Object value = extractValueFromContent(services, content, commands.get(keyCommand));
					data.put(keyCommand, value);
				} else {
					Object result = null;
					try {
						// We try to evaluate as alfresco javascript expression
						result = AlfrescoJavaScript.executeScript(executionContext, services, commands
								.get(keyCommand), Collections.EMPTY_LIST);
					} catch (Exception e) {
					}
					if (result == null)
						throw new InvalidValueOfParameterException(
								InvalidValueOfParameterException.BAD_FORMAT);
					else
						data.put(keyCommand, result.toString());
				}
			}
		}
		return data;
	}

	private static boolean isFormatCommand(String keyCommand, HashMap<String, String> commands) {
		if (keyCommand.endsWith(".format")) {
			String subCommand = keyCommand.substring(0, keyCommand.length() - 7);
			return commands.containsKey(subCommand);
		}
		return false;
	}

	private static Object extractValueFromContent(ServiceRegistry services, NodeRef content, String attribute)
			throws AttributeContentException {
		Object data = null;
		Map<QName, Serializable> properties = services.getNodeService().getProperties(content);
		Set<QName> propertiesNames = properties.keySet();
		boolean attributeExists = false;
		for (QName propertyName : propertiesNames) {
			if (propertyName.getLocalName().endsWith(attribute)) {
				attributeExists = true;
				data = (Object) properties.get(propertyName);
			}
		}
		if (!attributeExists) {
			throw new AttributeContentException(AttributeContentException.DOES_NOT_EXISTS);
		}
		// if (data instanceof Date){
		// data = formatDate(data);
		// }
		return data;
	}

	private static NodeRef followAssociations(ServiceRegistry services, ChildAssociationRef association,
			QName qnameType, String[] navigation, int indexNavigation) throws InvalidAssociationException,
			InvalidContentException {
		NodeRef finalTarget = null;
		String uri = qnameType.getNamespaceURI();
		if (association.getQName().toString().contains(uri)
				&& association.getQName().toString().contains(navigation[indexNavigation])) {
			NodeRef target = association.getChildRef();
			if (target != null) {
				if (indexNavigation < navigation.length - 2) {
					QName targetType = services.getNodeService().getType(target);
					List<ChildAssociationRef> nextAssociations = services.getNodeService().getChildAssocs(
							target);
					for (ChildAssociationRef nextAssociation : nextAssociations) {
						followAssociations(services, nextAssociation, targetType, navigation,
								indexNavigation++);
					}
				} else {
					finalTarget = target;
				}
			} else {
				throw new InvalidContentException(InvalidContentException.DOES_NOT_EXISTS);
			}
		} else {
			throw new InvalidAssociationException(InvalidAssociationException.DOES_NOT_EXISTS);
		}

		return finalTarget;
	}

}
