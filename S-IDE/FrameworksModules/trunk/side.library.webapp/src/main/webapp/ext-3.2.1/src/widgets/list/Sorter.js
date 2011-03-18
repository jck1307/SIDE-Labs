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
/**
 * @class Ext.list.Sorter
 * @extends Ext.util.Observable
 * <p>Supporting Class for Ext.list.ListView</p>
 * @constructor
 * @param {Object} config
 */
Ext.list.Sorter = Ext.extend(Ext.util.Observable, {
    /**
     * @cfg {Array} sortClasses
     * The CSS classes applied to a header when it is sorted. (defaults to <tt>["sort-asc", "sort-desc"]</tt>)
     */
    sortClasses : ["sort-asc", "sort-desc"],

    constructor: function(config){
        Ext.apply(this, config);
        Ext.list.Sorter.superclass.constructor.call(this);
    },

    init : function(listView){
        this.view = listView;
        listView.on('render', this.initEvents, this);
    },

    initEvents : function(view){
        view.mon(view.innerHd, 'click', this.onHdClick, this);
        view.innerHd.setStyle('cursor', 'pointer');
        view.mon(view.store, 'datachanged', this.updateSortState, this);
        this.updateSortState.defer(10, this, [view.store]);
    },

    updateSortState : function(store){
        var state = store.getSortState();
        if(!state){
            return;
        }
        this.sortState = state;
        var cs = this.view.columns, sortColumn = -1;
        for(var i = 0, len = cs.length; i < len; i++){
            if(cs[i].dataIndex == state.field){
                sortColumn = i;
                break;
            }
        }
        if(sortColumn != -1){
            var sortDir = state.direction;
            this.updateSortIcon(sortColumn, sortDir);
        }
    },

    updateSortIcon : function(col, dir){
        var sc = this.sortClasses;
        var hds = this.view.innerHd.select('em').removeClass(sc);
        hds.item(col).addClass(sc[dir == "DESC" ? 1 : 0]);
    },

    onHdClick : function(e){
        var hd = e.getTarget('em', 3);
        if(hd && !this.view.disableHeaders){
            var index = this.view.findHeaderIndex(hd);
            this.view.store.sort(this.view.columns[index].dataIndex);
        }
    }
});

// Backwards compatibility alias
Ext.ListView.Sorter = Ext.list.Sorter;
