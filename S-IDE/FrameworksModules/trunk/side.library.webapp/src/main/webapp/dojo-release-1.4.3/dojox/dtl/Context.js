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


if(!dojo._hasResource["dojox.dtl.Context"]){
dojo._hasResource["dojox.dtl.Context"]=true;
dojo.provide("dojox.dtl.Context");
dojo.require("dojox.dtl._base");
dojox.dtl.Context=dojo.extend(function(_1){
this._this={};
dojox.dtl._Context.call(this,_1);
},dojox.dtl._Context.prototype,{getKeys:function(){
var _2=[];
for(var _3 in this){
if(this.hasOwnProperty(_3)&&_3!="_dicts"&&_3!="_this"){
_2.push(_3);
}
}
return _2;
},extend:function(_4){
return dojo.delegate(this,_4);
},filter:function(_5){
var _6=new dojox.dtl.Context();
var _7=[];
var i,_8;
if(_5 instanceof dojox.dtl.Context){
_7=_5.getKeys();
}else{
if(typeof _5=="object"){
for(var _9 in _5){
_7.push(_9);
}
}else{
for(i=0;_8=arguments[i];i++){
if(typeof _8=="string"){
_7.push(_8);
}
}
}
}
for(i=0,_9;_9=_7[i];i++){
_6[_9]=this[_9];
}
return _6;
},setThis:function(_a){
this._this=_a;
},getThis:function(){
return this._this;
},hasKey:function(_b){
if(typeof this[_b]!="undefined"){
return true;
}
for(var i=0,_c;_c=this._dicts[i];i++){
if(typeof _c[_b]!="undefined"){
return true;
}
}
return false;
}});
}
