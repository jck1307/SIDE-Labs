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
Ext.onReady(function() {
    Ext.QuickTips.init();
    
    var form = new Ext.form.FormPanel({
        renderTo: 'docbody',
        title   : 'Composite Fields',
        autoHeight: true,
        width   : 600,
        
        bodyStyle: 'padding: 5px',
        defaults: {
            anchor: '0'
        },
        items   : [
            {
                xtype     : 'textfield',
                name      : 'email',
                fieldLabel: 'Email Address',
                anchor    : '-20'
            },
            {
                xtype: 'compositefield',
                fieldLabel: 'Date Range',
                msgTarget : 'side',
                anchor    : '-20',
                defaults: {
                    flex: 1
                },
                items: [
                    {
                        xtype     : 'datefield',
                        name      : 'startDate',
                        fieldLabel: 'Start'
                    },
                    {
                        xtype     : 'datefield',
                        name      : 'endDate',
                        fieldLabel: 'End'
                    }
                ]
            },
            {
                xtype: 'fieldset',
                title: 'Details',
                collapsible: true,
                items: [
                    {
                        xtype: 'compositefield',
                        fieldLabel: 'Phone',
                        // anchor    : '-20',
                        // anchor    : null,
                        msgTarget: 'under',
                        items: [
                            {xtype: 'displayfield', value: '('},
                            {xtype: 'textfield',    name: 'phone-1', width: 29, allowBlank: false},
                            {xtype: 'displayfield', value: ')'},
                            {xtype: 'textfield',    name: 'phone-2', width: 29, allowBlank: false, margins: '0 5 0 0'},
                            {xtype: 'textfield',    name: 'phone-3', width: 48, allowBlank: false}
                        ]
                    },
                    {
                        xtype: 'compositefield',
                        fieldLabel: 'Time worked',
                        combineErrors: false,
                        items: [
                           {
                               name : 'hours',
                               xtype: 'numberfield',
                               width: 48,
                               allowBlank: false
                           },
                           {
                               xtype: 'displayfield',
                               value: 'hours'
                           },
                           {
                               name : 'minutes',
                               xtype: 'numberfield',
                               width: 48,
                               allowBlank: false
                           },
                           {
                               xtype: 'displayfield',
                               value: 'mins'
                           }
                        ]
                    },
                    {
                        xtype : 'compositefield',
                        anchor: '-20',
                        msgTarget: 'side',
                        fieldLabel: 'Full Name',
                        items : [
                            {
                                //the width of this field in the HBox layout is set directly
                                //the other 2 items are given flex: 1, so will share the rest of the space
                                width:          50,


                                xtype:          'combo',
                                mode:           'local',
                                value:          'mrs',
                                triggerAction:  'all',
                                forceSelection: true,
                                editable:       false,
                                fieldLabel:     'Title',
                                name:           'title',
                                hiddenName:     'title',
                                displayField:   'name',
                                valueField:     'value',
                                store:          new Ext.data.JsonStore({
                                    fields : ['name', 'value'],
                                    data   : [
                                        {name : 'Mr',   value: 'mr'},
                                        {name : 'Mrs',  value: 'mrs'},
                                        {name : 'Miss', value: 'miss'}
                                    ]
                                })
                            },
                            {
                                xtype: 'textfield',
                                flex : 1,
                                name : 'firstName',
                                fieldLabel: 'First',
                                allowBlank: false
                            },
                            {
                                xtype: 'textfield',
                                flex : 1,
                                name : 'lastName',
                                fieldLabel: 'Last',
                                allowBlank: false
                            }
                        ]
                    }
                ]
            }
        ],
        buttons: [
            {
                text   : 'Load test data',
                handler: function() {
                    var Record = Ext.data.Record.create([
                       {name: 'email',     type: 'string'},
                       {name: 'title',     type: 'string'},
                       {name: 'firstName', type: 'string'},
                       {name: 'lastName',  type: 'string'},
                       {name: 'phone-1',   type: 'string'},
                       {name: 'phone-2',   type: 'string'},
                       {name: 'phone-3',   type: 'string'},
                       {name: 'hours',     type: 'number'},
                       {name: 'minutes',   type: 'number'},
                       {name: 'startDate', type: 'date'},
                       {name: 'endDate',   type: 'date'}
                    ]);
                    
                    form.form.loadRecord(new Record({
                        'email'    : 'ed@extjs.com',
                        'title'    : 'mr',
                        'firstName': 'Abraham',
                        'lastName' : 'Elias',
                        'startDate': '01/10/2003',
                        'endDate'  : '12/11/2009',
                        'phone-1'  : '555',
                        'phone-2'  : '123',
                        'phone-3'  : '4567',
                        'hours'    : 7,
                        'minutes'  : 15
                    }));
                }
            },
            {
                text   : 'Save',
                handler: function() {
                    if (form.form.isValid()) {
                        var s = '';
                    
                        Ext.iterate(form.form.getValues(), function(key, value) {
                            s += String.format("{0} = {1}<br />", key, value);
                        }, this);
                    
                        Ext.example.msg('Form Values', s);                        
                    }
                }
            },
            
            {
                text   : 'Reset',
                handler: function() {
                    form.form.reset();
                }
            }
        ]
    });
});
