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


package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception;

public class ValueActionException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public static final String BAD_FORMAT = "The value of action parameter is empty or incorrect.";
	
	public ValueActionException (String message){
		super (message);
	}
	
}
