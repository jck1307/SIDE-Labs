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


if(!dojo._hasResource["dojox.form.manager._ClassMixin"]){
dojo._hasResource["dojox.form.manager._ClassMixin"]=true;
dojo.provide("dojox.form.manager._ClassMixin");
dojo.require("dojox.form.manager._Mixin");
(function(){
var fm=dojox.form.manager,aa=fm.actionAdapter,ia=fm.inspectorAdapter;
dojo.declare("dojox.form.manager._ClassMixin",null,{gatherClassState:function(_1,_2){
var _3=this.inspect(ia(function(_4,_5){
return dojo.hasClass(_5,_1);
}),_2);
return _3;
},addClass:function(_6,_7){
this.inspect(aa(function(_8,_9){
dojo.addClass(_9,_6);
}),_7);
return this;
},removeClass:function(_a,_b){
this.inspect(aa(function(_c,_d){
dojo.removeClass(_d,_a);
}),_b);
return this;
}});
})();
}
