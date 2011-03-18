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


if(!dojo._hasResource["dojox.io.scriptFrame"]){
dojo._hasResource["dojox.io.scriptFrame"]=true;
dojo.provide("dojox.io.scriptFrame");
dojo.require("dojo.io.script");
dojo.require("dojo.io.iframe");
(function(){
var _1=dojo.io.script;
dojox.io.scriptFrame={_waiters:{},_loadedIds:{},_getWaiters:function(_2){
return this._waiters[_2]||(this._waiters[_2]=[]);
},_fixAttachUrl:function(_3){
},_loaded:function(_4){
var _5=this._getWaiters(_4);
this._loadedIds[_4]=true;
this._waiters[_4]=null;
for(var i=0;i<_5.length;i++){
var _6=_5[i];
_6.frameDoc=dojo.io.iframe.doc(dojo.byId(_4));
_1.attach(_6.id,_6.url,_6.frameDoc);
}
}};
var _7=_1._canAttach;
var _8=dojox.io.scriptFrame;
_1._canAttach=function(_9){
var _a=_9.args.frameDoc;
if(_a&&dojo.isString(_a)){
var _b=dojo.byId(_a);
var _c=_8._getWaiters(_a);
if(!_b){
_c.push(_9);
dojo.io.iframe.create(_a,dojox._scopeName+".io.scriptFrame._loaded('"+_a+"');");
}else{
if(_8._loadedIds[_a]){
_9.frameDoc=dojo.io.iframe.doc(_b);
this.attach(_9.id,_9.url,_9.frameDoc);
}else{
_c.push(_9);
}
}
return false;
}else{
return _7.apply(this,arguments);
}
};
})();
}
