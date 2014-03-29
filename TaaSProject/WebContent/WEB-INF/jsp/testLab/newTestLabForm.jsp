<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.List" %>
<%@ page import="cmpe281.lab.Model.TaaSUser" %>
<%
@SuppressWarnings("unchecked")
List<TaaSUser> adminList = (List<TaaSUser>) request.getAttribute("adminList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>CMPE 281 &mdash; Development Lab &mdash; New Test Lab</title>
	<style type="text/css">
	@import url(css/design.css);
	</style>
</head>
<body>
<div id="page">
	<%@ include file="../id.jsp" %>
	<h1>Cmpe 281 &mdash; New Test Lab</h1>
	<%@ include file="../nav.jsp" %>
	
	<div id="content">
		<form method="post" action="LabMgmt?action=createTestLab">
			<table>
				<tr>
					<td><label for="LabName">Test Lab Name:</label></td>
					<td><input type="text" id="LabName" name="LabName" /></td>
				</tr>
				<tr>
					<td><label for="AdminUserId">Select Lab Admin:</label></td>
					<td>
						<select id="AdminUserId" name="AdminUserId">
							<% for (TaaSUser user : adminList) { %>
							<option value="<%= user.getUserId() %>"><%= user.getFullName() %></option>
							<% } %>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Create Test Lab" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	<%@ include file="../footer.jsp" %>
</div>
</body>
</html>
