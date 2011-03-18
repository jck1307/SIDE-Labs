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


if(!dojo._hasResource["dojox.xml.widgetParser"]){
dojo._hasResource["dojox.xml.widgetParser"]=true;
dojo.provide("dojox.xml.widgetParser");
dojo.require("dojox.xml.parser");
dojo.require("dojo.parser");
dojox.xml.widgetParser=new function(){
var d=dojo;
this.parseNode=function(_1){
var _2=[];
d.query("script[type='text/xml']",_1).forEach(function(_3){
_2.push.apply(_2,this._processScript(_3));
},this).orphan();
return d.parser.instantiate(_2);
};
this._processScript=function(_4){
var _5=_4.src?d._getText(_4.src):_4.innerHTML||_4.firstChild.nodeValue;
var _6=this.toHTML(dojox.xml.parser.parse(_5).firstChild);
var _7=d.query("[dojoType]",_6);
dojo.query(">",_6).place(_4,"before");
_4.parentNode.removeChild(_4);
return _7;
};
this.toHTML=function(_8){
var _9;
var _a=_8.nodeName;
var dd=dojo.doc;
var _b=_8.nodeType;
if(_b>=3){
return dd.createTextNode((_b==3||_b==4)?_8.nodeValue:"");
}
var _c=_8.localName||_a.split(":").pop();
var _d=_8.namespaceURI||(_8.getNamespaceUri?_8.getNamespaceUri():"");
if(_d=="html"){
_9=dd.createElement(_c);
}else{
var _e=_d+"."+_c;
_9=_9||dd.createElement((_e=="dijit.form.ComboBox")?"select":"div");
_9.setAttribute("dojoType",_e);
}
d.forEach(_8.attributes,function(_f){
var _10=_f.name||_f.nodeName;
var _11=_f.value||_f.nodeValue;
if(_10.indexOf("xmlns")!=0){
if(dojo.isIE&&_10=="style"){
_9.style.setAttribute("cssText",_11);
}else{
_9.setAttribute(_10,_11);
}
}
});
d.forEach(_8.childNodes,function(cn){
var _12=this.toHTML(cn);
if(_c=="script"){
_9.text+=_12.nodeValue;
}else{
_9.appendChild(_12);
}
},this);
return _9;
};
}();
}
