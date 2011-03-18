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


if(!dojo._hasResource["dojo.cookie"]){
dojo._hasResource["dojo.cookie"]=true;
dojo.provide("dojo.cookie");
dojo.require("dojo.regexp");
dojo.cookie=function(_1,_2,_3){
var c=document.cookie;
if(arguments.length==1){
var _4=c.match(new RegExp("(?:^|; )"+dojo.regexp.escapeString(_1)+"=([^;]*)"));
return _4?decodeURIComponent(_4[1]):undefined;
}else{
_3=_3||{};
var _5=_3.expires;
if(typeof _5=="number"){
var d=new Date();
d.setTime(d.getTime()+_5*24*60*60*1000);
_5=_3.expires=d;
}
if(_5&&_5.toUTCString){
_3.expires=_5.toUTCString();
}
_2=encodeURIComponent(_2);
var _6=_1+"="+_2,_7;
for(_7 in _3){
_6+="; "+_7;
var _8=_3[_7];
if(_8!==true){
_6+="="+_8;
}
}
document.cookie=_6;
}
};
dojo.cookie.isSupported=function(){
if(!("cookieEnabled" in navigator)){
this("__djCookieTest__","CookiesAllowed");
navigator.cookieEnabled=this("__djCookieTest__")=="CookiesAllowed";
if(navigator.cookieEnabled){
this("__djCookieTest__","",{expires:-1});
}
}
return navigator.cookieEnabled;
};
}
