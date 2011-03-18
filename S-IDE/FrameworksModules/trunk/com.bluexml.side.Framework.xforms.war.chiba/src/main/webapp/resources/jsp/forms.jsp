<html>
<!--Copyright 2001-2007 ChibaXForms GmbH -->
<head>
<!--<title>Browse and view forms generated by the BlueXML XForms</title>-->
<title>BlueXML XForms</title>
<link rel="stylesheet" type="text/css" href="../styles/chiba-styles.css" />
<link rel="stylesheet" type="text/css"
	href="../styles/xforms.generated.css" />
<style type="text/css">
td {
	padding: 2px;
}
</style>

</head>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="org.apache.commons.logging.Log"%>
<%@ page import="org.apache.commons.logging.LogFactory"%>
<%@ page import="org.w3c.dom.Document"%>
<%@ page import="org.w3c.dom.Element"%>
<%@ page import="org.w3c.dom.Node"%>
<%@ page import="org.w3c.dom.NodeList"%>
<%@ page import="com.bluexml.side.form.utils.DOMUtil"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page session="true"%>

<body text="black" link="black" vlink="black" alink="orange">

<table width="100%" height="85%" cellpadding="10" cellspacing="0"
	border="0">
	<tr>

		<td valign="top"><%!static Log cat = LogFactory.getLog("org.chiba.agent.web.jsp");

	String chibaRoot = null;
	String rootDir = null;
	
	private class DirDescrBean {
		// Any of these fields may be null
		public String label;
		public String description;
		
		public DirDescrBean(String label, String description) {
			this.label = label;
			this.description = description;
		}
	}
	
	public String getTextContent(Element elt) {
		StringBuffer buffer = new StringBuffer();
		NodeList childList = elt.getChildNodes();
		for (int i = 0; i < childList.getLength(); i++) {
			Node child = childList.item(i);
			if (child.getNodeType() == Node.TEXT_NODE) {
				buffer.append(child.getNodeValue());
			}
		}

		return buffer.toString();
	}

	/**
	 * Never returns null.
	 */
	 
	public DirDescrBean getDirectoryDescrBean(String descrFilePath, String initialLabel) throws IOException {
		String resultLabel = initialLabel;
		Document doc = null;
		InputStream is;
		try {
			is = new FileInputStream(descrFilePath);
		} catch (Exception e) {
			return new DirDescrBean(initialLabel, null);
		}

		try {
			doc = DOMUtil.getDocumentFromStream(is);
		} catch (Exception e) {
			return new DirDescrBean(initialLabel, null);
		} finally {
			is.close();
		}

		Element labelElt = DOMUtil.getChild(doc, "label");
		if (labelElt != null) {
			resultLabel = StringUtils.trim(getTextContent(labelElt));
		}
		Element descriptionElt = DOMUtil.getChild(doc, "description");
		String descr = StringUtils.trimToNull(getTextContent(descriptionElt));
		return new DirDescrBean(resultLabel, descr);
	}

	public void jspInit() {

		// +++ read general parameters from web.xml
		chibaRoot = getServletConfig().getServletContext().getRealPath("");
		if (chibaRoot == null) {
			chibaRoot = getServletConfig().getServletContext().getRealPath(".");
		}
		rootDir = chibaRoot + "/";
	}%> <%
 	String uri = request.getQueryString();
 	String readDir = null;

 	if (uri == null) {
 		uri = "forms";
 	}
 	readDir = rootDir + uri;
 	cat.debug("URI: " + uri);
 	cat.debug("Read dir: " + readDir);
 %>
		<table width="100%" border="0" cellpadding="0" cellspacing="5">
			<tr>
				<td>Path: /<%=uri%></td>
			</tr>
		</table>

		<table style="border: thin solid orange;" cellspacing="1"
			cellpadding="2" width="100%">

			<%-- En-t�te --%>
			<tr bgcolor="#faeeaa">
				<td align="left" width="10%">File</td>
				<td align="center" width="5%"><span style="color: darkred">non-scripted
				HTML</span></td>

				<%--
		<td width="5%" align="center">
			scripted HTML
		</td>
				--%>

				<%--
        <td>
            Source
        </td>
