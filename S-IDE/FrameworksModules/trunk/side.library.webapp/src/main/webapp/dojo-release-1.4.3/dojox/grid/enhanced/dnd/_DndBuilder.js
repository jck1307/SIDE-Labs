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


if(!dojo._hasResource["dojox.grid.enhanced.dnd._DndBuilder"]){
dojo._hasResource["dojox.grid.enhanced.dnd._DndBuilder"]=true;
dojo.provide("dojox.grid.enhanced.dnd._DndBuilder");
dojo.declare("dojox.grid.enhanced.dnd._DndBuilder",null,{domouseup:function(e){
if(this.grid.select.isInSelectingMode("col")){
this.grid.nestedSorting?this.grid.focus.focusSelectColEndingHeader(e):this.grid.focus.focusHeaderNode(e.cellIndex);
}
if(e.cellNode){
this.grid.onMouseUp(e);
}
this.grid.onMouseUpRow(e);
}});
dojo.declare("dojox.grid.enhanced.dnd._DndHeaderBuilder",null,{domouseup:function(e){
if(this.grid.select.isInSelectingMode("col")){
this.grid.nestedSorting?this.grid.focus.focusSelectColEndingHeader(e):this.grid.focus.focusHeaderNode(e.cellIndex);
}
this.grid.onMouseUp(e);
}});
}
