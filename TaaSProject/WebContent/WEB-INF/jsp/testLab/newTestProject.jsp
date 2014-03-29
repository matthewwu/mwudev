<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.List" %>
<%@ page import="cmpe281.lab.Model.*" %>
<%
@SuppressWarnings("unchecked")
List<VirtualTestLab> vTLabList = (List<VirtualTestLab>) request.getAttribute("vTLabList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>CMPE 281 &mdash; Development Lab &mdash; New Test Project</title>
	<style type="text/css">
	@import url(css/design.css);
	</style>
</head>
<body>
<div id="page">
	<%@ include file="../id.jsp" %>
	<h1>Cmpe 281 &mdash; New Test Project</h1>
	<%@ include file="../nav.jsp" %>
	
	<div id="content">
		<form method="post" action="LabMgmt?action=createTestProject">
			<table>
				<tr>
					<td><label for="TPName">Test Project Name:</label></td>
					<td><input type="text" id="TPName" name="TPName" /></td>
				</tr>
				<tr>
					<td><label for="Priority">Priority:</label></td>
					<td>
						<select name="Priority" id="Priority">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><label for="VLabId">Associated Virtual Lab:</label></td>
					<td>
						<select name="VLabId" id="VLabId">
							<% for (VirtualTestLab vlab : vTLabList) { %>
							<option value="<%= vlab.getVLabId() %>"><%= vlab.getVLabName() %></option>
							<% } %>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Create Test Project" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	<%@ include file="../footer.jsp" %>
</div>
</body>
</html>
