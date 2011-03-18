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


package com.bluexml.side.framework.facetmap.multimap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class FacetDisplayPropsCacheManager {
	protected Map<String, Properties> propertiesCache = new HashMap<String, Properties>();
	static String fileMatch = "configuration_([^\\.]*)\\.properties";
	private Logger logger = Logger.getLogger(getClass());

	static final String propertiesFiles = "/multimap";

	public FacetDisplayPropsCacheManager() throws Exception {
		logger.debug("FacetDisplayPropsCacheManager");
		update();
	}

	public Properties getProps(String facetName) throws FacetMapNotAvailableException {
		if (!propertiesCache.containsKey(facetName)) {
			// bad news this file must be generated using SIDE
			throw new FacetMapNotAvailableException("properties file missing for " + facetName + " use Side FacetMap generator");
		}
		return propertiesCache.get(facetName);
	}

	public void update() throws Exception {
		File fileCacheRep = new Helper().getFileFromClassPath(propertiesFiles);
		if (fileCacheRep.exists()) {
			File[] l = fileCacheRep.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.matches(fileMatch);
				}
			});
			propertiesCache.clear();
			for (File file : l) {
				try {
					InputStream finf = new FileInputStream(file);
					Properties props = new Properties();
					props.load(finf);
					propertiesCache.put(getMapKey(file), props);
					logger.debug("Properties File registered :" + getMapKey(file));

				} catch (Exception e) {
					logger.error("Properties file " + getMapKey(file) + "not loaded :", e);
				}
			}
		}
		if (propertiesCache.size() == 0) {
			logger.warn("no properties files found !(" + propertiesFiles + ")");
		}
		logger.debug("Properties Files :" + propertiesCache.size());
	}

	private static String getMapKey(File file) {
		return file.getName().replaceFirst(fileMatch, "$1");
	}

	private File getPropertiesFile(String facetName) throws Exception {
		File fileCacheRep = new Helper().getFileFromClassPath(propertiesFiles);
		return new File(fileCacheRep,"configuration_"+facetName+".properties");
		
	}

	public void save(String facetName, String comments) throws Exception {
		File f = getPropertiesFile(facetName);
		getProps(facetName).store(new FileOutputStream(f), comments);
	}

	/**
	 * @return the propertiesCache
	 */
	public Map<String, Properties> getPropertiesCache() {
		return Collections.unmodifiableMap(propertiesCache);
	}
	
	
}
