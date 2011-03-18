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

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
import com.bluexml.xforms.controller.beans.FileUploadInfoBean;
import com.bluexml.xforms.controller.beans.PersistFormResultBean;
import com.bluexml.xforms.controller.binding.AssociationActions;
import com.bluexml.xforms.controller.binding.AssociationType;
import com.bluexml.xforms.controller.binding.ClassType;
import com.bluexml.xforms.controller.binding.FileFieldType;
import com.bluexml.xforms.controller.binding.FormFieldType;
import com.bluexml.xforms.controller.binding.FormType;
import com.bluexml.xforms.controller.binding.GenericAssociation;
import com.bluexml.xforms.controller.binding.GenericAssociations;
import com.bluexml.xforms.controller.binding.GenericAttribute;
import com.bluexml.xforms.controller.binding.GenericClass;
import com.bluexml.xforms.controller.binding.GenericClassReference;
import com.bluexml.xforms.controller.binding.Mapping;
import com.bluexml.xforms.controller.binding.ModelChoiceType;
import com.bluexml.xforms.controller.binding.ReferenceType;
import com.bluexml.xforms.controller.binding.ValueType;
import com.bluexml.xforms.controller.binding.WorkflowTaskType;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class MappingToolFormsToAlfresco.
 */
public class MappingToolFormsToAlfresco extends MappingToolCommon {

	/** The logger. */
	protected static Log logger = LogFactory.getLog(MappingToolFormsToAlfresco.class);

	/**
	 * Instantiates a new mapping tool forms to alfresco.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param controller
	 *            the controller
	 */
	public MappingToolFormsToAlfresco(Mapping mapping, AlfrescoController controller) {
		super(mapping, controller);
	}

	/**
	 * Transform forms to alfresco.
	 * 
	 * @param transaction
	 *            the login
	 * @param formName
	 *            the form name
	 * @param formNode
	 *            the form node
	 * 
	 * @return the com.bluexml.xforms.controller.alfresco.binding. class
	 * 
	 * @throws ServletException
	 */
	public GenericClass transformsToAlfresco(AlfrescoTransaction transaction, String formName,
			Node formNode, Map<String, String> initParams, boolean isMassTagging)
			throws ServletException {
		Element rootElt = getRootElement(formName, formNode);

		VirtualResolver virtualResolver = new VirtualResolver(this);
		virtualResolver.prepareXFormsToAlfresco(rootElt, formName);

		GenericClass result = persistFormElement(transaction, formName, rootElt, initParams,
				isMassTagging);

		return result;
	}

	/**
	 * Returns a JSON string reflecting the content of the GenericClass object built from the
	 * instance. The example used as a specification is:
	 * <p>
	 * 
	 * <pre>
	 * {type:"{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Personne",
	 *  properties:{"{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Personne_nom":"abad", 
	 *              "{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Personne_prenom":"david"
	 *             }
	 * }
	 * 
	 * <pre>
	 * @param transaction
	 * @param formName
	 * @param shortPropertyNames
	 * @param instance
	 * @return
	 * @throws ServletException
	 */
	public String transformsToJSON(AlfrescoTransaction transaction, String formName, Node formNode,
			boolean shortPropertyNames, Map<String, String> initParams) throws ServletException {

		Element root = getRootElement(formName, formNode);

		FormType formType = getFormType(formName);
		GenericClass alfClass = persistFormElement(transaction, formName, root, initParams, false);

		StringBuffer buf = new StringBuffer(256);
		String propName = "";
		String typeName = "";

		ClassType classType = formType.getRealClass();
		String packageName = classType.getPackage();
		String rootPackage = packageName;
		int pos = packageName.indexOf('.');
		if (pos != -1) {
			rootPackage = packageName.substring(0, pos);
		}
		String namespace = MsgId.INT_NAMESPACE_BLUEXML_CLASS + "/" + rootPackage + "/1.0";

		buf.append("{"); // open JSON string

		// the title
		buf.append("type:\"");
		typeName = "{" + namespace + "}" + classType.getAlfrescoName();
		buf.append(typeName);
		buf.append("\"");

		// the properties
		buf.append(",properties:{");
		boolean first = true;
		for (GenericAttribute attribute : alfClass.getAttributes().getAttribute()) {
			if (first == false) {
				buf.append(",");
			}
			first = false;
			propName = "{";
			propName += namespace;
			propName += "}";
			propName += attribute.getQualifiedName();
			if (shortPropertyNames) {
				propName = StringUtils.replace(propName, typeName, "");
			}
			buf.append("\"");
			buf.append(propName);
			buf.append("\":\"");
			buf.append(attribute.getValue().get(0).getValue());
			buf.append("\"");
		}
		buf.append("}"); // close properties

		buf.append("}"); // close the JSON string

		return buf.toString();
	}

