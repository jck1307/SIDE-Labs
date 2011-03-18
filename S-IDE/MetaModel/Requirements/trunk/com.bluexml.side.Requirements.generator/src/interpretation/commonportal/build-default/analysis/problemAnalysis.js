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


  var cmProblem = new Ext.grid.ColumnModel([{
    id : 'name',
    header : "Name",
    dataIndex : 'name',
    width : 220,
    sortable : true
  }, {
    header : "Type",
    dataIndex : 'type',
    width : 130,
    sortable : true
  },{
    header : "Severity",
    dataIndex : 'severity',
    width : 130,
    sortable : true
  },{
    header : "Description",
    dataIndex : 'description',
    width : 130,
    sortable : true
  },
]);

  var storeProblem = new Ext.data.GroupingStore({
    url: 'data/analysis/problem.json',
    reader: new Ext.data.JsonReader({
	    root: 'diagnostic',
	    fields: ['name', 'type', 'severity', 'description']
    }),
    groupField:'severity'
  });

  var gridProblem = new Ext.grid.GridPanel({
    store : storeProblem,
    cm : cmProblem,
    autoExpandColumn : 'name',
    view: new Ext.grid.GroupingView({
            forceFit:true,
            groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
        })
  });  
