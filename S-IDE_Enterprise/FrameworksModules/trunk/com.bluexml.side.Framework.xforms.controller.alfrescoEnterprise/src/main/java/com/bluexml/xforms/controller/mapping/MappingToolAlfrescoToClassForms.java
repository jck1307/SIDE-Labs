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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
import com.bluexml.xforms.controller.binding.AspectType;
import com.bluexml.xforms.controller.binding.AssociationType;
import com.bluexml.xforms.controller.binding.AttributeType;
import com.bluexml.xforms.controller.binding.ClassType;
import com.bluexml.xforms.controller.binding.GenericAssociation;
import com.bluexml.xforms.controller.binding.GenericAttribute;
import com.bluexml.xforms.controller.binding.GenericClass;
import com.bluexml.xforms.controller.binding.GenericClassReference;
import com.bluexml.xforms.controller.binding.Mapping;
import com.bluexml.xforms.controller.binding.ValueType;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class MappingToolImplAlfrescoToXForms.
 */
public class MappingToolAlfrescoToClassForms extends MappingToolCommon {

	/** The logger. */
	protected static Log logger = LogFactory.getLog(MappingToolAlfrescoToClassForms.class);

	/**
	 * Instantiates a new mapping tool impl alfresco to x forms.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param controller
	 *            the controller
	 */
	public MappingToolAlfrescoToClassForms(Mapping mapping, AlfrescoController controller) {
		super(mapping, controller);
	}

	/**
	 * Creates the x forms instance.
	 * 
	 * @param transaction
	 *            the login
	 * @param type
	 *            the type
	 * @param initParams
	 *            the init params
	 * @param stack
	 *            the stack
	 * 
	 * @return the document
	 */
	public Document createClassFormsInstance(
			AlfrescoTransaction transaction, String type,
			Map<String, String> initParams,
			Stack<AssociationType> stack, boolean formIsReadOnly,
			boolean isServletRequest) {
		Document xformsDocument = documentBuilder.newDocument();
		GenericClass alfrescoClass = alfrescoObjectFactory.createGenericClass();
		alfrescoClass.setQualifiedName(getClassType(type).getAlfrescoName());

		fillXFormsDocument(xformsDocument, alfrescoClass, type, initParams, formIsReadOnly,
				isServletRequest);

		logXML(xformsDocument, "createXFormsInstance", type);

		return xformsDocument;
	}

	/**
	 * Fill x forms aspect.
	 * 
	 * @param xformsDocument
	 *            the xforms document
	 * @param classElement
	 *            the class element
	 * @param aspect
	 *            the aspect
	 * @param attributes
	 *            the attributes
	 * @param initParams
	 *            the init params
	 * @param isServletRequest
	 */
	private void fillXFormsAspect(Document xformsDocument, Element classElement, AspectType aspect,
			List<GenericAttribute> attributes, Map<String, String> initParams,
			boolean formIsReadOnly, boolean isServletRequest) {
		Element aspectElement = xformsDocument.createElement(aspect.getName());
		List<AttributeType> aspectAttributes = getAspectType(aspect).getAttribute();
		for (AttributeType attributeType : aspectAttributes) {
			fillXFormsAttribute(xformsDocument, aspectElement, attributeType, attributes,
					initParams, formIsReadOnly, isServletRequest);
		}
		classElement.appendChild(aspectElement);
	}

