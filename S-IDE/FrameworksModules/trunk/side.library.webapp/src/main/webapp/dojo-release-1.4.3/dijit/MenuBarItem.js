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


/*
	Copyright (c) 2004-2009, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/


if(!dojo._hasResource["dijit.MenuBarItem"]){
dojo._hasResource["dijit.MenuBarItem"]=true;
dojo.provide("dijit.MenuBarItem");
dojo.require("dijit.MenuItem");
dojo.declare("dijit._MenuBarItemMixin",null,{templateString:dojo.cache("dijit","templates/MenuBarItem.html","<div class=\"dijitReset dijitInline dijitMenuItem dijitMenuItemLabel\" dojoAttachPoint=\"focusNode\" waiRole=\"menuitem\" tabIndex=\"-1\"'\n\t\tdojoAttachEvent=\"onmouseenter:_onHover,onmouseleave:_onUnhover,ondijitclick:_onClick\">\n\t<span dojoAttachPoint=\"containerNode\"></span>\n</div>\n"),attributeMap:dojo.delegate(dijit._Widget.prototype.attributeMap,{label:{node:"containerNode",type:"innerHTML"}})});
dojo.declare("dijit.MenuBarItem",[dijit.MenuItem,dijit._MenuBarItemMixin],{});
}
