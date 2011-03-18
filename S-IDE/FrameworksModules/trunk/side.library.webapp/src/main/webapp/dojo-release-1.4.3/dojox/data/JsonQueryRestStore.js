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


if(!dojo._hasResource["dojox.data.JsonQueryRestStore"]){
dojo._hasResource["dojox.data.JsonQueryRestStore"]=true;
dojo.provide("dojox.data.JsonQueryRestStore");
dojo.require("dojox.data.JsonRestStore");
dojo.require("dojox.data.util.JsonQuery");
dojo.requireIf(!!dojox.data.ClientFilter,"dojox.json.query");
dojo.declare("dojox.data.JsonQueryRestStore",[dojox.data.JsonRestStore,dojox.data.util.JsonQuery],{matchesQuery:function(_1,_2){
return _1.__id&&(_1.__id.indexOf("#")==-1)&&this.inherited(arguments);
}});
}
