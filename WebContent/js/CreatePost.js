/**
 * 
 */
$(document).ready(function(){
	$('#sub').click(function() {
		let name = $('#name').val();
		let stars = $('#stars').val();
		let status = $('#status').val();
		let phone = $('#phone').val();
		let city = $('#city').val();
		let street = $('#street').val();
		let image = $('#image').val();
		let wifi = $('#wi-fi').val();
		let parking = $('#parking').val();
		let beach = $('#beach').val();
		let forest = $('#forest').val();
		let facility = $('#facility').val();
		
		
		$.post('./js/addHotel.jsp', 
				{
			name : name,
			stars : stars,
			status : status,
			phone : phone,
			city : city,
			street : street,
			image : image,
			wifi : wifi,
			parking : parking,
			beach : beach,
			forest : forest,
			facility : facility
				}, 
				function(response) {
					if($.trim(response) == 'success') {
						$('#log-and-reg').css({"visibility":"hidden"});
						$('.menu-but').css({"display": "block"});	
						window.location.href = 'home.jsp'; 
					}else{
						$('.reg-fail').html("Account already exists!");
					}
		});
	});

});