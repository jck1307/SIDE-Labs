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


if(!dojo._hasResource["dojox.math.random.Secure"]){
dojo._hasResource["dojox.math.random.Secure"]=true;
dojo.provide("dojox.math.random.Secure");
dojo.declare("dojox.math.random.Secure",null,{constructor:function(_1,_2){
this.prng=_1;
var p=this.pool=new Array(_1.size);
this.pptr=0;
for(var i=0,_3=_1.size;i<_3;){
var t=Math.floor(65536*Math.random());
p[i++]=t>>>8;
p[i++]=t&255;
}
this.seedTime();
if(!_2){
this.h=[dojo.connect(dojo.body(),"onclick",this,"seedTime"),dojo.connect(dojo.body(),"onkeypress",this,"seedTime")];
}
},destroy:function(){
if(this.h){
dojo.forEach(this.h,dojo.disconnect);
}
},nextBytes:function(_4){
var _5=this.state;
if(!_5){
this.seedTime();
_5=this.state=this.prng();
_5.init(this.pool);
for(var p=this.pool,i=0,_6=p.length;i<_6;p[i++]=0){
}
this.pptr=0;
}
for(var i=0,_6=_4.length;i<_6;++i){
_4[i]=_5.next();
}
},seedTime:function(){
this._seed_int(new Date().getTime());
},_seed_int:function(x){
var p=this.pool,i=this.pptr;
p[i++]^=x&255;
p[i++]^=(x>>8)&255;
p[i++]^=(x>>16)&255;
p[i++]^=(x>>24)&255;
if(i>=this.prng.size){
i-=this.prng.size;
}
this.pptr=i;
}});
}
