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


package com.bluexml.side.requirements.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;

public class AnalysisGenerator extends RequirementsGenerator {

	private static String MM_URI = "http://www.kerblue.org/requirements/1.0";
	private static String KEY_RISKANALYSIS = "com.bluexml.side.Requirements.generator.office.excel.riskAnalysis";
	private static String KEY_DIAGNOSTIC = "com.bluexml.side.Requirements.generator.office.excel.diagnostic";

	@Override
	protected String getMetamodelURI() {
		return MM_URI;
	}

	@Override
	protected List<String> getTemplates(String keyGenerator) {
		List<String> l = new ArrayList<String>();
		if (keyGenerator.equals(KEY_RISKANALYSIS))
			l.add("/" + PLUGIN_ID
					+ "/src/interpretation/risk/generation/html/html.mt");
		if (keyGenerator.equals(KEY_DIAGNOSTIC))
			l
					.add("/"
							+ PLUGIN_ID
							+ "/src/interpretation/diagnostic/generation/html/json.mt");
		return l;
	}

	public Collection<IFile> complete(Map<String, List<IFile>> models) throws Exception {
		// Nothing to do
		return null;
	}

	@Override
	protected Map<String, String> getInputModels(String keyGenerator) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("IN", "RWM");
		return result;
	}

	@Override
	protected Map<String, String> getOutputModels(String keyGenerator) {
		Map<String, String> result = new HashMap<String, String>();
		if (keyGenerator.equals(KEY_RISKANALYSIS))
			result.put("OUT", "RISK");
		if (keyGenerator.equals(KEY_DIAGNOSTIC))
			result.put("OUT", "Diagnostic");
		return result;
	}

	@Override
	protected List<String> getANTScripts(String keyGenerator) {
		return Collections
					.singletonList("src/interpretation/commonportal/build-default/");
	}

	@Override
	protected String getASMFile(String keyGenerator) {
		if (keyGenerator.equals(KEY_RISKANALYSIS))
			return "src/interpretation/risk/transformation/RWM2Risk.asm";
		if (keyGenerator.equals(KEY_DIAGNOSTIC))
			return "src/interpretation/diagnostic/transformation/RWM2Diagnostic.asm";
		return "";
	}

	@Override
	protected String getTargetMetamodel(String keyGenerator) {
		if (keyGenerator.equals(KEY_RISKANALYSIS))
			return "src/interpretation/risk/risk.ecore";
		if (keyGenerator.equals(KEY_DIAGNOSTIC))
			return "src/interpretation/diagnostic/diagnostic.ecore";
		return "";
	}

	@Override
	protected Set<String> getTransformation() {
		Set<String> l = new HashSet<String>();

		if (generatorOptions.keySet().contains(KEY_RISKANALYSIS))
			l.add(KEY_RISKANALYSIS);
		if (generatorOptions.keySet().contains(KEY_DIAGNOSTIC))
			l.add(KEY_DIAGNOSTIC);

		return l;
	}

	@Override
	protected String getTargetModelName(String keyGenerator) {
		if (keyGenerator.equals(KEY_RISKANALYSIS))
			return "riskAnalysis.ecore";
		if (keyGenerator.equals(KEY_DIAGNOSTIC))
			return "diagnostic.ecore";
		return "";
	}

	protected Collection<String> getExtensionsForServices() {
		return Collections.singleton(".xml");
	}
}
