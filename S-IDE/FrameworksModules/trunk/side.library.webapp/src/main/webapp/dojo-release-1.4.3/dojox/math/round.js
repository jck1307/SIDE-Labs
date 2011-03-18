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


if(!dojo._hasResource["dojox.math.round"]){
dojo._hasResource["dojox.math.round"]=true;
dojo.provide("dojox.math.round");
dojo.experimental("dojox.math.round");
dojox.math.round=function(_1,_2,_3){
var _4=Math.log(Math.abs(_1))/Math.log(10);
var _5=10/(_3||10);
var _6=Math.pow(10,-15+_4);
return (_5*(+_1+(_1>0?_6:-_6))).toFixed(_2)/_5;
};
if((0.9).toFixed()==0){
(function(){
var _7=dojox.math.round;
dojox.math.round=function(v,p,m){
var d=Math.pow(10,-p||0),a=Math.abs(v);
if(!v||a>=d||a*Math.pow(10,p+1)<5){
d=0;
}
return _7(v,p,m)+(v>0?d:-d);
};
})();
}
}
