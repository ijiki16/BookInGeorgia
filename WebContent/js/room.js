/**
 * 
 */

$(document).ready(function(){
	  var val;
	  $(".button").click(function (event) {
		  event.stopPropagation();
		  event.stopImmediatePropagation();
		  val = $(this).val();
		  var sdate = new Date($('#sd' + val).val());
		  var edate = new Date($('#ed' + val).val());
		  sday = sdate.getDate();
		  smonth = sdate.getMonth() + 1;
		  syear = sdate.getFullYear();
		  eday = edate.getDate();
		  emonth = edate.getMonth() + 1;
		  eyear = edate.getFullYear();
		  
		  $.post('Book', 
					{
			  			sday: sday,
			  			smonth: smonth,
			  			syear: syear,
			  			eday: eday,
			  			emonth: emonth,
			  			eyear: eyear,
			  			roomid: val
					}, 
					function(response) {
						if($.trim(response) == 'success') {
							$("#" + val).addClass("onclic", 250, validate);
						}else{
							$("#" + val).addClass("onclic", 250, reject);
						}
			});
	  });
	  
	  function validate() {
	    setTimeout(function () {
	      $("#" + val).removeClass("onclic");
	      $("#" + val).addClass("validate", 450, callback);
	    }, 2250);
	  }
	  
	  function reject() {
		    setTimeout(function () {
		      $("#" + val).removeClass("onclic");
		      $("#" + val).addClass("reject", 450, callback);
		    }, 2250);
		  }
	  
	  function callback() {
	    setTimeout(function () {
	      $("#" + val).removeClass("validate");
	      $("#" + val).removeClass("reject");
	    }, 1250);
	  }
		
});

