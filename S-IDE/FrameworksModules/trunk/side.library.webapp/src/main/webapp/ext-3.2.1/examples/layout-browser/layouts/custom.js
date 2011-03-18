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
/*
 * CenterLayout demo panel
 */
var centerLayout = {
	id: 'center-panel',
    layout: 'ux.center',
    items: {
        title: 'Centered Panel: 75% of container width and fit height',
        layout: 'ux.center',
        autoScroll: true,
        width: '75%',
        bodyStyle: 'padding:20px 0;',
        items: [{
        	title: 'Inner Centered Panel',
        	html: 'Fixed 300px wide and auto height. The container panel will also autoscroll if narrower than 300px.',
        	width: 300,
        	frame: true,
        	autoHeight: true,
        	bodyStyle: 'padding:10px 20px;'
        }]
    }
};

/*
 * RowLayout demo panel
 */
var rowLayout = {
	id: 'row-panel',
	bodyStyle: 'padding:5px',
	layout: 'ux.row',
    title: 'Row Layout',
    items: [{
        title: 'Height = 25%, Width = 50%',
        rowHeight: 0.25,
        width: '50%'
    },{
        title: 'Height = 100px, Width = 300px',
        height: 100,
        width: 300
    },{
    	title: 'Height = 75%, Width = fit',
    	rowHeight: 0.75
    }]
};
