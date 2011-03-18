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


package com.bluexml.side.integration.cleanEclipse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.bluexml.side.integration.cleanEclipse.utils.FileHelper;

public class Application {

	public static void main(String[] args) {
		String eclipsePath = "";
		String projectName = "com.bluexml.side";
		if (args.length != 0) {
			eclipsePath = args[0];
			if (args.length == 2) {
				projectName = args[1];
			}
		} else {
			System.out.println("Parameter 1 -> the eclipse path to clean.");
			System.out
					.println("Parameter 2 -> the project name (default 'com.bluexml.side').");
			System.out
					.println("\nThis application will clean eclipse of any part of the project given in parameter.");
			System.out.println("Examples:");
			System.out
					.println("\t- If you gives 'com.bluexml.side.Application', -> only this project will be deleted from eclipse.");
			System.out
					.println("\t- If you gives 'com.bluexml.side', -> all projects which contains this name will be deleted.");
			System.exit(0);
		}

		/*
		 * browse(projectName, new File(eclipsePath + File.separator +
		 * "configuration" + File.separator + "org.eclipse.osgi" +
		 * File.separator + "bundles"), ".xml", "units", "unit", "id");
		 * 
		 * browse(projectName, new File(eclipsePath + File.separator + "p2" +
		 * File.separator + "org.eclipse.equinox.p2.core" + File.separator +
		 * "cache"), "content.xml", "units", "unit", "id");
		 * 
		 * browse(projectName, new File(eclipsePath + File.separator +
		 * "configuration" + File.separator + "org.eclipse.update" +
		 * File.separator + "history"), ".xml", "site", "feature", "id");
		 */
		
		cleanFolder(projectName, new File(eclipsePath + File.separator
				+ "dropins"));

		cleanFolder(projectName, new File(eclipsePath + File.separator
				+ "features"));

		cleanFolder(projectName, new File(eclipsePath + File.separator
				+ "plugins"));

		cleanXml(projectName, eclipsePath + File.separator + "artifacts.xml",
				"artifacts", "artifact", "id");
	}

	/**
	 * Delete all the file which contain the project name into the file
	 * 
	 * @param projectName
	 *            The project name
	 * @param file
	 *            The file to clean
	 */
	private static void cleanFolder(String projectName, File file) {

		File[] files = file.listFiles();

		for (int i = 0; i < files.length; i++) {

			if (files[i].getName().toLowerCase().indexOf(projectName.toLowerCase()) != -1) {

				System.out.print("\t- " + files[i].getAbsolutePath() + " -> ");

				boolean out = FileHelper.deleteFile(files[i]);

				if (out) {
					System.out.print("Supprim�\n");
				} else {
					System.out.print("Non Supprim�\n");
				}

			} else if (files[i].isDirectory()) {
					cleanFolder(projectName, files[i]);
			}
		}
	}

	/**
	 * Browse the file in parameter, and when it find a file which contains the
	 * xmlFileName, it execute 'cleanXml' with the other arguments
	 * 
	 * 
	 * @param projectName
	 *            The project name to delete
	 * @param file
	 *            The file (folder) to browse
	 * @param xmlFileName
	 *            The XML file name to analyze
	 * @param rootName
	 *            The root Name if the XML file
	 * @param childrenName
	 *            The children name -> The node to delete if the 'attributeName'
	 *            match with the 'projectName'
	 * @param attributeName
	 *            The attribute of the node to analyze
	 */
	private static void browse(String projectName, File file,
			String xmlFileName, String rootName, String childrenName,
			String attributeName) {

		if (file.isDirectory()) {
			File[] fl = file.listFiles();
			for (int i = 0; i < fl.length; i++) {
				browse(projectName, fl[i], xmlFileName, rootName, childrenName,
						attributeName);
			}
		}
		if (file.exists() && file.getName().indexOf(xmlFileName) != -1) {
			cleanXml(projectName, file.getAbsolutePath(), rootName,
					childrenName, attributeName);
		}
	}

	/**
	 * Analyze the 'filePath' and delete all occurrence of the 'projectName'
	 * which match with the 'attributeName' into all nodes 'childrenName' of the
	 * 'rootName'
	 * 
	 * @param projectName
	 *            The project name to delete
	 * @param filePath
	 *            The XML file name to analyze
	 * @param rootName
	 *            The root Name if the XML file
	 * @param childrenName
	 *            The children name -> The node to delete if the 'attributeName'
	 *            match with the 'projectName'
	 * @param attributeName
	 *            The attribute of the node to analyze
	 */
	private static void cleanXml(String projectName, String filePath,
			String rootName, String childrenName, String attributeName) {
		System.out.println("Dans le fichier: " + filePath);

		List<Element> listeArtifact = new ArrayList<Element>();

		org.jdom.Document document = null;
		org.jdom.Element racine;

		// On cr�e une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();

		try {
			// On cr�e un nouveau document JDOM avec en argument le fichier
			// XML
			document = sxb.build(new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// On initialise un nouvel �l�ment racine avec l'�l�ment racine du
		// document.
		racine = document.getRootElement();

		// On cr�e une List contenant tous les noeuds "artifacts" de
		// l'Element racine
		List<?> listArtifacts = racine.getChildren(rootName);

		// On cr�e un Iterator sur notre liste
		Iterator<?> i = listArtifacts.iterator();
		// on va parcourir tous les plugins
		while (i.hasNext()) {

			// On cr�e une List contenant tous les noeuds "artifact" de
			// l'Element racine

			Element parent = (Element) i.next();
			List<?> listArtifact = parent.getChildren(childrenName);

			// On cr�e un Iterator sur notre liste
			Iterator<?> j = listArtifact.iterator();
			// on va parcourir tous les plugins
			while (j.hasNext()) {

				Element courant = (Element) j.next();
				if ((courant.getAttributeValue(attributeName).toLowerCase())
						.indexOf(projectName.toLowerCase()) != -1) {
					listeArtifact.add(courant);
					// parent.removeContent(courant);
				}
			}

			for (Element element : listeArtifact) {
				System.out.println("\t- Suppression de : "
						+ element.getAttributeValue(attributeName));
				parent.removeContent(element);
			}
		}

		// Enregistrement du fichier
		try {
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
