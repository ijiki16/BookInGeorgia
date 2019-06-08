/**
 * 
 */

$(document).ready(function(){
	$('#l').click(function(){
		var clicks = $(this).data('clicks');
		if (clicks) {
			
		} else {
			$(".log").css({
				'display': 'none'
			});
			$(".flaticon-profile").css({
				'display': 'none'
			});
			$(".menu-but").css({
				'display': 'block',
			}); 
		}
		$(this).data("clicks", !clicks);
		
	});
});
