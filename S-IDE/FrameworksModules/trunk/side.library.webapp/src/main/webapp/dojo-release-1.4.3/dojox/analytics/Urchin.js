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


if(!dojo._hasResource["dojox.analytics.Urchin"]){
dojo._hasResource["dojox.analytics.Urchin"]=true;
dojo.provide("dojox.analytics.Urchin");
dojo.declare("dojox.analytics.Urchin",null,{acct:"",constructor:function(_1){
this.tracker=null;
dojo.mixin(this,_1);
this.acct=this.acct||dojo.config.urchin;
var re=/loaded|complete/,_2=("https:"==dojo.doc.location.protocol)?"https://ssl.":"http://www.",h=dojo.doc.getElementsByTagName("head")[0],n=dojo.create("script",{src:_2+"google-analytics.com/ga.js"},h);
n.onload=n.onreadystatechange=dojo.hitch(this,function(e){
if(e&&e.type=="load"||re.test(n.readyState)){
n.onload=n.onreadystatechange=null;
this._gotGA();
h.removeChild(n);
}
});
},_gotGA:function(){
this.tracker=_gat._getTracker(this.acct);
this.GAonLoad.apply(this,arguments);
},GAonLoad:function(){
this.trackPageView();
},trackPageView:function(_3){
this.tracker._trackPageview.apply(this,arguments);
}});
}
