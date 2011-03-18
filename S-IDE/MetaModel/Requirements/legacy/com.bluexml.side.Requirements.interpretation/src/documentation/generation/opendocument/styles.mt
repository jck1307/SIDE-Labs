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
metamodel http://www.bluexml.com/rwm/documentation/1.0/
%>

<%script type="Documentation.Book" name="styles" file="cdc/styles.xml"%>
<?xml version="1.0" encoding="UTF-8"?>
<office:document-styles
	xmlns:office="urn:oasis:names:tc:opendocument:xmlns:office:1.0"
	xmlns:style="urn:oasis:names:tc:opendocument:xmlns:style:1.0"
	xmlns:text="urn:oasis:names:tc:opendocument:xmlns:text:1.0"
	xmlns:table="urn:oasis:names:tc:opendocument:xmlns:table:1.0"
	xmlns:draw="urn:oasis:names:tc:opendocument:xmlns:drawing:1.0"
	xmlns:fo="urn:oasis:names:tc:opendocument:xmlns:xsl-fo-compatible:1.0"
	xmlns:xlink="http://www.w3.org/1999/xlink"
	xmlns:dc="http://purl.org/dc/elements/1.1/"
	xmlns:meta="urn:oasis:names:tc:opendocument:xmlns:meta:1.0"
	xmlns:number="urn:oasis:names:tc:opendocument:xmlns:datastyle:1.0"
	xmlns:svg="urn:oasis:names:tc:opendocument:xmlns:svg-compatible:1.0"
	xmlns:chart="urn:oasis:names:tc:opendocument:xmlns:chart:1.0"
	xmlns:dr3d="urn:oasis:names:tc:opendocument:xmlns:dr3d:1.0"
	xmlns:math="http://www.w3.org/1998/Math/MathML"
	xmlns:form="urn:oasis:names:tc:opendocument:xmlns:form:1.0"
	xmlns:script="urn:oasis:names:tc:opendocument:xmlns:script:1.0"
	xmlns:ooo="http://openoffice.org/2004/office"
	xmlns:ooow="http://openoffice.org/2004/writer"
	xmlns:oooc="http://openoffice.org/2004/calc"
	xmlns:dom="http://www.w3.org/2001/xml-events"
	xmlns:rpt="http://openoffice.org/2005/report"
	xmlns:of="urn:oasis:names:tc:opendocument:xmlns:of:1.2"
	xmlns:rdfa="http://docs.oasis-open.org/opendocument/meta/rdfa#"
	office:version="1.2">
	<office:font-face-decls>
		<style:font-face style:name="Tahoma1" svg:font-family="Tahoma" />
		<style:font-face style:name="Times New Roman"
			svg:font-family="&apos;Times New Roman&apos;"
			style:font-family-generic="roman" style:font-pitch="variable" />
		<style:font-face style:name="Arial" svg:font-family="Arial"
			style:font-family-generic="swiss" style:font-pitch="variable" />
		<style:font-face style:name="Arial Unicode MS"
			svg:font-family="&apos;Arial Unicode MS&apos;"
			style:font-family-generic="system" style:font-pitch="variable" />
		<style:font-face style:name="Tahoma" svg:font-family="Tahoma"
			style:font-family-generic="system" style:font-pitch="variable" />
	</office:font-face-decls>
	<office:styles>
		<style:default-style style:family="graphic">
			<style:graphic-properties draw:shadow-offset-x="0.3cm"
				draw:shadow-offset-y="0.3cm"
				draw:start-line-spacing-horizontal="0.283cm"
				draw:start-line-spacing-vertical="0.283cm"
				draw:end-line-spacing-horizontal="0.283cm"
				draw:end-line-spacing-vertical="0.283cm"
				style:flow-with-text="false" />
			<style:paragraph-properties
				style:text-autospace="ideograph-alpha" style:line-break="strict"
				style:writing-mode="lr-tb"
				style:font-independent-line-spacing="false">
				<style:tab-stops />
			</style:paragraph-properties>
			<style:text-properties style:use-window-font-color="true"
				fo:font-size="12pt" fo:language="fr" fo:country="FR"
				style:letter-kerning="true" style:font-size-asian="12pt"
				style:language-asian="zxx" style:country-asian="none"
				style:font-size-complex="12pt" style:language-complex="zxx"
				style:country-complex="none" />
		</style:default-style>
		<style:default-style style:family="paragraph">
			<style:paragraph-properties
				fo:hyphenation-ladder-count="no-limit"
				style:text-autospace="ideograph-alpha"
				style:punctuation-wrap="hanging" style:line-break="strict"
				style:tab-stop-distance="1.251cm" style:writing-mode="page" />
			<style:text-properties style:use-window-font-color="true"
				style:font-name="Times New Roman" fo:font-size="12pt"
				fo:language="fr" fo:country="FR" style:letter-kerning="true"
				style:font-name-asian="Arial Unicode MS"
				style:font-size-asian="12pt" style:language-asian="zxx"
				style:country-asian="none" style:font-name-complex="Tahoma"
				style:font-size-complex="12pt" style:language-complex="zxx"
				style:country-complex="none" fo:hyphenate="false"
				fo:hyphenation-remain-char-count="2"
				fo:hyphenation-push-char-count="2" />
		</style:default-style>
		<style:default-style style:family="table">
			<style:table-properties table:border-model="collapsing" />
		</style:default-style>
		<style:default-style style:family="table-row">
			<style:table-row-properties fo:keep-together="auto" />
		</style:default-style>
		<style:style style:name="Standard" style:family="paragraph"
			style:class="text" />
		<style:style style:name="Heading" style:family="paragraph"
			style:parent-style-name="Standard"
			style:next-style-name="Text_20_body" style:class="text">
			<style:paragraph-properties fo:margin-top="0.423cm"
				fo:margin-bottom="0.212cm" fo:keep-with-next="always" />
			<style:text-properties style:font-name="Arial"
				fo:font-size="14pt" style:font-name-asian="Arial Unicode MS"
				style:font-size-asian="14pt" style:font-name-complex="Tahoma"
				style:font-size-complex="14pt" />
		</style:style>
		<style:style style:name="Text_20_body"
			style:display-name="Text body" style:family="paragraph"
			style:parent-style-name="Standard" style:class="text">
			<style:paragraph-properties fo:margin-top="0cm"
				fo:margin-bottom="0.212cm" />
		</style:style>
		<style:style style:name="List" style:family="paragraph"
			style:parent-style-name="Text_20_body" style:class="list">
			<style:text-properties style:font-name-complex="Tahoma1" />
		</style:style>
		<style:style style:name="Caption" style:family="paragraph"
			style:parent-style-name="Standard" style:class="extra">
			<style:paragraph-properties fo:margin-top="0.212cm"
				fo:margin-bottom="0.212cm" text:number-lines="false"
				text:line-number="0" />
			<style:text-properties fo:font-size="12pt"
				fo:font-style="italic" style:font-size-asian="12pt"
				style:font-style-asian="italic" style:font-name-complex="Tahoma1"
				style:font-size-complex="12pt" style:font-style-complex="italic" />
		</style:style>
		<style:style style:name="Index" style:family="paragraph"
			style:parent-style-name="Standard" style:class="index">
			<style:paragraph-properties text:number-lines="false"
				text:line-number="0" />
			<style:text-properties style:font-name-complex="Tahoma1" />
		</style:style>
		<style:style style:name="Heading_20_1"
			style:display-name="Heading 1" style:family="paragraph"
			style:parent-style-name="Heading"
			style:next-style-name="Text_20_body" style:class="text"
			style:default-outline-level="1">
			<style:text-properties fo:font-size="115%"
				fo:font-weight="bold" style:font-size-asian="115%"
				style:font-weight-asian="bold" style:font-size-complex="115%"
				style:font-weight-complex="bold" />
		</style:style>
		<style:style style:name="Heading_20_2"
			style:display-name="Heading 2" style:family="paragraph"
			style:parent-style-name="Heading"
			style:next-style-name="Text_20_body" style:class="text"
			style:default-outline-level="2">
			<style:text-properties fo:font-size="14pt"
				fo:font-style="italic" fo:font-weight="bold"
				style:font-size-asian="14pt" style:font-style-asian="italic"
				style:font-weight-asian="bold" style:font-size-complex="14pt"
				style:font-style-complex="italic" style:font-weight-complex="bold" />
		</style:style>
		<style:style style:name="Heading_20_3"
			style:display-name="Heading 3" style:family="paragraph"
			style:parent-style-name="Heading"
			style:next-style-name="Text_20_body" style:class="text"
			style:default-outline-level="3">
			<style:text-properties fo:font-size="14pt"
				fo:font-weight="bold" style:font-size-asian="14pt"
				style:font-weight-asian="bold" style:font-size-complex="14pt"
				style:font-weight-complex="bold" />
		</style:style>
		<style:style style:name="Heading_20_4"
			style:display-name="Heading 4" style:family="paragraph"
			style:parent-style-name="Heading"
			style:next-style-name="Text_20_body" style:class="text"
			style:default-outline-level="4">
			<style:text-properties fo:font-size="85%"
				fo:font-style="italic" fo:font-weight="bold"
				style:font-size-asian="85%" style:font-style-asian="italic"
				style:font-weight-asian="bold" style:font-size-complex="85%"
				style:font-style-complex="italic" style:font-weight-complex="bold" />
		</style:style>
		<style:style style:name="Heading_20_5"
			style:display-name="Heading 5" style:family="paragraph"
			style:parent-style-name="Heading"
			style:next-style-name="Text_20_body" style:class="text"
			style:default-outline-level="5">
			<style:text-properties fo:font-size="85%"
				fo:font-weight="bold" style:font-size-asian="85%"
				style:font-weight-asian="bold" style:font-size-complex="85%"
				style:font-weight-complex="bold" />
		</style:style>
		<style:style style:name="Heading_20_6"
			style:display-name="Heading 6" style:family="paragraph"
			style:parent-style-name="Heading"
			style:next-style-name="Text_20_body" style:class="text"
			style:default-outline-level="6">
			<style:text-properties fo:font-size="75%"
				fo:font-weight="bold" style:font-size-asian="75%"
				style:font-weight-asian="bold" style:font-size-complex="75%"
				style:font-weight-complex="bold" />
		</style:style>
		<style:style style:name="Heading_20_7"
			style:display-name="Heading 7" style:family="paragraph"
			style:parent-style-name="Heading"
			style:next-style-name="Text_20_body" style:class="text"
			style:default-outline-level="7">
			<style:text-properties fo:font-size="75%"
				fo:font-weight="bold" style:font-size-asian="75%"
				style:font-weight-asian="bold" style:font-size-complex="75%"
				style:font-weight-complex="bold" />
		</style:style>
		<style:style style:name="Heading_20_8"
			style:display-name="Heading 8" style:family="paragraph"
			style:parent-style-name="Heading"
			style:next-style-name="Text_20_body" style:class="text"
			style:default-outline-level="8">
			<style:text-properties fo:font-size="75%"
				fo:font-weight="bold" style:font-size-asian="75%"
				style:font-weight-asian="bold" style:font-size-complex="75%"
				style:font-weight-complex="bold" />
		</style:style>
		<style:style style:name="Heading_20_9"
			style:display-name="Heading 9" style:family="paragraph"
			style:parent-style-name="Heading"
			style:next-style-name="Text_20_body" style:class="text"
			style:default-outline-level="9">
			<style:text-properties fo:font-size="75%"
				fo:font-weight="bold" style:font-size-asian="75%"
				style:font-weight-asian="bold" style:font-size-complex="75%"
				style:font-weight-complex="bold" />
		</style:style>
		<style:style style:name="Heading_20_10"
			style:display-name="Heading 10" style:family="paragraph"
			style:parent-style-name="Heading"
			style:next-style-name="Text_20_body" style:class="text"
			style:default-outline-level="10">
			<style:text-properties fo:font-size="75%"
				fo:font-weight="bold" style:font-size-asian="75%"
				style:font-weight-asian="bold" style:font-size-complex="75%"
				style:font-weight-complex="bold" />
		</style:style>
				<style:style style:name="Table_20_Contents"
			style:display-name="Table Contents" style:family="paragraph"
			style:parent-style-name="Standard" style:class="extra">
			<style:paragraph-properties text:number-lines="false"
				text:line-number="0" />
		</style:style>
		<style:style style:name="Table_20_Heading"
			style:display-name="Table Heading" style:family="paragraph"
			style:parent-style-name="Table_20_Contents" style:class="extra">
			<style:paragraph-properties fo:text-align="center"
				style:justify-single-word="false" text:number-lines="false"
				text:line-number="0" />
			<style:text-properties fo:font-weight="bold"
				style:font-weight-asian="bold" style:font-weight-complex="bold" />
		</style:style>
		<text:outline-style style:name="Outline">
			<text:outline-level-style text:level="1"
				style:num-format="">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="0.762cm" fo:text-indent="-0.762cm"
						fo:margin-left="0.762cm" />
				</style:list-level-properties>
			</text:outline-level-style>
			<text:outline-level-style text:level="2"
				style:num-format="">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="1.016cm" fo:text-indent="-1.016cm"
						fo:margin-left="1.016cm" />
				</style:list-level-properties>
			</text:outline-level-style>
			<text:outline-level-style text:level="3"
				style:num-format="">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="1.27cm" fo:text-indent="-1.27cm"
						fo:margin-left="1.27cm" />
				</style:list-level-properties>
			</text:outline-level-style>
			<text:outline-level-style text:level="4"
				style:num-format="">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="1.524cm" fo:text-indent="-1.524cm"
						fo:margin-left="1.524cm" />
				</style:list-level-properties>
			</text:outline-level-style>
			<text:outline-level-style text:level="5"
				style:num-format="">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="1.778cm" fo:text-indent="-1.778cm"
						fo:margin-left="1.778cm" />
				</style:list-level-properties>
			</text:outline-level-style>
			<text:outline-level-style text:level="6"
				style:num-format="">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="2.032cm" fo:text-indent="-2.032cm"
						fo:margin-left="2.032cm" />
				</style:list-level-properties>
			</text:outline-level-style>
			<text:outline-level-style text:level="7"
				style:num-format="">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="2.286cm" fo:text-indent="-2.286cm"
						fo:margin-left="2.286cm" />
				</style:list-level-properties>
			</text:outline-level-style>
			<text:outline-level-style text:level="8"
				style:num-format="">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="2.54cm" fo:text-indent="-2.54cm"
						fo:margin-left="2.54cm" />
				</style:list-level-properties>
			</text:outline-level-style>
			<text:outline-level-style text:level="9"
				style:num-format="">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="2.794cm" fo:text-indent="-2.794cm"
						fo:margin-left="2.794cm" />
				</style:list-level-properties>
			</text:outline-level-style>
			<text:outline-level-style text:level="10"
				style:num-format="">
				<style:list-level-properties
					text:list-level-position-and-space-mode="label-alignment">
					<style:list-level-label-alignment
						text:label-followed-by="listtab"
						text:list-tab-stop-position="3.048cm" fo:text-indent="-3.048cm"
						fo:margin-left="3.048cm" />
				</style:list-level-properties>
			</text:outline-level-style>
		</text:outline-style>
		<text:notes-configuration text:note-class="footnote"
			style:num-format="1" text:start-value="0"
			text:footnotes-position="page" text:start-numbering-at="document" />
		<text:notes-configuration text:note-class="endnote"
			style:num-format="i" text:start-value="0" />
		<text:linenumbering-configuration text:number-lines="false"
			text:offset="0.499cm" style:num-format="1"
			text:number-position="left" text:increment="5" />
	</office:styles>
	<office:automatic-styles>
		<style:page-layout style:name="Mpm1">
			<style:page-layout-properties fo:page-width="20.999cm"
				fo:page-height="29.699cm" style:num-format="1"
				style:print-orientation="portrait" fo:margin-top="2cm"
				fo:margin-bottom="2cm" fo:margin-left="2cm" fo:margin-right="2cm"
				style:writing-mode="lr-tb" style:footnote-max-height="0cm">
				<style:footnote-sep style:width="0.018cm"
					style:distance-before-sep="0.101cm"
					style:distance-after-sep="0.101cm" style:adjustment="left"
					style:rel-width="25%" style:color="#000000" />
			</style:page-layout-properties>
			<style:header-style />
			<style:footer-style />
		</style:page-layout>
	</office:automatic-styles>
	<office:master-styles>
		<style:master-page style:name="Standard"
			style:page-layout-name="Mpm1" />
	</office:master-styles>
</office:document-styles>
