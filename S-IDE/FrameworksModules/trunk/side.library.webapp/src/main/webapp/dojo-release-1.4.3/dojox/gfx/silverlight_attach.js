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


dojo.require("dojox.gfx.silverlight");
dojo.experimental("dojox.gfx.silverlight_attach");
(function(){
dojox.gfx.attachNode=function(_1){
return null;
if(!_1){
return null;
}
var s=null;
switch(_1.tagName.toLowerCase()){
case dojox.gfx.Rect.nodeType:
s=new dojox.gfx.Rect(_1);
break;
case dojox.gfx.Ellipse.nodeType:
if(_1.width==_1.height){
s=new dojox.gfx.Circle(_1);
}else{
s=new dojox.gfx.Ellipse(_1);
}
break;
case dojox.gfx.Polyline.nodeType:
s=new dojox.gfx.Polyline(_1);
break;
case dojox.gfx.Path.nodeType:
s=new dojox.gfx.Path(_1);
break;
case dojox.gfx.Line.nodeType:
s=new dojox.gfx.Line(_1);
break;
case dojox.gfx.Image.nodeType:
s=new dojox.gfx.Image(_1);
break;
case dojox.gfx.Text.nodeType:
s=new dojox.gfx.Text(_1);
_2(s);
break;
default:
return null;
}
_3(s);
if(!(s instanceof dojox.gfx.Image)){
_4(s);
_5(s);
}
_6(s);
return s;
};
dojox.gfx.attachSurface=function(_7){
return null;
};
var _4=function(_8){
return null;
};
var _5=function(_9){
return null;
};
var _6=function(_a){
return null;
};
var _2=function(_b){
return null;
};
var _3=function(_c){
return null;
};
})();
