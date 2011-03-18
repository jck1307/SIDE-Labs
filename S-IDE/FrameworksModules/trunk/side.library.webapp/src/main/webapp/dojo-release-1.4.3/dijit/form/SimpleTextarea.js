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


if(!dojo._hasResource["dijit.form.SimpleTextarea"]){
dojo._hasResource["dijit.form.SimpleTextarea"]=true;
dojo.provide("dijit.form.SimpleTextarea");
dojo.require("dijit.form.TextBox");
dojo.declare("dijit.form.SimpleTextarea",dijit.form.TextBox,{baseClass:"dijitTextArea",attributeMap:dojo.delegate(dijit.form._FormValueWidget.prototype.attributeMap,{rows:"textbox",cols:"textbox"}),rows:"3",cols:"20",templateString:"<textarea ${nameAttrSetting} dojoAttachPoint='focusNode,containerNode,textbox' autocomplete='off'></textarea>",postMixInProperties:function(){
if(!this.value&&this.srcNodeRef){
this.value=this.srcNodeRef.value;
}
this.inherited(arguments);
},filter:function(_1){
if(_1){
_1=_1.replace(/\r/g,"");
}
return this.inherited(arguments);
},postCreate:function(){
this.inherited(arguments);
if(dojo.isIE&&this.cols){
dojo.addClass(this.textbox,"dijitTextAreaCols");
}
},_previousValue:"",_onInput:function(e){
if(this.maxLength){
var _2=parseInt(this.maxLength);
var _3=this.textbox.value.replace(/\r/g,"");
var _4=_3.length-_2;
if(_4>0){
if(e){
dojo.stopEvent(e);
}
var _5=this.textbox;
if(_5.selectionStart){
var _6=_5.selectionStart;
var cr=0;
if(dojo.isOpera){
cr=(this.textbox.value.substring(0,_6).match(/\r/g)||[]).length;
}
this.textbox.value=_3.substring(0,_6-_4-cr)+_3.substring(_6-cr);
_5.setSelectionRange(_6-_4,_6-_4);
}else{
if(dojo.doc.selection){
_5.focus();
var _7=dojo.doc.selection.createRange();
_7.moveStart("character",-_4);
_7.text="";
_7.select();
}
}
}
this._previousValue=this.textbox.value;
}
this.inherited(arguments);
}});
}
