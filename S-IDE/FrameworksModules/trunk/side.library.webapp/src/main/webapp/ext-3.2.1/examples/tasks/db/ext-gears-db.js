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
Ext.data.GearsDB = Ext.extend(Ext.data.SqlDB, {
	// abstract methods
    open : function(db, cb, scope){
        this.conn = google.gears.factory.create('beta.database', '1.0');
        this.conn.open(db);
        this.openState = true;
		Ext.callback(cb, scope, [this]);
		this.fireEvent('open', this);
    },

	close : function(){
        this.conn.close();
        this.fireEvent('close', this);
    },

    exec : function(sql, cb, scope){
        this.conn.execute(sql).close();
        Ext.callback(cb, scope, [true]);
    },

	execBy : function(sql, args, cb, scope){
	    this.conn.execute(sql, args).close();
        Ext.callback(cb, scope, [true]);
    },

	query : function(sql, cb, scope){
        var rs = this.conn.execute(sql);
        var r = this.readResults(rs);
        Ext.callback(cb, scope, [r]);
        return r;
    },

	queryBy : function(sql, args, cb, scope){
        var rs = this.conn.execute(sql, args);
        var r = this.readResults(rs);
        Ext.callback(cb, scope, [r]);
        return r;
    },

    readResults : function(rs){
        var r = [];
        if(rs){
            var c = rs.fieldCount();
            // precache field names
            var fs = [];
            for(var i = 0; i < c; i++){
                fs[i] = rs.fieldName(i);
            }
            // read the data
            while(rs.isValidRow()){
                var o = {};
                for(var i = 0; i < c; i++){
                    o[fs[i]] = rs.field(i);
                }
                r[r.length] = o;
                rs.next();
            }
            rs.close();
        }
        return r;
    },

    // protected/inherited method
    isOpen : function(){
		return this.openState;
	},

	getTable : function(name, keyName){
		return new Ext.data.SqlDB.Table(this, name, keyName);
	}
});