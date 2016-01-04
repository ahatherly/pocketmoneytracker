<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@include file="includes/header.jspf" %>
<%@include file="includes/menu.jspf" %>

<div id="mainBody" personID="<%= request.getParameter("person_id") %>">
	 <div id="profileData"></div>
</div>

<footer class="row">
<div class="large-12 columns">
<hr/>
<div class="row">
<div class="large-6 columns">
<p>&copy; Copyright 2016, Adam Hatherly</p>
</div>
</div>
</div>
</footer>
<script>
      $(document).foundation();
      $(document).ready(loadProfileData);
</script>
</body>
</html>