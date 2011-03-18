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


document.specializeType('DigitizationProcess:com_bluexml_side_models_list_Document');

var wfAction = actions.create("start-workflow");
wfAction.parameters.workflowName = "jbpm$wfbxDigitizationProcess:DigitizationProcess";
wfAction.parameters.endStartTask = true;
wfAction.parameters["bpm:pooledActors"] = new Array(people.getGroup('GROUP_Digitization'));
wfAction.parameters["cm:owner"] = null;
wfAction.parameters["cm:context"] = null;

wfAction.execute(document);
