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


if(!dojo._hasResource["dojox.av.widget.PlayButton"]){
dojo._hasResource["dojox.av.widget.PlayButton"]=true;
dojo.provide("dojox.av.widget.PlayButton");
dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dijit.form.Button");
dojo.declare("dojox.av.widget.PlayButton",[dijit._Widget,dijit._Templated],{templateString:dojo.cache("dojox.av.widget","resources/PlayButton.html","<div class=\"PlayPauseToggle Pause\" dojoAttachEvent=\"click:onClick\">\n    <div class=\"icon\"></div>\n</div>\n"),postCreate:function(){
this.showPlay();
},setMedia:function(_1){
this.media=_1;
dojo.connect(this.media,"onEnd",this,"showPlay");
dojo.connect(this.media,"onStart",this,"showPause");
},onClick:function(){
if(this._mode=="play"){
this.onPlay();
}else{
this.onPause();
}
},onPlay:function(){
if(this.media){
this.media.play();
}
this.showPause();
},onPause:function(){
if(this.media){
this.media.pause();
}
this.showPlay();
},showPlay:function(){
this._mode="play";
dojo.removeClass(this.domNode,"Pause");
dojo.addClass(this.domNode,"Play");
},showPause:function(){
this._mode="pause";
dojo.addClass(this.domNode,"Pause");
dojo.removeClass(this.domNode,"Play");
}});
}
