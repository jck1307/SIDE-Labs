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


if(!dojo._hasResource["dojox.layout.DragPane"]){
dojo._hasResource["dojox.layout.DragPane"]=true;
dojo.provide("dojox.layout.DragPane");
dojo.require("dijit._Widget");
dojo.declare("dojox.layout.DragPane",dijit._Widget,{invert:true,postCreate:function(){
this.inherited(arguments);
this.connect(this.domNode,"onmousedown","_down");
this.connect(this.domNode,"onmouseup","_up");
},_down:function(e){
var t=this.domNode;
dojo.style(t,"cursor","move");
this._x=e.pageX;
this._y=e.pageY;
if((this._x<t.offsetLeft+t.clientWidth)&&(this._y<t.offsetTop+t.clientHeight)){
dojo.setSelectable(t,false);
this._mover=this.connect(t,"onmousemove","_move");
}
},_up:function(e){
dojo.setSelectable(this.domNode,true);
dojo.style(this.domNode,"cursor","pointer");
this.disconnect(this._mover);
},_move:function(e){
var _1=this.invert?1:-1;
this.domNode.scrollTop+=(this._y-e.pageY)*_1;
this.domNode.scrollLeft+=(this._x-e.pageX)*_1;
this._x=e.pageX;
this._y=e.pageY;
}});
}
