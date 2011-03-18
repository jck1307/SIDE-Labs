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
 * @class Ext.data.Request
 * A simple Request class used internally to the data package to provide more generalized remote-requests
 * to a DataProxy.
 * TODO Not yet implemented.  Implement in Ext.data.Store#execute
 */
Ext.data.Request = function(params) {
    Ext.apply(this, params);
};
Ext.data.Request.prototype = {
    /**
     * @cfg {String} action
     */
    action : undefined,
    /**
     * @cfg {Ext.data.Record[]/Ext.data.Record} rs The Store recordset associated with the request.
     */
    rs : undefined,
    /**
     * @cfg {Object} params HTTP request params
     */
    params: undefined,
    /**
     * @cfg {Function} callback The function to call when request is complete
     */
    callback : Ext.emptyFn,
    /**
     * @cfg {Object} scope The scope of the callback funtion
     */
    scope : undefined,
    /**
     * @cfg {Ext.data.DataReader} reader The DataReader instance which will parse the received response
     */
    reader : undefined
};