	/**
	 * Builds a GenericClass from fields and associations defined on the form only: the special
	 * attribute for data node content is added afterwards.
	 * 
	 * @param transaction
	 *            the login
	 * @param formName
	 *            the form name
	 * @param rootElt
	 *            the relevant root element. Normally, its node name is the form name.
	 * 
	 * @return the com.bluexml.xforms.controller.alfresco.binding. class
	 * 
	 * @throws ServletException
	 */
	private GenericClass persistFormElement(AlfrescoTransaction transaction, String formName,
			Element rootElt, Map<String, String> initParams, boolean isMassTagging)
			throws ServletException {

		GenericClass alfClass = alfrescoObjectFactory.createGenericClass();
		// ** #1241
		alfClass.setMassTagging("" + isMassTagging);
		if (isMassTagging) {
			String massIds = patchMassIds(initParams);
			alfClass.setId(massIds);
		}
		// ** #1241
		alfClass.setAttributes(alfrescoObjectFactory.createGenericAttributes());
		GenericAssociations createAssociations = alfrescoObjectFactory.createGenericAssociations();
		alfClass.setAssociations(createAssociations);

		FormType formType = getFormType(formName);
		if (formType != null) { // this is a FormClass
			ClassType classType = getClassType(formType.getRealClass());

			List<Element> children = DOMUtil.getAllChildren(rootElt);
			String elementId = xformsIdToAlfresco(children);

			alfClass.setQualifiedName(classType.getAlfrescoName());

			collectFields(formName, rootElt, formType.getField(), alfClass, initParams,
					isMassTagging);

			if (isMassTagging == false) {
				if (elementId != null) {
					alfClass.setId(elementId);
					collectAssocsToClear(formType, classType, alfClass);
				}

				collectAssocs(transaction, rootElt, formType.getModelChoice(), formType
						.getReference(), alfClass, initParams);
			}
		} else { // dealing with a FormWorkflow
			WorkflowTaskType taskType = getWorkflowTaskType(formName, false);
			collectFields(formName, rootElt, taskType.getField(), alfClass, initParams, false);
			// change the references list if references become supported in FormWorkflow's
			collectAssocs(transaction, rootElt, taskType.getModelChoice(),
					new ArrayList<ReferenceType>(), alfClass, initParams);

			// set the name of the form as qualified name: in case the worfklow form has FileFields
			// that upload to the filesystem, the name will serve as a directory name.
			alfClass.setQualifiedName(formName);
		}
		return alfClass;
	}

	/**
	 * @param initParams
	 * @return
	 */
	private String patchMassIds(Map<String, String> initParams) {
		String ids = initParams.get(MsgId.PARAM_MASS_IDS.getText());
		StringBuffer massIds = new StringBuffer();
		String[] splittedIds = StringUtils.split(ids, ',');
		boolean first = true;
		for (String splitId : splittedIds) {
			if (first == false) {
				massIds.append(',');
			}
			massIds.append(controller.patchDataId(splitId));
			first = false;
		}
		return massIds.toString();
	}

