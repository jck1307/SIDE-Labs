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


if(!dojo._hasResource["dojox.io.xhrScriptPlugin"]){
dojo._hasResource["dojox.io.xhrScriptPlugin"]=true;
dojo.provide("dojox.io.xhrScriptPlugin");
dojo.require("dojox.io.xhrPlugins");
dojo.require("dojo.io.script");
dojo.require("dojox.io.scriptFrame");
dojox.io.xhrScriptPlugin=function(_1,_2,_3){
dojox.io.xhrPlugins.register("script",function(_4,_5){
return _5.sync!==true&&(_4=="GET"||_3)&&(_5.url.substring(0,_1.length)==_1);
},function(_6,_7,_8){
var _9=function(){
_7.callbackParamName=_2;
if(dojo.body()){
_7.frameDoc="frame"+Math.random();
}
return dojo.io.script.get(_7);
};
return (_3?_3(_9,true):_9)(_6,_7,_8);
});
};
}
