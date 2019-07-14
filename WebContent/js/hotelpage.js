/**
 * 
 */

$(document).ready(function(){
	  $(".add").click(function (event) {
		  alert("aeeee");
		  text = $('#mess').val();
		  $.post('Book', 
					{
			  			text: text
					}, 
					function(response) {
						
			});
		  $(".add").addClass("onclic", 250, validate);
	  });
	  
	  function validate() {
	    setTimeout(function () {
	      $(".add").removeClass("onclic");
	      $(".add").addClass("validate", 450, callback);
	    }, 2250);
	  }
	  
	  function callback() {
	    setTimeout(function () {
	      $(".add").removeClass("validate");
	    }, 1250);
	  }
});

