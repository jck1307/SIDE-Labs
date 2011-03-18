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
Ext.ns('App', 'App.user');
/**
 * App.user.Grid
 * A typical EditorGridPanel extension.
 */
App.user.Grid = Ext.extend(Ext.grid.EditorGridPanel, {
    renderTo: 'user-grid',
    iconCls: 'silk-grid',
    frame: true,
    title: 'Users',
    height: 300,
    width: 500,
    style: 'margin-top: 10px',

    initComponent : function() {

        // typical viewConfig
        this.viewConfig = {
            forceFit: true
        };

        // relay the Store's CRUD events into this grid so these events can be conveniently listened-to in our application-code.
        this.relayEvents(this.store, ['destroy', 'save', 'update']);

        // build toolbars and buttons.
        this.tbar = this.buildTopToolbar();
        this.bbar = this.buildBottomToolbar();
        this.buttons = this.buildUI();

        // super
        App.user.Grid.superclass.initComponent.call(this);
    },

    /**
     * buildTopToolbar
     */
    buildTopToolbar : function() {
        return [{
            text: 'Add',
            iconCls: 'silk-add',
            handler: this.onAdd,
            scope: this
        }, '-', {
            text: 'Delete',
            iconCls: 'silk-delete',
            handler: this.onDelete,
            scope: this
        }, '-'];
    },

    /**
     * buildBottomToolbar
     */
    buildBottomToolbar : function() {
        return ['<b>@cfg:</b>', '-', {
            text: 'autoSave',
            enableToggle: true,
            pressed: true,
            tooltip: 'When enabled, Store will execute Ajax requests as soon as a Record becomes dirty.',
            toggleHandler: function(btn, pressed) {
                this.store.autoSave = pressed;
            },
            scope: this
        }, '-', {
            text: 'batch',
            enableToggle: true,
            pressed: true,
            tooltip: 'When enabled, Store will batch all records for each type of CRUD verb into a single Ajax request.',
            toggleHandler: function(btn, pressed) {
                this.store.batch = pressed;
            },
            scope: this
        }, '-', {
            text: 'writeAllFields',
            enableToggle: true,
            tooltip: 'When enabled, Writer will write *all* fields to the server -- not just those that changed.',
            toggleHandler: function(btn, pressed) {
                store.writer.writeAllFields = pressed;
            },
            scope: this
        }, '-'];
    },

    /**
     * buildUI
     */
    buildUI : function() {
        return [{
            text: 'Save',
            iconCls: 'icon-save',
            handler: this.onSave,
            scope: this
        }];
    },

    /**
     * onSave
     */
    onSave : function(btn, ev) {
        this.store.save();
    },

    /**
     * onAdd
     */
    onAdd : function(btn, ev) {
        var u = new this.store.recordType({
            first : '',
            last: '',
            email : ''
        });
        this.stopEditing();
        this.store.insert(0, u);
        this.startEditing(0, 1);
    },

    /**
     * onDelete
     */
    onDelete : function(btn, ev) {
        var index = this.getSelectionModel().getSelectedCell();
        if (!index) {
            return false;
        }
        var rec = this.store.getAt(index[0]);
        this.store.remove(rec);
    }
});
