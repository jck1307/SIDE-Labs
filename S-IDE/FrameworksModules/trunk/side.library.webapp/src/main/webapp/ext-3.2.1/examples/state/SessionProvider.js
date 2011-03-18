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
Ext.state.SessionProvider = Ext.extend(Ext.state.CookieProvider, {
    readCookies : function(){
        if(this.state){
            for(var k in this.state){
                if(typeof this.state[k] == 'string'){
                    this.state[k] = this.decodeValue(this.state[k]);
                }
            }
        }
        return Ext.apply(this.state || {}, Ext.state.SessionProvider.superclass.readCookies.call(this));
    }
});
