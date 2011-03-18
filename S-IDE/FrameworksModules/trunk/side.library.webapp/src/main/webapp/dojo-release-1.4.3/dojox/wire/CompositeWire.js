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


if(!dojo._hasResource["dojox.wire.CompositeWire"]){
dojo._hasResource["dojox.wire.CompositeWire"]=true;
dojo.provide("dojox.wire.CompositeWire");
dojo.require("dojox.wire._base");
dojo.require("dojox.wire.Wire");
dojo.declare("dojox.wire.CompositeWire",dojox.wire.Wire,{_wireClass:"dojox.wire.CompositeWire",constructor:function(_1){
this._initializeChildren(this.children);
},_getValue:function(_2){
if(!_2||!this.children){
return _2;
}
var _3=(dojo.isArray(this.children)?[]:{});
for(var c in this.children){
_3[c]=this.children[c].getValue(_2);
}
return _3;
},_setValue:function(_4,_5){
if(!_4||!this.children){
return _4;
}
for(var c in this.children){
this.children[c].setValue(_5[c],_4);
}
return _4;
},_initializeChildren:function(_6){
if(!_6){
return;
}
for(var c in _6){
var _7=_6[c];
_7.parent=this;
if(!dojox.wire.isWire(_7)){
_6[c]=dojox.wire.create(_7);
}
}
}});
}
