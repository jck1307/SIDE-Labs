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


if(!dojo._hasResource["dojox.charting.Element"]){
dojo._hasResource["dojox.charting.Element"]=true;
dojo.provide("dojox.charting.Element");
dojo.declare("dojox.charting.Element",null,{constructor:function(_1){
this.chart=_1;
this.group=null;
this.htmlElements=[];
this.dirty=true;
},createGroup:function(_2){
if(!_2){
_2=this.chart.surface;
}
if(!this.group){
this.group=_2.createGroup();
}
return this;
},purgeGroup:function(){
this.destroyHtmlElements();
if(this.group){
this.group.clear();
this.group.removeShape();
this.group=null;
}
this.dirty=true;
return this;
},cleanGroup:function(_3){
this.destroyHtmlElements();
if(!_3){
_3=this.chart.surface;
}
if(this.group){
this.group.clear();
}else{
this.group=_3.createGroup();
}
this.dirty=true;
return this;
},destroyHtmlElements:function(){
if(this.htmlElements.length){
dojo.forEach(this.htmlElements,dojo.destroy);
this.htmlElements=[];
}
},destroy:function(){
this.purgeGroup();
}});
}
