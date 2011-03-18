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


/**
 * 
 */
package com.bluexml.xforms.messages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Amenel
 * 
 */
public class MsgPool {
	/** The loaded properties. */
	private static Properties pool;

	/** The complete path to the message file that contains the properties. */
	private static String messageFilePath;

	private static InputStream inputStream = null;

	/** The logger. */
	protected static Log logger = LogFactory.getLog(MsgPool.class);

	private static MsgPool instance = null;

	private MsgPool() {
		boolean closeAfterwards = false;
		pool = new Properties();

		// open and load
		try {
			if (inputStream == null) {
				File messagesFile = new File(messageFilePath);
				inputStream = new FileInputStream(messagesFile);
				closeAfterwards = true;
			}
			pool.load(inputStream);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("Error opening the message file.");
			}
		}

		// close the stream if applicable
		if (closeAfterwards) {
			try {
				inputStream.close();
			} catch (IOException e) {
				if (logger.isErrorEnabled()) {
					logger.error("Failed to close the message file. Continuing anyway.");
				}
				e.printStackTrace();
			}
		}
	}

	/**
	 * Sets the stream from which to load a message file. Previously loaded messages are lost.<br/>
	 * Used at runtime.
	 * 
	 * @param inputStream
	 *            the inputStream to set
	 */
	public static void setInputStream(InputStream stream) {
		inputStream = stream;
		instance = null;
	}

	/**
	 * Sets the path to a new message file. Previously loaded messages are lost. <br/>
	 * Used at generation time.
	 * 
	 * @param path
	 *            the complete path to the message file.
	 */
	public static void setMessagesFile(String path) {
		messageFilePath = path;
		inputStream = null;
		instance = null;
	}

	/**
	 * Returns the string that is associated to a key in the messages file. The string may be empty
	 * but a null value is never returned.
	 * 
	 * @param msgKey
	 *            the key, either an enum from class MsgId or a String
	 * @return the string attached to the key
	 */
	public static String getMsg(Object msgKey, String... args) {
		String theKey = null;

		if (msgKey instanceof MsgId) {
			MsgId theMsgId = (MsgId) msgKey;
			theKey = theMsgId.getText();
		} else if (msgKey instanceof String) {
			theKey = (String) msgKey;
		}

		getInstance();
		String res = MsgPool.getPool().getProperty(theKey);
		if (res != null) {
			return replaceArgs(res, args);
		}
		// a key may be set to an empty string in the properties file
		if (hasProperty(theKey) == true) {
			return "";
		}
		if (logger.isWarnEnabled()) {
			logger.warn("Message not found for key: " + theKey);
		}
		if (StringUtils.equals(theKey, MsgId.MSG_KEY_NOT_FOUND.getText())) {
			return MsgId.INT_MSGPOOL_NO_MESSAGE_FILE.getText();
		}
		return MsgPool.getMsg(MsgId.MSG_KEY_NOT_FOUND);
	}

	private static String replaceArgs(String msg, String[] args) {
		String result = msg;
		int idx = 0;
		for (String arg : args) {
			String idxStr = "{" + idx + "}";
			result = StringUtils.replace(result, idxStr, arg);
			idx++;
		}
		return result;
	}

	/**
	 * Tests whether a key exists in the properties file.
	 * 
	 * @param theKey
	 *            the reference key text being tested
	 * @return
	 */
	private static boolean hasProperty(String theKey) {
		getInstance();
		Enumeration<?> properties = MsgPool.getPool().propertyNames();
		while (properties.hasMoreElements()) {
			String aKey = (String) properties.nextElement();
			if (StringUtils.equals(theKey, aKey)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Tests the availability of a key. Extends {@link #getMsg(Object, String...)} by providing the
	 * possibility of testing against a <code>null</code> value.
	 * 
	 * @param theKey
	 * @return the key value if the key is found, null otherwise
	 */
	public static String testMsg(Object theKey, String... args) {
		String res = MsgPool.getMsg(theKey, args);
		if (StringUtils.equals(res, MsgId.INT_MSGPOOL_NO_MESSAGE_FILE.getText())
				|| StringUtils.equals(res, MsgPool.getMsg(MsgId.MSG_KEY_NOT_FOUND))) {
			return null;
		}
		return res;
	}

	public static MsgPool getInstance() {
		if (instance == null) {
			instance = new MsgPool();
		}
		return instance;
	}

	/**
	 * @return the pool
	 */
	private static Properties getPool() {
		return pool;
	}

}
