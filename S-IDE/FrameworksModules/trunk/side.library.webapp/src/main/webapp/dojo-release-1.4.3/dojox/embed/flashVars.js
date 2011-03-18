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


if(!dojo._hasResource["dojox.embed.flashVars"]){
dojo._hasResource["dojox.embed.flashVars"]=true;
dojo.provide("dojox.embed.flashVars");
dojo.mixin(dojox.embed.flashVars,{serialize:function(n,o){
var _1=function(_2){
if(typeof _2=="string"){
_2=_2.replace(/;/g,"_sc_");
_2=_2.replace(/\./g,"_pr_");
_2=_2.replace(/\:/g,"_cl_");
}
return _2;
};
var df=dojox.embed.flashVars.serialize;
var _3="";
if(dojo.isArray(o)){
for(var i=0;i<o.length;i++){
_3+=df(n+"."+i,_1(o[i]))+";";
}
return _3.replace(/;{2,}/g,";");
}else{
if(dojo.isObject(o)){
for(var nm in o){
_3+=df(n+"."+nm,_1(o[nm]))+";";
}
return _3.replace(/;{2,}/g,";");
}
}
return n+":"+o;
}});
}
