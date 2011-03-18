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
Ext.chart.Chart.CHART_URL = '../../resources/charts.swf';

Ext.onReady(function(){
    var store = new Ext.data.JsonStore({
        fields: ['year', 'comedy', 'action', 'drama', 'thriller'],
        data: [
                {year: 2005, comedy: 34000000, action: 23890000, drama: 18450000, thriller: 20060000},
                {year: 2006, comedy: 56703000, action: 38900000, drama: 12650000, thriller: 21000000},
                {year: 2007, comedy: 42100000, action: 50410000, drama: 25780000, thriller: 23040000},
                {year: 2008, comedy: 38910000, action: 56070000, drama: 24810000, thriller: 26940000}
              ]
    });
    
    new Ext.Panel({
        width: 600,
        height: 400,
        renderTo: 'container',
        title: 'Stacked Bar Chart - Movie Takings by Genre',
        items: {
            xtype: 'stackedbarchart',
            store: store,
            yField: 'year',
            xAxis: new Ext.chart.NumericAxis({
                stackingEnabled: true,
                labelRenderer: Ext.util.Format.usMoney
            }),
            series: [{
                xField: 'comedy',
                displayName: 'Comedy'
            },{
                xField: 'action',
                displayName: 'Action'
            },{
                xField: 'drama',
                displayName: 'Drama'
            },{
                xField: 'thriller',
                displayName: 'Thriller'
            }]
        }
    });
});
