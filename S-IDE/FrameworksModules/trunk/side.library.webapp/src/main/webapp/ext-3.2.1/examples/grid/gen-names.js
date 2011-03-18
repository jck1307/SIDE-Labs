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
(function(){
    var lasts = ['Jones', 'Smith', 'Lee', 'Wilson', 'Black', 'Williams', 'Lewis', 'Johnson', 'Foot', 'Little', 'Vee', 'Train', 'Hot', 'Mutt'];
    var firsts = ['Fred', 'Julie', 'Bill', 'Ted', 'Jack', 'John', 'Mark', 'Mike', 'Chris', 'Bob', 'Travis', 'Kelly', 'Sara'];
    var lastLen = lasts.length, firstLen = firsts.length;

    Ext.ux.getRandomInt = function(min, max){
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    Ext.ux.generateName = function(){
        var name = firsts[Ext.ux.getRandomInt(0, firstLen-1)] + ' ' + lasts[Ext.ux.getRandomInt(0, lastLen-1)];
        if(Ext.ux.generateName.usedNames[name]){
            return Ext.ux.generateName();
        }
        Ext.ux.generateName.usedNames[name] = true;
        return name;
    }
    Ext.ux.generateName.usedNames = {};

})();
