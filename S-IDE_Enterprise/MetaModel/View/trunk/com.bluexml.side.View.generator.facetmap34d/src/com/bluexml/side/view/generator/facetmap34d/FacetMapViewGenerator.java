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


package com.bluexml.side.view.generator.facetmap34d;

import java.util.List;

import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator;

public class FacetMapViewGenerator extends ViewFacetmapGenerator {

	@Override
	protected List<String> getTemplates() {
		List<String> templates = super.getTemplates();
		// replace xsl generation to transform cmis
		templates.remove("/com.bluexml.side.View.generator.facetmap/templates/facetmap-facets-cmis2xfml-generation.mt");
		templates.add("/com.bluexml.side.View.generator.facetmap34d/com/bluexml/side/view/generator/facetmap34d/templates/facetmap-facets-cmis2xfml-generation.mt");
		
		return templates;
	}

	
	
}
