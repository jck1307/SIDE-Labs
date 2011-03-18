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


if(!dojo._hasResource["dojox.lang.functional.zip"]){
dojo._hasResource["dojox.lang.functional.zip"]=true;
dojo.provide("dojox.lang.functional.zip");
(function(){
var df=dojox.lang.functional;
dojo.mixin(df,{zip:function(){
var n=arguments[0].length,m=arguments.length,i=1,t=new Array(n),j,p;
for(;i<m;n=Math.min(n,arguments[i++].length)){
}
for(i=0;i<n;++i){
p=new Array(m);
for(j=0;j<m;p[j]=arguments[j][i],++j){
}
t[i]=p;
}
return t;
},unzip:function(a){
return df.zip.apply(null,a);
}});
})();
}
