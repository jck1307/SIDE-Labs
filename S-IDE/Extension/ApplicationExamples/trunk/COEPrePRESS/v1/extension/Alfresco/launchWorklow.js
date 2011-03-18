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


var folder = companyhome.childByNamePath("/Sites/coe/documentLibrary/Demande de publication/En cours");
var name =  (new Date()).getTime();
var sfolder = folder.createFolder(name);
document.move(sfolder);
document.specializeType("COEPrePRESS:Document");

var demande = sfolder.createNode(name+"demande","COEPrePRESS:Demande");
document.createAssociation(demande,"COEPrePRESS:Document_demande_Demande");

var wfAction = actions.create("start-workflow");
wfAction.parameters.workflowName = "jbpm$wfbxdemande:demande";
wfAction.parameters.endStartTask = false;
wfAction.parameters["bpm:pooledActors"] = new Array(people.getGroup('GROUP_auteur'));
wfAction.parameters["cm:owner"] = null;
wfAction.parameters["cm:context"] = null;

wfAction.execute(document);