	/**
	 * Collect assocs to clear.
	 * 
	 * @param transaction
	 *            the login
	 * @param element
	 *            the element
	 * @param formType
	 *            the form type
	 * @param classType
	 *            the class type
	 * @param alfClass
	 *            the alf class
	 */
	private void collectAssocsToClear(FormType formType, ClassType classType, GenericClass alfClass) {
		List<ModelChoiceType> modelChoices = new ArrayList<ModelChoiceType>(formType
				.getModelChoice());
		modelChoices.addAll(formType.getReference());

		List<ModelChoiceType> presentModelChoices = new ArrayList<ModelChoiceType>();

		List<AssociationType> associations = new ArrayList<AssociationType>();

		ClassType type = classType;
		do {
			type = getClassType(type);
			associations.addAll(type.getAssociation());
			type = type.getParentClass();
		} while (type != null);

		for (AssociationType associationType : associations) {
			String alfrescoName = associationType.getAlfrescoName();
			ModelChoiceType modelChoiceType = getModelChoice(modelChoices, alfrescoName);
			if (modelChoiceType != null) {
				presentModelChoices.add(modelChoiceType);
			}
		}

		for (ModelChoiceType modelChoiceType : presentModelChoices) {

			String associationAlfrescoName = modelChoiceType.getAlfrescoName();
			// String targetAlfrescoName = getClassType(
			// modelChoiceType.getRealClass()).getAlfrescoName();

			GenericAssociation association = alfrescoObjectFactory.createGenericAssociation();
			association.setQualifiedName(associationAlfrescoName);
			association.setAction(AssociationActions.DELETE_ALL);
			// Target alfTarget = alfrescoObjectFactory.createTarget();
			// alfTarget.setQualifiedName(targetAlfrescoName);
			// alfTarget.setValue("notused");
			// association.getAssociationClassOrTarget().add(alfTarget);
			alfClass.getAssociations().getAssociation().add(association);
		}
	}

	/**
	 * Gets the model choice that matches the alfresco name from the list.
	 * 
	 * @param modelChoices
	 *            a list of model choices
	 * @param alfrescoName
	 *            the alfresco name
	 * 
	 * @return the model choice
	 */
	private ModelChoiceType getModelChoice(List<ModelChoiceType> modelChoices, String alfrescoName) {
		for (ModelChoiceType modelChoiceType : modelChoices) {
			if (modelChoiceType.getAlfrescoName().equals(alfrescoName)) {
				return modelChoiceType;
			}
		}
		return null;
	}

	/**
	 * Collect assocs.
	 * 
	 * @param transaction
	 *            the login
	 * @param element
	 *            the element
	 * @param formType
	 *            the form type
	 * @param classType
	 *            the class type
	 * @param alfClass
	 *            the alf class
	 * 
	 * @throws ServletException
	 */
	// private void collectAssocs(AlfrescoTransaction transaction, Element
	// element, FormType formType,
	// GenericClass alfClass) throws ServletException {
	private void collectAssocs(AlfrescoTransaction transaction, Element element,
			List<ModelChoiceType> modelChoices, List<ReferenceType> references,
			GenericClass alfClass, Map<String, String> initParams) throws ServletException {
		for (ModelChoiceType modelChoiceType : modelChoices) {
			Element modelChoiceElement = DOMUtil.getChild(element, modelChoiceType.getUniqueName());
			if (isExtendedWidget(modelChoiceType) || isInline(modelChoiceType)) {
				collectModelChoices(transaction, alfClass, modelChoiceType, modelChoiceElement,
						initParams);
			} else {
				// plain select widget applies only when the field is: not inline and not extended
				collectModelChoiceSelectWidget(modelChoiceType, modelChoiceElement, alfClass);
			}
		}
		for (ReferenceType referenceType : references) {
			Element referenceElement = DOMUtil.getChild(element, referenceType.getUniqueName());
			List<FormType> targets = referenceType.getTarget();
			collectTargets(transaction, alfClass, referenceType, referenceElement, targets,
					initParams);
		}
	}

	/**
	 * Collect model choices.
	 * 
	 * @param transaction
	 *            the login
	 * @param alfClass
	 *            the alf class
	 * @param modelChoiceType
	 *            the model choice type
	 * @param modelChoiceElement
	 *            the model choice element
	 * 
	 * @throws ServletException
	 */
	private void collectModelChoices(AlfrescoTransaction transaction, GenericClass alfClass,
			ModelChoiceType modelChoiceType, Element modelChoiceElement,
			Map<String, String> initParams) throws ServletException {
		List<Element> values = new ArrayList<Element>(DOMUtil.getAllChildren(modelChoiceElement));
		if (modelChoiceType.getMaxBound() != 1) {
			values.remove(values.size() - 1);
		}
		for (Element value : values) {
			collectModelChoice(alfClass, modelChoiceType, value, transaction, initParams);
		}
	}

