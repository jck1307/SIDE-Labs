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


if(!dojo._hasResource["dojox.gfx3d._base"]){
dojo._hasResource["dojox.gfx3d._base"]=true;
dojo.provide("dojox.gfx3d._base");
dojo.mixin(dojox.gfx3d,{defaultEdges:{type:"edges",style:null,points:[]},defaultTriangles:{type:"triangles",style:null,points:[]},defaultQuads:{type:"quads",style:null,points:[]},defaultOrbit:{type:"orbit",center:{x:0,y:0,z:0},radius:50},defaultPath3d:{type:"path3d",path:[]},defaultPolygon:{type:"polygon",path:[]},defaultCube:{type:"cube",bottom:{x:0,y:0,z:0},top:{x:100,y:100,z:100}},defaultCylinder:{type:"cylinder",center:{x:0,y:0,z:0},height:100,radius:50}});
}
