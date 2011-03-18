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


if(!dojo._hasResource["dojo.behavior"]){
dojo._hasResource["dojo.behavior"]=true;
dojo.provide("dojo.behavior");
dojo.behavior=new function(){
function _1(_2,_3){
if(!_2[_3]){
_2[_3]=[];
}
return _2[_3];
};
var _4=0;
function _5(_6,_7,_8){
var _9={};
for(var x in _6){
if(typeof _9[x]=="undefined"){
if(!_8){
_7(_6[x],x);
}else{
_8.call(_7,_6[x],x);
}
}
}
};
this._behaviors={};
this.add=function(_a){
var _b={};
_5(_a,this,function(_c,_d){
var _e=_1(this._behaviors,_d);
if(typeof _e["id"]!="number"){
_e.id=_4++;
}
var _f=[];
_e.push(_f);
if((dojo.isString(_c))||(dojo.isFunction(_c))){
_c={found:_c};
}
_5(_c,function(_10,_11){
_1(_f,_11).push(_10);
});
});
};
var _12=function(_13,_14,_15){
if(dojo.isString(_14)){
if(_15=="found"){
dojo.publish(_14,[_13]);
}else{
dojo.connect(_13,_15,function(){
dojo.publish(_14,arguments);
});
}
}else{
if(dojo.isFunction(_14)){
if(_15=="found"){
_14(_13);
}else{
dojo.connect(_13,_15,_14);
}
}
}
};
this.apply=function(){
_5(this._behaviors,function(_16,id){
dojo.query(id).forEach(function(_17){
var _18=0;
var bid="_dj_behavior_"+_16.id;
if(typeof _17[bid]=="number"){
_18=_17[bid];
if(_18==(_16.length)){
return;
}
}
for(var x=_18,_19;_19=_16[x];x++){
_5(_19,function(_1a,_1b){
if(dojo.isArray(_1a)){
dojo.forEach(_1a,function(_1c){
_12(_17,_1c,_1b);
});
}
});
}
_17[bid]=_16.length;
});
});
};
};
dojo.addOnLoad(dojo.behavior,"apply");
}
