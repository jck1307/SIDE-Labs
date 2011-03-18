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


package com.bluexml.xforms.generator.forms.renderable.common;

import com.bluexml.xforms.generator.forms.Renderable;

/**
 * The Class RenderableTabWrapper.
 */
public class RenderableTabWrapper extends RenderableTab {

	/**
	 * Instantiates a new renderable tab wrapper.
	 * 
	 * @param renderable
	 *            the renderable
	 * @param title
	 *            the title
	 */
	public RenderableTabWrapper(Renderable renderable, String title) {
		super(title);
		add(renderable);
	}

}
