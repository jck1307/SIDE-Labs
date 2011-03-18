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


if(!dojo._hasResource["dojox.analytics.plugins.dojo"]){ //_hasResource checks added by build. Do not use _hasResource directly in your code.
dojo._hasResource["dojox.analytics.plugins.dojo"] = true;
dojo.require("dojox.analytics._base");
dojo.provide("dojox.analytics.plugins.dojo");

dojox.analytics.plugins.dojo = new (function(){
	// summary:
	//	plugin to have analyitcs return the base info dojo collects
	this.addData = dojo.hitch(dojox.analytics, "addData", "dojo");
	dojo.addOnLoad(dojo.hitch(this, function(){
		var data = {};
		for(var i in dojo){
			if ((i=="version") || ((!dojo.isObject(dojo[i]))&&(i[0]!="_"))){
				data[i]=dojo[i];
			}
		}

		if (dojo.config){data.djConfig=dojo.config}
		this.addData(data);
	}));
})();

}
