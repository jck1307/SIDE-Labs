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


if(!dojo._hasResource["dojox.lang.functional.object"]){
dojo._hasResource["dojox.lang.functional.object"]=true;
dojo.provide("dojox.lang.functional.object");
dojo.require("dojox.lang.functional.lambda");
(function(){
var d=dojo,df=dojox.lang.functional,_1={};
d.mixin(df,{keys:function(_2){
var t=[];
for(var i in _2){
if(!(i in _1)){
t.push(i);
}
}
return t;
},values:function(_3){
var t=[];
for(var i in _3){
if(!(i in _1)){
t.push(_3[i]);
}
}
return t;
},filterIn:function(_4,f,o){
o=o||d.global;
f=df.lambda(f);
var t={},v,i;
for(i in _4){
if(!(i in _1)){
v=_4[i];
if(f.call(o,v,i,_4)){
t[i]=v;
}
}
}
return t;
},forIn:function(_5,f,o){
o=o||d.global;
f=df.lambda(f);
for(var i in _5){
if(!(i in _1)){
f.call(o,_5[i],i,_5);
}
}
return o;
},mapIn:function(_6,f,o){
o=o||d.global;
f=df.lambda(f);
var t={},i;
for(i in _6){
if(!(i in _1)){
t[i]=f.call(o,_6[i],i,_6);
}
}
return t;
}});
})();
}
