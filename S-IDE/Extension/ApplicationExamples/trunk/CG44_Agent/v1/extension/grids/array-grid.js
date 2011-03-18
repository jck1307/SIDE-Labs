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
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.onReady(function(){

	var myReader = new Ext.data.JsonReader({
        fields: [
           'fileName',
           {name:'Version', mapping:'customProperties', convert:function(v, record){ return v["CG44Agent:Document_Version"]}},
		   {name:'Description', mapping:'customProperties', convert:function(v, record){ return v["CG44Agent:Document_Description"]}},
		   {name:'DateNumerisation', mapping:'customProperties', convert:function(v, record){ return v["CG44Agent:Document_DateNumerisation"]}},
		   {name:'Categorie', mapping:'customProperties', convert:function(v, record){ return v["CG44Agent:Document_Categorie"]}},
		   {name:'Origine', mapping:'customProperties', convert:function(v, record){ return v["CG44Agent:Document_Origine"]}},
		   {name:'Nature', mapping:'customProperties', convert:function(v, record){ return v["CG44Agent:Document_Nature"]}},
	  	   {name:'Objet', mapping:'customProperties', convert:function(v, record){ return v["CG44Agent:Document_Objet"]}}
        ],
        root: 'items'
	});


    // create the Data Store
    var store = new Ext.data.GroupingStore({
    	url:'http://localhost:8080/alfresco/service/slingshot/doclib/doclist/all/site/cg44/documentLibrary/Dossier%20Agent/111111A',
        autoLoad:true,
        reader:myReader,
        sortInfo:{field: 'fileName', direction: "ASC"}
    });

    // create the Grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        columns: [
            {id:'fileName',header: 'Nom du fichier', width: 160, sortable: true, dataIndex: 'fileName'},
            {id:'version',header: 'Version', width: 160, sortable: true, dataIndex: 'Version'},
            {id:'description',header: 'Description', width: 160, sortable: true, dataIndex: 'Description'},
            {id:'dateNumerisation',header: 'Date de numérisation', width: 160, sortable: true, dataIndex: 'DateNumerisation'},
            {id:'categorie',header: 'Catégorie', width: 160, sortable: true, dataIndex: 'Categorie'},
            {id:'origine',header: 'Origine', width: 160, sortable: true, dataIndex: 'Origine'},
            {id:'nature',header: 'Nature', width: 160, sortable: true, dataIndex: 'Nature'},
            {id:'objet',header: 'Objet', width: 160, sortable: true, dataIndex: 'Objet'}
        ],
        stripeRows: true,
        autoExpandColumn: 'fileName',
        height: 600,
        width: '100%',
        title: 'Documents de l\'agent John Doe (111111A)',
        // config options for stateful behavior
        stateful: true,
        stateId: 'grid',
        view: new Ext.grid.GroupingView({
            forceFit:true,
            groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
        })       
    });
    
    // render the grid to the specified div in the page
    grid.render('grid-example');
});
