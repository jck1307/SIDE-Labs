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


if(!dojo._hasResource["dijit.MenuBar"]){
dojo._hasResource["dijit.MenuBar"]=true;
dojo.provide("dijit.MenuBar");
dojo.require("dijit.Menu");
dojo.declare("dijit.MenuBar",dijit._MenuBase,{templateString:dojo.cache("dijit","templates/MenuBar.html","<div class=\"dijitMenuBar dijitMenuPassive\" dojoAttachPoint=\"containerNode\"  waiRole=\"menubar\" tabIndex=\"${tabIndex}\" dojoAttachEvent=\"onkeypress: _onKeyPress\"></div>\n"),_isMenuBar:true,constructor:function(){
this._orient=this.isLeftToRight()?{BL:"TL"}:{BR:"TR"};
},postCreate:function(){
var k=dojo.keys,l=this.isLeftToRight();
this.connectKeyNavHandlers(l?[k.LEFT_ARROW]:[k.RIGHT_ARROW],l?[k.RIGHT_ARROW]:[k.LEFT_ARROW]);
},focusChild:function(_1){
var _2=this.focusedChild,_3=_2&&_2.popup&&_2.popup.isShowingNow;
this.inherited(arguments);
if(_3&&_1.popup&&!_1.disabled){
this._openPopup();
}
},_onKeyPress:function(_4){
if(_4.ctrlKey||_4.altKey){
return;
}
switch(_4.charOrCode){
case dojo.keys.DOWN_ARROW:
this._moveToPopup(_4);
dojo.stopEvent(_4);
}
},onItemClick:function(_5,_6){
if(_5.popup&&_5.popup.isShowingNow){
_5.popup.onCancel();
}else{
this.inherited(arguments);
}
}});
}
