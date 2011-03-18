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
package com.bluexml.xforms.controller.alfresco;

import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;

import org.alfresco.service.cmr.workflow.WorkflowDefinition;
import org.alfresco.service.cmr.workflow.WorkflowTask;
import org.alfresco.service.cmr.workflow.WorkflowTaskDefinition;
import org.w3c.dom.Document;

import com.bluexml.xforms.controller.beans.RedirectionBean;
import com.bluexml.xforms.controller.beans.WorkflowTaskInfoBean;

/**
 * The public API of the BlueXML XForms component's controller for Alfresco.
 * <p/>
 * NOTES:
 * <ul>
 * <li>User name parameters refer to legitimate registered Alfresco users. There will be no
 * authentication performed so the caller <em>must</em> have verified the authentication credentials
 * before calling a function since, except for {@link #authenticate(String, String)}, no password
 * parameter is supported. In most instances, the user name is used to impersonate a registered
 * user.</li>
 * <li>There is no notion of persistence attached to communications with the Alfresco server. All
 * communications are unit communications. Thus, sessions, timeouts, keep-alives and all such
 * concepts are irrelevant in the context of this API.
 * <li>ServletException is thrown by functions whose offered functionality involves a communication
 * with the Alfresco server. An exception being thrown means that the functionality is hindered in
 * some way on the server side (server unreachable, invalid object id, unauthorized access, etc.).</li>
 * <li>Some of the workflow-related functions are prefixed with <em>getWorkflow</em> and others are
 * prefixed with <em>workflowGet</em>. Those starting with <em>getWorkflow</em> are somehow "local"
 * in that they don't use the Alfresco API but provide information about workflow forms and tasks as
 * can be found in the models. Those starting with <em>workflowGet</em> imply using Alfresco's API
 * in some way (whether calling a function or using a type), which means contacting the server.
 * </ul>
 * 
 * @see also {@link RedirectionBean}, {@link WorkflowTaskInfoBean}
 * @author Amenel
 * 
 */
public interface AlfrescoControllerAPI {

	public AlfrescoControllerAPI getController();

	/**
	 * Gets the URL to the Alfresco host.
	 * 
	 * @return the URL to the Alfresco host
	 */
	public String getAlfrescoUrl();

	/**
	 * Changes the URL to the Alfresco host. The host is initially set to the value defined in the
	 * forms.properties file.
	 * 
	 * @param alfrescoUrl
	 *            the URL to the Alfresco host to set. Must contain: protocol, hostname, port (if
	 *            relevant), and the Alfresco webapp's context name (which is usually
	 *            <code>alfresco</code>). For instance, "http://localhost:8080/alfresco".
	 */
	public void setAlfrescoUrl(String alfrescoUrl);

	/**
	 * Patches a node id to make it valid with respect to the usual Alfresco format for contents in
	 * the workspace. Ensures "workspace://SpacesStore/" is the prefix of the final id. But there's
	 * no checking that the provided id is indeed a valid Alfresco id: if "BOB THE CAT" is submitted
	 * as id, then "workspace://SpacesStore/BOB THE CAT" will be returned.
	 * 
	 * @param dataId
	 *            the node id, with ("full id") or without ("short id") protocol and store
	 *            specified.
	 * 
	 * @return the patched id or <code>null</code> if the the given id is <code>null</code>.
	 */
	public String patchDataId(String dataId);

	/**
	 * Removes the protocol+store prefix from a full node id. If the id contains no prefix, it is
	 * returned as provided.
	 * 
	 * @param dataId
	 *            a full and valid node id. If <code>null</code>, will cause a NullPointerException.
	 * @return the hex code part of the node id
	 */
	public String unpatchDataId(String dataId);

	/**
	 * Gets all custom forms, i.e. forms generated from Form models.<br/>
	 * In case read-only versions are generated, <em>only</em> the read-write versions are listed:
	 * the list <em>does not</em> contain both the read-write and read-only versions of a form.
	 * 
	 * @returns the list of custom forms
	 */
	public List<String> getAllCustomForms();

	/**
	 * Gets all default forms, i.e. forms generated from Data models.<br/>
	 * In case read-only versions are generated, <em>only</em> the read-write versions are listed.
	 * 
	 * @returns the list of default forms
	 */
	public List<String> getAllDefaultForms();

