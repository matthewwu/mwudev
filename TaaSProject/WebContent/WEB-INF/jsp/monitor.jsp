<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.amazonaws.services.ec2.*" %>
<%@ page import="com.amazonaws.services.ec2.model.*" %>
<%@ page import="cmpe281.lab.EC2.*" %>
<% AmazonEC2Client ec2 = (AmazonEC2Client) request.getAttribute("ec2"); %>
<% EC2Client ec2API = (EC2Client) request.getAttribute("ec2API"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>CMPE 281 &mdash; Development Lab &mdash; Monitor Instances</title>
	<style type="text/css">
	@import url(css/design.css);
	</style>
</head>
<body>
<div id="page">
	<%@ include file="id.jsp" %>
	<h1>Monitor Instances</h1>
	<%@ include file="nav.jsp" %>
	<table style="width: 100%;">
		<tr>
			<th>Amazon EC2 Instances</th>
			<th>Last Hour Avg CPU</th>
			<th>Last Hour Max CPU</th>
			<th>State</th>
			<th>DNS</th>
			<th colspan="3">Action</th>
		</tr>
		<% for (Reservation reservation : ec2.describeInstances().getReservations()) { %>
			<% for (Instance instance : reservation.getInstances()) { %>
			<tr>
				<td><%= instance.getInstanceId() %></td>
				<td><%= ec2API.GetLastHourCPUAvg(instance.getInstanceId() )%></td>
				<td><%= ec2API.GetLastHourCPUMax(instance.getInstanceId() )%></td>
				<td><%= instance.getState().getName() %></td>
				<td><a href="http://<%= instance.getPublicDnsName() %>:8080/cbsatesting/"><%= instance.getPublicDnsName() %></a></td>
				<td><a href="instance?action=start&amp;id=<%= instance.getInstanceId() %>">Start</a></td>
				<td><a href="instance?action=stop&amp;id=<%= instance.getInstanceId() %>">Stop</a></td>
				<td><a href="instance?action=terminate&amp;id=<%= instance.getInstanceId() %>">Terminate</a></td>
			</tr>
			<% } %>
		<% } %>
	</table>
	
	<p style="float: right;"><a href="instance?action=updateAll">Update all local EC2 instances in DB</a></p>
	<p><a href="instance?action=new">Create New Instance</a></p>
	
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>