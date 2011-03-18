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


if(!dojo._hasResource["dojox.widget.gauge.AnalogNeedleIndicator"]){
dojo._hasResource["dojox.widget.gauge.AnalogNeedleIndicator"]=true;
dojo.provide("dojox.widget.gauge.AnalogNeedleIndicator");
dojo.require("dojox.widget.AnalogGauge");
dojo.experimental("dojox.widget.gauge.AnalogNeedleIndicator");
dojo.declare("dojox.widget.gauge.AnalogNeedleIndicator",[dojox.widget.gauge.AnalogLineIndicator],{_getShapes:function(){
if(!this._gauge){
return null;
}
var x=Math.floor(this.width/2);
var _1=this.width*5;
var _2=(this.width&1);
var _3=[];
var _4={color:this.color,width:1};
if(this.color.type){
_4.color=this.color.colors[0].color;
}
var xy=(Math.sqrt(2)*(x));
_3[0]=this._gauge.surface.createPath().setStroke(_4).setFill(this.color).moveTo(xy,-xy).arcTo((2*x),(2*x),0,0,0,-xy,-xy).lineTo(0,-this.length).closePath();
_3[1]=this._gauge.surface.createCircle({cx:0,cy:0,r:this.width}).setStroke({color:this.color}).setFill(this.color);
return _3;
}});
}
