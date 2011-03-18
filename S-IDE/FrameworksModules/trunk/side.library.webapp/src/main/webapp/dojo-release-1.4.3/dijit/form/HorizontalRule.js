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


if(!dojo._hasResource["dijit.form.HorizontalRule"]){
dojo._hasResource["dijit.form.HorizontalRule"]=true;
dojo.provide("dijit.form.HorizontalRule");
dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.declare("dijit.form.HorizontalRule",[dijit._Widget,dijit._Templated],{templateString:"<div class=\"dijitRuleContainer dijitRuleContainerH\"></div>",count:3,container:"containerNode",ruleStyle:"",_positionPrefix:"<div class=\"dijitRuleMark dijitRuleMarkH\" style=\"left:",_positionSuffix:"%;",_suffix:"\"></div>",_genHTML:function(_1,_2){
return this._positionPrefix+_1+this._positionSuffix+this.ruleStyle+this._suffix;
},_isHorizontal:true,postCreate:function(){
var _3;
if(this.count==1){
_3=this._genHTML(50,0);
}else{
var i;
var _4=100/(this.count-1);
if(!this._isHorizontal||this.isLeftToRight()){
_3=this._genHTML(0,0);
for(i=1;i<this.count-1;i++){
_3+=this._genHTML(_4*i,i);
}
_3+=this._genHTML(100,this.count-1);
}else{
_3=this._genHTML(100,0);
for(i=1;i<this.count-1;i++){
_3+=this._genHTML(100-_4*i,i);
}
_3+=this._genHTML(0,this.count-1);
}
}
this.domNode.innerHTML=_3;
}});
}
