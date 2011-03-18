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


package com.bluexml.side.util.libs.ecore;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;

public class EcoreHelper {

	
	/**
	 * Launch validation on given EObject
	 * 
	 * @param eo
	 * @return
	 */
	public static boolean validate(EObject eo) {
		boolean result = true;
		Diagnostician diag = new Diagnostician();
		BasicDiagnostic diagnostics = diag.createDefaultDiagnostic(eo);
		diag.validate(eo, diagnostics);
		if (diagnostics.getSeverity() == Diagnostic.ERROR) {
			result = false;
		}
		return result;
	}
}
