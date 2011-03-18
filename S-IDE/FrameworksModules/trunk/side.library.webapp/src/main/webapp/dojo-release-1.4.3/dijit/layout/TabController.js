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


if(!dojo._hasResource["dijit.layout.TabController"]){
dojo._hasResource["dijit.layout.TabController"]=true;
dojo.provide("dijit.layout.TabController");
dojo.require("dijit.layout.StackController");
dojo.require("dijit.Menu");
dojo.require("dijit.MenuItem");
dojo.requireLocalization("dijit","common",null,"ROOT,ar,ca,cs,da,de,el,es,fi,fr,he,hu,it,ja,ko,nb,nl,pl,pt,pt-pt,ru,sk,sl,sv,th,tr,zh,zh-tw");
dojo.declare("dijit.layout.TabController",dijit.layout.StackController,{templateString:"<div wairole='tablist' dojoAttachEvent='onkeypress:onkeypress'></div>",tabPosition:"top",buttonWidget:"dijit.layout._TabButton",_rectifyRtlTabList:function(){
if(0>=this.tabPosition.indexOf("-h")){
return;
}
if(!this.pane2button){
return;
}
var _1=0;
for(var _2 in this.pane2button){
var ow=this.pane2button[_2].innerDiv.scrollWidth;
_1=Math.max(_1,ow);
}
for(_2 in this.pane2button){
this.pane2button[_2].innerDiv.style.width=_1+"px";
}
}});
dojo.declare("dijit.layout._TabButton",dijit.layout._StackButton,{baseClass:"dijitTab",templateString:dojo.cache("dijit.layout","templates/_TabButton.html","<div waiRole=\"presentation\" dojoAttachPoint=\"titleNode\" dojoAttachEvent='onclick:onClick,onmouseenter:_onMouse,onmouseleave:_onMouse'>\n    <div waiRole=\"presentation\" class='dijitTabInnerDiv' dojoAttachPoint='innerDiv'>\n        <div waiRole=\"presentation\" class='dijitTabContent' dojoAttachPoint='tabContent,focusNode'>\n\t        <img src=\"${_blankGif}\" alt=\"\" dojoAttachPoint='iconNode' waiRole=\"presentation\"/>\n\t        <span dojoAttachPoint='containerNode' class='tabLabel'></span>\n\t        <span class=\"closeButton\" dojoAttachPoint='closeNode'\n\t        \t\tdojoAttachEvent='onclick: onClickCloseButton, onmouseenter: _onCloseButtonEnter, onmouseleave: _onCloseButtonLeave'>\n\t        \t<img src=\"${_blankGif}\" alt=\"\" dojoAttachPoint='closeIcon' class='closeImage' waiRole=\"presentation\"/>\n\t            <span dojoAttachPoint='closeText' class='closeText'>x</span>\n\t        </span>\n        </div>\n    </div>\n</div>\n"),scrollOnFocus:false,postMixInProperties:function(){
if(!this.iconClass){
this.iconClass="dijitTabButtonIcon";
}
},postCreate:function(){
this.inherited(arguments);
dojo.setSelectable(this.containerNode,false);
if(this.iconNode.className=="dijitTabButtonIcon"){
dojo.style(this.iconNode,"width","1px");
}
},startup:function(){
this.inherited(arguments);
var n=this.domNode;
setTimeout(function(){
n.className=n.className;
},1);
},_setCloseButtonAttr:function(_3){
this.closeButton=_3;
dojo.toggleClass(this.innerDiv,"dijitClosable",_3);
this.closeNode.style.display=_3?"":"none";
if(_3){
var _4=dojo.i18n.getLocalization("dijit","common");
if(this.closeNode){
dojo.attr(this.closeNode,"title",_4.itemClose);
if(dojo.isIE<8){
dojo.attr(this.closeIcon,"title",_4.itemClose);
}
}
var _4=dojo.i18n.getLocalization("dijit","common");
this._closeMenu=new dijit.Menu({id:this.id+"_Menu",targetNodeIds:[this.domNode]});
this._closeMenu.addChild(new dijit.MenuItem({label:_4.itemClose,onClick:dojo.hitch(this,"onClickCloseButton")}));
}else{
if(this._closeMenu){
this._closeMenu.destroyRecursive();
delete this._closeMenu;
}
}
},destroy:function(){
if(this._closeMenu){
this._closeMenu.destroyRecursive();
delete this._closeMenu;
}
this.inherited(arguments);
},_onCloseButtonEnter:function(){
dojo.addClass(this.closeNode,"closeButton-hover");
},_onCloseButtonLeave:function(){
dojo.removeClass(this.closeNode,"closeButton-hover");
}});
}
