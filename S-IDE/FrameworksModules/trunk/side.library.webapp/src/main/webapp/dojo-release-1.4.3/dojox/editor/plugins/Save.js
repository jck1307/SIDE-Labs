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


if(!dojo._hasResource["dojox.editor.plugins.Save"]){
dojo._hasResource["dojox.editor.plugins.Save"]=true;
dojo.provide("dojox.editor.plugins.Save");
dojo.require("dijit._editor._Plugin");
dojo.require("dijit.form.Button");
dojo.require("dojo.i18n");
dojo.requireLocalization("dojox.editor.plugins","Save",null,"ROOT,cs,de,es,fr,hu,it,ja,ko,pl,pt,ru,zh,zh-tw");
dojo.declare("dojox.editor.plugins.Save",dijit._editor._Plugin,{iconClassPrefix:"dijitAdditionalEditorIcon",url:"",logResults:true,_initButton:function(){
var _1=dojo.i18n.getLocalization("dojox.editor.plugins","Save");
this.button=new dijit.form.Button({label:_1["save"],showLabel:false,iconClass:this.iconClassPrefix+" "+this.iconClassPrefix+"Save",tabIndex:"-1",onClick:dojo.hitch(this,"_save")});
},setEditor:function(_2){
this.editor=_2;
this._initButton();
},_save:function(){
var _3=this.editor.attr("value");
this.save(_3);
},save:function(_4){
var _5={"Content-Type":"text/html"};
if(this.url){
var _6={url:this.url,postData:_4,headers:_5,handleAs:"text"};
this.button.attr("disabled",true);
var _7=dojo.xhrPost(_6);
_7.addCallback(dojo.hitch(this,this.onSuccess));
_7.addErrback(dojo.hitch(this,this.onError));
}else{
}
},onSuccess:function(_8,_9){
this.button.attr("disabled",false);
if(this.logResults){
}
},onError:function(_a,_b){
this.button.attr("disabled",false);
if(this.logResults){
}
}});
dojo.subscribe(dijit._scopeName+".Editor.getPlugin",null,function(o){
if(o.plugin){
return;
}
var _c=o.args.name.toLowerCase();
if(_c==="save"){
o.plugin=new dojox.editor.plugins.Save({url:("url" in o.args)?o.args.url:"",logResults:("logResults" in o.args)?o.args.logResults:true});
}
});
}
