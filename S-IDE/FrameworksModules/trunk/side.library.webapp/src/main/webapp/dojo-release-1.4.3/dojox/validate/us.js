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


if(!dojo._hasResource["dojox.validate.us"]){
dojo._hasResource["dojox.validate.us"]=true;
dojo.provide("dojox.validate.us");
dojo.require("dojox.validate._base");
dojox.validate.us.isState=function(_1,_2){
var re=new RegExp("^"+dojox.validate.regexp.us.state(_2)+"$","i");
return re.test(_1);
};
dojox.validate.us.isPhoneNumber=function(_3){
var _4={format:["###-###-####","(###) ###-####","(###) ### ####","###.###.####","###/###-####","### ### ####","###-###-#### x#???","(###) ###-#### x#???","(###) ### #### x#???","###.###.#### x#???","###/###-#### x#???","### ### #### x#???","##########"]};
return dojox.validate.isNumberFormat(_3,_4);
};
dojox.validate.us.isSocialSecurityNumber=function(_5){
var _6={format:["###-##-####","### ## ####","#########"]};
return dojox.validate.isNumberFormat(_5,_6);
};
dojox.validate.us.isZipCode=function(_7){
var _8={format:["#####-####","##### ####","#########","#####"]};
return dojox.validate.isNumberFormat(_7,_8);
};
}
