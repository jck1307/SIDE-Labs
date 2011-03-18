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


if(!dojo._hasResource["dojox.drawing.manager.StencilUI"]){
dojo._hasResource["dojox.drawing.manager.StencilUI"]=true;
dojo.provide("dojox.drawing.manager.StencilUI");
(function(){
var _1,_2;
dojox.drawing.manager.StencilUI=dojox.drawing.util.oo.declare(function(_3){
_1=_3.surface;
this.canvas=_3.canvas;
this.defaults=dojox.drawing.defaults.copy();
this.mouse=_3.mouse;
this.keys=_3.keys;
this._mouseHandle=this.mouse.register(this);
this.stencils={};
},{register:function(_4){
this.stencils[_4.id]=_4;
return _4;
},onUiDown:function(_5){
if(!this._isStencil(_5)){
return;
}
this.stencils[_5.id].onDown(_5);
},onUiUp:function(_6){
if(!this._isStencil(_6)){
return;
}
this.stencils[_6.id].onUp(_6);
},onOver:function(_7){
if(!this._isStencil(_7)){
return;
}
this.stencils[_7.id].onOver(_7);
},onOut:function(_8){
if(!this._isStencil(_8)){
return;
}
this.stencils[_8.id].onOut(_8);
},_isStencil:function(_9){
return !!_9.id&&!!this.stencils[_9.id]&&this.stencils[_9.id].type=="drawing.library.UI.Button";
}});
})();
}
