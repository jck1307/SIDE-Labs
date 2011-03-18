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


if(!dojo._hasResource["dojox.data.WikipediaStore"]){
dojo._hasResource["dojox.data.WikipediaStore"]=true;
dojo.provide("dojox.data.WikipediaStore");
dojo.require("dojo.io.script");
dojo.require("dojox.rpc.Service");
dojo.require("dojox.data.ServiceStore");
dojo.experimental("dojox.data.WikipediaStore");
dojo.declare("dojox.data.WikipediaStore",dojox.data.ServiceStore,{constructor:function(_1){
if(_1&&_1.service){
this.service=_1.service;
}else{
var _2=new dojox.rpc.Service(dojo.moduleUrl("dojox.rpc.SMDLibrary","wikipedia.smd"));
this.service=_2.query;
}
this.idAttribute=this.labelAttribute="title";
},fetch:function(_3){
var rq=dojo.mixin({},_3.query);
if(rq&&(!rq.action||rq.action==="parse")){
rq.action="parse";
rq.page=rq.title;
delete rq.title;
}else{
if(rq.action==="query"){
rq.list="search";
rq.srwhat="text";
rq.srsearch=rq.text;
if(_3.start){
rq.sroffset=_3.start-1;
}
if(_3.count){
rq.srlimit=_3.count>=500?500:_3.count;
}
delete rq.text;
}
}
_3.query=rq;
return this.inherited(arguments);
},_processResults:function(_4,_5){
if(_4.parse){
_4.parse.title=dojo.queryToObject(_5.ioArgs.url.split("?")[1]).page;
_4=[_4.parse];
}else{
if(_4.query&&_4.query.search){
_4=_4.query.search;
var _6=this;
for(var i in _4){
_4[i]._loadObject=function(_7){
_6.fetch({query:{action:"parse",title:this.title},onItem:_7});
delete this._loadObject;
};
}
}
}
return this.inherited(arguments);
}});
}
