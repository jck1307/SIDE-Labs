<%--
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

--%>


<%--
Copyright (C) BlueXML 2005-2008

This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 --%>
<%
metamodel http://www.kerblue.org/workflow/1.0
import com.bluexml.side.workflow.generator.alfresco.templates.servicesTemplates.Common
import com.bluexml.side.workflow.generator.alfresco.WorkflowGenerator
%>
<%script type="workflow.Process" name="validatedFilename"%>
<%getTEMP_FOLDER()%>/<%getConfModulePath()%>/module-context.xml
<%script type="workflow.Process" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version='1.0' encoding='ISO-8859-1'?>
<!DOCTYPE beans PUBLIC '-//SPRING//DTD BEAN//EN' 'http://www.springframework.org/dtd/spring-beans.dtd'>
 
<beans>
 
    <bean id="<%getModuleIdService(name)%>.workflowBootstrap" parent="workflowDeployer">
		<property name="workflowDefinitions">
			<list>
				<props>
					<prop key="engineId">jbpm</prop>
					<prop key="location"><%getModulePath()%>/bpm/processdefinition.xml</prop>
					<prop key="mimetype">text/xml</prop>
					<prop key="redeploy">true</prop>
				</props>
			</list>
		</property>
		<property name="models">
			<list>
                <value><%getModulePath()%>/bpm/model.xml</value>
			</list>
		</property>
		<property name="labels">
			<list>
                <value><%getModulePath()%>/bpm/messages</value>
			</list>
		</property>
	</bean>
	
 	<bean id="<%getModuleIdService(name)%>_configBootstrap" class="org.alfresco.web.config.WebClientConfigBootstrap" init-method="init">
      <property name="configs">
        <list>
           <value>classpath:<%getModulePath()%>/web-client-config-custom.xml</value>
        </list>
      </property>
   </bean>
</beans>
