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


if(!dojo._hasResource["dojox.image._base"]){
dojo._hasResource["dojox.image._base"]=true;
dojo.provide("dojox.image._base");
(function(d){
var _1;
dojox.image.preload=function(_2){
if(!_1){
_1=d.create("div",{style:{position:"absolute",top:"-9999px",height:"1px",overflow:"hidden"}},d.body());
}
return d.map(_2,function(_3){
return d.create("img",{src:_3},_1);
});
};
if(d.config.preloadImages){
d.addOnLoad(function(){
dojox.image.preload(d.config.preloadImages);
});
}
})(dojo);
}
