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


package com.bluexml.side.integration.buildHudson.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TEST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> listeProjetPoms = new ArrayList<String>();
		File f = new File("/Users/davidabad/.hudson/jobs/Build_RCP_Community_TEST/buildAuto/Ankle/repositoryCopy/S-IDE");
		listeProjetPoms = BuilderUtils.findFile(f, "pom.xml");

		for (String string : listeProjetPoms) {
			System.out.println(string);
		}
		System.out.println("poms :"+listeProjetPoms.size());
	}

}
