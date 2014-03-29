<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="cmpe281.lab.Model.*" %>
<%
@SuppressWarnings("unchecked")
List<TestProject> listProjects = (List<TestProject>)request.getAttribute("listProjects");
@SuppressWarnings("unchecked")
List<Request> listRequests = (List<Request>)request.getAttribute("listRequests");
@SuppressWarnings("unchecked")
List<EC2Instance> listInstances = (List<EC2Instance>)request.getAttribute("listInstances");
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
	<h1>Allocation </h1>
	<%@ include file="nav.jsp" %>
	
<h2>Current Allocation</h2>
<table border="1">
			<tr>
				<th>Id</th>
				<th>Name</th>								
				<th>Priority</th>
				<th>Virtual Test Lab Id</th>
				<th>EC2 Instance</th>
			</tr>
			<% for (TestProject pitem : listProjects) {
				if(pitem.getInstanceId().length()>0)
				{
				%>
			<tr>
				<td><%= pitem.getProjectId() %></td>
				<td><%= pitem.getName() %></td>
				<td><%= pitem.getPriority() %></td>
				<td><%= pitem.getParentVLabId() %></td>
				<td><%= pitem.getInstanceId() %></td>
			</tr>
			<%
				}
			} %>
	</table>

<h2>Pending Request</h2>
<table border="1">
			<tr>
				<th>Id</th>
				<th>Project Name</th>								
				<th>Priority</th>				
				<th>Request Time</th>			
				<th>Available Instance</th>	
				<th>Action</th>
			</tr>
			<% for (Request ritem : listRequests) {
				if(ritem.getEC2InstanceId().length()==0)
				{
				%>
			<tr>
				<td><%= ritem.getRequestId() %></td>
				<td><%= ritem.getProjectName() %></td>
				<td><%= ritem.getProjectPriority() %></td>				
				<td><%= ritem.getRequestTime() %></td>
				<td>
						<select id="availableInstanceId" name="availableInstanceId">
							<% for (EC2Instance eitem : listInstances) { %>							
							<option value="<%= eitem.getInstanceId() %>"><%= eitem.getInstanceId() %></option>							
							<%} %>
						</select>
				</td>
				<td>			
				<%if(listInstances.size()>0){ %>	
					<a href="Allocation?action=grantrequest&amp;rid=<%= ritem.getRequestId() %>">Grant Request</a>					
				<%} else{%>
					No Instance Available
				<%} %>
				</td>
			</tr>
			<%
				}
			} %>
	</table>
<%@ include file="footer.jsp" %>
</div>
</body>
</html>