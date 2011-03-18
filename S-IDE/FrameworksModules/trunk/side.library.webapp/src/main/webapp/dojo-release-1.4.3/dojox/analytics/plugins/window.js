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


if(!dojo._hasResource["dojox.analytics.plugins.window"]){
dojo._hasResource["dojox.analytics.plugins.window"]=true;
dojo.require("dojox.analytics._base");
dojo.provide("dojox.analytics.plugins.window");
dojox.analytics.plugins.window=new (function(){
this.addData=dojo.hitch(dojox.analytics,"addData","window");
this.windowConnects=dojo.config["windowConnects"]||["open","onerror"];
for(var i=0;i<this.windowConnects.length;i++){
dojo.connect(window,this.windowConnects[i],dojo.hitch(this,"addData",this.windowConnects[i]));
}
dojo.addOnLoad(dojo.hitch(this,function(){
var _1={};
for(var i in window){
if(dojo.isObject(window[i])){
switch(i){
case "location":
case "console":
_1[i]=window[i];
break;
default:
break;
}
}else{
_1[i]=window[i];
}
}
this.addData(_1);
}));
})();
}
