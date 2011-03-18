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
Imgorg.TagWindow = Ext.extend(Ext.Window, {
    title: 'Choose Tag',
    layout: 'fit',
    closeAction: 'hide',
    width: 300,
    modal: true,
    
    initComponent: function() {
        Ext.apply(this, {
            items: [{
                autoHeight: true,
                xtype: 'form',
                id: 'tag-select',
                bodyStyle: 'padding:15px',
                labelWidth: 50,
                items: [{
                    anchor: '95%',
                    fieldLabel: 'Tag',
                    xtype: 'img-tagcombo',
                    name: 'tag',
                    allowBlank: false
                }]
            }],
            buttons: [{
                text: 'Tag Images',
                handler: this.tagImages,
                scope: this
            },{
                text: 'Cancel',
                handler: function() {
                    this.hide();
                },
                scope: this
            }]
        });
        Imgorg.TagWindow.superclass.initComponent.call(this);
    },
    
        
    tagImages: function() {
        var af = this.getComponent('tag-select').getForm();
        if (af.isValid()) {
            if (this.selectedRecords) {
                var imageIds = [];
                for (var i = 0; i < this.selectedRecords.length; i++) {
                    var r = this.selectedRecords[i];
                    imageIds.push(r.data.dbid || r.data.id);
                }
                var fld = af.findField('tag');
                var tag = fld.getRawValue();
                var idx = fld.store.find('text', tag);
                if (idx != -1) {
                    rec = fld.store.getAt(idx);
                    tag = rec.data.id;
                }
                Imgorg.ss.Images.tagImage({
                    images: imageIds,
                    tag: tag
                });
            }
            this.hide();
        }
    }
});

