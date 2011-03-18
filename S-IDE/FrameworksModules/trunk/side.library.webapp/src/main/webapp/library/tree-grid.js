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


function getProperty(el, name) {
	var props = el.getElementsByTagName('propertyId');
	if (props.length == 0)
		props = el.getElementsByTagName('cmis:propertyId');
	for (var i = 0; i < props.length; i++) {
	 	var el = props[i];
	 	if (el.getAttribute('propertyDefinitionId') == name) {
	 		var val = el.getElementsByTagName('value');
			if (val.length == 0)
				val = el.getElementsByTagName('cmis:value');
	 		if (val.length > 0) {
	 			return val[0].textContent;
	 		}
	 	}
	 }
	 return '';
} 

Ext.app.AlfrescoLoader = Ext.extend(Ext.tree.TreeLoader, {
	processResponse : function(response, node, callback) {
		try {
		    var entries = response.responseXML.getElementsByTagName('entry');
			if (entries.length == 0)
				entries = el.getElementsByTagName('cmis:entry');
		    node.beginUpdate();
		    for(var i = 0, len = entries.length; i < len; i++){
		    	var child = new Object();
		    	
		    	var title = entries[i].getElementsByTagName('title');
				if (title.length == 0)
					title = entries[i].getElementsByTagName('cmis:title');
		    	child.name = title[0].textContent;
		    	
		    	child.leaf = getProperty(entries[i],'cmis:baseTypeId') != 'cmis:folder';
		    	child.description = entries[i].getElementsByTagName('summary')[0].textContent;
		    	child.id = getProperty(entries[i],'cmis:objectId');

				var icon = entries[i].getElementsByTagName('icon');
				if (icon.length == 0)
					icon = entries[i].getElementsByTagName('alf:icon');
		    	child.icon = icon[0].textContent;

		    	if (child.leaf)
   			    	child.href = entries[i].getElementsByTagName('content')[0].getAttribute('src');
		    	
		        var n = this.createNode(child);
		        if(n){
		            node.appendChild(n);
		        }
		    }
		    node.endUpdate();
		    if(typeof callback == "function"){
		        callback(this, node);
		    }
		}catch(e){
		    this.handleFailure(response);
		}    
	}
});

function updateGrid(uuid,ticket) {
	var myLoader = new  Ext.app.AlfrescoLoader({
		dataUrl: '/alfresco/service/cmis/i/'+uuid.split("/")[3]+'/children?alf_ticket='+ticket,
		requestMethod: 'get'
	});
	
	myLoader.on("beforeload", function(treeLoader, node) {
		if (!node.isRoot) 
        	treeLoader.dataUrl = '/alfresco/service/cmis/i/'+node.id.split("/")[3]+'/children?alf_ticket='+ticket;
    }, this);

    var tree = new Ext.ux.tree.TreeGrid({
        title: 'Alfresco Repository',
        width: 700,
        height: 300,
		
        columns:[{
        	id:'name',
            header: 'Name',
            dataIndex: 'name',
            width: 400
        },{
            header: 'Description',
            width: 280,
            dataIndex: 'description'
        }],

		loader: myLoader
    });
    
    tree.render('grid-example-tree');	
};

function loadGrid() {
	Ext.Ajax.request({
		url: '/alfresco/service/cmis/p?alf_ticket='+_TICKET,
	   	success: function ( result, request ) {
	   		updateGrid(getProperty(result.responseXML,'cmis:objectId'), _TICKET);
	   	}
	});
};
 
Ext.onReady(function() {
    Ext.QuickTips.init();

	loadWithAuthentication(loadGrid);	
});
