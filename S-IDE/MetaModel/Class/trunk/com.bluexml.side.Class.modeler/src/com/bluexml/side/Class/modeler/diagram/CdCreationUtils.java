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


/*******************************************************************************
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package com.bluexml.side.Class.modeler.diagram;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.topcased.modeler.di.model.DiagramInterchangeFactory;
import org.topcased.modeler.di.model.EdgeObjectOffset;
import org.topcased.modeler.di.model.EdgeObjectUV;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.editor.AbstractCreationUtils;
import org.topcased.modeler.graphconf.DiagramGraphConf;

import com.bluexml.side.Class.modeler.ClazzPlugin;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.clazz.util.ClazzSwitch;

/**
 * This utility class allows to create a GraphElement associated with a Model Object
 *
 * @generated
 */
public class CdCreationUtils extends AbstractCreationUtils {

	private static final int LABEL_OFFSET = 10;
	
	/**
	 * Constructor
	 *
	 * @param diagramConf the Diagram Graphical Configuration
	 * @generated
	 */
	public CdCreationUtils(DiagramGraphConf diagramConf) {
		super(diagramConf);
	}

	/**
	 * @generated
	 */
	private class GraphicClazzSwitch extends ClazzSwitch {
		/**
		 * The presentation of the graphical element
		 *
		 * @generated
		 */
		private String presentation;

		/**
		 * Constructor
		 *
		 * @param presentation the presentation of the graphical element
		 * @generated
		 */
		public GraphicClazzSwitch(String presentation) {
			this.presentation = presentation;
		}

