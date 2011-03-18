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

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluexml.xforms.controller.binding.AttachContentInfo;
import com.bluexml.xforms.controller.binding.Batch;
import com.bluexml.xforms.controller.binding.GenericClass;
import com.bluexml.xforms.controller.binding.GenericCreate;
import com.bluexml.xforms.controller.binding.GenericDelete;
import com.bluexml.xforms.controller.binding.GenericUpdate;
import com.bluexml.xforms.controller.binding.ObjectFactory;
import com.bluexml.xforms.controller.binding.ServiceRequestSource;
import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.messages.MsgId;

public class AlfrescoTransaction {

	protected static Log logger = LogFactory.getLog(AlfrescoTransaction.class);

	private final ObjectFactory objectFactory = new ObjectFactory();

	private String login;
	private int counter = 0;
	private AlfrescoController alfrescoController;
	private Batch batch;
	private Map<String, String> ids = null;

	private Page page = null;

	/** the name of the form's */
	@SuppressWarnings("unused")
	private String formId = null; // #1212 // TODO: delete this because already provided by the page

	/** name(s) of file(s) uploaded to the file store, to be deleted in case of error */
	private ArrayList<String> uploadedFileNames = null; // #1278

	/** id(s) of node(s) created for uploads to the repository, to be deleted in case of error */
	private ArrayList<String> uploadedNodes = null; // #1278

	/** name(s) of temporary file(s) for the upload, to be deleted in case of success */
	private ArrayList<String> tempFileNames = null; // #1278

	/** id(s) of old node(s) on update, to be deleted in case of success */
	private ArrayList<String> tempNodeIds = null;

	public AlfrescoTransaction(AlfrescoController alfrescoController, String userName) {
		super();
		this.alfrescoController = alfrescoController;
		this.batch = null; // we need to nullify this for proper initialization later
		this.login = userName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	private void initializeBatch() {
		this.batch = objectFactory.createBatch();
		// ** #1365
		ServiceRequestSource requesterId = objectFactory.createServiceRequestSource();
		requesterId.setId("XFormsController");
		batch.getCreateOrUpdateOrDelete().add(requesterId);
		// ** #1365
		String saveToPath;
		Map<String, String> params = getInitParams();
		if (params != null) { // this should always be true
			saveToPath = params.get(MsgId.PARAM_SAVE_DATA_TO.getText());
			if (StringUtils.trimToNull(saveToPath) != null) {
				batch.setSaveTo(saveToPath);
			}
		}
	}

	/**
	 * Note that the "attach content" should be unique in the list of actions requested and should
	 * ALWAYS come AFTER the actions that process the receiver and provider. DO NOT rely on the
	 * webscript putting things back in the correct order.
	 * 
	 * @param receiver
	 *            either a node ref or a placeholder id (e.g. 'transactionID-1'). The webscript will
	 *            figure each case out.
	 * @param fileName
	 *            the display name for the node in the Alfresco web client/Explorer
	 * @param filePath
	 *            the complete path to the file on the server side
	 * @param mimeType
	 *            the MIME type as served by the web client
	 * @param shouldAppendSuffix
	 *            if set to true, an index [e.g. '(1)'] is appended to the filename if the original
	 *            filename is not available. However, for node contents, it should be false to allow
	 *            overwriting the previous content.
	 * @param contentType
	 *            the qualified name of the (generated) content type that has the meta data. This
	 *            type must correspond to the type of the receiver
	 */
	public void queueAttachContent(String receiver, String fileName, String filePath,
			String mimeType, boolean shouldAppendSuffix, String contentType) {
		if (batch == null) {
			initializeBatch();
		}
		AttachContentInfo entry = objectFactory.createAttachContentInfo();
		entry.setTargetNode(receiver);
		entry.setFileName(fileName);
		entry.setFilePath(filePath);
		entry.setMimeType(mimeType);
		entry.setContentType(contentType);
		entry.setAppendSuffix("" + shouldAppendSuffix);

		batch.getCreateOrUpdateOrDelete().add(entry);
	}

	public void queueDelete(String id) {
		if (batch == null) {
			initializeBatch();
		}
		GenericDelete entry = objectFactory.createGenericDelete();
		entry.setId(id);
		batch.getCreateOrUpdateOrDelete().add(entry);
	}

	public void queueSave(GenericClass alfClass) {
		if (batch == null) {
			initializeBatch();
		}
		alfClass.setId(createId());

		GenericCreate genericCreate = objectFactory.createGenericCreate();
		genericCreate.setClazz(alfClass);
		batch.getCreateOrUpdateOrDelete().add(genericCreate);
	}

	public void queueUpdate(GenericClass alfClass) {
		if (batch == null) {
			initializeBatch();
		}
		GenericUpdate genericUpdate = objectFactory.createGenericUpdate();
		genericUpdate.setClazz(alfClass);
		batch.getCreateOrUpdateOrDelete().add(genericUpdate);
	}

	private String createId() {
		counter++;
		return "transactionID-" + counter;
	}

	public void executeBatch() throws ServletException {
		if (batch == null) {
			initializeBatch();
		}
		if (batch.getCreateOrUpdateOrDelete().size() > 0) {
			alfrescoController.executeBatch(this);
		}
	}

	/**
	 * @return the batch
	 */
	public Batch getBatch() {
		return batch;
	}

	public Map<String, String> getIds() {
		return ids;
	}

	public void setIds(Map<String, String> ids) {
		this.ids = ids;
	}

	public Map<String, String> getInitParams() {
		if (page != null) {
			return page.getInitParams();
		}
		return null;
	}

	/**
	 * @return the form name for this transaction
	 */
	public String getFormId() {
		if (page != null) {
			return page.getFormName();
		}
		return null;
	}

	/**
	 * @return the tempFileName
	 */
	public ArrayList<String> getTempFileNames() {
		return tempFileNames;
	}

	/**
	 * @return the tempNodeIds
	 */
	public ArrayList<String> getTempNodeIds() {
		return tempNodeIds;
	}

	/**
	 * @return the uploadedFileName
	 */
	public ArrayList<String> getUploadedFileNames() {
		return uploadedFileNames;
	}

	/**
	 * @return the uploadedNodes
	 */
	public ArrayList<String> getUploadedNodes() {
		return uploadedNodes;
	}

	/**
	 * @param tempFileName
	 *            the tempFileName to set
	 */
	public void registerTempFileName(String tempFileName) {
		if (tempFileNames == null) {
			tempFileNames = new ArrayList<String>();
		}
		this.tempFileNames.add(tempFileName);
	}

	/**
	 * @param tempFileName
	 *            the tempFileName to set
	 */
	public void registerTempNodeId(String nodeId) {
		if (tempNodeIds == null) {
			tempNodeIds = new ArrayList<String>();
		}
		this.tempNodeIds.add(nodeId);
	}

	/**
	 * @param uploadedFileName
	 *            the uploadedFileName to set
	 */
	public void registerUploadedFileName(String uploadedFileName) {
		if (uploadedFileNames == null) {
			uploadedFileNames = new ArrayList<String>();
		}
		this.uploadedFileNames.add(uploadedFileName);
	}

	/**
	 * @param nodeId
	 *            a complete node Id
	 */
	public void registerUploadedNodes(String nodeId) {
		if (uploadedNodes == null) {
			uploadedNodes = new ArrayList<String>();
		}
		this.uploadedNodes.add(nodeId);
	}

	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}

}
