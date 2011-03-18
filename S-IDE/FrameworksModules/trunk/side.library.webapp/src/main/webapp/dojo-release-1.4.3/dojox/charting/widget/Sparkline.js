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


if(!dojo._hasResource["dojox.charting.widget.Sparkline"]){
dojo._hasResource["dojox.charting.widget.Sparkline"]=true;
dojo.provide("dojox.charting.widget.Sparkline");
dojo.require("dojox.charting.widget.Chart2D");
dojo.require("dojox.charting.themes.ET.greys");
(function(){
var d=dojo;
dojo.declare("dojox.charting.widget.Sparkline",dojox.charting.widget.Chart2D,{theme:dojox.charting.themes.ET.greys,margins:{l:0,r:0,t:0,b:0},type:"Lines",valueFn:"Number(x)",store:"",field:"",query:"",queryOptions:"",start:"0",count:"Infinity",sort:"",data:"",name:"default",buildRendering:function(){
var n=this.srcNodeRef;
if(!n.childNodes.length||!d.query("> .axis, > .plot, > .action, > .series",n).length){
var _1=document.createElement("div");
d.attr(_1,{"class":"plot","name":"default","type":this.type});
n.appendChild(_1);
var _2=document.createElement("div");
d.attr(_2,{"class":"series",plot:"default",name:this.name,start:this.start,count:this.count,valueFn:this.valueFn});
d.forEach(["store","field","query","queryOptions","sort","data"],function(i){
if(this[i].length){
d.attr(_2,i,this[i]);
}
},this);
n.appendChild(_2);
}
this.inherited(arguments);
}});
})();
}