	/**
	 * Gets the list of all search forms.
	 * 
	 * @return the list of search forms
	 */
	public List<String> getAllSearchForms();

	/**
	 * Gets the list of all workflow forms.
	 * 
	 * @return the list of workflow forms
	 */
	public List<String> getAllWorkflowForms();

	/**
	 * Creates or loads the XForms instance document for a default class form.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user <em>Must</em> be provided if the id of an
	 *            object is provided.
	 * @param formName
	 *            the id of a read-write form, under the form package + "." + name (see the mapping
	 *            file or the data model). The suffix for read-only forms <em>must</em> not be
	 *            appended.
	 * @param dataId
	 *            the node id (full or short) of an object to load. If <code>null</code>, the form
	 *            is empty (with the exception of default values [from the data model or from URL
	 *            parameters]). If non-null, the form is filled with values from the object.
	 * @param formIsReadOnly
	 *            whether the instance is for the read-only version of the form
	 * @param applyUserFormats
	 *            whether to use user formats (if <code>true</code>) or ISO 8601 (if
	 *            <code>false</code>) for read-only dates and times. This parameter is independent
	 *            of whether the form is read-only or not.
	 * 
	 * @return the default form instance document
	 * 
	 * @throws ServletException
	 */
	public Document getInstanceClass(String userName, String formName, String dataId,
			boolean formIsReadOnly, boolean applyUserFormats) throws ServletException;

	/**
	 * Creates or loads the XForms instance document for a FormClass (a customized form).<br/>
	 * <em>NOTE: user formats always apply.</em>
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user <em>Must</em> be provided if the id of an
	 *            object is provided.
	 * @param formName
	 *            the form id of a read-write form
	 * @param dataId
	 *            the node id (full or short) of an object to load. If <code>null</code>, the form
	 *            is empty (with the exception of default values [from the form model]). If non-
	 *            null, the form is filled with values from the object.
	 * @param formIsReadOnly
	 *            whether the instance is for the read only version of the form
	 * 
	 * @return the custom form instance document
	 * 
	 * @throws ServletException
	 */
	public Document getInstanceForm(String userName, String formName, String dataId,
			boolean formIsReadOnly) throws ServletException;

	/**
	 * Returns an XForms instance document for a FormSearch.
	 * 
	 * @param formName
	 *            the id of a generated search form
	 * @return the search form instance document. A <code>null</code> value is never returned even
	 *         if the form name does not correspond to the id of a search form actually generated.
	 */
	public Document getInstanceSearch(String formName);

	/**
	 * Returns an XForms instance document for a FormWorkflow. Note that if the form supports a data
	 * form, the instance for the data form is not included.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user
	 * @param formName
	 *            the id of a workflow form
	 * @return the workflow form instance document. A <code>null</code> value is never returned.
	 * @throws ServletException
	 */
	public Document getInstanceWorkflow(String userName, String formName);

	/**
	 * Gets a document filled with the properties of an existing repository object.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user
	 * @param dataId
	 *            the node id (full or short) of an object that exists in the repository
	 * @return a document containing all properties (including system properties) of the object.
	 * @throws ServletException
	 */
	public Document readObjectFromRepository(String userName, String dataId)
			throws ServletException;

	/**
	 * Gets the local part of the node type as returned by Alfresco.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user
	 * @param dataId
	 *            the node id, with or without the protocol and store.
	 * @return the local name, or <code>null</code> if the node does not exist or an exception
	 *         occurs Alfresco server-side
	 */
	public String getNodeType(String userName, String dataId);

	/**
	 * Gets the full node type as returned by Alfresco, i.e. including namespace URI and local name.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user
	 * @param dataId
	 *            the node id, with or without the protocol and store.
	 * @return the node type. Returns <code>null</code> if an exception occurs or if the value is
	 *         <code>null</code>, which is a hint that either the Alfresco server is not available
	 *         or the object does not exist.
	 */
	public String getNodeTypeFull(String userName, String dataId);

	/**
	 * Tells whether a custom form with the given id exists.
	 * 
	 * @param formName
	 *            the form id
	 * @return true if a FormClass with that id has been generated.
	 */
	public boolean isCustomForm(String formName);

