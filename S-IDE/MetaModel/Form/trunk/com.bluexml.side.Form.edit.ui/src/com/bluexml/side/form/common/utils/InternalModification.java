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


package com.bluexml.side.form.common.utils;

public class InternalModification {

	protected static boolean moveToDisabled = true;
	
	public static void moveToDisabled() {
		moveToDisabled = true;
	}
	
	public static void dontMoveToDisabled() {
		moveToDisabled = false;
	}
	
	public static boolean getMoveToDisabled() {
		return moveToDisabled;
	}
}
