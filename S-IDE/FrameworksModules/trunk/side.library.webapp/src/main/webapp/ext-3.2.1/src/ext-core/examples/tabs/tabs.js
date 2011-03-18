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
Ext.ns('Ext.ux');

Ext.ux.Tabs = Ext.extend(Ext.util.Observable, {
	// Configuration options
    activeTab: 0,
    
	// Our class constructor
    constructor : function(element, config) {
        Ext.apply(this, config);
        Ext.ux.Tabs.superclass.constructor.call(this);
        
        this.addEvents(
            'beforetabchange',
            'tabchange'
        );
        
        this.el = Ext.get(element);
        this.init();
    },
    
    init : function() {
        var me = this;

		this.el.addClass('ux-tabs-container');
		
		this.tabStrip = this.el.child('ul');
		this.tabStrip.addClass('ux-tabs-strip');
		
		this.tabStrip.on('click', this.onStripClick, this, {delegate: 'a'});
		
		this.tabs = this.tabStrip.select('> li');
		this.cards = this.el.select('> div');
		
		this.cardsContainer = this.el.createChild({
			cls: 'ux-tabs-cards'
		});		
		this.cardsContainer.setWidth(this.el.getWidth());
		
		this.cards.addClass('ux-tabs-card');
		this.cards.appendTo(this.cardsContainer);
		
		this.el.createChild({
			cls: 'ux-tabs-clearfix'
		});
		
		this.setActiveTab(this.activeTab || 0);
	},
	
	onStripClick : function(ev, t) {
		if(t && t.href && t.href.indexOf('#')) {
			ev.preventDefault();			
			this.setActiveTab(t.href.split('#')[1]);
		}
	},
	
	setActiveTab : function(tab) {
		var card;		
		if(Ext.isString(tab)) {
			card = Ext.get(tab);
			tab = this.tabStrip.child('a[href=#' + tab + ']').parent();
		}
		else if (Ext.isNumber(tab)) {
			tab = this.tabs.item(tab);
			card = Ext.get(tab.first().dom.href.split('#')[1]);
		}
		
		if(tab && card && this.fireEvent('beforetabchange', tab, card) !== false) {
			card.radioClass('ux-tabs-card-active');
			tab.radioClass('ux-tabs-tab-active');
			this.fireEvent('tabchange', tab, card);
		}
	}
});
