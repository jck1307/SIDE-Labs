
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


function check_selected_criteria(){
	var url = parent.frames[1].document.location;
	var el_2 = parent.frames[1].document.getElementById('facetmap-browse-content');
	if(el_2){
		if(url == 'facetmap/browseSimple_content.jsp' || url == 'facetmap/browseSimple_content.jsp?&s='){
		el_2.style.display = 'none';
		}	
	}	
}

function check_pagination(){
	/*alert("page");
	var url = parent.frames[1].document.location;
	if(url == 'http://localhost:8080/facetmap-content/browseSimple.jsp' || url == 'http://localhost:8080/facetmap-content/browseSimple.jsp?&s='){
		alert("page");
	}*/	
}
