/**
 * 
 */

$(document).ready(function(){
	$('.reg-btn').click(function() {
		let _firstname = $('#firstname').val();
		let _lastname = $('#lastname').val();
		let _email = $('#email').val();
		let _user = $('#user').val();
		let _password = $('#password').val();
		let _rpassword = $('#rep-password').val();
		let _year = $('#bd-year').val();
		let _month = $('#bd-month').val();
		let _day = $('#bd-day').val();
		let _bdate = _year + '-' + _month + '-' + _day;
		
		if(_email == ""){
            $('.reg-fail').html("Email is required!");
            return;
        }
		
		if(_user == ""){
            $('.reg-fail').html("Username is required!");
            return;
        }
		
		if(_password == ""){
            $('.reg-fail').html("Password is required!");
            return;
        }
		
		if(_year == "" || _month == "" || _day == ""){
            $('.reg-fail').html("Birth Date is required!");
            return;
        }
		
		if(_password == _rpassword){
			$.post('Register', 
					{
						firstname: _firstname,
						lastname: _lastname,
						email: _email,
						user: _user,
						password: _password,
						bdate: _bdate,
					}, 
					function(response) {
						if($.trim(response) == 'success') {
							$('#log-and-reg').css({"visibility":"hidden"});
							$('.menu-but').css({"display": "block"});
							sleep(1000);
							window.location.href = 'home.jsp'; 
						}else{
							$('.reg-fail').html("Account already exists!");
						}
			});
		}else{
			$('.reg-fail').html("Doesn't match passwords");
		}
	});

});

