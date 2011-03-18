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


if(!dojo._hasResource["dojox.form.manager._EnableMixin"]){
dojo._hasResource["dojox.form.manager._EnableMixin"]=true;
dojo.provide("dojox.form.manager._EnableMixin");
dojo.require("dojox.form.manager._Mixin");
(function(){
var fm=dojox.form.manager,aa=fm.actionAdapter,ia=fm.inspectorAdapter;
dojo.declare("dojox.form.manager._EnableMixin",null,{gatherEnableState:function(_1){
var _2=this.inspectFormWidgets(ia(function(_3,_4){
return !_4.attr("disabled");
}),_1);
if(this.inspectFormNodes){
dojo.mixin(_2,this.inspectFormNodes(ia(function(_5,_6){
return !dojo.attr(_6,"disabled");
}),_1));
}
return _2;
},enable:function(_7,_8){
if(arguments.length<2||_8===undefined){
_8=true;
}
this.inspectFormWidgets(aa(function(_9,_a,_b){
_a.attr("disabled",!_b);
}),_7,_8);
if(this.inspectFormNodes){
this.inspectFormNodes(aa(function(_c,_d,_e){
dojo.attr(_d,"disabled",!_e);
}),_7,_8);
}
return this;
},disable:function(_f){
var _10=this.gatherEnableState();
this.enable(_f,false);
return _10;
}});
})();
}
