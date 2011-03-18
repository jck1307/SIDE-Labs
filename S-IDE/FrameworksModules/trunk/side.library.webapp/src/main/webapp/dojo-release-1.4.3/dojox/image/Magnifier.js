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


if(!dojo._hasResource["dojox.image.Magnifier"]){
dojo._hasResource["dojox.image.Magnifier"]=true;
dojo.provide("dojox.image.Magnifier");
dojo.require("dojox.gfx");
dojo.require("dojox.image.MagnifierLite");
dojo.declare("dojox.image.Magnifier",dojox.image.MagnifierLite,{_createGlass:function(){
this.glassNode=dojo.create("div",{style:{height:this.glassSize+"px",width:this.glassSize+"px"},"className":"glassNode"},dojo.body());
this.surfaceNode=dojo.create("div",null,this.glassNode);
this.surface=dojox.gfx.createSurface(this.surfaceNode,this.glassSize,this.glassSize);
this.img=this.surface.createImage({src:this.domNode.src,width:this._zoomSize.w,height:this._zoomSize.h});
},_placeGlass:function(e){
var x=e.pageX-2,y=e.pageY-2,_1=this.offset.x+this.offset.w+2,_2=this.offset.y+this.offset.h+2;
if(x<this.offset.x||y<this.offset.y||x>_1||y>_2){
this._hideGlass();
}else{
this.inherited(arguments);
}
},_setImage:function(e){
var _3=(e.pageX-this.offset.l)/this.offset.w,_4=(e.pageY-this.offset.t)/this.offset.h,x=(this._zoomSize.w*_3*-1)+(this.glassSize*_3),y=(this._zoomSize.h*_4*-1)+(this.glassSize*_4);
this.img.setShape({x:x,y:y});
}});
}
