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


if(!dojo._hasResource["dojox.uuid.generateRandomUuid"]){
dojo._hasResource["dojox.uuid.generateRandomUuid"]=true;
dojo.provide("dojox.uuid.generateRandomUuid");
dojox.uuid.generateRandomUuid=function(){
var _1=16;
function _2(){
var _3=Math.floor((Math.random()%1)*Math.pow(2,32));
var _4=_3.toString(_1);
while(_4.length<8){
_4="0"+_4;
}
return _4;
};
var _5="-";
var _6="4";
var _7="8";
var a=_2();
var b=_2();
b=b.substring(0,4)+_5+_6+b.substring(5,8);
var c=_2();
c=_7+c.substring(1,4)+_5+c.substring(4,8);
var d=_2();
var _8=a+_5+b+_5+c+d;
_8=_8.toLowerCase();
return _8;
};
}
