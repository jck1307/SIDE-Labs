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
    var suite  = Ext.test.session.getSuite('Ext.form.FormPanel'),
        assert = Y.Assert;
    
    function buildForm(config) {
        return new Ext.form.FormPanel(config);
    };
    
    suite.add(new Y.Test.Case({
        name: 'initialization',
        
        testCreatesForm: function() {
            var form = buildForm();
            
            assert.isTrue(form.form instanceof Ext.form.BasicForm);
        },
        
        testInitsItems: function() {
            var FormPanel = Ext.form.FormPanel,
                proto     = FormPanel.prototype,
                oldInit   = proto.initItems,
                wasCalled = false;
            
            proto.initItems = function() {
                wasCalled = true;
            };
            
            var form = buildForm();
            assert.isTrue(wasCalled);
            
            proto.initItems = oldInit;
        },
        
        testStartsMonitoring: function() {
            var FormPanel = Ext.form.FormPanel,
                proto     = FormPanel.prototype,
                oldFunc   = proto.startMonitoring,
                wasCalled = false;
            
            proto.startMonitoring = function() {
                wasCalled = true;
            };
            
            var form = buildForm({
                monitorValid: true, 
                renderTo    : Ext.getBody()
            });
            
            form.render();
            assert.isTrue(wasCalled);
            
            proto.startMonitoring = oldFunc;
            form.destroy();
        }
    }));
    
    suite.add(new Y.Test.Case({
        name: 'destruction',
        
        testStopMonitoring: function() {
            var FormPanel = Ext.form.FormPanel,
                proto     = FormPanel.prototype,
                oldFunc   = proto.stopMonitoring,
                wasCalled = false;
            
            proto.stopMonitoring = function() {
                wasCalled = true;
            };
            
            var form = buildForm({
                monitorValid: true, 
                renderTo    : Ext.getBody()
            });
            
            form.render();
            form.destroy();
            assert.isTrue(wasCalled);
            
            proto.stopMonitoring = oldFunc;
        }
    }));
    
    suite.add(new Y.Test.Case({
        name: 'initFields',
        
        testIsField: function() {
            var mockField = {
                setValue    : Ext.emptyFn,
                getValue    : Ext.emptyFn,
                markInvalid : Ext.emptyFn,
                clearInvalid: Ext.emptyFn
            };
            
            var form = buildForm();
            
            assert.isTrue(form.isField(mockField));
        }
    }));
})();