	/**
	 * Analyzes the value when the association widget is a select in order to add the relevant
	 * associations in the GenericClass object.
	 * 
	 * @param modelChoiceType
	 *            the mapping entry for the association field
	 * @param rootElt
	 *            the node for the association field in the form instance
	 * @param alfClass
	 *            the object to be sent to the webscript
	 */
	private void collectModelChoiceSelectWidget(ModelChoiceType modelChoiceType, Element rootElt,
			GenericClass alfClass) {
		Element item = DOMUtil.getChild(rootElt, MsgId.INT_INSTANCE_ASSOCIATION_ITEM.getText());
		String textContent = item.getTextContent();
		String[] ids = StringUtils.split(textContent, " ");
		for (String id : ids) {
			collectAddAssociation(alfClass, id, modelChoiceType);
		}
	}

	/**
	 * Collect model choice.
	 * 
	 * @param alfClass
	 *            the alf class
	 * @param modelChoiceType
	 *            the model choice type
	 * @param value
	 *            the value
	 * @param at
	 * @throws ServletException
	 */
	private void collectModelChoice(GenericClass alfClass, ModelChoiceType modelChoiceType,
			Element value, AlfrescoTransaction at, Map<String, String> initParams)
			throws ServletException {
		String id = getModelChoiceId(value, modelChoiceType, at, initParams);
		if (id != null) {
			collectAddAssociation(alfClass, id, modelChoiceType);
		}
	}

	private String getModelChoiceId(Element value, ModelChoiceType modelChoiceType,
			AlfrescoTransaction at, Map<String, String> initParams) throws ServletException {
		String id = null;
		if (isInline(modelChoiceType)) {
			PersistFormResultBean result = controller.persistForm(at, modelChoiceType.getTarget()
					.get(0).getName(), DOMUtil.getFirstElement(value), initParams, false);
			id = result.getResultStr();
		} else {
			id = getId(value);
		}
		return id;
	}

	/**
	 * Collect targets.
	 * 
	 * @param transaction
	 *            the login
	 * @param alfClass
	 *            the alf class
	 * @param referenceType
	 *            the reference type
	 * @param referenceElement
	 *            the reference element
	 * @param targets
	 *            the targets
	 * 
	 * @throws ServletException
	 */
	private void collectTargets(AlfrescoTransaction transaction, GenericClass alfClass,
			ReferenceType referenceType, Element referenceElement, List<FormType> targets,
			Map<String, String> initParams) throws ServletException {

		int i = 0;
		for (FormType target : targets) {
			String targetId = null;
			Element targetElement = null;

			targetElement = DOMUtil.getChild(referenceElement, target.getName());

			PersistFormResultBean result = controller.persistForm(transaction, target.getName(),
					targetElement, initParams, false);
			targetId = result.getResultStr();
			collectAddAssociation(alfClass, targetId, referenceType);
			i++;
		}
	}

	/**
	 * Collect add association.
	 * 
	 * @param alfClass
	 *            the alf class
	 * @param targetId
	 *            the target id
	 * @param modelChoiceType
	 *            the model choice type
	 * @param associationClassId
	 *            the association class id
	 */
	private void collectAddAssociation(GenericClass alfClass, String targetId,
			ModelChoiceType modelChoiceType) {

		String associationAlfrescoName = modelChoiceType.getAlfrescoName();
		String targetAlfrescoName = getClassType(modelChoiceType.getRealClass()).getAlfrescoName();

		GenericAssociation association = alfrescoObjectFactory.createGenericAssociation();
		association.setQualifiedName(associationAlfrescoName);
		association.setAction(AssociationActions.ADD);
		association.setOrdered(isOrdered(modelChoiceType));

		GenericClassReference alfTarget = alfrescoObjectFactory.createGenericClassReference();
		alfTarget.setQualifiedName(targetAlfrescoName);
		alfTarget.setValue(targetId);
		association.setTarget(alfTarget);

		alfClass.getAssociations().getAssociation().add(association);
	}

