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


if(!dojo._hasResource["dijit.TooltipDialog"]){
dojo._hasResource["dijit.TooltipDialog"]=true;
dojo.provide("dijit.TooltipDialog");
dojo.require("dijit.layout.ContentPane");
dojo.require("dijit._Templated");
dojo.require("dijit.form._FormMixin");
dojo.require("dijit._DialogMixin");
dojo.declare("dijit.TooltipDialog",[dijit.layout.ContentPane,dijit._Templated,dijit.form._FormMixin,dijit._DialogMixin],{title:"",doLayout:false,autofocus:true,baseClass:"dijitTooltipDialog",_firstFocusItem:null,_lastFocusItem:null,templateString:dojo.cache("dijit","templates/TooltipDialog.html","<div waiRole=\"presentation\">\n\t<div class=\"dijitTooltipContainer\" waiRole=\"presentation\">\n\t\t<div class =\"dijitTooltipContents dijitTooltipFocusNode\" dojoAttachPoint=\"containerNode\" tabindex=\"-1\" waiRole=\"dialog\"></div>\n\t</div>\n\t<div class=\"dijitTooltipConnector\" waiRole=\"presentation\"></div>\n</div>\n"),postCreate:function(){
this.inherited(arguments);
this.connect(this.containerNode,"onkeypress","_onKey");
this.containerNode.title=this.title;
},orient:function(_1,_2,_3){
var c=this._currentOrientClass;
if(c){
dojo.removeClass(this.domNode,c);
}
c="dijitTooltipAB"+(_3.charAt(1)=="L"?"Left":"Right")+" dijitTooltip"+(_3.charAt(0)=="T"?"Below":"Above");
dojo.addClass(this.domNode,c);
this._currentOrientClass=c;
},onOpen:function(_4){
this.orient(this.domNode,_4.aroundCorner,_4.corner);
this._onShow();
if(this.autofocus){
this._getFocusItems(this.containerNode);
dijit.focus(this._firstFocusItem);
}
},onClose:function(){
this.onHide();
},_onKey:function(_5){
var _6=_5.target;
var dk=dojo.keys;
if(_5.charOrCode===dk.TAB){
this._getFocusItems(this.containerNode);
}
var _7=(this._firstFocusItem==this._lastFocusItem);
if(_5.charOrCode==dk.ESCAPE){
setTimeout(dojo.hitch(this,"onCancel"),0);
dojo.stopEvent(_5);
}else{
if(_6==this._firstFocusItem&&_5.shiftKey&&_5.charOrCode===dk.TAB){
if(!_7){
dijit.focus(this._lastFocusItem);
}
dojo.stopEvent(_5);
}else{
if(_6==this._lastFocusItem&&_5.charOrCode===dk.TAB&&!_5.shiftKey){
if(!_7){
dijit.focus(this._firstFocusItem);
}
dojo.stopEvent(_5);
}else{
if(_5.charOrCode===dk.TAB){
_5.stopPropagation();
}
}
}
}
}});
}
