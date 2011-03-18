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
 * @class Ext.form.TextArea
 * @extends Ext.form.TextField
 * Multiline text field.  Can be used as a direct replacement for traditional textarea fields, plus adds
 * support for auto-sizing.
 * @constructor
 * Creates a new TextArea
 * @param {Object} config Configuration options
 * @xtype textarea
 */
Ext.form.TextArea = Ext.extend(Ext.form.TextField,  {
    /**
     * @cfg {Number} growMin The minimum height to allow when <tt>{@link Ext.form.TextField#grow grow}=true</tt>
     * (defaults to <tt>60</tt>)
     */
    growMin : 60,
    /**
     * @cfg {Number} growMax The maximum height to allow when <tt>{@link Ext.form.TextField#grow grow}=true</tt>
     * (defaults to <tt>1000</tt>)
     */
    growMax: 1000,
    growAppend : '&#160;\n&#160;',

    enterIsSpecial : false,

    /**
     * @cfg {Boolean} preventScrollbars <tt>true</tt> to prevent scrollbars from appearing regardless of how much text is
     * in the field. This option is only relevant when {@link #grow} is <tt>true</tt>. Equivalent to setting overflow: hidden, defaults to 
     * <tt>false</tt>.
     */
    preventScrollbars: false,
    /**
     * @cfg {String/Object} autoCreate <p>A {@link Ext.DomHelper DomHelper} element spec, or true for a default
     * element spec. Used to create the {@link Ext.Component#getEl Element} which will encapsulate this Component.
     * See <tt>{@link Ext.Component#autoEl autoEl}</tt> for details.  Defaults to:</p>
     * <pre><code>{tag: "textarea", style: "width:100px;height:60px;", autocomplete: "off"}</code></pre>
     */

    // private
    onRender : function(ct, position){
        if(!this.el){
            this.defaultAutoCreate = {
                tag: "textarea",
                style:"width:100px;height:60px;",
                autocomplete: "off"
            };
        }
        Ext.form.TextArea.superclass.onRender.call(this, ct, position);
        if(this.grow){
            this.textSizeEl = Ext.DomHelper.append(document.body, {
                tag: "pre", cls: "x-form-grow-sizer"
            });
            if(this.preventScrollbars){
                this.el.setStyle("overflow", "hidden");
            }
            this.el.setHeight(this.growMin);
        }
    },

    onDestroy : function(){
        Ext.removeNode(this.textSizeEl);
        Ext.form.TextArea.superclass.onDestroy.call(this);
    },

    fireKey : function(e){
        if(e.isSpecialKey() && (this.enterIsSpecial || (e.getKey() != e.ENTER || e.hasModifier()))){
            this.fireEvent("specialkey", this, e);
        }
    },
    
    // private
    doAutoSize : function(e){
        return !e.isNavKeyPress() || e.getKey() == e.ENTER;
    },

    /**
     * Automatically grows the field to accomodate the height of the text up to the maximum field height allowed.
     * This only takes effect if grow = true, and fires the {@link #autosize} event if the height changes.
     */
    autoSize: function(){
        if(!this.grow || !this.textSizeEl){
            return;
        }
        var el = this.el,
            v = Ext.util.Format.htmlEncode(el.dom.value),
            ts = this.textSizeEl,
            h;
            
        Ext.fly(ts).setWidth(this.el.getWidth());
        if(v.length < 1){
            v = "&#160;&#160;";
        }else{
            v += this.growAppend;
            if(Ext.isIE){
                v = v.replace(/\n/g, '&#160;<br />');
            }
        }
        ts.innerHTML = v;
        h = Math.min(this.growMax, Math.max(ts.offsetHeight, this.growMin));
        if(h != this.lastHeight){
            this.lastHeight = h;
            this.el.setHeight(h);
            this.fireEvent("autosize", this, h);
        }
    }
});
Ext.reg('textarea', Ext.form.TextArea);