	/**
	 * Collect fields. //$$ TRACE LOG
	 * 
	 * @param formName
	 *            the name of the current form, used for messages.
	 * @param rootElt
	 *            the root element of the instance
	 * @param formType
	 *            the form type
	 * @param classType
	 *            the class type
	 * @param alfClass
	 *            the GenericClass to be filled
	 * @param isMassTagging
	 *            if <code>true</code>, only fields that have a value will end in the GenericClass
	 */
	private void collectFields(String formName, Element rootElt, List<FormFieldType> fields,
			GenericClass alfClass, Map<String, String> initParams, boolean isMassTagging) {
		for (FormFieldType fieldType : fields) {
			String uniqueName = fieldType.getUniqueName();
			String alfrescoName = fieldType.getAlfrescoName();
			Element fieldElement = DOMUtil.getChild(rootElt, uniqueName);
			if (fieldElement == null) {
				throw new RuntimeException("No DOM element was found in the instance for field: "
						+ uniqueName + "' (" + alfrescoName
						+ "). Probably another form has the same id as this one ('" + formName
						+ "') or there's a bug in the XForms engine.");
			}

			// whether the attribute will make it into the genericClass object
			boolean gotValue = false;
			//
			GenericAttribute attribute = alfrescoObjectFactory.createGenericAttribute();
			attribute.setQualifiedName(alfrescoName);
			String type = fieldType.getType();
			String inputTextContent = fieldElement.getTextContent();
			boolean readOnly = isReadOnly(fieldType);
			if (loggertrace.isTraceEnabled()) {
				logger.debug("Received value '" + inputTextContent + "' for attribute '"
						+ alfrescoName + "' with type '" + type + "'. Read-only status '"
						+ readOnly + "'. isFileField: " + (fieldType instanceof FileFieldType)
						+ " . isServletRequest: N/A");
			}

			//
			// convert the XForms field value to an attribute (possibly multiple) value
			if (isMultiple(fieldType)) {
				gotValue = convertXformsMultipleAttributeToAlfresco(attribute, inputTextContent,
						fieldElement, type, fieldType.getStaticEnumType(), initParams,
						isMassTagging);
			} else {
				if ((isMassTagging == false)
						|| (isMassTagging && (StringUtils.trimToNull(inputTextContent) != null))) {
					String alfrescoValue = null;
					// if applicable, take the user format into account
					if (isAmendable(type, isReadOnly(fieldType), false)) {
						inputTextContent = getReadOnlyDateOrTimeModifiedValue(type,
								inputTextContent);
					}
					if (type.equals("DateTime")) {
						String date;
						String time;
						if (readOnly) {
							date = extractDateFromDateTimeModified(inputTextContent);
							time = extractTimeFromDateTimeModified(inputTextContent);
						} else {
							date = DOMUtil.getChild(fieldElement, "date").getTextContent();
							time = DOMUtil.getChild(fieldElement, "time").getTextContent();
						}
						alfrescoValue = getDateTimeFromDateAndTime(date, time);
					} else if (isSearchEnum(fieldType)) {
						alfrescoValue = DOMUtil.getChild(fieldElement,
								MsgId.INT_INSTANCE_SIDEID.getText()).getTextContent();
					} else if (isSelectionCapable(fieldType)) {
						alfrescoValue = DOMUtil.getElementInDescentByName(fieldElement,
								MsgId.INT_INSTANCE_SIDEID.getText()).getTextContent();
					} else {
						alfrescoValue = convertXformsAttributeToAlfresco(inputTextContent, type,
								fieldType.getStaticEnumType(), initParams, isMassTagging);
					}
					ValueType valueType = alfrescoObjectFactory.createValueType();
					valueType.setValue(alfrescoValue);
					attribute.getValue().add(valueType);
					gotValue = true;
				}
			}
			//
			// mark FileFields with their destination. Useful for the webscript and for the upload
			// processing by the controller.
			if ((fieldType instanceof FileFieldType) && (isMassTagging == false)) {
				FileFieldType fileField = (FileFieldType) fieldType;
				String destination = isInRepository(fileField) ? MsgId.INT_UPLOAD_DEST_REPO
						.getText() : MsgId.INT_UPLOAD_DEST_FILE.getText();
				attribute.setUploadTo(destination);

				// we need a name for the node (in case of upload to the repository)
				ValueType valueTypeNameAndExt = alfrescoObjectFactory.createValueType();
				String nameAndExt = fieldElement.getAttribute("file");
				valueTypeNameAndExt.setValue(nameAndExt);
				attribute.getValue().add(valueTypeNameAndExt);

				// we also want the MIME type; not needed but it won't hurt
				ValueType valueTypeMIME = alfrescoObjectFactory.createValueType();
				String mimetype = fieldElement.getAttribute("type");
				valueTypeMIME.setValue(mimetype);
				attribute.getValue().add(valueTypeMIME);
			}

			if ((isMassTagging == false) || (isMassTagging && gotValue)) {
				alfClass.getAttributes().getAttribute().add(attribute);
			}
		}

		// if applicable, append the implicit attribute that will keep info about the node content
		Element nodeContentElt = DOMUtil.getChild(rootElt, MsgId.INT_INSTANCE_SIDE_NODE_CONTENT
				.getText());
		if ((isMassTagging == false) && (nodeContentElt != null)) {
			GenericAttribute contentAttr = alfrescoObjectFactory.createGenericAttribute();
			contentAttr.setQualifiedName(MsgId.INT_INSTANCE_SIDE_NODE_CONTENT.getText());
			contentAttr.setSkipMe("true"); // <-- this is MANDATORY!

			ValueType pathValue = alfrescoObjectFactory.createValueType();
			ValueType nameValue = alfrescoObjectFactory.createValueType();
			ValueType mimeValue = alfrescoObjectFactory.createValueType();

			String path = nodeContentElt.getTextContent();
			pathValue.setValue(path);

			String nameAndExt = nodeContentElt.getAttribute("file");
			nameValue.setValue(nameAndExt);

			String mimetype = nodeContentElt.getAttribute("type");
			mimeValue.setValue(mimetype);

			contentAttr.getValue().add(pathValue);
			contentAttr.getValue().add(nameValue);
			contentAttr.getValue().add(mimeValue);

			// append the attribute for content
			alfClass.getAttributes().getAttribute().add(contentAttr);
		}
	}

