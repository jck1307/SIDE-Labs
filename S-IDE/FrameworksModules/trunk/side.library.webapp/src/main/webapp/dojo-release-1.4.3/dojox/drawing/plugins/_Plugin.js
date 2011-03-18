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


if(!dojo._hasResource["dojox.drawing.plugins._Plugin"]){
dojo._hasResource["dojox.drawing.plugins._Plugin"]=true;
dojo.provide("dojox.drawing.plugins._Plugin");
dojox.drawing.plugins._Plugin=dojox.drawing.util.oo.declare(function(_1){
this._cons=[];
dojo.mixin(this,_1);
if(this.button&&this.onClick){
this.connect(this.button,"onClick",this,"onClick");
}
},{util:null,keys:null,mouse:null,drawing:null,stencils:null,anchors:null,canvas:null,node:null,button:null,type:"dojox.drawing.plugins._Plugin",connect:function(){
this._cons.push(dojo.connect.apply(dojo,arguments));
},disconnect:function(_2){
if(!_2){
return;
}
if(!dojo.isArray(_2)){
_2=[_2];
}
dojo.forEach(_2,dojo.disconnect,dojo);
}});
}
