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

//
// Extend the XmlTreeLoader to set some custom TreeNode attributes specific to our application:
//
Ext.app.BookLoader = Ext.extend(Ext.ux.tree.XmlTreeLoader, {
    processAttributes : function(attr){
        if(attr.first){ // is it an author node?

            // Set the node text that will show in the tree since our raw data does not include a text attribute:
            attr.text = attr.first + ' ' + attr.last;

            // Author icon, using the gender flag to choose a specific icon:
            attr.iconCls = 'author-' + attr.gender;

            // Override these values for our folder nodes because we are loading all data at once.  If we were
            // loading each node asynchronously (the default) we would not want to do this:
            attr.loaded = true;
            attr.expanded = true;
        }
        else if(attr.title){ // is it a book node?

            // Set the node text that will show in the tree since our raw data does not include a text attribute:
            attr.text = attr.title + ' (' + attr.published + ')';

            // Book icon:
            attr.iconCls = 'book';

            // Tell the tree this is a leaf node.  This could also be passed as an attribute in the original XML,
            // but this example demonstrates that you can control this even when you cannot dictate the format of
            // the incoming source XML:
            attr.leaf = true;
        }
    }
});

Ext.onReady(function(){

    var detailsText = '<i>Select a book to see more information...</i>';

	var tpl = new Ext.Template(
        '<h2 class="title">{title}</h2>',
        '<p><b>Published</b>: {published}</p>',
        '<p><b>Synopsis</b>: {innerText}</p>',
        '<p><a href="{url}" target="_blank">Purchase from Amazon</a></p>'
	);
    tpl.compile();

    new Ext.Panel({
        title: 'Reading List',
	    renderTo: 'tree',
        layout: 'border',
	    width: 500,
        height: 500,
        items: [{
            xtype: 'treepanel',
            id: 'tree-panel',
            region: 'center',
            margins: '2 2 0 2',
            autoScroll: true,
	        rootVisible: false,
	        root: new Ext.tree.AsyncTreeNode(),

            // Our custom TreeLoader:
	        loader: new Ext.app.BookLoader({
	            dataUrl:'xml-tree-data.xml'
	        }),

	        listeners: {
	            'render': function(tp){
                    tp.getSelectionModel().on('selectionchange', function(tree, node){
                        var el = Ext.getCmp('details-panel').body;
	                    if(node && node.leaf){
	                        tpl.overwrite(el, node.attributes);
	                    }else{
                            el.update(detailsText);
                        }
                    })
	            }
	        }
        },{
            region: 'south',
            title: 'Book Details',
            id: 'details-panel',
            autoScroll: true,
            collapsible: true,
            split: true,
            margins: '0 2 2 2',
            cmargins: '2 2 2 2',
            height: 220,
            html: detailsText
        }]
    });
});
