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
    
    // for this demo configure local and remote urls for demo purposes
    var url = {
        local:  'grid-filter.json',  // static data file
        remote: 'grid-filter.php'
    };

    // configure whether filter query is encoded or not (initially)
    var encode = false;
    
    // configure whether filtering is performed locally or remotely (initially)
    var local = true;

    store = new Ext.data.JsonStore({
        // store configs
        autoDestroy: true,
        url: (local ? url.local : url.remote),
        remoteSort: false,
        sortInfo: {
            field: 'company',
            direction: 'ASC'
        },
        storeId: 'myStore',
        
        // reader configs
        idProperty: 'id',
        root: 'data',
        totalProperty: 'total',
        fields: [{
            name: 'id'
        }, {
            name: 'company'
        }, {
            name: 'price',
            type: 'float'
        }, {
            name: 'date',
            type: 'date',
            dateFormat: 'Y-m-d H:i:s'
        }, {
            name: 'visible',
            type: 'boolean'
        }, {
            name: 'size'
        }]
    });

    var filters = new Ext.ux.grid.GridFilters({
        // encode and local configuration options defined previously for easier reuse
        encode: encode, // json encode the filter query
        local: local,   // defaults to false (remote filtering)
        filters: [{
            type: 'numeric',
            dataIndex: 'id'
        }, {
            type: 'string',
            dataIndex: 'company',
            disabled: true
        }, {
            type: 'numeric',
            dataIndex: 'price'
        }, {
            type: 'date',
            dataIndex: 'date'
        }, {
            type: 'list',
            dataIndex: 'size',
            options: ['small', 'medium', 'large', 'extra large'],
            phpMode: true
        }, {
            type: 'boolean',
            dataIndex: 'visible'
        }]
    });    
    
    // use a factory method to reduce code while demonstrating
    // that the GridFilter plugin may be configured with or without
    // the filter types (the filters may be specified on the column model
    var createColModel = function (finish, start) {

        var columns = [{
            dataIndex: 'id',
            header: 'Id',
            // instead of specifying filter config just specify filterable=true
            // to use store's field's type property (if type property not
            // explicitly specified in store config it will be 'auto' which
            // GridFilters will assume to be 'StringFilter'
            filterable: true
            //,filter: {type: 'numeric'}
        }, {
            dataIndex: 'company',
            header: 'Company',
            id: 'company',
            filter: {
                type: 'string'
                // specify disabled to disable the filter menu
                //, disabled: true
            }
        }, {
            dataIndex: 'price',
            header: 'Price',
            filter: {
                //type: 'numeric'  // specify type here or in store fields config
            }
        }, {
            dataIndex: 'size',
            header: 'Size',
            filter: {
                type: 'list',
                options: ['small', 'medium', 'large', 'extra large']
                //,phpMode: true
            }
        }, {
            dataIndex: 'date',
            header: 'Date',
            renderer: Ext.util.Format.dateRenderer('m/d/Y'),
            filter: {
                //type: 'date'     // specify type here or in store fields config
            }            
        }, {
            dataIndex: 'visible',
            header: 'Visible',
            filter: {
                //type: 'boolean'  // specify type here or in store fields config
            }
        }];

        return new Ext.grid.ColumnModel({
            columns: columns.slice(start || 0, finish),
            defaults: {
                sortable: true
            }
        });
    };
    
    var grid = new Ext.grid.GridPanel({
        border: false,
        store: store,
        colModel: createColModel(4),
        loadMask: true,
        plugins: [filters],
        autoExpandColumn: 'company',
        listeners: {
            render: {
                fn: function(){
                    store.load({
                        params: {
                            start: 0,
                            limit: 50
                        }
                    });
                }
            }
        },
        bbar: new Ext.PagingToolbar({
            store: store,
            pageSize: 50,
            plugins: [filters]
        })
    });

    // add some buttons to bottom toolbar just for demonstration purposes
    grid.getBottomToolbar().add([
        '->',
        {
            text: 'Encode: ' + (encode ? 'On' : 'Off'),
            tooltip: 'Toggle Filter encoding on/off',
            enableToggle: true,
            handler: function (button, state) {
                var encode = (grid.filters.encode===true) ? false : true;
                var text = 'Encode: ' + (encode ? 'On' : 'Off'); 
                // remove the prior parameters from the last load options
                grid.filters.cleanParams(grid.getStore().lastOptions.params);
                grid.filters.encode = encode;
                button.setText(text);
                grid.getStore().reload();
            } 
        },{
            text: 'Local Filtering: ' + (local ? 'On' : 'Off'),
            tooltip: 'Toggle Filtering between remote/local',
            enableToggle: true,
            handler: function (button, state) {
                var local = (grid.filters.local===true) ? false : true;
                var text = 'Local Filtering: ' + (local ? 'On' : 'Off');
                var newUrl = local ? url.local : url.remote;
                 
                // update the GridFilter setting
                grid.filters.local = local;
                // bind the store again so GridFilters is listening to appropriate store event
                grid.filters.bindStore(grid.getStore());
                // update the url for the proxy
                grid.getStore().proxy.setApi('read', newUrl);

                button.setText(text);
                grid.getStore().reload();
            } 
        },{
            text: 'All Filter Data',
            tooltip: 'Get Filter Data for Grid',
            handler: function () {
                var data = Ext.encode(grid.filters.getFilterData());
                Ext.Msg.alert('All Filter Data',data);
            } 
        },{
            text: 'Clear Filter Data',
            handler: function () {
                grid.filters.clearFilters();
            } 
        },{
            text: 'Reconfigure Grid',
            handler: function () {
                grid.reconfigure(store, createColModel(6));
            } 
        }    
    ]);

    var win = new Ext.Window({
        title: 'Grid Filters Example',
        height: 400,
        width: 700,
        layout: 'fit',
        items: grid
    }).show();
    
});
