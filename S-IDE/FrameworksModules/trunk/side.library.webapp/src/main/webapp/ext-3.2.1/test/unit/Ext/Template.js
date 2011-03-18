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
Ext.test.session.addTest( 'Ext', {

    name: 'Template',

    planned: 22,

    setUp: function() {
        this.es = [];
    },

    tearDown: function() {
        Ext.each( this.es, function( el ) {
            if ( el instanceof Ext.Element ) {
                el.remove();
            } else {
                Ext.removeNode( el );
            }
        });
        this.es.length = 0;
    }, 

    // 3
    test_from: function() {
        var id = Ext.id();
        var el = Ext.getBody().createChild({
            tag: 'textarea',
            cls: 'x-hidden',
            id: id,
            html: '{0} {1}'
        });
        this.es.push( el );
        var tpl1 = Ext.Template.from( el, { complied: true } );
        var tpl2 = Ext.Template.from( el.dom, { complied: true } );
        var tpl3 = Ext.Template.from( id, { complied: true } );
        Y.Assert.areEqual( 'Hello World', tpl1.apply( [ 'Hello', 'World' ] ), 'Test template compiled from textarea value (using Ext.element)' );
        Y.Assert.areEqual( 'Hello World', tpl2.apply( [ 'Hello', 'World' ] ), 'Test template compiled from textarea value (using Html element)' );
        Y.Assert.areEqual( 'Hello World', tpl3.apply( [ 'Hello', 'World' ] ), 'Test template compiled from textarea value (using element id)' );
    },

    // 5
    test_append: function() {
        var container = Ext.getBody().createChild({
            tag: 'div',
            cls: 'x-hidden'
        });
        this.es.push( container );
        var div = container.createChild({
            tag: 'div',
            html: 'foobar'
        });
        var tpl = new Ext.Template( '<div>{0} {1}</div>', {
            compiled: true,
            disableFormats: true
        });

        var newel = tpl.append( container, [ 'Hello', 'World' ] );
        Y.Assert.areEqual( 'Hello World', newel.innerHTML, 'Test if the new element\'s innerHTML matches the template' );
        Y.Assert.areSame( newel, div.dom.nextSibling, 'Test if nextSibling is the created element' );
        Y.Assert.areSame( newel, container.dom.lastChild, 'Test if container\'s lastChild is the created element' );
        Y.Assert.areEqual( 'Hello World', div.dom.nextSibling.innerHTML, 'Test if nextSibling\'s innerHTML is from the template' );
        Y.Assert.areEqual( 'Hello World', container.dom.lastChild.innerHTML, 'Test if the container\'s firstChild innerHTML is from the template' );
    },

    // 3
    test_apply: function() {
        var tpl1 = new Ext.Template( '{0}{hello} {1}{world}. How are you {2}{name}?', {
            compiled: true,
            disableFormats: true
        });
        var tpl2 = new Ext.Template( '{hello} {world}. How are you {name:ellipsis(8)}?', {
            compiled: true
        });
        Y.Assert.areEqual( 'Hello World. How are you TestyMcTester?', tpl1.apply( [ 'Hello', 'World', 'TestyMcTester' ] ), 'Test apply with an array, no formats' );
        Y.Assert.areEqual( 'Hello World. How are you TestyMcTester?', tpl1.apply( { hello: 'Hello', world: 'World', name: 'TestyMcTester' } ), 'Test apply with an object, no formats' );
        Y.Assert.areEqual( 'Hello World. How are you Testy...?', tpl2.apply( { hello: 'Hello', world: 'World', name: 'TestyMcTester' } ), 'Test apply with an object, with formats' );
    },

    // apply is an alias for applyTemplate

    // 3
    test_insertAfter: function() {
        var container = Ext.getBody().createChild({
            tag: 'div',
            cls: 'x-hidden'
        });
        this.es.push( container );
        var div = container.createChild({
            tag: 'div',
            html: 'foobar'
        });
        var tpl = new Ext.Template( '<div>{0} {1}</div>', {
            compiled: true,
            disableFormats: true
        });

        var newel = tpl.insertAfter( div, [ 'Hello', 'World' ] );
        Y.Assert.areEqual( 'Hello World', newel.innerHTML, 'Test if the new element\'s innerHTML matches the template' );
        Y.Assert.areSame( newel, div.dom.nextSibling, 'Test if nextSibling is the created element' );
        Y.Assert.areEqual( 'Hello World', div.dom.nextSibling.innerHTML, 'Test if nextSibling\'s innerHTML is from the template' );
    },
    
    // 3
    test_insertBefore: function() {
        var container = Ext.getBody().createChild({
            tag: 'div',
            cls: 'x-hidden'
        });
        this.es.push( container );
        var div = container.createChild({
            tag: 'div',
            html: 'foobar'
        });
        var tpl = new Ext.Template( '<div>{0} {1}</div>', {
            compiled: true,
            disableFormats: true
        });

        var newel = tpl.insertBefore( div, [ 'Hello', 'World' ] );
        Y.Assert.areEqual( 'Hello World', newel.innerHTML, 'Test if the new element\'s innerHTML matches the template' );
        Y.Assert.areSame( newel, container.dom.firstChild, 'Test if the container\'s firstChild is the created element' );
        Y.Assert.areEqual( 'Hello World', container.dom.firstChild.innerHTML, 'Test if the container\s firstChild innerHTML is from the template' );
    },
    
    // 3
    test_insertFirst: function() {
        var container = Ext.getBody().createChild({
            tag: 'div',
            cls: 'x-hidden'
        });
        this.es.push( container );
        container.createChild({
            tag: 'div',
            html: 'foobar'
        });
        var tpl = new Ext.Template( '<div>{0} {1}</div>', {
            compiled: true,
            disableFormats: true
        });

        var newel = tpl.insertFirst( container, [ 'Hello', 'World' ] );
        Y.Assert.areEqual( 'Hello World', newel.innerHTML, 'Test if the new element\'s innerHTML matches the template' );
        Y.Assert.areSame( newel, container.dom.firstChild, 'Test if the container\'s firstChild is the created element' );
        Y.Assert.areEqual( 'Hello World', container.dom.firstChild.innerHTML, 'Test if the container\s firstChild innerHTML is from the template' );
    },

    // 1
    test_overwrite: function() {
        var container = Ext.getBody().createChild({
            tag: 'div',
            cls: 'x-hidden'
        });
        this.es.push( container );
        container.createChild({
            tag: 'div',
            html: 'foobar'
        });
        var tpl = new Ext.Template( '{0} {1}', {
            compiled: true,
            disableFormats: true
        });

        var newel = tpl.overwrite( container, [ 'Hello', 'World' ] );
        Y.Assert.areEqual( 'Hello World', container.dom.innerHTML, 'Test if the container innerHTML matches the template' );
    },

    // 1
    test_set: function() {
        var foo = new Ext.Template( '{1}{0}' );
        foo.set( '{0}{1}', true );
        Y.Assert.areEqual( 'foobar', foo.apply( [ 'foo', 'bar' ] ), 'Test recompiled template using set' );
    }

});
