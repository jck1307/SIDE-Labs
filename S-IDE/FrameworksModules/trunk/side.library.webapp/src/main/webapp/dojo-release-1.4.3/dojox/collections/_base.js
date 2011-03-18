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


if(!dojo._hasResource["dojox.collections._base"]){
dojo._hasResource["dojox.collections._base"]=true;
dojo.provide("dojox.collections._base");
dojox.collections.DictionaryEntry=function(k,v){
this.key=k;
this.value=v;
this.valueOf=function(){
return this.value;
};
this.toString=function(){
return String(this.value);
};
};
dojox.collections.Iterator=function(_1){
var a=_1;
var _2=0;
this.element=a[_2]||null;
this.atEnd=function(){
return (_2>=a.length);
};
this.get=function(){
if(this.atEnd()){
return null;
}
this.element=a[_2++];
return this.element;
};
this.map=function(fn,_3){
return dojo.map(a,fn,_3);
};
this.reset=function(){
_2=0;
this.element=a[_2];
};
};
dojox.collections.DictionaryIterator=function(_4){
var a=[];
var _5={};
for(var p in _4){
if(!_5[p]){
a.push(_4[p]);
}
}
var _6=0;
this.element=a[_6]||null;
this.atEnd=function(){
return (_6>=a.length);
};
this.get=function(){
if(this.atEnd()){
return null;
}
this.element=a[_6++];
return this.element;
};
this.map=function(fn,_7){
return dojo.map(a,fn,_7);
};
this.reset=function(){
_6=0;
this.element=a[_6];
};
};
}
