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
					$('#log-and-reg').css({"visibility":"hidden"});
					$('.log-in').css({"visibility":"hidden"});
					$('.menu-but').css({"display": "block"});
					$('#posts').css({"display": "block"});
					$('#add').css({"display": "block"});
				}else{
					$('#posts').css({"display": "none"});
					$('#add').css({"display": "none"});
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
					$('#posts').css({"display": "none"});
					$('#add').css({"display": "none"});
					$('.menu').css({"visibility": "hidden"});
					window.location.replace("home.jsp");
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
	
	$('#show-password').click(function(){
		if($('.password').attr("type") == 'text'){
			$('.password')[0].type = 'password';
			$('#show-password')[0].className = "fas fa-eye";
		}else{
			$('.password')[0].type = 'text';
			$('#show-password')[0].className = "fas fa-eye-slash";
		}
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
						$('#posts').css({"display": "block"});
						$('#add').css({"display": "block"});
						$('.homepage').css({"visibility":"hidden"});
						$('.menu').css({"visibility":"visible"});
					}else{	
						$('.log-fail').html("Wrong User or Password");
						$('.log-fail').css({"font-size":"25px"});
					}
		});		
	});
	
	$('.filter-btn').click(function(){
		alert($("#1st").is(':checked'));
		$.post('./js/filter.jsp', 
				{
					s1: $("#1st").is(':checked'),
					s2: $("#2st").is(':checked'),
					s3: $("#3st").is(':checked'),
					s4: $("#4st").is(':checked'),
					s5: $("#5st").is(':checked'),
					wifi: $("#wi-fi").is(':checked'),
					parking: $("#parking").is(':checked'),
					beach: $("#beach").is(':checked'),
					forest: $("#forest").is(':checked'),
				}, 
				function(response) {
					location.reload(true);
		});	
	});
	

});
