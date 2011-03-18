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


if(!dojo._hasResource["dojox.timing._base"]){
dojo._hasResource["dojox.timing._base"]=true;
dojo.provide("dojox.timing._base");
dojo.experimental("dojox.timing");
dojox.timing.Timer=function(_1){
this.timer=null;
this.isRunning=false;
this.interval=_1;
this.onStart=null;
this.onStop=null;
};
dojo.extend(dojox.timing.Timer,{onTick:function(){
},setInterval:function(_2){
if(this.isRunning){
window.clearInterval(this.timer);
}
this.interval=_2;
if(this.isRunning){
this.timer=window.setInterval(dojo.hitch(this,"onTick"),this.interval);
}
},start:function(){
if(typeof this.onStart=="function"){
this.onStart();
}
this.isRunning=true;
this.timer=window.setInterval(dojo.hitch(this,"onTick"),this.interval);
},stop:function(){
if(typeof this.onStop=="function"){
this.onStop();
}
this.isRunning=false;
window.clearInterval(this.timer);
}});
}
