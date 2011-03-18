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


<%
metamodel http://www.kerblue.org/form/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.form.generator.alfresco34d.FormGenerator
 
%>

  
<%script type="form.ClassFormCollection" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext")%>/<%getModuleIdService(getRootPackage().name)%>/module-context.xml<%}%>

<%script type="form.ClassFormCollection" name="generate" file="<%fileName()%>" %>
<?xml version='1.0' encoding='UTF-8'?>
<!DOCTYPE beans PUBLIC '-//SPRING//DTD BEAN//EN' 'http://www.springframework.org/dtd/spring-beans.dtd'>

<beans>

   <bean id="<%getRootPackage().name%>ResourceBundle" class="org.springframework.extensions.surf.util.ResourceBundleBootstrapComponent">
      <property name="resourceBundles">
         <list>
            <value>alfresco.messages.<%getRootPackage().name%></value>
         </list>
      </property>
   </bean>

   <!-- Provide <%getRootPackage().name%> form config -->
   <bean id="<%getRootPackage().name%>FormsClientConfig" class="org.springframework.extensions.config.ConfigBootstrap" 
         init-method="register">
      <property name="configService" ref="web.config" />
      <property name="configs">
         <list>
            <value>classpath:alfresco/web-extension/<%getModuleIdService(getRootPackage().name)%>/share-forms-config.xml</value>
            <value>classpath:alfresco/web-extension/<%getModuleIdService(getRootPackage().name)%>/share-forms-config-custom.xml</value>
         </list>
      </property>
   </bean>

</beans>
