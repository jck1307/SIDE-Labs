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


package com.bluexml.xforms.controller.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
import com.bluexml.xforms.controller.binding.AspectType;
import com.bluexml.xforms.controller.binding.AssociationActions;
import com.bluexml.xforms.controller.binding.AssociationType;
import com.bluexml.xforms.controller.binding.AttributeType;
import com.bluexml.xforms.controller.binding.ClassType;
import com.bluexml.xforms.controller.binding.GenericAssociation;
import com.bluexml.xforms.controller.binding.GenericAssociations;
import com.bluexml.xforms.controller.binding.GenericAttribute;
import com.bluexml.xforms.controller.binding.GenericAttributes;
import com.bluexml.xforms.controller.binding.GenericClass;
import com.bluexml.xforms.controller.binding.GenericClassReference;
import com.bluexml.xforms.controller.binding.Mapping;
import com.bluexml.xforms.controller.binding.ValueType;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class MappingToolClassFormsToAlfresco.
 */
public class MappingToolClassFormsToAlfresco extends MappingToolCommon {

	protected static Log logger = LogFactory.getLog(MappingToolClassFormsToAlfresco.class);

	/**
	 * Instantiates a new mapping tool class forms to alfresco.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param controller
	 *            the controller
	 */
	public MappingToolClassFormsToAlfresco(Mapping mapping, AlfrescoController controller) {
		super(mapping, controller);
	}

	/**
	 * Fill alfresco class.
	 * 
	 * @param login
	 *            the login
	 * @param alfrescoClass
	 *            the alfresco class
	 * @param xformsNode
	 *            the xforms node
	 * @param isServletRequest
	 */
	private void fillAlfrescoClass(GenericClass alfrescoClass, Node xformsNode,
			boolean isServletRequest, Map<String, String> initParams) {
		Element element = null;
		if (xformsNode instanceof Document) {
			element = ((Document) xformsNode).getDocumentElement();
		} else {
			element = (Element) xformsNode;
		}
		List<Element> children = DOMUtil.getAllChildren(element);

		ClassType classType = null;
		Element sideDataType = DOMUtil.getOneElementByTagName(children,
				MsgId.INT_INSTANCE_SIDE_DATATYPE.getText());
		if (sideDataType != null) {
			classType = getClassType(sideDataType.getTextContent());
		} else {
			classType = getClassType(element.getTagName());
		}

		alfrescoClass.setQualifiedName(classType.getAlfrescoName());
		List<ClassType> classTypes = getParentClassTypes(classType);

		GenericAttributes attributes = alfrescoObjectFactory.createGenericAttributes();
		GenericAssociations associations = alfrescoObjectFactory.createGenericAssociations();
		associations.setAction("replace");
		for (ClassType subClassType : classTypes) {
			xformsAttributesToAlfresco(attributes, children, subClassType, isServletRequest,
					initParams);
			xformsAssociationsToAlfresco(associations, children, subClassType);
		}
		alfrescoClass.setAttributes(attributes);
		alfrescoClass.setAssociations(associations);

		String elementId = xformsIdToAlfresco(children);
		if (elementId != null) {
			alfrescoClass.setId(elementId);
		}
	}

	/**
	 * Process association.
	 * 
	 * @param login
	 *            the login
	 * @param associations
	 *            the associations
	 * @param associationType
	 *            the association type
	 * @param associationElement
	 *            the association element
	 * @deprecated
	 */
	@Deprecated
	@SuppressWarnings("unused")
	private void processAssociation(AlfrescoTransaction transaction,
			GenericAssociations associations, AssociationType associationType,
			Element associationElement, Map<String, String> initParams) {
		String targetId = null;

		Element targetNode = null;
		targetNode = associationElement;

		if (targetNode != null) {
			if (isInline(associationType)) {
				targetId = processSave(transaction, targetNode, initParams);
			} else {
				Element specificElement = DOMUtil.getChild(targetNode, MsgId.INT_INSTANCE_SIDEID
						.getText());
				if (specificElement != null) {
					targetId = StringUtils.trimToNull(specificElement.getTextContent());
					targetId = controller.patchDataId(targetId);
				}
			}
		}

		if (targetId != null) {
			GenericAssociation association = alfrescoObjectFactory.createGenericAssociation();
			association.setQualifiedName(associationType.getAlfrescoName());
			association.setAction(AssociationActions.ADD);

			GenericClassReference target = alfrescoObjectFactory.createGenericClassReference();
			target.setQualifiedName(associationType.getType().getAlfrescoName());
			target.setValue(targetId);
			association.setTarget(target);

			associations.getAssociation().add(association);
		}

	}

