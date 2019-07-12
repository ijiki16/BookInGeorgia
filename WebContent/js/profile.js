/**
 * 
 */

$(document).ready(function(){

	$('#edit-prof').click(function(){
		$('.prof-box').css({"display": "block"});
		$('.resrv-box').css({"display": "none"});
		$('.post-box').css({"display": "none"});
		$('.posts').css({"visibility": "hidden"});
	});

	$('#edit-post').click(function(){
		$('.prof-box').css({"display": "none"});
		$('.resrv-box').css({"display": "none"});
		$('.post-box').css({"display": "none"});
		$('.posts').css({"visibility": "visible"});
	});

	$('#edit-resrv').click(function(){
		$('.prof-box').css({"display": "none"});
		$('.resrv-box').css({"display": "block"});
		$('.post-box').css({"display": "none"});
		$('.posts').css({"visibility": "hidden"});
	});
	
	
	$('#save-prof').click(function(){
		let _firstname = $('#firstname').val();
		let _lastname = $('#lastname').val();
		let _email = $('#email').val();
		let _user = $('#user').val();
		let _password = $('#password').val();
		$.post('EditProfile', 
				{
					firstname: _firstname,
					lastname: _lastname,
					email: _email,
					user: _user,
					password: _password,
				}, 
				function(response) {
					$('.prof-box').css({"display": "none"});
				});
	});
	
	$('#save-post').click(function(){
		let _name = $('#hotelname').val();
		let _rating = $('#hotelrating').val();
		let _status = $('#hotelstatus').val();
		let _number = $('#hotelnumber').val();
		let _city = $('#city').val();
		let _address = $('#address').val();
		let _hotel_id = $('#hotel_id').val();
		$.post('EditPost', 
				{
					name:_name,
					rating:_rating,
					status:_status,
					number:_number,
					city:_city,
					address:_address,
					hotel_id: _hotel_id
				}, 
				function(response) {
					$('.post-box').css({"display": "none"});
				});
	});
	
	$('#show-password').click(function(){
		if($('#password').attr("type") == 'text'){
			$('#password')[0].type = 'password';
			$('#show-password')[0].className = "fas fa-eye-slash";
		}else{
			$('#password')[0].type = 'text';
			$('#show-password')[0].className = "fas fa-eye";
		}
	});
});