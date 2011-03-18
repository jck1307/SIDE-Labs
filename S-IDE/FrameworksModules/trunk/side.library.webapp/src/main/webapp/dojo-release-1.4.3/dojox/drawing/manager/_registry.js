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


if(!dojo._hasResource["dojox.drawing.manager._registry"]){
dojo._hasResource["dojox.drawing.manager._registry"]=true;
dojo.provide("dojox.drawing.manager._registry");
(function(){
var _1={tool:{},stencil:{},drawing:{},plugin:{}};
dojox.drawing.register=function(_2,_3){
if(_3=="drawing"){
_1.drawing[_2.id]=_2;
}else{
if(_3=="tool"){
_1.tool[_2.name]=_2;
}else{
if(_3=="stencil"){
_1.stencil[_2.name]=_2;
}else{
if(_3=="plugin"){
_1.plugin[_2.name]=_2;
}
}
}
}
};
dojox.drawing.getRegistered=function(_4,id){
return id?_1[_4][id]:_1[_4];
};
})();
}
