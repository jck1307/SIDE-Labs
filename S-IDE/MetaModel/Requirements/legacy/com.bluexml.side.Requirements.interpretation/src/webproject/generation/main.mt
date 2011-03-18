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
metamodel http://www.bluexml.com/rwm/webproject/1.0/
%>
<%script type="WebProject.LoginPage" name="loginPage" file="<%eContainer().name%>/<%name%>"%>
<html>
	<head>
		<title><%title%></title>
		<link rel="stylesheet" type="text/css" href="css/main.css">
	</head>
	<body>
		<div id="projectTitle">
			<h1><%eContainer().name%></h1>
		</div>
		<div id="users">
			<h1><%title%></h1>
			<ul>
			<%for (links.nSort("label")){%>
				<li/><a href="frame_<%page.name%>"><%label%></a>
			<%}%>
			</ul>
		</div>
	</body>
</html>
<%script type="WebProject.FramePage" name="goalPage_frame" file="<%eContainer().name%>/<%name%>"%>
<frameset cols="20%,80%" frameborder="no">
	<frame src="<%col1.name%>" name="goalItems">
	<frame name="data">
</frameset>
<%script type="WebProject.GoalPage" name="goalPage_list" file="<%eContainer().name%>/<%name%>"%>
<html>
	<head>
		<title><%title%></title>
		<link rel="stylesheet" type="text/css" href="css/main.css">
	</head>
	<body>
		<div id="goalItems">
			<h1><%title%></h1>
			<%for (items){%>
			 	<%goalPage_goalItem%>
			<%}%>
		</div>
		<br/>
		<div id="logout">
			<a href="<%eContainer().pages[eClass().name.equalsIgnoreCase("LoginPage")].name%>" target="_parent">Logout</a>
		</div>
	</body>
</html>
<%script type="WebProject.GoalItem" name="goalPage_goalItem"%>
<ul>
	<li/><a href="<%page.name%>" target="data"><%label%></a>
	<%for (sub){%>
		<%goalPage_goalItem%>
	<%}%>
</ul>
<%script type="WebProject.DataPage" name="dataPage" file="<%eContainer().name%>/<%name%>"%>
<html>
	<head>
		<title><%title%></title>
		<link rel="stylesheet" type="text/css" href="css/main.css">
	</head>
	<body>
		<? 
			require("./mysql/mysql_util.php");
			
			function appendParameter($url, $paramName, $paramValue) {
				$tmp = removeParameter($url,$paramName);
				$separator = "?";
				if (strpos($tmp,"?")!=false)
  					$separator = "&";
 				return $tmp . $separator . "$paramName=$paramValue";
			}
			
			function removeParameter($url, $paramName) {
 				$tmp = ereg_replace("$paramName=([a-zA-Z0-9])*","",$url);
 				$tmp = ereg_replace("\&\&","&",$tmp);
 				$tmp = ereg_replace("\?\&","?",$tmp);
 				return $tmp;
			}
		?>
		<div id="data">
			<h1><%title%></h1>
			<%for (components){%>
				<%if (current("DataPage").mainComponent == current()){%>
					<%dataPage_component%>
				<%}else{%>
					<? if (isset($_GET['view<%table.name%>'])) { ?>
					<%dataPage_component%>
					<? } ?>
				<%}%>
			<%}%>
		</div>
	</body>
</html>
<%script type="WebProject.Component" name="dataPage_component"%>
<div id="component">
	<div class="component_title">
	<h2><%name%></h2>
	</div>
	<%if (canRead){%>
		<%dataPage_component_read%>
	<%}%>
	<%if (canCreate){%>
		<div class="component_create">
		<a href="edit_<%table.name%>_<%eContainer().name%>?view<%table.name%>=1&<%table.name%>=1">Create a new <i><%name%></i></a>
		</div>
	<%}%>
