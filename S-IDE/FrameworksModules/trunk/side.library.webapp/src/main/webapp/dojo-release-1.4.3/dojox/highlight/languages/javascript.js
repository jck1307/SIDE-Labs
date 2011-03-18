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


if(!dojo._hasResource["dojox.highlight.languages.javascript"]){
dojo._hasResource["dojox.highlight.languages.javascript"]=true;
dojo.provide("dojox.highlight.languages.javascript");
dojo.require("dojox.highlight._base");
(function(){
var dh=dojox.highlight,_1=dh.constants;
dh.languages.javascript={defaultMode:{lexems:[_1.UNDERSCORE_IDENT_RE],contains:["string","comment","number","regexp","function"],keywords:{"keyword":{"in":1,"if":1,"for":1,"while":1,"finally":1,"var":1,"new":1,"function":1,"do":1,"return":1,"void":1,"else":1,"break":1,"catch":1,"instanceof":1,"with":1,"throw":1,"case":1,"default":1,"try":1,"this":1,"switch":1,"continue":1,"typeof":1,"delete":1},"literal":{"true":1,"false":1,"null":1}}},modes:[_1.C_LINE_COMMENT_MODE,_1.C_BLOCK_COMMENT_MODE,_1.C_NUMBER_MODE,_1.APOS_STRING_MODE,_1.QUOTE_STRING_MODE,_1.BACKSLASH_ESCAPE,{className:"regexp",begin:"/.*?[^\\\\/]/[gim]*",end:"^"},{className:"function",begin:"function\\b",end:"{",lexems:[_1.UNDERSCORE_IDENT_RE],keywords:{"function":1},contains:["title","params"]},{className:"title",begin:_1.UNDERSCORE_IDENT_RE,end:"^"},{className:"params",begin:"\\(",end:"\\)",contains:["string","comment"]}]};
})();
}
