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
    var simple = new Ext.FormPanel({
        labelWidth: 40, // label settings here cascade unless overridden
        frame: true,
        title: 'Simple Form',
        bodyStyle: 'padding:5px 5px 0',
        width: 210,
        defaults: {width: 135},
        defaultType: 'textfield',

        items: [
            new Ext.ux.form.SpinnerField({
                fieldLabel: 'Age',
                name: 'age'
            }),
            {
            	xtype: 'spinnerfield',
            	fieldLabel: 'Test',
            	name: 'test',
            	minValue: 0,
            	maxValue: 100,
            	allowDecimals: true,
            	decimalPrecision: 1,
            	incrementValue: 0.4,
            	alternateIncrementValue: 2.1,
            	accelerate: true
            }
        ]
    });

    simple.render('form-ct');
});
