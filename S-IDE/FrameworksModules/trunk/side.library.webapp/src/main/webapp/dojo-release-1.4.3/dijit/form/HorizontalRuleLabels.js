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


if(!dojo._hasResource["dijit.form.HorizontalRuleLabels"]){
dojo._hasResource["dijit.form.HorizontalRuleLabels"]=true;
dojo.provide("dijit.form.HorizontalRuleLabels");
dojo.require("dijit.form.HorizontalRule");
dojo.declare("dijit.form.HorizontalRuleLabels",dijit.form.HorizontalRule,{templateString:"<div class=\"dijitRuleContainer dijitRuleContainerH dijitRuleLabelsContainer dijitRuleLabelsContainerH\"></div>",labelStyle:"",labels:[],numericMargin:0,minimum:0,maximum:1,constraints:{pattern:"#%"},_positionPrefix:"<div class=\"dijitRuleLabelContainer dijitRuleLabelContainerH\" style=\"left:",_labelPrefix:"\"><span class=\"dijitRuleLabel dijitRuleLabelH\">",_suffix:"</span></div>",_calcPosition:function(_1){
return _1;
},_genHTML:function(_2,_3){
return this._positionPrefix+this._calcPosition(_2)+this._positionSuffix+this.labelStyle+this._labelPrefix+this.labels[_3]+this._suffix;
},getLabels:function(){
var _4=this.labels;
if(!_4.length){
_4=dojo.query("> li",this.srcNodeRef).map(function(_5){
return String(_5.innerHTML);
});
}
this.srcNodeRef.innerHTML="";
if(!_4.length&&this.count>1){
var _6=this.minimum;
var _7=(this.maximum-_6)/(this.count-1);
for(var i=0;i<this.count;i++){
_4.push((i<this.numericMargin||i>=(this.count-this.numericMargin))?"":dojo.number.format(_6,this.constraints));
_6+=_7;
}
}
return _4;
},postMixInProperties:function(){
this.inherited(arguments);
this.labels=this.getLabels();
this.count=this.labels.length;
}});
}
