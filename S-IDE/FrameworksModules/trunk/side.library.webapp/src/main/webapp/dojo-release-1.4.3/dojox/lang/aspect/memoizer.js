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


if(!dojo._hasResource["dojox.lang.aspect.memoizer"]){
dojo._hasResource["dojox.lang.aspect.memoizer"]=true;
dojo.provide("dojox.lang.aspect.memoizer");
(function(){
var _1=dojox.lang.aspect;
var _2={around:function(_3){
var _4=_1.getContext(),_5=_4.joinPoint,_6=_4.instance,t,u,_7;
if((t=_6.__memoizerCache)&&(t=t[_5.targetName])&&(_3 in t)){
return t[_3];
}
var _7=_1.proceed.apply(null,arguments);
if(!(t=_6.__memoizerCache)){
t=_6.__memoizerCache={};
}
if(!(u=t[_5.targetName])){
u=t[_5.targetName]={};
}
return u[_3]=_7;
}};
var _8=function(_9){
return {around:function(){
var _a=_1.getContext(),_b=_a.joinPoint,_c=_a.instance,t,u,_d,_e=_9.apply(_c,arguments);
if((t=_c.__memoizerCache)&&(t=t[_b.targetName])&&(_e in t)){
return t[_e];
}
var _d=_1.proceed.apply(null,arguments);
if(!(t=_c.__memoizerCache)){
t=_c.__memoizerCache={};
}
if(!(u=t[_b.targetName])){
u=t[_b.targetName]={};
}
return u[_e]=_d;
}};
};
_1.memoizer=function(_f){
return arguments.length==0?_2:_8(_f);
};
})();
}
