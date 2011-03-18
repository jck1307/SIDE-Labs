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


if(!dojo._hasResource["dojox.io.xhrWindowNamePlugin"]){
dojo._hasResource["dojox.io.xhrWindowNamePlugin"]=true;
dojo.provide("dojox.io.xhrWindowNamePlugin");
dojo.require("dojox.io.xhrPlugins");
dojo.require("dojox.io.windowName");
dojo.require("dojox.io.httpParse");
dojo.require("dojox.secure.capability");
dojox.io.xhrWindowNamePlugin=function(_1,_2,_3){
dojox.io.xhrPlugins.register("windowName",function(_4,_5){
return _5.sync!==true&&(_4=="GET"||_4=="POST"||_2)&&(_5.url.substring(0,_1.length)==_1);
},function(_6,_7,_8){
var _9=dojox.io.windowName.send;
var _a=_7.load;
_7.load=undefined;
var _b=(_2?_2(_9,true):_9)(_6,_7,_8);
_b.addCallback(function(_c){
var _d=_b.ioArgs;
_d.xhr={getResponseHeader:function(_e){
return dojo.queryToObject(_d.hash.match(/[^#]*$/)[0])[_e];
}};
if(_d.handleAs=="json"){
if(!_3){
dojox.secure.capability.validate(_c,["Date"],{});
}
return dojo.fromJson(_c);
}
return dojo._contentHandlers[_d.handleAs||"text"]({responseText:_c});
});
_7.load=_a;
if(_a){
_b.addCallback(_a);
}
return _b;
});
};
}
