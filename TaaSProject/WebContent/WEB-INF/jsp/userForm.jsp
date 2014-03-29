<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.List" %>
<%@ page import="cmpe281.lab.Model.UserType" %>
<%@ page import="cmpe281.lab.Model.TaaSUser" %>
<% 
@SuppressWarnings("unchecked")
List<UserType> userTypeList = (List<UserType>) request.getAttribute("userTypeList");
TaaSUser user = (TaaSUser) request.getAttribute("user");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<% if (user.getUserId() == 0) { %>
		<title>CMPE 281 &mdash; Development Lab &mdash; Create New User</title>
	<% } else { %>
		<title>CMPE 281 &mdash; Development Lab &mdash; Update user '<%= user.getFullName() %>'</title>
	<% } %>
	<style type="text/css">
		@import url(css/design.css);
	</style>
</head>
<body>
<div id="page">
	<%@ include file="id.jsp" %>
	<% if (user.getUserId() == 0) { %>
		<h1>Create New User</h1>
	<% } else { %>
		<h1>Update user '<%= user.getFullName() %>'</h1>
	<% } %>
	
	<%@ include file="nav.jsp" %>
	<% if (request.getAttribute("msg") != null) { %>
		<%= request.getAttribute("msg") %>
	<% } %>
	
	<% if (user.getUserId() == 0) { %>
	<form method="post" action="users?action=create">
		<table>
			<tr>
				<td><label for="UserName">UserName</label>:</td>
				<td><input type="text" name="UserName" id="UserName" /></td>
			</tr>
			<tr>
				<td><label for="Password">Password</label>:</td>
				<td><input type="password" name="Password" id="Password" /></td>
			</tr>
			<tr>
				<td><label for="FullName">Full Name</label>:</td>
				<td><input type="text" name="FullName" id="FullName" /></td>
			</tr>
			
			<tr>
				<td><label for="UserType">User Type</label>:</td>
				<td>
					<select name="UserType" id="UserType">
						<% for (UserType type : userTypeList) { %>
						<option value="<%= type.getTypeId() %>"><%= type.getTypeName() %></option>
						<% } %>
					</select>
				</td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="submit" value="Create" /></td>
			</tr>
		</table>
	</form>
	<% } else { %>
	<form method="post" action="users?action=update&amp;id=<%= user.getUserId() %>">
		<table>
			<tr>
				<td><label for="UserName">UserName</label>:</td>
				<td><input type="text" name="UserName" id="UserName" value="<%= user.getUserName() %>" /></td>
			</tr>
			<tr>
				<td><label for="Password">Password</label>:</td>
				<td><input type="password" name="Password" id="Password" value="<%= user.getPassword() %>" /></td>
			</tr>
			<tr>
				<td><label for="FullName">Full Name</label>:</td>
				<td><input type="text" name="FullName" id="FullName" value="<%= user.getFullName() %>" /></td>
			</tr>
			
			<tr>
				<td><label for="UserType">User Type</label>:</td>
				<td>
					<select name="UserType" id="UserType">
						<% for (UserType type : userTypeList) { %>
							<% if (user.getUserTypeId() == type.getTypeId()) { %>
								<option value="<%= type.getTypeId() %>" selected="selected"><%= type.getTypeName() %></option>
							<% } else { %>
								<option value="<%= type.getTypeId() %>"><%= type.getTypeName() %></option>
							<% } %>
						<% } %>
					</select>
				</td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="submit" value="Update" /></td>
			</tr>
		</table>
	</form>
	<% } %>
	
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>