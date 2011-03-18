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


if(!dojo._hasResource["dojox.fx.scroll"]){
dojo._hasResource["dojox.fx.scroll"]=true;
dojo.provide("dojox.fx.scroll");
dojo.experimental("dojox.fx.scroll");
dojo.require("dojox.fx._core");
dojox.fx.smoothScroll=function(_1){
if(!_1.target){
_1.target=dojo.coords(_1.node,true);
}
var _2=dojo[(dojo.isIE?"isObject":"isFunction")](_1["win"].scrollTo);
var _3=(_2)?(function(_4){
_1.win.scrollTo(_4[0],_4[1]);
}):(function(_5){
_1.win.scrollLeft=_5[0];
_1.win.scrollTop=_5[1];
});
var _6=new dojo.Animation(dojo.mixin({beforeBegin:function(){
if(this.curve){
delete this.curve;
}
var _7=_2?dojo._docScroll():{x:_1.win.scrollLeft,y:_1.win.scrollTop};
_6.curve=new dojox.fx._Line([_7.x,_7.y],[_1.target.x,_1.target.y]);
},onAnimate:_3},_1));
return _6;
};
}
