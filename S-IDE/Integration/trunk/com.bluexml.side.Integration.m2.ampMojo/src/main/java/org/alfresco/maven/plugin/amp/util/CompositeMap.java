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


 package org.alfresco.maven.plugin.amp.util;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @version $Id: CompositeMap.java 565036 2007-08-12 10:26:14Z snicoll $
 * @todo merge with resources/assembly plugin
 */
public class CompositeMap
    extends AbstractMap
{
    private Map recessive;

    private Map dominant;

    public CompositeMap( Map dominant, Map recessive )
    {
        this.dominant = Collections.unmodifiableMap( dominant );

        this.recessive = Collections.unmodifiableMap( recessive );
    }

    public synchronized Object get( Object key )
    {
        Object value = dominant.get( key );

        if ( value == null )
        {
            value = recessive.get( key );
        }

        return value;
    }

    public Set entrySet()
    {
        throw new UnsupportedOperationException( "Cannot enumerate properties in a composite map" );
    }
}
