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


if(!dojo._hasResource["dojox.widget.rotator.Fade"]){
dojo._hasResource["dojox.widget.rotator.Fade"]=true;
dojo.provide("dojox.widget.rotator.Fade");
dojo.require("dojo.fx");
(function(d){
function _1(_2,_3){
var n=_2.next.node;
d.style(n,{display:"",opacity:0});
_2.node=_2.current.node;
return d.fx[_3]([d.fadeOut(_2),d.fadeIn(d.mixin(_2,{node:n}))]);
};
d.mixin(dojox.widget.rotator,{fade:function(_4){
return _1(_4,"chain");
},crossFade:function(_5){
return _1(_5,"combine");
}});
})(dojo);
}
