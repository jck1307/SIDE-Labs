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
 * Ext JS Library 3.2.1
 * Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.History=(function(){var e,c;var j=false;var d;function f(){var k=top.location.href,l=k.indexOf("#");return l>=0?k.substr(l+1):null}function a(){c.value=d}function g(k){d=k;Ext.History.fireEvent("change",k)}function h(l){var k=['<html><body><div id="state">',Ext.util.Format.htmlEncode(l),"</div></body></html>"].join("");try{var n=e.contentWindow.document;n.open();n.write(k);n.close();return true}catch(m){return false}}function b(){if(!e.contentWindow||!e.contentWindow.document){setTimeout(b,10);return}var n=e.contentWindow.document;var l=n.getElementById("state");var k=l?l.innerText:null;var m=f();setInterval(function(){n=e.contentWindow.document;l=n.getElementById("state");var p=l?l.innerText:null;var o=f();if(p!==k){k=p;g(k);top.location.hash=k;m=k;a()}else{if(o!==m){m=o;h(o)}}},50);j=true;Ext.History.fireEvent("ready",Ext.History)}function i(){d=c.value?c.value:f();if(Ext.isIE){b()}else{var k=f();setInterval(function(){var l=f();if(l!==k){k=l;g(k);a()}},50);j=true;Ext.History.fireEvent("ready",Ext.History)}}return{fieldId:"x-history-field",iframeId:"x-history-frame",events:{},init:function(l,k){if(j){Ext.callback(l,k,[this]);return}if(!Ext.isReady){Ext.onReady(function(){Ext.History.init(l,k)});return}c=Ext.getDom(Ext.History.fieldId);if(Ext.isIE){e=Ext.getDom(Ext.History.iframeId)}this.addEvents("ready","change");if(l){this.on("ready",l,k,{single:true})}i()},add:function(k,l){if(l!==false){if(this.getToken()==k){return true}}if(Ext.isIE){return h(k)}else{top.location.hash=k;return true}},back:function(){history.go(-1)},forward:function(){history.go(1)},getToken:function(){return j?d:f()}}})();Ext.apply(Ext.History,new Ext.util.Observable());
