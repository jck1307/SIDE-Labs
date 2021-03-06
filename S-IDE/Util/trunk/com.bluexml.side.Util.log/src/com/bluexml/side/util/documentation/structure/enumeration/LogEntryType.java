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


package com.bluexml.side.util.documentation.structure.enumeration;

public enum LogEntryType {
	GENERATION_INFORMATION("GenerationInformation"), ERROR("Error"), WARNING(
			"Warning"), DEPLOYMENT_INFORMATION("DeploymentInformation"), GENERATED_FILE("GeneratedFile"), SERVICE("Service"), CONSOLE("ConsoleOutput");

	private final String name;

	private LogEntryType(String p_name) {
		this.name = p_name;
	}

	public String getName() {
		return name;
	}
}
