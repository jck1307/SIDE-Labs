<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cmis="http://www.cmis.org/2008/05" xmlns:alf="http://www.alfresco.com" version="2.0">
	<!-- classes node -->
	<xsl:template match="nodes">
		<facetmap>
			<resources>
				<xsl:apply-templates/>
			</resources>
			<taxonomy title="Personne.mobilite" root-heading-title="Personne.mobilite" facetid="com.bluexml.demo.rh.Personne.mobilite">
				<heading id="Mobilit� is null" title="Mobilit� is null"/> 
				<xsl:for-each select="child::node/property[@name='{http://www.bluexml.com/model/content/com/1.0}com_bluexml_demo_rh_Personne_mobilite']">          
					<heading id="{normalize-space(current())}" title="{normalize-space(current())}"/>             
				</xsl:for-each> 
			</taxonomy>
			<taxonomy title="Personne.permisB" root-heading-title="Personne.permisB" facetid="com.bluexml.demo.rh.Personne.permisB">
				<heading id="Permis B is null" title="Permis B is null"/> 
				<xsl:for-each select="child::node/property[@name='{http://www.bluexml.com/model/content/com/1.0}com_bluexml_demo_rh_Personne_permisB']">          
					<heading id="{normalize-space(current())}" title="{normalize-space(current())}"/>             
				</xsl:for-each> 
			</taxonomy>
			<taxonomy title="Personne.age" root-heading-title="Personne.age" facetid="com.bluexml.demo.rh.Personne.age">
				<heading id="Age is null" title="Age is null"/> 
				<xsl:for-each select="child::node/property[@name='{http://www.bluexml.com/model/content/com/1.0}com_bluexml_demo_rh_Personne_age']">          
					<heading id="{normalize-space(current())}" title="{normalize-space(current())}"/>             
				</xsl:for-each> 
			</taxonomy>
			
		</facetmap>
	</xsl:template>
	
	<!-- entry node -->
	<xsl:template match="node">
		<xsl:variable name="idDoc" select="substring(child::id[1],10)"/>  
		<resource
			title="Employe : {normalize-space(child::property[@name='{http://www.bluexml.com/model/content/com/1.0}com_bluexml_demo_rh_Personne_nom'])} {normalize-space(child::property[@name='{http://www.bluexml.com/model/content/com/1.0}com_bluexml_demo_rh_Personne_prenom'])}"
			href="{$idDoc}">
			<xsl:choose>	
				<xsl:when test="child::property[@name='{http://www.bluexml.com/model/content/com/1.0}com_bluexml_demo_rh_Personne_mobilite']!= ''">
					<map heading="{normalize-space(child::property[@name='{http://www.bluexml.com/model/content/com/1.0}com_bluexml_demo_rh_Personne_mobilite'])}"/>
				</xsl:when>
				<xsl:otherwise>
					<map heading="Mobilit� is null"/>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:choose>	
				<xsl:when test="child::property[@name='{http://www.bluexml.com/model/content/com/1.0}com_bluexml_demo_rh_Personne_permisB']!= ''">
					<map heading="{normalize-space(child::property[@name='{http://www.bluexml.com/model/content/com/1.0}com_bluexml_demo_rh_Personne_permisB'])}"/>
				</xsl:when>
				<xsl:otherwise>
					<map heading="Permis B is null"/>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:choose>	
				<xsl:when test="child::property[@name='{http://www.bluexml.com/model/content/com/1.0}com_bluexml_demo_rh_Personne_age']!= ''">
					<map heading="{normalize-space(child::property[@name='{http://www.bluexml.com/model/content/com/1.0}com_bluexml_demo_rh_Personne_age'])}"/>
				</xsl:when>
				<xsl:otherwise>
					<map heading="Age is null"/>
				</xsl:otherwise>
			</xsl:choose>
			
		</resource>
	</xsl:template>
	
	<!-- cmis:object node -->
	<xsl:template match="cmis:object">
		<xsl:apply-templates/>
	</xsl:template>
	
	<!-- cmis:properties node -->
	<xsl:template match="cmis:properties">
		<xsl:apply-templates/>
	</xsl:template>
	
</xsl:stylesheet>
