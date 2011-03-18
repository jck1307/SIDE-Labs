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


if(!dojo._hasResource["dojox.drawing.tools.Line"]){
dojo._hasResource["dojox.drawing.tools.Line"]=true;
dojo.provide("dojox.drawing.tools.Line");
dojox.drawing.tools.Line=dojox.drawing.util.oo.declare(dojox.drawing.stencil.Line,function(){
},{draws:true,showAngle:true,onTransformEnd:function(_1){
this._toggleSelected();
if(this.getRadius()<this.minimumSize){
var p=this.points;
this.setPoints([{x:p[0].x,y:p[0].y},{x:p[0].x,y:p[0].y}]);
}else{
var d=this.data;
var _2={start:{x:d.x1,y:d.y1},x:d.x2,y:d.y2};
var pt=this.util.snapAngle(_2,this.angleSnap/180);
this.setPoints([{x:d.x1,y:d.y1},{x:pt.x,y:pt.y}]);
this._isBeingModified=false;
this.onModify(this);
}
},onDrag:function(_3){
if(this.created){
return;
}
var x1=_3.start.x,y1=_3.start.y,x2=_3.x,y2=_3.y;
if(this.keys.shift){
var pt=this.util.snapAngle(_3,45/180);
x2=pt.x;
y2=pt.y;
}
if(this.keys.alt){
var dx=x2>x1?((x2-x1)/2):((x1-x2)/-2);
var dy=y2>y1?((y2-y1)/2):((y1-y2)/-2);
x1-=dx;
x2-=dx;
y1-=dy;
y2-=dy;
}
this.setPoints([{x:x1,y:y1},{x:x2,y:y2}]);
this.render();
},onUp:function(_4){
if(this.created||!this.shape){
return;
}
if(this.getRadius()<this.minimumSize){
this.remove(this.shape,this.hit);
return;
}
var pt=this.util.snapAngle(_4,this.angleSnap/180);
var p=this.points;
this.setPoints([{x:p[0].x,y:p[0].y},{x:pt.x,y:pt.y}]);
this.renderedOnce=true;
this.onRender(this);
}});
dojox.drawing.tools.Line.setup={name:"dojox.drawing.tools.Line",tooltip:"Line Tool",iconClass:"iconLine"};
dojox.drawing.register(dojox.drawing.tools.Line.setup,"tool");
}
