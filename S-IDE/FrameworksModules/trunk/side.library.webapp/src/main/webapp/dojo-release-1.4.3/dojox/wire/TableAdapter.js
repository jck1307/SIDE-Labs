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


if(!dojo._hasResource["dojox.wire.TableAdapter"]){
dojo._hasResource["dojox.wire.TableAdapter"]=true;
dojo.provide("dojox.wire.TableAdapter");
dojo.require("dojox.wire.CompositeWire");
dojo.declare("dojox.wire.TableAdapter",dojox.wire.CompositeWire,{_wireClass:"dojox.wire.TableAdapter",constructor:function(_1){
this._initializeChildren(this.columns);
},_getValue:function(_2){
if(!_2||!this.columns){
return _2;
}
var _3=_2;
if(!dojo.isArray(_3)){
_3=[_3];
}
var _4=[];
for(var i in _3){
var _5=this._getRow(_3[i]);
_4.push(_5);
}
return _4;
},_setValue:function(_6,_7){
throw new Error("Unsupported API: "+this._wireClass+"._setValue");
},_getRow:function(_8){
var _9=(dojo.isArray(this.columns)?[]:{});
for(var c in this.columns){
_9[c]=this.columns[c].getValue(_8);
}
return _9;
}});
}
