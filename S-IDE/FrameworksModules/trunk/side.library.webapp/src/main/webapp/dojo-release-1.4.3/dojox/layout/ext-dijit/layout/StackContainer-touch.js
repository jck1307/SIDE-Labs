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


if(!dojo._hasResource["dojox.layout.ext-dijit.layout.StackContainer-touch"]){
dojo._hasResource["dojox.layout.ext-dijit.layout.StackContainer-touch"]=true;
dojo.provide("dojox.layout.ext-dijit.layout.StackContainer-touch");
dojo.experimental("dojox.layout.ext-dijit.layout.StackContainer-touch");
dojo.require("dijit.layout.StackContainer");
dojo.connect(dijit.layout.StackContainer.prototype,"postCreate",function(){
this.axis=(this.baseClass=="dijitAccordionContainer")?"Y":"X";
dojo.forEach(["touchstart","touchmove","touchend","touchcancel"],function(p){
this.connect(this.domNode,p,function(e){
switch(e.type){
case "touchmove":
e.preventDefault();
if(this.touchPosition){
var _1=e.touches[0]["page"+this.axis]-this.touchPosition;
if(Math.abs(_1)>100){
if(this.axis=="Y"){
_1*=-1;
}
delete this.touchPosition;
if(_1>0){
!this.selectedChildWidget.isLastChild&&this.forward();
}else{
!this.selectedChildWidget.isFirstChild&&this.back();
}
}
}
break;
case "touchstart":
if(e.touches.length==1){
this.touchPosition=e.touches[0]["page"+this.axis];
break;
}
case "touchend":
case "touchcancel":
delete this.touchPosition;
}
});
},this);
});
}
