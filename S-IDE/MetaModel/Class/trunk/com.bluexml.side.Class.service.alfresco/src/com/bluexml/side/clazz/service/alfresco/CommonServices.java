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


package com.bluexml.side.clazz.service.alfresco;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Model;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.common.Tag;

public class CommonServices {
	public static String getNamedModelElementQName(NamedModelElement node) {
		if (isNativeModel(node)) {
			return node.getName();
		}
		return convertFullNameToQualifiedName(node.getFullName());
	}

	public static String convertFullNameToQualifiedName(String fullName) {
		return fullName.replaceAll("\\.", "_");
		// return StringHelper.getJavaQName(fullName, "\\.");
	}

	public static EObject getRootContainer(EObject o) {
		if (o.eContainer() != null) {
			return getRootContainer(o.eContainer());
		} else {
			return o;
		}
	}

	public static boolean isNativeModel(ModelElement element) {		
		List<Tag> ts = element.getTags();
		for (Tag tag : ts) {
			String key = tag.getKey();
			String value = tag.getValue();
			if (key.contains("reversed") && value.contains("alfresco")) {
				return true;
			}
		}
		return false;
	}
	
	public static String getPrefixFromTag(ModelElement element) {		
		List<Tag> ts = element.getTags();
		for (Tag tag : ts) {
			String key = tag.getKey();
			String value = tag.getValue();
			if (key.equals("prefix")) {
				return value;
			}
		}
		return "";
	}
	
	public static String getNsURIFromTag(ModelElement element) {		
		List<Tag> ts = element.getTags();
		for (Tag tag : ts) {
			String key = tag.getKey();
			String value = tag.getValue();
			if (key.equals("reversedURI")) {
				return value;
			}
		}
		return "";
	}
	
	public static boolean isReversed(ModelElement element) {		
		List<Tag> ts = element.getTags();
		for (Tag tag : ts) {
			String key = tag.getKey();
			if (key.contains("reversed")) {
				return true;
			}
		}
		return false;
	}
	
	
	public static String getPrefixedQName(NamedModelElement node,String separator) throws Exception {
		return getPrefixe(node) + separator + CommonServices.getNamedModelElementQName(node);
	}
	public static String getPrefixedQName(NamedModelElement node) throws Exception {
		return getPrefixedQName(node, ":");
	}

	public static String getPrefixe(EObject node) throws Exception {
		String prefix = "";
		EObject root = CommonServices.getRootContainer(node);
		if (root instanceof Model) {
			prefix = ((Model) root).getName();
		} else if (root instanceof ClassPackage) {
			// ensure retro compatibility
			prefix = ((ClassPackage) root).getName();
		} else {
			throw new Exception("Missing RootPackage object !!");
		}

		return prefix;
	}

	public static String getNamespaceURI(EObject node) throws Exception {
		return "http://www.bluexml.com/model/content/" + getPrefixe(node) + "/1.0";
	}

	public static String getPrefixedNamespaceQName(NamedModelElement node) throws Exception {
		return "{" + getNamespaceURI(node) + "}" + CommonServices.getNamedModelElementQName(node);
	}
}
