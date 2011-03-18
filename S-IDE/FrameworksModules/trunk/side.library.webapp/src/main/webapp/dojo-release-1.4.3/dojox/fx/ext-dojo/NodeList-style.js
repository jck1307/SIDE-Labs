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


if(!dojo._hasResource["dojox.fx.ext-dojo.NodeList-style"]){
dojo._hasResource["dojox.fx.ext-dojo.NodeList-style"]=true;
dojo.provide("dojox.fx.ext-dojo.NodeList-style");
dojo.experimental("dojox.fx.ext-dojo.NodeList-style");
dojo.require("dojo.NodeList-fx");
dojo.require("dojox.fx.style");
dojo.extend(dojo.NodeList,{addClassFx:function(_1,_2){
return dojo.fx.combine(this.map(function(n){
return dojox.fx.addClass(n,_1,_2);
}));
},removeClassFx:function(_3,_4){
return dojo.fx.combine(this.map(function(n){
return dojox.fx.removeClass(n,_3,_4);
}));
},toggleClassFx:function(_5,_6,_7){
return dojo.fx.combine(this.map(function(n){
return dojox.fx.toggleClass(n,_5,_6,_7);
}));
}});
}