	/**
	 * Tells whether a default form with the given id exists.
	 * 
	 * @param formName
	 *            the form id
	 * @return true if a form (generated from data models) with that id exists.
	 */
	public boolean isDefaultForm(String formName);

	/**
	 * Tells whether a search form with the given id exists.
	 * 
	 * @param formName
	 *            the form id
	 * @return true if a FormSearch with that id exists.
	 */
	public boolean isSearchForm(String formName);

	/**
	 * Tells whether a workflow form with the given id exists.
	 * 
	 * @param formName
	 *            the form id
	 * @return true if a FormWorkflowwith that id exists .
	 */
	public boolean isWorkflowForm(String formName);

	/**
	 * Tells whether a form supports a start task.
	 * 
	 * @param wkFormName
	 *            the form id
	 * @return true if the form exists as a workflow form and the form is linked to a start task.
	 */
	public boolean isStartTaskForm(String wkFormName);

	/**
	 * Returns the redirection bean for a specific workflow form.
	 * 
	 * @param formName
	 *            the name of the form (e.g. Evaluation_Demarrage)
	 * @see {@link RedirectionBean}
	 */
	public RedirectionBean getWorkflowRedirectionBean(String formName);

	/**
	 * Loads the redirection configuration from the file path. If the path is invalid, the previous
	 * configuration remains active.
	 * 
	 * @param filePath
	 *            the complete path to the redirection file, with path, name and extension if
	 *            applicable
	 * @return <code>true</code> if the file was loaded, <code>false</code> otherwise.
	 */
	public boolean loadRedirectionTable(String filePath);

	/**
	 * Gets the id of the first FormClass that supports the given data type.
	 * 
	 * @param dataType
	 *            the local name part of a node type as returned by {@link #getNodeType(String)} or
	 *            by the Alfresco API. It can also be found in form/realClass/alfrescoName in the
	 *            mapping file.
	 * @return a custom form id, or <code>null</code> if none is found.
	 */
	public String getCustomFormForDatatype(String dataType);

	/**
	 * Gets the id of the default form that supports the given data type.
	 * 
	 * @param dataType
	 *            the local name part of a node type as returned by {@link #getNodeType(String)} or
	 *            by the Alfresco API. It can also be found in class/alfrescoName in the mapping
	 *            file.
	 * @return a default form id, or <code>null</code> if none is found.
	 */
	public String getDefaultFormForDatatype(String dataType);

	/**
	 * Gets the name of the data type supported by the FormClass (customized form) with the given
	 * id. If the generation project contains only one customized form FF for that data type DD then
	 * getUnderlyingTypeForForm(FF) = DD and getCustomFormForDatatype(DD) = FF.
	 * 
	 * @param formName
	 *            the valid id of a FormClass that has been generated.
	 * @return the data type as defined in the class model, which is also what would be returned by
	 *         {@link #getNodeType(String)} for a node created using the given form. Returns
	 *         <code>null</code> if the form name is unknown.
	 */
	public String getUnderlyingTypeForForm(String formName);

	/**
	 * Gets the data type supported by the data form of a FormWorkflow.
	 * 
	 * @param formName
	 *            the valid id of a FormWorkflow that has been generated.
	 * @return the data type of the workflow form's data form, as defined in the class model, and as
	 *         would be returned by {@link #getUnderlyingTypeForForm(String)} for the data form.
	 *         Returns <code>null</code> if the form name is unknown.
	 */
	public String getUnderlyingTypeForWorkflow(String formName);

	/**
	 * Gets the id of the data form linked to a workflow form.
	 * 
	 * 
	 * @param formName
	 *            the valid id of a FormWorkflow that has been generated.
	 * @return the id of the data form.
	 */
	public String getUnderlyingDataFormForWorkflow(String formName);

	/**
	 * Gets a pool of information about a workflow form.
	 * 
	 * @param wkFormName
	 *            the id of a valid workflow form.
	 * @return the information bean.
	 * @see {@link WorkflowTaskInfoBean}
	 */
	public WorkflowTaskInfoBean getWorkflowTaskInfoBean(String wkFormName);

