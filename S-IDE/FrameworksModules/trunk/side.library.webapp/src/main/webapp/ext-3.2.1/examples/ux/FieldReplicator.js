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
Ext.ns("Ext.ux");

/**
 * @class Ext.ux.FieldReplicator
 * <p>A plugin for Field Components which creates clones of the Field for as
 * long as the user keeps filling them. Leaving the final one blank ends the repeating series.</p>
 * <p>Usage:</p>
 * <pre><code>
    {
        xtype: 'combo',
        plugins: [ Ext.ux.FieldReplicator ],
        triggerAction: 'all',
        fieldLabel: 'Select recipient',
        store: recipientStore
    }
 * </code></pre>
 */
Ext.ux.FieldReplicator = {
    init: function(f) {
        f.replicator = this;
        f.enableKeyEvents = true;
        f.on('change', this.onChange, this);
        f.onKeyDown = f.onKeyDown.createInterceptor(this.onKeyDown);
    },

//  If tabbing out and the change event will be fired, flag that
//  the change handler must focus the correct sibling Field.
    onKeyDown: function(e) {
        if ((e.getKey() == Ext.EventObject.TAB) && (String(this.startValue) !== String(this.getValue()))) {
            if (e.shiftKey) {
                this.focusPrev = true;
            } else if (!e.shiftKey && !e.altKey) {
                this.focusNext = true;
            }
        }
    },

//  Handle the field either being changed to blank or from blank.
    onChange: function(f, n, o) {

//		Ensure that "change" is only fired once.
    	f.startValue = n;

        var c = f.ownerCt, l,
            ps = f.previousSibling(),
            ns = f.nextSibling();
        if (Ext.isEmpty(n)) {
            if (!Ext.isEmpty(o)) {
//              The Field has been blanked, and it is not the only one left, remove it
                if ((ps && (ps.replicator === this)) || (ns && (ns.replicator === this))) {
                    l = f.findParentBy(function(p) {
                        return !Ext.isDefined(p.ownerCt);
                    });
                    c.remove(f);
                    l.doLayout();
                }
            }
        } else {
            if (Ext.isEmpty(o)) {
//              Field filled, insert a clone as the next sibling
                ns = new f.constructor(f.cloneConfig());
                c.insert(c.items.indexOf(f) + 1, ns);
                c.doLayout();
                l = f.findParentBy(function(p) {
                    return !Ext.isDefined(p.ownerCt);
                });
                l.doLayout();
            }
        }
        if (f.focusPrev) {
            delete f.focusPrev;
            ps.focus(false, true);
        } else  if (f.focusNext) {
            delete f.focusNext;
            ns.focus(false, true);
        }
    }
};
