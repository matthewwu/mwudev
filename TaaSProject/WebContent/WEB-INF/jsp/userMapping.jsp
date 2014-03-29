<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.List" %>
<%@ page import="cmpe281.lab.Model.*" %>
<%
TestProject testProject = (TestProject) request.getAttribute("testProject");
@SuppressWarnings("unchecked")
List<TaaSUser> userList = (List<TaaSUser>) request.getAttribute("userList");
@SuppressWarnings("unchecked")
List<TaaSUser> allUsers = (List<TaaSUser>) request.getAttribute("allUsers");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>CMPE 281 &mdash; Development Lab &mdash; User Mapping for <%= testProject.getName() %></title>
	<style type="text/css">
	@import url(css/design.css);
	</style>
</head>
<body>
<div id="page">
	<%@ include file="id.jsp" %>
	<h1>User Mapping for <%= testProject.getName() %></h1>
	<%@ include file="nav.jsp" %>
	
	<div id="content">
		<h2>List of current users associated with the project</h2>
		<table>
			<tr>
				<th>Name</th>
				<th>Type</th>
				<th>Action</th>
			</tr>
			<% for (TaaSUser user : userList) { %>
			<tr>
				<td><%= user.getFullName() %></td>
				<td><%= user.getUserTypeName() %></td>
				<td><a href="userMapping?action=removeUser&amp;pid=<%= testProject.getProjectId() %>&amp;uid=<%= user.getUserId() %>">Remove User</a></td>
			</tr>
			<% } %>
		</table>
		
		<h2>Add user to the test project</h2>
		<form method="post" action="userMapping?action=addUser">
			<p>
				<input type="hidden" name="pid" value="<%= testProject.getProjectId() %>" />
			
				<select name="uid">
					<% for (TaaSUser user : allUsers) { %>
					<option value="<%= user.getUserId() %>"><%= user.getFullName() %></option>
					<% } %>
				</select>
				
				<input type="submit" value="Add" />
			</p>
		</form>
	</div>
	
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>