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


if(!dojo._hasResource["dojox.editor.plugins.UploadImage"]){
dojo._hasResource["dojox.editor.plugins.UploadImage"]=true;
dojo.provide("dojox.editor.plugins.UploadImage");
dojo.require("dijit._editor._Plugin");
dojo.require("dojox.form.FileUploader");
dojo.experimental("dojox.editor.plugins.UploadImage");
dojo.declare("dojox.editor.plugins.UploadImage",dijit._editor._Plugin,{tempImageUrl:"",iconClassPrefix:"editorIcon",useDefaultCommand:false,uploadUrl:"",button:null,label:"Upload",setToolbar:function(_1){
this.button.destroy();
this.createFileInput();
_1.addChild(this.button);
},_initButton:function(){
this.command="uploadImage";
this.editor.commands[this.command]="Upload Image";
this.inherited("_initButton",arguments);
delete this.command;
},createFileInput:function(){
var _2=dojo.create("span",{innerHTML:"."},document.body);
dojo.style(_2,{width:"40px",height:"20px",paddingLeft:"8px",paddingRight:"8px"});
this.button=new dojox.form.FileUploader({isDebug:true,uploadUrl:this.uploadUrl,uploadOnChange:true,selectMultipleFiles:false,baseClass:"dojoxEditorUploadNorm",hoverClass:"dojoxEditorUploadHover",activeClass:"dojoxEditorUploadActive",disabledClass:"dojoxEditorUploadDisabled"},_2);
this.connect(this.button,"onChange","insertTempImage");
this.connect(this.button,"onComplete","onComplete");
},onComplete:function(_3,_4,_5){
_3=_3[0];
var _6=dojo.withGlobal(this.editor.window,"byId",dojo,[this.currentImageId]);
var _7;
if(this.downloadPath){
_7=this.downloadPath+_3.name;
}else{
_7=_3.file;
}
_6.src=_7;
dojo.attr(_6,"_djrealurl",_7);
if(_3.width){
_6.width=_3.width;
_6.height=_3.height;
}
},insertTempImage:function(){
this.currentImageId="img_"+(new Date().getTime());
var _8="<img id=\""+this.currentImageId+"\" src=\""+this.tempImageUrl+"\" width=\"32\" height=\"32\"/>";
this.editor.execCommand("inserthtml",_8);
}});
dojo.subscribe(dijit._scopeName+".Editor.getPlugin",null,function(o){
if(o.plugin){
return;
}
switch(o.args.name){
case "uploadImage":
o.plugin=new dojox.editor.plugins.UploadImage({url:o.args.url});
}
});
}
