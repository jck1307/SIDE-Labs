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


if(!dojo._hasResource["dojox.validate.ca"]){
dojo._hasResource["dojox.validate.ca"]=true;
dojo.provide("dojox.validate.ca");
dojo.require("dojox.validate._base");
dojo.mixin(dojox.validate.ca,{isPhoneNumber:function(_1){
return dojox.validate.us.isPhoneNumber(_1);
},isProvince:function(_2){
var re=new RegExp("^"+dojox.validate.regexp.ca.province()+"$","i");
return re.test(_2);
},isSocialInsuranceNumber:function(_3){
var _4={format:["###-###-###","### ### ###","#########"]};
return dojox.validate.isNumberFormat(_3,_4);
},isPostalCode:function(_5){
var re=new RegExp("^"+dojox.validate.regexp.ca.postalCode()+"$","i");
return re.test(_5);
}});
}
