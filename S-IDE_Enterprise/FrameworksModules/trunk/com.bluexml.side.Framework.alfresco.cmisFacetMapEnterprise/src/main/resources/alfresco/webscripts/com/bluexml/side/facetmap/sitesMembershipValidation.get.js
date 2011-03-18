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


script: {
	if (shortName != null && ticketValide != null) {
		// get Memberships
		var site = siteService.getSite(shortName);

		// get the filters
		var nameFilter = args["nf"];
		var roleFilter = args["rf"];

		var sizeString = args["size"];
		var memberships = site.listMembers(nameFilter, roleFilter,
				sizeString != null ? parseInt(sizeString) : 0);

		var users = getUsers(memberships);
		model.validity = false;
		for ( var c = 0; c < users.length; c++) {
			var u = users[c];
			if (u.properties["cm:userName"] == args["userName"]) {
				model.validity = true;
				break;
			}
		}
		model.validity = model.validity && ticketValide;
	} else {
		status.code = 500;
		status.message = "something wrong check given parameters";
		status.redirect = true;
		break script;
	}

}

function getUsers(memberships) {
	var users = [];
	for (userName in memberships) {
		var membershipRole = memberships[userName];
		if (userName.match("^GROUP_")) {
			var group_users = people.getMembers(people.getGroup(userName));
			users = users.concat(group_users);

		} else {
			users.push(people.getPerson(userName));
		}
	}
	return users;
}
