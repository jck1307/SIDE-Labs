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


function createXML(fileName) {
	var reg = new RegExp("<br>","g");
   	var _value = Ext.getCmp('content-field').getValue().replace(reg,'<br/>');
	
	return 		"<?xml version='1.0' encoding='utf-8'?>"
			+	"<entry xmlns='http://www.w3.org/2005/Atom' xmlns:cmis='http://www.cmis.org/2008/05'>"
			+		"<title>"+Ext.getCmp('name-field').getValue()+"</title>"
			+		"<summary>"+Ext.getCmp('description-field').getValue()+"</summary>"
			+		"<content type='text/html'>"
			+				Ext.util.base64.encode(_value)
			+		"</content>"
			+		"<cmis:object>"
			+			"<cmis:properties>"
			+				"<cmis:propertyString cmis:name='ObjectTypeId'><cmis:value>document</cmis:value></cmis:propertyString>"
			+			"</cmis:properties>"
			+		"</cmis:object>"
			+	"</entry>";
}

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
		    	child.text = title[0].textContent;
		    	
		    	child.leaf = getProperty(entries[i],'cmis:baseTypeId') != 'cmis:folder';
		    	child.id = getProperty(entries[i],'cmis:objectId');
		    	
		    	var icon = entries[i].getElementsByTagName('icon');
				if (icon.length == 0)
					icon = entries[i].getElementsByTagName('alf:icon');
		    	child.icon = icon[0].textContent;
		    	
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

function update(uuid,ticket) {

	var myLoader = new  Ext.app.AlfrescoLoader({
		dataUrl: '/alfresco/service/cmis/i/'+uuid.split("/")[3]+'/children?types=Folders&alf_ticket='+ticket,
		requestMethod: 'get'
	});
	
	myLoader.on("beforeload", function(treeLoader, node) {
		if (!node.isRoot) 
        	treeLoader.dataUrl = '/alfresco/service/cmis/i/'+node.id.split("/")[3]+'/children?types=Folders&alf_ticket='+ticket;
    }, this);
	
	var tree = new Ext.tree.TreePanel({
		title:'Select the folder...',
		flex:1,
		loader: myLoader,
		root:'Alfresco Repository',
		autoScroll: true
	});
	
	var form = new Ext.form.FormPanel({
		title:'Document information',
        labelWidth: 75,
        frame:true,
        bodyStyle:'padding:5px 5px 0',
        width:700,
        defaults: {width: 230},
        defaultType: 'textfield',

        items: [{
                fieldLabel: 'Name',
                name: 'filename',
                id: 'name-field',
                allowBlank:false
            },{
                fieldLabel: 'Description',
                name: 'description',
                id: 'description-field',
                xtype: 'textarea'
            },{
            	xtype:'htmleditor',
            	name:'content',
	            fieldLabel:'Content',
                id: 'content-field',
	            height:200,
	            width:600
        	}
        ],

        buttons: [{
            text: 'Create new content',
            handler: function(){
            	var valid = true;
            	
            	//Validation of folder
            	var selectedNode = tree.getSelectionModel().selNode;
            	if (selectedNode == null || selectedNode.isRoot) {
            		Ext.MessageBox.alert('Select a folder...','You must select a folder in the Alfresco repository!');
            		valid = false;
            	}
            	
                if(form.getForm().isValid() && valid){
                	var reg = new RegExp("://","g");
                	var uuid = selectedNode.id.replace(reg,'/').split('/')[2];
                	
                	Ext.Ajax.request({
                		url: '/alfresco/service/cmis/i/'+uuid+'/children?alf_ticket='+_TICKET,
	                    waitMsg: 'Uploading your document...',
	                    methode: 'POST',
	                    xmlData: createXML(),
	                    headers: {
	                    	"Content-Type":"application/atom+xml;type=entry"
	                    },
	                    success: function(fp, o){
	                        Ext.Msg.alert('Success', 'New content is created.');
	                        form.getForm().reset();
	                    }
                	});
                }
            }
        }]
    });

	
	var panel = new Ext.Panel({
        height: 350,
        width: '100%',
        title: 'Create content sample',
        layout: 'hbox',
        layoutConfig: {
		    align : 'stretch',
		    pack  : 'start',
		},
        items: [tree,form]
	});

    panel.render('example');
};

function load() {
	Ext.Ajax.request({
		url: '/alfresco/service/cmis/p?alf_ticket='+_TICKET,
	   	success: function ( result, request ) {
	   		update(getProperty(result.responseXML,'cmis:objectId'), _TICKET);
	   	}
	});
};

Ext.onReady(function(){
	loadWithAuthentication(load);
});
