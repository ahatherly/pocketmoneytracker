<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@include file="includes/header.jspf" %>
<%@include file="includes/menu.jspf" %>

<div id="mainBody" personID="<%= request.getParameter("person_id") %>">
	 <div id="profileData"></div>
</div>

<%@include file="includes/footer.jspf" %>

<script>
      $(document).foundation();
      $(document).ready(loadProfileData);
</script>
</body>
</html>