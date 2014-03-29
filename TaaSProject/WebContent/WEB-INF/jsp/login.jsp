<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>CMPE 281 &mdash; Development Lab &mdash; Login</title>
	<style type="text/css">
	@import url(css/design.css);
	</style>
</head>
<body>
<div id="page">
	<%@ include file="id.jsp" %>
	<h1>Cmpe 281 &mdash; Login</h1>
	<%@ include file="nav.jsp" %>
	
	<div id="content">
		<form method="post" action="login">
			<table style="width: 60%; margin: auto;">
				<tr>
					<td style="text-align: right;"><label for="login">Login:</label></td>
					<td><input type="text" id="login" name="login" /></td>
				</tr>
				<tr>
					<td style="text-align: right;"><label for="password">Password:</label></td>
					<td><input type="password" id="password" name="password" /></td>
				</tr>
				<tr style="text-align: center;">
					<td colspan="2"><input type="submit" value="Login" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>