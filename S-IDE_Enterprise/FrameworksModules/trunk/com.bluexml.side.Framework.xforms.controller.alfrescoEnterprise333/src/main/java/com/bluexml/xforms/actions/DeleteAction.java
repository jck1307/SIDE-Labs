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

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.hook.actions.AbstractTransactionalAction;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * The Class DeleteAction.<br>
 * Delete the edited instance
 */
public class DeleteAction extends AbstractTransactionalAction {

	private String elementId;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionCaption()
	 */
	@Override
	public String getActionCaption() {
		return MsgPool.getMsg(MsgId.CAPTION_BUTTON_DELETE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {
		return MsgId.INT_ACT_CODE_DELETE.getText();
	}

	@Override
	protected void prepareSubmit() {
		// delete element
		deleteNode();
	}

	@Override
	protected void afterSubmit() {
		// unstack last page
		Page currentPage = navigationPath.popCurrentPage();
		Map<String, String> initParams = currentPage.getInitParams();

		String url = StringUtils.trimToNull(initParams.get(MsgId.PARAM_NEXT_PAGE_DELETE.getText()));

		if (url == null) { // #1656
			// there may be something specified in the Xtension property of this form
			url = controller.getXtensionNextPageDelete(currentPage.getFormName(), currentPage
					.getFormType());
		}

		if (StringUtils.trimToNull(url) != null) {
			String nextPageUrl = buildRedirectionUrlWithParams(url, currentPage);
			super.redirectClient(nextPageUrl);
		} else {
			// previous page by default
			boolean empty = navigationPath.isEmpty();
			if (empty) {
				currentPage.setDataId(null);
				// forward to create if no page exists
				navigationPath.pushPage(currentPage);
			} else {
				// removes reference in current form
				if (elementId != null) {
					controller.removeReference(navigationPath.peekCurrentPage().getNode(),
							elementId);

				}
			}
			setSubmissionDefaultLocation(getServletURL(), result);
		}
	}

	/**
	 * Delete node.
	 * 
	 * @return the string
	 */
	private void deleteNode() {
		elementId = DOMUtil.getNodeValueByTagName(node, MsgId.INT_INSTANCE_SIDEID.getText(), false);
		// #977: deletes fail to be performed in form's
		if (elementId == null) {
			Element root = ((Document) node).getDocumentElement();
			Element data = DOMUtil.getFirstElement(root);
			elementId = DOMUtil.getNodeValueByTagName(data, MsgId.INT_INSTANCE_SIDEID.getText(),
					false);
		}
		// #977
		// we should always have a valid Id here
		if (elementId != null) {
			controller.delete(transaction, elementId);
		} else {
			navigationPath.setStatusMsg("Delete was NOT performed: no object ID.");
		}
	}

}
