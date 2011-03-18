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


if(!dojo._hasResource["dojox.analytics.plugins.idle"]){
dojo._hasResource["dojox.analytics.plugins.idle"]=true;
dojo.require("dojox.analytics._base");
dojo.provide("dojox.analytics.plugins.idle");
dojox.analytics.plugins.idle=new (function(){
this.addData=dojo.hitch(dojox.analytics,"addData","idle");
this.idleTime=dojo.config["idleTime"]||60000;
this.idle=true;
this.setIdle=function(){
this.addData("isIdle");
this.idle=true;
};
dojo.addOnLoad(dojo.hitch(this,function(){
var _1=["onmousemove","onkeydown","onclick","onscroll"];
for(var i=0;i<_1.length;i++){
dojo.connect(dojo.doc,_1[i],this,function(e){
if(this.idle){
this.idle=false;
this.addData("isActive");
this.idleTimer=setTimeout(dojo.hitch(this,"setIdle"),this.idleTime);
}else{
clearTimeout(this.idleTimer);
this.idleTimer=setTimeout(dojo.hitch(this,"setIdle"),this.idleTime);
}
});
}
}));
})();
}