	/**
	 * Fill x forms association type.
	 * 
	 * @param transaction
	 *            the login
	 * @param xformsDocument
	 *            the xforms document
	 * @param classElement
	 *            the class element
	 * @param association
	 *            the association
	 * @param alfrescoClass
	 *            the alfresco class
	 * @param initParams
	 *            the init params
	 * @param stack
	 *            the stack
	 * @param isServletRequest
	 * 
	 * @throws ServletException
	 * 
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private void fillXFormsAssociationType(AlfrescoTransaction transaction,
			Document xformsDocument, Element classElement, AssociationType association,
			GenericClass alfrescoClass, Map<String, String> initParams,
			Stack<AssociationType> stack, boolean formIsReadOnly, boolean isServletRequest)
			throws ServletException {
		if (!stackContains(stack, association)) {
			stack.push(association);
			List<GenericAssociation> alfrescoAssociations = findAssociations(alfrescoClass,
					association);
			String targetClassType = classTypeToString(association.getType());
			fillXFormsAssociationRoot(transaction, xformsDocument, classElement, association,
					stack, alfrescoAssociations, targetClassType, initParams, formIsReadOnly,
					isServletRequest);
			stack.pop();
		}
	}

	/**
	 * Fill x forms association.
	 * 
	 * @param transaction
	 *            the login
	 * @param xformsDocument
	 *            the xforms document
	 * @param classElement
	 *            the class element
	 * @param association
	 *            the association
	 * @param stack
	 *            the stack
	 * @param alfrescoAssociations
	 *            the alfresco associations
	 * @param targetClassTypeStr
	 *            the target class type
	 * @param initParams
	 *            the init params
	 * @param isServletRequest
	 */
	private void fillXFormsAssociationRoot(AlfrescoTransaction transaction,
			Document xformsDocument, Element classElement, AssociationType association,
			Stack<AssociationType> stack, List<GenericAssociation> alfrescoAssociations,
			String targetClassTypeStr, Map<String, String> initParams, boolean formIsReadOnly,
			boolean isServletRequest) {
		String initId = initParams.get("id");
		if (initId != null) {
			fillXFormsAssociationInit(transaction, xformsDocument, classElement, association,
					stack, targetClassTypeStr, initId, null, formIsReadOnly, isServletRequest);
		} else {
			Element associationElement = xformsDocument.createElement(association.getName());
			associationElement.setAttribute("multiple", "" + isMultiple(association));
			if (alfrescoAssociations.size() > 0) {
				for (GenericAssociation alfrescoAssociation : alfrescoAssociations) {
					GenericClassReference target = alfrescoAssociation.getTarget();
					String alfrescoTargetId = target.getValue();
					String targetTypeStr = target.getQualifiedName(); // #1485

					Node targetValue = null;
					if (isInline(association)) {
						targetValue = fillXFormsElementFromIdInline(transaction, xformsDocument,
								stack, alfrescoTargetId, formIsReadOnly, isServletRequest);
					} else {
						targetValue = fillXFormsElementFromIdNonInline(xformsDocument,
								targetClassTypeStr, target.getLabel(), alfrescoTargetId,
								targetTypeStr);
					}
					fillXFormsAssociationAddItem(associationElement, targetValue);
				}
				// add empty item if multiple
				if (isMultiple(association)) {
					processXFormsAssociationCreate(transaction, xformsDocument, association, stack,
							targetClassTypeStr, initParams, associationElement, formIsReadOnly,
							isServletRequest);
				}
			} else {
				processXFormsAssociationCreate(transaction, xformsDocument, association, stack,
						targetClassTypeStr, initParams, associationElement, formIsReadOnly,
						isServletRequest);
			}
			classElement.appendChild(associationElement);
		}
	}

	/**
	 * Process x forms association create.
	 * 
	 * @param transaction
	 *            the login
	 * @param xformsDocument
	 *            the xforms document
	 * @param association
	 *            the association
	 * @param stack
	 *            the stack
	 * @param targetClassType
	 *            the target class type
	 * @param initParams
	 *            the init params
	 * @param associationElement
	 *            the association element
	 */
	private void processXFormsAssociationCreate(AlfrescoTransaction transaction,
			Document xformsDocument, AssociationType association, Stack<AssociationType> stack,
			String targetClassType, Map<String, String> initParams, Element associationElement,
			boolean formIsReadOnly, boolean isServletRequest) {
		Node targetValue = null;
		if (isInline(association)) {
			targetValue = fillXFormsAssociationCreateElementInline(transaction, xformsDocument,
					association, stack, targetClassType, initParams, formIsReadOnly,
					isServletRequest);
		} else {
			targetValue = fillXFormsAssociationCreateElementNonInline(xformsDocument,
					targetClassType);
		}
		fillXFormsAssociationAddItem(associationElement, targetValue);
	}

