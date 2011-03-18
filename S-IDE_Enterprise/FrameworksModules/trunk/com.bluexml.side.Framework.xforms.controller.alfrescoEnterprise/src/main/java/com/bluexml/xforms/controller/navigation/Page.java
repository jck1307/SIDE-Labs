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


package com.bluexml.xforms.controller.navigation;

import java.util.Map;

import org.w3c.dom.Document;

import com.bluexml.xforms.controller.beans.PageInfoBean;

/**
 * The Class Page. Holds some data about pages served to users. Performs no significant processing.
 * 
 */
public class Page {
	private PageInfoBean pageBean;

	/** The data type set. */
	private boolean dataTypeSet;

	/** The node. */
	private Document node;

	/**
	 * Instantiates a new page.
	 * 
	 * @param bean
	 *            the page information bean
	 */
	public Page(PageInfoBean pageBean) {
		super();
		this.pageBean = pageBean;
	}

	//
	// LOCALLY MANAGED
	//

	/**
	 * Checks if is data type set.
	 * 
	 * @return true, if is data type set
	 */
	public boolean isDataTypeSet() {
		return dataTypeSet;
	}

	/**
	 * Sets the data type set.
	 * 
	 * @param dataTypeSet
	 *            the new data type set
	 */
	public void setDataTypeSet(boolean dataTypeSet) {
		this.dataTypeSet = dataTypeSet;
	}

	/**
	 * Gets the node.
	 * 
	 * @return the node
	 */
	public Document getNode() {
		return node;
	}

	/**
	 * Sets the node.
	 * 
	 * @param node
	 *            the new node
	 */
	public void setNode(Document node) {
		this.node = node;
	}

	//
	// DELEGATED
	//

	/**
	 * Gets the form type.
	 * 
	 * @return the form type
	 */
	public FormTypeEnum getFormType() {
		return pageBean.getFormType();
	}

	/**
	 * Sets the form type.
	 * 
	 * @param formType
	 *            the new form type
	 */
	public void setFormType(FormTypeEnum formType) {
		pageBean.setFormType(formType);
	}

	/**
	 * Gets the form name.
	 * 
	 * @return the form name
	 */
	public String getFormName() {
		return pageBean.getFormName();
	}

	/**
	 * Sets the form name.
	 * 
	 * @param formName
	 *            the new form name
	 */
	public void setFormName(String formName) {
		pageBean.setFormName(formName);
	}

	/**
	 * Gets the data id.
	 * 
	 * @return the data id
	 */
	public String getDataId() {
		return pageBean.getDataId();
	}

	/**
	 * Sets the data id.
	 * 
	 * @param dataId
	 *            the new data id
	 */
	public void setDataId(String dataId) {
		pageBean.setDataId(dataId);
	}

	/**
	 * Gets the template.
	 * 
	 * @return the template
	 */
	public String getTemplate() {
		return pageBean.getTemplateId();
	}

	/**
	 * Sets the template.
	 * 
	 * @param template
	 *            the new template
	 */
	public void setTemplate(String template) {
		pageBean.setTemplateId(template);
	}

	/**
	 * Gets the inits the params.
	 * 
	 * @return the inits the params
	 */
	public Map<String, String> getInitParams() {
		return pageBean.getInitParams();
	}

	/**
	 * Sets the init params.
	 * 
	 * @param initParams
	 *            the init params
	 */
	public void setInitParams(Map<String, String> initParams) {
		pageBean.setInitParams(initParams);
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return pageBean.getLanguage();
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		pageBean.setLanguage(language);
	}

	/**
	 * @return the showSubmitButtons
	 */
	public boolean isShowSubmitButtons() {
		return pageBean.isShowSubmits();
	}

	/**
	 * @param showSubmitButtons
	 *            the showSubmitButtons to set
	 */
	public void setShowSubmitButtons(boolean showSubmitButtons) {
		pageBean.setShowSubmits(showSubmitButtons);
	}

	/**
	 * @return the showCancel
	 */
	public boolean isShowCancel() {
		return pageBean.isShowCancel();
	}

	/**
	 * @param showCancel
	 *            the showCancel to set
	 */
	public void setShowCancel(boolean showCancel) {
		pageBean.setShowCancel(showCancel);
	}

	/**
	 * @return the showDelete
	 */
	public boolean isShowDelete() {
		return pageBean.isShowDelete();
	}

	/**
	 * @param showDelete
	 *            the showDelete to set
	 */
	public void setShowDelete(boolean showDelete) {
		pageBean.setShowDelete(showDelete);
	}

	/**
	 * @return the showValidate
	 */
	public boolean isShowValidate() {
		return pageBean.isShowValidate();
	}

	/**
	 * @param showValidate
	 *            the showValidate to set
	 */
	public void setShowValidate(boolean showValidate) {
		pageBean.setShowValidate(showValidate);
	}

	/**
	 * @return the wkflwProcessId
	 */
	public String getWkflwProcessId() {
		return pageBean.getProcessId();
	}

	/**
	 * @param wkflwProcessId
	 *            the wkflwProcessId to set
	 */
	public void setWkflwProcessId(String wkflwProcessId) {
		pageBean.setProcessId(wkflwProcessId);
	}

	/**
	 * @return the wkflwInstanceId
	 */
	public String getWkflwInstanceId() {
		return pageBean.getInstanceId();
	}

	/**
	 * @param wkflwInstanceId
	 *            the wkflwInstanceId to set
	 */
	public void setWkflwInstanceId(String wkflwInstanceId) {
		pageBean.setInstanceId(wkflwInstanceId);
	}

	/**
	 * @param wrongCallType
	 *            the wrongCallType to set
	 */
	public void setWrongCallType(boolean wrongCallType) {
		pageBean.setWrongCallType(wrongCallType);
	}

	/**
	 * @return the wrongCallType
	 */
	public boolean isWrongCallType() {
		return pageBean.isWrongCallType();
	}

	/**
	 * @param dataType
	 *            the dataType to set
	 */
	public void setDataType(String dataType) {
		pageBean.setDataType(dataType);
	}

	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return pageBean.getDataType();
	}
	
	/**
	 * @param massTagging
	 *            the massTagging to set
	 */
	public void setMassTagging(boolean massTagging) {
		pageBean.setMassTagging(massTagging);
	}

	/**
	 * @return the massTagging
	 */
	public boolean isMassTagging() {
		return pageBean.isMassTagging();
	}

	/**
	 * @param massIds the massIds to set
	 */
	public void setMassIds(String massIds) {
		pageBean.setMassIds(massIds);
	}

	/**
	 * @return the massIds
	 */
	public String getMassIds() {
		return pageBean.getMassIds();
	}


}
