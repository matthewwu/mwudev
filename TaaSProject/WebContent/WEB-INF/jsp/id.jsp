<% if (session.getAttribute("login") != null) { %>
	<p style="float:right; margin:0; padding: 0; padding-right: 2em;">Logged in as <%= session.getAttribute("login") %> &mdash; <a href="login?action=logout">Logout</a></p>
<% } else { %>
	<p style="float:right; margin:0; padding: 0; padding-right: 2em;"><a href="login">Login</a></p>
<% } %>