var people;

function updateTransactionValues(person_id, balance) {
	$('#payment-amount').val(balance);
	$("input[name='person_id']").val(person_id);
}

// Loads profile data
function loadProfileData() {
	var doc = document.documentElement;
	doc.setAttribute('data-useragent', navigator.userAgent);
	
	$.ajax({
		dataType: "json",
		url: "people"
	})
	.done(updateProfile);
}

function updateProfile(data) {
	var person_id = $('#mainBody').attr('personID');
	var peopleList = data['people'];
	for (n=0; n<peopleList.length; n++) {
		var person = peopleList[n];
		if (person.id == person_id) {
			// Update the profile section
			loadSection('profile','profileData', person, false);
		}
	}
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
	for (n=0; n<peopleList.length; n++) {
		// Update the person section
		loadSection('person','person'+(n+1), peopleList[n], true);
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
	loadSection('transactions',sectionName, data);
}

function loadSection(templateName, elementName, data, loadPagers) {
	  data.renderFunctions = renderFunctions;
	  $.get('templates/'+templateName+'.mst', function(template) {
		    var rendered = Mustache.render(template, data);
		    $('#'+elementName).html(rendered);
			// Apply currency formatting
			$('.currency').formatCurrency({ colorize:true, region: 'en-GB' });
			if (loadPagers) {
				addPagers(data);
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