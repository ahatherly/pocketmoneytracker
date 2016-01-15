<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@include file="includes/header.jspf" %>
<%@include file="includes/menu.jspf" %>

<div id="mainBody" username="<%= session.getAttribute("username") %>" personID="<%= session.getAttribute("personID") %>">
	 <div id="accounts">

		<div class="row">
			<div class="large-12 columns">
				Coming Soon..
			</div>
		</div>
	 </div>
</div>

<%@include file="includes/footer.jspf" %>

<script>
      $(document).foundation();
      //$(document).ready(loadUserData);
</script>
</body>
</html>