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


function showPannel(p_idPannel) {
	var pannels = new Array("stats", "deployment", "generation", "console", "service", "documentation");
	$.each(pannels, function(i, n) {
		if (n == p_idPannel) {
			$("#" + n).show();
		} else {
			$("#" + n).hide();
		}
	})
}

function switchLog(clickedText) {
	if (clickedText != null) {
		$(clickedText).hide();
		var sib = $(clickedText).siblings();
		$(sib[0]).slideDown("slow");
	}
}

function collapseBox(boxId, icon, otherIconId) {
	if (boxId != null) {
		$(document.getElementById(boxId)).slideUp("slow");
		$(icon).hide();
		$(document.getElementById(otherIconId)).show();
	}
}
function expandBox(boxId, icon, otherIconId) {
	if (boxId != null && $('#' + boxId) != null) {
		$(document.getElementById(boxId)).slideDown("slow");
		$(icon).hide();
		$(document.getElementById(otherIconId)).show();
	}
}
