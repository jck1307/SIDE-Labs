<?xml version="1.0" encoding="UTF-8"?>
<#--
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

-->


<nodes>
<#list nodes as node>
	<node>
<#list node.properties?keys as kkk>
 <#if node.properties[kkk]?exists && kkk != "{http://www.alfresco.org/model/content/1.0}content"> 
		<property name="${kkk}"><#if node.properties[kkk]?is_date>${node.properties[kkk]?date }<#elseif node.properties[kkk]?is_boolean>${node.properties[kkk]?string}<#else>${node.properties[kkk]!"-"}</#if></property>
 </#if>
</#list>

<#list node.assocs?keys as aaa>
 <#if node.assocs[aaa]?exists>
  <#list node.assocs[aaa] as assocaaa>
	<association name="${aaa}">${assocaaa.nodeRef}</association>
  </#list>
 </#if>
</#list>

<#list node.childAssocs?keys as aaa>
 <#if node.childAssocs[aaa]?exists>
  <#list node.childAssocs[aaa] as assocaaa>
	<child-association name="${aaa}">${assocaaa.nodeRef}</child-association>
  </#list>
 </#if>
</#list>

	</node>
</#list>
</nodes>
