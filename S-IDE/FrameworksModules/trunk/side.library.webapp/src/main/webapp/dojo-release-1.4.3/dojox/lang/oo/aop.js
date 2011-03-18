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


if(!dojo._hasResource["dojox.lang.oo.aop"]){
dojo._hasResource["dojox.lang.oo.aop"]=true;
dojo.provide("dojox.lang.oo.aop");
dojo.require("dojox.lang.oo.Decorator");
dojo.require("dojox.lang.oo.general");
(function(){
var oo=dojox.lang.oo,md=oo.makeDecorator,_1=oo.general,_2=oo.aop,_3=dojo.isFunction;
_2.before=_1.before;
_2.around=_1.wrap;
_2.afterReturning=md(function(_4,_5,_6){
return _3(_6)?function(){
var _7=_6.apply(this,arguments);
_5.call(this,_7);
return _7;
}:function(){
_5.call(this);
};
});
_2.afterThrowing=md(function(_8,_9,_a){
return _3(_a)?function(){
var _b;
try{
_b=_a.apply(this,arguments);
}
catch(e){
_9.call(this,e);
throw e;
}
return _b;
}:_a;
});
_2.after=md(function(_c,_d,_e){
return _3(_e)?function(){
var _f;
try{
_f=_e.apply(this,arguments);
}
finally{
_d.call(this);
}
return _f;
}:function(){
_d.call(this);
};
});
})();
}
