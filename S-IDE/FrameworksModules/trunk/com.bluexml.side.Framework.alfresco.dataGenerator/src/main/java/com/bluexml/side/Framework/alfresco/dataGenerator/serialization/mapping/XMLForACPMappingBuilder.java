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
 * This class manage the xml build containing instances's metadata by mapping 
 * generated nodes and arcs to their corresponding xml's representation
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.serialization.mapping;

import java.util.ArrayList;
import java.util.Collection;

import org.alfresco.service.namespace.NamespaceService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.bluexml.side.Framework.alfresco.dataGenerator.data.AlfrescoModelData;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.AlfrescoArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.IArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.INode;

/**
 * @author dchevrier
 *
 */
public class XMLForACPMappingBuilder implements IMapping {
	
	private Document document;
	private XMLForACPMappingHelper helper;
	private AlfrescoModelData alfrescoModelData;
	
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
	public XMLForACPMappingHelper getHelper() {
		return helper;
	}

	public void setHelper(XMLForACPMappingHelper helper) {
		this.helper = helper;
	}
	
	public AlfrescoModelData getAlfrescoModelData() {
		return alfrescoModelData;
	}

	public void setAlfrescoModelData(AlfrescoModelData alfrescoModelData) {
		this.alfrescoModelData = alfrescoModelData;
	}
	
	/**
	 * build the dom4j Document containing instances's metadata
	 * @return the created dom4j Document 
	 */
	public Document build (){
		
		document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");
		Element root = helper.createRoot();
		mapsAllNodes(root);
		mapsAllArcs(root);
		return document;
		
	}
	
	/**
	 * maps all generated nodes to their corresponding xml's elements
	 * @param root
	 */
	private void mapsAllNodes(Element root) {
		Collection<INode> generatedNodes = ((AlfrescoModelData) alfrescoModelData).getGeneratedTypesInstances();
		for (INode node : generatedNodes) {
			Element type = helper.createType(root,node);
			helper.createAspects(type,node);
			helper.createProperties(type,node);
		}
		
	}
	
	/**
	 * maps all generated arcs to their corresponding xml's elements
	 * @param root
	 */
	private void mapsAllArcs(Element root) {
		Collection<IArc> generatedArcs = ((AlfrescoModelData)alfrescoModelData).getGeneratedAssociationsInstances();
		Collection<IArc> tempUsedArcs = new ArrayList<IArc>();
		for (IArc iArc : generatedArcs) {
			if (!tempUsedArcs.contains(iArc)){
				INode src = ((AlfrescoArc)iArc).getSource();
				Element assoc = helper.createAssociation(root,src).addElement(helper.getServices().createTag(NamespaceService.REPOSITORY_VIEW_PREFIX,Constants.ASSOCIATIONS));
				Collection<IArc> arcsBySource = helper.getArcsBySource(((AlfrescoArc) iArc).getSource(),generatedArcs);
				for (IArc arcBySource : arcsBySource) {
					helper.createTargetAssociation(assoc,arcBySource);
				}
				tempUsedArcs.addAll(arcsBySource);	
			}
		}
	}

}
