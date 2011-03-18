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


if(!dojo._hasResource["dojox.charting.axis2d.Base"]){
dojo._hasResource["dojox.charting.axis2d.Base"]=true;
dojo.provide("dojox.charting.axis2d.Base");
dojo.require("dojox.charting.Element");
dojo.declare("dojox.charting.axis2d.Base",dojox.charting.Element,{constructor:function(_1,_2){
this.vertical=_2&&_2.vertical;
},clear:function(){
return this;
},initialized:function(){
return false;
},calculate:function(_3,_4,_5){
return this;
},getScaler:function(){
return null;
},getTicks:function(){
return null;
},getOffsets:function(){
return {l:0,r:0,t:0,b:0};
},render:function(_6,_7){
return this;
}});
}
