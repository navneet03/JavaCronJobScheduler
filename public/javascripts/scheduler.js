function hello(){
	var from = $('#from_email').val();
	var password = $('#password').val();
	var subject = $('#subject').val();
	var to = $('#to_email').val();
	var message = $('#message').val();
	var date = $('#date').val();
	var time = $('#time').val();
	
	if( (typeof(from) == "undefined" || from == "") || (typeof(password) == "undefined" || password == "") || 
			(typeof(subject) == "undefined" || subject == "") || (typeof(to) == "undefined" || to == "") || 
			(typeof(message) == "undefined" || message == "") || (typeof(date) == "undefined" || date == "") || 
			(typeof(time) == "undefined" || time == "")){
		alert("Please Enter the all Input Form Value");
	}
	else if(!(validateEmail(from) && validateEmail(to))){
		alert('Invalid Email Address');
	}
	else{
		var data ={"from":from, "subject":subject, "password":password, "to":to, "message":message, "date":date, "time":time};
		$.ajax({
	        url : '/getSchedulerDetails ',
	        type : "POST",
	        contentType: 'application/json; charset=utf-8',
	        dataType: 'text',
	        data: JSON.stringify(data),
	        success : function(response) {
//	            response = JSON.parse(response);
	            alert(response);
	        },

	        error : function(xhr, errmsg, err) {
	            console.log(xhr.status + ": " + xhr.responseText);
	        }
	    });
	}	
}

function validateEmail(email) {
	  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	  return re.test(email);
	}
