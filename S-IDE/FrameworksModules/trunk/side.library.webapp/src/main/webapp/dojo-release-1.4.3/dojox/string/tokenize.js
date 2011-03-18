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


if(!dojo._hasResource["dojox.string.tokenize"]){
dojo._hasResource["dojox.string.tokenize"]=true;
dojo.provide("dojox.string.tokenize");
dojox.string.tokenize=function(_1,re,_2,_3){
var _4=[];
var _5,_6,_7=0;
while(_5=re.exec(_1)){
_6=_1.slice(_7,re.lastIndex-_5[0].length);
if(_6.length){
_4.push(_6);
}
if(_2){
if(dojo.isOpera){
var _8=_5.slice(0);
while(_8.length<_5.length){
_8.push(null);
}
_5=_8;
}
var _9=_2.apply(_3,_5.slice(1).concat(_4.length));
if(typeof _9!="undefined"){
_4.push(_9);
}
}
_7=re.lastIndex;
}
_6=_1.slice(_7);
if(_6.length){
_4.push(_6);
}
return _4;
};
}
