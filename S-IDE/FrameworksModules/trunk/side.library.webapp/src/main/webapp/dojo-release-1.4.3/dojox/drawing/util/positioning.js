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


if(!dojo._hasResource["dojox.drawing.util.positioning"]){
dojo._hasResource["dojox.drawing.util.positioning"]=true;
dojo.provide("dojox.drawing.util.positioning");
(function(){
var _1=4;
var _2=20;
dojox.drawing.util.positioning.label=function(_3,_4){
var x=0.5*(_3.x+_4.x);
var y=0.5*(_3.y+_4.y);
var _5=dojox.drawing.util.common.slope(_3,_4);
var _6=_1/Math.sqrt(1+_5*_5);
if(_4.y>_3.y){
_6=-_6;
}
x+=-_6*_5;
y+=_6;
var _7=_4.x<_3.x?"end":"start";
if(_4.y>_3.y){
y-=_2;
}
return {x:x,y:y,foo:"bar",align:_7};
};
dojox.drawing.util.positioning.angle=function(_8,_9){
var x=0.7*_8.x+0.3*_9.x;
var y=0.7*_8.y+0.3*_9.y;
var _a=dojox.drawing.util.common.slope(_8,_9);
var _b=_1/Math.sqrt(1+_a*_a);
if(_9.x<_8.x){
_b=-_b;
}
x+=-_b*_a;
y+=_b;
var _c=_9.y>_8.y?"end":"start";
y+=_9.x>_8.x?0.5*_2:-0.5*_2;
return {x:x,y:y,align:_c};
};
})();
}
