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
 * @class Ext.slider.Tip
 * @extends Ext.Tip
 * Simple plugin for using an Ext.Tip with a slider to show the slider value. Example usage:
<pre>
new Ext.Slider({
    width: 214,
    minValue: 0,
    maxValue: 100,
    plugins: new Ext.slider.Tip()
});
</pre>
 * Optionally provide your own tip text by overriding getText:
 <pre>
 new Ext.Slider({
     width: 214,
     minValue: 0,
     maxValue: 100,
     plugins: new Ext.slider.Tip({
         getText: function(thumb){
             return String.format('<b>{0}% complete</b>', thumb.value);
         }
     })
 });
 </pre>
 */
Ext.slider.Tip = Ext.extend(Ext.Tip, {
    minWidth: 10,
    offsets : [0, -10],
    
    init: function(slider) {
        slider.on({
            scope    : this,
            dragstart: this.onSlide,
            drag     : this.onSlide,
            dragend  : this.hide,
            destroy  : this.destroy
        });
    },
    
    /**
     * @private
     * Called whenever a dragstart or drag event is received on the associated Thumb. 
     * Aligns the Tip with the Thumb's new position.
     * @param {Ext.slider.MultiSlider} slider The slider
     * @param {Ext.EventObject} e The Event object
     * @param {Ext.slider.Thumb} thumb The thumb that the Tip is attached to
     */
    onSlide : function(slider, e, thumb) {
        this.show();
        this.body.update(this.getText(thumb));
        this.doAutoWidth();
        this.el.alignTo(thumb.el, 'b-t?', this.offsets);
    },

    /**
     * Used to create the text that appears in the Tip's body. By default this just returns
     * the value of the Slider Thumb that the Tip is attached to. Override to customize.
     * @param {Ext.slider.Thumb} thumb The Thumb that the Tip is attached to
     * @return {String} The text to display in the tip
     */
    getText : function(thumb) {
        return String(thumb.value);
    }
});

//backwards compatibility - SliderTip used to be a ux before 3.2
Ext.ux.SliderTip = Ext.slider.Tip;
