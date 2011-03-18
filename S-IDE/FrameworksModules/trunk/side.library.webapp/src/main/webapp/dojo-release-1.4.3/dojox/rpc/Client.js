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


if(!dojo._hasResource["dojox.rpc.Client"]){
dojo._hasResource["dojox.rpc.Client"]=true;
dojo.provide("dojox.rpc.Client");
(function(){
dojo._defaultXhr=dojo.xhr;
dojo.xhr=function(_1,_2){
var _3=_2.headers=_2.headers||{};
_3["Client-Id"]=dojox.rpc.Client.clientId;
_3["Seq-Id"]=dojox._reqSeqId=(dojox._reqSeqId||0)+1;
return dojo._defaultXhr.apply(dojo,arguments);
};
})();
dojox.rpc.Client.clientId=(Math.random()+"").substring(2,14)+(new Date().getTime()+"").substring(8,13);
}
