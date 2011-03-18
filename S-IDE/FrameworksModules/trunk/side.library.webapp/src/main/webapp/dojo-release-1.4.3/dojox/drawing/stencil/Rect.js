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


if(!dojo._hasResource["dojox.drawing.stencil.Rect"]){
dojo._hasResource["dojox.drawing.stencil.Rect"]=true;
dojo.provide("dojox.drawing.stencil.Rect");
dojox.drawing.stencil.Rect=dojox.drawing.util.oo.declare(dojox.drawing.stencil._Base,function(_1){
if(this.points.length){
}
},{type:"dojox.drawing.stencil.Rect",anchorType:"group",baseRender:true,dataToPoints:function(d){
d=d||this.data;
this.points=[{x:d.x,y:d.y},{x:d.x+d.width,y:d.y},{x:d.x+d.width,y:d.y+d.height},{x:d.x,y:d.y+d.height}];
return this.points;
},pointsToData:function(p){
p=p||this.points;
var s=p[0];
var e=p[2];
this.data={x:s.x,y:s.y,width:e.x-s.x,height:e.y-s.y,r:this.data.r||0};
return this.data;
},_create:function(_2,d,_3){
this.remove(this[_2]);
this[_2]=this.container.createRect(d).setStroke(_3).setFill(_3.fill);
this._setNodeAtts(this[_2]);
},render:function(){
this.onBeforeRender(this);
this.renderHit&&this._create("hit",this.data,this.style.currentHit);
this._create("shape",this.data,this.style.current);
}});
dojox.drawing.register({name:"dojox.drawing.stencil.Rect"},"stencil");
}
