/**
 * 
 */

$(document).ready(function(){
	$('#save').click(function(){
		let _firstname = $('#firstname').val();
		let _lastname = $('#lastname').val();
		let _email = $('#email').val();
		let _user = $('#user').val();
		let _password = $('#password').val();
		alert("edit");
		$.post('./js/editprofile.jsp', 
				{
					firstname: _firstname,
					lastname: _lastname,
					email: _email,
					user: _user,
					password: _password,
				}, 
				function(response) {
					alert(response);
				});
	});
	
	$('#show-password').click(function(){
		if($('#password').attr("type") == 'text'){
			$('#password')[0].type = 'password';
			$('#show-password')[0].className = "fas fa-eye";
		}else{
			$('#password')[0].type = 'text';
			$('#show-password')[0].className = "fas fa-eye-slash";
		}
	});
});