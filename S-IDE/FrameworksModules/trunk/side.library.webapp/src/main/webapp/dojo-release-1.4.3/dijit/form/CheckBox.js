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


if(!dojo._hasResource["dijit.form.CheckBox"]){
dojo._hasResource["dijit.form.CheckBox"]=true;
dojo.provide("dijit.form.CheckBox");
dojo.require("dijit.form.Button");
dojo.declare("dijit.form.CheckBox",dijit.form.ToggleButton,{templateString:dojo.cache("dijit.form","templates/CheckBox.html","<div class=\"dijitReset dijitInline\" waiRole=\"presentation\"\n\t><input\n\t \t${nameAttrSetting} type=\"${type}\" ${checkedAttrSetting}\n\t\tclass=\"dijitReset dijitCheckBoxInput\"\n\t\tdojoAttachPoint=\"focusNode\"\n\t \tdojoAttachEvent=\"onmouseover:_onMouse,onmouseout:_onMouse,onclick:_onClick\"\n/></div>\n"),baseClass:"dijitCheckBox",type:"checkbox",value:"on",readOnly:false,attributeMap:dojo.delegate(dijit.form.ToggleButton.prototype.attributeMap,{readOnly:"focusNode"}),_setReadOnlyAttr:function(_1){
this.readOnly=_1;
dojo.attr(this.focusNode,"readOnly",_1);
dijit.setWaiState(this.focusNode,"readonly",_1);
this._setStateClass();
},_setValueAttr:function(_2){
if(typeof _2=="string"){
this.value=_2;
dojo.attr(this.focusNode,"value",_2);
_2=true;
}
if(this._created){
this.attr("checked",_2);
}
},_getValueAttr:function(){
return (this.checked?this.value:false);
},postMixInProperties:function(){
if(this.value==""){
this.value="on";
}
this.checkedAttrSetting=this.checked?"checked":"";
this.inherited(arguments);
},_fillContent:function(_3){
},reset:function(){
this._hasBeenBlurred=false;
this.attr("checked",this.params.checked||false);
this.value=this.params.value||"on";
dojo.attr(this.focusNode,"value",this.value);
},_onFocus:function(){
if(this.id){
dojo.query("label[for='"+this.id+"']").addClass("dijitFocusedLabel");
}
},_onBlur:function(){
if(this.id){
dojo.query("label[for='"+this.id+"']").removeClass("dijitFocusedLabel");
}
},_onClick:function(e){
if(this.readOnly){
return false;
}
return this.inherited(arguments);
}});
dojo.declare("dijit.form.RadioButton",dijit.form.CheckBox,{type:"radio",baseClass:"dijitRadio",_setCheckedAttr:function(_4){
this.inherited(arguments);
if(!this._created){
return;
}
if(_4){
var _5=this;
dojo.query("INPUT[type=radio]",this.focusNode.form||dojo.doc).forEach(function(_6){
if(_6.name==_5.name&&_6!=_5.focusNode&&_6.form==_5.focusNode.form){
var _7=dijit.getEnclosingWidget(_6);
if(_7&&_7.checked){
_7.attr("checked",false);
}
}
});
}
},_clicked:function(e){
if(!this.checked){
this.attr("checked",true);
}
}});
}
