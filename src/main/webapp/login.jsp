<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@include file="includes/header.jspf" %>

<div class="row">
	<div class="large-12 columns">
	<h3>Login</h3>
	<p>Enter your username and password to proceed:</p>
	<div id="login-messages"></div>
	</div>
</div>

<div class="row">
    <div class="large-3 columns">
      <label>Username
        <input type="text" id="username" placeholder="enter username" />
      </label>
    </div>
    <div class="large-3 columns">
      <label>Password
        <input type="password" id="password" placeholder="enter password" />
      </label>
    </div>
    <div class="large-3 columns end">
    	<a href="#" id="loginButton" class="button">Login</a>
    </div>
</div>

<%@include file="includes/footer.jspf" %>

<script>
      $(document).foundation();

      var doc = document.documentElement;
      doc.setAttribute('data-useragent', navigator.userAgent);
      
      $( "#loginButton" ).click(doLogin);
      
   	  // Capture enter key press in any of the login fields to initiate login
      $('#username').keyup(function(event){ if(event.keyCode == 13){ doLogin(); } });
      $('#password').keyup(function(event){ if(event.keyCode == 13){ doLogin(); } });
      
      $(document).ready(function() {
    	  var message="<%= request.getAttribute("Message") %>";
    	  if (message.length > 0) {
    		  $("#login-messages").html('<div data-alert class="alert-box info radius">'+message+'</div>');
    	  }
      });
    </script>
</body>
</html>