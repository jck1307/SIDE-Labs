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

    var store = new Ext.data.JsonStore({
        url: 'get-images.php',
        root: 'images',
        fields: ['name', 'url', {name:'size', type: 'float'}, {name:'lastmod', type:'date', dateFormat:'timestamp'}]
    });
    store.load();

    var listView = new Ext.list.ListView({
        store: store,
        multiSelect: true,
        emptyText: 'No images to display',
        reserveScrollOffset: true,

        columns: [{
            header: 'File',
            width: .5,
            dataIndex: 'name'
        },{
            header: 'Last Modified',
            xtype: 'datecolumn',
            format: 'm-d h:i a',
            width: .35, 
            dataIndex: 'lastmod'
        },{
            header: 'Size',
            dataIndex: 'size',
            tpl: '{size:fileSize}',
            align: 'right',
            cls: 'listview-filesize'
        }]
    });
    
    // put it in a Panel so it looks pretty
    var panel = new Ext.Panel({
        id:'images-view',
        width:425,
        height:250,
        collapsible:true,
        layout:'fit',
        title:'Simple ListView <i>(0 items selected)</i>',
        items: listView
    });
    panel.render(document.body);

    // little bit of feedback
    listView.on('selectionchange', function(view, nodes){
        var l = nodes.length;
        var s = l != 1 ? 's' : '';
        panel.setTitle('Simple ListView <i>('+l+' item'+s+' selected)</i>');
    });
});