</div>
<%script type="WebProject.Component" name="dataPage_component_read"%>
	<table>
		<tr>
		<%for (properties[canRead == true]) {%>
			<%if (	eClass().name.equalsIgnoreCase("ComponentAttribute") ||
					!(current("Component").precedingSibling().table.nContains(cast("ComponentRelationShip").idRight.eContainer())
					|| current("Component").precedingSibling().table.nContains(cast("ComponentRelationShip").idLeft.eContainer()))
				){%>
			<th><%name%></th>
			<%}%>
		<%}%>
		<%if (canUpdate){%>
			<th>Edit</th>
		<%}%>
		<%if (canDelete){%>
			<th>Delete</th>
		<%}%>
		</tr>
		<?
			<%if (current("Component").precedingSibling().nSize() > 0){%>
				$nbPreviousTable = "<%current("Component").precedingSibling().nLast().table.name%>";
				$pkPreviousTable = "<%current("Component").precedingSibling().nLast().table.primaryKey.name%>";
			<%}else{%>
				$nbPreviousTable = "<%current("Component").table.name%>";
			<%}%>
			
			<%dataPage_component_sqlQuery%>
			mysql_connect($host, $user, $password);
			mysql_select_db($db);
			
			$result = mysql_query($sql);
			while ($data = mysql_fetch_array($result) )
			{
				$tableName = "<%table.name%>";
				if (isset($_GET["id".$tableName]) && $_GET["id".$tableName]==$data["<%table.primaryKey.name%>"]) {
					echo "<tr class=\"line_selected\">\n";
				} else {
					echo "<tr>\n";
				};
		?>
				<%for (properties[canRead]){%>
					<%if (eClass().name.equalsIgnoreCase("ComponentAttribute")){%>
						<td><? echo $data['<%cast("ComponentAttribute").field.name%>']; ?></td>
					<%}else{%>
						<%if (!(current("Component").precedingSibling().table.nContains(cast("ComponentRelationShip").idRight.eContainer())
							|| current("Component").precedingSibling().table.nContains(cast("ComponentRelationShip").idLeft.eContainer()))
							){%>
						
						<td>
							<?
								<%if (current("Component").table == cast("ComponentRelationShip").idLeft.eContainer()){%>
								$idSource = "<%current("ComponentRelationShip").idLeft.name%>";
								$idTarget = "<%current("ComponentRelationShip").idRight.name%>";
								<%}else{%>
								$idSource = "<%current("ComponentRelationShip").idRight.name%>";
								$idTarget = "<%current("ComponentRelationShip").idLeft.name%>";
								<%}%>
							
								$sql = "SELECT COUNT(*) as nb FROM "
									." <%cast("ComponentRelationShip").idLeft.eContainer().name%>2<%cast("ComponentRelationShip").idRight.eContainer().name%>"
									." WHERE $idSource = " . $data[$idSource]
									.";";
								
								$result2 = mysql_query($sql);
								$data2 = mysql_fetch_array($result2);
								$nb = $data2['nb'];
								
								$url = $_SERVER['REQUEST_URI'];
								
								<%for (current("Component").followingSibling()){%>
								$url = removeParameter($url,"view<%table.name%>");
								$url = removeParameter($url,"id<%table.name%>");
								<%}%>

								<%if (current("Component").table == cast("ComponentRelationShip").idLeft.eContainer()){%>
								$name = "view<%current("ComponentRelationShip").idRight.eContainer().name%>";
								<%}else{%>
								$name = "view<%current("ComponentRelationShip").idLeft.eContainer().name%>";
								<%}%>
								$value = "1";
								
								$url = appendParameter($url,$name,$value);
								
 								$name = "id<%current("Component").table.name%>";
 								$value = $data[$idSource];

								$url = appendParameter($url,$name,$value);
								
							?>
							
							<? if ($nb>0) {?> 
							<a href="<? echo $url; ?>">See <? echo $nb; ?> element(s).</a>
							<? } else { ?>
							None
							<? } ?>
						</td>

						<%}%>
					<%}%>
				<%}%>
				<%if (canUpdate){%>
				<td><a href="edit_<%table.name%>_<%eContainer().name%>?<%table.name%>=1&idObject=<? echo $data['<%table.primaryKey.name%>']; ?>">Edit</a></td>
				<%}%>
				<%if (canDelete){%>
				<td><a href="<%eContainer().name%>?view<%table.name%>=1&deleteObject=1&table_name=<%table.name%>&field_name=<%table.primaryKey.name%>&idObject=<? echo $data['<%table.primaryKey.name%>']; ?>">Delete</a></td>
				<%}%>
			</tr>
		<?			
			}
			mysql_close();
		?>
	</table>
<%script type="WebProject.Component" name="dataPage_component_sqlQuery"%>
if (isset($pkPreviousTable)) {
	$sql = "SELECT "
		." <%properties[eClass().name.equalsIgnoreCase("ComponentAttribute") && canRead].cast("ComponentAttribute").field.name.sep(",")%>"
		<%if (properties[eClass().name.equalsIgnoreCase("ComponentAttribute") && canRead].nSize()> 0){%>
		.","
		<%}%>
		." <%table.primaryKey.name.split(",")%>"
		." FROM <%table.name%> as s"
		." WHERE ("
		."     SELECT COUNT(*)"
		<%for properties[eClass().name.equalsIgnoreCase("ComponentRelationShip")] {%>
			<%if (cast("ComponentRelationShip").idLeft.eContainer() == current("Component").table
				&& cast("ComponentRelationShip").idRight.eContainer() == current("Component").precedingSibling().nLast().table
				){%>
		."     FROM <%cast("ComponentRelationShip").idLeft.eContainer().name%>2<%cast("ComponentRelationShip").idRight.eContainer().name%> as t"
			<%}%>
			<%if (cast("ComponentRelationShip").idRight.eContainer() == current("Component").table
				&& cast("ComponentRelationShip").idLeft.eContainer() == current("Component").precedingSibling().nLast().table
				){%>
		."     FROM <%cast("ComponentRelationShip").idLeft.eContainer().name%>2<%cast("ComponentRelationShip").idRight.eContainer().name%> as t"
			<%}%>
		<%}%>
		."     WHERE t.<%table.primaryKey.name%> = s.<%table.primaryKey.name%>"
		."     AND t.$pkPreviousTable = \"".$_GET["id$nbPreviousTable"]."\") > 0;";
} else {
	$sql = "SELECT "
		." <%properties[eClass().name.equalsIgnoreCase("ComponentAttribute") && canRead].cast("ComponentAttribute").field.name.sep(",")%>"
		<%if (properties[eClass().name.equalsIgnoreCase("ComponentAttribute") && canRead].nSize()> 0){%>
		.","
		<%}%>
		." <%table.primaryKey.name.split(",")%>"
	    ." FROM <%table.name%>;";
}
