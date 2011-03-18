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


package org.demo.workflow.integration;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.json.JSONException;
import org.json.JSONObject;

public class Common {

	// must be nodeRef of PATH:"/app:company_home/st:sites/cm:demo/cm:documentLibrary/cm:digitHome/cm:inProgress"
	static String folderNodeRef = "workspace://SpacesStore/15e582ef-77e8-44d8-8294-90dbcb8bd68d";

	public static String getFirstTaskId(String workflow_instance_id, String authority) throws Exception {
		// get task list and use the first (replace user selection)
		Helper h = new Helper("admin", "admin", "http://localhost:8080/alfresco");

		String rt = h.getTasks(workflow_instance_id, authority, Helper.states[0], "-1", "0");
		System.out.println(rt);
		JSONObject root2 = new JSONObject(rt);
		String taskId = root2.getJSONArray("data").getJSONObject(0).getString("id");
		return taskId;
	}

	public static void delete(String workflow_instance_id) throws HttpException, IOException {

		Helper h = new Helper("admin", "admin", "http://localhost:8080/alfresco");
		h.deleteProcess(workflow_instance_id);
	}

	public static String createNewDoc() throws HttpException, IOException, JSONException {
		Map<String, String> m = new HashMap<String, String>();
		m.put("alf_destination", folderNodeRef);
		m.put("prop_cm_name", new Date().getTime() + "");
		m.put("prop_cm_title", "");
		m.put("prop_cm_description", "created doc to test workflow");
		m.put("prop_cm_content", "");
		m.put("prop_mimetype", "text/plain");
		m.put("prop_app_editInline", "true");
		Helper h = new Helper("admin", "admin", "http://localhost:8080/alfresco");
		String requestFormProcessor = h.requestFormProcessor("type", "cm_content", m);
		System.out.println(requestFormProcessor);
		String nodeRef = new JSONObject(requestFormProcessor).getString("persistedObject");
		return nodeRef;
	}
}
