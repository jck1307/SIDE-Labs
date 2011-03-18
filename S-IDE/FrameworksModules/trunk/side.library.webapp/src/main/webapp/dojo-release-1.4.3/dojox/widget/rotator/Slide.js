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


if(!dojo._hasResource["dojox.widget.rotator.Slide"]){
dojo._hasResource["dojox.widget.rotator.Slide"]=true;
dojo.provide("dojox.widget.rotator.Slide");
(function(d){
var _1=0,_2=1,UP=2,_3=3;
function _4(_5,_6){
var _7=_6.node=_6.next.node,r=_6.rotatorBox,m=_5%2,s=(m?r.w:r.h)*(_5<2?-1:1);
d.style(_7,{display:"",zIndex:(d.style(_6.current.node,"zIndex")||1)+1});
if(!_6.properties){
_6.properties={};
}
_6.properties[m?"left":"top"]={start:s,end:0};
return d.animateProperty(_6);
};
d.mixin(dojox.widget.rotator,{slideDown:function(_8){
return _4(_1,_8);
},slideRight:function(_9){
return _4(_2,_9);
},slideUp:function(_a){
return _4(UP,_a);
},slideLeft:function(_b){
return _4(_3,_b);
}});
})(dojo);
}
