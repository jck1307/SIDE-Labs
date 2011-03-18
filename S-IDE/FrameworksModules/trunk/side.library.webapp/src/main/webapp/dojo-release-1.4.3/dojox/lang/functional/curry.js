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


if(!dojo._hasResource["dojox.lang.functional.curry"]){
dojo._hasResource["dojox.lang.functional.curry"]=true;
dojo.provide("dojox.lang.functional.curry");
dojo.require("dojox.lang.functional.lambda");
(function(){
var df=dojox.lang.functional,ap=Array.prototype;
var _1=function(_2){
return function(){
var _3=_2.args.concat(ap.slice.call(arguments,0));
if(arguments.length+_2.args.length<_2.arity){
return _1({func:_2.func,arity:_2.arity,args:_3});
}
return _2.func.apply(this,_3);
};
};
dojo.mixin(df,{curry:function(f,_4){
f=df.lambda(f);
_4=typeof _4=="number"?_4:f.length;
return _1({func:f,arity:_4,args:[]});
},arg:{},partial:function(f){
var a=arguments,l=a.length,_5=new Array(l-1),p=[],i=1,t;
f=df.lambda(f);
for(;i<l;++i){
t=a[i];
_5[i-1]=t;
if(t===df.arg){
p.push(i-1);
}
}
return function(){
var t=ap.slice.call(_5,0),i=0,l=p.length;
for(;i<l;++i){
t[p[i]]=arguments[i];
}
return f.apply(this,t);
};
},mixer:function(f,_6){
f=df.lambda(f);
return function(){
var t=new Array(_6.length),i=0,l=_6.length;
for(;i<l;++i){
t[i]=arguments[_6[i]];
}
return f.apply(this,t);
};
},flip:function(f){
f=df.lambda(f);
return function(){
var a=arguments,l=a.length-1,t=new Array(l+1),i=0;
for(;i<=l;++i){
t[l-i]=a[i];
}
return f.apply(this,t);
};
}});
})();
}