	/**
	 * Gets a pool of information about the form that supports the given task id.
	 * 
	 * @param taskId
	 *            the id of a task.
	 * @return the information bean.
	 * @see {@link WorkflowTaskInfoBean}
	 */
	public WorkflowTaskInfoBean getWorkflowTaskInfoBeanByTaskId(String taskId);

	/**
	 * Dynamically reloads all reloadable info (such as the mapping file) except properties files.
	 * 
	 * @return <code>false</code> if an error occurs, <code>true</code> otherwise.
	 */
	public boolean performDynamicReload();

	//
	// WEBSCRIPT
	//

	/**
	 * Retrieves some information about the content of an existing node and provides a user-readable
	 * version of the info collected if the node has a defined content. <br/>
	 * <p>
	 * <em>NOTE: a K is 1024</em>
	 * </p>
	 * For now, gets:
	 * <ul>
	 * <li>node name as displayed via a web client</li>
	 * <li>the localized content size, as number of bytes and readable file size. e.g. "7 614 525
	 * bytes (7.26 MB)"</li>
	 * </ul>
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user
	 * @param nodeId
	 *            the node id (full or short).
	 * 
	 * @return the info about a content formatted as indicated by the relevant format in
	 *         messages.properties, or <code>null</code> if an exception occurs, or empty if no
	 *         content is associated with the node.
	 */
	public String getNodeContentInfo(String userName, String nodeId);

	/**
	 * Gets the string that the webscript's 'help' operation provides.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user
	 * @return the string, as provided by the web script
	 */
	public String getWebscriptHelp(String userName);

	/**
	 * Tests authentication credentials with the current Alfresco host (defined in the properties
	 * file or via {@link #setAlfrescoUrl(String)}).
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user authentication to succeed
	 * @param password
	 *            the plain password
	 * @return <code>true</code> if the authentication succeeded, <code>false</code> otherwise or in
	 *         case of exception.
	 */
	public boolean authenticate(String username, String password);

	/**
	 * Creates a folder in the repository, creating the missing parents as needed.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user
	 * @param folderPath
	 *            a standard (Alfresco-wise) XPath expression of a path in the repository, e.g.
	 *            "/app:company_home/app:dictionary/cm:Countries/cm:Europe/cm:Finland"
	 * @return the full node id of the terminal folder ('Finland' in the example above).
	 * @throws ServletException
	 */
	public String createPathInRepository(String userName, String folderPath)
			throws ServletException;

	//
	//
	// Bridge functions to Alfresco API via the webscript.
	//
	//

	/**
	 * Gets the set of authorities that exist in the current Alfresco host. The security risks
	 * involved with this function are obvious; the function should not be called unless the user is
	 * already authenticated.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user
	 * @param asGroups
	 *            if <code>true</code>, specifies that only groups, including standard Alfresco
	 *            groups, are returned. If <code>false</code>, only users. To have the full set of
	 *            users and groups, call twice.
	 * @return the set of registered authorities. Returns <code>null</code> if an exception occurs
	 *         or if the value is <code>null</code> by itself.
	 */
	public Set<String> systemGetAllAuthoritiesAsGroupsOrUsers(String userName, boolean asGroups);

	/**
	 * Returns all groups a specific user belongs to, including non-immediate parent groups.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user
	 * @param specificUserName
	 *            the name of the specific user
	 * @return the set of groups the specific user is part of. Returns <code>null</code> if an
	 *         exception occurs or if the value is <code>null</code> by itself.
	 */
	public Set<String> systemGetContainingGroups(String userName, String specificUserName);

	/**
	 * Gets the value of a property for a node.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user
	 * @param nodeId
	 *            a node id (full or short).
	 * @param propertyName
	 *            the qualified name of a property under the format {namespace URI}local_name
	 * @return the value of the property for the node. Returns <code>null</code> if an exception
	 *         occurs or if the value is <code>null</code>.
	 */
	public String systemGetNodeProperty(String userName, String propertyName, String nodeId);

	/**
	 * Returns the node id for a user identified by name. If no user with that name exists, the
	 * corresponding authority will not be created.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user
	 * @param specificUserName
	 *            the name of the user.
	 * @return the full node id for the user name. Returns <code>null</code> if an exception occurs
	 *         or if the value returned by Alfresco is <code>null</code>.
	 */
	public String systemGetNodeRefForUser(String userName, String specificUserName);

