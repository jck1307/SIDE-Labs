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


package com.bluexml.side.util.libs.eclipse;

import org.eclipse.core.runtime.internal.adaptor.ContextFinder;

public class EclipseUtils {

	@SuppressWarnings("restriction")
	public static ClassLoader getContextFinderClassLoader() {
		ClassLoader cl0 = Thread.currentThread().getContextClassLoader();
		ClassLoader cl = null;
		if (cl0 instanceof ContextFinder) {
			cl = cl0;
		} else {
			cl = new ContextFinder(cl0);
		}
		return cl;
	}
}
