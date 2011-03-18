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


if(!dojo._hasResource["dojo.robotx"]){
dojo._hasResource["dojo.robotx"]=true;
dojo.provide("dojo.robotx");
dojo.require("dojo.robot");
dojo.experimental("dojo.robotx");
(function(){
doh.robot._runsemaphore.lock.push("dojo.robotx.lock");
var _1=document.getElementById("robotapplication");
var _2=dojo.connect(doh,"_groupStarted",function(){
dojo.disconnect(_2);
_1.style.visibility="visible";
});
var _3=function(){
doh.robot._updateDocument();
_3=null;
var _4=(document.compatMode=="BackCompat")?document.body:document.documentElement;
var _5=document.getElementById("robotconsole").offsetHeight;
if(_5){
_1.style.height=(_4.clientHeight-_5)+"px";
}
doh.run();
};
var _6=function(){
if(_3){
_3();
}
var _7=dojo.connect(dojo.body(),"onunload",function(){
dojo.global=window;
dojo.doc=document;
dojo.disconnect(_7);
});
};
dojo.config.debugContainerId="robotconsole";
dojo.config.debugHeight=dojo.config.debugHeight||200;
document.write("<div id=\"robotconsole\" style=\"position:absolute;left:0px;bottom:0px;width:100%;\"></div>");
_1=document.createElement("iframe");
_1.setAttribute("ALLOWTRANSPARENCY","true");
_1.scrolling=dojo.isIE?"yes":"auto";
dojo.style(_1,{visibility:"hidden",border:"0px none",padding:"0px",margin:"0px",position:"absolute",left:"0px",top:"0px",width:"100%",height:"100%"});
if(_1["attachEvent"]!==undefined){
_1.attachEvent("onload",_6);
}else{
dojo.connect(_1,"onload",_6);
}
dojo.mixin(doh.robot,{_updateDocument:function(){
dojo.setContext(_1.contentWindow,_1.contentWindow.document);
var _8=dojo.global;
if(_8["dojo"]){
dojo._topics=_8.dojo._topics;
}
},initRobot:function(_9){
_1.src=_9;
dojo.addOnLoad(function(){
var _a={overflow:dojo.isWebKit?"hidden":"visible",margin:"0px",borderWidth:"0px",height:"100%",width:"100%"};
dojo.style(document.documentElement,_a);
dojo.style(document.body,_a);
document.body.appendChild(_1);
var _b=document.createElement("base");
_b.href=_9;
document.getElementsByTagName("head")[0].appendChild(_b);
});
},waitForPageToLoad:function(_c){
var d=new doh.Deferred();
_3=function(){
_3=null;
doh.robot._updateDocument();
d.callback(true);
};
_c();
return d;
}});
})();
}