	/**
	 * Transform class forms to alfresco.
	 * 
	 * @param node
	 *            the node
	 * @param isServletRequest
	 * 
	 * @return the com.bluexml.xforms.controller.alfresco.binding. class
	 */
	public GenericClass transformClassFormsToAlfresco(Node node, boolean isServletRequest,
			Map<String, String> initParams) {
		logXML(node, "transformXFormsToAlfresco", "input");

		GenericClass alfrescoClass = alfrescoObjectFactory.createGenericClass();
		fillAlfrescoClass(alfrescoClass, node, isServletRequest, initParams);

		// String datas = marshal(alfrescoClass);
		// logXML(null, "transformXFormsToAlfresco", "output", datas);
		// AlfrescoData alfrescoData = new AlfrescoData(datas, alfrescoClass
		// .getId());
		return alfrescoClass;
	}

	/**
	 * Xforms associations to alfresco.
	 * 
	 * @param login
	 *            the login
	 * @param associations
	 *            the associations
	 * @param children
	 *            the children
	 * @param classType
	 *            the class type
	 */
	private void xformsAssociationsToAlfresco(GenericAssociations associations,
			List<Element> children, ClassType classType) {
		List<AssociationType> xformsAssociations = classType.getAssociation();
		xformsAssociationsToAlfresco(children, associations, xformsAssociations);
	}

	/**
	 * Xforms associations to alfresco.
	 * 
	 * @param login
	 *            the login
	 * @param children
	 *            the children
	 * @param associations
	 *            the associations
	 * @param xformsAssociations
	 *            the xforms associations
	 */
	private void xformsAssociationsToAlfresco(List<Element> children,
			GenericAssociations associations, List<AssociationType> xformsAssociations) {
		for (AssociationType associationType : xformsAssociations) {
			xformsAssociationToAlfresco(children, associations, associationType);
		}
	}

	/**
	 * Interprets an association field tag from the form's XML instance and builds the appropriate
	 * elements of the generic class object being built.
	 * 
	 * @param children
	 *            the first-level nodes in the form instance. Each node is a field.
	 * @param associations
	 *            the associations being built in the generic class object
	 * @param associationType
	 *            the mapping entry for the association field
	 */
	private void xformsAssociationToAlfresco(List<Element> children,
			GenericAssociations associations, AssociationType associationType) {

		// find the node that stores the info for this association field
		Element associationElement = DOMUtil.getOneElementByTagName(children, associationType
				.getName());
		if (associationElement != null) { // simple precaution; should always turn true.
			Element item = DOMUtil.getChild(associationElement, MsgId.INT_INSTANCE_ASSOCIATION_ITEM
					.getText());
			String textContent = item.getTextContent();
			String[] ids = StringUtils.split(textContent, " ");
			for (String targetId : ids) {
				GenericAssociation association = alfrescoObjectFactory.createGenericAssociation();
				association.setQualifiedName(associationType.getAlfrescoName());
				association.setAction(AssociationActions.ADD);

				GenericClassReference target = alfrescoObjectFactory.createGenericClassReference();
				target.setQualifiedName(associationType.getType().getAlfrescoName());
				target.setValue(targetId);
				association.setTarget(target);

				associations.getAssociation().add(association);
			}
		}

		// This section is deprecated since the widget was changed to a plain select list. Inline
		// associations are no longer supported in these default forms since "simplifyClasses" was
		// forced to true.
		//
		// List<Element> associationElements = DOMUtil.getAllChildren(associationElement);
		// for (int i = 0; i < associationElements.size(); i++) {
		// if (!isMultiple(associationType) || i != (associationElements.size() - 1)) {
		// processAssociation(transaction, associations, associationType,
		// associationElements.get(i), initParams);
		// }
		// }

	}

