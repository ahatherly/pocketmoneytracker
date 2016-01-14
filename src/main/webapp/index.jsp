<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@include file="includes/header.jspf" %>
<%@include file="includes/menu.jspf" %>

<div id="mainBody">
</div>

<div id="transactionModal" class="reveal-modal" data-reveal aria-labelledby="modalTitle" aria-hidden="true" role="dialog">
	  <h2 id="modalTitle">Add a transaction</h2>
	  
	  <ul class="tabs" data-tab>
		  <li class="tab-title active"><a href="#payment">Withdrawal</a></li>
		  <li class="tab-title"><a href="#reward">Reward</a></li>
		  <li class="tab-title"><a href="#penalty">Penalty</a></li>
	  </ul>
	  <div class="tabs-content">
		  <div class="content active" id="payment">
		    <form action="addTransaction" method="post">
		    <input type="hidden" name="person_id"/>
		    <p>
		    	  <div class="row collapse prefix-round">
				     <div class="small-3 columns">
				       <a href="#" class="button prefix">£</a>
				     </div>
				     <div class="small-9 columns">
				       <input name="amount" id="payment-amount" type="text" placeholder="Amount" />
				     </div>
				  </div>
				  <div class="row collapse prefix-round">
				     <div class="small-3 columns">
				       <a href="#" class="button prefix">Description</a>
				     </div>
				     <div class="small-9 columns">
				       <input name="name" type="text" placeholder="Description of transaction (optional)" />
				     </div>
				  </div>
				  <input name="payment" value="Submit" type="submit" class="button" />
		    </p>
		    </form>
		  </div>
		  <div class="content" id="reward">
		    <form action="addTransaction" method="post">
		    <input type="hidden" name="person_id"/>
		    <p>
		    	  <div class="row collapse prefix-round">
				     <div class="small-3 columns">
				       <a href="#" class="button prefix">£</a>
				     </div>
				     <div class="small-9 columns">
				       <input name="amount" type="text" placeholder="Amount" value="1" />
				     </div>
				  </div>
				  <div class="row collapse prefix-round">
				     <div class="small-3 columns">
				       <a href="#" class="button prefix">Description</a>
				     </div>
				     <div class="small-9 columns">
				       <input name="name" type="text" placeholder="Description of transaction (optional)" />
				     </div>
				  </div>
				  
				  <input name="reward" value="Submit" type="submit" class="button" />
		    </p>
		    </form>
		  </div>
		  <div class="content" id="penalty">
		    <form action="addTransaction" method="post">
		    <input type="hidden" name="person_id"/>
		    <p>
		    	  <div class="row collapse prefix-round">
				     <div class="small-3 columns">
				       <a href="#" class="button prefix">£</a>
				     </div>
				     <div class="small-9 columns">
				       <input name="amount" type="text" placeholder="Amount" value="0.5" />
				     </div>
				  </div>
				  <div class="row collapse prefix-round">
				     <div class="small-3 columns">
				       <a href="#" class="button prefix">Description</a>
				     </div>
				     <div class="small-9 columns">
				       <input name="name" type="text" placeholder="Description of transaction (optional)" />
				     </div>
				  </div>
				  
				  <input name="penalty" value="Submit" type="submit" class="button" />
		    </p>
		    </form>
		  </div>
	  </div>
	  <a class="close-reveal-modal" aria-label="Close">&#215;</a>
  </form>
</div>

<%@include file="includes/footer.jspf" %>

<script>
      $(document).foundation();
      $(document).ready(loadData);
      $( "#logout-button" ).click(doLogout);
</script>
</body>
</html>