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


if(!dojo._hasResource["dojo.fx.Toggler"]){
dojo._hasResource["dojo.fx.Toggler"]=true;
dojo.provide("dojo.fx.Toggler");
dojo.declare("dojo.fx.Toggler",null,{node:null,showFunc:dojo.fadeIn,hideFunc:dojo.fadeOut,showDuration:200,hideDuration:200,constructor:function(_1){
var _2=this;
dojo.mixin(_2,_1);
_2.node=_1.node;
_2._showArgs=dojo.mixin({},_1);
_2._showArgs.node=_2.node;
_2._showArgs.duration=_2.showDuration;
_2.showAnim=_2.showFunc(_2._showArgs);
_2._hideArgs=dojo.mixin({},_1);
_2._hideArgs.node=_2.node;
_2._hideArgs.duration=_2.hideDuration;
_2.hideAnim=_2.hideFunc(_2._hideArgs);
dojo.connect(_2.showAnim,"beforeBegin",dojo.hitch(_2.hideAnim,"stop",true));
dojo.connect(_2.hideAnim,"beforeBegin",dojo.hitch(_2.showAnim,"stop",true));
},show:function(_3){
return this.showAnim.play(_3||0);
},hide:function(_4){
return this.hideAnim.play(_4||0);
}});
}
