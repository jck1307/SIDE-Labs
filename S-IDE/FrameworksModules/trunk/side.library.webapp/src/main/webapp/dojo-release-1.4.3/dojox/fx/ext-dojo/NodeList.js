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


if(!dojo._hasResource["dojox.fx.ext-dojo.NodeList"]){
dojo._hasResource["dojox.fx.ext-dojo.NodeList"]=true;
dojo.provide("dojox.fx.ext-dojo.NodeList");
dojo.experimental("dojox.fx.ext-dojo.NodeList");
dojo.require("dojo.NodeList-fx");
dojo.require("dojox.fx");
dojo.extend(dojo.NodeList,{sizeTo:function(_1){
return this._anim(dojox.fx,"sizeTo",_1);
},slideBy:function(_2){
return this._anim(dojox.fx,"slideBy",_2);
},highlight:function(_3){
return this._anim(dojox.fx,"highlight",_3);
},fadeTo:function(_4){
return this._anim(dojo,"_fade",_4);
},wipeTo:function(_5){
return this._anim(dojox.fx,"wipeTo",_5);
}});
}
