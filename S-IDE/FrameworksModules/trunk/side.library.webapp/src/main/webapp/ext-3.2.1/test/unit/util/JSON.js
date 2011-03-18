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
Ext.test.session.addTest( 'Ext.util', {

    name: 'JSON',

    planned: 4,

    // same as Ext.encode
    // 1
    test_encode: function() {
        Y.Assert.areEqual( '{"foo":"bar"}', Ext.util.JSON.encode( { foo: 'bar' } ), 'Test encode with simple object' );
    },

    // same as Ext.decode
    // 2
    test_decode: function() {
        Y.ObjectAssert.hasKeys({
            foo: 'bar'
        }, Ext.util.JSON.decode( '{"foo":"bar"}' ), 'Test decode with a simple object');
        Y.ObjectAssert.hasKeys({
            foo: ['bar','baz']
        }, Ext.util.JSON.decode( '{"foo":["bar","baz"]}' ), 'Test decode with a hash + array');
    }

    // encodeDate

});
