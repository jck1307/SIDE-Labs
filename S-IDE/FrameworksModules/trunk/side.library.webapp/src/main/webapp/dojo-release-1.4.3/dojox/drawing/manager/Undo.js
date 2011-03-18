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


if(!dojo._hasResource["dojox.drawing.manager.Undo"]){
dojo._hasResource["dojox.drawing.manager.Undo"]=true;
dojo.provide("dojox.drawing.manager.Undo");
dojox.drawing.manager.Undo=dojox.drawing.util.oo.declare(function(_1){
this.keys=_1.keys;
this.undostack=[];
this.redostack=[];
dojo.connect(this.keys,"onKeyDown",this,"onKeyDown");
},{onKeyDown:function(_2){
if(!_2.cmmd){
return;
}
if(_2.keyCode==90&&!_2.shift){
this.undo();
}else{
if((_2.keyCode==90&&_2.shift)||_2.keyCode==89){
this.redo();
}
}
},add:function(_3){
_3.args=dojo.mixin({},_3.args);
this.undostack.push(_3);
},apply:function(_4,_5,_6){
dojo.hitch(_4,_5)(_6);
},undo:function(){
var o=this.undostack.pop();
if(!o){
return;
}
o.before();
this.redostack.push(o);
},redo:function(){
var o=this.redostack.pop();
if(!o){
return;
}
if(o.after){
o.after();
}else{
o.before();
}
this.undostack.push(o);
}});
}
