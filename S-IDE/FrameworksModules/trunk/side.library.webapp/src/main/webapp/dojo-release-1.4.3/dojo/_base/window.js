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


if(!dojo._hasResource["dojo._base.window"]){
dojo._hasResource["dojo._base.window"]=true;
dojo.provide("dojo._base.window");
dojo.doc=window["document"]||null;
dojo.body=function(){
return dojo.doc.body||dojo.doc.getElementsByTagName("body")[0];
};
dojo.setContext=function(_1,_2){
dojo.global=_1;
dojo.doc=_2;
};
dojo.withGlobal=function(_3,_4,_5,_6){
var _7=dojo.global;
try{
dojo.global=_3;
return dojo.withDoc.call(null,_3.document,_4,_5,_6);
}
finally{
dojo.global=_7;
}
};
dojo.withDoc=function(_8,_9,_a,_b){
var _c=dojo.doc,_d=dojo._bodyLtr,_e=dojo.isQuirks;
try{
dojo.doc=_8;
delete dojo._bodyLtr;
dojo.isQuirks=dojo.doc.compatMode=="BackCompat";
if(_a&&typeof _9=="string"){
_9=_a[_9];
}
return _9.apply(_a,_b||[]);
}
finally{
dojo.doc=_c;
delete dojo._bodyLtr;
if(_d!==undefined){
dojo._bodyLtr=_d;
}
dojo.isQuirks=_e;
}
};
}