	/**
	 * Gets the (repository) content attribute.
	 * 
	 * @param alfClass
	 *            the alf class
	 * 
	 * @return the repository content attribute
	 */
	private GenericAttribute getNodeContentAttribute(GenericClass alfClass) {

		List<GenericAttribute> attributes = alfClass.getAttributes().getAttribute();
		for (GenericAttribute attribute : attributes) {
			String qualifiedName = attribute.getQualifiedName();

			if (qualifiedName != null
					&& qualifiedName.equals(MsgId.INT_INSTANCE_SIDE_NODE_CONTENT.getText())) {
				return attribute;
			}
		}
		return null;
	}

	/**
	 * Gets the file info bean about the repository content that may be in the class object.
	 * 
	 * @param transaction
	 *            the login
	 * @param alfClass
	 *            the alf class
	 * 
	 * @return null if no repository content file name was detected
	 */
	public FileUploadInfoBean getNodeContentInfo(AlfrescoTransaction transaction,
			GenericClass alfClass) {
		GenericAttribute contentAttribute = getNodeContentAttribute(alfClass);
		if (contentAttribute != null) {
			String path = contentAttribute.getValue().get(0).getValue();
			String name = contentAttribute.getValue().get(1).getValue();
			String type = contentAttribute.getValue().get(2).getValue();

			return new FileUploadInfoBean(path, name, type, contentAttribute, controller
					.getParamUploadRepoAppendSuffix(transaction.getInitParams()));
		}
		return null;
	}

}
