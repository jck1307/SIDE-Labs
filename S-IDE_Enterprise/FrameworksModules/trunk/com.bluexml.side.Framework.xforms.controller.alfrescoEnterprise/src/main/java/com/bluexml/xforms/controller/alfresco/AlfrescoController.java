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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Vector;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.workflow.WorkflowDefinition;
import org.alfresco.service.cmr.workflow.WorkflowInstance;
import org.alfresco.service.cmr.workflow.WorkflowPath;
import org.alfresco.service.cmr.workflow.WorkflowTask;
import org.alfresco.service.cmr.workflow.WorkflowTaskDefinition;
import org.alfresco.service.cmr.workflow.WorkflowTaskState;
import org.alfresco.service.namespace.QName;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.util.XMLCatalogResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.xforms.controller.alfresco.agents.MappingAgent;
import com.bluexml.xforms.controller.alfresco.agents.SystemAgent;
import com.bluexml.xforms.controller.beans.EditNodeBean;
import com.bluexml.xforms.controller.beans.GetInstanceFormBean;
import com.bluexml.xforms.controller.beans.ListActionBean;
import com.bluexml.xforms.controller.beans.PersistFormResultBean;
import com.bluexml.xforms.controller.beans.RedirectionBean;
import com.bluexml.xforms.controller.beans.WorkflowTaskInfoBean;
import com.bluexml.xforms.controller.binding.AssociationType;
import com.bluexml.xforms.controller.navigation.FormTypeEnum;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

/**
 * The Class AlfrescoController.
 */
public class AlfrescoController implements AlfrescoControllerAPI {

	private static final String EMPTY_STRING = "<empty string>";

	private static final String NULL_STRING = "<null string>";

	private static final String TRACE_LOGGER_CATEGORY = "com.bluexml.xforms.controller.alfresco.AlfrescoController.trace";

	/** The upload base directory in the file system. */
	public static File UPLOAD_DIRECTORY = null;

	/** Depth of the random path where to distribute uploaded files. */
	public static int UPLOAD_DIRECTORY_RANDOM_PATH_DEPTH = 3;

	/** The upload base directory in the content management system. */
	public static String UPLOAD_REPOSITORY = null;

	/**
	 * Whether file names of uploads receive a '(x)' in case the initial name
	 * already exists.
	 */
	public static boolean UPLOAD_REPOSITORY_APPEND = true;

	/**
	 * whether info of repository uploads are formatted in the same way as
	 * content information
	 */
	public static boolean UPLOAD_REPOSITORY_FORMAT_INFO = false;

	/** whether to check that the form being opened matches the data id */
	public static boolean CHECK_MATCH_DATA_FORM = true;

	/** The temp directory. */
	public static File TEMP_DIRECTORY = null;

	public static int MAX_RESULTS = 50;

	/** The alfresco url. */
	public static String ALFRESCO_URL = null;

	/** The alfresco xforms url. */
	public static String ALFRESCO_XFORMS_URL = null;

	/** The default user name for transactions */
	public static String USER_NAME = null;

	/** The user password */
	public static String USER_PSWD = null;

	/**
	 * The base folder for loading forms, enums, etc. Parent of 'forms',
	 * 'resources, 'WEB-INF'.
	 */
	public static String BASE_FOLDER = null;
	//
	//
	private static final String BLUEXML_WORKFLOW_PREFIX = "wfbx";

	private static final XStream xstream = new XStream();

	/** The logger. */
	protected static Log logger = LogFactory.getLog(AlfrescoController.class);

	/**
	 * We'll use this only as a marker for trace enabled status, not as an
	 * actual logger.
	 */
	public static Log loggertrace = LogFactory.getLog(TRACE_LOGGER_CATEGORY);

	/** The doc builder. */
	private static DocumentBuilder docBuilder = null;

	/** The controller. */
	private static AlfrescoController instance = null;

	//
	// general settings that are persisted through sessions and users
	private static String formsPropertiesPath = null;
	private static String messagesPropertiesPath = null;
	private static String CssUrl = null;
	private static String redirectXmlPath = null;
	//

	/** whether calls to the webscript are intercepted */
	private static boolean standaloneMode = false;

	/** Stores redirection info keyed by form names */
	private static Map<String, RedirectionBean> targetTable = new HashMap<String, RedirectionBean>();

	/** The config. */
	protected Properties config = null;

	private MappingAgent mappingAgent;
	private SystemAgent systemAgent;

