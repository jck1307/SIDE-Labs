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


if(!dojo._hasResource["dojox.widget.CalendarFx"]){
dojo._hasResource["dojox.widget.CalendarFx"]=true;
dojo.provide("dojox.widget.CalendarFx");
dojo.require("dojox.widget.FisheyeLite");
dojo.declare("dojox.widget._FisheyeFX",null,{addFx:function(_1,_2){
dojo.query(_1,_2).forEach(function(_3){
new dojox.widget.FisheyeLite({properties:{fontSize:1.1}},_3);
});
}});
dojo.declare("dojox.widget.CalendarFisheye",[dojox.widget.Calendar,dojox.widget._FisheyeFX],{});
}
