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


if(!dojo._hasResource["dojox.charting.themes.PlotKit.red"]){
dojo._hasResource["dojox.charting.themes.PlotKit.red"]=true;
dojo.provide("dojox.charting.themes.PlotKit.red");
dojo.require("dojox.charting.Theme");
(function(){
var _1=dojox.charting;
_1.themes.PlotKit.red=new _1.Theme({chart:{stroke:null,fill:"white"},plotarea:{stroke:null,fill:"#f5e6e6"},axis:{stroke:{color:"#fff",width:2},line:{color:"#fff",width:1},majorTick:{color:"#fff",width:2,length:12},minorTick:{color:"#fff",width:1,length:8},font:"normal normal normal 8pt Tahoma",fontColor:"#999"},series:{outline:{width:1,color:"#fff"},stroke:{width:2,color:"#666"},fill:new dojo.Color([102,102,102,0.8]),font:"normal normal normal 7pt Tahoma",fontColor:"#000"},marker:{stroke:{width:2},fill:"#333",font:"normal normal normal 7pt Tahoma",fontColor:"#000"},colors:[]});
_1.themes.PlotKit.red.defineColors({hue:1,saturation:60,low:40,high:88});
})();
}
