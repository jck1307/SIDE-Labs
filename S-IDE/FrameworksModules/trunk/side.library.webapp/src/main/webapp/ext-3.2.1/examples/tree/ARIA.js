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
(function() {

Ext.a11y.ARIA = Ext.apply(new Ext.util.Observable(), function() {
    return {
        setRole : function(el, role) {
            el = Ext.getDom(el);
            if(el) {
                el.setAttribute('role', role.toString());
            }
        },
        
        setProperty : function(el, key, value) {
            el = Ext.getDom(el);
            if(el) {
                el.setAttribute(key.toString(), value.toString());
            }                
        }
    }
}());

var ARIA = Ext.a11y.ARIA;

Ext.override(Ext.tree.TreeNode, {
    render : function(bulkRender){
        this.ui.render(bulkRender);
        if(!this.rendered){
            // make sure it is registered
            this.getOwnerTree().registerNode(this);
            this.rendered = true;
            this.fireEvent('noderender', this);
            if(this.expanded){
                this.expanded = false;
                this.expand(false, false);
            }
        }
    }
});

Ext.override(Ext.tree.TreePanel, {
    initARIA : function() {
        Ext.tree.TreePanel.superclass.initARIA.call(this);
        this.getSelectionModel().on('selectionchange', this.onNodeSelect, this);
        this.ariaTreeEl = this.body.down('.x-tree-root-ct');
        this.on('collapsenode', this.onNodeCollapse, this);
        this.on('expandnode', this.onNodeExpand, this);
    },
    
    // private
    registerNode : function(node){
        if(this.nodeHash[node.id] === undefined) {
            node.on('noderender', this.onNodeRender, this);
        }
        this.nodeHash[node.id] = node;
    },

    // private
    unregisterNode : function(node){
        node.un('noderender', this.onNodeRender, this);
        delete this.nodeHash[node.id];
    },
    
    onNodeRender : function(node) {
        var a = node.ui.anchor,
            level = this.rootVisible ? 1 : 0,
            pnode = node;
                                
        if(node.isRoot) {
            ARIA.setRole(this.ariaTreeEl, 'tree');
            ARIA.setProperty(this.ariaTreeEl, 'aria-labelledby', Ext.id(node.ui.textNode));
            ARIA.setProperty(this.ariaTreeEl, 'aria-activedescendant', 'false');
            if(!this.rootVisible) {
                return;
            }
        }
        ARIA.setRole(node.ui.wrap, 'treeitem');
        ARIA.setProperty(node.ui.wrap, 'aria-labelledby', Ext.id(node.ui.textNode));            
        ARIA.setProperty(node.ui.wrap, 'aria-expanded', 'false');
        ARIA.setProperty(node.ui.wrap, 'aria-selected', 'false');
        while (pnode.parentNode) {
            level++;
            pnode = pnode.parentNode;
        }
        ARIA.setProperty(node.ui.wrap, 'aria-level', level);   
        if(!node.isLeaf()) {
            ARIA.setRole(node.ui.ctNode, 'group');
            ARIA.setProperty(node.ui.wrap, 'aria-expanded', node.isExpanded());
        }
    },
    
    onNodeSelect : function(sm, node) {
        ARIA.setProperty(this.ariaTreeEl, 'aria-activedescendant', Ext.id(node.ui.wrap));
        ARIA.setProperty(node.ui.wrap, 'aria-selected', 'true');
    },
    
    onNodeCollapse : function(node) {
        ARIA.setProperty(node.ui.wrap, 'aria-expanded', 'false');
    },
    
    onNodeExpand : function(node) {
        ARIA.setProperty(node.ui.wrap, 'aria-expanded', 'true');
    }
});
     
})();
