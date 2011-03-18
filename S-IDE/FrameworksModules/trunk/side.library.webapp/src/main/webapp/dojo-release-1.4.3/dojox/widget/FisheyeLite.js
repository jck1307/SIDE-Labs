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


if(!dojo._hasResource["dojox.widget.FisheyeLite"]){
dojo._hasResource["dojox.widget.FisheyeLite"]=true;
dojo.provide("dojox.widget.FisheyeLite");
dojo.experimental("dojox.widget.FisheyeLite");
dojo.require("dijit._Widget");
dojo.require("dojo.fx.easing");
dojo.declare("dojox.widget.FisheyeLite",dijit._Widget,{durationIn:350,easeIn:dojo.fx.easing.backOut,durationOut:1420,easeOut:dojo.fx.easing.elasticOut,properties:null,units:"px",constructor:function(_1,_2){
this.properties=_1.properties||{fontSize:2.75};
},postCreate:function(){
this.inherited(arguments);
this._target=dojo.query(".fisheyeTarget",this.domNode)[0]||this.domNode;
this._makeAnims();
this.connect(this.domNode,"onmouseover","show");
this.connect(this.domNode,"onmouseout","hide");
this.connect(this._target,"onclick","onClick");
},show:function(){
this._runningOut.stop();
this._runningIn.play();
},hide:function(){
this._runningIn.stop();
this._runningOut.play();
},_makeAnims:function(){
var _3={},_4={},cs=dojo.getComputedStyle(this._target);
for(var p in this.properties){
var _5=this.properties[p],_6=dojo.isObject(_5),v=parseInt(cs[p]);
_4[p]={end:v,units:this.units};
_3[p]=_6?_5:{end:_5*v,units:this.units};
}
this._runningIn=dojo.animateProperty({node:this._target,easing:this.easeIn,duration:this.durationIn,properties:_3});
this._runningOut=dojo.animateProperty({node:this._target,duration:this.durationOut,easing:this.easeOut,properties:_4});
this.connect(this._runningIn,"onEnd",dojo.hitch(this,"onSelected",this));
},onClick:function(e){
},onSelected:function(e){
}});
}
