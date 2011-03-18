<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
version="1.0">
 
  <!-- URL -->
  <xsl:param name="server">../</xsl:param>
  <xsl:param name="app">facetmap</xsl:param>
  <xsl:param name="app2">facetmap</xsl:param>
  <xsl:param name="nb_paging_facets">5</xsl:param>
  
  <xsl:template match="superset">
  <div class="chosen-facets">
    <xsl:variable name="taxtitle" select="@taxtitle" />
      <xsl:if test="position()=1">
        <div class="chosen-facets-title">Crit�res s�lectionn�s</div>
        <hr Class="hr1" />
      </xsl:if>
      <ul style="list-style-image: url('{$icons_url}/bullet.gif');">
        <li class="chosen-facet">
          <xsl:apply-templates select="s" mode="superselection" />
          <xsl:text> &gt; </xsl:text>
          <xsl:value-of select="../heading[@facet=$taxtitle]/@title" />
        </li>
      </ul>
	 </div>
  </xsl:template>
  <xsl:template match="s" mode="superselection">
    <xsl:if test="position()!=1">
    <xsl:text> &gt; </xsl:text>
	</xsl:if>
    <a href="{$server}/{$app2}/{$pre_reference_url}{@ref}" onclick="show_selection('{$server}/{$app}/{$pre_reference_url}{@ref}')">
      <xsl:value-of select="@title" />
    </a>
  </xsl:template>
  
<xsl:template name="make_link_from_ref">
    <xsl:param name="ref" />
    <xsl:param name="text" />
    <xsl:element name="a">
      <xsl:attribute name="href">
        <xsl:value-of select="$pre_reference_url" />
        <xsl:value-of select="$ref" />
        <xsl:value-of select="$post_reference_url" />
      </xsl:attribute>
      <xsl:value-of select="$text" />
    </xsl:element>
  </xsl:template>
</xsl:stylesheet>
