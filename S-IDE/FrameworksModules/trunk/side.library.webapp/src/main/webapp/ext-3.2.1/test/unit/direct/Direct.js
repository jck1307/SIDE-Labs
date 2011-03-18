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
 * Tests Ext.data.Store functionality
 * @author Ed Spencer
 */
(function() {
    var suite  = Ext.test.session.getSuite('Ext.Direct'),
        assert = Y.Assert;

    //a shared setup function used by several of the suites
    var defaultSetup = function() {
        this.API = {
            "url": "php\/router.php",
            "type": "remoting",
            "actions": {
                "TestAction": [{
                    "name": "doEcho",
                    "len": 1
                }, {
                    "name": "multiply",
                    "len": 1
                }, {
                    "name": "getTree",
                    "len": 1
                }],
                "Profile": [{
                    "name": "getBasicInfo",
                    "len": 2
                }, {
                    "name": "getPhoneInfo",
                    "len": 1
                }, {
                    "name": "getLocationInfo",
                    "len": 1
                }, {
                    "name": "updateBasicInfo",
                    "len": 2,
                    "formHandler": true
                }]
            }
        };
    };

    suite.add(new Y.Test.Case({
        name: 'adding providers',

        setUp: defaultSetup,

        testAddProvider: function() {
            var p = Ext.Direct.addProvider(
                this.API
            );
            Y.ObjectAssert.hasKeys(p.actions, [
                "Profile",
                "TestAction"
            ], 'Test actions provided');
            Y.ObjectAssert.hasKeys(p.actions, p, Ext.Direct.providers, "Test providers cache");
        },
        testGetProvider: function() {
            var p = Ext.Direct.addProvider(
                this.API
            );
            Y.ObjectAssert.hasKeys(p, Ext.Direct.getProvider(p.id));
        },
        testRemoveProvider: function() {
            // Remove via id
            var p = Ext.Direct.addProvider(
                this.API
            );
            var id = p.id;
            Ext.Direct.removeProvider(id);
            Y.Assert.isUndefined(Ext.Direct.getProvider(id));

            // Remove via object
            var p = Ext.Direct.addProvider(
                this.API
            );
            var id = p.id;
            Ext.Direct.removeProvider(p);
            Y.Assert.isUndefined(Ext.Direct.getProvider(id));
        }
    }));
})();
