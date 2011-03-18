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

    var handleAction = function(action){
        Ext.example.msg('<b>Action</b>', 'You clicked "'+action+'"');
    };
    
    var p = new Ext.Window({
        title: 'Standard',
        closable: false,
        height:250,
        width: 500,
        bodyStyle: 'padding:10px',
        contentEl: 'content',
        autoScroll: true,
        tbar: new Ext.Toolbar({
            enableOverflow: true,
            items: [{
                xtype:'splitbutton',
                text: 'Menu Button',
                iconCls: 'add16',
                handler: handleAction.createCallback('Menu Button'),
                menu: [{text: 'Menu Item 1', handler: handleAction.createCallback('Menu Item 1')}]
            },'-',{
                xtype:'splitbutton',
                text: 'Cut',
                iconCls: 'add16',
                handler: handleAction.createCallback('Cut'),
                menu: [{text: 'Cut menu', handler: handleAction.createCallback('Cut menu')}]
            },{
                text: 'Copy',
                iconCls: 'add16',
                handler: handleAction.createCallback('Copy')
            },{
                text: 'Paste',
                iconCls: 'add16',
                menu: [{text: 'Paste menu', handler: handleAction.createCallback('Paste menu')}]
            },'-',{
                text: 'Format',
                iconCls: 'add16',
                handler: handleAction.createCallback('Format')
            },'->',{
                text: 'Right',
                iconCls: 'add16',
                handler: handleAction.createCallback('Right')
            }]
        })
    });
    p.show();

});
