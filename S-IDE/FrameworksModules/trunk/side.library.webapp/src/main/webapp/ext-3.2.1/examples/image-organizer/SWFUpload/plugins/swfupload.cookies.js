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
/*
	Cookie Plug-in
	
	This plug in automatically gets all the cookies for this site and adds them to the post_params.
	Cookies are loaded only on initialization.  The refreshCookies function can be called to update the post_params.
	The cookies will override any other post params with the same name.
*/

var SWFUpload;
if (typeof(SWFUpload) === "function") {
	SWFUpload.prototype.initSettings = function (oldInitSettings) {
		return function () {
			if (typeof(oldInitSettings) === "function") {
				oldInitSettings.call(this);
			}
			
			this.refreshCookies(false);	// The false parameter must be sent since SWFUpload has not initialzed at this point
		};
	}(SWFUpload.prototype.initSettings);
	
	// refreshes the post_params and updates SWFUpload.  The sendToFlash parameters is optional and defaults to True
	SWFUpload.prototype.refreshCookies = function (sendToFlash) {
		if (sendToFlash === undefined) {
			sendToFlash = true;
		}
		sendToFlash = !!sendToFlash;
		
		// Get the post_params object
		var postParams = this.settings.post_params;
		
		// Get the cookies
		var i, cookieArray = document.cookie.split(';'), caLength = cookieArray.length, c, eqIndex, name, value;
		for (i = 0; i < caLength; i++) {
			c = cookieArray[i];
			
			// Left Trim spaces
			while (c.charAt(0) === " ") {
				c = c.substring(1, c.length);
			}
			eqIndex = c.indexOf("=");
			if (eqIndex > 0) {
				name = c.substring(0, eqIndex);
				value = c.substring(eqIndex + 1);
				postParams[name] = value;
			}
		}
		
		if (sendToFlash) {
			this.setPostParams(postParams);
		}
	};

}
