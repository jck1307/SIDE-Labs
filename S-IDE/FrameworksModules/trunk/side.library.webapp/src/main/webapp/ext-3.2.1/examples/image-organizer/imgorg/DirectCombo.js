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
Imgorg.DirectCombo = Ext.extend(Ext.form.ComboBox, {
    displayField: 'text',
    valueField: 'id',
    triggerAction: 'all',
    queryAction: 'name',
    forceSelection: true,
    mode: 'remote',
    
    initComponent: function() {
        this.store = new Ext.data.DirectStore(Ext.apply({
            api: this.api,
            root: '',
            fields: this.fields || ['text', 'id']
        }, this.storeConfig));
        
        Imgorg.DirectCombo.superclass.initComponent.call(this);
    }
});

Imgorg.TagCombo = Ext.extend(Imgorg.DirectCombo,{
    forceSelection: false,
    storeConfig: {
        id: 'tag-store'
    },
    initComponent: function() {
        Ext.apply(this.storeConfig, {
            directFn: Imgorg.ss.Tags.load
        });
        Imgorg.TagCombo.superclass.initComponent.call(this);
    }
});
Ext.reg('img-tagcombo', Imgorg.TagCombo);

Imgorg.TagMultiCombo = Ext.extend(Ext.ux.MultiCombo,{
    listClass: 'label-combo',
    displayField: 'text',
    valueField: 'id',
    
    initComponent: function() {
        this.store = new Ext.data.DirectStore(Ext.apply({
            directFn: Imgorg.ss.Tags.load,
            root: '',
            autoLoad: true,
            fields: this.fields || ['text', 'id']
        }, this.storeConfig));
        this.plugins =new Ext.ux.MultiCombo.Checkable({});
        Imgorg.DirectCombo.superclass.initComponent.call(this);
    }
});
Ext.reg('img-tagmulticombo', Imgorg.TagMultiCombo);

Imgorg.AlbumCombo = Ext.extend(Imgorg.DirectCombo, {
    storeConfig: {
        id: 'album-store'
    },
    initComponent: function() {
        Ext.apply(this.storeConfig, {
            directFn: Imgorg.ss.Albums.getAllInfo
        });
        Imgorg.AlbumCombo.superclass.initComponent.call(this);
    }
});
Ext.reg('img-albumcombo', Imgorg.AlbumCombo);
