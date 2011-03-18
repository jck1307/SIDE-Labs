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


if(!dojo._hasResource["dojox.sketch.Anchor"]){
dojo._hasResource["dojox.sketch.Anchor"]=true;
dojo.provide("dojox.sketch.Anchor");
dojo.require("dojox.gfx");
(function(){
var ta=dojox.sketch;
ta.Anchor=function(an,id,_1){
var _2=this;
var _3=4;
var _4=null;
this.type=function(){
return "Anchor";
};
this.annotation=an;
this.id=id;
this._key="anchor-"+ta.Anchor.count++;
this.shape=null;
this.isControl=(_1!=null)?_1:true;
this.beginEdit=function(){
this.annotation.beginEdit(ta.CommandTypes.Modify);
};
this.endEdit=function(){
this.annotation.endEdit();
};
this.zoom=function(_5){
if(this.shape){
var rs=Math.floor(_3/_5);
var _6=dojox.gfx.renderer=="vml"?1:1/_5;
this.shape.setShape({x:an[id].x-rs,y:an[id].y-rs,width:rs*2,height:rs*2}).setStroke({color:"black",width:_6});
}
};
this.setBinding=function(pt){
an[id]={x:an[id].x+pt.dx,y:an[id].y+pt.dy};
an.draw();
an.drawBBox();
};
this.setUndo=function(){
an.setUndo();
};
this.enable=function(){
if(!an.shape){
return;
}
an.figure._add(this);
_4={x:an[id].x-_3,y:an[id].y-_3,width:_3*2,height:_3*2};
this.shape=an.shape.createRect(_4).setFill([255,255,255,0.35]);
this.shape.getEventSource().setAttribute("id",_2._key);
this.shape.getEventSource().setAttribute("shape-rendering","crispEdges");
this.zoom(an.figure.zoomFactor);
};
this.disable=function(){
an.figure._remove(this);
if(an.shape){
an.shape.remove(this.shape);
}
this.shape=null;
_4=null;
};
};
ta.Anchor.count=0;
})();
}
