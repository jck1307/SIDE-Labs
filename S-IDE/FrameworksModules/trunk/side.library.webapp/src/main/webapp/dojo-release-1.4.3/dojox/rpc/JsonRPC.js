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


if(!dojo._hasResource["dojox.rpc.JsonRPC"]){
dojo._hasResource["dojox.rpc.JsonRPC"]=true;
dojo.provide("dojox.rpc.JsonRPC");
dojo.require("dojox.rpc.Service");
(function(){
function _1(_2){
return {serialize:function(_3,_4,_5,_6){
var d={id:this._requestId++,method:_4.name,params:_5};
if(_2){
d.jsonrpc=_2;
}
return {data:dojo.toJson(d),handleAs:"json",contentType:"application/json",transport:"POST"};
},deserialize:function(_7){
if("Error"==_7.name){
_7=dojo.fromJson(_7.responseText);
}
if(_7.error){
var e=new Error(_7.error.message||_7.error);
e._rpcErrorObject=_7.error;
return e;
}
return _7.result;
}};
};
dojox.rpc.envelopeRegistry.register("JSON-RPC-1.0",function(_8){
return _8=="JSON-RPC-1.0";
},dojo.mixin({namedParams:false},_1()));
dojox.rpc.envelopeRegistry.register("JSON-RPC-2.0",function(_9){
return _9=="JSON-RPC-2.0";
},_1("2.0"));
})();
}
