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


if(!dojo._hasResource["dojox.lang.functional.util"]){
dojo._hasResource["dojox.lang.functional.util"]=true;
dojo.provide("dojox.lang.functional.util");
dojo.require("dojox.lang.functional.lambda");
(function(){
var df=dojox.lang.functional;
dojo.mixin(df,{inlineLambda:function(_1,_2,_3){
var s=df.rawLambda(_1);
if(_3){
df.forEach(s.args,_3);
}
var ap=typeof _2=="string",n=ap?s.args.length:Math.min(s.args.length,_2.length),a=new Array(4*n+4),i,j=1;
for(i=0;i<n;++i){
a[j++]=s.args[i];
a[j++]="=";
a[j++]=ap?_2+"["+i+"]":_2[i];
a[j++]=",";
}
a[0]="(";
a[j++]="(";
a[j++]=s.body;
a[j]="))";
return a.join("");
}});
})();
}
