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


if(!dojo._hasResource["dojox.grid.enhanced.dnd._DndMover"]){
dojo._hasResource["dojox.grid.enhanced.dnd._DndMover"]=true;
dojo.provide("dojox.grid.enhanced.dnd._DndMover");
dojo.require("dojo.dnd.move");
dojo.declare("dojox.grid.enhanced.dnd._DndMover",dojo.dnd.Mover,{onMouseMove:function(e){
dojo.dnd.autoScroll(e);
var m=this.marginBox;
this.host.onMove(this,{l:m.l+e.pageX,t:m.t+e.pageY},{x:e.pageX,y:e.pageY});
dojo.stopEvent(e);
}});
dojo.declare("dojox.grid.enhanced.dnd._DndBoxConstrainedMoveable",dojo.dnd.move.boxConstrainedMoveable,{movingType:"row",constructor:function(_1,_2){
if(!_2||!_2.movingType){
return;
}
this.movingType=_2.movingType;
},onFirstMove:function(_3){
this.inherited(arguments);
if(this.within){
var c=this.constraintBox,mb=dojo.marginBox(_3.node);
if(this.movingType=="row"){
c.r+=mb.w;
}else{
if(this.movingType=="col"){
c.b+=mb.h;
}
}
}
}});
}
