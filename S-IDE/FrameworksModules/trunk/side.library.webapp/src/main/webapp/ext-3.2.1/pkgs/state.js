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
Ext.state.Provider=function(){this.addEvents("statechange");this.state={};Ext.state.Provider.superclass.constructor.call(this)};Ext.extend(Ext.state.Provider,Ext.util.Observable,{get:function(b,a){return typeof this.state[b]=="undefined"?a:this.state[b]},clear:function(a){delete this.state[a];this.fireEvent("statechange",this,a,null)},set:function(a,b){this.state[a]=b;this.fireEvent("statechange",this,a,b)},decodeValue:function(b){var e=/^(a|n|d|b|s|o)\:(.*)$/;var f=e.exec(unescape(b));if(!f||!f[1]){return}var d=f[1];var a=f[2];switch(d){case"n":return parseFloat(a);case"d":return new Date(Date.parse(a));case"b":return(a=="1");case"a":var c=[];if(a!=""){Ext.each(a.split("^"),function(g){c.push(this.decodeValue(g))},this)}return c;case"o":var c={};if(a!=""){Ext.each(a.split("^"),function(h){var g=h.split("=");c[g[0]]=this.decodeValue(g[1])},this)}return c;default:return a}},encodeValue:function(c){var b;if(typeof c=="number"){b="n:"+c}else{if(typeof c=="boolean"){b="b:"+(c?"1":"0")}else{if(Ext.isDate(c)){b="d:"+c.toGMTString()}else{if(Ext.isArray(c)){var f="";for(var e=0,a=c.length;e<a;e++){f+=this.encodeValue(c[e]);if(e!=a-1){f+="^"}}b="a:"+f}else{if(typeof c=="object"){var f="";for(var d in c){if(typeof c[d]!="function"&&c[d]!==undefined){f+=d+"="+this.encodeValue(c[d])+"^"}}b="o:"+f.substring(0,f.length-1)}else{b="s:"+c}}}}}return escape(b)}});Ext.state.Manager=function(){var a=new Ext.state.Provider();return{setProvider:function(b){a=b},get:function(c,b){return a.get(c,b)},set:function(b,c){a.set(b,c)},clear:function(b){a.clear(b)},getProvider:function(){return a}}}();Ext.state.CookieProvider=function(a){Ext.state.CookieProvider.superclass.constructor.call(this);this.path="/";this.expires=new Date(new Date().getTime()+(1000*60*60*24*7));this.domain=null;this.secure=false;Ext.apply(this,a);this.state=this.readCookies()};Ext.extend(Ext.state.CookieProvider,Ext.state.Provider,{set:function(a,b){if(typeof b=="undefined"||b===null){this.clear(a);return}this.setCookie(a,b);Ext.state.CookieProvider.superclass.set.call(this,a,b)},clear:function(a){this.clearCookie(a);Ext.state.CookieProvider.superclass.clear.call(this,a)},readCookies:function(){var d={};var g=document.cookie+";";var b=/\s?(.*?)=(.*?);/g;var f;while((f=b.exec(g))!=null){var a=f[1];var e=f[2];if(a&&a.substring(0,3)=="ys-"){d[a.substr(3)]=this.decodeValue(e)}}return d},setCookie:function(a,b){document.cookie="ys-"+a+"="+this.encodeValue(b)+((this.expires==null)?"":("; expires="+this.expires.toGMTString()))+((this.path==null)?"":("; path="+this.path))+((this.domain==null)?"":("; domain="+this.domain))+((this.secure==true)?"; secure":"")},clearCookie:function(a){document.cookie="ys-"+a+"=null; expires=Thu, 01-Jan-70 00:00:01 GMT"+((this.path==null)?"":("; path="+this.path))+((this.domain==null)?"":("; domain="+this.domain))+((this.secure==true)?"; secure":"")}});
