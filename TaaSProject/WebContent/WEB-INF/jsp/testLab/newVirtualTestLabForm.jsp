<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.List" %>
<%@ page import="cmpe281.lab.Model.*" %>
<%
@SuppressWarnings("unchecked")
List<TestLab> testLabList = (List<TestLab>) request.getAttribute("testLabList");
@SuppressWarnings("unchecked")
List<TaaSUser> pmList = (List<TaaSUser>) request.getAttribute("pmList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>CMPE 281 &mdash; Development Lab &mdash; New Virtual Test Lab</title>
	<style type="text/css">
	@import url(css/design.css);
	</style>
</head>
<body>
<div id="page">
	<%@ include file="../id.jsp" %>
	<h1>Cmpe 281 &mdash; New Virtual Test Lab</h1>
	<%@ include file="../nav.jsp" %>
	
	<div id="content">
		<form method="post" action="LabMgmt?action=createVirtualTestLab">
			<table>
				<tr>
					<td><label for="VLabName">Virtual Lab Name:</label></td>
					<td><input type="text" id="VLabName" name="VLabName" /></td>
				</tr>
				<tr>
					<td><label for="ParentTestLab">Parent Test Lab:</label></td>
					<td>
						<select id="ParentTestLab" name="ParentTestLab">
							<% for (TestLab testLab : testLabList) { %>
								<option value="<%= testLab.getLabId() %>"><%= testLab.getLabName() %></option>
							<% } %>
						</select>
					</td>
				</tr>
				<tr>
					<td><label for="ProjectManagerId">Project Manager:</label></td>
					<td>
						<select id="ProjectManagerId" name="ProjectManagerId">
							<% for (TaaSUser pm : pmList) { %>
								<option value="<%= pm.getUserId() %>"><%= pm.getFullName() %></option>
							<% } %>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Create Virtual Test Lab" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	<%@ include file="../footer.jsp" %>
</div>
</body>
</html>
