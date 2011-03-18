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
    var p = new Ext.Panel({
        title: 'My Panel',
        collapsible:true,
        renderTo: 'panel-basic',
        width:400,
        html: Ext.example.bogusMarkup
    });

	// preventBodyReset: true
	new Ext.Panel({
		title: 'A Panel with W3C-suggested body-html styling',
		preventBodyReset: true,
		renderTo: 'panel-reset-true',
		width: 400,
		html: html.join('')
	});

	// preventBodyReset: false
	new Ext.Panel({
		title: 'Same panel as above with preventBodyReset: false',
		normal: false,
		renderTo: 'panel-reset-false',
		width: 400,
		html: html.join('')
	});
});

// Some sample html
var html = [
	'<h1>Heading One</h1>',
	'<h2>Heading Two</h2>',
	'<p>This is a paragraph with <strong>STRONG</strong>, <em>EMPHASIS</em> and a <a href="#">Link</a></p>',
	'<table>',
		'<tr>',
			'<td>Table Column One</td>',
			'<td>Table Column Two</td>',
		'</tr>',
	'</table>',
	'<ul>',
		'<li>Un-ordered List-item One</li>',
		'<li>Un-ordered List-item One</li>',
	'</ul>',
	'<ol>',
		'<li>Ordered List-item One</li>',
		'<li>Ordered List-item Two</li>',
	'</ol>',
	'<blockquote>This is a blockquote</blockquote>'
];
