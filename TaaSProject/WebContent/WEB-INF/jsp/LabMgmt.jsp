<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="cmpe281.lab.Model.TestLab" %>
<%@ page import="cmpe281.lab.Model.VirtualTestLab" %>
<%@ page import="cmpe281.lab.Model.TestProject" %>
<%@ page import="cmpe281.lab.Model.TaaSUser" %>
<%
@SuppressWarnings("unchecked")
List<TestLab> listLabs = (List<TestLab>) request.getAttribute("listLabs");
@SuppressWarnings("unchecked")
List<VirtualTestLab> listVLabs = (List<VirtualTestLab>) request.getAttribute("listVLabs");
VirtualTestLab VTestLab = new VirtualTestLab();
@SuppressWarnings("unchecked")
List<TestProject> listProjects = (List<TestProject>)request.getAttribute("listProjects");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CMPE 281 &mdash; Development Lab &mdash; Lab Management</title>
	<style type="text/css">
	@import url(css/design.css);
	</style>
</head>
<body>
<div id="page">
	<%@ include file="id.jsp" %>
	<h1>Lab Management</h1>
	<%@ include file="nav.jsp" %>
	<h2>Test Lab</h2>
	<table border="1">
		<tr>				
			<th>Test Lab Id</th>
			<th>Name</th>
			<th>Administrator</th>
			<th colspan="2">Actions</th>								
		</tr>
		<% for (TestLab item : listLabs) { %>
		<tr>
			<td><%= item.getLabId() %></td>
			<td><%= item.getLabName() %></td>
			<td><%= item.getAdminUserName() %></td>
			<td><a href="LabMgmt?action=edit&amp;id=<%= item.getLabId() %>">Edit</a></td>
			<td><a href="LabMgmt?action=delete&amp;id=<%= item.getLabId() %>">Delete</a></td>
		</tr>
		<% } %>
	</table>
	<p><a href="LabMgmt?action=newTestLab">Create new Test Lab</a></p>		
	<h2>Virtual Test Lab</h2>
	<table border="1">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Project Manager</th>	
			<th>Test Lab Id</th>
		</tr>
		<% for (VirtualTestLab vitem : listVLabs) { %>
		<tr>
			<td><%= vitem.getVLabId() %></td>
			<td><%= vitem.getVLabName() %></td>
			<td><%= vitem.getPMUserName() %></td>
			<td><%= vitem.getParentTestLabId()%></td>									
		</tr>
		<% } %>
	</table>
	<p><a href="LabMgmt?action=newVirtualTestLab">Create new Virtual Test Lab</a></p>
	<h2>Test Project</h2>
	<table border="1">
			<tr>
				<th>Id</th>
				<th>Name</th>								
				<th>Priority</th>
				<th>Virtual Test Lab Id</th>
				<th>EC2 Instance</th>
				<th colspan="2">Action</th>
			</tr>
			<% for (TestProject pitem : listProjects) { %>
			<tr>
				<td><%= pitem.getProjectId() %></td>
				<td><%= pitem.getName() %></td>
				<td><%= pitem.getPriority() %></td>
				<td><%= pitem.getParentVLabId() %></td>
				<td><%= pitem.getInstanceId() %></td>
				<td>
					<% if (pitem.getRequestStatus() == 0) { %>
						<a href="LabMgmt?action=requestinstance&amp;pid=<%= pitem.getProjectId() %>">Request Instance</a>
					<% } else if (pitem.getRequestStatus() == 1) { %>
						<em>Pending...</em>
					<% } else { %>
						<a href="LabMgmt?action=removeinstance&amp;pid=<%= pitem.getProjectId() %>">Remove Instance</a>
					<% } %>
				</td>
				<td><a href="userMapping?pid=<%= pitem.getProjectId() %>">Edit user mapping</a></td>
			</tr>
			<% } %>
	</table>
	<p><a href="LabMgmt?action=newTestProject">Create new Test Project</a></p>
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>