	static {
		// set the document builder and catalogs.
		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// ** #1330
			List<String> catalogs = new ArrayList<String>(8);
			URL xhtml_strict = AlfrescoController.class.getResource("/xhtml1/catalog.xml");
			catalogs.add(xhtml_strict.toString());
			XMLCatalogResolver resolver = new XMLCatalogResolver();
			resolver.setPreferPublic(true);
			resolver.setCatalogList(catalogs.toArray(new String[0]));
			docBuilder.setEntityResolver(resolver);
			// ** #1330
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e);
			}
			throw new RuntimeException(e);
		}

		try {
			instance = new AlfrescoController();
		} catch (Exception e1) {
			throw new RuntimeException("Failed to create the Alfresco Controller instance.", e1);
		}
		messagesPropertiesPath = null;
		formsPropertiesPath = null;
		CssUrl = null;
		standaloneMode = false;

		// statically load properties
		try {
			// loadMappingXml(); // already done by the mapping agent of the singleton instance
			loadProperties(formsPropertiesPath, messagesPropertiesPath);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e);
			}
			throw new RuntimeException(e);
		}
	}

	/**
	 * @return the docBuilder
	 */
	public static DocumentBuilder getDocBuilder() {
		return docBuilder;
	}

	/**
	 * Loads the properties files and passes them to the appropriate processor
	 * for parsing.
	 * 
	 * @return true if both files have been loaded. If any of the files was
	 *         found neither at the
	 *         specified location or at the default location, returns false. If
	 *         true, the files may
	 *         have been loaded from default locations instead of the given
	 *         paths.
	 */
	public static boolean loadProperties(String formsFilePath, String messagesFilePath) {

		//
		// forms.properties
		boolean resForms;
		if (StringUtils.trimToNull(formsFilePath) != null) { // we may be setting a new path
			try {
				File theFile = new File(formsFilePath);
				InputStream stream = new FileInputStream(theFile);
				resForms = loadPropertiesFormsFromStream(stream);
				// keep the path so that a subsequent reload does not require re-giving the path
				formsPropertiesPath = formsFilePath;
			} catch (Exception e) {
				if (logger.isWarnEnabled()) {
					logger.warn("Configuration file 'forms.properties' not found at '" + formsFilePath + "'. Will use defaults.", e);
				}
				resForms = loadPropertiesFormsDefault();
			}
		} else if (StringUtils.trimToNull(formsPropertiesPath) != null) { // reusing previous path
			try {
				File theFile = new File(formsPropertiesPath);
				InputStream stream = new FileInputStream(theFile);
				resForms = loadPropertiesFormsFromStream(stream);
			} catch (Exception e) {
				if (logger.isWarnEnabled()) {
					logger.warn("Configuration file 'forms.properties' not found at last location " + formsPropertiesPath, e);
				}
				resForms = loadPropertiesFormsDefault();
			}
		} else {
			resForms = loadPropertiesFormsDefault();
		}
		if (resForms == false) {
			return false;
		}

		// messages.properties
		InputStream streamMsgs;
		if (StringUtils.trimToNull(messagesFilePath) != null) {
			try {
				File theFile = new File(messagesFilePath);
				streamMsgs = new FileInputStream(theFile);
				messagesPropertiesPath = messagesFilePath;
			} catch (Exception e) {
				if (logger.isWarnEnabled()) {
					logger.warn("Configuration file 'messages.properties' not found at '" + messagesFilePath + "'. Will use defaults.", e);
				}
				streamMsgs = loadPropertiesMessagesDefaults();
			}
		} else if (StringUtils.trimToNull(messagesPropertiesPath) != null) {
			try {
				File theFile = new File(messagesPropertiesPath);
				streamMsgs = new FileInputStream(theFile);
			} catch (Exception e) {
				if (logger.isWarnEnabled()) {
					logger.error("Configuration file 'messages.properties' not found at last location " + messagesPropertiesPath, e);
				}
				streamMsgs = loadPropertiesMessagesDefaults();
			}
		} else {
			streamMsgs = loadPropertiesMessagesDefaults();
		}

		if (streamMsgs == null) {
			return false;
		}
		MsgPool.setInputStream(streamMsgs);
		return true;
	}

	private static boolean loadPropertiesFormsDefault() {
		// get the default file
		URL formsURL = AlfrescoController.class.getResource("/forms.properties");
		if (formsURL == null) {
			logger.error("Configuration file 'forms.properties' not found in WEB-INF/classes. Null URL received from system.");
			return false;
		}
		try {
			File formsFile = new File(new URI(formsURL.toString()));
			InputStream stream = new FileInputStream(formsFile);
			return loadPropertiesFormsFromStream(stream);
		} catch (URISyntaxException e) {
			// I don't think this will ever be reached
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			if (logger.isErrorEnabled()) {
				logger.error("Configuration file 'forms.properties' not found in WEB-INF/classes.", e);
			}
		}
		return false;
	}

	private static InputStream loadPropertiesMessagesDefaults() {
		// get the default file
		URL msgURL = AlfrescoController.class.getResource("/messages.properties");
		if (msgURL == null) {
			if (logger.isErrorEnabled()) {
				logger.error("Configuration file 'messages.properties' not found in WEB-INF/classes. Null URL received from system.");
			}
			return null;
		}
		try {
			File formsFile = new File(new URI(msgURL.toString()));
			InputStream stream = new FileInputStream(formsFile);
			return stream;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			if (logger.isErrorEnabled()) {
				logger.error("Configuration file 'messages.properties' not found in WEB-INF/classes.", e);
			}
		}
		return null;
	}

	/**
	 * Loads the properties from the given stream and initializes default
	 * values.
	 * 
	 * @param stream
	 * @return
	 */
	private static boolean loadPropertiesFormsFromStream(InputStream stream) {
		Properties config = new Properties();
		try {
			config.load(stream);
			instance.initConfig(config);
			return true;
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("Failed in loading and initializing 'forms.properties'.", e);
			}
			return false;
		}
	}

	/**
	 * Gets the single instance of AlfrescoController.
	 * 
	 * @return single instance of AlfrescoController
	 */
	public static AlfrescoController getInstance() {
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public AlfrescoControllerAPI getController() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String patchDataId(String dataId) {
		String result = null;
		if (dataId != null) {
			if (!dataId.startsWith("workspace://SpacesStore/")) {
				result = "workspace://SpacesStore/" + dataId;
			} else {
				result = dataId;
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String unpatchDataId(String dataId) {
		int pos = dataId.lastIndexOf('/');
		if (pos == -1) {
			return null;
		}
		return dataId.substring(pos + 1);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String getAlfrescoUrl() {
		return ALFRESCO_URL;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public void setAlfrescoUrl(String alfrescoUrl) {
		ALFRESCO_URL = alfrescoUrl;
	}

	/**
	 * Instantiates a new Alfresco controller.
	 * 
	 * @throws Exception
	 */
	private AlfrescoController() throws Exception {
		super();
		// singleton

		// reference the agents
		systemAgent = new SystemAgent(this, xstream);
		mappingAgent = new MappingAgent(this);

		loadRedirectionTable(null);
	}

	//
	//
	// SystemAgent
	//
	//

	public Set<String> systemGetAllAuthoritiesAsGroupsOrUsers(AlfrescoTransaction transaction, boolean asGroups) {
		return systemAgent.getAllAuthoritiesAsGroupsOrUsers(transaction, asGroups);
	}

	public Set<String> systemGetContainingGroups(AlfrescoTransaction transaction, String userName) {
		return systemAgent.getContainingGroups(transaction, userName);
	}

	public String systemGetNodeProperty(AlfrescoTransaction transaction, NodeRef node, QName propertyName) {
		return systemAgent.getNodeProperty(transaction, node, propertyName);
	}

	public NodeRef systemGetNodeRefForUser(AlfrescoTransaction transaction, String userName) {
		return systemAgent.getNodeRefForUser(transaction, userName);
	}

	public NodeRef systemGetNodeRefForGroup(AlfrescoTransaction transaction, String groupName) {

		return systemAgent.getNodeRefForGroup(transaction, groupName);
	}

	public QName systemGetNodeType(AlfrescoTransaction transaction, String dataId) {
		return systemAgent.getNodeType(transaction, dataId);
	}

	//
	//
	// MappingAgent
	//
	//

	/**
	 * @return the mappingAgent
	 */
	public MappingAgent getMappingAgent() {
		return mappingAgent;
	}

	/**
	 * Creates or loads the XForms instance document for a default class form.
	 * 
	 * @param transaction
	 *            the transaction
	 * @param formName
	 *            the content type
	 * @param dataId
	 *            the node id
	 * @param idAsServlet
	 *            whether the request comes from a servlet
	 * @return the class
	 * @throws ServletException
	 */
	public Document getInstanceClass(AlfrescoTransaction transaction, String formName, String dataId, boolean formIsReadOnly, boolean isServletRequest) throws ServletException {
		return mappingAgent.getInstanceClass(transaction, formName, dataId, formIsReadOnly, isServletRequest);
	}

	/**
	 * Creates or loads the XForms instance document for a FormClass (a
	 * customized form).
	 * 
	 * @param transaction
	 *            the transaction
	 * @return the form instance
	 * @throws ServletException
	 */
	public Document getInstanceForm(AlfrescoTransaction transaction, GetInstanceFormBean bean) throws ServletException {
		return mappingAgent.getInstanceForm(transaction, bean);
	}

	public Document getInstanceWorkflow(AlfrescoTransaction transaction, String formName) {
		return mappingAgent.getInstanceWorkflow(transaction, formName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public Document getInstanceSearch(String formName) {
		return mappingAgent.getInstanceSearch(formName);
	}

	/**
	 * Gets the XForms instance document for an object.
	 * 
	 * @param transaction
	 *            the transaction
	 * @param id
	 *            the id
	 * @param stack
	 *            the stack
	 * @param isServletRequest
	 * @return the element
	 * @throws ServletException
	 */
	public Document getObjectInstance(AlfrescoTransaction transaction, String id, Stack<AssociationType> stack, boolean formIsReadOnly, boolean isServletRequest) throws ServletException {
		Document alfrescoNode = readObjectFromRepository(transaction, id);

		return mappingAgent.transformAlfrescoToClassForms(transaction, alfrescoNode, stack, formIsReadOnly, isServletRequest);
	}

	/**
	 * Reads an object form Alfresco.
	 * 
	 * @param transaction
	 *            the transaction.
	 * @param dataId
	 *            the id
	 * @return the document
	 * @throws ServletException
	 */
	public Document readObjectFromRepository(AlfrescoTransaction transaction, String dataId) throws ServletException {
		Map<String, String> parameters = new TreeMap<String, String>();
		parameters.put("objectId", patchDataId(dataId));
		return requestDocumentFromAlfresco(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_READ);
	}

	/**
	 * Initializes configuration elements by interpreting the configuration
	 * properties file.<br/>
	 * NOTE: the properties file has already been read.
	 * 
	 * @param config
	 *            the properties from the configuration file
	 */
	public void initConfig(Properties config) {
		String fsPath;
		this.config = config;

		// user name and password. We don't provide hard coded defaults for these.
		USER_NAME = config.getProperty(MsgId.KEY_USER_NAME.getText());
		USER_PSWD = config.getProperty(MsgId.KEY_USER_PSWD.getText());

		// temp dir for file system uploads
		fsPath = config.getProperty(MsgId.KEY_TEMP_DIRECTORY.getText());
		if (StringUtils.trimToNull(fsPath) == null) {
			fsPath = "/tmp"; // TODO: check that this works on all platforms
		}
		TEMP_DIRECTORY = new File(fsPath);

		// file system archive folder for uploads. If none, default to predefined folder.
		fsPath = config.getProperty(MsgId.KEY_UPLOAD_DIRECTORY.getText());
		if (StringUtils.trimToNull(fsPath) == null) {
			fsPath = "/tmp/uploads"; // TODO: check on all platforms
		}
		UPLOAD_DIRECTORY = new File(fsPath);

		// repo folder for uploads, if none given, use "/app:company_home/app:user_homes"
		UPLOAD_REPOSITORY = config.getProperty(MsgId.KEY_UPLOAD_REPOSITORY.getText());
		if (StringUtils.trimToNull(UPLOAD_REPOSITORY) == null) {
			UPLOAD_REPOSITORY = "/app:company_home/app:user_homes";
		}

		// max results: default number of items in lists
		String property = config.getProperty(MsgId.KEY_MAX_RESULTS.getText());
		try {
			int value = Integer.parseInt(property);
			MAX_RESULTS = value;
		} catch (NumberFormatException e) {
			if (logger.isErrorEnabled()) {
				logger.error("Can't parse the value '" + property + "' for key '" + MsgId.KEY_MAX_RESULTS + "'. Will revert to the default value.", e);
			}
			MAX_RESULTS = 50;
		}

		// whether to check matching of form vs data
		property = config.getProperty(MsgId.KEY_CHECK_MATCH_DATA_FORM.getText());
		CHECK_MATCH_DATA_FORM = !(StringUtils.equals(property, "false"));

		// whether to append ordering suffix to file names
		property = config.getProperty(MsgId.KEY_UPLOAD_REPOSITORY_APPEND.getText());
		UPLOAD_REPOSITORY_APPEND = !(StringUtils.equals(property, "false"));

		// whether to format info of repo uploads like the info of node content
		property = config.getProperty(MsgId.KEY_UPLOAD_REPOSITORY_FORMAT_INFO.getText());
		UPLOAD_REPOSITORY_FORMAT_INFO = StringUtils.equals(property, "true");

		// depth of the random path
		property = config.getProperty(MsgId.KEY_UPLOAD_DIR_PATH_DEPTH.getText());
		try {
			int depth = Integer.parseInt(property);
			UPLOAD_DIRECTORY_RANDOM_PATH_DEPTH = depth;
		} catch (NumberFormatException e) {
			if (logger.isErrorEnabled()) {
				logger.error("Can't parse the value '" + property + "' for key '" + MsgId.KEY_UPLOAD_DIR_PATH_DEPTH + "'. Will revert to the default value.", e);
			}
			UPLOAD_DIRECTORY_RANDOM_PATH_DEPTH = 3;
		}

		checkDirectoryExists(UPLOAD_DIRECTORY, true);
		checkDirectoryExists(TEMP_DIRECTORY, false);

		ALFRESCO_URL = config.getProperty(MsgId.KEY_ALFRESCO_URL.getText());
		ALFRESCO_XFORMS_URL = ALFRESCO_URL + "/service/xforms/";

		BASE_FOLDER = getDefaultBaseFolder();
	}

	/**
	 * Checks whether a directory (from the configuration file) exists. If not,
	 * attempts to create
	 * the paths. If that fails, creates a path under the webapp's folder.
	 * 
	 * @param file
	 *            the file
	 * @param isUploadDir
	 */
	private void checkDirectoryExists(File file, boolean isUploadDir) {
		if (!file.exists()) {
			if (logger.isErrorEnabled()) {
				logger.error(file.getAbsolutePath() + " doesn't exist. Will try to create the path.");
			}
			if (file.mkdirs() == false) {
				String dirName = isUploadDir ? "upload" : "temp";
				if (logger.isErrorEnabled()) {
					logger.error("Couldn't create " + file.getAbsolutePath() + ". Will default to '" + dirName + "' under the webapp's current folder.");
				}
				// we need to create the directory under the webapp's folder
				String dirPath = getDefaultBaseFolder();
				dirPath += File.separator + dirName;
				boolean result;
				if (isUploadDir) {
					UPLOAD_DIRECTORY = new File(dirPath);
					result = UPLOAD_DIRECTORY.mkdirs();
				} else {
					TEMP_DIRECTORY = new File(dirPath);
					result = TEMP_DIRECTORY.mkdirs();
				}
				if (result == false) {
					if (logger.isErrorEnabled()) {
						logger.error("Couldn't create directory " + dirPath + ". Uploads will not perform correctly.");
					}
				}
			}
		}
	}

	/**
	 * Gets the complete filesystem path to the default base folder (which
	 * contains the 'forms' and
	 * 'WEB-INF') folders.
	 * 
	 * @return
	 */
	private String getDefaultBaseFolder() {
		String dirPath = null;
		try {
			File mappingFile = null;
			URL url = AlfrescoController.class.getResource("/mapping.xml");
			mappingFile = new File(new URI(url.toString()));
			dirPath = mappingFile.getAbsolutePath();
			// @Amenel: I am not comfortable with the assumption about the path to
			// mapping.xml: although true today, it may not be so in the future, depending
			// on OS or JVM.
			String subPath = File.separator + "WEB-INF" + File.separator + "classes" + File.separator + "mapping.xml";
			// subPath is "/WEB-INF/classes/mapping.xml" on Mac OS, Unix, Linux
			// subPath is "\WEB-INF\classes\mapping.xml" on Windows
			dirPath = StringUtils.replace(dirPath, subPath, "");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return dirPath;
	}

	/**
	 * Returns the directory for uploading files into the file system. If none
	 * given in initParams
	 * or the directory does not exist, falls back to the value from
	 * configuration file.
	 * 
	 * @param initParams
	 */
	public File getParamUploadPathInFileSystem(Map<String, String> initParams) {

		String result = null;
		if (initParams != null) {
			result = initParams.get(MsgId.PARAM_UPLOAD_DIRECTORY.getText());
		}
		if (StringUtils.trimToNull(result) == null) {
			return UPLOAD_DIRECTORY;
		}
		File resFile = new File(result);
		return resFile.exists() ? resFile : UPLOAD_DIRECTORY;
	}

	/**
	 * Returns a path for uploading files into the repository. If none given in
	 * initParams, falls
	 * back to the value from the configuration file.
	 * 
	 * @param initParams
	 */
	public String getParamUploadPathInRepository(Map<String, String> initParams) {

		String result = null;
		if (initParams != null) {
			result = initParams.get(MsgId.PARAM_UPLOAD_REPOSITORY.getText());
		}
		return (result != null) ? result : UPLOAD_REPOSITORY;
	}

	//
	// for the next functions, see MsgId.
	//
	public String getParamBaseFolder(Map<String, String> initParams) {
		String result = null;
		if (initParams != null) {
			result = initParams.get(MsgId.PARAM_BASE_FOLDER.getText());
		}
		return (StringUtils.trimToNull(result) == null) ? BASE_FOLDER : result;
	}

	public boolean getParamUploadRepoAppendSuffix(Map<String, String> initParams) {
		String paramStr = null;
		if (initParams != null) {
			paramStr = initParams.get(MsgId.PARAM_UPLOAD_REPOSITORY_APPEND.getText());
		}
		if (StringUtils.trimToNull(paramStr) != null) {
			return !(StringUtils.equals(paramStr, "false"));
		}
		return UPLOAD_REPOSITORY_APPEND;
	}

	public boolean getParamUploadRepoFormatInfo(Map<String, String> initParams) {
		String paramStr = null;
		if (initParams != null) {
			paramStr = initParams.get(MsgId.PARAM_UPLOAD_REPOSITORY_FORMAT.getText());
		}
		if (StringUtils.trimToNull(paramStr) != null) {
			return !(StringUtils.equals(paramStr, "false"));
		}
		return UPLOAD_REPOSITORY_FORMAT_INFO;
	}

	/**
	 * This method check the parameter 'checkMatchDF' in the request.
	 * Use 'checkMatchDF=false' in the case of form Workflow which are not
	 * associated to Form class in order to avoid the various check of data type
	 * 
	 * @param initParams
	 *            the request parameters
	 * @return true if data type check necessary, false otherwise (form workflow
	 *         not associated to form class)
	 */
	public boolean getParamCheckMatchDataForm(Map<String, String> initParams) {
		String paramStr = null;
		if (initParams != null) {
			paramStr = initParams.get(MsgId.PARAM_CHECK_MATCH_DATA_FORM.getText());
		}
		if (StringUtils.trimToNull(paramStr) != null) {
			return !(StringUtils.equals(paramStr, "false"));
		}
		return CHECK_MATCH_DATA_FORM;
	}

	public String getParamUserName(Map<String, String> initParams) {
		String result = null;
		if (initParams != null) {
			result = initParams.get(MsgId.PARAM_USER_NAME.getText());
		}
		return (StringUtils.trimToNull(result) == null) ? USER_NAME : result;
	}

	public String getParamUserPswd(Map<String, String> initParams) {
		String result = null;
		if (initParams != null) {
			result = initParams.get(MsgId.PARAM_USER_PSWD.getText());
		}
		return (StringUtils.trimToNull(result) == null) ? USER_PSWD : result;
	}

	public int getParamMaxResults(Map<String, String> initParams) {
		int result = MAX_RESULTS;
		if (initParams != null) {
			String resultStr = initParams.get(MsgId.PARAM_MAX_RESULTS.getText());
			if (StringUtils.trimToNull(resultStr) != null) {
				try {
					result = Integer.parseInt(resultStr);
				} catch (NumberFormatException e) {
					if (logger.isErrorEnabled()) {
						logger.error("Can't parse the value '" + resultStr + "' for parameter '" + MsgId.PARAM_MAX_RESULTS + "'. Will revert to the previous value.", e);
					}
					result = MAX_RESULTS;
				}
			}
		}
		return result;
	}

	/**
	 * Provides the number of directory levels into which filesystem uploads
	 * should be randomly
	 * distributed.
	 * 
	 * @param initParams
	 * @return
	 */
	public int getParamUploadPathDepth(Map<String, String> initParams) {

		String result = null;
		if (initParams != null) {
			result = initParams.get(MsgId.PARAM_UPLOAD_DEPTH.getText());
		}
		if (StringUtils.trimToNull(result) == null) {
			return UPLOAD_DIRECTORY_RANDOM_PATH_DEPTH;
		}
		try {
			return Integer.parseInt(result);
		} catch (NumberFormatException e) {
			return UPLOAD_DIRECTORY_RANDOM_PATH_DEPTH;
		}
	}

	/**
	 * Persists a search form: collects the search info on search forms.
	 * 
	 * @param transaction
	 * @param instance
	 * @param initParams
	 * @param isServletRequest
	 * @return
	 * @throws ServletException
	 */
	public String persistSearch(String formName, Node instance, boolean shortPropertyNames, Map<String, String> initParams) throws ServletException {
		return mappingAgent.persistSearch(formName, instance, shortPropertyNames, initParams);
	}

	/**
	 * Persists a class form: transforms the XForms instance into a
	 * GenericClass, and either saves
	 * or updates a node.
	 * 
	 * @param instance
	 *            the instance
	 * @param transaction
	 *            the transaction
	 * @param isServletRequest
	 * @param initParams
	 * @return the object id as given by the transaction manager
	 * @throws ServletException
	 */
	public PersistFormResultBean persistClass(AlfrescoTransaction transaction, Node instance, boolean isServletRequest, Map<String, String> initParams) throws ServletException {
		return mappingAgent.persistClass(transaction, instance, isServletRequest, initParams);
	}

	/**
	 * Persists a FormClass: transforms the XForms instance into a GenericClass,
	 * and either saves or
	 * updates a node.
	 * 
	 * @param type
	 *            the type
	 * @param instance
	 *            the instance
	 * @param transaction
	 *            the transaction
	 * @param initParams
	 * @param isMassTagging
	 * @return the temporary id assigned to the class
	 * @throws ServletException
	 */
	public PersistFormResultBean persistForm(AlfrescoTransaction transaction, String type, Node instance, Map<String, String> initParams, boolean isMassTagging) throws ServletException {
		return mappingAgent.persistForm(transaction, type, instance, initParams, isMassTagging);
	}

	/**
	 * Returns the data on the form in a specific JSON format. This is to be
	 * used with forms other
	 * than FormSearch's when called in search mode. If the form is a
	 * FormSearch, use
	 * {@link #persistSearch(AlfrescoTransaction, Node, boolean)}
	 * 
	 * @param transaction
	 * @param formName
	 * @param instance
	 * @param initParams
	 * @return a JSON string
	 * @throws ServletException
	 */
	public String persistFormJSON(AlfrescoTransaction transaction, String formName, Node instance, boolean shortPropertyNames, Map<String, String> initParams) throws ServletException {
		return mappingAgent.persistFormToJSON(transaction, formName, instance, shortPropertyNames, initParams);
	}

	/**
	 * Builds a GenericClass object from the instance of a workflow and
	 * processes the possible
	 * upload fields.
	 * 
	 * @param transaction
	 * @param taskTypeName
	 *            the id of the workflow form
	 * @param taskElt
	 *            the root element containing the workflow properties
	 * @param initParams
	 * @return
	 * @throws ServletException
	 */
	public PersistFormResultBean persistWorkflow(AlfrescoTransaction transaction, String taskTypeName, Node taskElt, Map<String, String> initParams) throws ServletException {
		return mappingAgent.persistWorkflow(transaction, taskTypeName, taskElt, initParams);
	}

	public void executeBatch(AlfrescoTransaction alfrescoTransaction) throws ServletException {
		Map<String, String> parameters = new TreeMap<String, String>();
		parameters.put("datas", MappingAgent.marshalBatch(alfrescoTransaction));

		Map<String, String> ids = new HashMap<String, String>();

		Document result = requestDocumentFromAlfresco(alfrescoTransaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_BATCH);
		if (result == null) {
			throw new ServletException(MsgId.INT_MSG_ALFRESCO_SERVER_DOWN.getText());
		}
		Element idsElement = result.getDocumentElement();
		NodeList childNodes = idsElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node idNode = childNodes.item(i);
			if (idNode instanceof Element) {
				Element idElement = (Element) idNode;
				String to = null;
				String from = null;
				NodeList idChildNodes = idElement.getChildNodes();
				for (int j = 0; j < idChildNodes.getLength(); j++) {
					Node node = idChildNodes.item(j);
					if (node instanceof Element) {
						Element element = (Element) node;
						if (StringUtils.equals(element.getTagName(), "from")) {
							from = StringUtils.trim(element.getTextContent());
						}
						if (StringUtils.equals(element.getTagName(), "to")) {
							to = StringUtils.trim(element.getTextContent());
						}
					}
				}
				ids.put(from, to);
			}
		}

		alfrescoTransaction.setIds(ids);
	}

	/**
	 * Gets the captions.
	 * 
	 * @param ids
	 *            the ids
	 * @param transaction
	 *            the transaction
	 * @return the captions
	 * @throws ServletException
	 */
	@SuppressWarnings("all")
	public List<String> getCaptions(AlfrescoTransaction transaction, List<String> ids) throws ServletException {
		Map<String, String> parameters = new TreeMap<String, String>();
		parameters.put("query", StringUtils.join(ids, ";"));
		Document requestDocument = requestDocumentFromAlfresco(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_LABELS);
		if (requestDocument == null) {
			throw new ServletException(MsgId.INT_MSG_ALFRESCO_SERVER_DOWN.getText());
		}

		List<String> result = new ArrayList<String>();
		// List elements = DOMUtil.getChildElements(requestDocument.getDocumentElement());
		List<Element> elements = DOMUtil.getAllChildren(requestDocument.getDocumentElement());
		for (Element element : elements) {
			result.add(DOMUtil.getChild(element, "value").getTextContent());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public String getEnumCaption(AlfrescoTransaction transaction, String code) throws ServletException {
		Map<String, String> parameters = new TreeMap<String, String>();
		parameters.put("query", "enum;" + code);
		Document requestDocument = requestDocumentFromAlfresco(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_LABELS);
		if (requestDocument == null) {
			throw new ServletException(MsgId.INT_MSG_ALFRESCO_SERVER_DOWN.getText());
		}

		String result = null;
		List elements = DOMUtil.getAllChildren(requestDocument.getDocumentElement());
		if (elements.size() > 0) {
			Object object = elements.get(0);
			Element element = (Element) object;
			result = DOMUtil.getChild(element, "value").getTextContent();
		}
		return result;
	}

	/**
	 * Gets the enum.
	 * 
	 * @param transaction
	 *            the transaction
	 * @param type
	 *            the type
	 * @param filterParent
	 *            the filter parent
	 * @param filterData
	 *            the filter data
	 * @param query
	 * @return the enum
	 * @throws ServletException
	 */
	public Node getDynamicEnum(AlfrescoTransaction transaction, String type, String filterParent, String filterData, String query, boolean limit) throws ServletException {
		Map<String, String> parameters = new TreeMap<String, String>();
		String fp = StringUtils.trimToNull(filterParent);
		if (fp != null) {
			parameters.put("parent", fp);
		}
		String fd = StringUtils.trimToNull(filterData);
		if (fd != null) {
			parameters.put("context", fd);
		}
		String q = StringUtils.trimToNull(query);
		if (q != null) {
			parameters.put("query", q);
		}
		if (limit) {
			parameters.put("limit", "true");
		}

		parameters.put("type", mappingAgent.getEnumTypeName(type));
		Document reqDoc = requestDocumentFromAlfresco(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_ENUM);
		if (reqDoc == null) {
			throw new ServletException(MsgId.INT_MSG_ALFRESCO_SERVER_DOWN.getText());
		}
		Element docElt = reqDoc.getDocumentElement();

		Element queryElement = reqDoc.createElement("query");
		queryElement.setTextContent(StringUtils.trimToEmpty(query));
		docElt.appendChild(queryElement);
		docElt.appendChild(reqDoc.createElement(MsgId.INT_INSTANCE_SELECTEDID.getText()));
		docElt.appendChild(reqDoc.createElement(MsgId.INT_INSTANCE_SELECTEDLABEL.getText()));
		return reqDoc;
	}

	/**
	 * Gets the result set for the list action by calling the webscript. This
	 * function is called
	 * when a form is loaded (to provide the initial item set), and each time a
	 * search is launched,
	 * whether by character input or by a "launch search" button.
	 * 
	 * @param transaction
	 *            the transaction
	 * @param bean
	 *            a bean for parameters, will allow us to extend the parameter
	 *            set without changing
	 *            the signature
	 * @return the list document
	 * @throws ServletException
	 */
	public Node getList(AlfrescoTransaction transaction, ListActionBean bean) throws ServletException {
		// The use of trimToEmpty is for backward compatibility.
		String dataType = bean.getDataType();
		String query = bean.getQuery();
		String maxResults = bean.getMaxResults();
		String format = StringUtils.trimToEmpty(bean.getFormat());
		String maxLength = bean.getMaxLength();
		String identifier = StringUtils.trimToEmpty(bean.getIdentifier());
		String filterAssoc = StringUtils.trimToEmpty(bean.getFilterAssoc());
		String compositionStatus = StringUtils.trimToEmpty(bean.getCompositionStatus());
		String searchModeStatus = StringUtils.trimToEmpty(bean.getSearchMode());
		String luceneQuery = StringUtils.trimToEmpty(bean.getLuceneQuery());
		String dataSourceURI = StringUtils.trimToNull(bean.getDataSourceURI());

		Map<String, String> parameters = new TreeMap<String, String>();

		String type = null;
		/*
		 * maxSize fixe le nombre d'élements à afficher dans le widget, par
		 * défaut, MAX_RESULTS. Ce
		 * nbre sera réinitialisé par le bouton 'Tout'. Valeurs possibles:
		 * MAX_RESULTS ("50" ou
		 * celle indiquée ds forms.properties) ou fixé par la propriété 'field
		 * size' dans le
		 * modeleur. Dans ce cas, SELECTMAX conserve tjrs la valeur de field
		 * size.
		 */
		String maxSize = mappingAgent.getFieldSizeForField(dataType, "" + getParamMaxResults(transaction.getInitParams()), transaction.getFormId());
		type = (identifier == null ? mappingAgent.getClassTypeAlfrescoName(dataType) : dataType);

		// always provided in the URI
		parameters.put("type", type);

		parameters.put("format", format);

		// always provided in the URI
		parameters.put("maxLength", maxLength);

		parameters.put("identifier", identifier);
		parameters.put("filterAssoc", filterAssoc);

		// always provided in the URI.
		parameters.put("isComposition", compositionStatus);

		// always provided in the URI.
		parameters.put("isSearchMode", searchModeStatus);
		parameters.put("luceneQuery", luceneQuery);

		String q = StringUtils.trimToNull(query);
		if (q != null) {
			parameters.put("query", q);
		}

		if (StringUtils.trimToNull(maxResults) != null) {
			parameters.put("maxResults", maxResults);
		} else {
			parameters.put("maxResults", maxSize);
		}

		Document reqDoc;
		if (isInStandaloneMode()) {
			// NOTE: we use the maxLength for indicating the number of items in the list!
			reqDoc = requestDummyDocumentList(type, maxLength, searchModeStatus);
		} else {

			if (dataSourceURI == null) {
				reqDoc = requestDocumentFromAlfresco(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_LIST);
			} else {
				// do not use default Xform webscript uri
				reqDoc = requestDocumentFromAlfresco(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_LIST, dataSourceURI);
			}

			// ** #1234
			if (reqDoc == null) {
				if (logger.isErrorEnabled()) {
					logger.error("The Alfresco server is unavailable. Returning a dummy list.");
				}
				// setStandaloneMode(true);
				reqDoc = requestDummyDocumentList(type, maxLength, searchModeStatus);
			}
			// ** #1234
		}
		Element docElement = reqDoc.getDocumentElement();

		docElement.appendChild(reqDoc.createElement(MsgId.INT_INSTANCE_SELECTEDID.getText()));
		docElement.appendChild(reqDoc.createElement(MsgId.INT_INSTANCE_SELECTEDLABEL.getText()));
		docElement.appendChild(reqDoc.createElement(MsgId.INT_INSTANCE_SELECTEDTYPE.getText()));

		Element maxResultsElement = reqDoc.createElement(MsgId.INT_INSTANCE_SELECTEDMAX.getText());
		maxResultsElement.setTextContent(maxSize);
		docElement.appendChild(maxResultsElement);

		return reqDoc;

	}

	/**
	 * Returns a fake list built without contacting Alfresco.
	 * 
	 * @param alfrescoName
	 *            the datatype
	 * @param size
	 *            the number of items in the list (will be overridden if search
	 *            mode)
	 * @param searchModeStatus
	 *            "1" if the widget is a search instead of a filter
	 * @return
	 */
	private Document requestDummyDocumentList(String alfrescoName, String size, String searchModeStatus) {
		Document doc = docBuilder.newDocument();
		int nb = Integer.parseInt(size);
		if (nb == 0) {
			nb = 10;
		}
		if (StringUtils.equals(searchModeStatus, "1")) { // override
			nb = 0;
		}

		Element root = doc.createElement("results");

		Element query = doc.createElement("query");
		Element count = doc.createElement("count");
		count.setTextContent("" + nb);

		Element maxResults = doc.createElement("maxResults");
		maxResults.setTextContent("0");

		Element returned = doc.createElement("returned");
		returned.setTextContent("" + nb);

		Element filteredOut = doc.createElement("filteredOut");
		filteredOut.setTextContent("0");

		Element typeFound = doc.createElement("typeFound");
		typeFound.setTextContent("simulated");

		Element subquery = doc.createElement("query");
		query.appendChild(count);
		query.appendChild(maxResults);
		query.appendChild(returned);
		query.appendChild(filteredOut);
		query.appendChild(typeFound);
		query.appendChild(subquery);

		root.appendChild(query);

		for (int i = 0; i < nb; i++) {
			Element item = doc.createElement(MsgId.INT_INSTANCE_SELECT_ITEM.getText());
			Element id = doc.createElement(MsgId.INT_INSTANCE_SELECT_ID.getText());
			id.setTextContent("" + i);
			Element label = doc.createElement(MsgId.INT_INSTANCE_SELECT_LABEL.getText());
			label.setTextContent(alfrescoName + "_" + i);
			Element qname = doc.createElement(MsgId.INT_INSTANCE_SELECT_TYPE.getText());
			qname.setTextContent("type_" + alfrescoName + "_" + i);

			item.appendChild(id);
			item.appendChild(label);
			item.appendChild(qname);

			root.appendChild(item);
		}
		doc.appendChild(root);
		return doc;
	}

	/**
	 * Provides a bridge to the webscript, returning a String. //$$ NON-API
	 * 
	 * @param parameters
	 *            the parameters
	 * @param opCode
	 *            the opCode
	 * @param transaction
	 *            the transaction
	 * @return the string
	 * @throws ServletException
	 */
	public String requestString(AlfrescoTransaction transaction, Map<String, String> parameters, MsgId opCode) throws ServletException {
		String result = null;
		try {
			PostMethod post = requestPost(transaction, parameters, opCode);
			result = StringUtils.trim(post.getResponseBodyAsString());
		} catch (ConnectException e) {
			throw new ServletException(MsgId.INT_MSG_ALFRESCO_SERVER_DOWN.getText());
		} catch (IOException e) {
			if (logger.isErrorEnabled()) {
				logger.error("Caught exception while requesting string from Alfresco", e);
			}
			throw new ServletException(MsgPool.getMsg(MsgId.MSG_ERROR_DEFAULT_MSG));
		}
		if (result != null && result.startsWith("<exception>")) {
			throw new AlfrescoWebscriptException(result);
		}
		return result;
	}

	/**
	 * @param transaction
	 * @param parameters
	 * @param opCode
	 * @return
	 * @throws ServletException
	 */
	private Document requestDocumentFromAlfresco(AlfrescoTransaction transaction, Map<String, String> parameters, MsgId opCode) throws ServletException {
		return requestDocumentFromAlfresco(transaction, parameters, opCode, null);
	}

	/**
	 * Provides a bridge to the webscript, returning a Document.
	 * 
	 * @param transaction
	 *            the transaction
	 * @param parameters
	 *            the parameters
	 * @param opCode
	 *            the opCode
	 * @param dataSourceURI
	 * @return the document
	 * @throws ServletException
	 */
	private Document requestDocumentFromAlfresco(AlfrescoTransaction transaction, Map<String, String> parameters, MsgId opCode, String dataSourceURI) throws ServletException {
		Document result = null;
		PostMethod post;

		// request the document from the webscript @ Alfresco
		try {
			post = requestPost(transaction, parameters, opCode, dataSourceURI);
		} catch (ConnectException e) { // #1234
			if (!isInDebugMode()) {
				throw new ServletException(MsgId.INT_MSG_ALFRESCO_SERVER_DOWN.getText());
			}
			return null;
		} catch (ServletException se) {
			throw se;
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("Caught exception while requesting document from Alfresco", e);
			}
			throw new ServletException(MsgPool.getMsg(MsgId.MSG_ERROR_DEFAULT_MSG));
		}
		

		// parse the result into a document
		try {

			if (logger.isDebugEnabled()) {
				String response = post.getResponseBodyAsString();
				logger.debug(response);
			}

			result = synchronizedParse(post.getResponseBodyAsStream());
		} catch (IOException e) {
			logger.error("Exception while parsing the document requested from Alfresco", e);
			throw new ServletException(MsgPool.getMsg(MsgId.MSG_ERROR_DEFAULT_MSG));
		} catch (SAXException e) {
			logger.error("Exception while parsing the document requested from Alfresco", e);
			throw new ServletException(MsgPool.getMsg(MsgId.MSG_ERROR_DEFAULT_MSG));
		}
		if (result != null) {
			if (StringUtils.equalsIgnoreCase("exception", result.getDocumentElement().getTagName())) {
				throw new AlfrescoWebscriptException(result, transaction);
			}
		}
		return result;
	}

	/**
	 * @param is
	 *            the input stream to parse from
	 * @return the document built from the stream
	 * @throws IOException
	 * @throws SAXException
	 */
	// #1227
	synchronized public Document synchronizedParse(InputStream is) throws IOException, SAXException {
		return docBuilder.parse(is);
	}

	/**
	 * @param transaction
	 * @param parameters
	 * @param opCode
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	private PostMethod requestPost(AlfrescoTransaction transaction, Map<String, String> parameters, MsgId opCode) throws IOException, ServletException {
		return requestPost(transaction, parameters, opCode, null);
	}

	/**
	 * Request post. Bridge to our XForms webscript under Alfresco. //$$ TRACE
	 * LOG
	 * 
	 * @param transaction
	 *            the transaction. MANDATORY and NEVER <code>null</code>
	 * @param parameters
	 *            the parameters
	 * @param opCode
	 *            the code for the webscript operation being called.
	 * @param dataSourceURI
	 * @return the post method
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws HttpException
	 *             the http exception
	 * @throws ServletException
	 */
	private PostMethod requestPost(AlfrescoTransaction transaction, Map<String, String> parameters, MsgId opCode, String dataSourceURI) throws IOException, ServletException {
		//
		// security: enforce use of possible access controls on the Alfresco side.
		if (transaction == null) {
			logger.error("Null transaction.");
			throw new ServletException("A transaction is required for webscript requests.");
		}
		String legitimateLogin = transaction.getLogin();
		if (legitimateLogin == null) {
			legitimateLogin = getParamUserName(transaction.getPage().getInitParams());
		}
		if (StringUtils.trimToNull(legitimateLogin) == null) {
			logger.error("No user name in the transaction.");
			throw new ServletException("Cannot complete the action: a user name is required for webscript requests.");
		}

		//
		// log some info
		if (loggertrace.isTraceEnabled()) {
			logger.trace("Calling the webscript for user '" + legitimateLogin + "' with request: " + opCode);
			logger.trace("Parameters : ");
			Set<Entry<String, String>> entrySet2 = parameters.entrySet();
			for (Entry<String, String> entry2 : entrySet2) {
				String value = entry2.getValue();
				if (value == null) {
					value = NULL_STRING;
				} else if (value.equals("")) {
					value = EMPTY_STRING;
				}
				logger.trace("  " + entry2.getKey() + " = " + value);
			}
		}

		// set url
		String url;
		boolean useDataSource = dataSourceURI != null && dataSourceURI.startsWith("http");
		if (useDataSource) {
			// it's possible that url have parameters so to avoid conflic with sidereader parameters '&' is replaced by '#'			
			url = dataSourceURI.replaceAll("@\\$@", "&");
		} else {
			url = ALFRESCO_XFORMS_URL + opCode;
		}
		PostMethod post = new PostMethod(url);
		if (!useDataSource) {
			Set<Entry<String, String>> entrySet = parameters.entrySet();
			for (Entry<String, String> entry : entrySet) {
				post.setParameter(entry.getKey(), entry.getValue());
			}
			// if (StringUtils.trimToNull(transaction.getLogin()) == null) {
			// post.setParameter("username", getParamLoginUserName(transaction.getInitParams()));
			// } else {
			// post.setParameter("username", transaction.getLogin());
			// }
			post.setParameter("username", transaction.getLogin());

			post.setParameter("serviceCallerId", "XFormsController");
			post.setParameter("serviceCallerVersion", "2.0.0");
		} else {
			Gson gson = new Gson();
			String json = gson.toJson(parameters);
			post.setParameter("xformsParameters", json);
		}

		post.addRequestHeader("Content-Type", PostMethod.FORM_URL_ENCODED_CONTENT_TYPE + "; charset=UTF-8");

		executeMethod(post, false);

		if (loggertrace.isTraceEnabled()) {
			logger.trace("Response : ");
			String responseBodyAsString = post.getResponseBodyAsString();
			if (responseBodyAsString == null) {
				logger.trace(NULL_STRING);
			} else if (responseBodyAsString.equals("")) {
				logger.trace(EMPTY_STRING);
			} else {
				logger.trace(responseBodyAsString);
			}
		}

		return post;
	}

	/**
	 * Executes an HTTP method.
	 * 
	 * @param method
	 *            the method
	 * @param hasTicket
	 *            the has ticket
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws HttpException
	 *             the http exception
	 */
	private void executeMethod(HttpMethodBase method, boolean hasTicket) throws IOException, HttpException {
		HttpClient client = new HttpClient();
		client.executeMethod(method);
	}

	/**
	 * Enqueues a delete operation for the current transaction.
	 * 
	 * @param nodeRef
	 *            the node ref
	 * @param transaction
	 *            the transaction
	 */
	public void delete(AlfrescoTransaction transaction, String nodeRef) {
		transaction.queueDelete(nodeRef);
	}

	/**
	 * Removes the reference.
	 * 
	 * @param node
	 *            the node
	 * @param elementId
	 *            the element id
	 */
	public void removeReference(Document node, String elementId) {
		mappingAgent.removeReference(node, elementId);
	}

	/**
	 * Checks whether a datatype has sub types.
	 * 
	 * @param dataType
	 *            the data type
	 * @return true, if successful
	 */
	public boolean hasSubTypes(String dataType) {
		return mappingAgent.hasSubTypes(dataType);
	}

	/**
	 * Checks if is an enumeration is dynamic.
	 * 
	 * @param type
	 *            the type
	 * @return true, if is dynamic enum
	 */
	public boolean isDynamicEnum(String type) {
		return mappingAgent.isDynamicEnum(type);
	}

	/**
	 * Gets the label under which an association is displayed on a specific
	 * form. The label is
	 * indicated for the model choice field in the modeler.
	 * 
	 * @param completeAssoName
	 * @param dataType
	 *            the form id, i.e. a class name or form name in the mapping.xml
	 *            file
	 * @return
	 */
	public String getShortAssociationName(String completeAssoName, String dataType) {
		return mappingAgent.getDisplayLabelFromAssociationName(completeAssoName, dataType);
	}

	/**
	 * Extracts the id to be edited from an XForms instance. The DOM node
	 * providing that id must be
	 * reset (i.e. emptied) so that subsequent editions can happen correctly on
	 * the same form.
	 * 
	 * @param node
	 * @return the id, or null (this latter case should not happen if the Edit
	 *         button's action of
	 *         setting the <edit id> in the instance is done correctly).
	 */
	public EditNodeBean getEditNodeAndReset(Node node) {
		Element rootElt = ((Document) node).getDocumentElement();
		// find the edit id element
		Element editIdElt = DOMUtil.getElementInDescentByNameNonNull(rootElt, MsgId.INT_INSTANCE_SIDEEDIT.getText());
		if (editIdElt != null) {
			String id = editIdElt.getTextContent();
			editIdElt.setTextContent(null); // <- reset the id. MANDATORY.

			// get the data type // #1510
			String dataType = null;
			try {
				Element parent = (Element) editIdElt.getParentNode();
				Element typeElt = DOMUtil.getChild(parent, MsgId.INT_INSTANCE_SIDETYPE.getText());
				if (typeElt != null) {
					dataType = typeElt.getTextContent();
				}
			} catch (Exception e) {
				// nothing to do
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Getting edit id, found: '" + id + "' with data type: '" + dataType + "'");
			}
			return new EditNodeBean(id, dataType);
		}
		if (logger.isErrorEnabled()) {
			logger.error("No id found for node edition.");
		}
		return null;
	}

	//
	// additions for workflows
	//
	/**
	 * Starts a workflow and returns the instance id.
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @param workflowDefinitionId
	 * @param attributes
	 *            Qualified Alfresco Attributes
	 * @return the id of the newly created workflow instance, or
	 *         <code>null</code> if problem.
	 */
	public String workflowStart(AlfrescoTransaction transaction, String workflowDefinitionId, Map<QName, Serializable> attributes) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("wfName", workflowDefinitionId);
		parameters.put("attributes", attributes);
		return (String) workflowRequestCall(transaction, "wfStart", parameters);
	}

	/**
	 * Ends a task by following the given transition.
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @param taskId
	 *            Task to update
	 * @param transitionId
	 *            the id of the transition to follow
	 * @return the id of the task, or <code>null</code> if a problem occurred
	 */
	public boolean workflowEndTask(AlfrescoTransaction transaction, String taskId, String transitionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("taskId", taskId);
		parameters.put("transitionId", transitionId);
		Boolean result = (Boolean) workflowRequestCall(transaction, "wfEnd", parameters);
		if (result == null) {
			return false;
		}
		return result;
	}

	/**
	 * Updates a task with the given properties.
	 * 
	 * @param transaction
	 * @param taskId
	 * @param properties
	 * @return
	 */
	public boolean workflowUpdateTask(AlfrescoTransaction transaction, String taskId, Map<QName, Serializable> properties) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("taskId", taskId);
		parameters.put("properties", properties);
		Boolean result = (Boolean) workflowRequestCall(transaction, "wfUpdate", parameters);
		if (result == null) {
			return false;
		}
		return result;
	}

	/**
	 * Cancels a workflow.
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @param workflowId
	 *            Id of the workflow to cancel
	 * @return The updated instance
	 */
	public WorkflowInstance workflowCancel(AlfrescoTransaction transaction, String workflowId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("workflowId", workflowId);
		return (WorkflowInstance) workflowRequestCall(transaction, "wfCancel", parameters);
	}

	/**
	 * Collects all non empty properties available on an instanceId.
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @param instanceId
	 *            the workflow instance Id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<QName, Serializable> workflowCollectInstanceProperties(AlfrescoTransaction transaction, String instanceId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("workflowId", instanceId);

		return (Map<QName, Serializable>) workflowRequestCall(transaction, "wfCollectInstanceProperties", parameters);
	}

	/**
	 * Gets selected pieces of information about the currently active tasks for
	 * the given instance.
	 * Each item of the list is a string in which the pieces of info are
	 * concatenated and separated
	 * using a separator string defined in {@link XFormsWork.wfGetCurrentTasks}.
	 * e.g. (with
	 * SEPARATOR="{::}":
	 * 
	 * "jbpm$64{::}wfbxDigitizationProcess:Debut{::}Demarrage de la dematerialisation"
	 * )
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @param instanceId
	 *            the id of a currently live workflow instance
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> workflowGetCurrentTasksInfo(AlfrescoTransaction transaction, String instanceId) { // #1534
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("workflowId", instanceId);

		return (List<String>) workflowRequestCall(transaction, "wfGetCurrentTasksIds", parameters);
	}

	/**
	 * Gets the id for the latest version of a process.
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @param processName
	 *            the workflow definition name
	 * @return
	 */
	public String workflowGetIdForProcessDefinition(AlfrescoTransaction transaction, String processName) { // #1534
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("processName", processName);

		return (String) workflowRequestCall(transaction, "wfGetIdForProcessDefinition", parameters);
	}

	/**
	 * Gets the set of properties qualified names a for a task of a process
	 * definition.<br/>
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @param processId
	 *            the workflow definition name, e.g. "jbpm$105"
	 * @param taskName
	 *            the name/id of the task, e.g. "wfbxDigitizationProcess:Debut"
	 * @return the set, or <code>null</code> if either of the ids was not found
	 */
	@SuppressWarnings("unchecked")
	public List<String> workflowGetTaskPropertiesQNames(AlfrescoTransaction transaction, String processId, String taskName) { // #1534
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("processId", processId);
		parameters.put("taskId", taskName);

		return (List<String>) workflowRequestCall(transaction, "wfGetTaskDefinitionPropertyQNames", parameters);
	}

	/**
	 * Creates a workflow package under the default package location and adds an
	 * existing content to
	 * that workflow package so that a call to
	 * WorkflowService.getWorkflowsForContent for that
	 * content links to the workflow for which the created package is the
	 * package.
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @return the new package
	 * @throws ServletException
	 */
	public NodeRef workflowCreatePackage(AlfrescoTransaction transaction, String nodeToAdd) throws ServletException {

		Map<String, String> parameters = new HashMap<String, String>();
		if (nodeToAdd != null) { // #1284: workflows without attached data form
			parameters.put("content", nodeToAdd);
		}
		// parameters.put("package", (String) null); // do not set a null value

		String resultId = requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_PACKAGE);

		if (StringUtils.trimToNull(resultId) != null) {
			return (NodeRef) xstream.fromXML(resultId);
		}
		return null;
	}

	/**
	 * Calls a method of org.alfresco.service.cmr.workflowWorkflowService,
	 * putting the parameters
	 * from the list into a map, and creating a transaction if none is given.<br/>
	 * 
	 * @param transaction
	 *            a transaction object. May NOT be null.
	 * @param methodName
	 *            the name of the method to call, case-sensitive.
	 * @param methodParameters
	 *            a list containing the method parameters in the order of the
	 *            function's signature.
	 *            CANNOT BE NULL.
	 * @return an object of the return type of the function that was called.
	 */
	public Object workflowRequestWrapper(AlfrescoTransaction transaction, String methodName, List<Object> methodParameters) {
		Map<String, Object> parameterMaps = new HashMap<String, Object>();
		int i = 0;
		for (Object parameter : methodParameters) {
			parameterMaps.put("arg" + i, parameter);
			i++;
		}

		return workflowRequestCall(transaction, methodName, parameterMaps);
	}

	/**
	 * Executes a workflow request on Alfresco by calling the webscript.
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @param methodName
	 *            Method to call (mapped to a method in WorkflowService).
	 *            <b>Case-sensitive</b>.
	 * @param methodParameters
	 *            Parameters needed by the method (same types as declared in
	 *            WorkflowService)
	 * @return The deserialized Object or null if exception
	 */
	private Object workflowRequestCall(AlfrescoTransaction transaction, String methodName, Map<String, Object> methodParameters) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("method", methodName);
		Set<String> keys = methodParameters.keySet();
		for (String key : keys) {
			Object parameterValue = methodParameters.get(key);
			if (parameterValue instanceof String) {
				parameters.put(key, (String) parameterValue);
			} else {
				String xmlParameter = xstream.toXML(parameterValue);
				parameters.put(key, xmlParameter);
			}
		}
		String sresult;

		if (isInStandaloneMode()) {
			return null;
		}

		try {
			sresult = requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_WORKFLOW);
		} catch (ServletException e) {
			return null;
		}
		Object result = null;
		if (StringUtils.trimToNull(sresult) != null) {
			result = xstream.fromXML(sresult);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private List<String> workflowGetCurrentTasks(String instanceId, AlfrescoTransaction trans) {
		List<String> result = new Vector<String>();
		// get the paths from the instance id, which should exist
		List<WorkflowPath> paths = null;
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(instanceId);
		paths = (List<WorkflowPath>) workflowRequestWrapper(trans, "getWorkflowPaths", paramList);
		if (paths == null) {
			if (logger.isErrorEnabled()) {
				logger.error(MsgId.INT_ERR_NULL_WKFLW_INSTANCE_PATHS + instanceId);
			}
			return result;
		}
		// we need to probe all active paths
		for (WorkflowPath path : paths) {
			if (path.active) {
				// get the tasks for the path
				List<WorkflowTask> tasks = null;
				ArrayList<Object> params = new ArrayList<Object>();
				params.add(path.id);
				tasks = (List<WorkflowTask>) workflowRequestWrapper(trans, "getTasksForWorkflowPath", params);
				if (tasks == null) {
					return result;
				}
				// add the tasks to complete
				for (WorkflowTask task : tasks) {
					if (task.state == WorkflowTaskState.IN_PROGRESS) {
						result.add(task.id);
					}
				}
			}
		}
		return result;
	}

	public WorkflowTaskDefinition workflowGetTaskDefinition(String processDefId, String task, AlfrescoTransaction trans) {

		// List<WorkflowTaskDefinition> taskDefs = workflowGetTaskDefinitions(processDefId, trans);
		// for (WorkflowTaskDefinition taskDef : taskDefs) {
		// if (StringUtils.equals(taskDef.id, task)) {
		// return taskDef;
		// }
		// }
		// return null;
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("processDefId", processDefId);
		parameters.put("task", "" + task);

		return (WorkflowTaskDefinition) workflowRequestCall(trans, "wfGetTaskDefinition", parameters);
	}

	// @SuppressWarnings("unchecked")
	// public List<WorkflowTaskDefinition> workflowGetTaskDefinitions(String processDefId,
	// AlfrescoTransaction trans) {
	// String methodName = "getTaskDefinitions";
	// List<Object> params = new ArrayList<Object>();
	// params.add(processDefId);
	// List<WorkflowTaskDefinition> workflowRequest = (List<WorkflowTaskDefinition>)
	// workflowRequestWrapper(
	// trans, methodName, params);
	// return workflowRequest;
	// }

	@SuppressWarnings("unchecked")
	public List<String> workflowGetWorkflowsForContent(String refStr, boolean onlyActive, AlfrescoTransaction trans) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nodeId", refStr);
		parameters.put("onlyActive", "" + onlyActive);

		return (List<String>) workflowRequestCall(trans, "wfGetWorkflowsForContent", parameters);
	}

	public WorkflowDefinition workflowGetWorkflowById(String defId, AlfrescoTransaction trans) {
		String methodName = "getDefinitionById";
		List<Object> params = new ArrayList<Object>();
		params.add(defId);
		WorkflowDefinition workflowRequest = (WorkflowDefinition) workflowRequestWrapper(trans, methodName, params);
		return workflowRequest;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String getWorkflowFormNameByTaskId(String fullTaskId) {
		return mappingAgent.getWorkflowFormNameByTaskId(fullTaskId);
	}

	/**
	 * Returns the Alfresco name for the given field from a specific workflow
	 * form.
	 * 
	 * @param wkFormName
	 * @param fieldName
	 * @return
	 */
	private String getWorkflowFieldAlfrescoName(String wkFormName, String fieldName) {
		return mappingAgent.getWorkflowFieldAlfrescoName(wkFormName, fieldName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String createPathInRepository(String userName, String folderPath) throws ServletException {

		// collect parameters
		Map<String, String> parameters = new TreeMap<String, String>();
		parameters.put("path", folderPath);
		// call the webscript
		AlfrescoTransaction transaction = new AlfrescoTransaction(this, userName);

		String resultId = requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_MKDIR);

		return resultId;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String workflowExtractTaskNameFromFormName(String formName) {
		// e.g. "Evaluation_Demarrage" -> "Demarrage"
		return formName.substring(formName.indexOf("_") + 1);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String workflowExtractProcessNameFromFormName(String formName) {
		// e.g. "Evaluation_Demarrage" -> "Evaluation"
		return formName.substring(0, formName.indexOf("_"));
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String workflowExtractNamespacePrefix(String name) {
		int start = name.indexOf(BLUEXML_WORKFLOW_PREFIX);
		int end = name.indexOf(':');
		if ((start == -1) || (end == -1) || (end < start)) {
			return name;
		}
		String prefix = name.substring(start, end);
		return prefix;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public boolean workflowIsBlueXMLDefinition(String name) {
		int start = name.indexOf(BLUEXML_WORKFLOW_PREFIX);
		int end = name.indexOf(':');
		if (start == -1 || end == -1 || (end < start)) {
			return false;
		}
		String prefix = name.substring(start, end);
		String processName = name.substring(end + 1);
		return StringUtils.endsWith(prefix, processName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String workflowExtractProcessNameFromDefName(String name) {
		return name.substring(name.indexOf(':') + 1);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String getWorkflowBlueXMLDefinitionName(String name) {
		// Used when reacting to a workflow transition action.
		// e.g. "Evaluation" --> "jbpm$wfbxEvaluation:Evaluation"
		return "jbpm$" + BLUEXML_WORKFLOW_PREFIX + name + ":" + name;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String getWorkflowBlueXMLTaskName(String formName) {
		// e.g. "Evaluation_Annotation" --> "wfbxEvaluation:Annotation"<br/>
		// NOTE: a duplicate of this function is also defined in MappingGenerator
		return BLUEXML_WORKFLOW_PREFIX + formName.replace('_', ':');
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String getWorkflowFormNameFromTask(String taskName) {
		// e.g. "wfbxEvaluation:Annotation" --> "Evaluation_Annotation"<br/>
		// e.g. "jbpm$wfbxEvaluation:Annotation" --> "Evaluation_Annotation"<br/>
		String searchString = BLUEXML_WORKFLOW_PREFIX;

		if (taskName.indexOf(searchString) != 0) {
			searchString = "jbpm$" + BLUEXML_WORKFLOW_PREFIX; // the demo webapp uses this format
			if (taskName.indexOf(searchString) != 0) {
				return null;
			}
		}

		String substr = taskName.substring(searchString.length());
		return substr.replace(':', '_');
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String getWorkflowNamespaceURI(String processName) {
		return MsgId.INT_NAMESPACE_BLUEXML_WORKFLOW + "/" + processName + "/1.0";
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String getWorkflowStartTaskFormName(String workflowDefName) {
		String prefix = workflowExtractNamespacePrefix(workflowDefName);

		// we got "wfbxwfTest" but we want "wfTest"
		prefix = prefix.substring(BLUEXML_WORKFLOW_PREFIX.length());

		return mappingAgent.getWorkflowStartTaskFormName(prefix);
	}

	@SuppressWarnings("unchecked")
	public List<String> workflowGetPooledTasks(String userName, AlfrescoTransaction trans) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("userName", userName);

		return (List<String>) workflowRequestCall(trans, "wfGetPooledTasks", parameters);
	}

	public WorkflowTask workflowGetTaskById(String taskId, AlfrescoTransaction trans) {
		String methodName = "getTaskById";
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add(taskId);

		WorkflowTask workflowRequest = (WorkflowTask) workflowRequestWrapper(trans, methodName, methodParameters);
		return workflowRequest;
	}

	@SuppressWarnings("unchecked")
	public List<String> workflowGetPackageContents(String taskId, AlfrescoTransaction trans) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("taskId", taskId);

		return (List<String>) workflowRequestCall(trans, "wfGetPackageContents", parameters);

	}

	/**
	 * Sets initial values for workflow fields from BlueXML properties (stored
	 * in the repository) of
	 * the workflow instance.
	 * 
	 * @param wkFormName
	 *            the name of the workflow form to display
	 * @param doc
	 *            the XForms instance
	 * @param instanceId
	 *            the workflow instance Id (previously provided by Alfresco)
	 * @return false if a lethal exception occurred. True if the normal end of
	 *         the function was
	 *         reached, which does not imply anything about the setting of
	 *         initial values.
	 */
	public boolean workflowPatchInstance(AlfrescoTransaction transaction, String wkFormName, Document doc, String instanceId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Patching workflow instance with Id:'" + instanceId + "', form name: " + wkFormName);
		}
		QName qname;
		String namespaceURI = null; // to be set once
		Map<QName, Serializable> properties = null; // to be set once

		if (StringUtils.trimToNull(instanceId) == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("  No patching performed: the instanceId is null");
			}
			return true;
		}
		if (instanceId.equals("null")) {
			if (logger.isDebugEnabled()) {
				logger.debug("  No patching performed, invalid instanceId with string 'null'");
			}
			return true;
		}
		Element root = doc.getDocumentElement();
		Element formElt = DOMUtil.getChild(root, wkFormName);
		List<Element> allFields = DOMUtil.getAllChildren(formElt);

		// we need to fail silently so that the form is displayed even in the event of errors
		for (Element field : allFields) {
			String fieldUniqueName = field.getTagName();
			Serializable fieldValue = null;
			String localName = getWorkflowFieldAlfrescoName(wkFormName, fieldUniqueName);
			if (localName != null) {
				// build the QName
				if (namespaceURI == null) {
					String processName = workflowExtractProcessNameFromFormName(wkFormName);
					namespaceURI = getWorkflowNamespaceURI(processName);
				}
				qname = QName.createQName(namespaceURI, localName);

				// read the QName value from the collected properties of the workflow instance
				if (properties == null) {
					properties = workflowCollectInstanceProperties(transaction, instanceId);
					if (properties == null) {
						return false; // there's no point in continuing without the properties
					}
				}
				try {
					// set the initial value
					fieldValue = properties.get(qname);
					if (fieldValue != null) {
						field.setTextContent(fieldValue.toString());
					}
				} catch (NullPointerException e) {
					// we'll get this when displaying workflow forms while the webscript is down
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public void setStandaloneMode(boolean standaloneMode) {
		AlfrescoController.standaloneMode = standaloneMode;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public boolean isInStandaloneMode() {
		return standaloneMode;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public void setCssUrl(String cssUrl) {
		CssUrl = cssUrl;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String getCssUrl() {
		return CssUrl;
	}

	/**
	 * Gets the suffix appended to read only forms. <br/>
	 * NOTE: In the BxDS version, that suffix is configured at generation time
	 * and stored in the
	 * mapping file. Here (in SIDE) we don't have (yet) any means to do the
	 * same.
	 * 
	 * @return the suffix appended to names of read-write forms to produce the
	 *         names of the
	 *         read-only versions of the same forms.
	 */
	public String getReadOnlyFormsSuffix() {
		return mappingAgent.getReadOnlyFormsSuffix();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public boolean isInDebugMode() {
		return mappingAgent.getDebugModeStatus();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public boolean isStartTaskForm(String wkFormName) { // PUBLIC-API
		return mappingAgent.isStartTaskForm(wkFormName);
	}

	/**
	 * Gets the actual data type for a form. Added because read only forms are
	 * distinguished from
	 * R/W forms using a suffix. Hence the form name in itself cannot be used to
	 * designate the
	 * datatype (or "form id" to be more precise).
	 * 
	 * @param formName
	 * @return the id of the read-write version of the form
	 */
	public String getDataTypeFromFormName(String formName) {
		String underlyingDataType = formName;
		String readOnlyFormsSuffix = getReadOnlyFormsSuffix();
		if (StringUtils.endsWith(formName, readOnlyFormsSuffix)) {
			underlyingDataType = StringUtils.removeEnd(formName, readOnlyFormsSuffix);
		}
		return underlyingDataType;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public boolean authenticate(String username, String password) { // PUBLIC-API
		AlfrescoTransaction transaction = new AlfrescoTransaction(this, username);
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", username);
		parameters.put("password", password);
		transaction.setLogin(username);
		String result;
		try {
			result = requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_AUTHENTICATE);
		} catch (ServletException e) {
			e.printStackTrace();
			return false;
		}
		return StringUtils.equalsIgnoreCase(result, "success");
	}

	public String getWebscriptHelp(AlfrescoTransaction transaction) {
		String result;
		Map<String, String> parameters = new HashMap<String, String>();
		try {
			result = requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_HELP);
		} catch (ServletException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public boolean performDynamicReload() { // PUBLIC-API
		try {
			mappingAgent.loadMappingXml(false);
		} catch (Exception e) {
			logger.error("Error while loading the dynamic reload", e);
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String getNodeType(String userName, String dataId) { // PUBLIC-API
		AlfrescoTransaction transaction = new AlfrescoTransaction(this, userName);
		transaction.setLogin(userName);

		QName qname = systemGetNodeType(transaction, patchDataId(dataId));
		if (qname == null) {
			return null;
		}
		return qname.getLocalName();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String getNodeTypeFull(String userName, String dataId) { // PUBLIC-API
		AlfrescoTransaction transaction = new AlfrescoTransaction(this, userName);
		transaction.setLogin(userName);

		QName qname = systemGetNodeType(transaction, dataId);
		if (qname == null) {
			return null;
		}
		return qname.toString();
	}

	public String getNodeContentInfo(AlfrescoTransaction transaction, String nodeId) {
		if (StringUtils.trimToNull(nodeId) == null) {
			return "";
		}
		String request;
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("nodeId", nodeId);

		try {
			request = requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_NODE_INFO);
		} catch (ServletException e) {
			e.printStackTrace();
			return null;
		}

		if (StringUtils.trimToNull(request) == null) {
			return "";
		}

		String result;
		String[] infos = request.split(","); // <- see the webscript for the actual format
		String nodeName = infos[0]; // the node name
		long size = Long.parseLong(infos[1]); // the content's size
		if (size > 0) {
			String sizeUnit = "";
			Formatter formatter = new Formatter();
			String sizeBytes = formatter.format("%,d", size).toString();
			char multiplier = getFileSizeUnit(size);
			if (multiplier != 'b') {
				sizeUnit = " (" + getFileSizeShortReadable(size, multiplier) + ")";
			} else {
				//
			}
			result = MsgPool.getMsg(MsgId.MSG_UPLOAD_CONTENT_REPO_FORMAT, nodeName, sizeBytes, sizeUnit, nodeId);
		} else {
			result = MsgPool.getMsg(MsgId.MSG_UPLOAD_CONTENT_NO_CONTENT);
		}
		// result += " " + result;
		return result;
	}

	/**
	 * Gets the "appropriate" unit for the given file size.
	 * 
	 * @param size
	 * @return 'b' or 'k' or 'm' or 'g'
	 */
	private static char getFileSizeUnit(long size) {
		char unit = 'b'; // bytes
		if (size > 1024 * 1024 * 1024) {
			unit = 'g';
		} else if (size > 1024 * 1024) {
			unit = 'm';
		} else if (size > 1024) {
			unit = 'k';
		}
		return unit;
	}

	/**
	 * Returns a "short" version of the given file size using the appropriate
	 * multiplier, and
	 * formatted with a fixed number (see the format) of decimal places. <br/>
	 * Examples: 1024 bytes => 1.00 KB, 7 614 525 bytes => 7.26 MB
	 * 
	 * @param transferred
	 * @param unit
	 * @return
	 */
	private static String getFileSizeShortReadable(long transferred, char unit) {
		float res = transferred;
		String unitStr = "B";
		switch (unit) {
		case 'k':
		case 'K':
			res = (float) transferred / (float) (1024);
			unitStr = "KB";
			break;
		case 'm':
		case 'M':
			res = (float) transferred / (float) (1024 * 1024);
			unitStr = "MB";
			break;
		case 'g':
		case 'G':
			res = (float) transferred / (float) (1024 * 1024 * 1024);
			unitStr = "GB";
			break;
		}
		Formatter formatter = new Formatter();
		return formatter.format("%,.2f", res) + " " + unitStr;
	}

	//
	// REDIRECTION
	//

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public RedirectionBean getWorkflowRedirectionBean(String formName) {
		return targetTable.get(formName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public boolean loadRedirectionTable(String filePath) {
		if (logger.isDebugEnabled()) {
			logger.debug("Reading redirection configuration file.");
		}

		String actualFilePath = ""; // the path where the stream is successfully open
		FileInputStream stream = null;
		if (StringUtils.trimToNull(filePath) != null) {
			try {
				actualFilePath = filePath;
				File file = new File(actualFilePath);
				stream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace(); // continue anyway
			}
		}

		if (stream == null) {
			if (StringUtils.trimToNull(redirectXmlPath) != null) {
				try {
					actualFilePath = redirectXmlPath;
					File file = new File(actualFilePath);
					stream = new FileInputStream(file);
				} catch (FileNotFoundException e) {
					e.printStackTrace(); // continue anyway
				}
			}
			URL url = AlfrescoController.class.getResource("/redirect.xml");
			if (url == null) {
				if (logger.isErrorEnabled()) {
					logger.error("Redirection file not found. Redirection will not be available.");
				}
				return false;
			}
			File file;
			try {
				URI fileURI = new URI(url.toString());
				file = new File(fileURI);
				actualFilePath = fileURI.getSchemeSpecificPart();
				stream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return false;
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
				return false;
			}

		} else {
			redirectXmlPath = filePath;
		}
		String formName;
		String url;
		boolean autoAdvance;
		boolean addParams;

		Document document = DOMUtil.getDocumentFromStream(stream);
		if (document == null) {
			return false;
		}

		HashMap<String, RedirectionBean> localTable = new HashMap<String, RedirectionBean>();

		// we won't check the tag name for the root element
		Element root = document.getDocumentElement();
		List<Element> entries = DOMUtil.getChildren(root, MsgId.INT_REDIRECTION_ENTRY.getText());
		// for each entry of the file, store the info
		for (Element entry : entries) {
			Element nameElt = DOMUtil.getChild(entry, MsgId.INT_REDIRECTION_NAME.getText());
			if (nameElt != null) {
				try {
					formName = nameElt.getTextContent();
					Element urlElt = DOMUtil.getChild(entry, MsgId.INT_REDIRECTION_URL.getText());
					url = urlElt.getTextContent();
					Element autoElt = DOMUtil.getChild(entry, MsgId.INT_REDIRECTION_AUTO_ADVANCE.getText());
					autoAdvance = StringUtils.equals(autoElt.getTextContent(), "true");

					Element addElt = DOMUtil.getChild(entry, MsgId.INT_REDIRECTION_ADD_PARAMS.getText());
					addParams = StringUtils.equals(addElt.getTextContent(), "true");
					RedirectionBean bean = new RedirectionBean(url, autoAdvance, addParams);

					localTable.put(formName, bean);
				} catch (NullPointerException ne) {
					logger.error("Caught a null pointer exception while loading the redirection file at '" + actualFilePath + "'. The file is probably not correctly formatted.", ne);
					return false;
				}
			} else {
				// // get rid of everything previously read
				// targetTable = new HashMap<String, RedirectionBean>();
				// return false;
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Redirection configuration file successfully read.");
		}

		targetTable = localTable;
		return true;
	}

	//
	// PUBLIC API
	//

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String getCustomFormForDatatype(String dataType) {
		return mappingAgent.getCustomFormForDatatype(dataType);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String getDefaultFormForDatatype(String dataType) {
		return mappingAgent.getDefaultFormForDatatype(dataType);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String getUnderlyingTypeForForm(String formName) {
		return mappingAgent.getUnderlyingClassForForm(formName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String getUnderlyingTypeForWorkflow(String formName) {
		return mappingAgent.getUnderlyingClassForWorkflow(formName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String getUnderlyingDataFormForWorkflow(String formName) {
		return mappingAgent.getUnderlyingDataFormForWorkflow(formName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public WorkflowTaskInfoBean getWorkflowTaskInfoBean(String wkFormName) {
		return mappingAgent.getWorkflowTaskInfoBean(wkFormName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public WorkflowTaskInfoBean getWorkflowTaskInfoBeanByTaskId(String taskId) {
		return mappingAgent.getWorkflowTaskInfoBeanById(taskId);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public List<String> getAllCustomForms() {
		return mappingAgent.getAllCustomForms();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public List<String> getAllDefaultForms() {
		return mappingAgent.getAllDefaultForms();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public List<String> getAllSearchForms() {
		return mappingAgent.getAllSearchForms();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public List<String> getAllWorkflowForms() {
		return mappingAgent.getAllWorkflowForms();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public Document getInstanceForm(String userName, String formName, String dataId, boolean formIsReadOnly) throws ServletException {
		AlfrescoTransaction transaction = new AlfrescoTransaction(this, userName);

		GetInstanceFormBean bean = new GetInstanceFormBean(formName, patchDataId(dataId), formIsReadOnly, false, null);
		return getInstanceForm(transaction, bean);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public Document getInstanceClass(String userName, String formName, String dataId, boolean formIsReadOnly, boolean applyUserFormats) throws ServletException {
		AlfrescoTransaction transaction = new AlfrescoTransaction(this, userName);

		return getInstanceClass(transaction, formName, patchDataId(dataId), formIsReadOnly, applyUserFormats);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public Document getInstanceWorkflow(String userName, String formName) {
		AlfrescoTransaction transaction = new AlfrescoTransaction(this, userName);

		return getInstanceWorkflow(transaction, formName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public Document readObjectFromRepository(String userName, String dataId) throws ServletException {
		AlfrescoTransaction transaction = new AlfrescoTransaction(this, userName);

		return readObjectFromRepository(transaction, dataId);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public boolean isCustomForm(String formName) {
		return mappingAgent.isCustomForm(formName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public boolean isDefaultForm(String formName) {
		return mappingAgent.isDefaultForm(formName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public boolean isSearchForm(String formName) {
		return mappingAgent.isSearchForm(formName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public boolean isWorkflowForm(String formName) {
		return mappingAgent.isWorkflowForm(formName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public List<String> workflowGetCurrentTasks(String userName, String instanceId) {
		AlfrescoTransaction trans = new AlfrescoTransaction(this, userName);

		return workflowGetCurrentTasks(instanceId, trans);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public List<String> workflowGetPackageContents(String userName, String taskId) {
		AlfrescoTransaction trans = new AlfrescoTransaction(this, userName);

		return workflowGetPackageContents(taskId, trans);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public List<String> workflowGetPooledTasks(String userName, String managerUserName) {
		AlfrescoTransaction trans = new AlfrescoTransaction(this, userName);

		return workflowGetPooledTasks(managerUserName, trans);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public WorkflowTask workflowGetTaskById(String userName, String taskId) {
		AlfrescoTransaction trans = new AlfrescoTransaction(this, userName);

		return workflowGetTaskById(taskId, trans);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public WorkflowTaskDefinition workflowGetTaskDefinition(String userName, String processDefId, String task) {
		AlfrescoTransaction trans = new AlfrescoTransaction(this, userName);

		return workflowGetTaskDefinition(processDefId, task, trans);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public WorkflowDefinition workflowGetWorkflowById(String userName, String defId) {
		AlfrescoTransaction trans = new AlfrescoTransaction(this, userName);

		return workflowGetWorkflowById(defId, trans);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public List<String> workflowGetWorkflowsForContent(String userName, String nodeId, boolean onlyActive) {
		AlfrescoTransaction trans = new AlfrescoTransaction(this, userName);

		return workflowGetWorkflowsForContent(patchDataId(nodeId), onlyActive, trans);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String getNodeContentInfo(String userName, String nodeId) {
		AlfrescoTransaction trans = new AlfrescoTransaction(this, userName);

		return getNodeContentInfo(trans, patchDataId(nodeId));
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String getWebscriptHelp(String userName) {
		AlfrescoTransaction trans = new AlfrescoTransaction(this, userName);

		return getWebscriptHelp(trans);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public Set<String> systemGetAllAuthoritiesAsGroupsOrUsers(String userName, boolean asGroups) {
		AlfrescoTransaction transaction = new AlfrescoTransaction(this, userName);

		return systemGetAllAuthoritiesAsGroupsOrUsers(transaction, asGroups);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public Set<String> systemGetContainingGroups(String userName, String specificUserName) {
		AlfrescoTransaction transaction = new AlfrescoTransaction(this, userName);

		return systemGetContainingGroups(transaction, specificUserName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String systemGetNodeProperty(String userName, String propertyName, String nodeId) {
		AlfrescoTransaction transaction = new AlfrescoTransaction(this, userName);

		NodeRef node;

		int posProtocolStoreSep = nodeId.indexOf(':');
		int posStoreIdSep = nodeId.indexOf("://");
		if (posProtocolStoreSep == -1 && posStoreIdSep == -1) {
			node = new NodeRef(patchDataId(nodeId));
		} else {
			node = new NodeRef(nodeId);
		}
		QName propertyQName = QName.createQName(propertyName);
		return systemGetNodeProperty(transaction, node, propertyQName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String systemGetNodeRefForGroup(String userName, String groupName) {
		AlfrescoTransaction transaction = new AlfrescoTransaction(this, userName);

		NodeRef nodeRef = systemGetNodeRefForGroup(transaction, groupName);
		if (nodeRef == null) {
			return null;
		}
		return nodeRef.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String systemGetNodeRefForUser(String userName, String specificUserName) {
		AlfrescoTransaction transaction = new AlfrescoTransaction(this, userName);

		NodeRef nodeRef = systemGetNodeRefForUser(transaction, specificUserName);
		if (nodeRef == null) {
			return null;
		}
		return nodeRef.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.xforms.controller.alfresco.AlfrescoControllerAPI
	 */
	public String systemGetNodeType(String userName, String dataId) {
		AlfrescoTransaction transaction = new AlfrescoTransaction(this, userName);

		QName qname = systemGetNodeType(transaction, dataId);
		if (qname == null) {
			return null;
		}
		return qname.toString();
	}

	/**
	 * Gets the id, label and qname of the ids (complete node ids, with protocol
	 * and store) in the
	 * comma-separated list
	 * 
	 * @param transaction
	 * @param format
	 *            the (one) pattern for formatting the labels
	 * @param ids
	 *            the comma-separated list of ids
	 * @return
	 * @throws ServletException
	 */
	public String readObjectsInfo(AlfrescoTransaction transaction, String format, String ids) throws ServletException {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("ids", ids);
		parameters.put("format", format);
		return requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_NODE_INFO);
	}

	/**
	 * Gets the complete id, label and qname for the node (of the given content
	 * type) whose
	 * identifier property has a specific value. selection-capable form field
	 * initialized using a
	 * URL parameter. For this function to do its job correctly, there should be
	 * an object of the
	 * datatype whose identifier property has the initialization value. Unless
	 * specified, the
	 * parameters MUST NOT be <code>null</code>.
	 * 
	 * @param transaction
	 * @param datatype
	 *            the prefixed content type name to search.
	 * @param identifier
	 *            the local name of a property (in the datatype definition) used
	 *            as an identifier.
	 * @param format
	 *            the pattern for formatting the label. <code>null</code>-able.
	 * @param labelLength
	 *            the maximum length allowed for the label. <code>null</code>
	 *            -able.
	 * @param value
	 *            the initialization value, also the value (of the identifier
	 *            property) to look for.
	 * @return
	 * @throws ServletException
	 */
	public String resolveObjectInfo(AlfrescoTransaction transaction, String datatype, String identifier, String format, String labelLength, String value) throws ServletException {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("datatype", datatype);
		parameters.put("identifier", identifier);
		parameters.put("format", StringUtils.trimToEmpty(format));
		parameters.put("id", value);
		String maxLength = StringUtils.trimToNull(labelLength) == null ? "0" : labelLength;
		parameters.put("labelLength", maxLength);
		return requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_NODE_INFO);
	}

	/**
	 * Gets the value of the MsgId.MODEL_XTENSION_FAILURE_PAGE for a form of a
	 * given type.
	 * 
	 * @param formName
	 *            the id of the workflow form
	 */
	public String getXtensionFailurePage(String formName) {
		return mappingAgent.getXtensionFailurePage(formName);
	}

	/**
	 * Gets the value of the MsgId.MODEL_XTENSION_SUCCESS_PAGE for a form of a
	 * given type.
	 * 
	 * @param formName
	 *            the id of the workflow form
	 */
	public String getXtensionSuccessPage(String formName) {
		return mappingAgent.getXtensionSuccessPage(formName);
	}

	/**
	 * Gets the value of the MsgId.MODEL_XTENSION_NEXT_PAGE_CANCEL for a form of
	 * a given type.
	 * 
	 * @param formName
	 *            the id of the form
	 * @param formTypeEnum
	 */
	public String getXtensionNextPageCancel(String formName, FormTypeEnum formTypeEnum) {
		return mappingAgent.getXtensionNextPageCancel(formName, formTypeEnum);
	}

	/**
	 * Gets the value of the MsgId.MODEL_XTENSION_NEXT_PAGE_DELETE for a form of
	 * a given type.
	 * 
	 * @param formName
	 *            the id of the form
	 * @param formTypeEnum
	 */
	public String getXtensionNextPageDelete(String formName, FormTypeEnum formTypeEnum) {
		return mappingAgent.getXtensionNextPageDelete(formName, formTypeEnum);
	}

	/**
	 * Gets the value of the MsgId.MODEL_XTENSION_NEXT_PAGE_SUBMIT for a form of
	 * a given type.
	 * 
	 * @param formName
	 *            the id of the form
	 * @param formTypeEnum
	 */
	public String getXtensionNextPageSubmit(String formName, FormTypeEnum formTypeEnum) {
		return mappingAgent.getXtensionNextPageSubmit(formName, formTypeEnum);
	}

	/**
	 * Gets the value of the MsgId.MODEL_XTENSION_SKIP_ADDITIONAL_INFO for a
	 * form of a given type.
	 * 
	 * @param formName
	 *            the id of the form
	 * @param formTypeEnum
	 */
	public boolean getXtensionSkipAdditionalInfo(String formName, FormTypeEnum formTypeEnum) {
		return mappingAgent.getXtensionSkipAdditionalInfo(formName, formTypeEnum);
	}

}
