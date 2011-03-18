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


if(!dojo._hasResource["dojo.data.api.Identity"]){
dojo._hasResource["dojo.data.api.Identity"]=true;
dojo.provide("dojo.data.api.Identity");
dojo.require("dojo.data.api.Read");
dojo.declare("dojo.data.api.Identity",dojo.data.api.Read,{getFeatures:function(){
return {"dojo.data.api.Read":true,"dojo.data.api.Identity":true};
},getIdentity:function(_1){
throw new Error("Unimplemented API: dojo.data.api.Identity.getIdentity");
var _2=null;
return _2;
},getIdentityAttributes:function(_3){
throw new Error("Unimplemented API: dojo.data.api.Identity.getIdentityAttributes");
return null;
},fetchItemByIdentity:function(_4){
if(!this.isItemLoaded(_4.item)){
throw new Error("Unimplemented API: dojo.data.api.Identity.fetchItemByIdentity");
}
}});
}