	/**
	 * Fill x forms association add item.
	 * 
	 * @param xformsDocument
	 *            the xforms document
	 * @param association
	 *            the association
	 * @param associationElement
	 *            the association element
	 * @param targetValue
	 *            the target value
	 * @param associationClassValue
	 *            the association class value
	 */
	private void fillXFormsAssociationAddItem(Element associationElement, Node targetValue) {
		associationElement.appendChild(targetValue);
	}

	/**
	 * Fill x forms element non inline.
	 * 
	 * @param xformsDocument
	 *            the xforms document
	 * @param targetClassType
	 *            the target class type
	 * @param id
	 *            the id
	 * @param label
	 *            the label
	 * @param targetTypeStr
	 * 
	 * @return the node
	 */
	private Node fillXFormsElementNonInline(Document xformsDocument, String targetClassType,
			String id, String label, String targetTypeStr) {
		Element subNode;

		subNode = xformsDocument.createElement(MsgId.INT_INSTANCE_ASSOCIATION_ITEM.getText());
		subNode.setAttribute("type", targetClassType); // #1665

		Element eltLabel = xformsDocument.createElement(MsgId.INT_INSTANCE_SIDELABEL.getText());
		eltLabel.setTextContent(StringUtils.trimToEmpty(label));
		subNode.appendChild(eltLabel);

		Element eltId = xformsDocument.createElement(MsgId.INT_INSTANCE_SIDEID.getText());
		eltId.setTextContent(id);
		subNode.appendChild(eltId);

		Element eltEdit = xformsDocument.createElement(MsgId.INT_INSTANCE_SIDEEDIT.getText());
		eltEdit.setTextContent("");
		subNode.appendChild(eltEdit);

		// ** #1485
		Element eltType = xformsDocument.createElement(MsgId.INT_INSTANCE_SIDETYPE.getText());
		eltType.setTextContent(targetTypeStr);
		subNode.appendChild(eltType);
		// ** #1485

		return subNode;
	}

	/**
	 * Fill x forms element from id non inline.
	 * 
	 * @param xformsDocument
	 *            the xforms document
	 * @param targetClassType
	 *            the target class type
	 * @param target
	 *            the target
	 * @param alfrescoTargetId
	 *            the alfresco target id
	 * @param targetTypeStr
	 * 
	 * @return the node
	 */
	private Node fillXFormsElementFromIdNonInline(Document xformsDocument, String targetClassType,
			String targetLabel, String alfrescoTargetId, String targetTypeStr) {
		return fillXFormsElementNonInline(xformsDocument, targetClassType, alfrescoTargetId,
				targetLabel, targetTypeStr);
	}

	/**
	 * Fill x forms association create element non inline.
	 * 
	 * @param xformsDocument
	 *            the xforms document
	 * @param targetClassType
	 *            the target class type
	 * 
	 * @return the node
	 */
	private Node fillXFormsAssociationCreateElementNonInline(Document xformsDocument,
			String targetClassType) {
		return fillXFormsElementNonInline(xformsDocument, targetClassType, "", "", "");
	}

	/**
	 * Fill x forms association create element inline.
	 * 
	 * @param transaction
	 *            the login
	 * @param xformsDocument
	 *            the xforms document
	 * @param association
	 *            the association
	 * @param stack
	 *            the stack
	 * @param targetClassType
	 *            the target class type
	 * @param initParams
	 *            the init params
	 * 
	 * @return the node
	 */
	private Node fillXFormsAssociationCreateElementInline(AlfrescoTransaction transaction,
			Document xformsDocument, AssociationType association, Stack<AssociationType> stack,
			String targetClassType, Map<String, String> initParams, boolean formIsReadOnly,
			boolean isServletRequest) {
		Document inlineElement = null;
		inlineElement = createClassFormsInstance(transaction, targetClassType, subMap(initParams,
				association.getName()), stack, formIsReadOnly, isServletRequest);
		Node clonedNode = inlineElement.getDocumentElement().cloneNode(true);
		clonedNode = xformsDocument.adoptNode(clonedNode);
		return clonedNode;
	}

