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


if(!dojo._hasResource["dojox.form.BusyButton"]){
dojo._hasResource["dojox.form.BusyButton"]=true;
dojo.provide("dojox.form.BusyButton");
dojo.require("dijit.form.Button");
dojo.requireLocalization("dijit","loading",null,"ROOT,ar,ca,cs,da,de,el,es,fi,fr,he,hu,it,ja,ko,nb,nl,pl,pt,pt-pt,ru,sk,sl,sv,th,tr,zh,zh-tw");
dojo.declare("dojox.form._BusyButtonMixin",null,{isBusy:false,busyLabel:"",timeout:null,useIcon:true,postMixInProperties:function(){
this.inherited(arguments);
if(!this.busyLabel){
this.busyLabel=dojo.i18n.getLocalization("dijit","loading",this.lang).loadingState;
}
},postCreate:function(){
this.inherited(arguments);
this._label=this.containerNode.innerHTML;
this._initTimeout=this.timeout;
if(this.isBusy){
this.makeBusy();
}
},makeBusy:function(){
this.isBusy=true;
this.attr("disabled",true);
this.setLabel(this.busyLabel,this.timeout);
},cancel:function(){
this.attr("disabled",false);
this.isBusy=false;
this.setLabel(this._label);
if(this._timeout){
clearTimeout(this._timeout);
}
this.timeout=this._initTimeout;
},resetTimeout:function(_1){
if(this._timeout){
clearTimeout(this._timeout);
}
if(_1){
this._timeout=setTimeout(dojo.hitch(this,function(){
this.cancel();
}),_1);
}else{
if(_1==undefined||_1===0){
this.cancel();
}
}
},setLabel:function(_2,_3){
this.label=_2;
while(this.containerNode.firstChild){
this.containerNode.removeChild(this.containerNode.firstChild);
}
this.containerNode.innerHTML=this.label;
if(this.showLabel==false&&!(dojo.attr(this.domNode,"title"))){
this.titleNode.title=dojo.trim(this.containerNode.innerText||this.containerNode.textContent||"");
}
if(_3){
this.resetTimeout(_3);
}else{
this.timeout=null;
}
if(this.useIcon&&this.isBusy){
var _4=new Image();
_4.src=this._blankGif;
dojo.attr(_4,"id",this.id+"_icon");
dojo.addClass(_4,"dojoxBusyButtonIcon");
this.containerNode.appendChild(_4);
}
},_clicked:function(e){
if(!this.isBusy){
this.makeBusy();
}
}});
dojo.declare("dojox.form.BusyButton",[dijit.form.Button,dojox.form._BusyButtonMixin],{});
dojo.declare("dojox.form.BusyComboButton",[dijit.form.ComboButton,dojox.form._BusyButtonMixin],{});
dojo.declare("dojox.form.BusyDropDownButton",[dijit.form.DropDownButton,dojox.form._BusyButtonMixin],{});
}
