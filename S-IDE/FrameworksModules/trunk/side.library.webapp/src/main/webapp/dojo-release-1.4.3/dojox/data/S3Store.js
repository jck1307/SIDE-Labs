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


if(!dojo._hasResource["dojox.data.S3Store"]){
dojo._hasResource["dojox.data.S3Store"]=true;
dojo.provide("dojox.data.S3Store");
dojo.require("dojox.rpc.ProxiedPath");
dojo.require("dojox.data.JsonRestStore");
dojo.declare("dojox.data.S3Store",dojox.data.JsonRestStore,{_processResults:function(_1){
var _2=_1.getElementsByTagName("Key");
var _3=[];
var _4=this;
for(var i=0;i<_2.length;i++){
var _5=_2[i];
var _6={_loadObject:(function(_7,_8){
return function(_9){
delete this._loadObject;
_4.service(_7).addCallback(_9);
};
})(_5.firstChild.nodeValue,_6)};
_3.push(_6);
}
return {totalCount:_3.length,items:_3};
}});
}
