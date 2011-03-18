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


if(!dojo._hasResource["dojox.gfx3d.vector"]){
dojo._hasResource["dojox.gfx3d.vector"]=true;
dojo.provide("dojox.gfx3d.vector");
dojo.mixin(dojox.gfx3d.vector,{sum:function(){
var v={x:0,y:0,z:0};
dojo.forEach(arguments,function(_1){
v.x+=_1.x;
v.y+=_1.y;
v.z+=_1.z;
});
return v;
},center:function(){
var l=arguments.length;
if(l==0){
return {x:0,y:0,z:0};
}
var v=dojox.gfx3d.vector.sum(arguments);
return {x:v.x/l,y:v.y/l,z:v.z/l};
},substract:function(a,b){
return {x:a.x-b.x,y:a.y-b.y,z:a.z-b.z};
},_crossProduct:function(x,y,z,u,v,w){
return {x:y*w-z*v,y:z*u-x*w,z:x*v-y*u};
},crossProduct:function(a,b,c,d,e,f){
if(arguments.length==6&&dojo.every(arguments,function(_2){
return typeof _2=="number";
})){
return dojox.gfx3d.vector._crossProduct(a,b,c,d,e,f);
}
return dojox.gfx3d.vector._crossProduct(a.x,a.y,a.z,b.x,b.y,b.z);
},_dotProduct:function(x,y,z,u,v,w){
return x*u+y*v+z*w;
},dotProduct:function(a,b,c,d,e,f){
if(arguments.length==6&&dojo.every(arguments,function(_3){
return typeof _3=="number";
})){
return dojox.gfx3d.vector._dotProduct(a,b,c,d,e,f);
}
return dojox.gfx3d.vector._dotProduct(a.x,a.y,a.z,b.x,b.y,b.z);
},normalize:function(a,b,c){
var l,m,n;
if(a instanceof Array){
l=a[0];
m=a[1];
n=a[2];
}else{
l=a;
m=b;
n=c;
}
var u=dojox.gfx3d.vector.substract(m,l);
var v=dojox.gfx3d.vector.substract(n,l);
return dojox.gfx3d.vector.crossProduct(u,v);
}});
}
