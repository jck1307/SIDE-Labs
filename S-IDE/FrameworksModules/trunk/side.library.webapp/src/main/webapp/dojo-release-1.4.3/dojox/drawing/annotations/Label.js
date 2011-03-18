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


if(!dojo._hasResource["dojox.drawing.annotations.Label"]){
dojo._hasResource["dojox.drawing.annotations.Label"]=true;
dojo.provide("dojox.drawing.annotations.Label");
dojo.require("dojox.drawing.stencil.Text");
dojox.drawing.annotations.Label=dojox.drawing.util.oo.declare(dojox.drawing.stencil.Text,function(_1){
this.master=_1.stencil;
this.labelPosition=_1.labelPosition||"BR";
if(dojo.isFunction(this.labelPosition)){
this.setLabel=this.setLabelCustom;
}
this.setLabel(_1.text||"");
this.connect(this.master,"onTransform",this,"setLabel");
this.connect(this.master,"destroy",this,"destroy");
if(this.style.labelSameColor){
this.connect(this.master,"attr",this,"beforeAttr");
}
},{_align:"start",setLabelCustom:function(_2){
var d=dojo.hitch(this.master,this.labelPosition)();
this.setData({x:d.x,y:d.y,width:d.w||this.style.text.minWidth,height:d.h||this._lineHeight});
if(_2&&!_2.split){
_2=null;
}
this.render(_2);
},setLabel:function(_3){
var x,y,_4=this.master.getBounds();
if(/B/.test(this.labelPosition)){
y=_4.y2-this._lineHeight;
}else{
y=_4.y1;
}
if(/R/.test(this.labelPosition)){
x=_4.x2;
}else{
y=_4.y1;
this._align="end";
}
if(!this.labelWidth||(_3&&_3.split&&_3!=this._text)){
this.setData({x:x,y:y,height:this._lineHeight,width:this.style.text.minWidth});
this.labelWidth=this.style.text.minWidth;
this.render(_3);
}else{
this.setData({x:x,y:y,height:this.data.height,width:this.data.width});
this.render();
}
},beforeAttr:function(_5,_6){
if(_6!==undefined){
var k=_5;
_5={};
_5[k]=_6;
}
delete _5.x;
delete _5.y;
delete _5.width;
delete _5.height;
this.attr(_5);
!this.created&&this.render();
}});
}
