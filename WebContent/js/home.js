/**
 * 
 */

$(document).ready(function(){
	$.post('Session', 
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
		$.post('Session', 
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
	
	$('#about').click(function(){
			$('.hotels').css({
				"display" : "none"
			});
			$('.about').css({
				"display" : "block"
			});
	});
	
	$('.log').click(function() {
		$('.moto').css({"visibility":"hidden"});
		$('.log-in').css({"visibility":"visible"});
		$('.homepage').css({"visibility":"visible"});
	});
	
	$('#close-moto').click(function() {
		$('.moto').css({"visibility":"hidden"});
	});
	
	$('#close').click(function() {
		$('.log-in').css({"visibility":"hidden"});
		$('.homepage').css({"visibility":"hidden"});
		$('.user').val("");
		$('.password').val("");
	});
	
	$('.go-to-reg').click(function() {
		$('.moto').css({"visibility":"hidden"});
		$('.log-in').css({"visibility":"hidden"});
		$('.register-box').css({"visibility":"visible"});
	});
	
	$('#show-password').click(function(){
		alert("aee");
		if($('.password').attr("type") == 'text'){
			$('.password')[0].type = 'password';
			$('#show-password')[0].className = "fas fa-eye-slash";
		}else{
			$('.password')[0].type = 'text';
			$('#show-password')[0].className = "fas fa-eye";
		}
	});
	
	$('.log-btn').click(function(){
		$('.moto').css({"visibility":"hidden"});
		let _user = $('.user').val();
		let _password = $('.password').val();
		$('.user').val("");
		$('.password').val("");
		$.post('Login', 
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
	
	$("#search").click(function(){
		$('.moto').css({"visibility":"hidden"});
		$.post('./js/search.jsp',
				{
					option: $("exampleFormControlSelect1").val(),
					hotel_name : $("exampleFormControlSelect1").val()
				},
				function(response){
					location.reload(true);
				}	
		);
	});
	
	$('.filter-btn').click(function(){
		$('.moto').css({"visibility":"hidden"});
		alert($("#1st").is(':checked'));
		$.post('Filter', 
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
