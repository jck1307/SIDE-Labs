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


if(!dojo._hasResource["dojox.form.TimeSpinner"]){
dojo._hasResource["dojox.form.TimeSpinner"]=true;
dojo.provide("dojox.form.TimeSpinner");
dojo.require("dijit.form._Spinner");
dojo.require("dijit.form.NumberTextBox");
dojo.require("dojo.date");
dojo.require("dojo.date.locale");
dojo.require("dojo.date.stamp");
dojo.declare("dojox.form.TimeSpinner",[dijit.form._Spinner],{required:false,adjust:function(_1,_2){
return dojo.date.add(_1,"minute",_2);
},isValid:function(){
return true;
},smallDelta:5,largeDelta:30,timeoutChangeRate:0.5,parse:function(_3,_4){
return dojo.date.locale.parse(_3,{selector:"time",formatLength:"short"});
},format:function(_5,_6){
if(dojo.isString(_5)){
return _5;
}
return dojo.date.locale.format(_5,{selector:"time",formatLength:"short"});
},serialize:dojo.date.stamp.toISOString,value:"12:00 AM",_onKeyPress:function(e){
if((e.charOrCode==dojo.keys.HOME||e.charOrCode==dojo.keys.END)&&!(e.ctrlKey||e.altKey||e.metaKey)&&typeof this.attr("value")!="undefined"){
var _7=this.constraints[(e.charOrCode==dojo.keys.HOME?"min":"max")];
if(_7){
this._setValueAttr(_7,true);
}
dojo.stopEvent(e);
}
}});
}
