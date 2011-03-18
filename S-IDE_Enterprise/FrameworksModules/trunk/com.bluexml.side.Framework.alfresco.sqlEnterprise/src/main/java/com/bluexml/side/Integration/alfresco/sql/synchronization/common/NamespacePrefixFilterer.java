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


package com.bluexml.side.Integration.alfresco.sql.synchronization.common;

import org.alfresco.model.ContentModel;
import org.alfresco.service.namespace.QName;

public class NamespacePrefixFilterer extends AbstractFilterer {
	
	public boolean acceptQName(QName qname) {
		return qname.getNamespaceURI().startsWith(namespacePrefix);
	}

	public boolean acceptPropertyQName(QName qname) {
		return super.acceptPropertyQName(qname) || ContentModel.PROP_NODE_DBID.equals(qname) || ContentModel.PROP_NODE_UUID.equals(qname);
	}
	
	//
	// IoC/DI Spring
	//

	// Dependencies
	private String namespacePrefix;

	public void setNamespacePrefix(String namespacePrefix_) {
		namespacePrefix = namespacePrefix_;
	}

	
}
