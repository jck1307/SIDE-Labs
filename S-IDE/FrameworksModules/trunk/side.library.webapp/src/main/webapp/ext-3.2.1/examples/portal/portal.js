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

    // NOTE: This is an example showing simple state management. During development,
    // it is generally best to disable state management as dynamically-generated ids
    // can change across page loads, leading to unpredictable results.  The developer
    // should ensure that stable state ids are set for stateful components in real apps.
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

    // create some portlet tools using built in Ext tool ids
    var tools = [{
        id:'gear',
        handler: function(){
            Ext.Msg.alert('Message', 'The Settings tool was clicked.');
        }
    },{
        id:'close',
        handler: function(e, target, panel){
            panel.ownerCt.remove(panel, true);
        }
    }];

    var viewport = new Ext.Viewport({
        layout:'border',
        items:[{
            region:'west',
            id:'west-panel',
            title:'West',
            split:true,
            width: 200,
            minSize: 175,
            maxSize: 400,
            collapsible: true,
            margins:'35 0 5 5',
            cmargins:'35 5 5 5',
            layout:'accordion',
            layoutConfig:{
                animate:true
            },
            items: [{
                html: Ext.example.shortBogusMarkup,
                title:'Navigation',
                autoScroll:true,
                border:false,
                iconCls:'nav'
            },{
                title:'Settings',
                html: Ext.example.shortBogusMarkup,
                border:false,
                autoScroll:true,
                iconCls:'settings'
            }]
        },{
            xtype:'portal',
            region:'center',
            margins:'35 5 5 0',
            items:[{
                columnWidth:.33,
                style:'padding:10px 0 10px 10px',
                items:[{
                    title: 'Grid in a Portlet',
                    layout:'fit',
                    tools: tools,
                    items: new SampleGrid([0, 2, 3])
                },{
                    title: 'Another Panel 1',
                    tools: tools,
                    html: Ext.example.shortBogusMarkup
                }]
            },{
                columnWidth:.33,
                style:'padding:10px 0 10px 10px',
                items:[{
                    title: 'Panel 2',
                    tools: tools,
                    html: Ext.example.shortBogusMarkup
                },{
                    title: 'Another Panel 2',
                    tools: tools,
                    html: Ext.example.shortBogusMarkup
                }]
            },{
                columnWidth:.33,
                style:'padding:10px',
                items:[{
                    title: 'Panel 3',
                    tools: tools,
                    html: Ext.example.shortBogusMarkup
                },{
                    title: 'Another Panel 3',
                    tools: tools,
                    html: Ext.example.shortBogusMarkup
                }]
            }]
            
            /*
             * Uncomment this block to test handling of the drop event. You could use this
             * to save portlet position state for example. The event arg e is the custom 
             * event defined in Ext.ux.Portal.DropZone.
             */
//            ,listeners: {
//                'drop': function(e){
//                    Ext.Msg.alert('Portlet Dropped', e.panel.title + '<br />Column: ' + 
//                        e.columnIndex + '<br />Position: ' + e.position);
//                }
//            }
        }]
    });
});

