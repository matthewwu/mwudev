<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.List" %>
<%@ page import="com.amazonaws.services.ec2.model.Image" %>
<%
@SuppressWarnings("unchecked")
List<Image> images = (List<Image>) request.getAttribute("images");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>CMPE 281 &mdash; Development Lab &mdash; Create New Instance</title>
	<style type="text/css">
	@import url(css/design.css);
	</style>
</head>
<body>
<div id="page">
	<%@ include file="id.jsp" %>
	<h1>Cmpe 281 &mdash; Create New Instance</h1>
	<%@ include file="nav.jsp" %>
	
	<div id="content">
		<form method="post" action="instance?action=create">
			<table>
				<tr>
					<td><label for="imageId">Select <abbr title="Amazon Machine Image">AMI</abbr> to launch:</label></td>
					<td>
						<select id="imageId" name="imageId">
							<% for (Image img : images) { %>
							<option value="<%= img.getImageId() %>"><%= img.getName() %></option>
							<% } %>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Create" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>