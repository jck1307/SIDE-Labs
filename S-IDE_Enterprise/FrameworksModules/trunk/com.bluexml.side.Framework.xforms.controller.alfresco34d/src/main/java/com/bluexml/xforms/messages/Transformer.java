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


package com.bluexml.xforms.messages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Use this class for building the value of the arrays in
 * {@link com.bluexml.xforms.messages.DefaultMessages}
 * <p/>
 * HOWTO: change the path to a '.properties' file and run the class. Copy the output and paste as
 * the value for a string array.
 * <p/>
 * Obviously, the '.properties' file whose path is indicated must be exactly what is expected in
 * default messages files that will be generated.
 * 
 * @author Amenel
 * 
 */
public class Transformer {

	public String convertStreamToString(InputStream is) {
		/*
		 * To convert the InputStream to String we use the BufferedReader.readLine() method. We
		 * iterate until the BufferedReader return null which means there's no more data to read.
		 * Each line will be appended to a StringBuilder and returned as String.
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		String outputLine;
		String replaced;
		boolean first = true;
		try {
			while ((line = reader.readLine()) != null) {
				outputLine = (first) ? "" : ",";
				replaced = line.replaceAll("\\\\", "\\\\\\\\");
				replaced = replaced.replaceAll("\"", "\\\\\"");
				outputLine += "\"" + replaced + "\"";
				System.out.println(outputLine);
				sb.append(outputLine);
				first = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String path = "/Users/amenel/workspace/repository/XForms/dev/examples/xforms/src/main/resources/messages.properties";
		FileInputStream is = new FileInputStream(new File(path));
		new Transformer().convertStreamToString(is);
	}

}
