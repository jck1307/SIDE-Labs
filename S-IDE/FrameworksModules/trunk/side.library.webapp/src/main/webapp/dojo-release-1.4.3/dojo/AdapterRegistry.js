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


if(!dojo._hasResource["dojo.AdapterRegistry"]){
dojo._hasResource["dojo.AdapterRegistry"]=true;
dojo.provide("dojo.AdapterRegistry");
dojo.AdapterRegistry=function(_1){
this.pairs=[];
this.returnWrappers=_1||false;
};
dojo.extend(dojo.AdapterRegistry,{register:function(_2,_3,_4,_5,_6){
this.pairs[((_6)?"unshift":"push")]([_2,_3,_4,_5]);
},match:function(){
for(var i=0;i<this.pairs.length;i++){
var _7=this.pairs[i];
if(_7[1].apply(this,arguments)){
if((_7[3])||(this.returnWrappers)){
return _7[2];
}else{
return _7[2].apply(this,arguments);
}
}
}
throw new Error("No match found");
},unregister:function(_8){
for(var i=0;i<this.pairs.length;i++){
var _9=this.pairs[i];
if(_9[0]==_8){
this.pairs.splice(i,1);
return true;
}
}
return false;
}});
}
