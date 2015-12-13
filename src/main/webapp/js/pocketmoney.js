var people;

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
	console.log(data);
	var peopleList = data['people'];
	for (n=0; n<peopleList.length; n++) {
		loadTransactions(n+1, peopleList[n]['id']);
	}
}

function loadTransactions(index, pid) {
    // Load the transactions for this person
	// http://localhost:8081/pocketmoneytracker/transactions?person_id=def981ff-6622-4db7-84c8-6586f3aad76a
	var person_id = {};
    person_id["person_id"] = pid;
    person_id["index"] = index;
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
	loadSection('transactions',sectionName, data);
}

function loadSection(templateName, elementName, data) {
		$.get('templates/'+templateName+'.mst', function(template) {
		    var rendered = Mustache.render(template, data);
		    $('#'+elementName).html(rendered);
	  });
}