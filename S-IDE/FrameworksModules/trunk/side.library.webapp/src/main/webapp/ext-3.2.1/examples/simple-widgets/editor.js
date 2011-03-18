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
    var ct = new Ext.form.FormPanel({
        renderTo: 'container',
        width: 700,
        height: 400,
        title: 'User Details',
        defaultType: 'textfield',
        padding: 10,
        labelWidth: 90,
        items: [{
            fieldLabel: 'First Name'
        }, {
            fieldLabel: 'Middle Name'
        }, {
            fieldLabel: 'Last Name'
        }, {
            xtype: 'datefield',
            fieldLabel: 'D.O.B'
        }],
        listeners: {
            afterrender: function(form){
                var cfg = {
                    shadow: false,
                    completeOnEnter: true,
                    cancelOnEsc: true,
                    updateEl: true,
                    ignoreNoChange: true
                };

                var labelEditor = new Ext.Editor(Ext.apply({
                    alignment: 'l-l',
                    listeners: {
                        beforecomplete: function(ed, value){
                            if(value.charAt(value.length - 1) != ':'){
                                ed.setValue(ed.getValue() + ':');
                            }
                            return true;
                        },
                        complete: function(ed, value, oldValue){
                            Ext.example.msg('Label Changed', '"{0}" changed to "{1}"', oldValue, value);
                        }
                    },
                    field: {
                        allowBlank: false,
                        xtype: 'textfield',
                        width: 90,
                        selectOnFocus: true
                    }
                }, cfg));
                form.body.on('dblclick', function(e, t){
                    labelEditor.startEdit(t);
                }, null, {
                    delegate: 'label.x-form-item-label'
                });

                var titleEditor = new Ext.Editor(Ext.apply({
                    cls: 'x-small-editor',
                    alignment: 'bl-bl?',
                    offsets: [0, 3],
                    listeners: {
                        complete: function(ed, value, oldValue){
                            Ext.example.msg('Title Changed', '"{0}" changed to "{1}"', oldValue, value);
                        }
                    },
                    field: {
                        width: 110,
                        triggerAction: 'all',
                        xtype: 'combo',
                        editable: false,
                        forceSelection: true,
                        store: ['User Details', 'Developer Details', 'Manager Details']
                    }
                }, cfg));

                form.header.child('.x-panel-header-text').on('dblclick', function(e, t){
                    titleEditor.startEdit(t);
                });
            }
        }
    });
});
