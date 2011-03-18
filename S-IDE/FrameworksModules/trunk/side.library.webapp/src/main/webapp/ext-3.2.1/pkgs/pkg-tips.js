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
 * Ext JS Library 3.2.1
 * Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.Tip=Ext.extend(Ext.Panel,{minWidth:40,maxWidth:300,shadow:"sides",defaultAlign:"tl-bl?",autoRender:true,quickShowInterval:250,frame:true,hidden:true,baseCls:"x-tip",floating:{shadow:true,shim:true,useDisplay:true,constrain:false},autoHeight:true,closeAction:"hide",initComponent:function(){Ext.Tip.superclass.initComponent.call(this);if(this.closable&&!this.title){this.elements+=",header"}},afterRender:function(){Ext.Tip.superclass.afterRender.call(this);if(this.closable){this.addTool({id:"close",handler:this[this.closeAction],scope:this})}},showAt:function(a){Ext.Tip.superclass.show.call(this);if(this.measureWidth!==false&&(!this.initialConfig||typeof this.initialConfig.width!="number")){this.doAutoWidth()}if(this.constrainPosition){a=this.el.adjustForConstraints(a)}this.setPagePosition(a[0],a[1])},doAutoWidth:function(a){a=a||0;var b=this.body.getTextWidth();if(this.title){b=Math.max(b,this.header.child("span").getTextWidth(this.title))}b+=this.getFrameWidth()+(this.closable?20:0)+this.body.getPadding("lr")+a;this.setWidth(b.constrain(this.minWidth,this.maxWidth));if(Ext.isIE7&&!this.repainted){this.el.repaint();this.repainted=true}},showBy:function(a,b){if(!this.rendered){this.render(Ext.getBody())}this.showAt(this.el.getAlignToXY(a,b||this.defaultAlign))},initDraggable:function(){this.dd=new Ext.Tip.DD(this,typeof this.draggable=="boolean"?null:this.draggable);this.header.addClass("x-tip-draggable")}});Ext.reg("tip",Ext.Tip);Ext.Tip.DD=function(b,a){Ext.apply(this,a);this.tip=b;Ext.Tip.DD.superclass.constructor.call(this,b.el.id,"WindowDD-"+b.id);this.setHandleElId(b.header.id);this.scroll=false};Ext.extend(Ext.Tip.DD,Ext.dd.DD,{moveOnly:true,scroll:false,headerOffsets:[100,25],startDrag:function(){this.tip.el.disableShadow()},endDrag:function(a){this.tip.el.enableShadow(true)}});Ext.ToolTip=Ext.extend(Ext.Tip,{showDelay:500,hideDelay:200,dismissDelay:5000,trackMouse:false,anchorToTarget:true,anchorOffset:0,targetCounter:0,constrainPosition:false,initComponent:function(){Ext.ToolTip.superclass.initComponent.call(this);this.lastActive=new Date();this.initTarget(this.target);this.origAnchor=this.anchor},onRender:function(b,a){Ext.ToolTip.superclass.onRender.call(this,b,a);this.anchorCls="x-tip-anchor-"+this.getAnchorPosition();this.anchorEl=this.el.createChild({cls:"x-tip-anchor "+this.anchorCls})},afterRender:function(){Ext.ToolTip.superclass.afterRender.call(this);this.anchorEl.setStyle("z-index",this.el.getZIndex()+1)},initTarget:function(c){var a;if((a=Ext.get(c))){if(this.target){var b=Ext.get(this.target);this.mun(b,"mouseover",this.onTargetOver,this);this.mun(b,"mouseout",this.onTargetOut,this);this.mun(b,"mousemove",this.onMouseMove,this)}this.mon(a,{mouseover:this.onTargetOver,mouseout:this.onTargetOut,mousemove:this.onMouseMove,scope:this});this.target=a}if(this.anchor){this.anchorTarget=this.target}},onMouseMove:function(b){var a=this.delegate?b.getTarget(this.delegate):this.triggerElement=true;if(a){this.targetXY=b.getXY();if(a===this.triggerElement){if(!this.hidden&&this.trackMouse){this.setPagePosition(this.getTargetXY())}}else{this.hide();this.lastActive=new Date(0);this.onTargetOver(b)}}else{if(!this.closable&&this.isVisible()){this.hide()}}},getTargetXY:function(){if(this.delegate){this.anchorTarget=this.triggerElement}if(this.anchor){this.targetCounter++;var c=this.getOffsets(),k=(this.anchorToTarget&&!this.trackMouse)?this.el.getAlignToXY(this.anchorTarget,this.getAnchorAlign()):this.targetXY,a=Ext.lib.Dom.getViewWidth()-5,g=Ext.lib.Dom.getViewHeight()-5,h=document.documentElement,e=document.body,j=(h.scrollLeft||e.scrollLeft||0)+5,i=(h.scrollTop||e.scrollTop||0)+5,b=[k[0]+c[0],k[1]+c[1]],f=this.getSize();this.anchorEl.removeClass(this.anchorCls);if(this.targetCounter<2){if(b[0]<j){if(this.anchorToTarget){this.defaultAlign="l-r";if(this.mouseOffset){this.mouseOffset[0]*=-1}}this.anchor="left";return this.getTargetXY()}if(b[0]+f.width>a){if(this.anchorToTarget){this.defaultAlign="r-l";if(this.mouseOffset){this.mouseOffset[0]*=-1}}this.anchor="right";return this.getTargetXY()}if(b[1]<i){if(this.anchorToTarget){this.defaultAlign="t-b";if(this.mouseOffset){this.mouseOffset[1]*=-1}}this.anchor="top";return this.getTargetXY()}if(b[1]+f.height>g){if(this.anchorToTarget){this.defaultAlign="b-t";if(this.mouseOffset){this.mouseOffset[1]*=-1}}this.anchor="bottom";return this.getTargetXY()}}this.anchorCls="x-tip-anchor-"+this.getAnchorPosition();this.anchorEl.addClass(this.anchorCls);this.targetCounter=0;return b}else{var d=this.getMouseOffset();return[this.targetXY[0]+d[0],this.targetXY[1]+d[1]]}},getMouseOffset:function(){var a=this.anchor?[0,0]:[15,18];if(this.mouseOffset){a[0]+=this.mouseOffset[0];a[1]+=this.mouseOffset[1]}return a},getAnchorPosition:function(){if(this.anchor){this.tipAnchor=this.anchor.charAt(0)}else{var a=this.defaultAlign.match(/^([a-z]+)-([a-z]+)(\?)?$/);if(!a){throw"AnchorTip.defaultAlign is invalid"}this.tipAnchor=a[1].charAt(0)}switch(this.tipAnchor){case"t":return"top";case"b":return"bottom";case"r":return"right"}return"left"},getAnchorAlign:function(){switch(this.anchor){case"top":return"tl-bl";case"left":return"tl-tr";case"right":return"tr-tl";default:return"bl-tl"}},getOffsets:function(){var b,a=this.getAnchorPosition().charAt(0);if(this.anchorToTarget&&!this.trackMouse){switch(a){case"t":b=[0,9];break;case"b":b=[0,-13];break;case"r":b=[-13,0];break;default:b=[9,0];break}}else{switch(a){case"t":b=[-15-this.anchorOffset,30];break;case"b":b=[-19-this.anchorOffset,-13-this.el.dom.offsetHeight];break;case"r":b=[-15-this.el.dom.offsetWidth,-13-this.anchorOffset];break;default:b=[25,-13-this.anchorOffset];break}}var c=this.getMouseOffset();b[0]+=c[0];b[1]+=c[1];return b},onTargetOver:function(b){if(this.disabled||b.within(this.target.dom,true)){return}var a=b.getTarget(this.delegate);if(a){this.triggerElement=a;this.clearTimer("hide");this.targetXY=b.getXY();this.delayShow()}},delayShow:function(){if(this.hidden&&!this.showTimer){if(this.lastActive.getElapsed()<this.quickShowInterval){this.show()}else{this.showTimer=this.show.defer(this.showDelay,this)}}else{if(!this.hidden&&this.autoHide!==false){this.show()}}},onTargetOut:function(a){if(this.disabled||a.within(this.target.dom,true)){return}this.clearTimer("show");if(this.autoHide!==false){this.delayHide()}},delayHide:function(){if(!this.hidden&&!this.hideTimer){this.hideTimer=this.hide.defer(this.hideDelay,this)}},hide:function(){this.clearTimer("dismiss");this.lastActive=new Date();if(this.anchorEl){this.anchorEl.hide()}Ext.ToolTip.superclass.hide.call(this);delete this.triggerElement},show:function(){if(this.anchor){this.showAt([-1000,-1000]);this.origConstrainPosition=this.constrainPosition;this.constrainPosition=false;this.anchor=this.origAnchor}this.showAt(this.getTargetXY());if(this.anchor){this.syncAnchor();this.anchorEl.show();this.constrainPosition=this.origConstrainPosition}else{this.anchorEl.hide()}},showAt:function(a){this.lastActive=new Date();this.clearTimers();Ext.ToolTip.superclass.showAt.call(this,a);if(this.dismissDelay&&this.autoHide!==false){this.dismissTimer=this.hide.defer(this.dismissDelay,this)}if(this.anchor&&!this.anchorEl.isVisible()){this.syncAnchor();this.anchorEl.show()}},syncAnchor:function(){var a,b,c;switch(this.tipAnchor.charAt(0)){case"t":a="b";b="tl";c=[20+this.anchorOffset,2];break;case"r":a="l";b="tr";c=[-2,11+this.anchorOffset];break;case"b":a="t";b="bl";c=[20+this.anchorOffset,-2];break;default:a="r";b="tl";c=[2,11+this.anchorOffset];break}this.anchorEl.alignTo(this.el,a+"-"+b,c)},setPagePosition:function(a,b){Ext.ToolTip.superclass.setPagePosition.call(this,a,b);if(this.anchor){this.syncAnchor()}},clearTimer:function(a){a=a+"Timer";clearTimeout(this[a]);delete this[a]},clearTimers:function(){this.clearTimer("show");this.clearTimer("dismiss");this.clearTimer("hide")},onShow:function(){Ext.ToolTip.superclass.onShow.call(this);Ext.getDoc().on("mousedown",this.onDocMouseDown,this)},onHide:function(){Ext.ToolTip.superclass.onHide.call(this);Ext.getDoc().un("mousedown",this.onDocMouseDown,this)},onDocMouseDown:function(a){if(this.autoHide!==true&&!this.closable&&!a.within(this.el.dom)){this.disable();this.doEnable.defer(100,this)}},doEnable:function(){if(!this.isDestroyed){this.enable()}},onDisable:function(){this.clearTimers();this.hide()},adjustPosition:function(a,d){if(this.contstrainPosition){var c=this.targetXY[1],b=this.getSize().height;if(d<=c&&(d+b)>=c){d=c-b-5}}return{x:a,y:d}},beforeDestroy:function(){this.clearTimers();Ext.destroy(this.anchorEl);delete this.anchorEl;delete this.target;delete this.anchorTarget;delete this.triggerElement;Ext.ToolTip.superclass.beforeDestroy.call(this)},onDestroy:function(){Ext.getDoc().un("mousedown",this.onDocMouseDown,this);Ext.ToolTip.superclass.onDestroy.call(this)}});Ext.reg("tooltip",Ext.ToolTip);Ext.QuickTip=Ext.extend(Ext.ToolTip,{interceptTitles:false,tagConfig:{namespace:"ext",attribute:"qtip",width:"qwidth",target:"target",title:"qtitle",hide:"hide",cls:"qclass",align:"qalign",anchor:"anchor"},initComponent:function(){this.target=this.target||Ext.getDoc();this.targets=this.targets||{};Ext.QuickTip.superclass.initComponent.call(this)},register:function(e){var g=Ext.isArray(e)?e:arguments;for(var f=0,a=g.length;f<a;f++){var k=g[f];var h=k.target;if(h){if(Ext.isArray(h)){for(var d=0,b=h.length;d<b;d++){this.targets[Ext.id(h[d])]=k}}else{this.targets[Ext.id(h)]=k}}}},unregister:function(a){delete this.targets[Ext.id(a)]},cancelShow:function(b){var a=this.activeTarget;b=Ext.get(b).dom;if(this.isVisible()){if(a&&a.el==b){this.hide()}}else{if(a&&a.el==b){this.clearTimer("show")}}},getTipCfg:function(d){var b=d.getTarget(),c,a;if(this.interceptTitles&&b.title&&Ext.isString(b.title)){c=b.title;b.qtip=c;b.removeAttribute("title");d.preventDefault()}else{a=this.tagConfig;c=b.qtip||Ext.fly(b).getAttribute(a.attribute,a.namespace)}return c},onTargetOver:function(h){if(this.disabled){return}this.targetXY=h.getXY();var c=h.getTarget();if(!c||c.nodeType!==1||c==document||c==document.body){return}if(this.activeTarget&&((c==this.activeTarget.el)||Ext.fly(this.activeTarget.el).contains(c))){this.clearTimer("hide");this.show();return}if(c&&this.targets[c.id]){this.activeTarget=this.targets[c.id];this.activeTarget.el=c;this.anchor=this.activeTarget.anchor;if(this.anchor){this.anchorTarget=c}this.delayShow();return}var f,g=Ext.fly(c),b=this.tagConfig,d=b.namespace;if(f=this.getTipCfg(h)){var a=g.getAttribute(b.hide,d);this.activeTarget={el:c,text:f,width:g.getAttribute(b.width,d),autoHide:a!="user"&&a!=="false",title:g.getAttribute(b.title,d),cls:g.getAttribute(b.cls,d),align:g.getAttribute(b.align,d)};this.anchor=g.getAttribute(b.anchor,d);if(this.anchor){this.anchorTarget=c}this.delayShow()}},onTargetOut:function(a){if(this.activeTarget&&a.within(this.activeTarget.el)&&!this.getTipCfg(a)){return}this.clearTimer("show");if(this.autoHide!==false){this.delayHide()}},showAt:function(b){var a=this.activeTarget;if(a){if(!this.rendered){this.render(Ext.getBody());this.activeTarget=a}if(a.width){this.setWidth(a.width);this.body.setWidth(this.adjustBodyWidth(a.width-this.getFrameWidth()));this.measureWidth=false}else{this.measureWidth=true}this.setTitle(a.title||"");this.body.update(a.text);this.autoHide=a.autoHide;this.dismissDelay=a.dismissDelay||this.dismissDelay;if(this.lastCls){this.el.removeClass(this.lastCls);delete this.lastCls}if(a.cls){this.el.addClass(a.cls);this.lastCls=a.cls}if(this.anchor){this.constrainPosition=false}else{if(a.align){b=this.el.getAlignToXY(a.el,a.align);this.constrainPosition=false}else{this.constrainPosition=true}}}Ext.QuickTip.superclass.showAt.call(this,b)},hide:function(){delete this.activeTarget;Ext.QuickTip.superclass.hide.call(this)}});Ext.reg("quicktip",Ext.QuickTip);Ext.QuickTips=function(){var b,a=[];return{init:function(c){if(!b){if(!Ext.isReady){Ext.onReady(function(){Ext.QuickTips.init(c)});return}b=new Ext.QuickTip({elements:"header,body"});if(c!==false){b.render(Ext.getBody())}}},enable:function(){if(b){a.pop();if(a.length<1){b.enable()}}},disable:function(){if(b){b.disable()}a.push(1)},isEnabled:function(){return b!==undefined&&!b.disabled},getQuickTip:function(){return b},register:function(){b.register.apply(b,arguments)},unregister:function(){b.unregister.apply(b,arguments)},tips:function(){b.register.apply(b,arguments)}}}();Ext.slider.Tip=Ext.extend(Ext.Tip,{minWidth:10,offsets:[0,-10],init:function(a){a.on({scope:this,dragstart:this.onSlide,drag:this.onSlide,dragend:this.hide,destroy:this.destroy})},onSlide:function(b,c,a){this.show();this.body.update(this.getText(a));this.doAutoWidth();this.el.alignTo(a.el,"b-t?",this.offsets)},getText:function(a){return String(a.value)}});Ext.ux.SliderTip=Ext.slider.Tip;
