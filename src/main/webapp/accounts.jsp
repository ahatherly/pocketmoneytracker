<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@include file="includes/header.jspf" %>
<%@include file="includes/menu.jspf" %>

<div id="mainBody" username="<%= session.getAttribute("username") %>" personID="<%= session.getAttribute("personID") %>">
	 <div id="accounts">

		<div class="row">
			<div class="large-12 columns">
				<ul class="button-group round">
			  		<li><a href="#" class="small button"
						data-reveal-id="addAccountModal">Add an Account</a></li>
				</ul>
			</div>
		</div>
	 </div>
</div>

<div id="addAccountModal" class="reveal-modal" data-reveal aria-labelledby="modalTitle" aria-hidden="true" role="dialog">
	  <h2 id="modalTitle">Add an account</h2>
	  <div id="profileDetails"></div>
	  <a class="close-reveal-modal" aria-label="Close">&#215;</a>
</div>

<%@include file="includes/footer.jspf" %>

<script>
      $(document).foundation();
      $(document).ready(loadAccounts);
</script>
</body>
</html>