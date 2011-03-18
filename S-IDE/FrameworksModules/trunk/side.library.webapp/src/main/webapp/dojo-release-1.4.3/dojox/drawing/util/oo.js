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


if(!dojo._hasResource["dojox.drawing.util.oo"]){
dojo._hasResource["dojox.drawing.util.oo"]=true;
dojo.provide("dojox.drawing.util.oo");
dojox.drawing.util.oo={declare:function(){
var f,o,_1=0,a=arguments;
if(a.length<2){
console.error("gfx.oo.declare; not enough arguments");
}
if(a.length==2){
f=a[0];
o=a[1];
}else{
a=Array.prototype.slice.call(arguments);
o=a.pop();
f=a.pop();
_1=1;
}
for(var n in o){
f.prototype[n]=o[n];
}
if(_1){
a.unshift(f);
f=this.extend.apply(this,a);
}
return f;
},extend:function(){
var a=arguments,_2=a[0];
if(a.length<2){
console.error("gfx.oo.extend; not enough arguments");
}
var f=function(){
for(var i=1;i<a.length;i++){
a[i].prototype.constructor.apply(this,arguments);
}
_2.prototype.constructor.apply(this,arguments);
};
for(var i=1;i<a.length;i++){
for(var n in a[i].prototype){
f.prototype[n]=a[i].prototype[n];
}
}
for(var n in _2.prototype){
f.prototype[n]=_2.prototype[n];
}
return f;
}};
}
