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
var TreeTest = function(){
    // shorthand
    var Tree = Ext.tree;

    return {
        init : function(){
            // yui-ext tree
            var tree = new Tree.TreePanel({
                animate:true,
                autoScroll:true,
                loader: new Tree.TreeLoader({dataUrl:'get-nodes.php'}),
                containerScroll: true,
                border: false,
                height: 300,
                width: 300
            });

            // add a tree sorter in folder mode
            new Tree.TreeSorter(tree, {folderSort:true});

            // set the root node
            var root = new Tree.AsyncTreeNode({
                text: 'Ext JS',
                draggable:false, // disable root node dragging
                id:'src'
            });
            tree.setRootNode(root);

            // render the tree
            tree.render('tree');
            root.expand(false, /*no anim*/ false);
            tree.bodyFocus.fi.setFrameEl(tree.el);
            tree.getSelectionModel().select(tree.getRootNode());
            tree.enter.defer(100, tree);
        }
    };
}();

Ext.EventManager.onDocumentReady(TreeTest.init, TreeTest, true);
