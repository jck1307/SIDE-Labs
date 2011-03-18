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
 * 	Copyright (C) BlueXML 2005-2008
 *
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
package com.bluexml.side.clazz.generator.alfresco.services;

import java.util.List;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.Class.modeler.diagram.utils.metainfo.value.VisualTypeComponent_Enum;
import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.common.Comment;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.Stereotype;

public class ClassServices {

	// Returns True if the class has got additional aspects attributes
	public static boolean definesAspects(Clazz cl) {
		boolean hasAspects = false;
		hasAspects = cl.getAspects().size() > 0;
		if (hasAspects) {
			return true;
		}
		return cl.getAllInheritedAttributes().size() != cl
				.getAllInheritedClassAndAspectAttributes().size();
	}



	public List<Association> getAllAssociationsByClass(Clazz cl) {
		List<Association> result = cl.getAllSourceAssociations();
		result.addAll(cl.getAllTargetAssociations());
		return result;
	}
	

	public static boolean useTabComponent(Clazz c) {
		for (Object obj : c.getMetainfo()) {
			if (obj instanceof MetaInfo) {
				MetaInfo mi = (MetaInfo) obj;
				if (mi.getKey().equalsIgnoreCase("visual-component")) {
					return mi.getValue().equalsIgnoreCase(
							VisualTypeComponent_Enum.HorizontalTab.toString())
							|| mi.getValue().equalsIgnoreCase(
									VisualTypeComponent_Enum.VerticalTab
											.toString());
				}
			}
		}
		return false;
	}

	public static boolean useSeperatorComponent(Clazz c) {
		for (Object obj : c.getMetainfo()) {
			if (obj instanceof MetaInfo) {
				MetaInfo mi = (MetaInfo) obj;
				if (mi.getKey().equalsIgnoreCase("visual-component")) {
					return mi.getValue().equalsIgnoreCase(
							VisualTypeComponent_Enum.Separator.toString());
				}
			}
		}
		return false;
	}

	public static boolean useVerticalTabComponent(Clazz c) {
		for (Object obj : c.getMetainfo()) {
			if (obj instanceof MetaInfo) {
				MetaInfo mi = (MetaInfo) obj;
				if (mi.getKey().equalsIgnoreCase("visual-component")) {
					return mi.getValue().equalsIgnoreCase(
							VisualTypeComponent_Enum.VerticalTab.toString());
				}
			}
		}
		return false;
	}

	public static boolean hasLabel(Clazz cl) {
		EList<?> list = cl.getComments();
		for (Object o : list) {
			if (o instanceof Comment) {
				Comment c = (Comment) o;
				EList<?> stereotypes = c.getStereotypes();
				for (Object o2 : stereotypes) {
					if (o2 instanceof Stereotype) {
						Stereotype s = (Stereotype) o2;
						if (s.getName().equalsIgnoreCase("view")) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static String getLabelHTML(Clazz c1) {
		HTMLEncoder htmlEncoder = new HTMLEncoder();
		String label = c1.getLabel();
		label = htmlEncoder.encode(label);		
		return label;
	}

	public static String getView(Clazz cl) {
		String resultLine = "";
		EList<?> list = cl.getComments();
		for (Object o : list) {
			if (o instanceof Comment) {
				Comment c = (Comment) o;
				EList<?> stereotypes = c.getStereotypes();
				for (Object o2 : stereotypes) {
					if (o2 instanceof Stereotype) {
						Stereotype s = (Stereotype) o2;
						if (s.getName().equalsIgnoreCase("view")) {
							String view = c.getValue();
							if (view.contains("\n")) {
								String firstLine = view.substring(0, view
										.lastIndexOf("\n") - 1);
								if (firstLine.startsWith("@actionsFile@")) {
									String othersLines = view
											.substring(firstLine.length() + 2);
									if (othersLines.contains("\n")) {
										String secondLine = othersLines
												.substring(0, view
														.lastIndexOf("\n") - 1);
										resultLine = secondLine;
									} else {
										resultLine = othersLines;
									}
								} else {
									resultLine = firstLine;
								}
							} else
								resultLine = view;
						}
					}
				}
			}
		}
		return resultLine.replaceAll("\\s", "");
	}

	public static String getQualifiedName(AbstractClass classe) {
		return classe.getFullName().replace(".", "_");
	}

	
}
