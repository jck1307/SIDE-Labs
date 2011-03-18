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


if(!dojo._hasResource["dojox.date.relative"]){
dojo._hasResource["dojox.date.relative"]=true;
dojo.provide("dojox.date.relative");
dojo.require("dojo.date");
dojo.require("dojo.date.locale");
(function(d){
var _1=1000*60*60*24;
var _2=6*_1;
var _3=d.delegate;
var _4=d.date.locale;
var _5=_4._getGregorianBundle;
var _6=_4.format;
function _7(_8){
_8=dojo.clone(_8);
_8.setHours(0);
_8.setMinutes(0);
_8.setSeconds(0);
_8.setMilliseconds(0);
return _8;
};
dojox.date.relative.format=function(_9,_a){
_a=_a||{};
var _b=_7(_a.relativeDate||new Date());
var _c=_b.getTime()-_7(_9).getTime();
var _d={locale:_a.locale};
if(_c===0){
return _6(_9,_3(_d,{selector:"time"}));
}else{
if(_c<=_2&&_c>0&&_a.weekCheck!==false){
return _6(_9,_3(_d,{selector:"date",datePattern:"EEE"}))+" "+_6(_9,_3(_d,{selector:"time",formatLength:"short"}));
}else{
if(_9.getFullYear()==_b.getFullYear()){
var _e=_5(dojo.i18n.normalizeLocale(_a.locale));
return _6(_9,_3(_d,{selector:"date",datePattern:_e["dateFormatItem-MMMd"]}));
}else{
return _6(_9,_3(_d,{selector:"date",formatLength:"medium",locale:_a.locale}));
}
}
}
};
})(dojo);
}
