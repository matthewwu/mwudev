<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.List" %>
<%@ page import="com.amazonaws.services.ec2.model.Image" %>
<%
@SuppressWarnings("unchecked")
List<Image> imageList = (List<Image>) request.getAttribute("imageList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>CMPE 281 &mdash; Development Lab &mdash; Instances Manager</title>
	<style type="text/css">
	@import url(css/design.css);
	</style>
</head>
<body>
<div id="page">
	<%@ include file="id.jsp" %>
	<h1>Cmpe 281 &mdash; Instances Manager</h1>
	
	<ul>
		<% for (Image img : imageList) { %>
		<li><%= img.getName() %></li>
		<% } %>
	</ul>
	
	<%@ include file="nav.jsp" %>
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>