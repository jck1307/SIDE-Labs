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


if(!dojo._hasResource["dojo.gears"]){
dojo._hasResource["dojo.gears"]=true;
dojo.provide("dojo.gears");
dojo.gears._gearsObject=function(){
var _1;
var _2;
var _3=dojo.getObject("google.gears");
if(_3){
return _3;
}
if(typeof GearsFactory!="undefined"){
_1=new GearsFactory();
}else{
if(dojo.isIE){
try{
_1=new ActiveXObject("Gears.Factory");
}
catch(e){
}
}else{
if(navigator.mimeTypes["application/x-googlegears"]){
_1=document.createElement("object");
_1.setAttribute("type","application/x-googlegears");
_1.setAttribute("width",0);
_1.setAttribute("height",0);
_1.style.display="none";
document.documentElement.appendChild(_1);
}
}
}
if(!_1){
return null;
}
dojo.setObject("google.gears.factory",_1);
return dojo.getObject("google.gears");
};
dojo.gears.available=(!!dojo.gears._gearsObject())||0;
}
