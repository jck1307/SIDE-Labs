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
package com.bluexml.side.Framework.alfresco.dataGenerator.generator;

import com.bluexml.side.Framework.alfresco.dataGenerator.graph.IArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.INode;

/**
 * @author davidchevrier
 *
 */
public interface IInstance {
	
	/**
	 * 
	 * @param type
	 * @return the instance node of the given type
	 * @throws Exception
	 */
	public INode instanciation(Object type) throws Exception;
	
	/**
	 * 
	 * @param source
	 * @param target
	 * @param associationDefinition
	 * @return arc instance of the association 
	 */
	public IArc instanciation(INode source, INode target, Object associationDefinition);

}
