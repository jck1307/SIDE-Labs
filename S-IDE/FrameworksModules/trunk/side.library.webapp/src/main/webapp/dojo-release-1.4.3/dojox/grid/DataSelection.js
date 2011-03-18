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


if(!dojo._hasResource["dojox.grid.DataSelection"]){
dojo._hasResource["dojox.grid.DataSelection"]=true;
dojo.provide("dojox.grid.DataSelection");
dojo.require("dojox.grid.Selection");
dojo.declare("dojox.grid.DataSelection",dojox.grid.Selection,{getFirstSelected:function(){
var _1=dojox.grid.Selection.prototype.getFirstSelected.call(this);
if(_1==-1){
return null;
}
return this.grid.getItem(_1);
},getNextSelected:function(_2){
var _3=this.grid.getItemIndex(_2);
var _4=dojox.grid.Selection.prototype.getNextSelected.call(this,_3);
if(_4==-1){
return null;
}
return this.grid.getItem(_4);
},getSelected:function(){
var _5=[];
for(var i=0,l=this.selected.length;i<l;i++){
if(this.selected[i]){
_5.push(this.grid.getItem(i));
}
}
return _5;
},addToSelection:function(_6){
if(this.mode=="none"){
return;
}
var _7=null;
if(typeof _6=="number"||typeof _6=="string"){
_7=_6;
}else{
_7=this.grid.getItemIndex(_6);
}
dojox.grid.Selection.prototype.addToSelection.call(this,_7);
},deselect:function(_8){
if(this.mode=="none"){
return;
}
var _9=null;
if(typeof _8=="number"||typeof _8=="string"){
_9=_8;
}else{
_9=this.grid.getItemIndex(_8);
}
dojox.grid.Selection.prototype.deselect.call(this,_9);
},deselectAll:function(_a){
var _b=null;
if(_a||typeof _a=="number"){
if(typeof _a=="number"||typeof _a=="string"){
_b=_a;
}else{
_b=this.grid.getItemIndex(_a);
}
dojox.grid.Selection.prototype.deselectAll.call(this,_b);
}else{
this.inherited(arguments);
}
}});
}
