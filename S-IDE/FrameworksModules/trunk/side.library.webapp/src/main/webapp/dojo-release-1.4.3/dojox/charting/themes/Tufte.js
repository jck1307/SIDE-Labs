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


if(!dojo._hasResource["dojox.charting.themes.Tufte"]){
dojo._hasResource["dojox.charting.themes.Tufte"]=true;
dojo.provide("dojox.charting.themes.Tufte");
dojo.require("dojox.charting.Theme");
(function(){
var _1=dojox.charting;
_1.themes.Tufte=new _1.Theme({antiAlias:false,chart:{stroke:null,fill:"inherit"},plotarea:{stroke:null,fill:"transparent"},axis:{stroke:{width:0},line:{width:0},majorTick:{color:"#666666",width:1,length:5},minorTick:{color:"black",width:1,length:2},font:"normal normal normal 8pt Tahoma",fontColor:"#999999"},series:{outline:{width:0,color:"black"},stroke:{width:1,color:"black"},fill:new dojo.Color([59,68,75,0.85]),font:"normal normal normal 7pt Tahoma",fontColor:"#717171"},marker:{stroke:{width:1},fill:"#333",font:"normal normal normal 7pt Tahoma",fontColor:"#000"},colors:[dojo.colorFromHex("#8a8c8f"),dojo.colorFromHex("#4b4b4b"),dojo.colorFromHex("#3b444b"),dojo.colorFromHex("#2e2d30"),dojo.colorFromHex("#000000")]});
})();
}
