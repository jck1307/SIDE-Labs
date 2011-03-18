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


if(!dojo._hasResource["dojo.dnd.TimedMoveable"]){
dojo._hasResource["dojo.dnd.TimedMoveable"]=true;
dojo.provide("dojo.dnd.TimedMoveable");
dojo.require("dojo.dnd.Moveable");
(function(){
var _1=dojo.dnd.Moveable.prototype.onMove;
dojo.declare("dojo.dnd.TimedMoveable",dojo.dnd.Moveable,{timeout:40,constructor:function(_2,_3){
if(!_3){
_3={};
}
if(_3.timeout&&typeof _3.timeout=="number"&&_3.timeout>=0){
this.timeout=_3.timeout;
}
},markupFactory:function(_4,_5){
return new dojo.dnd.TimedMoveable(_5,_4);
},onMoveStop:function(_6){
if(_6._timer){
clearTimeout(_6._timer);
_1.call(this,_6,_6._leftTop);
}
dojo.dnd.Moveable.prototype.onMoveStop.apply(this,arguments);
},onMove:function(_7,_8){
_7._leftTop=_8;
if(!_7._timer){
var _9=this;
_7._timer=setTimeout(function(){
_7._timer=null;
_1.call(_9,_7,_7._leftTop);
},this.timeout);
}
}});
})();
}
