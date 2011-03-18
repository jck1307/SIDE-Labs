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
Ext.test.session.addTest('Number', {

    name: 'Global Number Decorators',
    
    planned: 15,
    
    // 15
    test_constrain: function(){
        Y.Assert.areEqual(1, (1).constrain(1, 1), 'Test where the number being constrained is equal to both the min and the max');
        Y.Assert.areEqual(5, (5).constrain(1, 5), 'Test where the number being constrained is equal to the max');
        Y.Assert.areEqual(3, (3).constrain(3, 5), 'Test where the number being constrained is equal to the min');
        
        Y.Assert.areEqual(3, (3).constrain(1, 5), 'Test with an integer within the constraints');
        Y.Assert.areEqual(-3, (-3).constrain(-5, -1), 'Test with a negative integer within the constraints');
        Y.Assert.areEqual(3.3, (3.3).constrain(3.1, 3.5), 'Test with a float within the constraints');
        Y.Assert.areEqual(-3.3, (-3.3).constrain(-3.5, -3.1), 'Test with a negative float within the constraints');
        
        Y.Assert.areEqual(5, (100).constrain(1, 5), 'Test with an integer over the maximum of the constraint');
        Y.Assert.areEqual(3, (1).constrain(3, 5), 'Test with an integer under the maximum of the constraint');
        Y.Assert.areEqual(-50, (-5).constrain(-100, -50), 'Test with a negative integer over the maximum of the constraint');
        Y.Assert.areEqual(-5, (-100).constrain(-5, -3), 'Test with a negative integer under the maximum of the constraint');
        
        Y.Assert.areEqual(4.1, (6.7).constrain(3.1, 4.1), 'Test with a float over the maximum of the constraint');
        Y.Assert.areEqual(6.7, (3.1).constrain(6.7, 12.4), 'Test with a float under the maximum of the constraint');
        Y.Assert.areEqual(-50.5, (-3.1).constrain(-100.5, -50.5), 'Test with a negative float over the maximum of the constraint');
        Y.Assert.areEqual(-5.4, (-100.7).constrain(-5.4, -3.1), 'Test with a negative float under the maximum of the constraint');
    }
    
});
