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

    /**
     * Handler specified for the 'Available' column renderer
     * @param {Object} value
     */
    function formatDate(value){
        return value ? value.dateFormat('M d, Y') : '';
    }

    // shorthand alias
    var fm = Ext.form;

    // the check column is created using a custom plugin
    var checkColumn = new Ext.grid.CheckColumn({
       header: 'Indoor?',
       dataIndex: 'indoor',
       width: 55
    });

    // the column model has information about grid columns
    // dataIndex maps the column to the specific data field in
    // the data store (created below)
    var cm = new Ext.grid.ColumnModel({
        // specify any defaults for each column
        defaults: {
            sortable: true // columns are not sortable by default           
        },
        columns: [
            {
                id: 'common',
                header: 'Common Name',
                dataIndex: 'common',
                width: 220,
                // use shorthand alias defined above
                editor: new fm.TextField({
                    allowBlank: false
                })
            }, {
                header: 'Light',
                dataIndex: 'light',
                width: 130,
                editor: new fm.ComboBox({
                    typeAhead: true,
                    triggerAction: 'all',
                    // transform the data already specified in html
                    transform: 'light',
                    lazyRender: true,
                    listClass: 'x-combo-list-small'
                })
            }, {
                header: 'Price',
                dataIndex: 'price',
                width: 70,
                align: 'right',
                renderer: 'usMoney',
                editor: new fm.NumberField({
                    allowBlank: false,
                    allowNegative: false,
                    maxValue: 100000
                })
            }, {
                header: 'Available',
                dataIndex: 'availDate',
                width: 95,
                renderer: formatDate,
                editor: new fm.DateField({
                    format: 'm/d/y',
                    minValue: '01/01/06',
                    disabledDays: [0, 6],
                    disabledDaysText: 'Plants are not available on the weekends'
                })
            },
            checkColumn // the plugin instance
        ]
    });

    // create the Data Store
    var store = new Ext.data.Store({
        // destroy the store if the grid is destroyed
        autoDestroy: true,
        
        // load remote data using HTTP
        url: 'plants.xml',

        // specify a XmlReader (coincides with the XML format of the returned data)
        reader: new Ext.data.XmlReader({
            // records will have a 'plant' tag
            record: 'plant',
            // use an Array of field definition objects to implicitly create a Record constructor
            fields: [
                // the 'name' below matches the tag name to read, except 'availDate'
                // which is mapped to the tag 'availability'
                {name: 'common', type: 'string'},
                {name: 'botanical', type: 'string'},
                {name: 'light'},
                {name: 'price', type: 'float'},             
                // dates can be automatically converted by specifying dateFormat
                {name: 'availDate', mapping: 'availability', type: 'date', dateFormat: 'm/d/Y'},
                {name: 'indoor', type: 'bool'}
            ]
        }),

        sortInfo: {field:'common', direction:'ASC'}
    });

    // create the editor grid
    var grid = new Ext.grid.EditorGridPanel({
        store: store,
        cm: cm,
        renderTo: 'editor-grid',
        width: 600,
        height: 300,
        autoExpandColumn: 'common', // column with this id will be expanded
        title: 'Edit Plants?',
        frame: true,
        // specify the check column plugin on the grid so the plugin is initialized
        plugins: checkColumn,
        clicksToEdit: 1,
        tbar: [{
            text: 'Add Plant',
            handler : function(){
                // access the Record constructor through the grid's store
                var Plant = grid.getStore().recordType;
                var p = new Plant({
                    common: 'New Plant 1',
                    light: 'Mostly Shade',
                    price: 0,
                    availDate: (new Date()).clearTime(),
                    indoor: false
                });
                grid.stopEditing();
                store.insert(0, p);
                grid.startEditing(0, 0);
            }
        }]
    });

    // manually trigger the data store load
    store.load({
        // store loading is asynchronous, use a load listener or callback to handle results
        callback: function(){
            Ext.Msg.show({
                title: 'Store Load Callback',
                msg: 'store was loaded, data available for processing',
                modal: false,
                icon: Ext.Msg.INFO,
                buttons: Ext.Msg.OK
            });
        }
    });
});
