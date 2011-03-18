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


package com.bluexml.side.util.libs.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipManager {

	static int BUFFER = 2048;

	public static void zip(File file, File outFile, boolean includeRoot) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFile)));
		if (file.isDirectory()) {
			if (includeRoot) {
				zipFolder(file, out, file.getParentFile().getPath());
			} else {
				zipFolder(file, out, file.getPath());
			}
		} else {
			zipFile(file, out);
		}
		cleanUp(out);
	}

	public static void zipFile(File inFolder, File outFile) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFile)));
		zipFile(inFolder, out);
		cleanUp(out);
	}

	private static void zipFile(File inFolder, ZipOutputStream out) throws Exception {

		// System.out.println("Adding: " + files[i]);
		BufferedInputStream in = null;
		byte[] data = new byte[BUFFER];
		in = new BufferedInputStream(new FileInputStream(inFolder.getPath()), BUFFER);

		out.putNextEntry(new ZipEntry(inFolder.getPath())); // write

		int count;
		while ((count = in.read(data, 0, BUFFER)) != -1) {
			out.write(data, 0, count);
		}
		out.closeEntry(); // close each entry
		cleanUp(in);
	}

	public static void zipFolder(File inFolder, File outFile) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFile)));
		zipFolder(inFolder, out, inFolder.getPath());
		cleanUp(out);
	}

	private static ZipOutputStream zipFolder(File inFolder, ZipOutputStream out, String rootPath) {

		try {
			// compress outfile stream

			// writting stream
			BufferedInputStream in = null;

			byte[] data = new byte[BUFFER];
			File files[] = inFolder.listFiles();

			for (int i = 0; i < files.length; i++) {
				File current = files[i];
				if (current.isDirectory()) {
					zipFolder(current, out, rootPath);
				} else {
					// System.out.println("Adding: " + files[i]);
					in = new BufferedInputStream(new FileInputStream(current), BUFFER);

					out.putNextEntry(new ZipEntry(buildZipPath(current, rootPath))); // write
					// data
					// header
					// (name, size, etc)
					int count;
					while ((count = in.read(data, 0, BUFFER)) != -1) {
						out.write(data, 0, count);
					}
					out.closeEntry(); // close each entry
				}
			}
			// cleanUp(out);
			cleanUp(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	private static void cleanUp(InputStream in) throws Exception {
		if (in != null) {
			in.close();
		}
	}

	private static void cleanUp(OutputStream out) throws Exception {
		if (out != null) {
			out.flush();
			out.close();
		}
	}

	private static String buildZipPath(File file, String rootPath) {
		String entryFilePath=file.getPath().replaceAll("\\\\", "/");
		if (rootPath.equals("")) {
			return entryFilePath;
		} else {			
			String pathToRemove =rootPath.replaceAll("\\\\", "/") + "/";			
			entryFilePath=entryFilePath.replaceFirst(pathToRemove, "");
			return entryFilePath; 
		}
	}
	
	public static void main(String[] args) throws Exception {
		File files = new File(args[1]);
		File zip = new File(args[0]);		
		ZipManager.zip(files, zip, false);
	}

}
