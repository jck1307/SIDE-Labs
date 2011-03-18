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

    new Ext.Slider({
        renderTo: 'basic-slider',
        width: 214,
        minValue: 0,
        maxValue: 100
    });

    new Ext.Slider({
        renderTo: 'increment-slider',
        width: 214,
        value:50,
        increment: 10,
        minValue: 0,
        maxValue: 100
    });

    new Ext.Slider({
        renderTo: 'vertical-slider',
        height: 214,
        vertical: true,
        minValue: 0,
        maxValue: 100
    });

    new Ext.Slider({
        renderTo: 'tip-slider',
        width: 214,
        minValue: 0,
        maxValue: 100,
        plugins: new Ext.slider.Tip()
    });

    var tip = new Ext.slider.Tip({
        getText: function(thumb){
            return String.format('<b>{0}% complete</b>', thumb.value);
        }
    });

    new Ext.Slider({
        renderTo: 'custom-tip-slider',
        width: 214,
        increment: 10,
        minValue: 0,
        maxValue: 100,
        plugins: tip
    });

    new Ext.Slider({
        renderTo: 'custom-slider',
        width: 214,
        increment: 10,
        minValue: 0,
        maxValue: 100,
        plugins: new Ext.slider.Tip()
    });
    
    new Ext.Slider({
        renderTo: 'multi-slider-horizontal',
        width   : 214,
        minValue: 0,
        maxValue: 100,
        values  : [10, 50, 90],
        plugins : new Ext.slider.Tip()
    });
    
    new Ext.Slider({
        renderTo : 'multi-slider-vertical',
        vertical : true,
        height   : 214,
        minValue: 0,
        maxValue: 100,
        values  : [10, 50, 90],
        plugins : new Ext.slider.Tip()
    });
});
