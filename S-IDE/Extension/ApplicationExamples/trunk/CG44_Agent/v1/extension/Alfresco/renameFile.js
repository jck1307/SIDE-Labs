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


var index = document.parent.children.length;
var matricule = document.parent.name;

if (index <= 9)
	index = "00" + index;
else if (index <= 99)
	index = "0" + index;

var name = matricule+"_"+index+".pdf";

document.properties["cm:name"] = name;
document.properties["cm:title"] = "";
document.properties["cm:author"] = matricule;
document.save();
