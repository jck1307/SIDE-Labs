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


if(!dojo._hasResource["dojox.drawing.ui.Tooltip"]){
dojo._hasResource["dojox.drawing.ui.Tooltip"]=true;
dojo.provide("dojox.drawing.ui.Tooltip");
dojo.require("dojox.drawing.plugins._Plugin");
(function(){
var _1=null;
var _2=dojox.drawing.util.oo.declare(dojox.drawing.plugins._Plugin,function(_3){
this.createDom();
},{show:function(_4,_5){
this.domNode.innerHTML=_5;
var dx=30,dy=30;
var px=_4.data.x+_4.data.width;
var py=_4.data.y+_4.data.height;
var x=px+this.mouse.origin.x+dx;
var y=py+this.mouse.origin.y+dx;
dojo.style(this.domNode,{display:"inline",left:x+"px",top:y+"px"});
var _6=dojo.marginBox(this.domNode);
this.createShape(x-this.mouse.origin.x,y-this.mouse.origin.y,_6.w,_6.h);
},createShape:function(x,y,w,h){
this.balloon&&this.balloon.destroy();
var r=5,x2=x+w,y2=y+h,_7=[];
var _8=function(){
for(var i=0;i<arguments.length;i++){
_7.push(arguments[i]);
}
};
_8({x:x,y:y+5},{t:"Q",x:x,y:y},{x:x+r,y:y});
_8({t:"L",x:x2-r,y:y});
_8({t:"Q",x:x2,y:y},{x:x2,y:y+r});
_8({t:"L",x:x2,y:y2-r});
_8({t:"Q",x:x2,y:y2},{x:x2-r,y:y2});
_8({t:"L",x:x+r,y:y2});
_8({t:"Q",x:x,y:y2},{x:x,y:y2-r});
_8({t:"L",x:x,y:y+r});
this.balloon=this.drawing.addUI("path",{points:_7});
},createDom:function(){
this.domNode=dojo.create("span",{"class":"drawingTooltip"},document.body);
dojo.style(this.domNode,{display:"none",position:"absolute"});
}});
dojox.drawing.ui.Tooltip=dojox.drawing.util.oo.declare(dojox.drawing.plugins._Plugin,function(_9){
if(!_1){
_1=new _2(_9);
}
if(_9.stencil){
}else{
if(this.button){
this.connect(this.button,"onOver",this,"onOver");
this.connect(this.button,"onOut",this,"onOut");
}
}
},{width:300,height:200,onOver:function(){
_1.show(this.button,this.data.text);
},onOut:function(){
}});
dojox.drawing.register({name:"dojox.drawing.ui.Tooltip"},"stencil");
})();
}