	/**
	 * Xforms attributes to alfresco.
	 * 
	 * @param attributes
	 *            the attributes
	 * @param children
	 *            the children
	 * @param classType
	 *            the class type
	 * @param isServletRequest
	 */
	private void xformsAttributesToAlfresco(GenericAttributes attributes, List<Element> children,
			ClassType classType, boolean isServletRequest, Map<String, String> initParams) {
		List<AttributeType> xformsAttributes = classType.getAttribute();
		xformsAttributesToAlfresco(children, attributes, xformsAttributes, isServletRequest,
				initParams);

		List<AspectType> aspects = classType.getAspect();
		for (AspectType aspectType : aspects) {
			Element aspect = DOMUtil.getOneElementByTagName(children, aspectType.getName());
			if (aspect != null) {
				List<AttributeType> aspectAttributes = getAspectType(aspectType).getAttribute();
				List<Element> aspectChildren = DOMUtil.getAllChildren(aspect);
				xformsAttributesToAlfresco(aspectChildren, attributes, aspectAttributes,
						isServletRequest, initParams);
			}
		}
	}

	/**
	 * Xforms attributes to alfresco.
	 * 
	 * @param children
	 *            the children
	 * @param attributes
	 *            the attributes
	 * @param xformsAttributes
	 *            the xforms attributes
	 */
	private void xformsAttributesToAlfresco(List<Element> children, GenericAttributes attributes,
			List<AttributeType> xformsAttributes, boolean isServletRequest,
			Map<String, String> initParams) {
		for (AttributeType xformsAttribute : xformsAttributes) {
			Element child = DOMUtil.getOneElementByTagName(children, xformsAttribute.getName());
			if (child != null) {
				attributes.getAttribute().add(
						xformsAttributeToAlfresco(child, xformsAttribute, isServletRequest,
								initParams));
			}
		}
	}

	/**
	 * Xforms attribute to alfresco. //$$ TRACE LOG
	 * 
	 * @param child
	 *            the child
	 * @param attributeType
	 *            the xforms attribute
	 * @param isServletRequest
	 * 
	 * @return the attribute
	 */
	private GenericAttribute xformsAttributeToAlfresco(Element child, AttributeType attributeType,
			boolean isServletRequest, Map<String, String> initParams) {
		GenericAttribute result = alfrescoObjectFactory.createGenericAttribute();
		String alfrescoName = attributeType.getAlfrescoName();
		result.setQualifiedName(alfrescoName);
		String enumName = isDynamicEnum(attributeType) ? null : attributeType.getEnumQName();

		String inputTextContent = child.getTextContent();
		String type = attributeType.getType();
		if (isMultiple(attributeType)) {
			convertXformsMultipleAttributeToAlfresco(result, inputTextContent, child, type,
					enumName, initParams, false);
		} else {
			String value = null;
			boolean attributeIsReadOnly = isReadOnly(attributeType);
			if (loggertrace.isTraceEnabled()) {
				logger.debug("Received value '" + inputTextContent + "' for attribute '"
						+ alfrescoName + "' with type '" + type + "'. Read-only status '"
						+ attributeIsReadOnly + "'. isFileField: " + isFileField(attributeType)
						+ " . isServletRequest: " + isServletRequest);
			}
			if (isAmendable(type, attributeIsReadOnly, isServletRequest)) {
				inputTextContent = getReadOnlyDateOrTimeModifiedValue(type, inputTextContent);
			}
			if (type.equalsIgnoreCase(MsgId.INT_TYPE_XSD_DATETIME.getText())) {
				String date;
				String time;
				if (attributeIsReadOnly) {
					date = extractDateFromDateTimeModified(inputTextContent);
					time = extractTimeFromDateTimeModified(inputTextContent);
				} else {
					date = DOMUtil.getChild(child, "date").getTextContent();
					time = DOMUtil.getChild(child, "time").getTextContent();
				}
				value = getDateTimeFromDateAndTime(date, time);
			} else {
				value = convertXformsAttributeToAlfresco(inputTextContent, type, enumName,
						initParams, false);
			}
			result.getValue().clear();
			ValueType valueType = alfrescoObjectFactory.createValueType();
			valueType.setValue(value);
			result.getValue().add(valueType);
			if (isRepositoryContent(attributeType)) {
				// we need a name for the node when uploaded in the repository
				ValueType valueTypeNameAndExt = alfrescoObjectFactory.createValueType();
				String nameAndExt = child.getAttribute("file");
				valueTypeNameAndExt.setValue(nameAndExt);
				result.getValue().add(valueTypeNameAndExt);
				// we also need the MIME type
				ValueType valueTypeMIME = alfrescoObjectFactory.createValueType();
				String mimetype = child.getAttribute("type");
				valueTypeMIME.setValue(mimetype);
				result.getValue().add(valueTypeMIME);
			}
		}

		return result;
	}

