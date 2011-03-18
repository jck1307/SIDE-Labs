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
Ext.onReady(function(){

	var activeMenu;

	function createMenu(name){
		var el = Ext.get(name+'-link');
		var tid = 0, menu, doc = Ext.getDoc();

		var handleOver = function(e, t){
			if(t != el.dom && t != menu.dom && !e.within(el) && !e.within(menu)){
				hideMenu();
			}
		};

		var hideMenu = function(){
			if(menu){
				menu.hide();
				el.setStyle('text-decoration', '');
				doc.un('mouseover', handleOver);
				doc.un('mousedown', handleDown);
			}
		};

		var handleDown = function(e){
			if(!e.within(menu)){
				hideMenu();
			}
		};

		var showMenu = function(){
			clearTimeout(tid);
			tid = 0;

			if (!menu) {
				menu = new Ext.Layer({shadow:'sides',hideMode: 'display'}, name+'-menu');
			}
			menu.hideMenu = hideMenu;

			menu.el = el;
			if(activeMenu && menu != activeMenu){
				activeMenu.hideMenu();
			}
			activeMenu = menu;

			if (!menu.isVisible()) {
				menu.show();
				menu.alignTo(el, 'tl-bl?');
				menu.sync();
				el.setStyle('text-decoration', 'underline');

				doc.on('mouseover', handleOver, null, {buffer:150});
				doc.on('mousedown', handleDown);
			}
		};

		el.on('mouseover', function(e){
			if(!tid){
				tid = showMenu.defer(150);
			}
		});

		el.on('mouseout', function(e){
			if(tid && !e.within(el, true)){
				clearTimeout(tid);
				tid = 0;
			}
		});
	}

	createMenu('products');
	createMenu('support');
	createMenu('store');

	// expanders
	Ext.getBody().on('click', function(e, t){
		t = Ext.get(t);
		e.stopEvent();

		var bd = t.next('div.expandable-body');
		bd.enableDisplayMode();
		var bdi = bd.first();
		var expanded = bd.isVisible();

		if(expanded){
			bd.hide();
		}else{
			bdi.hide();
			bd.show();
			bdi.slideIn('l', {duration:0.2, stopFx: true, easing:'easeOut'});
		}

		t.update(!expanded ? 'Hide details' : 'Show details');

	}, null, {delegate:'a.expander'});
});
