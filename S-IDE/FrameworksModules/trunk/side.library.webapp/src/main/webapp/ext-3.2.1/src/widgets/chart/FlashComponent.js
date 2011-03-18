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
 * @class Ext.FlashComponent
 * @extends Ext.BoxComponent
 * @constructor
 * @xtype flash
 */
Ext.FlashComponent = Ext.extend(Ext.BoxComponent, {
    /**
     * @cfg {String} flashVersion
     * Indicates the version the flash content was published for. Defaults to <tt>'9.0.115'</tt>.
     */
    flashVersion : '9.0.115',

    /**
     * @cfg {String} backgroundColor
     * The background color of the chart. Defaults to <tt>'#ffffff'</tt>.
     */
    backgroundColor: '#ffffff',

    /**
     * @cfg {String} wmode
     * The wmode of the flash object. This can be used to control layering. Defaults to <tt>'opaque'</tt>.
     */
    wmode: 'opaque',

    /**
     * @cfg {Object} flashVars
     * A set of key value pairs to be passed to the flash object as flash variables. Defaults to <tt>undefined</tt>.
     */
    flashVars: undefined,

    /**
     * @cfg {Object} flashParams
     * A set of key value pairs to be passed to the flash object as parameters. Possible parameters can be found here:
     * http://kb2.adobe.com/cps/127/tn_12701.html Defaults to <tt>undefined</tt>.
     */
    flashParams: undefined,

    /**
     * @cfg {String} url
     * The URL of the chart to include. Defaults to <tt>undefined</tt>.
     */
    url: undefined,
    swfId : undefined,
    swfWidth: '100%',
    swfHeight: '100%',

    /**
     * @cfg {Boolean} expressInstall
     * True to prompt the user to install flash if not installed. Note that this uses
     * Ext.FlashComponent.EXPRESS_INSTALL_URL, which should be set to the local resource. Defaults to <tt>false</tt>.
     */
    expressInstall: false,

    initComponent : function(){
        Ext.FlashComponent.superclass.initComponent.call(this);

        this.addEvents(
            /**
             * @event initialize
             *
             * @param {Chart} this
             */
            'initialize'
        );
    },

    onRender : function(){
        Ext.FlashComponent.superclass.onRender.apply(this, arguments);

        var params = Ext.apply({
            allowScriptAccess: 'always',
            bgcolor: this.backgroundColor,
            wmode: this.wmode
        }, this.flashParams), vars = Ext.apply({
            allowedDomain: document.location.hostname,
            YUISwfId: this.getId(),
            YUIBridgeCallback: 'Ext.FlashEventProxy.onEvent'
        }, this.flashVars);

        new swfobject.embedSWF(this.url, this.id, this.swfWidth, this.swfHeight, this.flashVersion,
            this.expressInstall ? Ext.FlashComponent.EXPRESS_INSTALL_URL : undefined, vars, params);

        this.swf = Ext.getDom(this.id);
        this.el = Ext.get(this.swf);
    },

    getSwfId : function(){
        return this.swfId || (this.swfId = "extswf" + (++Ext.Component.AUTO_ID));
    },

    getId : function(){
        return this.id || (this.id = "extflashcmp" + (++Ext.Component.AUTO_ID));
    },

    onFlashEvent : function(e){
        switch(e.type){
            case "swfReady":
                this.initSwf();
                return;
            case "log":
                return;
        }
        e.component = this;
        this.fireEvent(e.type.toLowerCase().replace(/event$/, ''), e);
    },

    initSwf : function(){
        this.onSwfReady(!!this.isInitialized);
        this.isInitialized = true;
        this.fireEvent('initialize', this);
    },

    beforeDestroy: function(){
        if(this.rendered){
            swfobject.removeSWF(this.swf.id);
        }
        Ext.FlashComponent.superclass.beforeDestroy.call(this);
    },

    onSwfReady : Ext.emptyFn
});

/**
 * Sets the url for installing flash if it doesn't exist. This should be set to a local resource.
 * @static
 * @type String
 */
Ext.FlashComponent.EXPRESS_INSTALL_URL = 'http:/' + '/swfobject.googlecode.com/svn/trunk/swfobject/expressInstall.swf';

Ext.reg('flash', Ext.FlashComponent);
