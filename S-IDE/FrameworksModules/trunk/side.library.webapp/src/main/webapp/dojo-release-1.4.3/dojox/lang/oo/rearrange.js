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


if(!dojo._hasResource["dojox.lang.oo.rearrange"]){
dojo._hasResource["dojox.lang.oo.rearrange"]=true;
dojo.provide("dojox.lang.oo.rearrange");
(function(){
var _1=dojo._extraNames,_2=_1.length,_3=Object.prototype.toString;
dojox.lang.oo.rearrange=function(_4,_5){
var _6,_7,_8,i,t;
for(_6 in _5){
_7=_5[_6];
if(!_7||_3.call(_7)=="[object String]"){
_8=_4[_6];
if(!(_6 in empty)||empty[_6]!==_8){
if(!(delete _4[_6])){
_4[_6]=undefined;
}
if(_7){
_4[_7]=_8;
}
}
}
}
if(_2){
for(i=0;i<_2;++i){
_6=_1[i];
_7=_5[_6];
if(!_7||_3.call(_7)=="[object String]"){
_8=_4[_6];
if(!(_6 in empty)||empty[_6]!==_8){
if(!(delete _4[_6])){
_4[_6]=undefined;
}
if(_7){
_4[_7]=_8;
}
}
}
}
}
return _4;
};
})();
}