	/**
	 * Returns the node id for a user group identified by name.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user
	 * @param groupName
	 *            the group name as can be seen in Alfresco's web client. The system prefix for
	 *            groups will be automatically prepended if it's absent.
	 * @return the full node id for the user group. Returns <code>null</code> if an exception occurs
	 *         or if the group does not exist.
	 */
	public String systemGetNodeRefForGroup(String userName, String groupName);

	//
	//
	// Workflow-related
	//
	//

	/**
	 * Gets the list of workflow instances that apply to a node and fit the required status.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user
	 * @param nodeId
	 *            the node id (full or short) of a repository content item
	 * @param onlyActive
	 *            the status that returned workflows must be in. <code>true</code> means "active",
	 *            <code>false</code> means "completed".
	 * @return the list of workflow instances
	 */
	public List<String> workflowGetWorkflowsForContent(String userName, String nodeId,
			boolean onlyActive);

	/**
	 * Gets the workflow definition for the given workflow id.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user
	 * @param defId
	 *            the workflow definition id
	 * @return the definition, as returned by the Alfresco API
	 */
	public WorkflowDefinition workflowGetWorkflowById(String userName, String defId);

	/**
	 * Gets the task name from the form name.
	 * 
	 * @param formName
	 *            the id of a workflow form
	 * @return the name of the task, which should not be <code>null</code> or empty
	 */
	public String workflowExtractTaskNameFromFormName(String formName);

	/**
	 * Gets the process name from the form name.<br/>
	 * 
	 * @param formName
	 *            the id of a workflow form
	 * @return the name of the process. Should not be <code>null</code> or empty.
	 */
	public String workflowExtractProcessNameFromFormName(String formName);

	/**
	 * Returns the namespace prefix part of a generated workflow from a WorkflowDefinition.name
	 * field. In case the name does not have the expected format, the parameter is returned as is.
	 * 
	 * @param name
	 *            a process definition name (e.g. "jbpm$wfbxEvaluation:Evaluation")
	 * @return the local name of the process (e.g. "wfbxEvaluation")
	 */
	public String workflowExtractNamespacePrefix(String name);

	/**
	 * Gets the process name from WorkflowDefinition name.
	 * 
	 * @param name
	 *            a process definition name
	 * @return the local name of the process (e.g. "jbpm$wf:review" -> "review")
	 */
	public String workflowExtractProcessNameFromDefName(String name);

	/**
	 * Tells whether a workflow definition fits the definition name of processes generated via the
	 * BlueXML workflow modeler and generator.
	 * 
	 * @param name
	 *            a process definition name
	 * @return <code>true</code> if the definition name is a BlueXML definition, <code>false</code>
	 *         otherwise.
	 */
	public boolean workflowIsBlueXMLDefinition(String name);

	/**
	 * Gives the name under which a process is known to the workflow engine under Alfresco. <br/>
	 * This replicates the name construction rules that are used by the BlueXML workflow generator.
	 * 
	 * @param name
	 *            a process definition name, as indicated in the 'Name' property in the workflow
	 *            modeler. e.g. "Evaluation"
	 * @return the process definition name as can be found in WorkflowDefinition.name. e.g.
	 *         "jbpm$wfbxEvaluation:Evaluation"
	 */
	public String getWorkflowBlueXMLDefinitionName(String name);

	/**
	 * Gives the name under which a task is defined in the process definition model. <br/>
	 * This matches the name construction rules that are used by the BlueXML workflow generator.
	 * 
	 * @param formName
	 *            the id of a workflow form
	 * @return the task name as should be found in the generated model.xml or processDefintion.xml.
	 */
	public String getWorkflowBlueXMLTaskName(String formName);

	/**
	 * Gives the name of the form that corresponds to a task name.
	 * 
	 * @param taskName
	 *            the task id, with or without the "jbpm$" prefix, e.g. "wfbxEvaluation:Annotation"
	 *            or "jbpm$wfbxEvaluation:Annotation"
	 * @return the id of a workflow form
	 */
	public String getWorkflowFormNameFromTask(String taskName);

