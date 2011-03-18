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


Ext.onReady(function() {
  Ext.QuickTips.init();

  var viewport = new Ext.Viewport({
      layout:'fit',
      items:[{
        xtype: 'grouptabpanel',
  		tabWidth: 180,
  		activeGroup: 1,
  		items: [{
  			items: [ 
              {
                  title: 'Analysis',
              }, {
				title: 'Risk analysis',
                iconCls: 'x-icon-risk',
                style: 'padding: 10px;',
				layout: 'fit',
  				items: [gridRisk]	
  			}, {
  				title: 'Problem analysis',
                iconCls: 'x-icon-diagnostic',
                style: 'padding: 10px;',
				layout: 'fit',
  				items: [gridProblem]
  			}]
          }, {
              expanded: true,
              items: [{
                  title: 'Web Tool',
                  iconCls: 'x-icon-webtool',
                  style: 'padding: 10px;'
              }, {
                  title: 'Go to the tool...',
  				  layout: 'fit',
  				  items: [webtoolPanel],
                  iconCls: 'x-icon-webtool-go',
                  style: 'padding: 10px;'
              }]
          }]
}]
  });

  // trigger the data store load
  storeRisk.load();
  storeProblem.load();
});
