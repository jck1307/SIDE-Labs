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
    var title = new Ext.ux.BubblePanel({
	bodyStyle: 'padding-left: 8px;color: #0d2a59',
	renderTo: 'bubbleCt',
	html: '<h3>Ext.ux.BubblePanel</h3',
	width: 200,
	autoHeight: true
    });
    var cp = new Ext.ux.BubblePanel({
	renderTo: 'bubbleCt',
	padding: 5,
	width: 400,
	autoHeight: true,
	contentEl: 'bubble-markup'
    });

    var plainOldPanel = new Ext.Panel({
        renderTo: 'panelCt',
	padding: 5,
	frame: true,
	width: 400,
	autoHeight: true,
	title: 'Plain Old Panel',
	html: Ext.example.bogusMarkup
    });

});


