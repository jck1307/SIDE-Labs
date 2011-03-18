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


package com.bluexml.side.view.generator.alfresco34d;

import java.util.List;

public class ViewGenerator extends com.bluexml.side.view.generator.alfresco.extension.sideenterprise.ViewGenerator {

	@Override
	protected List<String> getTemplates() {
		List<String> templates = super.getTemplates();
		// replace template
		templates.remove("/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates/doclistView/doclist_ftl.mt");
		templates.add("/com.bluexml.side.View.generator.alfresco34d/com/bluexml/side/view/generator/alfresco34d/templates/doclist_ftl.mt");
		
		return templates;
	}

}
