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


//var sitePresets = [{id: "site-dashboard", name: msg.get("title.collaborationSite")}
//,{id: "site-demo", name: msg.get("title.demoSite")}
//,{id: "site-test", name: msg.get("title.testSite")}
//];
var sitePresets = [];
var siteDisabled = ["user-dashboard", "rm-site-dashboard", "document-workspace", "meeting-workspace"];


for ( var c = 0; c < presets.length; c++) {
	var id_ = presets[c];
	var record = null;
	
	if (!disabled(id_)) {
		// manage collaborationSite
		if (id_ == "site-dashboard") {
			record = {
				id : "site-dashboard",
				name : msg.get("title.collaborationSite")
			}
		} else {
			var name_ = msg.get("title." + id_ + "Site");
			record = {
				id : id_,
				name : name_
			};
		}
		sitePresets.push(record);
	}
	
	
}

model.sitePresets = sitePresets;

function disabled(id) {	
	for (item in siteDisabled) {
		if (id == siteDisabled[item]) {
			return true;
		}
	}	
	return false;
}
