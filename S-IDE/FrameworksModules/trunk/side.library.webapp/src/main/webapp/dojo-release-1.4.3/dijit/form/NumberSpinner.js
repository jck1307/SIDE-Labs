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


if(!dojo._hasResource["dijit.form.NumberSpinner"]){
dojo._hasResource["dijit.form.NumberSpinner"]=true;
dojo.provide("dijit.form.NumberSpinner");
dojo.require("dijit.form._Spinner");
dojo.require("dijit.form.NumberTextBox");
dojo.declare("dijit.form.NumberSpinner",[dijit.form._Spinner,dijit.form.NumberTextBoxMixin],{adjust:function(_1,_2){
var tc=this.constraints,v=isNaN(_1),_3=!isNaN(tc.max),_4=!isNaN(tc.min);
if(v&&_2!=0){
_1=(_2>0)?_4?tc.min:_3?tc.max:0:_3?this.constraints.max:_4?tc.min:0;
}
var _5=_1+_2;
if(v||isNaN(_5)){
return _1;
}
if(_3&&(_5>tc.max)){
_5=tc.max;
}
if(_4&&(_5<tc.min)){
_5=tc.min;
}
return _5;
},_onKeyPress:function(e){
if((e.charOrCode==dojo.keys.HOME||e.charOrCode==dojo.keys.END)&&!(e.ctrlKey||e.altKey||e.metaKey)&&typeof this.attr("value")!="undefined"){
var _6=this.constraints[(e.charOrCode==dojo.keys.HOME?"min":"max")];
if(_6){
this._setValueAttr(_6,true);
}
dojo.stopEvent(e);
}
}});
}
