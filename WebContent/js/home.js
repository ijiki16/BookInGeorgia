/**
 * 
 */

$(document).ready(function(){
	
	$('.log').click(function() {
		$('.log-in').css({"visibility":"visible"});
		$('.homepage').css({"visibility":"visible"});
	});
	
	
	$('#close').click(function() {
		$('.log-in').css({"visibility":"hidden"});
		$('.homepage').css({"visibility":"hidden"});
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
					if(false) {
						alert(response);
						$('.log-in').css({"visibility":"hidden"});
						$('#log-and-reg').css({"visibility":"hidden"});
						$('.menu-but').css({"display": "block"});
						$('.homepage').css({"visibility":"hidden"});
					}else{
						$('.log-fail').html("Wrong User or Password");
						$('.log-fail').css({"font-size":"25px"});
					}
		});		
	});
	

});
