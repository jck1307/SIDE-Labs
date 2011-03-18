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

<%script type="Documentation.Section" name="content_chapter"%>
<!-- Break line -->
<text:p text:style-name="Text_20_body"/><text:p text:style-name="P1"/>
<text:h text:style-name="Heading_20_1" text:outline-level="1"><text:bookmark text:name="<%title%>" />
<%title%></text:h>
<%for (para){%>
	<%content_paragraph%>
<%}%>
<%for (section){%>
	<%content_section(1)%>
<%}%>
<%script type="Documentation.Section" name="content_section"%>
<%-- args[0] : level of parent section --%>
<text:h text:style-name="Heading_20_<%args(0)+1%>" text:outline-level="<%args(0)+1%>">
	<text:bookmark text:name="<%title%>" /><%title%>
</text:h>
<%for (para){%>
	<%content_paragraph%>
<%}%>
<%for (section){%>
	<%content_section(args(0)+1)%>
<%}%>
<%script type="Documentation.Paragraph" name="content_paragraph"%>
<text:p text:style-name="Text_20_body">
<%for (values){%>
	<%if (filter("TextualValue") != null){%>
		<%value%>
	<%}%>
	<%if (filter("EmphasisValue") != null){%>
		<text:span text:style-name="T1"><%value%></text:span>
	<%}%>
	<%if (filter("ItemizedListValue") != null){%>
		</text:p>
		<text:list text:style-name="L1">
			<%for (filter("ItemizedListValue").items){%>
			<text:list-item>
				<%content_paragraph%>
			</text:list-item>
			<%}%>
		</text:list>
		<text:p text:style-name="Text_20_body">
	<%}%>
	<%if (filter("XRefValue") != null){%>
		<text:a xlink:type="simple" xlink:href="#<%filter("XRefValue").linkend.title%>"><%filter("XRefValue").linkend.title%></text:a>
	<%}%>
	<%if (filter("InformalTableValue") != null){%>
		</text:p>
		<%filter("InformalTableValue").content_table%>
		<text:p text:style-name="Text_20_body">
	<%}%>
<%}%>
</text:p>
<%script type="Documentation.InformalTableValue" name="content_table"%>
<table:table table:style-name="Tableau1">
	<table:table-column table:style-name="Tableau1.A" table:number-columns-repeated="<%tgroup.cols%>" />
	<%for (tgroup.thead.rows){%>
		<table:table-row>
			 <%for (entry){%>
			 	<%if (current() == nLast()){%>
				<table:table-cell table:style-name="Tableau1.D1" office:value-type="string">
			 	<%}else{%>
				<table:table-cell table:style-name="Tableau1.A1" office:value-type="string">
			 	<%}%>
					<text:p text:style-name="Table_20_Heading"><%value%></text:p>
				</table:table-cell>
			 <%}%>
		</table:table-row>
	<%}%>
	<%for (tgroup.tbody.rows){%>
		<table:table-row>
			 <%for (entry){%>
			 	<%if (current() == nLast()){%>
				<table:table-cell table:style-name="Tableau1.D2" office:value-type="string">
			 	<%}else{%>
				<table:table-cell table:style-name="Tableau1.A2" office:value-type="string">
			 	<%}%>
					<text:p text:style-name="Table_20_Contents"><%value%></text:p>
				</table:table-cell>
			 <%}%>
		</table:table-row>
	<%}%>
</table:table>
