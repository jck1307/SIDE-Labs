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


if(!dojo._hasResource["dojox.rpc.ProxiedPath"]){
dojo._hasResource["dojox.rpc.ProxiedPath"]=true;
dojo.provide("dojox.rpc.ProxiedPath");
dojo.require("dojox.rpc.Service");
dojox.rpc.envelopeRegistry.register("PROXIED-PATH",function(_1){
return _1=="PROXIED-PATH";
},{serialize:function(_2,_3,_4){
var i;
var _5=dojox.rpc.getTarget(_2,_3);
if(dojo.isArray(_4)){
for(i=0;i<_4.length;i++){
_5+="/"+(_4[i]==null?"":_4[i]);
}
}else{
for(i in _4){
_5+="/"+i+"/"+_4[i];
}
}
return {data:"",target:(_3.proxyUrl||_2.proxyUrl)+"?url="+encodeURIComponent(_5)};
},deserialize:function(_6){
return _6;
}});
}
