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
 * @class Ext.direct.JsonProvider
 * @extends Ext.direct.Provider
 */
Ext.direct.JsonProvider = Ext.extend(Ext.direct.Provider, {
    parseResponse: function(xhr){
        if(!Ext.isEmpty(xhr.responseText)){
            if(typeof xhr.responseText == 'object'){
                return xhr.responseText;
            }
            return Ext.decode(xhr.responseText);
        }
        return null;
    },

    getEvents: function(xhr){
        var data = null;
        try{
            data = this.parseResponse(xhr);
        }catch(e){
            var event = new Ext.Direct.ExceptionEvent({
                data: e,
                xhr: xhr,
                code: Ext.Direct.exceptions.PARSE,
                message: 'Error parsing json response: \n\n ' + data
            });
            return [event];
        }
        var events = [];
        if(Ext.isArray(data)){
            for(var i = 0, len = data.length; i < len; i++){
                events.push(Ext.Direct.createEvent(data[i]));
            }
        }else{
            events.push(Ext.Direct.createEvent(data));
        }
        return events;
    }
});
