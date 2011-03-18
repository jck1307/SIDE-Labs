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


if(!dojo._hasResource["dijit._editor.plugins.NewPage"]){
dojo._hasResource["dijit._editor.plugins.NewPage"]=true;
dojo.provide("dijit._editor.plugins.NewPage");
dojo.require("dijit._editor._Plugin");
dojo.require("dijit.form.Button");
dojo.require("dojo.i18n");
dojo.requireLocalization("dijit._editor","commands",null,"ROOT,ar,ca,cs,da,de,el,es,fi,fr,he,hu,it,ja,ko,nb,nl,pl,pt,pt-pt,ru,sk,sl,sv,th,tr,zh,zh-tw");
dojo.declare("dijit._editor.plugins.NewPage",dijit._editor._Plugin,{content:"<br>",_initButton:function(){
var _1=dojo.i18n.getLocalization("dijit._editor","commands");
this.button=new dijit.form.Button({label:_1["newPage"],showLabel:false,iconClass:this.iconClassPrefix+" "+this.iconClassPrefix+"NewPage",tabIndex:"-1",onClick:dojo.hitch(this,"_newPage")});
},setEditor:function(_2){
this.editor=_2;
this._initButton();
},_newPage:function(){
this.editor.attr("value",this.content);
this.editor.focus();
}});
dojo.subscribe(dijit._scopeName+".Editor.getPlugin",null,function(o){
if(o.plugin){
return;
}
var _3=o.args.name.toLowerCase();
if(_3==="newpage"){
o.plugin=new dijit._editor.plugins.NewPage({content:("content" in o.args)?o.args.content:"<br>"});
}
});
}
