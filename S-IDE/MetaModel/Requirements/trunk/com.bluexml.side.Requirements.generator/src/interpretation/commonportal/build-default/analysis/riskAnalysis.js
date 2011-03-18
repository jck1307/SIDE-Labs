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


  function convertPercent(v, record) {
  	var val = v.replace(/,/g,".");
  	val = val * 100;
  	//Take only 2 decimals
  	val = Math.round(val*100)/100
  	return val;
  }

  // custom plugin Ext.ux.ProgressColumn example
  var riskColumn = new Ext.ux.grid.ProgressColumn({
    header : "Risk",
    dataIndex : 'value',
    width : 85,
    sortable : true,
    groupable : false,
    textPst : '%', // string added to the end of the cell value (defaults to '%')
    colored : true // True for pretty colors, false for just blue (defaults to false)
  });

  // the column model has information about grid columns
  // dataIndex maps the column to the specific data field in
  // the data store (created below)
  var cmRisk = new Ext.grid.ColumnModel([{
    id : 'name',
    groupable : false,
    header : "Name",
    dataIndex : 'name',
    width : 220,
    sortable : true
  }, {
    header : "Type",
    dataIndex : 'type',
    width : 130,
    sortable : true
  },
  riskColumn]);

  var storeRisk = new Ext.data.GroupingStore({
    url: 'data/analysis/diagnostic.json',
    reader: new Ext.data.JsonReader({
	    root: 'diagnostic',
    	fields: ['name', 'type', {name:'value', convert:convertPercent}]
    }),
    groupField:'type'
  });

  var gridRisk = new Ext.grid.GridPanel({
    store : storeRisk,
    cm : cmRisk,
    autoExpandColumn : 'name',
    plugins : [riskColumn],
    view: new Ext.grid.GroupingView({
            forceFit:true,
            groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
    })
  });
