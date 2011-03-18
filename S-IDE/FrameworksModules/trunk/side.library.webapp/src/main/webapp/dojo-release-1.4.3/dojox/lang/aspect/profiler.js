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


if(!dojo._hasResource["dojox.lang.aspect.profiler"]){
dojo._hasResource["dojox.lang.aspect.profiler"]=true;
dojo.provide("dojox.lang.aspect.profiler");
(function(){
var _1=dojox.lang.aspect,_2=0;
var _3=function(_4){
this.args=_4?[_4]:[];
this.inCall=0;
};
dojo.extend(_3,{before:function(){
if(!(this.inCall++)){
console.profile.apply(console,this.args);
}
},after:function(){
if(!--this.inCall){
}
}});
_1.profiler=function(_5){
return new _3(_5);
};
})();
}
