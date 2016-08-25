function MailSchedulers(){
	var from = ($('#from_email').val());
	var password = ($('#password').val()).trim();
	var subject = ($('#subject').val()).trim();
	var to = ($('#to_email').val()).trim();
	var message = ($('#message').val()).trim();
	var second = ($('#second').val()).trim();
	var minute = ($('#minute').val()).trim();
	var hour = ($('#hour').val()).trim();
	var day = ($('#day').val()).trim();
	var month = ($('#month').val()).trim();
	var week = ($('#week').val()).trim();
	var year = ($('#year').val()).trim();
	
	var SchedulerTimePattern;
	if(year != "NO"){
		SchedulerTimePattern = second + " " + minute + " " + hour + " " + day + " " + month + " " + week + " " + year;
	}
	else{
		SchedulerTimePattern = second + " " + minute + " " + hour + " " + day + " " + month + " " + week;
	}
	
	if( (typeof(from) == "undefined" || from == "") || (typeof(password) == "undefined" || password == "") || 
			(typeof(subject) == "undefined" || subject == "") || (typeof(to) == "undefined" || to == "") || 
			(typeof(message) == "undefined" || message == "")){
				alert("Please Enter the all Input Form Value");
	}
	else if(!(validateEmail(from) && validateEmail(to))){
		alert('Invalid Email Address');
	}
	else{
		var data ={"from":from, "subject":subject, "password":password, "to":to, "message":message, "SchedulerTimePattern":SchedulerTimePattern};
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
