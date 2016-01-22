function doLogin() {
	var credentials = {}
	credentials['action'] = "login";
	credentials['username'] = $("#username").val();
	credentials['password'] = $("#password").val();
	$.ajax({
		dataType: "text",
		type: "POST",
		url: "/pocketmoneytracker/login",
		data:credentials
	})
	.done(function(data) {
		if (data == "OK") {
			$.cookie('username', credentials['username']);
			$.cookie('active', 'true');
			document.location.href = './index.jsp';
		} else {
			$("#login-messages").html('<div data-alert class="alert-box alert round">Login or password incorrect</div>');
		}
	});
}

function doLogout() {
	$.removeCookie('username');
	$.removeCookie('active');
	$.ajax({
		dataType: "text",
		url: "/pocketmoneytracker/login",
		data:{}
	})
	.done(function(data) {
		document.location.href = './login.jsp';
	});
}


var people;

function updateTransactionValues(person_id, balance) {
	$('#payment-amount').val(balance);
	$("input[name='person_id']").val(person_id);
}

// Loads all person and transaction data
function loadData() {
	var doc = document.documentElement;
	doc.setAttribute('data-useragent', navigator.userAgent);
	
	$.ajax({
		dataType: "json",
		url: "people"
	})
	.done(updatePeople);
}

function updatePeople(data) {
	var peopleList = data['people'];
	var admin=$('#appMenu').attr('admin');
	var person_id=$('#appMenu').attr('person_id');
	var id_to_append_person_to = '';
	
	if (peopleList.length == 0) {
		$('#mainBody').append('<div id="mainBodyRow" class="row">')
		$('#mainBodyRow').append('<div id="mainBodyCol" class="large-12 medium-12 columns center">')
		$('#mainBodyCol').append("<p>You don't have any accounts yet - click 'Accounts' from the menu above, then click the 'Add an account' button.</p>");
	}
	
	for (n=0; n<peopleList.length; n++) {
		// Add person section
		var content = '<div class="large-6 medium-6 columns">'+
							'<p id="person'+(n+1)+'"><img src="img/spinner.gif"/></p>'+
							'<p id="person'+(n+1)+'-transactions"><img src="img/spinner.gif"/></p></div>'
		if (n%2 == 0) {
			$('#mainBody').append('<div class="row" id="personRow-'+(n/2)+'">');
			id_to_append_person_to = 'personRow-'+(n/2);
		}
		$('#'+id_to_append_person_to).append(content);
		
		// Update the person section
		if (admin == "true") {
			loadSection('person_admin','person'+(n+1), peopleList[n], null);
		} else if (person_id == peopleList[n].id) {
			loadSection('person_me','person'+(n+1), peopleList[n], null);
		} else {
			loadSection('person','person'+(n+1), peopleList[n], null);
		}
		// Load the transactions
		loadTransactions(n+1, peopleList[n]['id'], 0);
	}
}

function loadTransactions(index, pid, offset) {
    // Load the transactions for this person
	// http://localhost:8081/pocketmoneytracker/transactions?person_id=def981ff-6622-4db7-84c8-6586f3aad76a
	var person_id = {};
    person_id["person_id"] = pid;
    person_id["index"] = index;
    person_id["offset"] = offset;
	$.ajax({
			dataType: "json",
			url: "transactions",
			data:person_id
	})
	.done(function(data) {
			updateTransactions(index, data);
		});
}

function updateTransactions(index, data) {
	var sectionName = 'person'+index+'-transactions';
	data.index = index;
	loadSection('transactions',sectionName, data, addPagers);
}

function loadSection(templateName, elementName, data, postRenderFunction) {
	  data.renderFunctions = renderFunctions;
	  
	  $.get('templates/'+templateName+'.mst', function(template) {
		    var rendered = Mustache.render(template, data);
		    $('#'+elementName).html(rendered);
			// Apply currency formatting
			$('.currency').formatCurrency({ colorize:true, region: 'en-GB' });
			if (postRenderFunction != null) {
				postRenderFunction(data);
			}
	  });
}

function addPagers(data) {
	var person_id=data.person_id;
	var index=data.index;
	var prev_offset = data.offset-10;
	if (prev_offset < 0) {
		prev_offset = 0;
	}
	var prev_link = "loadTransactions("+index+", \""+person_id+"\","+prev_offset+");";
	var next_link = "loadTransactions("+index+", \""+person_id+"\","+(data.offset+10)+");";
	// Now add pagers if required
	if (data.offset > 0) {
		// Show previous button
		$('#previous-'+data.index).html("<a href='#' onclick='"+prev_link+"'><i class='fa fa-backward'></i> Prev</a>");
	}
	if ((data.offset + 12) > data.count) {
		// No next button
		$('#pager-'+data.index).html("Showing "+(data.offset+1)+" - "+data.count+" of "+data.count);
	} else {
		// Show next button
		$('#next-'+data.index).html("<a href='#' onclick='"+next_link+"'>Next <i class='fa fa-forward'></i></a>");
		$('#pager-'+data.index).html("Showing "+(data.offset+1)+" - "+(data.offset+10)+" of "+data.count);
	}
}