	/**
	 * Fill x forms association init.
	 * 
	 * @param transaction
	 *            the login
	 * @param xformsDocument
	 *            the xforms document
	 * @param classElement
	 *            the class element
	 * @param association
	 *            the association
	 * @param stack
	 *            the stack
	 * @param targetClassType
	 *            the target class type
	 * @param initId
	 *            the init id
	 * @param isServletRequest
	 * 
	 * @return the int
	 */
	private int fillXFormsAssociationInit(AlfrescoTransaction transaction, Document xformsDocument,
			Element classElement, AssociationType association, Stack<AssociationType> stack,
			String targetClassType, String initId, String targetType, boolean formIsReadOnly,
			boolean isServletRequest) {
		String realInitId = initId;
		realInitId = controller.patchDataId(realInitId);

		Element associationElement = xformsDocument.createElement(association.getName());
		Node subNode = null;
		if (isInline(association)) {
			subNode = fillXFormsElementFromIdInline(transaction, xformsDocument, stack, realInitId,
					formIsReadOnly, isServletRequest);
		} else {
			String label = null;
			try {
				List<String> captions = controller.getCaptions(transaction, Arrays
						.asList(new String[] { realInitId }));
				label = captions.get(0);
			} catch (Exception e) {
				if (logger.isErrorEnabled()) {
					logger.error("Caught exception while requesting captions", e);
				}
			}

			subNode = fillXFormsElementNonInline(xformsDocument, targetClassType, realInitId,
					label, targetType);
		}
		associationElement.appendChild(subNode);
		classElement.appendChild(associationElement);
		return 1;
	}

	/**
	 * Fill x forms element from id.
	 * 
	 * @param transaction
	 *            the login
	 * @param xformsDocument
	 *            the xforms document
	 * @param stack
	 *            the stack
	 * @param alfrescoTargetId
	 *            the alfresco target id
	 * @param isServletRequest
	 * 
	 * @return the node
	 */
	private Node fillXFormsElementFromIdInline(AlfrescoTransaction transaction,
			Document xformsDocument, Stack<AssociationType> stack, String alfrescoTargetId,
			boolean formIsReadOnly, boolean isServletRequest) {
		Node subNode;
		Document inlineElement = null;
		try {
			inlineElement = controller.getObjectInstance(transaction, alfrescoTargetId, stack,
					formIsReadOnly, isServletRequest);
		} catch (ServletException e) {
			throw new RuntimeException(e);
		}
		subNode = inlineElement.getDocumentElement().cloneNode(true);
		subNode = xformsDocument.adoptNode(subNode);
		return subNode;
	}