		/**
		 * @see com.bluexml.side.clazz.util.ClazzSwitch#caseClazz(com.bluexml.side.clazz.Clazz)
		 * @generated
		 */
		public Object caseClazz(com.bluexml.side.clazz.Clazz object) {
			if ("default".equals(presentation)) {
				return createGraphElementClazz(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.clazz.util.ClazzSwitch#caseAspect(com.bluexml.side.clazz.Aspect)
		 * @generated
		 */
		public Object caseAspect(com.bluexml.side.clazz.Aspect object) {
			if ("default".equals(presentation)) {
				return createGraphElementAspect(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.clazz.util.ClazzSwitch#caseAttribute(com.bluexml.side.clazz.Attribute)
		 * @generated
		 */
		public Object caseAttribute(com.bluexml.side.clazz.Attribute object) {
			if ("default".equals(presentation)) {
				return createGraphElementAttribute(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.clazz.util.ClazzSwitch#caseClassComment(com.bluexml.side.clazz.ClassComment)
		 * @generated
		 */
		public Object caseClassComment(com.bluexml.side.clazz.ClassComment object) {
			if ("default".equals(presentation)) {
				return createGraphElementClassComment(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.clazz.util.ClazzSwitch#caseEnumeration(com.bluexml.side.clazz.Enumeration)
		 * @generated
		 */
		public Object caseEnumeration(com.bluexml.side.clazz.Enumeration object) {
			if ("default".equals(presentation)) {
				return createGraphElementEnumeration(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.clazz.util.ClazzSwitch#caseEnumerationLiteral(com.bluexml.side.clazz.EnumerationLiteral)
		 * @generated
		 */
		public Object caseEnumerationLiteral(com.bluexml.side.clazz.EnumerationLiteral object) {
			if ("default".equals(presentation)) {
				return createGraphElementEnumerationLiteral(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.clazz.util.ClazzSwitch#caseAssociation(com.bluexml.side.clazz.Association)
		 * @generated
		 */
		public Object caseAssociation(com.bluexml.side.clazz.Association object) {
			if ("default".equals(presentation)) {
				return createGraphElementAssociation(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.clazz.util.ClazzSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object) {
			return null;
		}
	}

	/**
	 * @see org.topcased.modeler.editor.ICreationUtils#createGraphElement(org.eclipse.emf.ecore.EObject, java.lang.String)
	 * @generated
	 */
	public GraphElement createGraphElement(EObject obj, String presentation) {
		Object graphElt = null;

		if ("http://www.kerblue.org/class/1.0".equals(obj.eClass().getEPackage().getNsURI())) {
			graphElt = new GraphicClazzSwitch(presentation).doSwitch(obj);
		}

		return (GraphElement) graphElt;
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementClazz(com.bluexml.side.clazz.Clazz element, String presentation) {
		// TODO this snippet of code should be customized if it is not well generated
		GraphNode nodeParent = createGraphNode(element, presentation);

		GraphNode attribute = createGraphNode(element, ClazzPackage.CLAZZ__ATTRIBUTES, presentation);
		attribute.setContainer(nodeParent);

		GraphNode operation = createGraphNode(element, ClazzPackage.CLAZZ__OPERATIONS, presentation);
		operation.setContainer(nodeParent);

		GraphNode aspect = createGraphNode(element, ClazzPackage.CLAZZ__ASPECTS, presentation);
		aspect.setContainer(nodeParent);

		return nodeParent;
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementAspect(com.bluexml.side.clazz.Aspect element, String presentation) {
		// TODO this snippet of code should be customized if it is not well generated
		GraphNode nodeParent = createGraphNode(element, presentation);

		GraphNode attribute = createGraphNode(element, ClazzPackage.ASPECT__ATTRIBUTES, presentation);
		attribute.setContainer(nodeParent);

		return nodeParent;
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementAttribute(com.bluexml.side.clazz.Attribute element, String presentation) {
		return createGraphNode(element, presentation);
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementClassComment(com.bluexml.side.clazz.ClassComment element, String presentation) {
		return createGraphNode(element, presentation);
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementEnumeration(com.bluexml.side.clazz.Enumeration element, String presentation) {
		// TODO this snippet of code should be customized if it is not well generated
		GraphNode nodeParent = createGraphNode(element, presentation);

		GraphNode enumerationliteral = createGraphNode(element, ClazzPackage.ENUMERATION__LITERALS, presentation);
		enumerationliteral.setContainer(nodeParent);

		return nodeParent;
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementEnumerationLiteral(com.bluexml.side.clazz.EnumerationLiteral element, String presentation) {
		return createGraphNode(element, presentation);
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @_generated
	 */
	protected GraphElement createGraphElementAssociation(com.bluexml.side.clazz.Association element, String presentation) {
		GraphEdge graphEdge = createGraphEdge(element, presentation);
		
        EdgeObjectUV srcnameEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
        srcnameEdgeObjectUV.setId(CdEdgeObjectConstants.SRCNAME_EDGE_OBJECT_ID);
        srcnameEdgeObjectUV.setUDistance(LABEL_OFFSET);
        srcnameEdgeObjectUV.setVDistance(LABEL_OFFSET);
        graphEdge.getContained().add(srcnameEdgeObjectUV);
        EdgeObjectUV srccountEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
        srccountEdgeObjectUV.setId(CdEdgeObjectConstants.SRCCOUNT_EDGE_OBJECT_ID);
        srccountEdgeObjectUV.setUDistance(LABEL_OFFSET);
        srccountEdgeObjectUV.setVDistance(-LABEL_OFFSET);
        graphEdge.getContained().add(srccountEdgeObjectUV);
        EdgeObjectUV targetnameEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
        targetnameEdgeObjectUV.setId(CdEdgeObjectConstants.TARGETNAME_EDGE_OBJECT_ID);
        targetnameEdgeObjectUV.setUDistance(LABEL_OFFSET);
        targetnameEdgeObjectUV.setVDistance(LABEL_OFFSET);
        graphEdge.getContained().add(targetnameEdgeObjectUV);
        EdgeObjectUV targetcountEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
        targetcountEdgeObjectUV.setId(CdEdgeObjectConstants.TARGETCOUNT_EDGE_OBJECT_ID);
        targetcountEdgeObjectUV.setUDistance(LABEL_OFFSET);
        targetcountEdgeObjectUV.setVDistance(-LABEL_OFFSET);
        graphEdge.getContained().add(targetcountEdgeObjectUV);
        EdgeObjectOffset middlenameEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
        middlenameEdgeObjectOffset.setId(CdEdgeObjectConstants.MIDDLENAME_EDGE_OBJECT_ID);
        middlenameEdgeObjectOffset.setOffset(new Dimension(0, 0));
        graphEdge.getContained().add(middlenameEdgeObjectOffset);
        return graphEdge;
	}

	/**
	 * Create the ModelObject with its initial children
	 * 
	 * @param obj the model object
	 * @return the model object with its children
	 * @generated
	 */
	public EObject createModelObject(EObject obj) {
		return obj;
	}

	/**
	 * Get the preference store associated with the current editor.
	 * 
	 * @return IPreferenceStore
	 * @generated
	 */
	private IPreferenceStore getPreferenceStore() {
		IEditorInput editorInput = ClazzPlugin.getActivePage().getActiveEditor().getEditorInput();
		if (editorInput instanceof IFileEditorInput) {
			IProject project = ((IFileEditorInput) editorInput).getFile().getProject();
			Preferences root = Platform.getPreferencesService().getRootNode();
			try {
				if (root.node(ProjectScope.SCOPE).node(project.getName()).nodeExists(ClazzPlugin.getId())) {
					return new ScopedPreferenceStore(new ProjectScope(project), ClazzPlugin.getId());
				}
			} catch (BackingStoreException e) {
				e.printStackTrace();
			}
		}
		return ClazzPlugin.getDefault().getPreferenceStore();
	}
}
