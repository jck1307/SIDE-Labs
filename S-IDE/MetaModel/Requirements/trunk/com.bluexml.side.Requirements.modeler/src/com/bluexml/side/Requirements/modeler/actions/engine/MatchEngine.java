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


package com.bluexml.side.Requirements.modeler.actions.engine;

import org.eclipse.emf.compare.FactoryException;
import org.eclipse.emf.compare.match.engine.GenericMatchEngine;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.requirements.Goal;
import com.bluexml.side.requirements.Privilege;
import com.bluexml.side.requirements.PrivilegeGroup;
import com.bluexml.side.requirements.PrivilegeNature;

public class MatchEngine extends GenericMatchEngine {

	public double computeSimilarity(Goal left, Goal right) {
		try {
			double csimilarity = contentSimilarity(left, right);
			double psimilarity = policySimilarity(left, right);
			return csimilarity * 0.8d +psimilarity * 0.2d;
		} catch (FactoryException e) {
			//Nothing to do
		}
		return 0;
	}
	
	private double policySimilarity(Goal left, Goal right) {
		double similarity = 0d;
		int[] policyL = {0,0,0,0};
		int[] policyR = {0,0,0,0};
		double[] common = {0,0,0,0};
		
		for (PrivilegeGroup pg : left.getPrivilegeGroup())
			for (Privilege p : pg.getPrivileges())
				if (p.getCategory().equals(PrivilegeNature.CREATE))
					policyL[0]++;
				else if (p.getCategory().equals(PrivilegeNature.READ))
					policyL[1]++;
				else if (p.getCategory().equals(PrivilegeNature.UPDATE))
					policyL[2]++;
				else if (p.getCategory().equals(PrivilegeNature.DELETE))
					policyL[3]++;

		for (PrivilegeGroup pg : right.getPrivilegeGroup())
			for (Privilege p : pg.getPrivileges())
				if (p.getCategory().equals(PrivilegeNature.CREATE))
					policyR[0]++;
				else if (p.getCategory().equals(PrivilegeNature.READ))
					policyR[1]++;
				else if (p.getCategory().equals(PrivilegeNature.UPDATE))
					policyR[2]++;
				else if (p.getCategory().equals(PrivilegeNature.DELETE))
					policyR[3]++;
		
		int nb = 4;
		for (int i = 0; i < 4; i++)
			if (Math.max(policyL[i], policyR[i])>0)
				common[i] = (1.0 * Math.min(policyL[i], policyR[i])) / Math.max(policyL[i], policyR[i]);
			else
				nb--;
		
		for (int i = 0; i < 4; i++)
			if (common[i] > 0)
				similarity += common[i]/nb;
		
		if (nb == 0)
			similarity = 1;
		
		return similarity;
	}

	protected double contentSimilarity(EObject obj1, EObject obj2) throws FactoryException {
		double similarity = 0d;
		similarity = NameSimilarity.nameSimilarityMetric(NameSimilarity.contentValue(obj1),
					NameSimilarity.contentValue(obj2));
		return similarity;
	}
}
