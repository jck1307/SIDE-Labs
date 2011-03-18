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


if(!dojo._hasResource["dojo.DeferredList"]){
dojo._hasResource["dojo.DeferredList"]=true;
dojo.provide("dojo.DeferredList");
dojo.declare("dojo.DeferredList",dojo.Deferred,{constructor:function(_1,_2,_3,_4,_5){
this.list=_1;
this.resultList=new Array(this.list.length);
this.chain=[];
this.id=this._nextId();
this.fired=-1;
this.paused=0;
this.results=[null,null];
this.canceller=_5;
this.silentlyCancelled=false;
if(this.list.length===0&&!_2){
this.callback(this.resultList);
}
this.finishedCount=0;
this.fireOnOneCallback=_2;
this.fireOnOneErrback=_3;
this.consumeErrors=_4;
dojo.forEach(this.list,function(d,_6){
d.addCallback(this,function(r){
this._cbDeferred(_6,true,r);
return r;
});
d.addErrback(this,function(r){
this._cbDeferred(_6,false,r);
return r;
});
},this);
},_cbDeferred:function(_7,_8,_9){
this.resultList[_7]=[_8,_9];
this.finishedCount+=1;
if(this.fired!==0){
if(_8&&this.fireOnOneCallback){
this.callback([_7,_9]);
}else{
if(!_8&&this.fireOnOneErrback){
this.errback(_9);
}else{
if(this.finishedCount==this.list.length){
this.callback(this.resultList);
}
}
}
}
if(!_8&&this.consumeErrors){
_9=null;
}
return _9;
},gatherResults:function(_a){
var d=new dojo.DeferredList(_a,false,true,false);
d.addCallback(function(_b){
var _c=[];
dojo.forEach(_b,function(_d){
_c.push(_d[1]);
});
return _c;
});
return d;
}});
}
