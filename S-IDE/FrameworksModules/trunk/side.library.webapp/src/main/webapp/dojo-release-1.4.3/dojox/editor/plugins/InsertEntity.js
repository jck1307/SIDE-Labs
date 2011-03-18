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


if(!dojo._hasResource["dojox.editor.plugins.InsertEntity"]){
dojo._hasResource["dojox.editor.plugins.InsertEntity"]=true;
dojo.provide("dojox.editor.plugins.InsertEntity");
dojo.require("dijit._editor._Plugin");
dojo.require("dijit.form.Button");
dojo.require("dijit.TooltipDialog");
dojo.require("dojox.editor.plugins.EntityPalette");
dojo.require("dojox.html.entities");
dojo.require("dojo.i18n");
dojo.requireLocalization("dojox.editor.plugins","InsertEntity",null,"ROOT,cs,de,es,fr,hu,it,ja,ko,pl,pt,ru,zh,zh-tw");
dojo.declare("dojox.editor.plugins.InsertEntity",dijit._editor._Plugin,{iconClassPrefix:"dijitAdditionalEditorIcon",_initButton:function(){
this.dropDown=new dojox.editor.plugins.EntityPalette({showCode:this.showCode,showEntityName:this.showEntityName});
this.connect(this.dropDown,"onChange",function(_1){
this.button.closeDropDown();
this.editor.focus();
this.editor.execCommand("inserthtml",_1);
});
var _2=dojo.i18n.getLocalization("dojox.editor.plugins","InsertEntity");
this.button=new dijit.form.DropDownButton({label:_2["insertEntity"],showLabel:false,iconClass:this.iconClassPrefix+" "+this.iconClassPrefix+"InsertEntity",tabIndex:"-1",dropDown:this.dropDown});
},setEditor:function(_3){
this.editor=_3;
this._initButton();
this.editor.addKeyHandler("s",true,true,dojo.hitch(this,function(){
this.button.openDropDown();
this.dropDown.focus();
}));
_3.contentPreFilters.push(this._preFilterEntities);
_3.contentPostFilters.push(this._postFilterEntities);
},_preFilterEntities:function(s){
return dojox.html.entities.decode(s,dojox.html.entities.latin);
},_postFilterEntities:function(s){
return dojox.html.entities.encode(s,dojox.html.entities.latin);
}});
dojo.subscribe(dijit._scopeName+".Editor.getPlugin",null,function(o){
if(o.plugin){
return;
}
var _4=o.args.name?o.args.name.toLowerCase():"";
if(_4==="insertentity"){
o.plugin=new dojox.editor.plugins.InsertEntity({showCode:("showCode" in o.args)?o.args.showCode:false,showEntityName:("showEntityName" in o.args)?o.args.showEntityName:false});
}
});
}
