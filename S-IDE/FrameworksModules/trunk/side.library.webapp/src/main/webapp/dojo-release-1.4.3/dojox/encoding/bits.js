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


if(!dojo._hasResource["dojox.encoding.bits"]){
dojo._hasResource["dojox.encoding.bits"]=true;
dojo.provide("dojox.encoding.bits");
dojox.encoding.bits.OutputStream=function(){
this.reset();
};
dojo.extend(dojox.encoding.bits.OutputStream,{reset:function(){
this.buffer=[];
this.accumulator=0;
this.available=8;
},putBits:function(_1,_2){
while(_2){
var w=Math.min(_2,this.available);
var v=(w<=_2?_1>>>(_2-w):_1)<<(this.available-w);
this.accumulator|=v&(255>>>(8-this.available));
this.available-=w;
if(!this.available){
this.buffer.push(this.accumulator);
this.accumulator=0;
this.available=8;
}
_2-=w;
}
},getWidth:function(){
return this.buffer.length*8+(8-this.available);
},getBuffer:function(){
var b=this.buffer;
if(this.available<8){
b.push(this.accumulator&(255<<this.available));
}
this.reset();
return b;
}});
dojox.encoding.bits.InputStream=function(_3,_4){
this.buffer=_3;
this.width=_4;
this.bbyte=this.bit=0;
};
dojo.extend(dojox.encoding.bits.InputStream,{getBits:function(_5){
var r=0;
while(_5){
var w=Math.min(_5,8-this.bit);
var v=this.buffer[this.bbyte]>>>(8-this.bit-w);
r<<=w;
r|=v&~(~0<<w);
this.bit+=w;
if(this.bit==8){
++this.bbyte;
this.bit=0;
}
_5-=w;
}
return r;
},getWidth:function(){
return this.width-this.bbyte*8-this.bit;
}});
}
