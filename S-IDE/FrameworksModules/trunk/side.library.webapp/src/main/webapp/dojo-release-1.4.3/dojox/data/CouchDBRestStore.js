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


if(!dojo._hasResource["dojox.data.CouchDBRestStore"]){
dojo._hasResource["dojox.data.CouchDBRestStore"]=true;
dojo.provide("dojox.data.CouchDBRestStore");
dojo.require("dojox.data.JsonRestStore");
dojo.declare("dojox.data.CouchDBRestStore",dojox.data.JsonRestStore,{save:function(_1){
var _2=this.inherited(arguments);
var _3=this.service.servicePath;
for(var i=0;i<_2.length;i++){
(function(_4,_5){
_5.addCallback(function(_6){
if(_6){
_4.__id=_3+_6.id;
_4._rev=_6.rev;
}
return _6;
});
})(_2[i].content,_2[i].deferred);
}
},fetch:function(_7){
_7.query=_7.query||"_all_docs?";
if(_7.start){
_7.query=(_7.query?(_7.query+"&"):"")+"startkey="+_7.start;
delete _7.start;
}
if(_7.count){
_7.query=(_7.query?(_7.query+"&"):"")+"limit="+_7.count;
delete _7.count;
}
return this.inherited(arguments);
},_processResults:function(_8){
var _9=_8.rows;
if(_9){
var _a=this.service.servicePath;
var _b=this;
for(var i=0;i<_9.length;i++){
var _c=_9[i].value;
_c.__id=_a+_9[i].id;
_c._id=_9[i].id;
_c._loadObject=dojox.rpc.JsonRest._loader;
_9[i]=_c;
}
return {totalCount:_8.total_rows,items:_8.rows};
}else{
return {items:_8};
}
}});
dojox.data.CouchDBRestStore.getStores=function(_d){
var _e=dojo.xhrGet({url:_d+"_all_dbs",handleAs:"json",sync:true});
var _f={};
_e.addBoth(function(dbs){
for(var i=0;i<dbs.length;i++){
_f[dbs[i]]=new dojox.data.CouchDBRestStore({target:_d+dbs[i],idAttribute:"_id"});
}
return _f;
});
return _f;
};
}
