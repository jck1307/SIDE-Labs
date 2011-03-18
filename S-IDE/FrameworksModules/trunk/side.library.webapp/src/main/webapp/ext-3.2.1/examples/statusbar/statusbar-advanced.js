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
Ext.QuickTips.init();

Ext.onReady(function(){

    var fp = new Ext.FormPanel({
        id: 'status-form',
        renderTo: Ext.getBody(),
        labelWidth: 75,
        width: 350,
        buttonAlign: 'right',
        border: false,
        bodyStyle: 'padding:10px 10px 0;',
        defaults: {
            anchor: '95%',
            allowBlank: false,
            selectOnFocus: true,
            msgTarget: 'side'
        },
        items:[{
            xtype: 'textfield',
            fieldLabel: 'Name',
            blankText: 'Name is required'
        },{
            xtype: 'datefield',
            fieldLabel: 'Birthdate',
            blankText: 'Birthdate is required'
        }],
        buttons: [{
            text: 'Save',
            handler: function(){
                if(fp.getForm().isValid()){
                    var sb = Ext.getCmp('form-statusbar');
                    sb.showBusy('Saving form...');
                    fp.getEl().mask();
                    fp.getForm().submit({
                        url: 'fake.php',
                        success: function(){
                            sb.setStatus({
                                text:'Form saved!',
                                iconCls:'',
                                clear: true
                            });
                            fp.getEl().unmask();
                        }
                    });
                }
            }
        }]
    });

    new Ext.Panel({
        title: 'StatusBar with Integrated Form Validation',
        renderTo: Ext.getBody(),
        width: 350,
        autoHeight: true,
        layout: 'fit',
        items: fp,
        bbar: new Ext.ux.StatusBar({
            id: 'form-statusbar',
            defaultText: 'Ready',
            plugins: new Ext.ux.ValidationStatus({form:'status-form'})
        })
    });

});
