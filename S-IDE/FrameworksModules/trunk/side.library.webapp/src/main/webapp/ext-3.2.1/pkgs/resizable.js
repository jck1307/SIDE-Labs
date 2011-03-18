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
Ext.Resizable=Ext.extend(Ext.util.Observable,{constructor:function(d,e){this.el=Ext.get(d);if(e&&e.wrap){e.resizeChild=this.el;this.el=this.el.wrap(typeof e.wrap=="object"?e.wrap:{cls:"xresizable-wrap"});this.el.id=this.el.dom.id=e.resizeChild.id+"-rzwrap";this.el.setStyle("overflow","hidden");this.el.setPositioning(e.resizeChild.getPositioning());e.resizeChild.clearPositioning();if(!e.width||!e.height){var f=e.resizeChild.getSize();this.el.setSize(f.width,f.height)}if(e.pinned&&!e.adjustments){e.adjustments="auto"}}this.proxy=this.el.createProxy({tag:"div",cls:"x-resizable-proxy",id:this.el.id+"-rzproxy"},Ext.getBody());this.proxy.unselectable();this.proxy.enableDisplayMode("block");Ext.apply(this,e);if(this.pinned){this.disableTrackOver=true;this.el.addClass("x-resizable-pinned")}var j=this.el.getStyle("position");if(j!="absolute"&&j!="fixed"){this.el.setStyle("position","relative")}if(!this.handles){this.handles="s,e,se";if(this.multiDirectional){this.handles+=",n,w"}}if(this.handles=="all"){this.handles="n s e w ne nw se sw"}var n=this.handles.split(/\s*?[,;]\s*?| /);var c=Ext.Resizable.positions;for(var h=0,k=n.length;h<k;h++){if(n[h]&&c[n[h]]){var m=c[n[h]];this[m]=new Ext.Resizable.Handle(this,m,this.disableTrackOver,this.transparent,this.handleCls)}}this.corner=this.southeast;if(this.handles.indexOf("n")!=-1||this.handles.indexOf("w")!=-1){this.updateBox=true}this.activeHandle=null;if(this.resizeChild){if(typeof this.resizeChild=="boolean"){this.resizeChild=Ext.get(this.el.dom.firstChild,true)}else{this.resizeChild=Ext.get(this.resizeChild,true)}}if(this.adjustments=="auto"){var b=this.resizeChild;var l=this.west,g=this.east,a=this.north,n=this.south;if(b&&(l||a)){b.position("relative");b.setLeft(l?l.el.getWidth():0);b.setTop(a?a.el.getHeight():0)}this.adjustments=[(g?-g.el.getWidth():0)+(l?-l.el.getWidth():0),(a?-a.el.getHeight():0)+(n?-n.el.getHeight():0)-1]}if(this.draggable){this.dd=this.dynamic?this.el.initDD(null):this.el.initDDProxy(null,{dragElId:this.proxy.id});this.dd.setHandleElId(this.resizeChild?this.resizeChild.id:this.el.id);if(this.constrainTo){this.dd.constrainTo(this.constrainTo)}}this.addEvents("beforeresize","resize");if(this.width!==null&&this.height!==null){this.resizeTo(this.width,this.height)}else{this.updateChildSize()}if(Ext.isIE){this.el.dom.style.zoom=1}Ext.Resizable.superclass.constructor.call(this)},adjustments:[0,0],animate:false,disableTrackOver:false,draggable:false,duration:0.35,dynamic:false,easing:"easeOutStrong",enabled:true,handles:false,multiDirectional:false,height:null,width:null,heightIncrement:0,widthIncrement:0,minHeight:5,minWidth:5,maxHeight:10000,maxWidth:10000,minX:0,minY:0,pinned:false,preserveRatio:false,resizeChild:false,transparent:false,resizeTo:function(b,a){this.el.setSize(b,a);this.updateChildSize();this.fireEvent("resize",this,b,a,null)},startSizing:function(c,b){this.fireEvent("beforeresize",this,c);if(this.enabled){if(!this.overlay){this.overlay=this.el.createProxy({tag:"div",cls:"x-resizable-overlay",html:"&#160;"},Ext.getBody());this.overlay.unselectable();this.overlay.enableDisplayMode("block");this.overlay.on({scope:this,mousemove:this.onMouseMove,mouseup:this.onMouseUp})}this.overlay.setStyle("cursor",b.el.getStyle("cursor"));this.resizing=true;this.startBox=this.el.getBox();this.startPoint=c.getXY();this.offsets=[(this.startBox.x+this.startBox.width)-this.startPoint[0],(this.startBox.y+this.startBox.height)-this.startPoint[1]];this.overlay.setSize(Ext.lib.Dom.getViewWidth(true),Ext.lib.Dom.getViewHeight(true));this.overlay.show();if(this.constrainTo){var a=Ext.get(this.constrainTo);this.resizeRegion=a.getRegion().adjust(a.getFrameWidth("t"),a.getFrameWidth("l"),-a.getFrameWidth("b"),-a.getFrameWidth("r"))}this.proxy.setStyle("visibility","hidden");this.proxy.show();this.proxy.setBox(this.startBox);if(!this.dynamic){this.proxy.setStyle("visibility","visible")}}},onMouseDown:function(a,b){if(this.enabled){b.stopEvent();this.activeHandle=a;this.startSizing(b,a)}},onMouseUp:function(b){this.activeHandle=null;var a=this.resizeElement();this.resizing=false;this.handleOut();this.overlay.hide();this.proxy.hide();this.fireEvent("resize",this,a.width,a.height,b)},updateChildSize:function(){if(this.resizeChild){var d=this.el;var e=this.resizeChild;var c=this.adjustments;if(d.dom.offsetWidth){var a=d.getSize(true);e.setSize(a.width+c[0],a.height+c[1])}if(Ext.isIE){setTimeout(function(){if(d.dom.offsetWidth){var f=d.getSize(true);e.setSize(f.width+c[0],f.height+c[1])}},10)}}},snap:function(c,e,b){if(!e||!c){return c}var d=c;var a=c%e;if(a>0){if(a>(e/2)){d=c+(e-a)}else{d=c-a}}return Math.max(b,d)},resizeElement:function(){var a=this.proxy.getBox();if(this.updateBox){this.el.setBox(a,false,this.animate,this.duration,null,this.easing)}else{this.el.setSize(a.width,a.height,this.animate,this.duration,null,this.easing)}this.updateChildSize();if(!this.dynamic){this.proxy.hide()}if(this.draggable&&this.constrainTo){this.dd.resetConstraints();this.dd.constrainTo(this.constrainTo)}return a},constrain:function(b,c,a,d){if(b-c<a){c=b-a}else{if(b-c>d){c=b-d}}return c},onMouseMove:function(v){if(this.enabled&&this.activeHandle){try{if(this.resizeRegion&&!this.resizeRegion.contains(v.getPoint())){return}var s=this.curSize||this.startBox,k=this.startBox.x,j=this.startBox.y,c=k,b=j,l=s.width,t=s.height,d=l,n=t,m=this.minWidth,z=this.minHeight,r=this.maxWidth,C=this.maxHeight,g=this.widthIncrement,a=this.heightIncrement,A=v.getXY(),q=-(this.startPoint[0]-Math.max(this.minX,A[0])),o=-(this.startPoint[1]-Math.max(this.minY,A[1])),i=this.activeHandle.position,D,f;switch(i){case"east":l+=q;l=Math.min(Math.max(m,l),r);break;case"south":t+=o;t=Math.min(Math.max(z,t),C);break;case"southeast":l+=q;t+=o;l=Math.min(Math.max(m,l),r);t=Math.min(Math.max(z,t),C);break;case"north":o=this.constrain(t,o,z,C);j+=o;t-=o;break;case"west":q=this.constrain(l,q,m,r);k+=q;l-=q;break;case"northeast":l+=q;l=Math.min(Math.max(m,l),r);o=this.constrain(t,o,z,C);j+=o;t-=o;break;case"northwest":q=this.constrain(l,q,m,r);o=this.constrain(t,o,z,C);j+=o;t-=o;k+=q;l-=q;break;case"southwest":q=this.constrain(l,q,m,r);t+=o;t=Math.min(Math.max(z,t),C);k+=q;l-=q;break}var p=this.snap(l,g,m);var B=this.snap(t,a,z);if(p!=l||B!=t){switch(i){case"northeast":j-=B-t;break;case"north":j-=B-t;break;case"southwest":k-=p-l;break;case"west":k-=p-l;break;case"northwest":k-=p-l;j-=B-t;break}l=p;t=B}if(this.preserveRatio){switch(i){case"southeast":case"east":t=n*(l/d);t=Math.min(Math.max(z,t),C);l=d*(t/n);break;case"south":l=d*(t/n);l=Math.min(Math.max(m,l),r);t=n*(l/d);break;case"northeast":l=d*(t/n);l=Math.min(Math.max(m,l),r);t=n*(l/d);break;case"north":D=l;l=d*(t/n);l=Math.min(Math.max(m,l),r);t=n*(l/d);k+=(D-l)/2;break;case"southwest":t=n*(l/d);t=Math.min(Math.max(z,t),C);D=l;l=d*(t/n);k+=D-l;break;case"west":f=t;t=n*(l/d);t=Math.min(Math.max(z,t),C);j+=(f-t)/2;D=l;l=d*(t/n);k+=D-l;break;case"northwest":D=l;f=t;t=n*(l/d);t=Math.min(Math.max(z,t),C);l=d*(t/n);j+=f-t;k+=D-l;break}}this.proxy.setBounds(k,j,l,t);if(this.dynamic){this.resizeElement()}}catch(u){}}},handleOver:function(){if(this.enabled){this.el.addClass("x-resizable-over")}},handleOut:function(){if(!this.resizing){this.el.removeClass("x-resizable-over")}},getEl:function(){return this.el},getResizeChild:function(){return this.resizeChild},destroy:function(b){Ext.destroy(this.dd,this.overlay,this.proxy);this.overlay=null;this.proxy=null;var c=Ext.Resizable.positions;for(var a in c){if(typeof c[a]!="function"&&this[c[a]]){this[c[a]].destroy()}}if(b){this.el.update("");Ext.destroy(this.el);this.el=null}this.purgeListeners()},syncHandleHeight:function(){var a=this.el.getHeight(true);if(this.west){this.west.el.setHeight(a)}if(this.east){this.east.el.setHeight(a)}}});Ext.Resizable.positions={n:"north",s:"south",e:"east",w:"west",se:"southeast",sw:"southwest",nw:"northwest",ne:"northeast"};Ext.Resizable.Handle=Ext.extend(Object,{constructor:function(d,f,c,e,a){if(!this.tpl){var b=Ext.DomHelper.createTemplate({tag:"div",cls:"x-resizable-handle x-resizable-handle-{0}"});b.compile();Ext.Resizable.Handle.prototype.tpl=b}this.position=f;this.rz=d;this.el=this.tpl.append(d.el.dom,[this.position],true);this.el.unselectable();if(e){this.el.setOpacity(0)}if(!Ext.isEmpty(a)){this.el.addClass(a)}this.el.on("mousedown",this.onMouseDown,this);if(!c){this.el.on({scope:this,mouseover:this.onMouseOver,mouseout:this.onMouseOut})}},afterResize:function(a){},onMouseDown:function(a){this.rz.onMouseDown(this,a)},onMouseOver:function(a){this.rz.handleOver(this,a)},onMouseOut:function(a){this.rz.handleOut(this,a)},destroy:function(){Ext.destroy(this.el);this.el=null}});
