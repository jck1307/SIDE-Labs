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
    var tree = new Ext.ux.tree.ColumnTree({
        width: 550,
        height: 300,
        rootVisible:false,
        autoScroll:true,
        title: 'Example Tasks',
        renderTo: Ext.getBody(),

        columns:[{
            header:'Task',
            width:330,
            dataIndex:'task'
        },{
            header:'Duration',
            width:100,
            dataIndex:'duration'
        },{
            header:'Assigned To',
            width:100,
            dataIndex:'user'
        }],

        loader: new Ext.tree.TreeLoader({
            dataUrl:'column-data.json',
            uiProviders:{
                'col': Ext.ux.tree.ColumnNodeUI
            }
        }),

        root: new Ext.tree.AsyncTreeNode({
            text:'Tasks'
        })
    });
});
