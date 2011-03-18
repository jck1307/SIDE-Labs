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


dojo.require("dojox.grid.DataGrid");
dojo.require("dojox.data.XmlStore");

dojo.addOnLoad(function() {

	function load() {
		// our test data store for this example:
		var store = new dojox.data.XmlStore({
			url: getDataSource('xml',_TICKET),
			rootItem: 'item',
			allowNoTrailingSlash: true
		});
		
		// set the layout structure:
		var layout = [{
				field: '_reference',
				name: 'Ref',
				width: '50px',
				formatter: function(item) { return item.toString(); }
			},{
				field: '_name',
				name: 'Name',
				width: 'auto',
				formatter: function(item) { return item.toString(); }
			},{
				field: '_typeOfDocument',
				name: 'Type',
				width: '150px',
				formatter: function(item) { return item.toString(); }
			},{
				field: '_latitude',
				name: 'Latitude',
				width: '70px',
				formatter: function(item) { return item.toString(); }
			},{
				field: '_longitude',
				name: 'Longitude',
				width: '70px',
				formatter: function(item) { return item.toString(); }
			},{
				field: 'size',
				name: 'Size',
				width: '70px',
				formatter: function(item) {
					var reg=new RegExp("(\\s)", "g");
					val = item.toString().replace(reg,"");
					if ((val/1000 > 500) || (val/1000 < 100)){
						return '<span style="color:red;">' + Math.floor(val/1024) + ' KB</span>';
					}
						return '<span style="color:green;">' + Math.floor(val/1024) + ' KB</span>';
					}
		}];
		
		// create a new grid:
		var grid = new dojox.grid.DataGrid({
			store: store,
			clientSort: true,
			rowSelector: '20px',
			structure: layout
		},document.createElement('div'));
		
		// append the new grid to the div "gridContainer4":
		dojo.byId("gridContainer").appendChild(grid.domNode);
		
		// Call startup, in order to render the grid:
		grid.startup();
	}
		
	loadWithAuthentication(load);
});
