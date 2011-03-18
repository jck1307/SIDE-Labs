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


if(!dojo._hasResource["dojox.charting.scaler.primitive"]){
dojo._hasResource["dojox.charting.scaler.primitive"]=true;
dojo.provide("dojox.charting.scaler.primitive");
dojox.charting.scaler.primitive={buildScaler:function(_1,_2,_3,_4){
return {bounds:{lower:_1,upper:_2,from:_1,to:_2,scale:_3/(_2-_1),span:_3},scaler:dojox.charting.scaler.primitive};
},buildTicks:function(_5,_6){
return {major:[],minor:[],micro:[]};
},getTransformerFromModel:function(_7){
var _8=_7.bounds.from,_9=_7.bounds.scale;
return function(x){
return (x-_8)*_9;
};
},getTransformerFromPlot:function(_a){
var _b=_a.bounds.from,_c=_a.bounds.scale;
return function(x){
return x/_c+_b;
};
}};
}
