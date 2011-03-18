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
 * @class Ext.menu.ColorMenu
 * @extends Ext.menu.Menu
 * <p>A menu containing a {@link Ext.ColorPalette} Component.</p>
 * <p>Notes:</p><div class="mdetail-params"><ul>
 * <li>Although not listed here, the <b>constructor</b> for this class
 * accepts all of the configuration options of <b>{@link Ext.ColorPalette}</b>.</li>
 * <li>If subclassing ColorMenu, any configuration options for the ColorPalette must be
 * applied to the <tt><b>initialConfig</b></tt> property of the ColorMenu.
 * Applying {@link Ext.ColorPalette ColorPalette} configuration settings to
 * <b><tt>this</tt></b> will <b>not</b> affect the ColorPalette's configuration.</li>
 * </ul></div> * 
 * @xtype colormenu
 */
 Ext.menu.ColorMenu = Ext.extend(Ext.menu.Menu, {
    /** 
     * @cfg {Boolean} enableScrolling
     * @hide 
     */
    enableScrolling : false,
    /**
     * @cfg {Function} handler
     * Optional. A function that will handle the select event of this menu.
     * The handler is passed the following parameters:<div class="mdetail-params"><ul>
     * <li><code>palette</code> : ColorPalette<div class="sub-desc">The {@link #palette Ext.ColorPalette}.</div></li>
     * <li><code>color</code> : String<div class="sub-desc">The 6-digit color hex code (without the # symbol).</div></li>
     * </ul></div>
     */
    /**
     * @cfg {Object} scope
     * The scope (<tt><b>this</b></tt> reference) in which the <code>{@link #handler}</code>
     * function will be called.  Defaults to this ColorMenu instance.
     */    
    
    /** 
     * @cfg {Boolean} hideOnClick
     * False to continue showing the menu after a color is selected, defaults to true.
     */
    hideOnClick : true,
    
    cls : 'x-color-menu',
    
    /** 
     * @cfg {String} paletteId
     * An id to assign to the underlying color palette. Defaults to <tt>null</tt>.
     */
    paletteId : null,
    
    /** 
     * @cfg {Number} maxHeight
     * @hide 
     */
    /** 
     * @cfg {Number} scrollIncrement
     * @hide 
     */
    /**
     * @property palette
     * @type ColorPalette
     * The {@link Ext.ColorPalette} instance for this ColorMenu
     */
    
    
    /**
     * @event click
     * @hide
     */
    
    /**
     * @event itemclick
     * @hide
     */
    
    initComponent : function(){
        Ext.apply(this, {
            plain: true,
            showSeparator: false,
            items: this.palette = new Ext.ColorPalette(Ext.applyIf({
                id: this.paletteId
            }, this.initialConfig))
        });
        this.palette.purgeListeners();
        Ext.menu.ColorMenu.superclass.initComponent.call(this);
        /**
         * @event select
         * Fires when a color is selected from the {@link #palette Ext.ColorPalette}
         * @param {Ext.ColorPalette} palette The {@link #palette Ext.ColorPalette}
	     * @param {String} color The 6-digit color hex code (without the # symbol)
         */
        this.relayEvents(this.palette, ['select']);
        this.on('select', this.menuHide, this);
        if(this.handler){
            this.on('select', this.handler, this.scope || this);
        }
    },

    menuHide : function(){
        if(this.hideOnClick){
            this.hide(true);
        }
    }
});
Ext.reg('colormenu', Ext.menu.ColorMenu);
