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
FeedViewer = {};

Ext.onReady(function(){
    Ext.QuickTips.init();

    Ext.state.Manager.setProvider(new Ext.state.SessionProvider({state: Ext.appState}));

    var tpl = Ext.Template.from('preview-tpl', {
        compiled:true,
        getBody : function(v, all){
            return Ext.util.Format.stripScripts(v || all.description);
        }
    });
    FeedViewer.getTemplate = function(){
        return tpl;
    }

    var feeds = new FeedPanel();
    var mainPanel = new MainPanel();

    feeds.on('feedselect', function(feed){
        mainPanel.loadFeed(feed);
    });
    
    var viewport = new Ext.Viewport({
        layout:'border',
        items:[
            new Ext.BoxComponent({ // raw element
                region:'north',
                el: 'header',
                height:32
            }),
            feeds,
            mainPanel
         ]
    });

    // add some default feeds
    feeds.addFeed({
        url:'http://feeds.feedburner.com/extblog',
        text: 'ExtJS.com Blog'
    }, false, true);

    feeds.addFeed({
        url:'http://extjs.com/forum/external.php?type=RSS2',
        text: 'ExtJS.com Forums'
    }, true);

    feeds.addFeed({
        url:'http://feeds.feedburner.com/ajaxian',
        text: 'Ajaxian'
    }, true);
    
    Ext.get('header').on('click', function() {
        viewport.focus();
    });
    
    feeds.focus();
});

// This is a custom event handler passed to preview panels so link open in a new windw
FeedViewer.LinkInterceptor = {
    render: function(p){
        p.body.on({
            'mousedown': function(e, t){ // try to intercept the easy way
                t.target = '_blank';
            },
            'click': function(e, t){ // if they tab + enter a link, need to do it old fashioned way
                if(String(t.target).toLowerCase() != '_blank'){
                    e.stopEvent();
                    window.open(t.href);
                }
            },
            delegate:'a'
        });
    }
};
