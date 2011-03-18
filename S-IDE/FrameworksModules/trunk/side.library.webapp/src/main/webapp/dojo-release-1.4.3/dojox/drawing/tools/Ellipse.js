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


if(!dojo._hasResource["dojox.drawing.tools.Ellipse"]){
dojo._hasResource["dojox.drawing.tools.Ellipse"]=true;
dojo.provide("dojox.drawing.tools.Ellipse");
dojox.drawing.tools.Ellipse=dojox.drawing.util.oo.declare(dojox.drawing.stencil.Ellipse,function(){
},{draws:true,onDrag:function(_1){
var s=_1.start,e=_1;
var x=s.x<e.x?s.x:e.x,y=s.y<e.y?s.y:e.y,w=s.x<e.x?e.x-s.x:s.x-e.x,h=s.y<e.y?e.y-s.y:s.y-e.y;
if(this.keys.shift){
w=h=Math.max(w,h);
}
if(!this.keys.alt){
x+=w/2;
y+=h/2;
w/=2;
h/=2;
}else{
if(y-h<0){
h=y;
}
if(x-w<0){
w=x;
}
}
this.points=[{x:x-w,y:y-h},{x:x+w,y:y-h},{x:x+w,y:y+h},{x:x-w,y:y+h}];
this.render();
},onUp:function(_2){
if(this.created||!this.shape){
return;
}
var o=this.pointsToData();
if(o.rx*2<this.minimumSize&&o.ry*2<this.minimumSize){
this.remove(this.shape,this.hit);
return;
}
this.onRender(this);
}});
dojox.drawing.tools.Ellipse.setup={name:"dojox.drawing.tools.Ellipse",tooltip:"Ellipse Tool",iconClass:"iconEllipse"};
dojox.drawing.register(dojox.drawing.tools.Ellipse.setup,"tool");
}
