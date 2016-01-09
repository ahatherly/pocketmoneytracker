<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@include file="includes/header.jspf" %>
<%@include file="includes/menu.jspf" %>

<div id="mainBody" username="<%= session.getAttribute("username") %>" personID="<%= session.getAttribute("personID") %>">
	 <div id="users"></div>
</div>

<%@include file="includes/footer.jspf" %>

<script>
      $(document).foundation();
      $(document).ready(loadUserData);
</script>
</body>
</html>