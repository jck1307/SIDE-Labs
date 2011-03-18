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


if(!dojo._hasResource["dojox.highlight.languages.xml"]){
dojo._hasResource["dojox.highlight.languages.xml"]=true;
dojo.provide("dojox.highlight.languages.xml");
dojo.require("dojox.highlight._base");
(function(){
var _1={className:"comment",begin:"<!--",end:"-->"};
var _2={className:"attribute",begin:" [a-zA-Z-]+=",end:"^",contains:["value"]};
var _3={className:"value",begin:"\"",end:"\""};
var dh=dojox.highlight,_4=dh.constants;
dh.languages.xml={defaultMode:{contains:["pi","comment","cdata","tag"]},case_insensitive:true,modes:[{className:"pi",begin:"<\\?",end:"\\?>",relevance:10},_1,{className:"cdata",begin:"<\\!\\[CDATA\\[",end:"\\]\\]>"},{className:"tag",begin:"</?",end:">",contains:["title","tag_internal"],relevance:1.5},{className:"title",begin:"[A-Za-z:_][A-Za-z0-9\\._:-]+",end:"^",relevance:0},{className:"tag_internal",begin:"^",endsWithParent:true,contains:["attribute"],relevance:0,illegal:"[\\+\\.]"},_2,_3],XML_COMMENT:_1,XML_ATTR:_2,XML_VALUE:_3};
})();
}
