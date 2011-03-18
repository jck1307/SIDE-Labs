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


if(!dojo._hasResource["dojox.image.Gallery"]){
dojo._hasResource["dojox.image.Gallery"]=true;
dojo.provide("dojox.image.Gallery");
dojo.experimental("dojox.image.Gallery");
dojo.require("dojo.fx");
dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dojox.image.ThumbnailPicker");
dojo.require("dojox.image.SlideShow");
dojo.declare("dojox.image.Gallery",[dijit._Widget,dijit._Templated],{imageHeight:375,imageWidth:500,pageSize:dojox.image.SlideShow.prototype.pageSize,autoLoad:true,linkAttr:"link",imageThumbAttr:"imageUrlThumb",imageLargeAttr:"imageUrl",titleAttr:"title",slideshowInterval:3,templateString:dojo.cache("dojox.image","resources/Gallery.html","<div dojoAttachPoint=\"outerNode\" class=\"imageGalleryWrapper\">\n\t<div dojoAttachPoint=\"thumbPickerNode\"></div>\n\t<div dojoAttachPoint=\"slideShowNode\"></div>\n</div>\n"),postCreate:function(){
this.widgetid=this.id;
this.inherited(arguments);
this.thumbPicker=new dojox.image.ThumbnailPicker({linkAttr:this.linkAttr,imageLargeAttr:this.imageLargeAttr,imageThumbAttr:this.imageThumbAttr,titleAttr:this.titleAttr,useLoadNotifier:true,size:this.imageWidth},this.thumbPickerNode);
this.slideShow=new dojox.image.SlideShow({imageHeight:this.imageHeight,imageWidth:this.imageWidth,autoLoad:this.autoLoad,linkAttr:this.linkAttr,imageLargeAttr:this.imageLargeAttr,titleAttr:this.titleAttr,slideshowInterval:this.slideshowInterval,pageSize:this.pageSize},this.slideShowNode);
var _1=this;
dojo.subscribe(this.slideShow.getShowTopicName(),function(_2){
_1.thumbPicker._showThumbs(_2.index);
});
dojo.subscribe(this.thumbPicker.getClickTopicName(),function(_3){
_1.slideShow.showImage(_3.index);
});
dojo.subscribe(this.thumbPicker.getShowTopicName(),function(_4){
_1.slideShow.moveImageLoadingPointer(_4.index);
});
dojo.subscribe(this.slideShow.getLoadTopicName(),function(_5){
_1.thumbPicker.markImageLoaded(_5);
});
this._centerChildren();
},setDataStore:function(_6,_7,_8){
this.thumbPicker.setDataStore(_6,_7,_8);
this.slideShow.setDataStore(_6,_7,_8);
},reset:function(){
this.slideShow.reset();
this.thumbPicker.reset();
},showNextImage:function(_9){
this.slideShow.showNextImage();
},toggleSlideshow:function(){
dojo.deprecated("dojox.widget.Gallery.toggleSlideshow is deprecated.  Use toggleSlideShow instead.","","2.0");
this.toggleSlideShow();
},toggleSlideShow:function(){
this.slideShow.toggleSlideShow();
},showImage:function(_a,_b){
this.slideShow.showImage(_a,_b);
},resize:function(_c){
this.thumbPicker.resize(_c);
},_centerChildren:function(){
var _d=dojo.marginBox(this.thumbPicker.outerNode);
var _e=dojo.marginBox(this.slideShow.outerNode);
var _f=(_d.w-_e.w)/2;
if(_f>0){
dojo.style(this.slideShow.outerNode,"marginLeft",_f+"px");
}else{
if(_f<0){
dojo.style(this.thumbPicker.outerNode,"marginLeft",(_f*-1)+"px");
}
}
}});
}
