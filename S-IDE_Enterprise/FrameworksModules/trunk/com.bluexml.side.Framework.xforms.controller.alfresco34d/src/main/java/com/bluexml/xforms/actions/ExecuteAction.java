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
package com.bluexml.xforms.actions;

import org.apache.commons.lang.StringUtils;

import com.bluexml.xforms.messages.MsgId;

/**
 * Provides support for user defined actions through action buttons (#956). The action must be
 * implemented as a Java class with signature:
 * <code>java.lang.String run(org.w3c.dom.Node, java.lang.String)</code>. <br/>
 * 
 * @author Amenel
 * 
 */
public class ExecuteAction extends AbstractWriteAction {

	private static final String CLASS_NAME = MsgId.INT_ACT_PARAM_EXEC_ACTION.getText();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {

		return MsgId.INT_ACT_CODE_EXECUTE.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getParamNames()
	 */
	@Override
	protected String[] getParamNames() {
		return new String[] { CLASS_NAME };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#submit()
	 */
	@Override
	public void submit() {
		try {
			// load the class
			String className = requestParameters.get(CLASS_NAME);
			String url = callExternalAction(className);
			if (StringUtils.trimToNull(url) != null) {
				redirectClient(url);
			} else {
				setSubmissionDefaultLocation(getServletURL(), result);
			}
			navigationPath.setStatusMsg("Your action completed successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
