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

    var xg = Ext.grid;

    var reader = new Ext.data.JsonReader({
        idProperty:'taskId',
        fields: [
            {name: 'projectId', type: 'int'},
            {name: 'project', type: 'string'},
            {name: 'taskId', type: 'int'},
            {name: 'description', type: 'string'},
            {name: 'estimate', type: 'float'},
            {name: 'rate', type: 'float'},
            {name: 'cost', type: 'float'},
            {name: 'due', type: 'date', dateFormat:'m/d/Y'}
        ],
		// additional configuration for remote
        root:'data',
        remoteGroup:true,
        remoteSort: true
    });

    // define a custom summary function
    Ext.ux.grid.GroupSummary.Calculations['totalCost'] = function(v, record, field){
        return v + (record.data.estimate * record.data.rate);
    };

	// utilize custom extension for Hybrid Summary
    var summary = new Ext.ux.grid.HybridSummary();

    var grid = new xg.EditorGridPanel({
        ds: new Ext.data.GroupingStore({
            reader: reader,
			// use remote data
            proxy : new Ext.data.HttpProxy({
                url: 'totals-hybrid.json',
                method: 'GET'
            }),
            sortInfo: {field: 'due', direction: 'ASC'},
            groupField: 'project'
        }),

        columns: [
            {
                id: 'description',
                header: 'Task',
                width: 80,
                sortable: true,
                dataIndex: 'description',
                summaryType: 'count',
                hideable: false,
                summaryRenderer: function(v, params, data){
                    return ((v === 0 || v > 1) ? '(' + v +' Tasks)' : '(1 Task)');
                },
                editor: new Ext.form.TextField({
                   allowBlank: false
                })
            },{
                header: 'Project',
                width: 20,
                sortable: true,
                dataIndex: 'project'
            },{
                header: 'Due Date',
                width: 25,
                sortable: true,
                dataIndex: 'due',
                summaryType:'max',
                renderer: Ext.util.Format.dateRenderer('m/d/Y'),
                editor: new Ext.form.DateField({
                    format: 'm/d/Y'
                })
            },{
                header: 'Estimate',
                width: 20,
                sortable: true,
                dataIndex: 'estimate',
                summaryType:'sum',
                renderer : function(v){
                    return v +' hours';
                },
                editor: new Ext.form.NumberField({
                   allowBlank: false,
                   allowNegative: false,
                    style: 'text-align:left'
                })
            },{
                header: 'Rate',
                width: 20,
                sortable: true,
                renderer: Ext.util.Format.usMoney,
                dataIndex: 'rate',
                summaryType:'average',
                editor: new Ext.form.NumberField({
                    allowBlank: false,
                    allowNegative: false,
                    style: 'text-align:left'
                })
            },{
                id: 'cost',
                header: 'Cost',
                width: 20,
                sortable: false,
                groupable: false,
                renderer: function(v, params, record){
                    return Ext.util.Format.usMoney(record.data.estimate * record.data.rate);
                },
                dataIndex: 'cost',
                summaryType: 'totalCost',
                summaryRenderer: Ext.util.Format.usMoney
            }
        ],

        view: new Ext.grid.GroupingView({
            forceFit:true,
            showGroupName: false,
            enableNoGroups:false,
			enableGroupingMenu:false,
            hideGroupedColumn: true
        }),

        plugins: summary,

        tbar : [{
            text: 'Toggle',
            tooltip: 'Toggle the visibility of summary row',
            handler: function(){summary.toggleSummaries();}
        }],

        frame: true,
        width: 800,
        height: 450,
        clicksToEdit: 1,
        collapsible: true,
        animCollapse: false,
        trackMouseOver: false,
        //enableColumnMove: false,
        title: 'Sponsored Projects',
        iconCls: 'icon-grid',
        renderTo: document.body
    });

    grid.on('afteredit', function(){
        var groupValue = 'Ext Forms: Field Anchoring';
        summary.showSummaryMsg(groupValue, 'Updating Summary...');
        setTimeout(function(){ // simulate server call
            // HybridSummary class implements updateSummaryData
            summary.updateSummaryData(groupValue,
                // create data object based on configured dataIndex
                {description: 22, estimate: 888, rate: 888, due: new Date(), cost: 8});
        }, 2000);
    });

	// load the remote data
    grid.store.load();

});
