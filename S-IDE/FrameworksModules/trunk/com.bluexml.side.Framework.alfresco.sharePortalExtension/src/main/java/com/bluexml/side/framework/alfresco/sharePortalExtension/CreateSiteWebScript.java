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


package com.bluexml.side.framework.alfresco.sharePortalExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.web.scripts.Cache;
import org.alfresco.web.scripts.DeclarativeWebScript;
import org.alfresco.web.scripts.Status;
import org.alfresco.web.scripts.WebScriptRequest;
import org.apache.log4j.Logger;

public class CreateSiteWebScript extends DeclarativeWebScript {
	private static Logger logger = Logger.getLogger(CreateSiteWebScript.class);
	protected PresetsManagerExtension persetManager;

	/**
	 * @return the persetManager
	 */
	public PresetsManagerExtension getPersetManager() {
		return persetManager;
	}

	/**
	 * @param persetManager
	 *            the persetManager to set
	 */
	public void setPersetManager(PresetsManagerExtension persetManager) {
		this.persetManager = persetManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.alfresco.web.scripts.DeclarativeWebScript#executeImpl(org.alfresco
	 * .web.scripts.WebScriptRequest, org.alfresco.web.scripts.Status,
	 * org.alfresco.web.scripts.Cache)
	 */
	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
		logger.debug("create Site JavaBacked webScript");
		Map<String, Object> model = new HashMap<String, Object>();
		List<String> ids = getPersetManager().getPresetsIds();
		String[] idst = ids.toArray(new String[ids.size()]);
		model.put("presets", idst);

		return model;
	}

}
