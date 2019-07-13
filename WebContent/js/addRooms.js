
$(document).ready(function() {

	$('#save2').click(function() {
		let image = $('#image').val();
		let tv = $("#tv").is(':checked');
		let wifi = $('#wifi').is(':checked');
		let hotWater = $('#hotWater').is(':checked');
		let airCo = $('#airCo').is(':checked');
		let id = $('#hotel_id').val();
		let numBeds = $('#numBeds').val();
		let sDate = $('#sDate').val();
		let eDate = $('#eDate').val();
		let rPrice = $('#rPrice').val();

		$.post('addRoom', {
			tv2 : tv,
			hotWater2 : hotWater,
			airCo2 : airCo,
			numBeds2 : numBeds,
			sDate2 : sDate,
			eDate2 : eDate,
			image2 : image,
			wifi2 : wifi,
			rPrice2 : rPrice,
			hotel_id : id
		}, function(response) {
			if($.trim(response) == 'fail'){
				
			} else {
				$('#save2').css({
					"display" : "none"
				});
				$('#next2').css({
					"display" : "block"
				});
			}
		});
	});
	
	$('#chooseFile2').bind('change', function() {
		var filename = $("#chooseFile2").val();
		if (/^\s*$/.test(filename)) {
			$(".image-upload").removeClass('active');
			$("#noFile").text("No file chosen...");
		} else {
			$(".image-upload").addClass('active');
			$("#noFile").text(filename.replace("C:\\fakepath\\", ""));
		}
	});

});