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
Ext.onReady(function(){

    // This function renders a block of buttons
    function renderButtons(title){

        Ext.getBody().createChild({tag: 'h2', html: title});

        new ButtonPanel(
            'Text Only',
            [{
                text: 'Add User'
            },{
                text: 'Add User',
                scale: 'medium'
            },{
                text: 'Add User',
                scale: 'large'
            }]
        );

        new ButtonPanel(
            'Icon Only',
            [{
                iconCls: 'add16'
            },{
                iconCls: 'add24',
                scale: 'medium'
            },{
                iconCls: 'add',
                scale: 'large'
            }]
        );

        new ButtonPanel(
            'Icon and Text (left)',
            [{
                text: 'Add User',
                iconCls: 'add16'
            },{
                text: 'Add User',
                iconCls: 'add24',
                scale: 'medium'
            },{
                text: 'Add User',
                iconCls: 'add',
                scale: 'large'
            }]
        );

        new ButtonPanel(
            'Icon and Text (top)',
            [{
                text: 'Add User',
                iconCls: 'add16',
                iconAlign: 'top'
            },{
                text: 'Add User',
                iconCls: 'add24',
                scale: 'medium',
                iconAlign: 'top'
            },{
                text: 'Add User',
                iconCls: 'add',
                scale: 'large',
                iconAlign: 'top'
            }]
        );

        new ButtonPanel(
            'Icon and Text (right)',
            [{
                text: 'Add User',
                iconCls: 'add16',
                iconAlign: 'right'
            },{
                text: 'Add User',
                iconCls: 'add24',
                scale: 'medium',
                iconAlign: 'right'
            },{
                text: 'Add User',
                iconCls: 'add',
                scale: 'large',
                iconAlign: 'right'
            }]
        );

        new ButtonPanel(
            'Icon and Text (bottom)',
            [{
                text: 'Add User',
                iconCls: 'add16',
                iconAlign: 'bottom'
            },{
                text: 'Add User',
                iconCls: 'add24',
                scale: 'medium',
                iconAlign: 'bottom'
            },{
                text: 'Add User',
                iconCls: 'add',
                scale: 'large',
                iconAlign: 'bottom'
            }]
        );
    }

    renderButtons('Normal Buttons');

    ButtonPanel.override({
        enableToggle: true
    });

    renderButtons('Toggle Buttons');

    ButtonPanel.override({
        enableToggle : undefined,
        menu : {items: [{text:'Menu Item 1'},{text:'Menu Item 2'},{text:'Menu Item 3'}]}
    });

    renderButtons('Menu Buttons');

    ButtonPanel.override({
        split: true,
        defaultType: 'splitbutton'
    });

    renderButtons('Split Buttons');

    ButtonPanel.override({
        split: false,
        defaultType: 'button',
        arrowAlign: 'bottom'
    });

    renderButtons('Menu Buttons (Arrow on bottom)');

    ButtonPanel.override({
        split: true,
        defaultType: 'splitbutton'
    });

    renderButtons('Split Buttons (Arrow on bottom)');
});

// Helper class for organizing the buttons
ButtonPanel = Ext.extend(Ext.Panel, {
    layout:'table',
    defaultType: 'button',
    baseCls: 'x-plain',
    cls: 'btn-panel',
    renderTo : 'docbody',
    menu: undefined,
    split: false,

    layoutConfig: {
        columns:3
    },

    constructor: function(desc, buttons){
        // apply test configs
        for(var i = 0, b; b = buttons[i]; i++){
            b.menu = this.menu;
            b.enableToggle = this.enableToggle;
            b.split = this.split;
            b.arrowAlign = this.arrowAlign;
        }
        var items = [{
            xtype: 'box',
            autoEl: {tag: 'h3', html: desc, style:"padding:15px 0 3px;"},
            colspan: 3
        }].concat(buttons);

        ButtonPanel.superclass.constructor.call(this, {
            items: items
        });
    }
});
