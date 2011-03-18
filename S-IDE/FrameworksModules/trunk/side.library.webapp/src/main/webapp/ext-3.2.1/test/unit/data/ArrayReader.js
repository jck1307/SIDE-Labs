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
Ext.test.session.addTest( 'ArrayReader', {
    name: 'readRecords',
    setUp: function() {
        this.reader = new Ext.data.ArrayReader({
            idIndex: 1,
            fields: [
               {name: 'floater', type: 'float'},
               {name: 'id'},
               {name: 'totalProp', type: 'integer'},
               {name: 'bool', type: 'boolean'},
               {name: 'msg'}
            ]
        });
        this.data1 = [
            [ 1.23, 1, 6, true, 'hello' ]
        ];
        this.rec1 = this.reader.readRecords(this.data1);
    },
    test_tearDown: function() {
        delete this.reader;
        delete this.data1;
        delete this.rec1;
    },
    test_TotalRecords: function() {
        Y.Assert.areSame(this.rec1.totalRecords, 1);
    },
    test_Records: function() {
        Y.Assert.areSame(this.rec1.records[0].data.floater, this.data1[0][0]);
        Y.Assert.areSame(this.rec1.records[0].data.id, this.data1[0][1]);
        Y.Assert.areSame(this.rec1.records[0].data.totalProp, this.data1[0][2]);
        Y.Assert.areSame(this.rec1.records[0].data.bool, this.data1[0][3]);
        Y.Assert.areSame(this.rec1.records[0].data.msg, this.data1[0][4]);
    }
});
