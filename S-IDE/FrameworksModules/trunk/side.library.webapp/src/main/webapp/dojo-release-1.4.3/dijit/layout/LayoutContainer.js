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


if(!dojo._hasResource["dijit.layout.LayoutContainer"]){
dojo._hasResource["dijit.layout.LayoutContainer"]=true;
dojo.provide("dijit.layout.LayoutContainer");
dojo.require("dijit.layout._LayoutWidget");
dojo.declare("dijit.layout.LayoutContainer",dijit.layout._LayoutWidget,{baseClass:"dijitLayoutContainer",constructor:function(){
dojo.deprecated("dijit.layout.LayoutContainer is deprecated","use BorderContainer instead",2);
},layout:function(){
dijit.layout.layoutChildren(this.domNode,this._contentBox,this.getChildren());
},addChild:function(_1,_2){
this.inherited(arguments);
if(this._started){
dijit.layout.layoutChildren(this.domNode,this._contentBox,this.getChildren());
}
},removeChild:function(_3){
this.inherited(arguments);
if(this._started){
dijit.layout.layoutChildren(this.domNode,this._contentBox,this.getChildren());
}
}});
dojo.extend(dijit._Widget,{layoutAlign:"none"});
}
