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
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.nyroModal-1.5.5.pack.js"></script>
		<link rel="stylesheet" href="styles/nyroModal.css" type="text/css" media="screen" />
	</head>
	<body class="body">
		<div class="box">
			<h1><%title%></h1>
		</div>
		<br/>
		<div class="box">
			<div class="user">
			<%for (links.nSort("label")){%>
				<%if (!page.title.startsWith("Process : ")){%><li/><a href="frame_<%page.name%>"><%label%></a><%}%>
			<%}%>
			</div>
		</div>
		<br/>
		<div class="box">
			<div class="process">
			<%for (links.nSort("label")){%>
				<%if (page.title.startsWith("Process : ")){%><li/><a href="frame_<%page.name%>"><%label%></a><%}%>
			<%}%>
			</div>
		</div>
		<br/>		
		<div class="box">
			<span style="font-weight:bold;">Description :</span><br/>
				<%comment.nFirst()%>
			</div>
		</div>
		<br/>
		<div id="logout" class="box">
			<img src="images/addcomment.png"/><a href="addComment.php?elementId=<%id%>" class="nyroModal">Add a comment</a>
			--
			<img src="images/comment.png"/><a href="listComment.php?elementId=<%id%>" class="nyroModal">List all comments</a>
		</div>
	</body>
</html>
<%script type="WebProject.FramePage" name="goalPage_frame" file="<%eContainer().name%>/<%name%>"%>
<frameset cols="20%,80%" frameborder="no">
	<frame src="<%col1.name%>" name="goalItems">
	<frame src="blank.html" name="data">
</frameset>
<%script type="WebProject.GoalPage" name="goalPage_list" file="<%eContainer().name%>/<%name%>"%>
<html>
	<head>
		<title><%title%></title>
		<link rel="stylesheet" type="text/css" href="css/main.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.nyroModal-1.5.5.pack.js"></script>
		<link rel="stylesheet" href="styles/nyroModal.css" type="text/css" media="screen" />
	</head>
	<body class="body">
		<div <%if (comment==null){%>id="goalItems" <%}%>class="box">
			<h2><%title%></h2>
			<hr/>
			<ul>
			<%for (items){%>
			 	<%goalPage_goalItem%>
			<%}%>
			</ul>
		</div>
		<br/>
		<%if (comment != null) {%>
			<div class="box">
				<span style="font-weight:bold;">Description :</span><br/>
				<%comment.nFirst()%>
			</div>
			<br/>
			<div class="box">
				<span style="font-weight:bold;">Synopsis :</span><br/>
				<%comment.nLast()%>
			</div>
			<br/>
		<%}%>
		<div id="logout" class="box">
			<img src="images/addcomment.png"/><a href="addComment.php?elementId=<%id%>" class="nyroModal">Add a comment</a>
			<br/>
			<img src="images/comment.png"/><a href="listComment.php?elementId=<%id%>" class="nyroModal">List all comments</a>
		</div>
		<br/>
		<div id="logout" class="box">
			<img src="images/logout.png"/><a href="<%eContainer().pages[eClass().name.equalsIgnoreCase("LoginPage")].name%>" target="_parent">Logout</a>
		</div>
	</body>
</html>
<%script type="WebProject.GoalItem" name="goalPage_goalItem"%>
	<li/><a href="<%page.name%>" target="data"><%label%></a>
	<%if (sub.nSize() > 0){%><ul><%}%>
	<%for (sub){%>
		<%goalPage_goalItem%>
	<%}%>
	<%if (sub.nSize() > 0){%></ul><%}%>
