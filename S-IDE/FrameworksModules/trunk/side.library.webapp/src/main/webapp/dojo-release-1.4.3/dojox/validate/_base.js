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


if(!dojo._hasResource["dojox.validate._base"]){
dojo._hasResource["dojox.validate._base"]=true;
dojo.provide("dojox.validate._base");
dojo.experimental("dojox.validate");
dojo.require("dojo.regexp");
dojo.require("dojo.number");
dojo.require("dojox.validate.regexp");
dojox.validate.isText=function(_1,_2){
_2=(typeof _2=="object")?_2:{};
if(/^\s*$/.test(_1)){
return false;
}
if(typeof _2.length=="number"&&_2.length!=_1.length){
return false;
}
if(typeof _2.minlength=="number"&&_2.minlength>_1.length){
return false;
}
if(typeof _2.maxlength=="number"&&_2.maxlength<_1.length){
return false;
}
return true;
};
dojox.validate._isInRangeCache={};
dojox.validate.isInRange=function(_3,_4){
_3=dojo.number.parse(_3,_4);
if(isNaN(_3)){
return false;
}
_4=(typeof _4=="object")?_4:{};
var _5=(typeof _4.max=="number")?_4.max:Infinity,_6=(typeof _4.min=="number")?_4.min:-Infinity,_7=(typeof _4.decimal=="string")?_4.decimal:".",_8=dojox.validate._isInRangeCache,_9=_3+"max"+_5+"min"+_6+"dec"+_7;
if(typeof _8[_9]!="undefined"){
return _8[_9];
}
_8[_9]=!(_3<_6||_3>_5);
return _8[_9];
};
dojox.validate.isNumberFormat=function(_a,_b){
var re=new RegExp("^"+dojox.validate.regexp.numberFormat(_b)+"$","i");
return re.test(_a);
};
dojox.validate.isValidLuhn=function(_c){
var _d=0,_e,_f;
if(!dojo.isString(_c)){
_c=String(_c);
}
_c=_c.replace(/[- ]/g,"");
_e=_c.length%2;
for(var i=0;i<_c.length;i++){
_f=parseInt(_c.charAt(i));
if(i%2==_e){
_f*=2;
}
if(_f>9){
_f-=9;
}
_d+=_f;
}
return !(_d%10);
};
}
