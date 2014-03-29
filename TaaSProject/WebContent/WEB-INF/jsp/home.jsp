<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="cmpe281.lab.Model.TestProject" %>
<%@ page import="java.util.List" %>
<%
@SuppressWarnings("unchecked")
List<TestProject> listProjects = (List<TestProject>)request.getAttribute("listProjects");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>CMPE 281 &mdash; Development Lab &mdash; Home</title>
	<style type="text/css">
	@import url(css/design.css);
	</style>
</head>
<body>
<div id="page">
	<%@ include file="id.jsp" %>
	<h1>Cmpe 281 &mdash; Development Lab</h1>
	<%@ include file="nav.jsp" %>
	
	<% if (request.getAttribute("login") != null) { %>
	<h2><%= request.getAttribute("login")%>'s Project</h2>
	<table border="1">
			<tr>
				<th>Id</th>
				<th>Name</th>								
				<th>Priority</th>
				<th>Virtual Test Lab Id</th>
				<th>EC2 Instance</th>
				<th>DNS</th>				
			</tr>
			<% for (TestProject pitem : listProjects) { %>
			<tr>
				<td><%= pitem.getProjectId() %></td>
				<td><%= pitem.getName() %></td>
				<td><%= pitem.getPriority() %></td>
				<td><%= pitem.getParentVLabId() %></td>
				<td><%= pitem.getInstanceId() %></td>
				<%if(pitem.getInstanceDNS() != null) {%>				
				<td><a href="http://<%= pitem.getInstanceDNS() %>:8080/cbsatesting"><%= pitem.getInstanceDNS() %></a></td>						
				<%}else{ %>
				<td>
				
				</td>
				<%} %>														
			</tr>
			<% } %>
	</table>
	<% } else { %>
	<p>On this page, you can see all your project, but because you're an anonymous user, you can't.<br />
	Please <a href="login">login</a> as an user to see your test projects.</p>
	
	<% } %>
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>