/**
 * 
 */
$(document).ready(function() {
	$('#save').click(function() {
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
		$.post('./js/addHotel.jsp', {
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
		}, function(response) {
			$('#next').css({
				"display" : "block"
			});
		});
	});
	
	$('#chooseFile').bind('change', function() {
		var filename = $("#chooseFile").val();
		if (/^\s*$/.test(filename)) {
			$(".image-upload").removeClass('active');
			$("#noFile").text("No file chosen...");
		} else {
			$(".image-upload").addClass('active');
			$("#noFile").text(filename.replace("C:\\fakepath\\", ""));
		}
	});

});