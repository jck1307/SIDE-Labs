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


if(!dojo._hasResource["dijit.tree._dndContainer"]){
dojo._hasResource["dijit.tree._dndContainer"]=true;
dojo.provide("dijit.tree._dndContainer");
dojo.require("dojo.dnd.common");
dojo.require("dojo.dnd.Container");
dojo.declare("dijit.tree._dndContainer",null,{constructor:function(_1,_2){
this.tree=_1;
this.node=_1.domNode;
dojo.mixin(this,_2);
this.map={};
this.current=null;
this.containerState="";
dojo.addClass(this.node,"dojoDndContainer");
this.events=[dojo.connect(this.node,"onmouseenter",this,"onOverEvent"),dojo.connect(this.node,"onmouseleave",this,"onOutEvent"),dojo.connect(this.tree,"_onNodeMouseEnter",this,"onMouseOver"),dojo.connect(this.tree,"_onNodeMouseLeave",this,"onMouseOut"),dojo.connect(this.node,"ondragstart",dojo,"stopEvent"),dojo.connect(this.node,"onselectstart",dojo,"stopEvent")];
},getItem:function(_3){
var _4=this.selection[_3],_5={data:dijit.getEnclosingWidget(_4),type:["treeNode"]};
return _5;
},destroy:function(){
dojo.forEach(this.events,dojo.disconnect);
this.node=this.parent=null;
},onMouseOver:function(_6,_7){
this.current=_6.rowNode;
this.currentWidget=_6;
},onMouseOut:function(_8,_9){
this.current=null;
this.currentWidget=null;
},_changeState:function(_a,_b){
var _c="dojoDnd"+_a;
var _d=_a.toLowerCase()+"State";
dojo.removeClass(this.node,_c+this[_d]);
dojo.addClass(this.node,_c+_b);
this[_d]=_b;
},_addItemClass:function(_e,_f){
dojo.addClass(_e,"dojoDndItem"+_f);
},_removeItemClass:function(_10,_11){
dojo.removeClass(_10,"dojoDndItem"+_11);
},onOverEvent:function(){
this._changeState("Container","Over");
},onOutEvent:function(){
this._changeState("Container","");
}});
}
