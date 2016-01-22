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
	var admin=$('#appMenu').attr('admin');
	
	for (n=0; n<peopleList.length; n++) {
		var person = peopleList[n];
		if (person.id == person_id) {
			// Update the profile section
			if (admin == "true") {
				loadSection('profile','profilePicture', person, null);
				loadSection('profile_data','profileData', person, addDeleteButton);
			} else {
				loadSection('profile','profilePicture', person, null);
			}
		}
	}
}

function addDeleteButton(data) {
	loadSection('profile_delete_button','profileDelete', data, null);
	loadSection('profile_delete','profileDeleteConfirmForm', data, null);
}

function openDeleteConfirmModal(person) {
	
}