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


if(!dojo._hasResource["dojox.encoding.base64"]){
dojo._hasResource["dojox.encoding.base64"]=true;
dojo.provide("dojox.encoding.base64");
(function(){
var p="=";
var _1="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
var _2=dojox.encoding;
_2.base64.encode=function(ba){
var s=[],l=ba.length;
var rm=l%3;
var x=l-rm;
for(var i=0;i<x;){
var t=ba[i++]<<16|ba[i++]<<8|ba[i++];
s.push(_1.charAt((t>>>18)&63));
s.push(_1.charAt((t>>>12)&63));
s.push(_1.charAt((t>>>6)&63));
s.push(_1.charAt(t&63));
}
switch(rm){
case 2:
var t=ba[i++]<<16|ba[i++]<<8;
s.push(_1.charAt((t>>>18)&63));
s.push(_1.charAt((t>>>12)&63));
s.push(_1.charAt((t>>>6)&63));
s.push(p);
break;
case 1:
var t=ba[i++]<<16;
s.push(_1.charAt((t>>>18)&63));
s.push(_1.charAt((t>>>12)&63));
s.push(p);
s.push(p);
break;
}
return s.join("");
};
_2.base64.decode=function(_3){
var s=_3.split(""),_4=[];
var l=s.length;
while(s[--l]==p){
}
for(var i=0;i<l;){
var t=_1.indexOf(s[i++])<<18;
if(i<=l){
t|=_1.indexOf(s[i++])<<12;
}
if(i<=l){
t|=_1.indexOf(s[i++])<<6;
}
if(i<=l){
t|=_1.indexOf(s[i++]);
}
_4.push((t>>>16)&255);
_4.push((t>>>8)&255);
_4.push(t&255);
}
while(_4[_4.length-1]==0){
_4.pop();
}
return _4;
};
})();
}