	/**
	 * Fill x forms attribute. Creates the attribute node and sets the value in the instance.
	 * Priority order: value from the uri; if none, the attribute's default; if none, a default
	 * value for the type is created.
	 * 
	 * @param xformsDoc
	 *            the xforms document
	 * @param containerElement
	 *            the container element
	 * @param attrType
	 *            the attribute
	 * @param attributes
	 *            the attributes
	 * @param initParams
	 *            the init params
	 * @param isServletRequest
	 */
	private void fillXFormsAttribute(Document xformsDoc, Element containerElement,
			AttributeType attrType, List<GenericAttribute> attributes,
			Map<String, String> initParams, boolean formIsReadOnly, boolean isServletRequest) {

		Element attrElt = xformsDoc.createElement(attrType.getName());
		// the value to set eventually
		String textualValue = null;
		// there may be a default value
		String defaultValue = getDefault(attrType);
		// #999: enums may be dynamic so don't treat them all as static
		String staticEnumName = isDynamicEnum(attrType) ? null : attrType.getEnumQName();

		GenericAttribute alfAttribute = findAttribute(attributes, attrType);
		String type = attrType.getType();
		if (attrType.getName().equals(MsgId.INT_INSTANCE_SIDEID.getText())) {
			if (alfAttribute != null) {
				textualValue = alfAttribute.getValue().get(0).getValue();
			}
		} else {
			if (alfAttribute == null) {
				String initialValue = safeMapGet(initParams, attrType.getName());
				// setting the value from the uri
				if (StringUtils.trimToNull(initialValue) != null) {
					textualValue = convertAlfrescoAttributeToXforms(initialValue, type,
							staticEnumName, initParams);
				} else { // empty form (but we want to include default values)
					textualValue = createXFormsInitialValue(type, defaultValue, staticEnumName,
							initParams);
				}
			} else {
				textualValue = convertAlfrescoAttributeToXforms(alfAttribute.getValue(), type,
						staticEnumName, initParams);
			}
		}

		// we set the text content depending on the data type, with user format if applicable
		final boolean applyUserFormat = (isServletRequest == false)
				&& (formIsReadOnly || isReadOnly(attrType));
		if (type.equals("DateTime")) {
			String timeValue = getTimeFromDateTime(textualValue);
			String dateValue = getDateFromDateTime(textualValue);
			if (applyUserFormat) {
				dateValue = transformDateValueForDisplay(dateValue);
				timeValue = transformTimeValueForDisplay(timeValue);
				attrElt.setTextContent(dateValue + " " + timeValue);
			} else {
				Element dateField = xformsDoc.createElement("date");
				dateField.setTextContent(dateValue);
				attrElt.appendChild(dateField);
				Element timeField = xformsDoc.createElement("time");
				timeField.setTextContent(timeValue);
				attrElt.appendChild(timeField);
			}
		} else if (type.equals("Date")) {
			String dateValue = textualValue;
			if (applyUserFormat) {
				transformDateValueForDisplay(textualValue);
			}
			attrElt.setTextContent(dateValue);
		} else if (type.equals("Time")) {
			String timeValue = textualValue;
			if (applyUserFormat) {
				timeValue = transformTimeValueForDisplay(textualValue);
			}
			attrElt.setTextContent(timeValue);
			// } else if (isMultiple(attrType) && (attrType.getEnumQName() == null)) {
			// // ** #1420: support for text fields with 'multiple' property set to 'true'
			// Element input =
			// xformsDoc.createElement(MsgId.INT_INSTANCE_INPUT_MULT_INPUT.getText());
			// attrElt.appendChild(input);
			//
			// // add legitimate multiple values
			// if (alfAttribute != null) {
			// for (ValueType valueType : alfAttribute.getValue()) {
			// Element item = getInputMultipleItemWithValue(xformsDoc, valueType.getValue());
			// attrElt.appendChild(item);
			// }
			// }
			//
			// // add ghost
			// Element ghostItem = getInputMultipleItemWithValue(xformsDoc, "");
			// attrElt.appendChild(ghostItem);
			// // ** #1420
		} else {
			attrElt.setTextContent(textualValue);
		}

		if (isFileField(attrType)) {
			attrElt.setAttribute("file", "");
			attrElt.setAttribute("type", "");
		}

		containerElement.appendChild(attrElt);
	}

