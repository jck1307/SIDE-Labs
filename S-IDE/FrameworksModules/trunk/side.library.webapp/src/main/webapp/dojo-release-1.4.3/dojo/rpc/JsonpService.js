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


if(!dojo._hasResource["dojo.rpc.JsonpService"]){
dojo._hasResource["dojo.rpc.JsonpService"]=true;
dojo.provide("dojo.rpc.JsonpService");
dojo.require("dojo.rpc.RpcService");
dojo.require("dojo.io.script");
dojo.declare("dojo.rpc.JsonpService",dojo.rpc.RpcService,{constructor:function(_1,_2){
if(this.required){
if(_2){
dojo.mixin(this.required,_2);
}
dojo.forEach(this.required,function(_3){
if(_3==""||_3==undefined){
throw new Error("Required Service Argument not found: "+_3);
}
});
}
},strictArgChecks:false,bind:function(_4,_5,_6,_7){
var _8=dojo.io.script.get({url:_7||this.serviceUrl,callbackParamName:this.callbackParamName||"callback",content:this.createRequest(_5),timeout:this.timeout,handleAs:"json",preventCache:true});
_8.addCallbacks(this.resultCallback(_6),this.errorCallback(_6));
},createRequest:function(_9){
var _a=(dojo.isArrayLike(_9)&&_9.length==1)?_9[0]:{};
dojo.mixin(_a,this.required);
return _a;
}});
}