	/**
	 * Returns the form name for a task, based on the full task id.
	 * 
	 * @param fullTaskId
	 *            the task id as generated by the BlueXML workflow generator, e.g. "wfbxwfTest:T1"
	 * @return the id of the workflow form that can be used for the task, or <code>null</code> if no
	 *         such form exists
	 */
	public String getWorkflowFormNameByTaskId(String fullTaskId);

	/**
	 * Builds the namespace URI for workflow models generated with SIDE.<br/>
	 * This matches the name construction rules that are used by the BlueXML workflow generator.
	 * 
	 * @param processName
	 *            the token that is used as process name in the BlueXML workflow modeler.
	 * @return the namespace URI
	 */
	public String getWorkflowNamespaceURI(String processName);

	/**
	 * Retrieves the name/id of a form that implements the start task of a workflow definition name.<br/>
	 * 
	 * @param workflowDefName
	 *            the definition name as provided by WorkflowService.getDefinitions(), e.g.
	 *            "jbpm$wfbxwfTest:wfTest"
	 * @return the id of a form, e.g. "wfTest_Start"
	 */
	public String getWorkflowStartTaskFormName(String workflowDefName);

	/**
	 * Gets a list of in-progress workflow tasks for a user, whether they are pooled or specific to
	 * the user.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user
	 * @param managerUserName
	 *            the login name of the user whose tasks are being fetched
	 * @return the list of ids of in-progress tasks for the manager user.
	 */
	public List<String> workflowGetPooledTasks(String userName, String managerUserName);

	/**
	 * Gets the WorkflowTask object for the given task id.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user
	 * @param taskId
	 *            the task id
	 * @return the task object, or <code>null</code> if not found
	 */
	public WorkflowTask workflowGetTaskById(String userName, String taskId);

	/**
	 * Gets the contents of the workflow package for the given task id.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user
	 * @param taskId
	 *            the task id
	 * @return the list of the full ids of items associated with the task's package
	 */
	public List<String> workflowGetPackageContents(String userName, String taskId);

	/**
	 * Retrieves a specific task definition for a given process Id.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user
	 * @param processDefId
	 *            the id of a process, e.g. "jbpm$6"
	 * @param task
	 *            the id of a task to search for in the definition, e.g. "wfbxdemande:Accepter"
	 * @return
	 */
	public WorkflowTaskDefinition workflowGetTaskDefinition(String userName, String processDefId,
			String task);

	/**
	 * Retrieves all in-progress tasks found for the instance. Since several paths may be associated
	 * with the instance, each active path may provide tasks.
	 * 
	 * @param userName
	 *            the name of a registered Alfresco user
	 * @param instanceId
	 *            the id of a live workflow instance
	 * @return the list (empty if an exception occurs) of in-progress tasks ids.
	 * @throws ServletException
	 */
	public List<String> workflowGetCurrentTasks(String userName, String instanceId);

	//
	//
	// General
	//
	//
	/**
	 * Gets the suffix appended to read only forms.
	 * 
	 * @return the suffix appended to names of read-write forms to produce the names of the
	 *         read-only versions of the same forms.
	 */
	public String getReadOnlyFormsSuffix();

	/**
	 * Gets the URL for the CSS file that was previously set.
	 * 
	 * @return the CSS URL; may be <code>null</code>
	 */
	public String getCssUrl();

	/**
	 * Sets a CSS file URL that will be added in the head section of all forms at serving time. The
	 * URL applies from the moment it is set: setting it for one form sets it for all subsequent
	 * forms until the URL is changed.
	 * 
	 * @param cssUrl
	 *            the CSS URL to set. If <code>null</code>, the adding of the CSS URL to forms is
	 *            disabled.
	 */
	public void setCssUrl(String cssUrl);

	/**
	 * Sets the controller in or out of the stand alone mode.
	 * 
	 * @param standaloneMode
	 *            the standalone status to set: true=ON and false=OFF.
	 */
	public void setStandaloneMode(boolean standaloneMode);

	/**
	 * Tells whether the controller is in stand alone mode. If so, the Alfresco server is considered
	 * unavailable and all reads and writes are either simulated or disabled.
	 * 
	 * @return the standalone status
	 */
	public boolean isInStandaloneMode();

}
