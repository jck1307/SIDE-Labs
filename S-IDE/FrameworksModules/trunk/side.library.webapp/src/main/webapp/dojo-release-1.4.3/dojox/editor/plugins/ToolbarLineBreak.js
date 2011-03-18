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


if(!dojo._hasResource["dojox.editor.plugins.ToolbarLineBreak"]){
dojo._hasResource["dojox.editor.plugins.ToolbarLineBreak"]=true;
dojo.provide("dojox.editor.plugins.ToolbarLineBreak");
dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dijit._editor._Plugin");
dojo.declare("dojox.editor.plugins._ToolbarLineBreak",[dijit._Widget,dijit._Templated],{templateString:"<span class='dijit dijitReset'><br></span>",postCreate:function(){
dojo.setSelectable(this.domNode,false);
},isFocusable:function(){
return false;
}});
dojo.subscribe(dijit._scopeName+".Editor.getPlugin",null,function(o){
if(o.plugin){
return;
}
var _1=o.args.name.toLowerCase();
if(_1==="||"||_1==="toolbarlinebreak"){
o.plugin=new dijit._editor._Plugin({button:new dojox.editor.plugins._ToolbarLineBreak()});
}
});
}