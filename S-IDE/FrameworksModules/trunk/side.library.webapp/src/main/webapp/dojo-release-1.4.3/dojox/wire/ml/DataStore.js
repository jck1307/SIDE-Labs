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


if(!dojo._hasResource["dojox.wire.ml.DataStore"]){
dojo._hasResource["dojox.wire.ml.DataStore"]=true;
dojo.provide("dojox.wire.ml.DataStore");
dojo.require("dijit._Widget");
dojo.require("dojox.wire._base");
dojo.declare("dojox.wire.ml.DataStore",dijit._Widget,{storeClass:"",postCreate:function(){
this.store=this._createStore();
},_createStore:function(){
if(!this.storeClass){
return null;
}
var _1=dojox.wire._getClass(this.storeClass);
if(!_1){
return null;
}
var _2={};
var _3=this.domNode.attributes;
for(var i=0;i<_3.length;i++){
var a=_3.item(i);
if(a.specified&&!this[a.nodeName]){
_2[a.nodeName]=a.nodeValue;
}
}
return new _1(_2);
},getFeatures:function(){
return this.store.getFeatures();
},fetch:function(_4){
return this.store.fetch(_4);
},save:function(_5){
this.store.save(_5);
},newItem:function(_6){
return this.store.newItem(_6);
},deleteItem:function(_7){
return this.store.deleteItem(_7);
},revert:function(){
return this.store.revert();
}});
}
