<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@include file="includes/header.jspf" %>
<%@include file="includes/menu.jspf" %>

<div id="mainBody">
	<div class="row">
		<div class="large-6 columns">
			<div id="profile-pic"></div>
			<a href="#" id="setImage" class="button">Set as profile picture</a>
			<div id="info"></div>
		</div>
	</div>
</div>

<%@include file="includes/footer.jspf" %>

<script>
      $(document).foundation();
      var person_id="<%= request.getParameter("person_id") %>";
      
      var basic = $('#profile-pic').croppie({
    	    viewport: {
    	        width: 140,
    	        height: 140,
    	        type: 'circle'
    	    },
		    boundary: {
			        width: 500,
			        height: 500
			}
    	});
    	basic.croppie('bind', {
    	    url: 'image'
    	});
      $('#setImage').on('click', function (ev) {
    	    basic.croppie('result', {"type":"canvas","size":"viewport"}).then(function (resp) {
    	    	$.ajax({
    	    		   url: 'storeimage',
    	    		   data: {
    	    			   data: resp,
    	    			   id: person_id
    	    		   },
    	    		   error: function() {
    	    		      $('#info').html('<p>An error has occurred</p>');
    	    		   },
    	    		   success: function(data) {
    	    			   window.location.href = "profile.jsp?person_id="+person_id;
    	    		   },
    	    		   type: 'POST'
    	    		});
				
			});
	  });
      
</script>
</body>
</html>