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
 * Creates a window with 40 tabs, profiles initial rendering speed and resize rendering speed
 */

function addWindowPerformanceTest() {
        Ext.test.profiler.add({
        name      : 'AnchorLayout',
        skipVersions: ['2.2.0', '3.1.0'],
        iterations: 1000,
        newWindow : true,

        beforeAll : function() {
            this.previousBuffer = Ext.Container.bufferResize;
            Ext.Container.bufferResize = false;
        },

        afterAll : function() {
            Ext.Container.bufferResize = this.previousBuffer;
        },

        execute   : function() {


            this.win = new Ext.Viewport({
                layout: 'fit',
                items: [{
                    xtype: 'container',
                    layout: 'form',
                    items: [{
                        xtype: 'textfield',
                        width: 100
                    }, {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%'
                    },
                    {
                        xtype: 'textfield',
                        width: 100
                    }]
                }]
            });

        },

        afterEach: function() {
          this.win.destroy();
        }
    });
/*
    Ext.test.profiler.add({
        name      : 'ColumnLayout',
        skipVersions: ['2.2.0'],
        iterations: 50,
        newWindow : true,

        beforeAll : function() {
            this.previousBuffer = Ext.Container.bufferResize;
            Ext.Container.bufferResize = false;
        },

        afterAll : function() {
            Ext.Container.bufferResize = this.previousBuffer;
        },

        execute   : function() {

            this.win = new Ext.Viewport({
                layout: 'fit',
                items: [{
                    xtype: 'container',
                    layout: 'column',
                    defaults: {
                        height: 30
                    },
                    items: [{
                        xtype: 'container',
                        width: 100
                    }, {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        columnWidth: .05
                    },
                    {
                        xtype: 'container',
                        width: 100
                    }]
                }]
            });

        },

        afterEach: function() {
          this.win.destroy();
        }
    });

    Ext.test.profiler.add({
        name      : 'HBoxLayout',
        skipVersions: ['2.2.0'],
        iterations: 50,
        newWindow : true,

        beforeAll : function() {
            this.previousBuffer = Ext.Container.bufferResize;
            Ext.Container.bufferResize = false;
        },

        afterAll : function() {
            Ext.Container.bufferResize = this.previousBuffer;
        },

        execute   : function() {

            this.win = new Ext.Viewport({
                layout: 'fit',
                items: [{
                    xtype: 'container',
                    layout: 'hbox',
                    layoutConfig: {
                        align: 'stretch'
                    },
                    items: [{
                        xtype: 'container',
                        width: 100
                    }, {
                        xtype: 'container',
                        flex: 1
                    },
                    {
                        xtype: 'container',
                        flex: 2
                    },
                    {
                        xtype: 'container',
                        flex: 3
                    },
                    {
                        xtype: 'container',
                        flex: 1
                    },
                    {
                        xtype: 'container',
                        flex: 2
                    },
                    {
                        xtype: 'container',
                        flex: 3
                    },
                    {
                        xtype: 'container',
                        flex: 1
                    },
                    {
                        xtype: 'container',
                        flex: 2
                    },
                    {
                        xtype: 'container',
                        flex: 3
                    },
                    {
                        xtype: 'container',
                        flex: 1
                    },
                    {
                        xtype: 'container',
                        flex: 2
                    },
                    {
                        xtype: 'container',
                        flex: 3
                    },
                    {
                        xtype: 'container',
                        flex: 1
                    },
                    {
                        xtype: 'container',
                        flex: 2
                    },
                    {
                        xtype: 'container',
                        flex: 3
                    },
                    {
                        xtype: 'container',
                        flex: 1
                    },
                    {
                        xtype: 'container',
                        flex: 2
                    },
                    {
                        xtype: 'container',
                        flex: 3
                    },
                    {
                        xtype: 'container',
                        flex: 1
                    },
                    {
                        xtype: 'container',
                        flex: 2
                    },
                    {
                        xtype: 'container',
                        flex: 3
                    },
                    {
                        xtype: 'container',
                        flex: 1
                    },
                    {
                        xtype: 'container',
                        flex: 2
                    },
                    {
                        xtype: 'container',
                        flex: 3
                    },
                    {
                        xtype: 'container',
                        flex: 1
                    },
                    {
                        xtype: 'container',
                        flex: 2
                    },
                    {
                        xtype: 'container',
                        flex: 3
                    },
                    {
                        xtype: 'container',
                        flex: 1
                    },
                    {
                        xtype: 'container',
                        flex: 2
                    },
                    {
                        xtype: 'container',
                        flex: 3
                    },
                    {
                        xtype: 'container',
                        flex: 1
                    },
                    {
                        xtype: 'container',
                        flex: 2
                    },
                    {
                        xtype: 'container',
                        flex: 3
                    },
                    {
                        xtype: 'container',
                        flex: 1
                    },
                    {
                        xtype: 'container',
                        flex: 2
                    },
                    {
                        xtype: 'container',
                        flex: 3
                    },
                    {
                        xtype: 'container',
                        flex: 1
                    },
                    {
                        xtype: 'container',
                        flex: 2
                    },
                    {
                        xtype: 'container',
                        flex: 3
                    },
                    {
                        xtype: 'container',
                        flex: 1
                    },
                    {
                        xtype: 'container',
                        flex: 2
                    },
                    {
                        xtype: 'container',
                        flex: 3
                    },
                    {
                        xtype: 'container',
                        flex: 1
                    },
                    {
                        xtype: 'container',
                        flex: 2
                    },
                    {
                        xtype: 'container',
                        flex: 3
                    },
                    {
                        xtype: 'container',
                        flex: 1
                    },
                    {
                        xtype: 'container',
                        flex: 2
                    },
                    {
                        xtype: 'container',
                        flex: 3
                    },
                    {
                        xtype: 'container',
                        width: 100
                    }]
                }]
            });

      } ,

        afterEach: function() {
          this.win.destroy();
        }
    });
*/
};

if (typeof Ext != 'undefined' && typeof Ext.test != 'undefined') {
    addWindowPerformanceTest();
} else {
    Ext.onReady(addWindowPerformanceTest);
}
