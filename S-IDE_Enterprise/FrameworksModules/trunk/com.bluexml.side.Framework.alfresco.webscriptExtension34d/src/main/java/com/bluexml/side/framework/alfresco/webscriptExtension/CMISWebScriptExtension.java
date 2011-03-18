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


package com.bluexml.side.framework.alfresco.webscriptExtension;

import java.util.ArrayList;
import java.util.List;

import org.alfresco.cmis.CMISDictionaryService;
import org.alfresco.cmis.CMISQueryService;
import org.alfresco.cmis.CMISResultSet;
import org.alfresco.cmis.CMISResultSetRow;
import org.alfresco.cmis.CMISServices;
import org.alfresco.cmis.search.CMISResultSetImpl;
import org.alfresco.repo.cmis.rest.CMISScript;
import org.alfresco.repo.jscript.BaseScopableProcessorExtension;
import org.alfresco.repo.model.Repository;
import org.alfresco.repo.template.TemplateNode;
import org.alfresco.repo.web.scripts.RepositoryImageResolver;
import org.alfresco.repo.web.util.paging.Page;
import org.alfresco.repo.web.util.paging.PagedResults;
import org.alfresco.repo.web.util.paging.Paging;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.apache.commons.beanutils.ResultSetIterator;
import org.apache.log4j.Logger;

public class CMISWebScriptExtension extends BaseScopableProcessorExtension {
	private Logger logger = Logger.getLogger(getClass());
	private ServiceRegistry serviceRegistry;
	private Repository repository;
	private CMISServices cmisService;
	private CMISDictionaryService cmisDictionaryService;
	private CMISQueryService cmisQueryService;
	private Paging paging;
	private CMISScript cmiswebscript;
	private RepositoryImageResolver imageResolver;
	public static final String FOLDER = "cmis:folder";

	/**
	 * @return the cmiswebscript
	 */
	public CMISScript getCmiswebscript() {
		return cmiswebscript;
	}

	/**
	 * @param cmiswebscript
	 *            the cmiswebscript to set
	 */
	public void setCmiswebscript(CMISScript cmiswebscript) {
		this.cmiswebscript = cmiswebscript;
	}

	/**
	 * @return the repository
	 */
	public Repository getRepository() {
		return repository;
	}

	/**
	 * @param repository
	 *            the repository to set
	 */
	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	/**
	 * @return the cmisService
	 */
	public CMISServices getCmisService() {
		return cmisService;
	}

	/**
	 * @param cmisService
	 *            the cmisService to set
	 */
	public void setCmisService(CMISServices cmisService) {
		this.cmisService = cmisService;
	}

	/**
	 * @return the cmisDictionaryService
	 */
	public CMISDictionaryService getCmisDictionaryService() {
		return cmisDictionaryService;
	}

	/**
	 * @param cmisDictionaryService
	 *            the cmisDictionaryService to set
	 */
	public void setCmisDictionaryService(CMISDictionaryService cmisDictionaryService) {
		this.cmisDictionaryService = cmisDictionaryService;
	}

	/**
	 * @return the cmisQueryService
	 */
	public CMISQueryService getCmisQueryService() {
		return cmisQueryService;
	}

	/**
	 * @param cmisQueryService
	 *            the cmisQueryService to set
	 */
	public void setCmisQueryService(CMISQueryService cmisQueryService) {
		this.cmisQueryService = cmisQueryService;
	}

	/**
	 * @return the paging
	 */
	public Paging getPaging() {
		return paging;
	}

	/**
	 * @param paging
	 *            the paging to set
	 */
	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public PagedResults queryChildren(NodeRef folder, String contentType) {
		String cmisQuerySelect = "SELECT * FROM ";
		String tables = contentType;
		String clauseFolder = "IN_FOLDER('" + folder + "')";
		String clauses = " WHERE " + clauseFolder;
		String statement = cmisQuerySelect + tables + clauses;
		
		logger.debug("queryChildren :" + statement);
		
		Page p = this.paging.createUnlimitedPage();
		PagedResults paged = cmiswebscript.query(statement, p);
		return paged;
	}

	public List<TemplateNode> getChildren(NodeRef node, String types, Page page) {
		List<TemplateNode> ret = getChildren(node, types);
		int startAt = page.getNumber();
		int max = page.getSize();
		if (max > 0) {
			return ret.subList(startAt, startAt + max);
		} else {
			return ret;
		}

	}

	public List<TemplateNode> getChildren(NodeRef node, String types) {
		List<TemplateNode> results = new ArrayList<TemplateNode>();
		PagedResults pagedDocs = queryChildren(node, types);

		CMISResultSet resultSet = (CMISResultSet) pagedDocs.getResult();

		for (NodeRef nodeRef : resultSet.getNodeRefs()) {
			TemplateNode tnode = new TemplateNode(nodeRef, serviceRegistry, imageResolver.getImageResolver());
			if (tnode.getIsDocument()) {
				logger.debug("add result :" + tnode.getNodeRef());
				results.add(tnode);
			}
		}

		PagedResults pagedFolders = queryChildren(node, FOLDER);
		CMISResultSet resultSetFolders = (CMISResultSet) pagedFolders.getResult();
		for (NodeRef nodeRef : resultSetFolders.getNodeRefs()) {
			// folder
			logger.debug("search in folder ..." + nodeRef);
			List<TemplateNode> tmp = getChildren(nodeRef, types);
			results.addAll(tmp);
		}
		return results;

	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	public void setImageResolver(RepositoryImageResolver imageResolver) {
		this.imageResolver = imageResolver;
	}

	public RepositoryImageResolver getImageResolver() {
		return imageResolver;
	}

}
