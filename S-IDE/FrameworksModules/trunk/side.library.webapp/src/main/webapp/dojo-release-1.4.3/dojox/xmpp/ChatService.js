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


if(!dojo._hasResource["dojox.xmpp.ChatService"]){
dojo._hasResource["dojox.xmpp.ChatService"]=true;
dojo.provide("dojox.xmpp.ChatService");
dojox.xmpp.chat={CHAT_STATE_NS:"http://jabber.org/protocol/chatstates",ACTIVE_STATE:"active",COMPOSING_STATE:"composing",INACTIVE_STATE:"inactive",PAUSED_STATE:"paused",GONE_STATE:"gone"};
dojo.declare("dojox.xmpp.ChatService",null,{state:"",constructor:function(){
this.state="";
this.chatid=Math.round(Math.random()*1000000000000000);
},recieveMessage:function(_1,_2){
if(_1&&!_2){
this.onNewMessage(_1);
}
},setSession:function(_3){
this.session=_3;
},setState:function(_4){
if(this.state!=_4){
this.state=_4;
}
},invite:function(_5){
if(this.uid){
return;
}
if(!_5||_5==""){
throw new Error("ChatService::invite() contact is NULL");
}
this.uid=_5;
var _6={xmlns:"jabber:client",to:this.uid,from:this.session.jid+"/"+this.session.resource,type:"chat"};
var _7=new dojox.string.Builder(dojox.xmpp.util.createElement("message",_6,false));
_7.append(dojox.xmpp.util.createElement("thread",{},false));
_7.append(this.chatid);
_7.append("</thread>");
_7.append(dojox.xmpp.util.createElement("active",{xmlns:dojox.xmpp.chat.CHAT_STATE_NS},true));
_7.append("</message>");
this.session.dispatchPacket(_7.toString());
this.onInvite(_5);
this.setState(dojox.xmpp.chat.CHAT_STATE_NS);
},sendMessage:function(_8){
if(!this.uid){
return;
}
if((!_8.body||_8.body=="")&&!_8.xhtml){
return;
}
var _9={xmlns:"jabber:client",to:this.uid,from:this.session.jid+"/"+this.session.resource,type:"chat"};
var _a=new dojox.string.Builder(dojox.xmpp.util.createElement("message",_9,false));
var _b=dojox.xmpp.util.createElement("html",{"xmlns":dojox.xmpp.xmpp.XHTML_IM_NS},false);
var _c=dojox.xmpp.util.createElement("body",{"xml:lang":this.session.lang,"xmlns":dojox.xmpp.xmpp.XHTML_BODY_NS},false)+_8.body+"</body>";
var _d=dojox.xmpp.util.createElement("body",{},false)+dojox.xmpp.util.stripHtml(_8.body)+"</body>";
if(_a.subject&&_a.subject!=""){
_a.append(dojox.xmpp.util.createElement("subject",{},false));
_a.append(_a.subject);
_a.append("</subject>");
}
_a.append(_d);
_a.append(_b);
_a.append(_c);
_a.append("</html>");
_a.append(dojox.xmpp.util.createElement("thread",{},false));
_a.append(this.chatid);
_a.append("</thread>");
if(this.useChatStates){
_a.append(dojox.xmpp.util.createElement("active",{xmlns:dojox.xmpp.chat.CHAT_STATE_NS},true));
}
_a.append("</message>");
this.session.dispatchPacket(_a.toString());
},sendChatState:function(_e){
if(!this.useChatState||this.firstMessage){
return;
}
if(_e==this._currentState){
return;
}
var _f={xmlns:"jabber:client",to:this.uid,from:this.session.jid+"/"+this.session.resource,type:"chat"};
var _10=new dojox.string.Builder(dojox.xmpp.util.createElement("message",_f,false));
_10.append(dojox.xmpp.util.createElement(_e,{xmlns:dojox.xmpp.chat.CHAT_STATE_NS},true));
this._currentState=_e;
_10.append("<thread>");
_10.append(this.chatid);
_10.append("</thread></message>");
this.session.dispatchPacket(_10.toString());
},onNewMessage:function(msg){
},onInvite:function(_11){
}});
}
