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
    var suite  = Ext.test.session.getSuite('Ext.layout.ContainerLayout'),
        assert = Y.Assert;
    
    function buildLayout(config) {
        var layout = new Ext.layout.FormLayout(config || {});
        
        //give a mock container
        layout.container = {
            itemCls: 'ctCls'
        };
        
        return layout;
    };
    
    suite.add(new Y.Test.Case({
        name: 'parseMargins',
        
        setUp: function() {
            this.cont = new Ext.layout.ContainerLayout();
        },
        
        testParseFromNumber: function() {
            var res = this.cont.parseMargins(10);
            
            assert.areEqual(10, res.top);
            assert.areEqual(10, res.right);
            assert.areEqual(10, res.bottom);
            assert.areEqual(10, res.left);
        },
        
        testParseFromString: function() {
            var res = this.cont.parseMargins("10");
            
            assert.areEqual(10, res.top);
            assert.areEqual(10, res.right);
            assert.areEqual(10, res.bottom);
            assert.areEqual(10, res.left);
        },
        
        testParseFromTwoArgString: function() {
            var res = this.cont.parseMargins("10 5");
            
            assert.areEqual(10, res.top);
            assert.areEqual(5,  res.right);
            assert.areEqual(10, res.bottom);
            assert.areEqual(5,  res.left);
        },
        
        testParseFromThreeArgString: function() {
            var res = this.cont.parseMargins("10 5 2");
            
            assert.areEqual(10, res.top);
            assert.areEqual(5,  res.right);
            assert.areEqual(2,  res.bottom);
            assert.areEqual(5,  res.left);
        },
        
        testParseFromFourArgString: function() {
            var res = this.cont.parseMargins("10 5 2 1");
            
            assert.areEqual(10, res.top);
            assert.areEqual(5,  res.right);
            assert.areEqual(2,  res.bottom);
            assert.areEqual(1,  res.left);
        }
    }));
    
    suite.add(new Y.Test.Case({
        name: 'configureItem',
        
        setUp: function() {
            this.layout = new Ext.layout.ContainerLayout({
                extraCls: 'myExtraClass'
            });
            
            //mock component object
            this.component = {
                addClass: Ext.emptyFn,
                doLayout: Ext.emptyFn
            };
        },
        
        testAddsExtraCls: function() {
            var layout = this.layout;
            
            var addedClass = "";
            
            //mock component object
            c = {
                addClass: function(cls) {
                    addedClass = cls;
                }
            };
            
            layout.configureItem(c, 0);
            
            assert.areEqual('myExtraClass', addedClass);
        },
        
        testAddsExtraClsToPositionEl: function() {
            var layout = this.layout;
            
            var addedClass = "";
            
            //mock component object
            c = {
                getPositionEl: function() {
                    return posEl;
                }
            };
            
            //mock position el
            posEl = {
                addClass: function(cls) {
                    addedClass = cls;
                }
            };
            
            layout.configureItem(c, 0);
            
            assert.areEqual('myExtraClass', addedClass);
        },
        
        testLaysOutIfForced: function() {
            var laidOut = false;
            
            var layout = this.layout,
                comp   = this.component;
            
            //mock component object
            comp.doLayout = function() {
                laidOut = true;
            };
            
            layout.configureItem(comp, 0);
            
            assert.isFalse(laidOut);
            
            layout.forceLayout = true;
            layout.configureItem(comp, 0);
            
            assert.isTrue(laidOut);
        },
        
        testHidesIfRenderHidden: function() {
            
        }
    }));
})();
