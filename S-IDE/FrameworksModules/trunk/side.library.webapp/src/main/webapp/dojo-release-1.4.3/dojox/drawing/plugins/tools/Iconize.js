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


if(!dojo._hasResource["dojox.drawing.plugins.tools.Iconize"]){
dojo._hasResource["dojox.drawing.plugins.tools.Iconize"]=true;
dojo.provide("dojox.drawing.plugins.tools.Iconize");
dojo.require("dojox.drawing.plugins._Plugin");
dojox.drawing.plugins.tools.Iconize=dojox.drawing.util.oo.declare(dojox.drawing.plugins._Plugin,function(_1){
},{onClick:function(){
var _2;
for(var nm in this.stencils.stencils){
if(this.stencils.stencils[nm].shortType=="path"){
_2=this.stencils.stencils[nm];
break;
}
}
if(_2){
this.makeIcon(_2.points);
}
},makeIcon:function(p){
var _3=function(n){
return Number(n.toFixed(1));
};
var x=10000;
var y=10000;
p.forEach(function(pt){
if(pt.x!==undefined&&!isNaN(pt.x)){
x=Math.min(x,pt.x);
y=Math.min(y,pt.y);
}
});
var _4=0;
var _5=0;
p.forEach(function(pt){
if(pt.x!==undefined&&!isNaN(pt.x)){
pt.x=_3(pt.x-x);
pt.y=_3(pt.y-y);
_4=Math.max(_4,pt.x);
_5=Math.max(_5,pt.y);
}
});
var s=60;
var m=20;
p.forEach(function(pt){
pt.x=_3(pt.x/_4)*s+m;
pt.y=_3(pt.y/_5)*s+m;
});
var _6="[\n";
dojo.forEach(p,function(pt,i){
_6+="{\t";
if(pt.t){
_6+="t:'"+pt.t+"'";
}
if(pt.x!==undefined&&!isNaN(pt.x)){
if(pt.t){
_6+=", ";
}
_6+="x:"+pt.x+",\t\ty:"+pt.y;
}
_6+="\t}";
if(i!=p.length-1){
_6+=",";
}
_6+="\n";
});
_6+="]";
var n=dojo.byId("data");
if(n){
n.value=_6;
}
}});
dojox.drawing.plugins.tools.Iconize.setup={name:"dojox.drawing.plugins.tools.Iconize",tooltip:"Iconize Tool",iconClass:"iconPan"};
dojox.drawing.register(dojox.drawing.plugins.tools.Iconize.setup,"plugin");
}
