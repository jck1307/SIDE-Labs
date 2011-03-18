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


if(!dojo._hasResource["dojox.form.Manager"]){
dojo._hasResource["dojox.form.Manager"]=true;
dojo.provide("dojox.form.Manager");
dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dojox.form.manager._Mixin");
dojo.require("dojox.form.manager._NodeMixin");
dojo.require("dojox.form.manager._FormMixin");
dojo.require("dojox.form.manager._ValueMixin");
dojo.require("dojox.form.manager._EnableMixin");
dojo.require("dojox.form.manager._DisplayMixin");
dojo.require("dojox.form.manager._ClassMixin");
dojo.declare("dojox.form.Manager",[dijit._Widget,dojox.form.manager._Mixin,dojox.form.manager._NodeMixin,dojox.form.manager._FormMixin,dojox.form.manager._ValueMixin,dojox.form.manager._EnableMixin,dojox.form.manager._DisplayMixin,dojox.form.manager._ClassMixin],{buildRendering:function(){
var _1=this.domNode=this.srcNodeRef;
if(!this.containerNode){
this.containerNode=_1;
}
this._attachPoints=[];
dijit._Templated.prototype._attachTemplateNodes.call(this,_1);
},destroyRendering:function(){
dijit._Templated.prototype.destroyRendering.call(this);
}});
}
