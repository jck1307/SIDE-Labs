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


if(!dojo._hasResource["dojox.highlight.languages.pygments.html"]){
dojo._hasResource["dojox.highlight.languages.pygments.html"]=true;
dojo.provide("dojox.highlight.languages.pygments.html");
dojo.require("dojox.highlight._base");
dojo.require("dojox.highlight.languages.pygments._html");
(function(){
var dh=dojox.highlight,_1=dh.languages,_2=[],ht=_1.pygments._html.tags;
for(var _3 in ht){
_2.push(_3);
}
_2="\\b("+_2.join("|")+")\\b";
_1.html={case_insensitive:true,defaultMode:{contains:["name entity","comment","comment preproc","_script","_style","_tag"]},modes:[{className:"comment",begin:"<!--",end:"-->"},{className:"comment preproc",begin:"\\<\\!\\[CDATA\\[",end:"\\]\\]\\>"},{className:"comment preproc",begin:"\\<\\!",end:"\\>"},{className:"string",begin:"'",end:"'",illegal:"\\n",relevance:0},{className:"string",begin:"\"",end:"\"",illegal:"\\n",relevance:0},{className:"name entity",begin:"\\&[a-z]+;",end:"^"},{className:"name tag",begin:_2,end:"^",relevance:5},{className:"name attribute",begin:"\\b[a-z0-9_\\:\\-]+\\s*=",end:"^",relevance:0},{className:"_script",begin:"\\<script\\b",end:"\\</script\\>",relevance:5},{className:"_style",begin:"\\<style\\b",end:"\\</style\\>",relevance:5},{className:"_tag",begin:"\\<(?!/)",end:"\\>",contains:["name tag","name attribute","string","_value"]},{className:"_tag",begin:"\\</",end:"\\>",contains:["name tag"]},{className:"_value",begin:"[^\\s\\>]+",end:"^"}]};
})();
}
