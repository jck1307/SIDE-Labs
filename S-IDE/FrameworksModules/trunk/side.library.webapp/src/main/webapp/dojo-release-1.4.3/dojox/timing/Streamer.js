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


if(!dojo._hasResource["dojox.timing.Streamer"]){
dojo._hasResource["dojox.timing.Streamer"]=true;
dojo.provide("dojox.timing.Streamer");
dojo.require("dojox.timing._base");
dojox.timing.Streamer=function(_1,_2,_3,_4,_5){
var _6=this;
var _7=[];
this.interval=_3||1000;
this.minimumSize=_4||10;
this.inputFunction=_1||function(q){
};
this.outputFunction=_2||function(_8){
};
var _9=new dojox.timing.Timer(this.interval);
var _a=function(){
_6.onTick(_6);
if(_7.length<_6.minimumSize){
_6.inputFunction(_7);
}
var _b=_7.shift();
while(typeof (_b)=="undefined"&&_7.length>0){
_b=_7.shift();
}
if(typeof (_b)=="undefined"){
_6.stop();
return;
}
_6.outputFunction(_b);
};
this.setInterval=function(ms){
this.interval=ms;
_9.setInterval(ms);
};
this.onTick=function(_c){
};
this.start=function(){
if(typeof (this.inputFunction)=="function"&&typeof (this.outputFunction)=="function"){
_9.start();
return;
}
throw new Error("You cannot start a Streamer without an input and an output function.");
};
this.onStart=function(){
};
this.stop=function(){
_9.stop();
};
this.onStop=function(){
};
_9.onTick=this.tick;
_9.onStart=this.onStart;
_9.onStop=this.onStop;
if(_5){
_7.concat(_5);
}
};
}
