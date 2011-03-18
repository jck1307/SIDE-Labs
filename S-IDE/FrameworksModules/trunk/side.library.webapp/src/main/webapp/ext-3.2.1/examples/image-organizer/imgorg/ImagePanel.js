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
Imgorg.ImagePanel = Ext.extend(Ext.Panel,{
    closable: true,
    border: false,
    tagTpl: new Ext.XTemplate(
        '<h3 class="image-prop-header">Tags</h3>',
        '<tpl for=".">',
            '<div class="image-prop">{text}</div>',
        '</tpl>'
    ),
    albumTpl: new Ext.XTemplate(
        '<h3 class="image-prop-header">Album</h3>',
        '<tpl for=".">',
            '<div class="image-prop">{text}</div>',
        '</tpl>'
    ),
    infoTpl: new Ext.XTemplate(
        '<h3 class="image-prop-header">File Info</h3>',
        '<div class="image-prop">Filename: {FileName}</div>',
        '<div class="image-prop">Size: {FileSize:fileSize}</div>',
        '<div class="image-prop">Height: {[values["COMPUTED"].Height]}</div>',
        '<div class="image-prop">Width: {[values["COMPUTED"].Width]}</div>'
    ),
    initComponent: function() {
        Ext.apply(this,{
            layout: 'border',
            items: [{
                border: false,
                region: 'center',
                html: '<div style="text-align:center;"><img src="'+this.url+'"/></div>',
                autoScroll: true
            },{
                border: false,
                region: 'east',
                itemId: 'image-properties',
                width: 250,
                title: 'Properties',
                collapsible: true,
                style: 'border-left: 1px solid #99BBE8'
            }]
        });
        Imgorg.ImagePanel.superclass.initComponent.call(this);
    },
    
    afterRender: function() {
        Imgorg.ImagePanel.superclass.afterRender.call(this);
        Imgorg.ss.Images.getInfo({image: this.imageData.id}, this.onGetInfo, this);
        Imgorg.ss.Albums.getAlbums({image: this.imageData.id}, this.onGetAlbums,this);
        Imgorg.ss.Tags.getTags({image: this.imageData.id}, this.onGetTags, this);
    },
    
    onGetInfo: function(data, resp) {
        var prop = this.getComponent('image-properties');
        this.infoTpl.append(prop.body, data)
    },
    
    onGetTags: function(data, resp) {
        var prop = this.getComponent('image-properties');
        this.tagTpl.append(prop.body, data);
    },
    
    onGetAlbums: function(data, resp) {
        var prop = this.getComponent('image-properties');
        this.albumTpl.append(prop.body, data);
    }
});
Ext.reg('img-panel',Imgorg.ImagePanel);
