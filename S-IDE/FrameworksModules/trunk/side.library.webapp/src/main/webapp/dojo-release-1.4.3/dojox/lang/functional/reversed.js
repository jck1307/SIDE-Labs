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


if(!dojo._hasResource["dojox.lang.functional.reversed"]){
dojo._hasResource["dojox.lang.functional.reversed"]=true;
dojo.provide("dojox.lang.functional.reversed");
dojo.require("dojox.lang.functional.lambda");
(function(){
var d=dojo,df=dojox.lang.functional;
d.mixin(df,{filterRev:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var t=[],v,i=a.length-1;
for(;i>=0;--i){
v=a[i];
if(f.call(o,v,i,a)){
t.push(v);
}
}
return t;
},forEachRev:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
for(var i=a.length-1;i>=0;f.call(o,a[i],i,a),--i){
}
},mapRev:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var n=a.length,t=new Array(n),i=n-1,j=0;
for(;i>=0;t[j++]=f.call(o,a[i],i,a),--i){
}
return t;
},everyRev:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
for(var i=a.length-1;i>=0;--i){
if(!f.call(o,a[i],i,a)){
return false;
}
}
return true;
},someRev:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
for(var i=a.length-1;i>=0;--i){
if(f.call(o,a[i],i,a)){
return true;
}
}
return false;
}});
})();
}
