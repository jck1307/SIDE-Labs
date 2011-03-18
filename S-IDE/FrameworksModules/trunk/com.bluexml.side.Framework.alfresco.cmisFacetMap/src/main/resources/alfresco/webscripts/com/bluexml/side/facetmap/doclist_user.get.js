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


<import resource="classpath:alfresco/templates/webscripts/org/alfresco/cmis/lib/read.lib.js">
script: {

	var contentType = "cmis:document";
	if (args["contentType"] && args["contentType"].indexOf(":") != -1) {
		contentType = args["contentType"];
	}

	// process paging
	var skipCount = null;
	if (args["skipCount"] && args["skipCount"].indexOf(":") != -1) {
		skipCount = parseInt(args["skipCount"]);
	}
	var pageSize = null;
	if (args["pageSize"] && args["pageSize"].indexOf(":") != -1) {
		pageSize = parseInt(args["pageSize"]);
	}

	var page = paging.createPageOrWindow(null, null, skipCount, pageSize);

	// perform query
	var paged;
	if (args["path"]) {
		var refType = "path";

		model.node = findFolder(args["path"], refType);
		if (model.node == null) {
			status.code = 500;
			status.message = "folder not found please check your request";
			status.redirect = true;
			break script;
		}
		model.contentType=contentType;
		model.resultset = sidecmis.getChildren(model.node.nodeRef, model.contentType, page);
		

	} else {

	}

	if (typeof (model.resultset) == "object") {
		// bad must convert type in array
		logger.warn("CMIS :" + model.resultset.length);
	}

	
}

function findFolder(path, refType) {
	var object = getObjectFromUrl();
    if (object.node == null)
    {
        return null;
    }
    var node = object.node;
    return node;
}
