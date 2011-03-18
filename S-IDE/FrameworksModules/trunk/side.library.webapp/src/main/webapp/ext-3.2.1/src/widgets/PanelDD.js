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


/*!
 * Ext JS Library 3.2.1
 * Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
/**
 * @class Ext.dd.PanelProxy
 * A custom drag proxy implementation specific to {@link Ext.Panel}s. This class is primarily used internally
 * for the Panel's drag drop implementation, and should never need to be created directly.
 * @constructor
 * @param panel The {@link Ext.Panel} to proxy for
 * @param config Configuration options
 */
Ext.dd.PanelProxy = function(panel, config){
    this.panel = panel;
    this.id = this.panel.id +'-ddproxy';
    Ext.apply(this, config);
};

Ext.dd.PanelProxy.prototype = {
    /**
     * @cfg {Boolean} insertProxy True to insert a placeholder proxy element while dragging the panel,
     * false to drag with no proxy (defaults to true).
     */
    insertProxy : true,

    // private overrides
    setStatus : Ext.emptyFn,
    reset : Ext.emptyFn,
    update : Ext.emptyFn,
    stop : Ext.emptyFn,
    sync: Ext.emptyFn,

    /**
     * Gets the proxy's element
     * @return {Element} The proxy's element
     */
    getEl : function(){
        return this.ghost;
    },

    /**
     * Gets the proxy's ghost element
     * @return {Element} The proxy's ghost element
     */
    getGhost : function(){
        return this.ghost;
    },

    /**
     * Gets the proxy's element
     * @return {Element} The proxy's element
     */
    getProxy : function(){
        return this.proxy;
    },

    /**
     * Hides the proxy
     */
    hide : function(){
        if(this.ghost){
            if(this.proxy){
                this.proxy.remove();
                delete this.proxy;
            }
            this.panel.el.dom.style.display = '';
            this.ghost.remove();
            delete this.ghost;
        }
    },

    /**
     * Shows the proxy
     */
    show : function(){
        if(!this.ghost){
            this.ghost = this.panel.createGhost(undefined, undefined, Ext.getBody());
            this.ghost.setXY(this.panel.el.getXY());
            if(this.insertProxy){
                this.proxy = this.panel.el.insertSibling({cls:'x-panel-dd-spacer'});
                this.proxy.setSize(this.panel.getSize());
            }
            this.panel.el.dom.style.display = 'none';
        }
    },

    // private
    repair : function(xy, callback, scope){
        this.hide();
        if(typeof callback == "function"){
            callback.call(scope || this);
        }
    },

    /**
     * Moves the proxy to a different position in the DOM.  This is typically called while dragging the Panel
     * to keep the proxy sync'd to the Panel's location.
     * @param {HTMLElement} parentNode The proxy's parent DOM node
     * @param {HTMLElement} before (optional) The sibling node before which the proxy should be inserted (defaults
     * to the parent's last child if not specified)
     */
    moveProxy : function(parentNode, before){
        if(this.proxy){
            parentNode.insertBefore(this.proxy.dom, before);
        }
    }
};

// private - DD implementation for Panels
Ext.Panel.DD = function(panel, cfg){
    this.panel = panel;
    this.dragData = {panel: panel};
    this.proxy = new Ext.dd.PanelProxy(panel, cfg);
    Ext.Panel.DD.superclass.constructor.call(this, panel.el, cfg);
    var h = panel.header;
    if(h){
        this.setHandleElId(h.id);
    }
    (h ? h : this.panel.body).setStyle('cursor', 'move');
    this.scroll = false;
};

Ext.extend(Ext.Panel.DD, Ext.dd.DragSource, {
    showFrame: Ext.emptyFn,
    startDrag: Ext.emptyFn,
    b4StartDrag: function(x, y) {
        this.proxy.show();
    },
    b4MouseDown: function(e) {
        var x = e.getPageX();
        var y = e.getPageY();
        this.autoOffset(x, y);
    },
    onInitDrag : function(x, y){
        this.onStartDrag(x, y);
        return true;
    },
    createFrame : Ext.emptyFn,
    getDragEl : function(e){
        return this.proxy.ghost.dom;
    },
    endDrag : function(e){
        this.proxy.hide();
        this.panel.saveState();
    },

    autoOffset : function(x, y) {
        x -= this.startPageX;
        y -= this.startPageY;
        this.setDelta(x, y);
    }
});
