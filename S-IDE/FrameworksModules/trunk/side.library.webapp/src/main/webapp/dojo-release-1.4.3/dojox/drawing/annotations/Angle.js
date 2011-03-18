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


if(!dojo._hasResource["dojox.drawing.annotations.Angle"]){
dojo._hasResource["dojox.drawing.annotations.Angle"]=true;
dojo.provide("dojox.drawing.annotations.Angle");
dojox.drawing.annotations.Angle=dojox.drawing.util.oo.declare(function(_1){
this.stencil=_1.stencil;
this.util=_1.stencil.util;
this.mouse=_1.stencil.mouse;
this.stencil.connectMult([["onDrag",this,"showAngle"],["onUp",this,"hideAngle"],["onTransformBegin",this,"showAngle"],["onTransform",this,"showAngle"],["onTransformEnd",this,"hideAngle"]]);
},{type:"dojox.drawing.tools.custom",angle:0,showAngle:function(){
if(!this.stencil.selected&&this.stencil.created){
return;
}
if(this.stencil.getRadius()<this.stencil.minimumSize){
this.hideAngle();
return;
}
var _2=this.getAngleNode();
var d=this.stencil.pointsToData();
var pt=dojox.drawing.util.positioning.angle({x:d.x1,y:d.y1},{x:d.x2,y:d.y2});
var sc=this.mouse.scrollOffset();
var mx=this.stencil.getTransform();
var dx=mx.dx/this.mouse.zoom;
var dy=mx.dy/this.mouse.zoom;
pt.x/=this.mouse.zoom;
pt.y/=this.mouse.zoom;
var x=this.stencil._offX+pt.x-sc.left+dx;
var y=this.stencil._offY+pt.y-sc.top+dy;
dojo.style(_2,{left:x+"px",top:y+"px",align:pt.align});
_2.innerHTML=Math.ceil(this.stencil.getAngle());
},getAngleNode:function(){
if(!this._angleNode){
this._angleNode=dojo.create("span",null,dojo.body());
dojo.addClass(this._angleNode,"textAnnotation");
dojo.style(this._angleNode,"opacity",1);
}
return this._angleNode;
},hideAngle:function(){
if(this._angleNode&&dojo.style(this._angleNode,"opacity")>0.9){
dojo.fadeOut({node:this._angleNode,duration:500,onEnd:function(_3){
dojo.destroy(_3);
}}).play();
this._angleNode=null;
}
}});
}
