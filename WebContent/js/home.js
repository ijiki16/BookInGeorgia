/**
 * 
 */

$(document).ready(function(){
	$.post('./js/sessions.jsp', 
			{
				todo: "login",
			}, 
			function(response) {
				if($.trim(response) != 'fail') {
					$('.log-in').css({"visibility":"hidden"});
					$('.menu-but').css({"display": "block"});
				}else{
					$('#log-and-reg').css({"visibility":"visible"});
				}
	});
	
	$('#log-out').click(function(){
		$.post('./js/sessions.jsp', 
				{
					todo: "logout",
				}, 
				function(response) {
					$('#log-and-reg').css({"visibility":"visible"});
					$('.menu-but').css({"display": "none"});
				});
	});
	
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
		$('.user').val("");
		$('.password').val("");
		$.post('./js/logging.jsp', 
				{
					user: _user,
					password: _password
				}, 
				function(response) {
					if($.trim(response) != 'fail') {
						$('.log-in').css({"visibility":"hidden"});
						$('#log-and-reg').css({"visibility":"hidden"});
						$('.menu-but').css({"display": "block"});
						$('.menu-but').html(response + "  <i class=\"arrow down\"></i>");
						$('.homepage').css({"visibility":"hidden"});
					}else{	
						$('.log-fail').html("Wrong User or Password");
						$('.log-fail').css({"font-size":"25px"});
					}
		});		
	});
	

});
