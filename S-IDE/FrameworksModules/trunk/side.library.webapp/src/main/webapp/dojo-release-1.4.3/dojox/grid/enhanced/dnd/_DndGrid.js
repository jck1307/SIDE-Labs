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


if(!dojo._hasResource["dojox.grid.enhanced.dnd._DndGrid"]){
dojo._hasResource["dojox.grid.enhanced.dnd._DndGrid"]=true;
dojo.provide("dojox.grid.enhanced.dnd._DndGrid");
dojo.require("dojox.grid.enhanced.dnd._DndEvents");
dojo.declare("dojox.grid.enhanced.dnd._DndGrid",dojox.grid.enhanced.dnd._DndEvents,{select:null,dndSelectable:true,constructor:function(_1){
this.select=_1;
},domousedown:function(e){
if(!e.cellNode){
this.onRowHeaderMouseDown(e);
}
},domouseup:function(e){
if(!e.cellNode){
this.onRowHeaderMouseUp(e);
}
}});
}
