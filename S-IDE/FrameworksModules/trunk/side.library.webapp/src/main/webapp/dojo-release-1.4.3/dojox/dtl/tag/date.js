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


if(!dojo._hasResource["dojox.dtl.tag.date"]){
dojo._hasResource["dojox.dtl.tag.date"]=true;
dojo.provide("dojox.dtl.tag.date");
dojo.require("dojox.dtl._base");
dojo.require("dojox.dtl.utils.date");
dojox.dtl.tag.date.NowNode=function(_1,_2){
this._format=_1;
this.format=new dojox.dtl.utils.date.DateFormat(_1);
this.contents=_2;
};
dojo.extend(dojox.dtl.tag.date.NowNode,{render:function(_3,_4){
this.contents.set(this.format.format(new Date()));
return this.contents.render(_3,_4);
},unrender:function(_5,_6){
return this.contents.unrender(_5,_6);
},clone:function(_7){
return new this.constructor(this._format,this.contents.clone(_7));
}});
dojox.dtl.tag.date.now=function(_8,_9){
var _a=_9.split_contents();
if(_a.length!=2){
throw new Error("'now' statement takes one argument");
}
return new dojox.dtl.tag.date.NowNode(_a[1].slice(1,-1),_8.create_text_node());
};
}
