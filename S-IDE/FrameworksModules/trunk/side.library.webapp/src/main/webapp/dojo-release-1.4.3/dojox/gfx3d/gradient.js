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


if(!dojo._hasResource["dojox.gfx3d.gradient"]){
dojo._hasResource["dojox.gfx3d.gradient"]=true;
dojo.provide("dojox.gfx3d.gradient");
dojo.require("dojox.gfx3d.vector");
dojo.require("dojox.gfx3d.matrix");
(function(){
var _1=function(a,b){
return Math.sqrt(Math.pow(b.x-a.x,2)+Math.pow(b.y-a.y,2));
};
var N=32;
dojox.gfx3d.gradient=function(_2,_3,_4,_5,_6,to,_7){
var m=dojox.gfx3d.matrix,v=dojox.gfx3d.vector,mx=m.normalize(_7),f=m.multiplyPoint(mx,_5*Math.cos(_6)+_4.x,_5*Math.sin(_6)+_4.y,_4.z),t=m.multiplyPoint(mx,_5*Math.cos(to)+_4.x,_5*Math.sin(to)+_4.y,_4.z),c=m.multiplyPoint(mx,_4.x,_4.y,_4.z),_8=(to-_6)/N,r=_1(f,t)/2,_9=_2[_3.type],_a=_3.finish,_b=_3.color,_c=[{offset:0,color:_9.call(_2,v.substract(f,c),_a,_b)}];
for(var a=_6+_8;a<to;a+=_8){
var p=m.multiplyPoint(mx,_5*Math.cos(a)+_4.x,_5*Math.sin(a)+_4.y,_4.z),df=_1(f,p),dt=_1(t,p);
_c.push({offset:df/(df+dt),color:_9.call(_2,v.substract(p,c),_a,_b)});
}
_c.push({offset:1,color:_9.call(_2,v.substract(t,c),_a,_b)});
return {type:"linear",x1:0,y1:-r,x2:0,y2:r,colors:_c};
};
})();
}
