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
 * @class Ext.state.Manager
 * This is the global state manager. By default all components that are "state aware" check this class
 * for state information if you don't pass them a custom state provider. In order for this class
 * to be useful, it must be initialized with a provider when your application initializes. Example usage:
 <pre><code>
// in your initialization function
init : function(){
   Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
   var win = new Window(...);
   win.restoreState();
}
 </code></pre>
 * @singleton
 */
Ext.state.Manager = function(){
    var provider = new Ext.state.Provider();

    return {
        /**
         * Configures the default state provider for your application
         * @param {Provider} stateProvider The state provider to set
         */
        setProvider : function(stateProvider){
            provider = stateProvider;
        },

        /**
         * Returns the current value for a key
         * @param {String} name The key name
         * @param {Mixed} defaultValue The default value to return if the key lookup does not match
         * @return {Mixed} The state data
         */
        get : function(key, defaultValue){
            return provider.get(key, defaultValue);
        },

        /**
         * Sets the value for a key
         * @param {String} name The key name
         * @param {Mixed} value The state data
         */
         set : function(key, value){
            provider.set(key, value);
        },

        /**
         * Clears a value from the state
         * @param {String} name The key name
         */
        clear : function(key){
            provider.clear(key);
        },

        /**
         * Gets the currently configured state provider
         * @return {Provider} The state provider
         */
        getProvider : function(){
            return provider;
        }
    };
}();
