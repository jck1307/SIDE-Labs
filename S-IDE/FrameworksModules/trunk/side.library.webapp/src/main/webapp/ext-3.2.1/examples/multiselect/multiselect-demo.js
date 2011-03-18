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

    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';

    /*
     * Ext.ux.form.MultiSelect Example Code
     */
    var msForm = new Ext.form.FormPanel({
        title: 'MultiSelect Test',
        width: 700,
        bodyStyle: 'padding:10px;',
        renderTo: 'multiselect',
        items:[{
            xtype: 'multiselect',
            fieldLabel: 'Multiselect<br />(Required)',
            name: 'multiselect',
            width: 250,
            height: 200,
            allowBlank:false,
            store: [[123,'One Hundred Twenty Three'],
                    ['1', 'One'], ['2', 'Two'], ['3', 'Three'], ['4', 'Four'], ['5', 'Five'],
                    ['6', 'Six'], ['7', 'Seven'], ['8', 'Eight'], ['9', 'Nine']],
            tbar:[{
                text: 'clear',
                handler: function(){
	                msForm.getForm().findField('multiselect').reset();
	            }
            }],
            ddReorder: true
        }],
        tbar:[{
            text: 'Options',
            menu: [{
	            text: 'Set Value (2,3)',
	            handler: function(){
	                msForm.getForm().findField('multiselect').setValue('2,3');
	            }
	        },{
	            text: 'Toggle Enabled',
	            handler: function(){
	                var m = msForm.getForm().findField('multiselect');
	                if (!m.disabled) {
	                    m.disable();
	                } else {
	                    m.enable();
	                }
	            }
            }]
        }],

        buttons: [{
            text: 'Save',
            handler: function(){
                if(msForm.getForm().isValid()){
	                Ext.Msg.alert('Submitted Values', 'The following will be sent to the server: <br />'+
	                    msForm.getForm().getValues(true));
                }
            }
        }]
    });


    var ds = new Ext.data.ArrayStore({
        data: [[123,'One Hundred Twenty Three'],
            ['1', 'One'], ['2', 'Two'], ['3', 'Three'], ['4', 'Four'], ['5', 'Five'],
            ['6', 'Six'], ['7', 'Seven'], ['8', 'Eight'], ['9', 'Nine']],
        fields: ['value','text'],
        sortInfo: {
            field: 'value',
            direction: 'ASC'
        }
    });

    /*
     * Ext.ux.form.ItemSelector Example Code
     */
    var isForm = new Ext.form.FormPanel({
        title: 'ItemSelector Test',
        width:700,
        bodyStyle: 'padding:10px;',
        renderTo: 'itemselector',
        items:[{
            xtype: 'itemselector',
            name: 'itemselector',
            fieldLabel: 'ItemSelector',
	        imagePath: '../ux/images/',
            multiselects: [{
                width: 250,
                height: 200,
                store: ds,
                displayField: 'text',
                valueField: 'value'
            },{
                width: 250,
                height: 200,
                store: [['10','Ten']],
                tbar:[{
                    text: 'clear',
                    handler:function(){
	                    isForm.getForm().findField('itemselector').reset();
	                }
                }]
            }]
        }],

        buttons: [{
            text: 'Save',
            handler: function(){
                if(isForm.getForm().isValid()){
                    Ext.Msg.alert('Submitted Values', 'The following will be sent to the server: <br />'+
                        isForm.getForm().getValues(true));
                }
            }
        }]
    });

});