<%script type="WebProject.DataPage" name="dataPage" file="<%eContainer().name%>/<%name%>"%>
<html>
	<head>
		<title><%title%></title>
		<link rel="stylesheet" type="text/css" href="css/main.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.nyroModal-1.5.5.pack.js"></script>
		<link rel="stylesheet" href="styles/nyroModal.css" type="text/css" media="screen" />
	</head>
	<body class="body"> 
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
		<div id="data" class="box">
			<h2><%title%></h2>
			<div class="box">
				<span style="font-weight:bold;">Description :</span><br/>
				<%comment.nFirst()%>
			</div>
			<br/>
			<div class="box">
				<span style="font-weight:bold;">Synopsis :</span><br/>
				<%comment.nLast()%>
			</div>
			<br/>
			<div id="logout" class="box">
				<img src="images/addcomment.png"/><a href="addComment.php?elementId=<%id%>" class="nyroModal">Add a comment</a>
				--
				<img src="images/comment.png"/><a href="listComment.php?elementId=<%id%>" class="nyroModal">List all comments</a>
			</div>
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
	<h3><%name%></h3>
	</div>
	<%if (canRead){%>
		<%dataPage_component_read%>
	<%}%>
	<%if (canCreate){%>
		<table class="list-table">
		<tr><th><a href="edit_<%table.name%>_<%eContainer().name%>?view<%table.name%>=1&<%table.name%>=1"><img src="images/create.png"/>Create a new <i><%name%></i></a></th></tr>
		</table>
	<%}%>
</div>
<%script type="WebProject.Component" name="dataPage_component_read"%>
	<table class="list-table">
		<tr>
		<%for (properties[canRead == true]) {%>
			<%if (	eClass().name.equalsIgnoreCase("ComponentAttribute") ||
					!(current("Component").precedingSibling().table.nContains(cast("ComponentRelationShip").idRight.eContainer())
					|| current("Component").precedingSibling().table.nContains(cast("ComponentRelationShip").idLeft.eContainer()))
				){%>
			<th><%name%></th>
			<%}%>
		<%}%>
			<th>Actions</th>
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
									." WHERE \"$idSource\" = " . $data[$idSource]
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
								
								$joinTable = "<%current("ComponentRelationShip").idLeft.eContainer().name%>2<%current("ComponentRelationShip").idRight.eContainer().name%>";
								
								$url = appendParameter($url,$name,$value);
								$url = appendParameter($url,"joinTable",$joinTable);
								
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
				<td class="action_col">
				<%if (canUpdate){%>
				<a href="edit_<%table.name%>_<%eContainer().name%>?<%table.name%>=1&idObject=<? echo $data['<%table.primaryKey.name%>']; ?>"><img src="images/edit.png"/></a>
				<%}%>
				<%if (canDelete){%>
				<a href="<%eContainer().name%>?view<%table.name%>=1&deleteObject=1&table_name=<%table.name%>&field_name=<%table.primaryKey.name%>&idObject=<? echo $data['<%table.primaryKey.name%>']; ?>"><img src="images/delete.png"/></a>
				<%}%>
				</td>
			</tr>
		<?			
			}
			mysql_close();
		?>
	</table>
<%script type="WebProject.Component" name="dataPage_component_sqlQuery"%>
if (isset($pkPreviousTable)) {
	$sql = "SELECT "
		<%if (properties[eClass().name.equalsIgnoreCase("ComponentAttribute") && canRead].nSize()> 0){%>
		." `"
		<%}%>
		."<%properties[eClass().name.equalsIgnoreCase("ComponentAttribute") && canRead].cast("ComponentAttribute").field.name.sep("`,`")%>"
		<%if (properties[eClass().name.equalsIgnoreCase("ComponentAttribute") && canRead].nSize()> 0){%>
		."`,"
		<%}%>
		." `<%table.primaryKey.name.split("`,`")%>`"
		." FROM <%table.name%> as s"
		." WHERE ("
		."     SELECT COUNT(*)"
		."     FROM ".$_GET["joinTable"]." t"
		."     WHERE t.`<%table.primaryKey.name%>` = s.`<%table.primaryKey.name%>`"
		."     AND t.$pkPreviousTable = \"".$_GET["id$nbPreviousTable"]."\") > 0;";
} else {
	$sql = "SELECT "
		<%if (properties[eClass().name.equalsIgnoreCase("ComponentAttribute") && canRead].nSize()> 0){%>
		." `"
		<%}%>
		."<%properties[eClass().name.equalsIgnoreCase("ComponentAttribute") && canRead].cast("ComponentAttribute").field.name.sep("`,`")%>"
		<%if (properties[eClass().name.equalsIgnoreCase("ComponentAttribute") && canRead].nSize()> 0){%>
		."`,"
		<%}%>
		." `<%table.primaryKey.name.split("`,`")%>`"
	    ." FROM <%table.name%>;";
}
