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
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.facetmap.simple.SimpleFacetMapX;

public class FacetMapCacheManager {
	private Logger logger = Logger.getLogger(getClass());
	public static String cacheRep = "/multimap/cache";
	protected Map<String, SimpleFacetMapX> availableFacetMap = new HashMap<String, SimpleFacetMapX>();

	public FacetMapCacheManager() throws Exception {
		logger.debug("instanciate cache mananger");
		update();
	}

	public void update() throws Exception {
		File fileCacheRep = new Helper().getFileFromClassPath(cacheRep);
		if (fileCacheRep.exists()) {
			File[] l = fileCacheRep.listFiles();
			availableFacetMap.clear();
			for (File file : l) {
				try {
					InputStream finf = new FileInputStream(file);
					SimpleFacetMapX localMap = (SimpleFacetMapX) SimpleFacetMapX.createFromXml(finf);
					availableFacetMap.put(getMapKey(file), localMap);
					logger.debug("Registered Facet :" + getMapKey(file));
				} catch (Exception e) {
					logger.error("FacetMap " + getMapKey(file) + " not loaded :\n" + e.getMessage());
				}
			}
		}
		logger.debug("facetMaps :" + availableFacetMap.size());
	}

	public SimpleFacetMapX getFacetMap(String facetName, String community) throws Exception {

		String groupId = getMapKey(facetName, community);
		if (!availableFacetMap.containsKey(groupId)) {
			update();
			if (!availableFacetMap.containsKey(groupId)) {
				// update do not found required file
				logger.warn("required map not ready, must run update");
				return null;
			}
		}

		return availableFacetMap.get(getMapKey(facetName, community));
	}

	private static String getMapKey(File file) {
		return file.getName().replaceFirst("map_([^\\.]*)\\.xml", "$1");
	}

	public static String getMapKey(String facetName, String community) {
		return facetName + "_" + community;
	}

	public void deleteAllFacets() throws Exception {
		availableFacetMap.clear();
		// purge all facets (cache and files)
		File fileCacheRep = new Helper().getFileFromClassPath(cacheRep);
		if (fileCacheRep.exists()) {
			File[] l = fileCacheRep.listFiles();			
			for (File file : l) {
				try {
					FileUtils.forceDelete(file);
					logger.debug("Erase Facet :" + getMapKey(file));
				} catch (Exception e) {
					logger.error("FacetMap " + getMapKey(file) + " not Erase :\n" + e.getMessage());
				}
			}
		}
		logger.debug("facetMaps :" + availableFacetMap.size());
	}

	/**
	 * @return the availableFacetMap
	 */
	public Map<String, SimpleFacetMapX> getAvailableFacetMap() {
		return Collections.unmodifiableMap(availableFacetMap);
	}
	
}