--%>
				<td>last update</td>
			</tr>
			<%
				//list files from documents directory
				File root = new File(readDir);
				if (!root.exists()) {
					root.mkdirs();
				}
				String[] files = root.list();
				cat.debug("files: " + files.length);
				File f = null;
				String up = null;
				if (files != null) {
					java.util.Arrays.sort(files);
					if (uri.indexOf("/") != -1) {
						up = uri.substring(0, uri.lastIndexOf("/"));
			%>
			<tr bgcolor="#FCF6D3" style="border: thin solid orange;">
				<td valign="middle" colspan="5"><a href="forms.jsp?<%=up%>">
				<img
					src="<%=request.getContextPath()%>/resources/images/folder.gif"
					border="0" width="20" height="20" align="left">.. </a></td>
			</tr>
			<%
				}

					DirDescrBean dirBean;
					String dirDescr = null;
					for (int i = 0; i < files.length; i++) {
						File aFile = new File(files[i]);
						f = new File(readDir + "/" + aFile.getName());
						if (f.isDirectory()) {
							dirBean = getDirectoryDescrBean(f.getPath() + "/descr.xml", aFile.getName());
			%>
			<tr bgcolor="#FCF6D3">
				<td valign="middle" colspan="5"><a
					href="<%=request.getContextPath()%>/resources/jsp/forms.jsp?<%=uri%>/<%=aFile.getName()%>">
				<img src="<%=request.getContextPath()%>/resources/images/folder.gif"
					border="0" width="20" height="20" align="left"><%=dirBean.label%>
					<%
						if (dirBean.description != null) {
					%>
					(<%=dirBean.description%>)
					<%
						}
					%>
				</a></td>

			</tr>
			<%
				}
					}
				}
				
				//root = new File(readDir);
				//files = root.list();
				//cat.debug("files: " + files.length);

				if (files != null) {
					//java.util.Arrays.sort(files);
					for (int i = 0; i < files.length; i++) {
						File aFile = new File(files[i]);
						f = new File(readDir + "/" + aFile.getName());
						if (!(f.isDirectory()) && !(aFile.getName().equals("descr.xml"))) {
			%>
			<tr bgcolor="#FCF6D3">

				<td>
				<%
					String formType = "";
					String rootPath = root.getPath();
					if (aFile.getName().endsWith(".xhtml")) {
						String className = aFile.getName().replace(".xhtml", "");
									if (rootPath.endsWith("/forms/forms")
											|| rootPath.endsWith("\\forms\\forms")) {
							formType = "&formType=form";
						}
									if (rootPath.endsWith("/forms/lists")
											|| rootPath.endsWith("\\forms\\lists")) {
							formType = "&formType=list";
						}
						if (rootPath.endsWith("/forms/selectors") || rootPath.endsWith("\\forms\\selectors")) {
							formType = "&formType=selector";
						}
						if (rootPath.endsWith("/forms/search") || rootPath.endsWith("\\forms\\search")) {
							formType = "&formType=search";
						}
						if (rootPath.endsWith("/forms/workflows") || rootPath.endsWith("\\forms\\workflows")) {
							if (aFile.getName().endsWith("ProcessSelection.xhtml")) {
								formType = "&formType=wkflwSel";
							} else {
								formType = "&formType=wkflw";
							}
						}
				%> <a
					href="<%=request.getContextPath()%>/xforms?type=<%=className%><%=formType%>">
					<%=className%> </a> <%
					} else {
					%> <%=aFile.getName()%> <%
					}
				%>
				</td>
				<td align="center" valign="middle">
				<%
					if (aFile.getName().endsWith(".xhtml")) {
				%> <%--<a href="<%=request.getContextPath()%>/XFormsServlet?form=/<%=uri%>/<%=aFile.getName()%>">--%>
				<a
					href="<%=request.getContextPath()%>/<%=uri%>/<%=aFile.getName()%>">
				<img src="<%=request.getContextPath()%>/resources/images/text.gif"
					border="0" width="20" height="20" align="middle"> </a> <%
 	} else {
 %> <img src="<%=request.getContextPath()%>/resources/images/text.gif"
					border="0" width="20" height="20" align="middle"> <%
 	}
 %>
				</td>

				<%--
                    <td align="center" valign="middle">
                        <a href="<%=request.getContextPath()%>/XFormsServlet?form=/<%=uri%>/<%=aFile.getName()%>"
                            onclick="this.href=this.href + '&js=enabled'">
                            <img src="<%=request.getContextPath()%>/resources/images/text.gif" border="0" width="20" height="20" align="center">
                        </a>
					</td>
--%>

				<%--
                    <td>
                            <a href="<%=request.getContextPath()%>/<%=uri%>/<%=aFile.getName()%>">source</a>
                    </td>
--%>
				<td><%=""
								+ DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
										DateFormat.MEDIUM).format(new Date(f.lastModified()))%></td>

			</tr>
			<%
				}
					}
				}
			%>

		</table>
		</td>
	</tr>
</table>

<br>
<!--
<a href="<%=request.getContextPath()%>/forms/demo/actions.xhtml?form=<%=URLEncoder.encode(
							"http://147.87.255.30:8080/chiba-web-2.0.0/forms/demo/hello.xhtml",
							"UTF-8")%>">
This link loads a XForm from an absolute HTTP URL. Though actions.xhtml is requested hello.xhtml will be served as it's specified as a request param.
</a>
-->

<div class="side_jsp_footer"><a href="http://www.bluexml.com">BlueXML</a></div>
<div class="side_jsp_using">Using <a href="http://chiba.sourceforge.net/">Chiba</a></div>
<!-- <p align="right" style="font-size:8pt;">&copy; 2003-2005 Chiba</p> -->
</body>
</html>
