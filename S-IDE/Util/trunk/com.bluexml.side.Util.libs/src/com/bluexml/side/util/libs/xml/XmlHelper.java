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


package com.bluexml.side.util.libs.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.jdom.Content;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class XmlHelper {

	public static Document buildJdomDocument(String serializedDoc) throws JDOMException, IOException {
		Document doc;
		org.jdom.input.SAXBuilder builder = new SAXBuilder();
		StringReader sr = new StringReader(serializedDoc);
		doc = builder.build(sr);
		return doc;
	}

	public static Document buildJdomDocument(File xmlFile) throws Exception {
		Document doc;
		org.jdom.input.SAXBuilder builder = new SAXBuilder();
		doc = builder.build(xmlFile);
		return doc;
	}

	public static void writeXmlFile(File f, Document doc) throws Exception {
		XMLOutputter outputer = new XMLOutputter();
		FileWriter fwriter = new FileWriter(f);
		outputer.output(doc, fwriter);
	}
	
/**
 * Use this method to include a Document into another one
 * @param base the document where include
 * @param toInclude the document to include
 * @param keepRoot true if the root
 * @return
 */
	public static Document includeDocument(Document base, Document toInclude, boolean keepRoot) {
		if (keepRoot) {
			base.getRootElement().addContent(toInclude.getRootElement().detach());
		} else {
			List<Content> ch = toInclude.getRootElement().getChildren();
			// use ultimate tips to avoid
			// java.util.ConcurrentModificationException
			Iterator<Content> it = ch.iterator();
			while (it.hasNext()) {
				Content current = it.next();
				it.remove();
				base.getRootElement().addContent(current);
			}
		}
		return base;
	}
}
