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
 * @class Ext.Viewport
 * @extends Ext.Container
 * <p>A specialized container representing the viewable application area (the browser viewport).</p>
 * <p>The Viewport renders itself to the document body, and automatically sizes itself to the size of
 * the browser viewport and manages window resizing. There may only be one Viewport created
 * in a page. Inner layouts are available by virtue of the fact that all {@link Ext.Panel Panel}s
 * added to the Viewport, either through its {@link #items}, or through the items, or the {@link #add}
 * method of any of its child Panels may themselves have a layout.</p>
 * <p>The Viewport does not provide scrolling, so child Panels within the Viewport should provide
 * for scrolling if needed using the {@link #autoScroll} config.</p>
 * <p>An example showing a classic application border layout:</p><pre><code>
new Ext.Viewport({
    layout: 'border',
    items: [{
        region: 'north',
        html: '&lt;h1 class="x-panel-header">Page Title&lt;/h1>',
        autoHeight: true,
        border: false,
        margins: '0 0 5 0'
    }, {
        region: 'west',
        collapsible: true,
        title: 'Navigation',
        width: 200
        // the west region might typically utilize a {@link Ext.tree.TreePanel TreePanel} or a Panel with {@link Ext.layout.AccordionLayout Accordion layout}
    }, {
        region: 'south',
        title: 'Title for Panel',
        collapsible: true,
        html: 'Information goes here',
        split: true,
        height: 100,
        minHeight: 100
    }, {
        region: 'east',
        title: 'Title for the Grid Panel',
        collapsible: true,
        split: true,
        width: 200,
        xtype: 'grid',
        // remaining grid configuration not shown ...
        // notice that the GridPanel is added directly as the region
        // it is not "overnested" inside another Panel
    }, {
        region: 'center',
        xtype: 'tabpanel', // TabPanel itself has no title
        items: {
            title: 'Default Tab',
            html: 'The first tab\'s content. Others may be added dynamically'
        }
    }]
});
</code></pre>
 * @constructor
 * Create a new Viewport
 * @param {Object} config The config object
 * @xtype viewport
 */
Ext.Viewport = Ext.extend(Ext.Container, {
    /*
     * Privatize config options which, if used, would interfere with the
     * correct operation of the Viewport as the sole manager of the
     * layout of the document body.
     */
    /**
     * @cfg {Mixed} applyTo @hide
     */
    /**
     * @cfg {Boolean} allowDomMove @hide
     */
    /**
     * @cfg {Boolean} hideParent @hide
     */
    /**
     * @cfg {Mixed} renderTo @hide
     */
    /**
     * @cfg {Boolean} hideParent @hide
     */
    /**
     * @cfg {Number} height @hide
     */
    /**
     * @cfg {Number} width @hide
     */
    /**
     * @cfg {Boolean} autoHeight @hide
     */
    /**
     * @cfg {Boolean} autoWidth @hide
     */
    /**
     * @cfg {Boolean} deferHeight @hide
     */
    /**
     * @cfg {Boolean} monitorResize @hide
     */

    initComponent : function() {
        Ext.Viewport.superclass.initComponent.call(this);
        document.getElementsByTagName('html')[0].className += ' x-viewport';
        this.el = Ext.getBody();
        this.el.setHeight = Ext.emptyFn;
        this.el.setWidth = Ext.emptyFn;
        this.el.setSize = Ext.emptyFn;
        this.el.dom.scroll = 'no';
        this.allowDomMove = false;
        this.autoWidth = true;
        this.autoHeight = true;
        Ext.EventManager.onWindowResize(this.fireResize, this);
        this.renderTo = this.el;
    },

    fireResize : function(w, h){
        this.fireEvent('resize', this, w, h, w, h);
    }
});
Ext.reg('viewport', Ext.Viewport);
