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
   
    var SamplePanel = Ext.extend(Ext.Panel, {
        width: 500,
        height:250,
        style: 'margin-top:15px',
        bodyStyle: 'padding:10px',
        renderTo: 'docbody',
        html: Ext.example.shortBogusMarkup,
        autoScroll: true
    });

    new SamplePanel({
        title: 'Standard',
        tbar: [{
            xtype:'splitbutton',
            text: 'Menu Button',
            iconCls: 'add16',
            menu: [{text: 'Menu Button 1'}]
        },'-',{
            xtype:'splitbutton',
            text: 'Cut',
            iconCls: 'add16',
            menu: [{text: 'Cut Menu Item'}]
        },{
            text: 'Copy',
            iconCls: 'add16'
        },{
            text: 'Paste',
            iconCls: 'add16',
            menu: [{text: 'Paste Menu Item'}]
        },'-',{
            text: 'Format',
            iconCls: 'add16'
        }]
    });

    new SamplePanel({
        title: 'Multi columns',
        tbar: [{
            xtype: 'buttongroup',
            title: 'Clipboard',
            columns: 2,
            defaults: {
                scale: 'small'
            },
            items: [{
                xtype:'splitbutton',
                text: 'Menu Button',
                iconCls: 'add16',
                menu: [{text: 'Menu Item 1'}]
            },{
                xtype:'splitbutton',
                text: 'Cut',
                iconCls: 'add16',
                menu: [{text: 'Cut Menu Item'}]
            },{
                text: 'Copy',
                iconCls: 'add16'
            },{
                text: 'Paste',
                iconCls: 'add16',
                menu: [{text: 'Paste Menu Item'}]
            },{
                text: 'Format',
                iconCls: 'add16'
            }]
        },{
            xtype: 'buttongroup',
            title: 'Other Bogus Actions',
            columns: 2,
            defaults: {
                scale: 'small'
            },
            items: [{
                xtype:'splitbutton',
                text: 'Menu Button',
                iconCls: 'add16',
                menu: [{text: 'Menu Button 1'}]
            },{
                xtype:'splitbutton',
                text: 'Cut',
                iconCls: 'add16',
                menu: [{text: 'Cut Menu Item'}]
            },{
                text: 'Copy',
                iconCls: 'add16'
            },{
                text: 'Paste',
                iconCls: 'add16',
                menu: [{text: 'Paste Menu Item'}]
            },{
                text: 'Format',
                iconCls: 'add16'
            }]
        }]
    });



    new SamplePanel({
        title: 'Multi columns (No titles, double stack)',
        tbar: [{
            xtype: 'buttongroup',
            columns: 3,
            defaults: {
                scale: 'small'
            },
            items: [{
                xtype:'splitbutton',
                text: 'Menu Button',
                iconCls: 'add16',
                menu: [{text: 'Menu Item 1'}]
            },{
                xtype:'splitbutton',
                text: 'Cut',
                iconCls: 'add16',
                menu: [{text: 'Cut Menu Item'}]
            },{
                text: 'Copy',
                iconCls: 'add16'
            },{
                text: 'Paste',
                iconCls: 'add16',
                menu: [{text: 'Paste Menu Item'}]
            },{
                text: 'Format',
                iconCls: 'add16'
            }]
        },{
            xtype: 'buttongroup',
            columns: 3,
            defaults: {
                scale: 'small'
            },
            items: [{
                xtype:'splitbutton',
                text: 'Menu Button',
                iconCls: 'add16',
                menu: [{text: 'Menu Item 1'}]
            },{
                xtype:'splitbutton',
                text: 'Cut',
                iconCls: 'add16',
                menu: [{text: 'Cut Menu Item'}]
            },{
                text: 'Copy',
                iconCls: 'add16'
            },{
                text: 'Paste',
                iconCls: 'add16',
                menu: [{text: 'Paste Menu Item'}]
            },{
                text: 'Format',
                iconCls: 'add16'
            }]
        }]
    });

    new SamplePanel({
        title: 'Mix and match icon sizes to create a huge unusable toolbar',
        tbar: [{
            xtype: 'buttongroup',
            columns: 3,
            title: 'Clipboard',
            items: [{
                text: 'Paste',
                scale: 'large',
                rowspan: 3, iconCls: 'add',
                iconAlign: 'top',
                cls: 'x-btn-as-arrow'
            },{
                xtype:'splitbutton',
                text: 'Menu Button',
                scale: 'large',
                rowspan: 3,
                iconCls: 'add',
                iconAlign: 'top',
                arrowAlign:'bottom',
                menu: [{text: 'Menu Item 1'}]
            },{
                xtype:'splitbutton', text: 'Cut', iconCls: 'add16', menu: [{text: 'Cut Menu Item'}]
            },{
                text: 'Copy', iconCls: 'add16'
            },{
                text: 'Format', iconCls: 'add16'
            }]
        },{
            xtype: 'buttongroup',
            columns: 3,
            title: 'Other Actions',
            items: [{
                text: 'Paste',
                scale: 'large',
                rowspan: 3, iconCls: 'add',
                iconAlign: 'top',
                cls: 'x-btn-as-arrow'
            },{
                xtype:'splitbutton',
                text: 'Menu Button',
                scale: 'large',
                rowspan: 3,
                iconCls: 'add',
                iconAlign: 'top',
                arrowAlign:'bottom',
                menu: [{text: 'Menu Button 1'}]
            },{
                xtype:'splitbutton', text: 'Cut', iconCls: 'add16', menu: [{text: 'Cut Menu Item'}]
            },{
                text: 'Copy', iconCls: 'add16'
            },{
                text: 'Format', iconCls: 'add16'
            }]
        }]
    });

    new SamplePanel({
        title: 'Medium icons, arrows to the bottom',
        tbar: [{
            xtype: 'buttongroup',
            title: 'Clipboard',
            defaults: {
                scale: 'medium',
                iconAlign:'top'
            },
            items: [{
                xtype:'splitbutton',
                text: 'Menu Button',
                iconCls: 'add24',
                arrowAlign:'bottom',
                menu: [{text: 'Menu Item 1'}]
            },{
                xtype:'splitbutton',
                text: 'Cut',
                iconCls: 'add24',
                arrowAlign:'bottom',
                menu: [{text: 'Cut Menu Item'}]
            },{
                text: 'Copy',
                iconCls: 'add24',
                cls: 'x-btn-as-arrow'
            },{
                text: 'Paste',
                iconCls: 'add24',
                arrowAlign:'bottom',
                menu: [{text: 'Paste Menu Item'}]
            },{
                text: 'Format<br/>Stuff',
                iconCls: 'add24'
            }]
        },{
            xtype: 'buttongroup',
            title: 'Other Bogus Actions',
            defaults: {
                scale: 'medium',
                iconAlign:'top'
            },
            items: [{
                xtype:'splitbutton',
                text: 'Menu Button',
                iconCls: 'add24',
                arrowAlign:'bottom',
                menu: [{text: 'Menu Item 1'}]
            },{
                xtype:'splitbutton',
                text: 'Cut',
                iconCls: 'add24',
                arrowAlign:'bottom',
                menu: [{text: 'Cut Menu Item'}]
            },{
                text: 'Copy',
                iconCls: 'add24',
                cls: 'x-btn-as-arrow'
            },{
                text: 'Paste',
                iconCls: 'add24',
                arrowAlign:'bottom',
                menu: [{text: 'Paste Menu Item'}]
            },{
                text: 'Format',
                iconCls: 'add24',
                cls: 'x-btn-as-arrow'
            }]
        }]
    });


    new SamplePanel({
        title: 'Medium icons, text and arrows to the left',
        tbar: [{
            xtype:'buttongroup',
            items: [{
                text: 'Cut',
                iconCls: 'add24',
                scale: 'medium'
            },{
                text: 'Copy',
                iconCls: 'add24',
                scale: 'medium'
            },{
                text: 'Paste',
                iconCls: 'add24',
                scale: 'medium',
                menu: [{text: 'Paste Menu Item'}]
            }]
        }, {
            xtype:'buttongroup',
            items: [{
                text: 'Format',
                iconCls: 'add24',
                scale: 'medium'
            }]
        }]
    });

    new SamplePanel({
        title: 'Small icons, text and arrows to the left',
        tbar: [{
            xtype:'buttongroup',
            items: [{
                text: 'Cut',
                iconCls: 'add16',
                scale: 'small'
            },{
                text: 'Copy',
                iconCls: 'add16',
                scale: 'small'
            },{
                text: 'Paste',
                iconCls: 'add16',
                scale: 'small',
                menu: [{text: 'Paste Menu Item'}]
            }]
        }, {
            xtype:'buttongroup',
            items: [{
                text: 'Format',
                iconCls: 'add16',
                scale: 'small'
            }]
        }]
    });

});
