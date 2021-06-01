/**
 * 
 */

$(document).ready(function(){
	  $(".add").click(function (event) {
		  text = $('#mess').val();
		  id = $('#hotId').val();
		  $.post('js/addComment.jsp', 
					{
			  			id: id,
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
	      location.reload(true);
	    }, 1250);
	  }
});

