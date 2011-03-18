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


if(!dojo._hasResource["dojox.drawing._base"]){
dojo._hasResource["dojox.drawing._base"]=true;
dojo.provide("dojox.drawing._base");
dojo.experimental("dojox.drawing");
dojo.require("dojox.drawing.manager._registry");
dojo.require("dojox.gfx");
dojo.require("dojox.drawing.Drawing");
dojo.require("dojox.drawing.util.oo");
dojo.require("dojox.drawing.util.common");
dojo.require("dojox.drawing.defaults");
dojo.require("dojox.drawing.manager.Canvas");
dojo.require("dojox.drawing.manager.Undo");
dojo.require("dojox.drawing.manager.keys");
dojo.require("dojox.drawing.manager.Mouse");
dojo.require("dojox.drawing.manager.Stencil");
dojo.require("dojox.drawing.manager.StencilUI");
dojo.require("dojox.drawing.manager.Anchors");
dojo.require("dojox.drawing.stencil._Base");
dojo.require("dojox.drawing.stencil.Line");
dojo.require("dojox.drawing.stencil.Rect");
dojo.require("dojox.drawing.stencil.Ellipse");
dojo.require("dojox.drawing.stencil.Path");
dojo.require("dojox.drawing.stencil.Text");
dojo.require("dojox.drawing.stencil.Image");
dojo.require("dojox.drawing.annotations.Label");
dojo.require("dojox.drawing.annotations.Angle");
dojo.require("dojox.drawing.annotations.Arrow");
dojo.require("dojox.drawing.annotations.BoxShadow");
}
