/**
 * 
 */

$(document).ready(function(){

	$('#edit-prof').click(function(){
		$('.prof-box').css({"display": "block"});
		$('.reserv').css({"visibility": "hidden"});
		$('.post-box').css({"display": "none"});
		$('.reserv-box').css({"display": "none"});
		$('.posts').css({"visibility": "hidden"});
		$('.room-box').css({"display": "none"});
		$('.rooms').css({"visibility": "hidden"});
	});

	$('#edit-post').click(function(){
		$('.prof-box').css({"display": "none"});
		$('.reserv').css({"visibility": "hidden"});
		$('.post-box').css({"display": "none"});
		$('.reserv-box').css({"display": "none"});
		$('.posts').css({"visibility": "visible"});
		$('.room-box').css({"display": "none"});
		$('.rooms').css({"visibility": "hidden"});
	});

	$('#edit-resrv').click(function(){
		$('.prof-box').css({"display": "none"});
		$('.reserv').css({"visibility": "visible"});
		$('.post-box').css({"display": "none"});
		$('.reserv-box').css({"display": "none"});
		$('.posts').css({"visibility": "hidden"});
		$('.room-box').css({"display": "none"});
		$('.rooms').css({"visibility": "hidden"});
	});
	
	
	$('#save-prof').click(function(){
		let _firstname = $('#firstname').val();
		let _lastname = $('#lastname').val();
		let _email = $('#email').val();
		let _user = $('#user').val();
		let _password = $('#pass').val();
		$.post('EditProfile', 
				{
					firstname: _firstname,
					lastname: _lastname,
					email: _email,
					user: _user,
					password: _password,
				}, 
				function(response) {
					location.reload(true);
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
					location.reload(true);
				});
	});
	
	$('#save-room').click(function(){
		alert("aeeeeeeeeee");
		let _tv = $("#edit-tv").is(':checked');
		let _wifi = $('#edit-wifi').is(':checked');
		let _hotWater = $('#edit-hotWater').is(':checked');
		let _airCo = $('#edit-airCo').is(':checked');
		let _numBeds = $('#edit-numBeds').val();
		let _sDate = $('#edit-sDate').val();
		let _eDate = $('#edit-eDate').val();
		let _rPrice = $('#edit-rPrice').val();
		let _hotel_id = $('#edit-hotel-id').val();
		let _room_id = $('#edit-room-id').val();
		alert(_tv);
		alert(_wifi);
		alert(_hotWater);
		alert(_airCo);
		alert(_numBeds);
		alert(_sDate);
		alert(_eDate);
		alert(_rPrice);
		alert(_hotel_id);
		alert(_room_id);
		$.post('EditRoom',
				{
					tv: _tv,
					wifi: _wifi,
					hotWater: _hotWater,
					airCo: _airCo,
					numBeds: _numBeds,
					rPrice: _rPrice,
					sDate: _sDate,
					eDate: _eDate,
					hotel_id: _hotel_id,
					room_id: _room_id,
				}, 
				function(response) {
					location.reload(true);
				});
	});
	
	$('#delete-post').click(function(){
		let _hotel_id = $('#hotel_id').val();
		$.post('js/deletePost.jsp', 
				{
					hotel_id: _hotel_id
				}, 
				function(response) {
					if($.trim(response) == 'true'){
						window.location.replace("Profile.jsp");
					} else {
						$('#editInfo').text("Your hotel is booked!");
					}
				});
	});
	
	$('#show').click(function(){
		if($('#pass').attr("type") == 'text'){
			$('#pass')[0].type = 'password';
			$('#show')[0].className = "fas fa-eye-slash";
		}else{
			$('#pass')[0].type = 'text';
			$('#show')[0].className = "fas fa-eye";
		}
	});
	
	$("#del").click(function () {
		  val = $("#res_id").val();
		  $.post('js/deleteReservation.jsp', 
					{
			  			reserved_id: val
					}, 
					function(response) {
						window.location.replace("Profile.jsp");
			});
	  });
	
	$("#del-room").click(function () {
		  let room_id = $("#edit-room-id").val();
		  $.post('js/deleteRoom.jsp', 
					{
			  			room_id: room_id,
					}, 
					function(response) {
						if($.trim(response) == 'true'){
							window.location.replace("Profile.jsp");
						} else {
							$('#room-info').text("Your room is booked!");
						}
					});
	  });
	
	$("#edit-rooms").click(function () {
		$('.rooms').css({"visibility": "visible"});
		$('.post-box').css({"display": "none"});
	  });
	
});