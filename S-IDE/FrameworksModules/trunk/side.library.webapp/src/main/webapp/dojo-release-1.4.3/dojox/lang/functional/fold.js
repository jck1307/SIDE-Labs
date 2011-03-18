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


if(!dojo._hasResource["dojox.lang.functional.fold"]){
dojo._hasResource["dojox.lang.functional.fold"]=true;
dojo.provide("dojox.lang.functional.fold");
dojo.require("dojox.lang.functional.lambda");
(function(){
var d=dojo,df=dojox.lang.functional,_1={};
d.mixin(df,{foldl:function(a,f,z,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var i,n;
if(d.isArray(a)){
for(i=0,n=a.length;i<n;z=f.call(o,z,a[i],i,a),++i){
}
}else{
if(typeof a.hasNext=="function"&&typeof a.next=="function"){
for(i=0;a.hasNext();z=f.call(o,z,a.next(),i++,a)){
}
}else{
for(i in a){
if(!(i in _1)){
z=f.call(o,z,a[i],i,a);
}
}
}
}
return z;
},foldl1:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var z,i,n;
if(d.isArray(a)){
z=a[0];
for(i=1,n=a.length;i<n;z=f.call(o,z,a[i],i,a),++i){
}
}else{
if(typeof a.hasNext=="function"&&typeof a.next=="function"){
if(a.hasNext()){
z=a.next();
for(i=1;a.hasNext();z=f.call(o,z,a.next(),i++,a)){
}
}
}else{
var _2=true;
for(i in a){
if(!(i in _1)){
if(_2){
z=a[i];
_2=false;
}else{
z=f.call(o,z,a[i],i,a);
}
}
}
}
}
return z;
},foldr:function(a,f,z,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
for(var i=a.length;i>0;--i,z=f.call(o,z,a[i],i,a)){
}
return z;
},foldr1:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var n=a.length,z=a[n-1],i=n-1;
for(;i>0;--i,z=f.call(o,z,a[i],i,a)){
}
return z;
},reduce:function(a,f,z){
return arguments.length<3?df.foldl1(a,f):df.foldl(a,f,z);
},reduceRight:function(a,f,z){
return arguments.length<3?df.foldr1(a,f):df.foldr(a,f,z);
},unfold:function(pr,f,g,z,o){
o=o||d.global;
f=df.lambda(f);
g=df.lambda(g);
pr=df.lambda(pr);
var t=[];
for(;!pr.call(o,z);t.push(f.call(o,z)),z=g.call(o,z)){
}
return t;
}});
})();
}
