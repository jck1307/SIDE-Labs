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


dojo.provide("chiba.widget.DropdownTimePicker");

dojo.require("dojo.widget.DropdownTimePicker");

dojo.widget.defineWidget(
        "chiba.widget.DropdownTimePicker",
        dojo.widget.DropdownTimePicker,
{
    widgetType: "chiba:DropdownTimePicker",
    datatype:"time",
    id:"",

    fillInTemplate: function(args, frag) {
        dojo.debug("Date.fillInTemplate", this, arguments);
        chiba.widget.DropdownTimePicker.superclass.fillInTemplate.apply(this, arguments);        
        this.domNode.setAttribute("xfreadonly", this.xfreadonly);
        this.inputNode.setAttribute("class", "value");

        if (this.xfreadonly == true) {
            this.inputNode.setAttribute("disabled", "disabled");
        }
        else {
            this.inputNode.removeAttribute("disabled");
        }

    },

		getValue: function(){
			// summary: return current date in RFC 3339 format
			return this.inputNode.value; /*String*/
		},

    onIconClick: function(evt) {
        if (this.xfreadonly == true) {
            this.isEnabled = false;
        }
        else {
            this.isEnabled = true;
        }
        chiba.widget.DropdownTimePicker.superclass.onIconClick.apply(this, arguments);
    },

    updateReadonly: function(readonly) {
        this.xfreadonly = readonly;
        if (this.xfreadonly == true) {
            this.inputNode.setAttribute("disabled", "disabled");
            this.isEnabled = false;
        }
        else {
            this.isEnabled = true;
            this.inputNode.removeAttribute("disabled");
        }
    },
    
    onInputChange: function() {
        chiba.widget.DropdownTimePicker.superclass.onInputChange.call(this);
        var sessionKey = document.getElementById("chibaSessionKey").value;
        Flux.setXFormsValue(updateUI, this.id.substring(0, this.id.length - 6), this.getValue() + ":00", sessionKey);
        //summary: triggered when this.value is changed
    },

    onSetTime: function() {
        var oldTime = this.getValue();
        chiba.widget.DropdownTimePicker.superclass.onSetTime.call(this);
        var newTime = this.getValue();
        if (oldTime != newTime) {
            var sessionKey = document.getElementById("chibaSessionKey").value;
            Flux.setXFormsValue(updateUI, this.id.substring(0, this.id.length - 6), newTime + ":00", sessionKey);
        }
    }
},
        "html"
        );
