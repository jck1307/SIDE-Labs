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


if(!dojo._hasResource["dojox.lang.aspect.cflow"]){
dojo._hasResource["dojox.lang.aspect.cflow"]=true;
dojo.provide("dojox.lang.aspect.cflow");
(function(){
var _1=dojox.lang.aspect;
_1.cflow=function(_2,_3){
if(arguments.length>1&&!(_3 instanceof Array)){
_3=[_3];
}
var _4=_1.getContextStack();
for(var i=_4.length-1;i>=0;--i){
var c=_4[i];
if(_2&&c.instance!=_2){
continue;
}
if(!_3){
return true;
}
var n=c.joinPoint.targetName;
for(var j=_3.length-1;j>=0;--j){
var m=_3[j];
if(m instanceof RegExp){
if(m.test(n)){
return true;
}
}else{
if(n==m){
return true;
}
}
}
}
return false;
};
})();
}