	/**
	 * Removes the reference.
	 * 
	 * @param node
	 *            the node
	 * @param elementId
	 *            the element id
	 */
	public void removeReference(Node node, String elementId) {
		String relementId = controller.patchDataId(elementId);

		Element element = null;
		if (node instanceof Document) {
			element = ((Document) node).getDocumentElement();
		} else {
			if (node instanceof Element) {
				element = (Element) node;
			} else {
				throw new RuntimeException("Unknow type of DOM node element");
			}

		}

		List<Element> children = DOMUtil.getAllChildren(element);
		ClassType classType = null;
		Element dataType = DOMUtil.getOneElementByTagName(children,
				MsgId.INT_INSTANCE_SIDE_DATATYPE.getText());
		if (dataType != null) {
			classType = getClassType(dataType.getTextContent());
		} else {
			classType = getClassType(element.getTagName());
		}
		List<ClassType> classTypes = getParentClassTypes(classType);

		List<Element> elementsToRemove = new ArrayList<Element>();

		for (ClassType subClassType : classTypes) {
			List<AssociationType> xformsAssociations = subClassType.getAssociation();
			for (AssociationType associationType : xformsAssociations) {
				Element associationElement = DOMUtil.getOneElementByTagName(children,
						associationType.getName());
				if (associationElement != null) {
					List<Element> associationElements = DOMUtil.getAllChildren(associationElement);
					for (Element association : associationElements) {
						processRemoveReference(relementId, elementsToRemove, associationType,
								association, isMultiple(associationType));
					}
				}
			}
		}
		for (Element elementToRemove : elementsToRemove) {
			element.removeChild(elementToRemove);
		}
	}

	/**
	 * Process remove reference.
	 * 
	 * @param relementId
	 *            the relement id
	 * @param elementsToRemove
	 *            the elements to remove
	 * @param associationType
	 *            the association type
	 * @param associationElement
	 *            the association element
	 * @param multiple
	 *            the multiple
	 */
	private void processRemoveReference(String relementId, List<Element> elementsToRemove,
			AssociationType associationType, Element associationElement, boolean multiple) {

		String targetId = null;

		Element targetNode = null;
		targetNode = associationElement;

		if (targetNode != null) {
			targetId = getId(targetNode);
			if (StringUtils.equals(targetId, relementId)) {
				if (multiple) {
					elementsToRemove.add(associationElement);
				} else {
					Element eltTargetId = DOMUtil.getOneElementByTagName(DOMUtil
							.getAllChildren(targetNode), MsgId.INT_INSTANCE_SIDEID.getText());
					Element eltTargetLabel = DOMUtil.getOneElementByTagName(DOMUtil
							.getAllChildren(targetNode), MsgId.INT_INSTANCE_SIDELABEL.getText());
					eltTargetId.setTextContent("");
					eltTargetLabel.setTextContent("");
				}
			} else if (isInline(associationType)) {
				removeReference(targetNode, relementId);
			}
		}
	}

	/**
	 * Checks for sub types.
	 * 
	 * @param dataType
	 *            the data type
	 * 
	 * @return true, if successful and the datatype actually has some subtypes.
	 */
	public boolean hasSubTypes(String dataType) {
		ClassType classType = getClassType(dataType);
		if (classType == null) {
			return false;
		}
		return (classType.getSubClass().size() > 0);
	}
}
