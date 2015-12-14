Number.prototype.formatMoney = function(c, d, t){
var n = this, 
    c = isNaN(c = Math.abs(c)) ? 2 : c, 
    d = d == undefined ? "." : d, 
    t = t == undefined ? "," : t, 
    s = n < 0 ? "-" : "", 
    i = parseInt(n = Math.abs(+n || 0).toFixed(c)) + "", 
    j = (j = i.length) > 3 ? j % 3 : 0;
   return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "£1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
 };

/* JSONP file containing rendering functions for use in moustache templates for specific field types */
var renderFunctions = {
		"currency": function () {
		    return function (text, render) {
		    	//return render(text).formatMoney(2);
		    	//return render(text);
		    	return "Test";
		    }
		  }
		/*,
		"boolean": function () {
			return function (text, render) {
		    	var val = render(text).trim();
		    	if (val == "true") return "Yes";
		    	if (val == "false") return "No";
		    	return val;
			}
		  }*/
}