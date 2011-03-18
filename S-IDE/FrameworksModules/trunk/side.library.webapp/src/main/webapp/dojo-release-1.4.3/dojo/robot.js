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


if(!dojo._hasResource["dojo.robot"]){
dojo._hasResource["dojo.robot"]=true;
dojo.provide("dojo.robot");
dojo.experimental("dojo.robot");
dojo.require("doh.robot");
(function(){
dojo.mixin(doh.robot,{_resolveNode:function(n){
if(typeof n=="function"){
n=n();
}
return n?dojo.byId(n):null;
},_scrollIntoView:function(_1){
_1.scrollIntoView(false);
},_position:function(n){
return dojo.position(n,false);
},scrollIntoView:function(_2,_3){
doh.robot.sequence(function(){
doh.robot._scrollIntoView(doh.robot._resolveNode(_2));
},_3);
},mouseMoveAt:function(_4,_5,_6,_7,_8){
doh.robot._assertRobot();
_6=_6||100;
this.sequence(function(){
_4=doh.robot._resolveNode(_4);
doh.robot._scrollIntoView(_4);
var _9=doh.robot._position(_4);
if(_8===undefined){
_7=_9.w/2;
_8=_9.h/2;
}
var x=_9.x+_7;
var y=_9.y+_8;
doh.robot._mouseMove(x,y,false,_6);
},_5,_6);
}});
})();
}
