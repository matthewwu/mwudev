<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="cmpe281.lab.Model.TaaSUser" %>
<%
@SuppressWarnings("unchecked")
List<TaaSUser> listUsers = (List<TaaSUser>) request.getAttribute("listUsers"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>CMPE 281 &mdash; Development Lab &mdash; Users Management</title>
	<style type="text/css">
		@import url(css/design.css);
	</style>
</head>
<body>
<div id="page">
	<%@ include file="id.jsp" %>
	<h1>Users Management</h1>
	
	<%@ include file="nav.jsp" %>
	
	<% if (request.getAttribute("msg") != null) { %>
		<p class="msg"><%= request.getAttribute("msg") %></p>
	<% } %>
	
	<table>
		<tr>
			<th>User ID</th>
			<th>Name</th>
			<th>Full Name</th>
			<th>Type</th>
			<th colspan="2">Actions</th>
		</tr>
		<% for (TaaSUser user : listUsers) { %>
		<tr>
			<td><%= user.getUserId() %></td>
			<td><%= user.getUserName() %></td>
			<td><%= user.getFullName() %></td>
			<td><%= user.getUserTypeName() %></td>
			<td><a href="users?id=<%= user.getUserId() %>&amp;action=update">update</a></td>
			<td><a href="users?id=<%= user.getUserId() %>&amp;action=delete">delete</a></td>
		</tr>
		<% } %>
	</table>
	
	<p><a href="users?action=new">Create New User</a></p>
	
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>