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


package com.bluexml.side.util.componentmonitor.headless;

import org.eclipse.swt.graphics.Point;

import com.bluexml.side.util.componentmonitor.headLessinterface.ProgressBarInterface;
public class progressBarHeadLess implements ProgressBarInterface {

	public int max = 1;
	public int min = 0;
	public int state = 1;
	public int selection = 0;

	public Point computeSize(int wHint, int hHint, boolean changed) {
		return null;
	}

	public int getMaximum() {
		return max;
	}

	public int getMinimum() {
		return min;
	}

	public int getSelection() {
		return selection;
	}

	public int getState() {
		return state;
	}

	public void setMaximum(int value) {
		max = value;
	}

	public void setMinimum(int value) {
		min = value;
	}

	public void setSelection(int selection) {
		this.selection = selection;
	}

	public void setState(int state) {
		this.state = state;
	}

}
