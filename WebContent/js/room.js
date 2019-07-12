/**
 * 
 */

$(document).ready(function(){
	  $("#button").click(function () {
		  var sdate = new Date($('#sd').val());
		  var edate = new Date($('#ed').val());
		  sday = sdate.getDate();
		  smonth = sdate.getMonth() + 1;
		  syear = sdate.getFullYear();
		  eday = edate.getDate();
		  emonth = edate.getMonth() + 1;
		  eyear = edate.getFullYear();
		  roomid = $('#roomid').val();
		  $.post('Book', 
					{
			  			sday: sday,
			  			smonth: smonth,
			  			syear: syear,
			  			eday: eday,
			  			emonth: emonth,
			  			eyear: eyear,
			  			roomid: roomid
					}, 
					function(response) {
						if($.trim(response) == 'success') {
							$("#button").addClass("onclic", 250, validate);
						}else{
							$("#button").addClass("onclic", 250, reject);
						}
			});
	  });
	  
	  function validate() {
	    setTimeout(function () {
	      $("#button").removeClass("onclic");
	      $("#button").addClass("validate", 450, callback);
	    }, 2250);
	  }
	  
	  function reject() {
		    setTimeout(function () {
		      $("#button").removeClass("onclic");
		      $("#button").addClass("reject", 450, callback);
		    }, 2250);
		  }
	  
	  function callback() {
	    setTimeout(function () {
	      $("#button").removeClass("validate");
	      $("#button").removeClass("reject");
	    }, 1250);
	  }
		
});

