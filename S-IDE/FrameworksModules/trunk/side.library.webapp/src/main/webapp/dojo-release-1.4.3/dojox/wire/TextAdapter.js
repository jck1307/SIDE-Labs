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


if(!dojo._hasResource["dojox.wire.TextAdapter"]){
dojo._hasResource["dojox.wire.TextAdapter"]=true;
dojo.provide("dojox.wire.TextAdapter");
dojo.require("dojox.wire.CompositeWire");
dojo.declare("dojox.wire.TextAdapter",dojox.wire.CompositeWire,{_wireClass:"dojox.wire.TextAdapter",constructor:function(_1){
this._initializeChildren(this.segments);
if(!this.delimiter){
this.delimiter="";
}
},_getValue:function(_2){
if(!_2||!this.segments){
return _2;
}
var _3="";
for(var i in this.segments){
var _4=this.segments[i].getValue(_2);
_3=this._addSegment(_3,_4);
}
return _3;
},_setValue:function(_5,_6){
throw new Error("Unsupported API: "+this._wireClass+"._setValue");
},_addSegment:function(_7,_8){
if(!_8){
return _7;
}else{
if(!_7){
return _8;
}else{
return _7+this.delimiter+_8;
}
}
}});
}
