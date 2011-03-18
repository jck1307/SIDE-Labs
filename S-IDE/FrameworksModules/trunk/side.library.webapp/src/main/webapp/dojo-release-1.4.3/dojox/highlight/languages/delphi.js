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


if(!dojo._hasResource["dojox.highlight.languages.delphi"]){
dojo._hasResource["dojox.highlight.languages.delphi"]=true;
dojo.provide("dojox.highlight.languages.delphi");
dojo.require("dojox.highlight._base");
(function(){
var _1={"and":1,"safecall":1,"cdecl":1,"then":1,"string":1,"exports":1,"library":1,"not":1,"pascal":1,"set":1,"virtual":1,"file":1,"in":1,"array":1,"label":1,"packed":1,"end.":1,"index":1,"while":1,"const":1,"raise":1,"for":1,"to":1,"implementation":1,"with":1,"except":1,"overload":1,"destructor":1,"downto":1,"finally":1,"program":1,"exit":1,"unit":1,"inherited":1,"override":1,"if":1,"type":1,"until":1,"function":1,"do":1,"begin":1,"repeat":1,"goto":1,"nil":1,"far":1,"initialization":1,"object":1,"else":1,"var":1,"uses":1,"external":1,"resourcestring":1,"interface":1,"end":1,"finalization":1,"class":1,"asm":1,"mod":1,"case":1,"on":1,"shr":1,"shl":1,"of":1,"register":1,"xorwrite":1,"threadvar":1,"try":1,"record":1,"near":1,"stored":1,"constructor":1,"stdcall":1,"inline":1,"div":1,"out":1,"or":1,"procedure":1};
var _2={"safecall":1,"stdcall":1,"pascal":1,"stored":1,"const":1,"implementation":1,"finalization":1,"except":1,"to":1,"finally":1,"program":1,"inherited":1,"override":1,"then":1,"exports":1,"string":1,"read":1,"not":1,"mod":1,"shr":1,"try":1,"div":1,"shl":1,"set":1,"library":1,"message":1,"packed":1,"index":1,"for":1,"near":1,"overload":1,"label":1,"downto":1,"exit":1,"public":1,"goto":1,"interface":1,"asm":1,"on":1,"of":1,"constructor":1,"or":1,"private":1,"array":1,"unit":1,"raise":1,"destructor":1,"var":1,"type":1,"until":1,"function":1,"else":1,"external":1,"with":1,"case":1,"default":1,"record":1,"while":1,"protected":1,"property":1,"procedure":1,"published":1,"and":1,"cdecl":1,"do":1,"threadvar":1,"file":1,"in":1,"if":1,"end":1,"virtual":1,"write":1,"far":1,"out":1,"begin":1,"repeat":1,"nil":1,"initialization":1,"object":1,"uses":1,"resourcestring":1,"class":1,"register":1,"xorwrite":1,"inline":1};
var dh=dojox.highlight,_3=dh.constants;
dh.languages.delphi={defaultMode:{lexems:[_3.IDENT_RE],illegal:"(\"|\\$[G-Zg-z]|\\/\\*|</)",contains:["comment","string","number","function","class"],keywords:_1},case_insensitive:true,modes:[{className:"comment",begin:"{",end:"}"},{className:"comment",begin:"\\(\\*",end:"\\*\\)",relevance:10},_3.C_LINE_COMMENT_MODE,{className:"number",begin:_3.NUMBER_RE,end:"^",relevance:0},{className:"string",begin:"'",end:"'",contains:["quote"],relevance:0},{className:"string",begin:"(#\\d+)+",end:"^"},{className:"quote",begin:"''",end:"^"},{className:"function",begin:"function",end:"[:;]",lexems:[_3.IDENT_RE],keywords:{"function":1},contains:["title","params","comment"],relevance:0},{className:"function",begin:"(procedure|constructor|destructor)",end:";",lexems:[_3.IDENT_RE],keywords:{"constructor":1,"destructor":1,"procedure":1},contains:["title","params","comment"],relevance:10},{className:"title",begin:_3.IDENT_RE,end:"^"},{className:"params",begin:"\\(",end:"\\)",lexems:[_3.IDENT_RE],keywords:_1,contains:["string"]},{className:"class",begin:"=\\s*class",end:"end;",lexems:[_3.IDENT_RE],keywords:_2,contains:["string","comment","function"]}]};
})();
}