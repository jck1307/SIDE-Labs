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


package com.bluexml.side.portal.generator.alfresco.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.common.Visibility;
import com.bluexml.side.portal.InternalPortletType;
import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.Portal;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.PortletInternal;

public class ShareGeneratorServices {
	Properties defaultShareElements;
	List<String> publicPages = new ArrayList<String>();
	public final static String defaultPublicPagesKey = "defaultPublicPages";
	List<String> defaultPages = new ArrayList<String>();
	public final static String defaultPagesKey = "defaultPages";

	public ShareGeneratorServices() {
		defaultShareElements = new Properties();
		InputStream in = this.getClass().getResourceAsStream(
				"defaultShareElements.properties");

		try {

			defaultShareElements.load(in);
			
			String[] pages =defaultShareElements.getProperty(defaultPagesKey).split(",");
			for (String string : pages) {
				publicPages.add(string);
			}
			String[] publicPages =defaultShareElements.getProperty(defaultPublicPagesKey).split(",");
			for (String string : publicPages) {
				defaultPages.add(string);
			}
			
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ShareGeneratorServices();
	}

	public static String getPublicPageList(Portal p) {
		EList<Page> l=p.getPageSet();
		List<String> rt = new ArrayList<String>();
		for (Page page : l) {
			if (page.getVisibility().equals(Visibility.PUBLIC)) {
				rt.add("{\"pageId\":\""+page.getID().toLowerCase()+"\"}");
			}
		}
		
		return rt.toString();
	}
	
	public static boolean isFormPortlet(Portlet p) {
		PortletInternal pInt=p.getIsPortletInternal();
		return pInt !=null && pInt.getType().equals(InternalPortletType.FORM);
	}
	public static boolean isViewPortlet(Portlet p) {
		PortletInternal pInt=p.getIsPortletInternal();
		return pInt !=null && pInt.getType().equals(InternalPortletType.VIEW);
	}
	public static boolean isCustomPortlet(Portlet p) {
		return p.getIsPortletInternal() == null && p.getIsInstanceOfPortletType() !=null;
	}
}
