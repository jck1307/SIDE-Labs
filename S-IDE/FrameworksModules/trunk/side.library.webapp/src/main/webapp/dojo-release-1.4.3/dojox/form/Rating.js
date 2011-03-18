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


if(!dojo._hasResource["dojox.form.Rating"]){
dojo._hasResource["dojox.form.Rating"]=true;
dojo.provide("dojox.form.Rating");
dojo.require("dijit.form._FormWidget");
dojo.declare("dojox.form.Rating",dijit.form._FormWidget,{templateString:null,numStars:3,value:0,constructor:function(_1){
dojo.mixin(this,_1);
var _2="<div dojoAttachPoint=\"domNode\" class=\"dojoxRating dijitInline\">"+"<input type=\"hidden\" value=\"0\" dojoAttachPoint=\"focusNode\" /><ul>${stars}</ul>"+"</div>";
var _3="<li class=\"dojoxRatingStar dijitInline\" dojoAttachEvent=\"onclick:onStarClick,onmouseover:_onMouse,onmouseout:_onMouse\" value=\"${value}\"></li>";
var _4="";
for(var i=0;i<this.numStars;i++){
_4+=dojo.string.substitute(_3,{value:i+1});
}
this.templateString=dojo.string.substitute(_2,{stars:_4});
},postCreate:function(){
this.inherited(arguments);
this._renderStars(this.value);
},_onMouse:function(_5){
this.inherited(arguments);
if(this._hovering){
var _6=+dojo.attr(_5.target,"value");
this.onMouseOver(_5,_6);
this._renderStars(_6,true);
}else{
this._renderStars(this.value);
}
},_renderStars:function(_7,_8){
dojo.query(".dojoxRatingStar",this.domNode).forEach(function(_9,i){
if(i+1>_7){
dojo.removeClass(_9,"dojoxRatingStarHover");
dojo.removeClass(_9,"dojoxRatingStarChecked");
}else{
dojo.removeClass(_9,"dojoxRatingStar"+(_8?"Checked":"Hover"));
dojo.addClass(_9,"dojoxRatingStar"+(_8?"Hover":"Checked"));
}
});
},onStarClick:function(_a){
var _b=+dojo.attr(_a.target,"value");
this.setAttribute("value",_b==this.value?0:_b);
this._renderStars(this.value);
this.onChange(this.value);
},onMouseOver:function(){
},setAttribute:function(_c,_d){
this.inherited("setAttribute",arguments);
if(_c=="value"){
this._renderStars(this.value);
this.onChange(this.value);
}
}});
}
