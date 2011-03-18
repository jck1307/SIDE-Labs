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


if(!dojo._hasResource["dojo.cache"]){
dojo._hasResource["dojo.cache"]=true;
dojo.provide("dojo.cache");
(function(){
var _1={};
dojo.cache=function(_2,_3,_4){
if(typeof _2=="string"){
var _5=dojo.moduleUrl(_2,_3);
}else{
_5=_2;
_4=_3;
}
var _6=_5.toString();
var _7=_4;
if(_4!==undefined&&!dojo.isString(_4)){
_7=("value" in _4?_4.value:undefined);
}
var _8=_4&&_4.sanitize?true:false;
if(_7||_7===null){
if(_7==null){
delete _1[_6];
}else{
_7=_1[_6]=_8?dojo.cache._sanitize(_7):_7;
}
}else{
if(!(_6 in _1)){
_7=dojo._getText(_6);
_1[_6]=_8?dojo.cache._sanitize(_7):_7;
}
_7=_1[_6];
}
return _7;
};
dojo.cache._sanitize=function(_9){
if(_9){
_9=_9.replace(/^\s*<\?xml(\s)+version=[\'\"](\d)*.(\d)*[\'\"](\s)*\?>/im,"");
var _a=_9.match(/<body[^>]*>\s*([\s\S]+)\s*<\/body>/im);
if(_a){
_9=_a[1];
}
}else{
_9="";
}
return _9;
};
})();
}
