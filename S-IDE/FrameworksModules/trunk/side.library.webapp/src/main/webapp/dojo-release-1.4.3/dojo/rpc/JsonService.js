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


if(!dojo._hasResource["dojo.rpc.JsonService"]){
dojo._hasResource["dojo.rpc.JsonService"]=true;
dojo.provide("dojo.rpc.JsonService");
dojo.require("dojo.rpc.RpcService");
dojo.declare("dojo.rpc.JsonService",dojo.rpc.RpcService,{bustCache:false,contentType:"application/json-rpc",lastSubmissionId:0,callRemote:function(_1,_2){
var _3=new dojo.Deferred();
this.bind(_1,_2,_3);
return _3;
},bind:function(_4,_5,_6,_7){
var _8=dojo.rawXhrPost({url:_7||this.serviceUrl,postData:this.createRequest(_4,_5),contentType:this.contentType,timeout:this.timeout,handleAs:"json-comment-optional"});
_8.addCallbacks(this.resultCallback(_6),this.errorCallback(_6));
},createRequest:function(_9,_a){
var _b={"params":_a,"method":_9,"id":++this.lastSubmissionId};
var _c=dojo.toJson(_b);
return _c;
},parseResults:function(_d){
if(dojo.isObject(_d)){
if("result" in _d){
return _d.result;
}
if("Result" in _d){
return _d.Result;
}
if("ResultSet" in _d){
return _d.ResultSet;
}
}
return _d;
}});
}
