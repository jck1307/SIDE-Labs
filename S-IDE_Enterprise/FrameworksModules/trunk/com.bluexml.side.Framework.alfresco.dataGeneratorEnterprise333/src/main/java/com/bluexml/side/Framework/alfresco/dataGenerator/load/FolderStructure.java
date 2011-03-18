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
 * This class ensures, if the option is checked in the webscript's form, the organization of the content
 * instances grouped by type into folders
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.load;

import java.io.Serializable;
import java.util.List;

import org.alfresco.model.ContentModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.repository.NodeRef;

/**
 * @author dchevrier
 *
 */
public class FolderStructure {
	
	private ServiceRegistry service;
	
	/**
	 * @return the service
	 */
	public ServiceRegistry getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(ServiceRegistry service) {
		this.service = service;
	}
	/**
	 * manage the organization by type into folders of content instances
	 * @param repository
	 * @return true if the process is successful
	 * @throws Exception
	 */
	public boolean manageFolders(NodeRef repository) throws Exception {
		// We choose to first generate the nodes and then organize them into folders moving them;
		// in this way, folders organization can be made as a webscript's option. 
		List<FileInfo> nodes = getContentInstances(repository);
		for (int l = 0; l < nodes.size(); l++){
			NodeRef node = nodes.get(l).getNodeRef();
			String nodeName = getNodeName(node);
			if (nodeName.length() == 0){
				throw new Exception("Node " + node.getId() + "has no name ...");
			}
			String[] nameParts = nodeName.split("_|[.]");
			String folderName = nameParts[0];
			NodeRef folder = getFolder(folderName, repository); 
			if(folder == null){
				folder = service.getFileFolderService().create(repository, folderName, ContentModel.TYPE_FOLDER).getNodeRef();
			}
			service.getFileFolderService().move(node, folder, nodeName);
		}
		return true;
	}
	
	/**
	 * allows access to a folder of a given name
	 * @param folderName
	 * @param repository
	 * @return folder of the given name if exists in the given repository;
	 * 		   null otherwise
	 */
	private NodeRef getFolder(String folderName, NodeRef repository) {
		NodeRef folder= null;
		List<FileInfo> folders = service.getFileFolderService().listFolders(repository);
		if (!folders.isEmpty()){
			for (int i = 0; i < folders.size(); i++){
				if (folders.get(i).getName().equals(folderName)){
					return folders.get(i).getNodeRef();
				}
			}
		}
		return folder;
	}
	
	/**
	 * allows access to the cm:name property of a given name
	 * @param node
	 * @return cm:name property of the given name
	 */
	private String getNodeName(NodeRef node) {
		Serializable nameProperty = service.getNodeService().getProperty(node, ContentModel.PROP_NAME);
		return nameProperty.toString();
	}
	
	/**
	 * allows access to the generated contents
	 * @param repository
	 * @return generated content instances 
	 */
	private List<FileInfo> getContentInstances(NodeRef repository) {
		return service.getFileFolderService().listFiles(repository);
	}

}
