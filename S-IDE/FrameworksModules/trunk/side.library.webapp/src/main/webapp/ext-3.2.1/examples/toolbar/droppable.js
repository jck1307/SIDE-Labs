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
    var toolbar = new Ext.Toolbar({
        renderTo: 'docbody',
        plugins : [
            new Ext.ux.ToolbarDroppable({
                createItem: function(data) {
                    var record = data.draggedRecord;
                    
                    return new Ext.Button({
                        text   : record.get('company'),
                        iconCls: record.get('change') > 0 ? 'money-up' : 'money-down',
                        reorderable: true
                    });
                }
            }),
            new Ext.ux.ToolbarReorderer({defaultReorderable: false})
        ],
        items: ['Drag items here:']
    });
    
    // sample static data for the store
    var myData = [
        [1,  '3m Co',71.72,0.02,0.03,'9/1 12:00am'],
        [2,  'Alcoa',29.01,0.42,1.47,'9/1 12:00am'],
        [3,  'Altria Group',83.81,-0.28,0.34,'9/1 12:00am'],
        [4,  'American Express',52.55,0.01,0.02,'9/1 12:00am'],
        [5,  'Microsoft',25.84,-0.14,0.54,'9/1 12:00am'],
        [6,  'Pfizer Inc',27.96,-0.4,1.45,'9/1 12:00am'],
        [7,  'Coca-Cola',45.07,0.26,0.58,'9/1 12:00am'],
        [8,  'Home Depot.',34.64,0.35,1.02,'9/1 12:00am'],
        [9,  'Procter & Gamble',61.91,0.01,0.02,'9/1 12:00am'],
        [10, 'United Technologies',63.26,-0.55,0.88,'9/1 12:00am'],
        [11, 'Verizon Communications',35.57,0.39,1.11,'9/1 12:00am'],            
        [12, 'Wal-Mart Stores',45.45,-0.73,1.63,'9/1 12:00am']
    ];

    // create the data store
    var store = new Ext.data.ArrayStore({
        fields: [
            {name: 'id'},
            {name: 'company'},
            {name: 'price',      type: 'float'},
            {name: 'change',     type: 'float'},
            {name: 'pctChange',  type: 'float'},
            {name: 'lastChange', type: 'date', dateFormat: 'n/j h:ia'}
        ]
    });
    
    store.loadData(myData);
    
    var dataview = new Ext.DataView({
        emptyText:    'No items to display',
        singleSelect: true,
        autoScroll:   true,
        overClass:    'x-view-over',
        itemSelector: 'div.company',
        store:        store,
        tpl:          new Ext.XTemplate(
          '<tpl for=".">',
              '<div class="company">',
                  '<h3>{company}</h3>',
                  '<p class="{[values.change > 0 ? "up" : "down"]}">{[values.change > 0 ? "Up" : "Down"]} {pctChange} since {lastChange:date("d/m/Y")}',
              '</div>',
          '</tpl>'
        )
    });
    
    dataview.on('render', function(v) {
        dataview.dragZone = new Ext.dd.DragZone(v.getEl(), {

            getDragData: function(e) {

                var sourceEl = e.getTarget(v.itemSelector, 10);

                if (sourceEl) {
                    d = sourceEl.cloneNode(true);
                    d.id = Ext.id();
                    return {
                        ddel: d,
                        sourceEl: sourceEl,
                        repairXY: Ext.fly(sourceEl).getXY(),
                        sourceStore: v.store,
                        draggedRecord: v.getRecord(sourceEl)
                    };
                }
            },

            getRepairXY: function() {
                return this.dragData.repairXY;
            }
        });
    });
    
    new Ext.Panel({
        renderTo: 'docbody',
        tbar    : toolbar,
        border  : true,
        width   : 600,
        height  : 400,
        layout  : 'fit',
        items   : dataview
    });
});
