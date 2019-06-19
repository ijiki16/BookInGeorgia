/**
 * 
 */

$(document).ready(function(){
	$('.butt').click(function() {
		let _firstname = $('#firstname').val();
		let _lastname = $('#lastname').val();
		let _email = $('#email').val();
		let _user = $('#user').val();
		let _password = $('#password').val();
		let _rpassword = $('#rep-password').val();
		let _bdate = $('#bd-month').val() + $('#bd-day').val() + $('#bd-year').val();
		if(_password == _rpassword){
			$.post('./js/registering.jsp', 
					{
						firstname: _firstname,
						lastname: _lastname,
						email: _email,
						user: _user,
						password: _password,
						bdate: _bdate,
					}, 
					function(response) {
						if(response) {
							alert(_bdate);
							alert(response);
							
						}
			});
		}else{
			alert("failed");
		}
	});

});