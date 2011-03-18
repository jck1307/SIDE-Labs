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


if(!dojo._hasResource["dojo.data.api.Write"]){
dojo._hasResource["dojo.data.api.Write"]=true;
dojo.provide("dojo.data.api.Write");
dojo.require("dojo.data.api.Read");
dojo.declare("dojo.data.api.Write",dojo.data.api.Read,{getFeatures:function(){
return {"dojo.data.api.Read":true,"dojo.data.api.Write":true};
},newItem:function(_1,_2){
var _3;
throw new Error("Unimplemented API: dojo.data.api.Write.newItem");
return _3;
},deleteItem:function(_4){
throw new Error("Unimplemented API: dojo.data.api.Write.deleteItem");
return false;
},setValue:function(_5,_6,_7){
throw new Error("Unimplemented API: dojo.data.api.Write.setValue");
return false;
},setValues:function(_8,_9,_a){
throw new Error("Unimplemented API: dojo.data.api.Write.setValues");
return false;
},unsetAttribute:function(_b,_c){
throw new Error("Unimplemented API: dojo.data.api.Write.clear");
return false;
},save:function(_d){
throw new Error("Unimplemented API: dojo.data.api.Write.save");
},revert:function(){
throw new Error("Unimplemented API: dojo.data.api.Write.revert");
return false;
},isDirty:function(_e){
throw new Error("Unimplemented API: dojo.data.api.Write.isDirty");
return false;
}});
}
