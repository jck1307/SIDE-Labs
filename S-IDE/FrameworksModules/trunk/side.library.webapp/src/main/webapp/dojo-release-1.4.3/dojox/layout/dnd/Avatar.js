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


if(!dojo._hasResource["dojox.layout.dnd.Avatar"]){
dojo._hasResource["dojox.layout.dnd.Avatar"]=true;
dojo.provide("dojox.layout.dnd.Avatar");
dojo.require("dojo.dnd.Avatar");
dojo.require("dojo.dnd.common");
dojo.declare("dojox.layout.dnd.Avatar",dojo.dnd.Avatar,{constructor:function(_1,_2){
this.opacity=_2||0.9;
},construct:function(){
var _3=this.manager.source,_4=_3.creator?_3._normalizedCreator(_3.getItem(this.manager.nodes[0].id).data,"avatar").node:this.manager.nodes[0].cloneNode(true);
dojo.addClass(_4,"dojoDndAvatar");
_4.id=dojo.dnd.getUniqueId();
_4.style.position="absolute";
_4.style.zIndex=1999;
_4.style.margin="0px";
_4.style.width=dojo.marginBox(_3.node).w+"px";
dojo.style(_4,"opacity",this.opacity);
this.node=_4;
},update:function(){
dojo.toggleClass(this.node,"dojoDndAvatarCanDrop",this.manager.canDropFlag);
},_generateText:function(){
}});
}
