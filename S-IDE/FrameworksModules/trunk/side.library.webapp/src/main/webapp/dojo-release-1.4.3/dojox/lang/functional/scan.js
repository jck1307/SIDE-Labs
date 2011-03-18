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


if(!dojo._hasResource["dojox.lang.functional.scan"]){
dojo._hasResource["dojox.lang.functional.scan"]=true;
dojo.provide("dojox.lang.functional.scan");
dojo.require("dojox.lang.functional.lambda");
(function(){
var d=dojo,df=dojox.lang.functional,_1={};
d.mixin(df,{scanl:function(a,f,z,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var t,n,i;
if(d.isArray(a)){
t=new Array((n=a.length)+1);
t[0]=z;
for(i=0;i<n;z=f.call(o,z,a[i],i,a),t[++i]=z){
}
}else{
if(typeof a.hasNext=="function"&&typeof a.next=="function"){
t=[z];
for(i=0;a.hasNext();t.push(z=f.call(o,z,a.next(),i++,a))){
}
}else{
t=[z];
for(i in a){
if(!(i in _1)){
t.push(z=f.call(o,z,a[i],i,a));
}
}
}
}
return t;
},scanl1:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var t,n,z,_2=true;
if(d.isArray(a)){
t=new Array(n=a.length);
t[0]=z=a[0];
for(var i=1;i<n;t[i]=z=f.call(o,z,a[i],i,a),++i){
}
}else{
if(typeof a.hasNext=="function"&&typeof a.next=="function"){
if(a.hasNext()){
t=[z=a.next()];
for(var i=1;a.hasNext();t.push(z=f.call(o,z,a.next(),i++,a))){
}
}
}else{
for(var i in a){
if(!(i in _1)){
if(_2){
t=[z=a[i]];
_2=false;
}else{
t.push(z=f.call(o,z,a[i],i,a));
}
}
}
}
}
return t;
},scanr:function(a,f,z,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var n=a.length,t=new Array(n+1),i=n;
t[n]=z;
for(;i>0;--i,z=f.call(o,z,a[i],i,a),t[i]=z){
}
return t;
},scanr1:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var n=a.length,t=new Array(n),z=a[n-1],i=n-1;
t[i]=z;
for(;i>0;--i,z=f.call(o,z,a[i],i,a),t[i]=z){
}
return t;
}});
})();
}
