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


if(!dojo._hasResource["dojox.drawing.tools.Rect"]){
dojo._hasResource["dojox.drawing.tools.Rect"]=true;
dojo.provide("dojox.drawing.tools.Rect");
dojox.drawing.tools.Rect=dojox.drawing.util.oo.declare(dojox.drawing.stencil.Rect,function(){
},{draws:true,onDrag:function(_1){
var s=_1.start,e=_1;
var x=s.x<e.x?s.x:e.x,y=s.y<e.y?s.y:e.y,w=s.x<e.x?e.x-s.x:s.x-e.x,h=s.y<e.y?e.y-s.y:s.y-e.y;
if(this.keys.shift){
w=h=Math.max(w,h);
}
if(this.keys.alt){
x-=w;
y-=h;
w*=2;
h*=2;
x=Math.max(x,0);
y=Math.max(y,0);
}
this.setPoints([{x:x,y:y},{x:x+w,y:y},{x:x+w,y:y+h},{x:x,y:y+h}]);
this.render();
},onUp:function(_2){
if(this.created||!this.shape){
return;
}
var o=this.data;
if(o.width<this.minimumSize&&o.height<this.minimumSize){
this.remove(this.shape,this.hit);
return;
}
this.onRender(this);
}});
dojox.drawing.tools.Rect.setup={name:"dojox.drawing.tools.Rect",tooltip:"<span class=\"drawingTipTitle\">Rectangle Tool</span><br/>"+"<span class=\"drawingTipDesc\">SHIFT - constrain to square</span>",iconClass:"iconRect"};
dojox.drawing.register(dojox.drawing.tools.Rect.setup,"tool");
}
