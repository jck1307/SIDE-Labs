<%--
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

--%>


<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("javaWebServicesAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/crud/AbstractClassFactory.java<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package com.bluexml.side.alfresco.crud;

import java.rmi.RemoteException;

import org.alfresco.webservice.authoring.AuthoringFault;
import org.alfresco.webservice.content.Content;
import org.alfresco.webservice.content.ContentFault;
import org.alfresco.webservice.content.ContentServiceSoapBindingStub;
import org.alfresco.webservice.repository.Association;
import org.alfresco.webservice.repository.QueryResult;
import org.alfresco.webservice.repository.RepositoryFault;
import org.alfresco.webservice.repository.UpdateResult;
import org.alfresco.webservice.types.CML;
import org.alfresco.webservice.types.CMLCreateAssociation;
import org.alfresco.webservice.types.CMLDelete;
import org.alfresco.webservice.types.CMLRemoveAssociation;
import org.alfresco.webservice.types.ContentFormat;
import org.alfresco.webservice.types.Node;
import org.alfresco.webservice.types.Predicate;
import org.alfresco.webservice.types.Query;
import org.alfresco.webservice.types.Reference;
import org.alfresco.webservice.types.ResultSet;
import org.alfresco.webservice.types.Store;
import org.alfresco.webservice.types.Version;
import org.alfresco.webservice.types.VersionHistory;
import org.alfresco.webservice.util.Constants;
import org.alfresco.webservice.util.WebServiceFactory;

public abstract class AbstractClassFactory {
	
	protected Store store = null;
	
	public AbstractClassFactory() {
		store = new Store(Constants.WORKSPACE_STORE, "SpacesStore");
	}
	
	public AbstractClassFactory(Store store) {
		this.store = store;
	}
	
	/**
	 * Method to delete a node
	 * @param uuid
	 * @return
	 * @throws RepositoryFault
	 * @throws RemoteException
	 */
	public UpdateResult[] delete(String uuid) throws RepositoryFault, RemoteException {
		Reference doc = new Reference(store, uuid, null);
		Predicate predicate = new Predicate(new Reference[] { doc }, null, null);
		CMLDelete delete = new CMLDelete(predicate);
		CML cml = new CML();
		cml.setDelete(new CMLDelete[] { delete });
		
		// Execute CML Block
		return WebServiceFactory.getRepositoryService().update(cml);
	}

	/**
	 * Method to create association
	 * @param sourceNodeUuid
	 * @param associationQName
	 * @param targetNodeUuid
	 * @return
	 * @throws RepositoryFault
	 * @throws RemoteException
	 */
	protected UpdateResult[] createAssociation(String sourceNodeUuid,String associationQName, String targetNodeUuid) throws RepositoryFault, RemoteException {
		Reference nodeFrom = new Reference(store, sourceNodeUuid, null);
		Reference nodeTo = new Reference(store, targetNodeUuid, null);
		Predicate from =new Predicate(new Reference[] { nodeFrom }, store, null);
		Predicate to = new Predicate(new Reference[] { nodeTo }, store, null);
		CMLCreateAssociation cmlCreateAsso = new CMLCreateAssociation(from, sourceNodeUuid, to, targetNodeUuid, associationQName);
		CML cml = new CML();
		cml.setCreateAssociation(new CMLCreateAssociation[]{cmlCreateAsso});
		// Execute CML Block
		return WebServiceFactory.getRepositoryService().update(cml);
	}
	
	/**
	 * Method to remove association
	 * @param sourceNodeUuid
	 * @param associationQName
	 * @param targetNodeUuid
	 * @return
	 * @throws RepositoryFault
	 * @throws RemoteException
	 */
	protected UpdateResult[] removeAssociation(String sourceNodeUuid,String associationQName, String targetNodeUuid) throws RepositoryFault, RemoteException {
		Reference nodeFrom = new Reference(store, sourceNodeUuid, null);
		Reference nodeTo = new Reference(store, targetNodeUuid, null);
		Predicate from =new Predicate(new Reference[] { nodeFrom }, store, null);
		Predicate to = new Predicate(new Reference[] { nodeTo }, store, null);
		CMLRemoveAssociation cmlRemoveAsso = new CMLRemoveAssociation(from, sourceNodeUuid, to, targetNodeUuid, associationQName);
		CML cml = new CML();
		cml.setRemoveAssociation(new CMLRemoveAssociation[]{cmlRemoveAsso});
		
		// Execute CML Block
		return WebServiceFactory.getRepositoryService().update(cml);
	}
	
	/**
	 * Request Alfresco repository using lucene 
	 * @param luceneQuery
	 * @return
	 * @throws RepositoryFault
	 * @throws RemoteException
	 */
	protected ResultSet  requestLucene(String luceneQuery) throws RepositoryFault, RemoteException {
		Query query = new Query(Constants.QUERY_LANG_LUCENE, luceneQuery);
		// Execute the query
		QueryResult queryResult = WebServiceFactory.getRepositoryService().query(store, query, false);
		ResultSet resultSet = queryResult.getResultSet();
		return resultSet;
	}
	
	/**
	 * Method to get node associated
	 * @param uuid node to search target or source
	 * @param associationType association QName 
	 * @param direction associated node to return
	 * @throws RepositoryFault
	 * @throws RemoteException
	 */
	protected QueryResult getAssociatedNode(String uuid, String associationType, String direction) throws RepositoryFault, RemoteException {
		Reference node = new Reference(store, uuid, null);
		Association asso = new Association(associationType, direction);
		return WebServiceFactory.getRepositoryService().queryAssociated(node, asso);
	}
	
	/**
	 * Get all versions of this node
	 * 
	 * @param uuid
	 *            of the document (in current version)
	 * @return
	 * @throws AuthoringFault
	 * @throws RemoteException
	 */
	public Version[] getVersions(String uuid) throws AuthoringFault, RemoteException {
		VersionHistory vh = WebServiceFactory.getAuthoringService().getVersionHistory(new Reference(store, uuid, null));
		return vh.getVersions();
	}

	/**
	 * Get a specific version
	 * 
	 * @param uuid
	 *            (current version)
	 * @param version
	 *            (version label)
	 * @return
	 * @throws AuthoringFault
	 * @throws RemoteException
	 */
	public Version getVersion(String uuid, String version) throws AuthoringFault, RemoteException {
		VersionHistory vh = WebServiceFactory.getAuthoringService().getVersionHistory(new Reference(store, uuid, null));
		Version[] vs = vh.getVersions();
		for (Version v : vs) {
			if (v.getLabel().equals(version)) {
				return v;
			}
		}
		return null;
	}

	/**
	 * Get File content of this node
	 * 
	 * @param uuid
	 *            (node in current version)
	 * @param version
	 *            (version label)
	 * @return
	 * @throws ContentFault
	 * @throws RemoteException
	 */
	public Content getContent(String uuid, String version) throws ContentFault, RemoteException {
		ContentServiceSoapBindingStub contentService = WebServiceFactory.getContentService();
		Reference ref = null;
		if (version != null) {
			Version v = getVersion(uuid, version);
			ref = v.getId();
		}
		Content[] readResult = contentService.read(new Predicate(new Reference[] { ref }, ref.getStore(), null), Constants.PROP_CONTENT);
		Content content = readResult[0];
		return content;
	}

	/**
	 * Set file content of this node
	 * 
	 * @param uuid uuid of the node
	 * @param bytes content to write
	 * @param format mimetype
	 * @return
	 * @throws ContentFault
	 * @throws RemoteException
	 */
	public Content setContent(String uuid, byte[] bytes, ContentFormat format) throws ContentFault, RemoteException {
		Content content = WebServiceFactory.getContentService().write(new Reference(store, uuid, null), Constants.PROP_CONTENT, bytes, format);
		return content;
	}
	
	/**
	 * get Node object
	 * @param uuid
	 * @return
	 * @throws RemoteException
	 * @throws RepositoryFault
	 */
	protected Node getNode(String uuid) throws RemoteException, RepositoryFault {
		Reference r = new Reference(store, uuid, null);
		Node[] n = WebServiceFactory.getRepositoryService().get(new Predicate(new Reference[]{r}, store, null));
		return n[0];
	}
}
