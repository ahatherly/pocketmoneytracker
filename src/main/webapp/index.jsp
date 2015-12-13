<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@include file="includes/header.jspf" %>
<%@include file="includes/menu.jspf" %>

<div id="mainBody">
	<div class="row">
		<div class="large-6 columns">
		<p id="person1"><img src="img/spinner.gif"/></p>
		<p id="person1-transactions"><img src="img/spinner.gif"/></p>
		</div>
		<div class="large-6 columns">
		<p id="person2"><img src="img/spinner.gif"/></p>
		<p id="person2-transactions"><img src="img/spinner.gif"/></p>
		</div>
	</div>
</div>

<footer class="row">
<div class="large-12 columns">
<hr/>
<div class="row">
<div class="large-6 columns">
<p>&copy; Copyright 2015, Adam Hatherly</p>
</div>
</div>
</div>
</footer>
<script>
      $(document).foundation();
      $(document).ready(loadData);
</script>
</body>
</html>