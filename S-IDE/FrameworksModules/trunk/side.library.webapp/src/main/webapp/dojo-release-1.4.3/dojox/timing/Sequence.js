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


if(!dojo._hasResource["dojox.timing.Sequence"]){
dojo._hasResource["dojox.timing.Sequence"]=true;
dojo.provide("dojox.timing.Sequence");
dojo.experimental("dojox.timing.Sequence");
dojo.declare("dojox.timing.Sequence",null,{_goOnPause:0,_running:false,constructor:function(){
this._defsResolved=[];
},go:function(_1,_2){
this._running=true;
dojo.forEach(_1,function(_3){
if(_3.repeat>1){
var _4=_3.repeat;
for(var j=0;j<_4;j++){
_3.repeat=1;
this._defsResolved.push(_3);
}
}else{
this._defsResolved.push(_3);
}
},this);
var _5=_1[_1.length-1];
if(_2){
this._defsResolved.push({func:_2});
}
this._defsResolved.push({func:[this.stop,this]});
this._curId=0;
this._go();
},_go:function(){
if(!this._running){
return;
}
var _6=this._defsResolved[this._curId];
this._curId+=1;
function _7(_8){
var _9=null;
if(dojo.isArray(_8)){
if(_8.length>2){
_9=_8[0].apply(_8[1],_8.slice(2));
}else{
_9=_8[0].apply(_8[1]);
}
}else{
_9=_8();
}
return _9;
};
if(this._curId>=this._defsResolved.length){
_7(_6.func);
return;
}
if(_6.pauseAfter){
if(_7(_6.func)!==false){
setTimeout(dojo.hitch(this,"_go"),_6.pauseAfter);
}else{
this._goOnPause=_6.pauseAfter;
}
}else{
if(_6.pauseBefore){
var x=dojo.hitch(this,function(){
if(_7(_6.func)!==false){
this._go();
}
});
setTimeout(x,_6.pauseBefore);
}else{
if(_7(_6.func)!==false){
this._go();
}
}
}
},goOn:function(){
if(this._goOnPause){
setTimeout(dojo.hitch(this,"_go"),this._goOnPause);
this._goOnPause=0;
}else{
this._go();
}
},stop:function(){
this._running=false;
}});
}
