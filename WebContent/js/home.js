/**
 * 
 */

$(document).ready(function(){
	
	$('.log').click(function() {
		$('.log-in').css({"visibility":"visible"});
	});
	

	$('.reg').click(function() {
		$('.register-box').css({"visibility":"visible"});
	});
	

	$('.go-to-reg').click(function() {
		$('.log-in').css({"visibility":"hidden"});
		$('.register-box').css({"visibility":"visible"});
	});
	
	$('.log-btn').click(function(){
		let _user = $('.user').val();
		let _password = $('.password').val();
		$.post('./js/logging.jsp', 
				{
					user: _user,
					password: _password
				}, 
				function(response) {
					if(response) {
						alert(response);
						$('.log-in').css({"visibility":"hidden"});
						$('.log').css({"display":"none"});
						$('.menu-but').css({"display": "block"});
						$('#log-fail').css({"visibility":"visible"});
					}
		});
		
	$('.reg-btn').click(function() {
		alert("she kitro shena");
	});
		
	});
	

});
