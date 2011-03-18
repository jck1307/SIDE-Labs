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


if(!dojo._hasResource["dojox.lang.functional.array"]){
dojo._hasResource["dojox.lang.functional.array"]=true;
dojo.provide("dojox.lang.functional.array");
dojo.require("dojox.lang.functional.lambda");
(function(){
var d=dojo,df=dojox.lang.functional,_1={};
d.mixin(df,{filter:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var t=[],v,i,n;
if(d.isArray(a)){
for(i=0,n=a.length;i<n;++i){
v=a[i];
if(f.call(o,v,i,a)){
t.push(v);
}
}
}else{
if(typeof a.hasNext=="function"&&typeof a.next=="function"){
for(i=0;a.hasNext();){
v=a.next();
if(f.call(o,v,i++,a)){
t.push(v);
}
}
}else{
for(i in a){
if(!(i in _1)){
v=a[i];
if(f.call(o,v,i,a)){
t.push(v);
}
}
}
}
}
return t;
},forEach:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var i,n;
if(d.isArray(a)){
for(i=0,n=a.length;i<n;f.call(o,a[i],i,a),++i){
}
}else{
if(typeof a.hasNext=="function"&&typeof a.next=="function"){
for(i=0;a.hasNext();f.call(o,a.next(),i++,a)){
}
}else{
for(i in a){
if(!(i in _1)){
f.call(o,a[i],i,a);
}
}
}
}
return o;
},map:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var t,n,i;
if(d.isArray(a)){
t=new Array(n=a.length);
for(i=0;i<n;t[i]=f.call(o,a[i],i,a),++i){
}
}else{
if(typeof a.hasNext=="function"&&typeof a.next=="function"){
t=[];
for(i=0;a.hasNext();t.push(f.call(o,a.next(),i++,a))){
}
}else{
t=[];
for(i in a){
if(!(i in _1)){
t.push(f.call(o,a[i],i,a));
}
}
}
}
return t;
},every:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var i,n;
if(d.isArray(a)){
for(i=0,n=a.length;i<n;++i){
if(!f.call(o,a[i],i,a)){
return false;
}
}
}else{
if(typeof a.hasNext=="function"&&typeof a.next=="function"){
for(i=0;a.hasNext();){
if(!f.call(o,a.next(),i++,a)){
return false;
}
}
}else{
for(i in a){
if(!(i in _1)){
if(!f.call(o,a[i],i,a)){
return false;
}
}
}
}
}
return true;
},some:function(a,f,o){
if(typeof a=="string"){
a=a.split("");
}
o=o||d.global;
f=df.lambda(f);
var i,n;
if(d.isArray(a)){
for(i=0,n=a.length;i<n;++i){
if(f.call(o,a[i],i,a)){
return true;
}
}
}else{
if(typeof a.hasNext=="function"&&typeof a.next=="function"){
for(i=0;a.hasNext();){
if(f.call(o,a.next(),i++,a)){
return true;
}
}
}else{
for(i in a){
if(!(i in _1)){
if(f.call(o,a[i],i,a)){
return true;
}
}
}
}
}
return false;
}});
})();
}
