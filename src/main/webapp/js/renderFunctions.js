
/* JSONP file containing rendering functions for use in moustache templates for specific field types */
var renderFunctions = {
		"cat-icon": function () {
		    return function (text, render) {
		    	var val = render(text);
		    	if (val == 'reward') {
		    		return render("<i class='fa fa-thumbs-up'></i>");
		    	} else if (val == 'penalty') {
		    		return render("<i class='fa fa-thumbs-down'></i>");
		    	} else if (val == 'payment') {
		    		return render("<i class='fa fa-money'></i>");
		    	} else if (val == 'weekly') {
		    		return render("<i class='fa fa-clock-o'></i>");
		    	} else {
		    		return val;
		    	}
		    }
		},
		"day-of-week-dropdown": function () {
			    return function (text, render) {
			    	var result = "<select name='dayOfWeekPocketMoneyPaid'>";
			    	var val = render(text);
			    	console.log(val);
			    	for (n=1; n<8; n++) {
			    		result = result + "<option value='"+n+"' ";
			    		if (n==val) result = result + "selected";
			    		result = result + ">";
			    		switch(n) {
			    		case 1:
			    			result = result + "Sunday";
			    			break;
			    		case 2:
			    			result = result + "Monday";
			    			break;
			    		case 3:
			    			result = result + "Tuesday";
			    			break;
			    		case 4:
			    			result = result + "Wednesday";
			    			break;
			    		case 5:
			    			result = result + "Thursday";
			    			break;
			    		case 6:
			    			result = result + "Friday";
			    			break;
			    		case 7:
			    			result = result + "Saturday";
			    			break;
			    		}
			    		result = result + "</option>";
			    	}
			    	result = result + "</select>";
			    	console.log(result);
			    	return result;
			    }
		},
}