	/**
	 * Fill x forms document.
	 * 
	 * @param transaction
	 *            the login
	 * @param xformsDocument
	 *            the xforms document
	 * @param alfrescoClass
	 *            the alfresco class
	 * @param type
	 *            the type
	 * @param initParams
	 *            the init params
	 * @param stack
	 *            the stack
	 * @param isServletRequest
	 */
	private void fillXFormsDocument(Document xformsDocument, GenericClass alfrescoClass,
			String type, Map<String, String> initParams, boolean formIsReadOnly,
			boolean isServletRequest) {
		ClassType classType = getClassType(type);

		Element classElement = xformsDocument.createElement(classTypeToString(classType));
		Element typeElement = xformsDocument.createElement(MsgId.INT_INSTANCE_SIDE_DATATYPE
				.getText());
		typeElement.setTextContent(classTypeToString(getClassTypeAlfrescoName(alfrescoClass
				.getQualifiedName())));

		List<ClassType> allClasses = getParentClassTypes(classType);

		Map<String, AttributeType> attributes = new TreeMap<String, AttributeType>();
		Map<String, AspectType> aspects = new TreeMap<String, AspectType>();
		Map<String, AssociationType> associations = new TreeMap<String, AssociationType>();
		for (ClassType fakeClassType : allClasses) {
			ClassType aClassType = getClassType(fakeClassType);
			List<AttributeType> subAttributes = aClassType.getAttribute();
			for (AttributeType attributeType : subAttributes) {
				String alfrescoName = attributeType.getAlfrescoName();
				if (alfrescoName != null) {
					// this case happens when using retroengineered alfresco models that do not have
					// the tags they are supposed to have
					attributes.put(alfrescoName, attributeType);
				}
			}
			List<AspectType> subAspects = aClassType.getAspect();
			for (AspectType aspectDecl : subAspects) {
				aspects.put(aspectDecl.getAlfrescoName(), getAspectType(aspectDecl));
			}
			List<AssociationType> subAssociations = aClassType.getAssociation();
			for (AssociationType associationType : subAssociations) {
				associations.put(associationType.getAlfrescoName(), associationType);
			}
		}

		String alfrescoId = StringUtils.trimToNull(alfrescoClass.getId());

		List<GenericAttribute> alfrescoAttributes = null;
		if (alfrescoClass.getAttributes() != null) {
			if (alfrescoClass.getAttributes().getAttribute() != null) {
				alfrescoAttributes = alfrescoClass.getAttributes().getAttribute();

				if (alfrescoId != null) {
					GenericAttribute idAttribute = alfrescoObjectFactory.createGenericAttribute();
					idAttribute.setQualifiedName(MsgId.INT_INSTANCE_SIDEID.getText());
					ValueType valueType = alfrescoObjectFactory.createValueType();
					valueType.setValue(alfrescoId);
					idAttribute.getValue().add(valueType);
					alfrescoAttributes.add(idAttribute);
				}

			}
		}

		Set<Entry<String, AttributeType>> attributesEntrySet = attributes.entrySet();
		for (Entry<String, AttributeType> entry : attributesEntrySet) {
			AttributeType attribute = entry.getValue();
			fillXFormsAttribute(xformsDocument, classElement, attribute, alfrescoAttributes,
					initParams, formIsReadOnly, isServletRequest);
		}
		Set<Entry<String, AspectType>> aspectsEntrySet = aspects.entrySet();
		for (Entry<String, AspectType> entry : aspectsEntrySet) {
			AspectType aspect = entry.getValue();
			if (aspect != null) { // aspects from Alfresco models yield a null here
				fillXFormsAspect(xformsDocument, classElement, aspect, alfrescoAttributes, subMap(
						initParams, aspect.getName()), formIsReadOnly, isServletRequest);
			}
		}
		Set<Entry<String, AssociationType>> associationsEntrySet = associations.entrySet();
		for (Entry<String, AssociationType> entry : associationsEntrySet) {
			AssociationType association = entry.getValue();
			// fillXFormsAssociationType(transaction, xformsDocument, classElement, association,
			// alfrescoClass, subMap(initParams, association.getName()), stack,
			// formIsReadOnly, isServletRequest);
			fillXFormsAssociationTypeSelect(xformsDocument, classElement, alfrescoClass,
					association, subMap(initParams, association.getName()));
		}

		classElement.appendChild(typeElement);
		addId(xformsDocument, classElement, alfrescoId);
		xformsDocument.appendChild(classElement);
	}

	/**
	 * Fills the appropriate node for an association and adds it to the form instance.
	 * 
	 * @param xformsDocument
	 *            the form instance document
	 * @param classElement
	 *            the root node of the document, which will contain all field nodes
	 * @param alfrescoClass
	 *            the object being displayed on the form
	 * @param association
	 *            the mapping entry for the association field
	 * @param subMap
	 *            the sub map of URL parameters relevant for the field
	 */
	private void fillXFormsAssociationTypeSelect(Document xformsDocument, Element classElement,
			GenericClass alfrescoClass, AssociationType association, Map<String, String> subMap) {
		Element assoFieldElt = xformsDocument.createElement(association.getName());
		assoFieldElt.setAttribute("multiple", "" + isMultiple(association));

		Element item = xformsDocument.createElement(MsgId.INT_INSTANCE_ASSOCIATION_ITEM.getText());
		assoFieldElt.appendChild(item);

		String initId = safeMapGet(subMap, "id"); // if present, overrides the existing associations
		if (initId == null) {
			initId = "";
			List<GenericAssociation> alfAssociations = findAssociations(alfrescoClass, association);

			// build the space-separated list of ids
			boolean first = true;
			for (GenericAssociation asso : alfAssociations) {
				if (!first) {
					initId += " ";
				}
				initId += asso.getTarget();
				first = false;
			}
		}
		item.setTextContent(initId);
		classElement.appendChild(assoFieldElt);
	}

