<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@include file="includes/header.jspf" %>
<%@include file="includes/menu.jspf" %>

<div id="mainBody" personID="<%= request.getParameter("person_id") %>">
	 <div class="row">
		<div class="large-6 columns">
			<div id="profilePicture"></div>
		</div>
		<div class="large-6 columns">
			<div id="profileData"></div>
		</div>
	</div>
</div>

<div id="deleteAccountModal" class="reveal-modal" data-reveal aria-labelledby="modalTitle" aria-hidden="true" role="dialog">
	  <div id="profileDeleteConfirmForm"></div>
	  <a class="close-reveal-modal" aria-label="Close">&#215;</a>
</div>

<%@include file="includes/footer.jspf" %>

<script>
      $(document).foundation();
      $(document).ready(loadProfileData);
</script>
</body>
</html>