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


if(!dojo._hasResource["dojox.lang.aspect.tracer"]){
dojo._hasResource["dojox.lang.aspect.tracer"]=true;
dojo.provide("dojox.lang.aspect.tracer");
(function(){
var _1=dojox.lang.aspect;
var _2=function(_3){
this.method=_3?"group":"log";
if(_3){
this.after=this._after;
}
};
dojo.extend(_2,{before:function(){
var _4=_1.getContext(),_5=_4.joinPoint,_6=Array.prototype.join.call(arguments,", ");
console[this.method](_4.instance,"=>",_5.targetName+"("+_6+")");
},afterReturning:function(_7){
var _8=_1.getContext().joinPoint;
if(typeof _7!="undefined"){
}else{
}
},afterThrowing:function(_9){
},_after:function(_a){
}});
_1.tracer=function(_b){
return new _2(_b);
};
})();
}