	/**
	 * Finds the association items (association refs) that are present in the repository object and
	 * that match the given association.
	 * 
	 * @param alfrescoClass
	 *            the alfresco object
	 * @param associationType
	 *            the association type
	 * 
	 * @return the list< association>
	 */
	private List<GenericAssociation> findAssociations(GenericClass alfrescoClass,
			AssociationType associationType) {
		List<GenericAssociation> result = new ArrayList<GenericAssociation>();
		if (alfrescoClass.getAssociations() != null) {
			List<GenericAssociation> xformsAssociations = alfrescoClass.getAssociations()
					.getAssociation();
			if (xformsAssociations != null) {
				for (GenericAssociation xformsAssociation : xformsAssociations) {
					if (xformsAssociation.getQualifiedName().equals(
							associationType.getAlfrescoName())) {
						result.add(xformsAssociation);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Find attribute.
	 * 
	 * @param attributes
	 *            the attributes
	 * @param attribute
	 *            the attribute
	 * 
	 * @return the attribute
	 */
	private GenericAttribute findAttribute(List<GenericAttribute> attributes,
			AttributeType attribute) {
		GenericAttribute result = null;
		if (attributes != null) {
			for (GenericAttribute anAttribute : attributes) {
				if (anAttribute.getQualifiedName().equals(attribute.getAlfrescoName())) {
					result = anAttribute;
				}
			}
		}
		return result;
	}

	/**
	 * Stack contains.
	 * 
	 * @param stack
	 *            the stack
	 * @param association
	 *            the association
	 * 
	 * @return true, if successful
	 */
	private boolean stackContains(Stack<AssociationType> stack, AssociationType association) {
		boolean result = false;
		String searchedAssociation = association.getPackage() + association.getName();
		for (AssociationType associationType : stack) {
			String associationame = associationType.getPackage() + associationType.getName();
			if (StringUtils.equals(searchedAssociation, associationame)) {
				return true;
			}
		}
		return result;
	}

	/**
	 * Transform alfresco to x forms.
	 * 
	 * @param transaction
	 *            the login
	 * @param alfrescoNode
	 *            the alfresco node
	 * @param stack
	 *            the stack
	 * @param isServletRequest
	 * 
	 * @return the document
	 * 
	 * @throws ServletException
	 */
	public Document transformAlfrescoToClassForms(
			AlfrescoTransaction transaction, Document alfrescoNode,
			Stack<AssociationType> stack, boolean formIsReadOnly,
			boolean isServletRequest) throws ServletException {
		logXML(alfrescoNode, "transformAlfrescoToXForms", "input");

		if (alfrescoNode == null) {
			return null;
		}

		if (alfrescoNode.getDocumentElement().getTagName().equalsIgnoreCase("exception")) {
			throw new ServletException(alfrescoNode.getDocumentElement().getTextContent());
		}

		Document xformsDocument = documentBuilder.newDocument();
		try {
			GenericClass alfrescoClass = unmarshal(alfrescoNode);

			ClassType classTypeQName = getClassTypeAlfrescoName(alfrescoClass.getQualifiedName());
			String realType = classTypeToString(classTypeQName);

			fillXFormsDocument(xformsDocument, alfrescoClass, realType, null, formIsReadOnly,
					isServletRequest);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e);
			}
			throw new ServletException(e);
		}

		logXML(xformsDocument, "transformAlfrescoToXForms", "output");

		return xformsDocument;
	}

}
