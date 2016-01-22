
// Load user page data
function loadUserData() {
	var doc = document.documentElement;
	doc.setAttribute('data-useragent', navigator.userAgent);
	
	$.ajax({
		dataType: "json",
		url: "users"
	})
	.done(updateUser);
}

function updateUser(data) {
	var username = $('#mainBody').attr('username');
	
	var userList = data['logins'];
	for (n=0; n<userList.length; n++) {
		var user = userList[n];
		// Add a new div and load the user details template into it
		$('#users').append('<div id=user_'+n+'></div>');
		loadSection('user','user_'+n,user,null);
	}
}