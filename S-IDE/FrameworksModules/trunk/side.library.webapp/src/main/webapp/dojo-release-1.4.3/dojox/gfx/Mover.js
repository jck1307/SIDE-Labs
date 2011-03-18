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


if(!dojo._hasResource["dojox.gfx.Mover"]){
dojo._hasResource["dojox.gfx.Mover"]=true;
dojo.provide("dojox.gfx.Mover");
dojo.declare("dojox.gfx.Mover",null,{constructor:function(_1,e,_2){
this.shape=_1;
this.lastX=e.clientX;
this.lastY=e.clientY;
var h=this.host=_2,d=document,_3=dojo.connect(d,"onmousemove",this,"onFirstMove");
this.events=[dojo.connect(d,"onmousemove",this,"onMouseMove"),dojo.connect(d,"onmouseup",this,"destroy"),dojo.connect(d,"ondragstart",dojo,"stopEvent"),dojo.connect(d,"onselectstart",dojo,"stopEvent"),_3];
if(h&&h.onMoveStart){
h.onMoveStart(this);
}
},onMouseMove:function(e){
var x=e.clientX;
var y=e.clientY;
this.host.onMove(this,{dx:x-this.lastX,dy:y-this.lastY});
this.lastX=x;
this.lastY=y;
dojo.stopEvent(e);
},onFirstMove:function(){
this.host.onFirstMove(this);
dojo.disconnect(this.events.pop());
},destroy:function(){
dojo.forEach(this.events,dojo.disconnect);
var h=this.host;
if(h&&h.onMoveStop){
h.onMoveStop(this);
}
this.events=this.shape=null;
}});
}
