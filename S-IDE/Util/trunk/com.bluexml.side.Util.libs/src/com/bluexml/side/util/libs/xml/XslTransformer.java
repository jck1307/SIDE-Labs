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
 * 	Copyright (C) BlueXML 2005-2009
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
package com.bluexml.side.util.libs.xml;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.transform.JDOMResult;
import org.w3c.dom.Document;

/**
 * 
 * @author pbertrand
 * 
 */
public class XslTransformer {

	private XslTransformer() {
	};

	/**
	 * Fonction qui transforme un fichier src
	 * 
	 * @param src
	 *            Source file (path)
	 * @param xsl
	 *            Stylesheet (path)
	 * @param result
	 *            Result file (path)
	 * @return A reference on the result file
	 * @throws Exception 
	 */
	public static Result transform(String src, String xsl, String result) throws Exception {
		// get resources
		DocumentBuilderFactory fabriqueD = DocumentBuilderFactory.newInstance();
		DocumentBuilder constructeur;
		constructeur = fabriqueD.newDocumentBuilder();
		File fileXml = new File(src);
		Document document = constructeur.parse(fileXml);
		Source source = new DOMSource(document);
		File fileHtml = new File(result);
		Result resultat = new StreamResult(fileHtml);

		// set transformer
		TransformerFactory fabriqueT = net.sf.saxon.TransformerFactoryImpl.newInstance();
		StreamSource stylesource = new StreamSource(new File(xsl));
		Transformer transformer = fabriqueT.newTransformer(stylesource);
		// transformer.setOutputProperty(OutputKeys.METHOD, "html");

		// Transformation
		transformer.transform(source, resultat);
		return resultat;
	}

	public static Result transform(Document document, String xsl, String result) throws Exception {
		// get resources
		File fileHtml = new File(result);
		Result resultat = new StreamResult(fileHtml);
		Source source = new DOMSource(document);

		// set transformer
		TransformerFactory fabriqueT = net.sf.saxon.TransformerFactoryImpl.newInstance();
		StreamSource stylesource = new StreamSource(new File(xsl));
		Transformer transformer = fabriqueT.newTransformer(stylesource);

		// Transformation
		transformer.transform(source, resultat);
		return resultat;
	}

	public static void transform(org.jdom.Document doc, File xslName, File htmlName) throws Exception {
		JDOMResult docResult = new JDOMResult();
		org.jdom.Document resultat = null;
		TransformerFactory factory = net.sf.saxon.TransformerFactoryImpl.newInstance();

		Transformer transformer = factory.newTransformer(new StreamSource(xslName));

		transformer.transform(new org.jdom.transform.JDOMSource(doc), docResult);
		resultat = docResult.getDocument();
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		outputter.output(resultat, new FileOutputStream(htmlName));

	}

}
