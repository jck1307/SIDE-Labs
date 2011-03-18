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


package com.bluexml.side.Util.MetaModel.gendoc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;




public class Main {
	
	// TODO erreurs d'écriture dans les .ecore à gérer
	protected static Logger logger = Logger.getLogger("process");

	/**
	 * Effectue la génération de documentation, le fichier .diagramconfigurator
	 * étant passé en paramètre
	 * @param args
	 */
	public static void main(String[] args) {
		
		 Properties properties = new Properties();
		 try {
		     properties.load(new FileInputStream("modelspath.properties"));
		 } catch (IOException e) {
			 StringBuffer sb = new StringBuffer();
				for (int i = 0; i < e.getStackTrace().length; i++){
					sb.append(e.getStackTrace()[i]+"\n");
				}
			logger.log(Level.SEVERE, e.toString()+"\n"+sb.toString());
		 }
		 Set<Object> keys = properties.keySet();
		 for (Object key : keys) {
			String palettePath = properties.getProperty((String) key);
			if (palettePath.indexOf(".ecore")>0) {
				// case of a file with the extension .ecore in the file modelspath.properties
				EPackage ePackage = Main.getEPackage(palettePath);
				List<String> objects = new ArrayList<String>();
				List<EClassifier> classifiers = ePackage.getEClassifiers();
				for (EClassifier classifier : classifiers) {
					objects.add(classifier.getName());
				}
				
				DocMetaModel processDoc = new DocMetaModel();
				processDoc.head(ePackage);
				processDoc.processPackage(ePackage, objects);
				processDoc.foot(ePackage);
			}
			else {
				HashMap<String, List<String>> palette = ParsePalette.extractNames(ParsePalette.extractGenClass(palettePath));
				Set<String> metamodels = palette.keySet();
				for (String metamodel : metamodels) {
					List<String> objects = palette.get(metamodel);
					DocMetaModel processDoc = new DocMetaModel();
					EPackage ePackage = getEPackage(metamodel);
					processDoc.head(ePackage);
					processDoc.processPackage(ePackage, objects);
					processDoc.foot(ePackage);
				}
			}
		 }	
	}

	
	/**
	 * Récupère le package père dans un fichier .ecore
	 * @param file
	 * @return
	 */
	public static EPackage getEPackage(String file){
		
		EPackage ePackage = null;
		try{
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());
			ResourceSet resourceSet = new ResourceSetImpl();
			Resource resource = resourceSet.getResource(URI.createFileURI(file), true);
			EList<EObject> contents = resource.getContents();
			EObject rootContent = contents.get(0);
			if (rootContent instanceof EPackageImpl){
				ePackage = (EPackage) rootContent;
			}
			else{
				logger.log(Level.WARNING, "Erreur process.Main.getEPackage: il n'y a pas de package 'père'.");
			}
			
		}
		catch (Exception e){
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < e.getStackTrace().length; i++){
				sb.append(e.getStackTrace()[i]+"\n");
			}
			logger.log(Level.SEVERE, e.toString()+"\n"+sb.toString());
		}
		return ePackage;
		
	}

}
