<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Card to account</title>
<link rel="stylesheet" href="css/Visa.css">
</head>
<body>

	<div class="checkout">
  		<h1> Checkout </h1>
		  <form action="payment" method="post">
				<input type="text" placeholder="Card Number" name="cardnumber" value=""> <br>	 
				<input type="text" placeholder="Name on card" name="nameoncard" value="">
				
				<div class="half">  
					<input type="text" placeholder="MM/YY" name="mmyy" value="">
					<input type="text" placeholder="CVC" name="cvc" value=""> <img src="images/visa.png" ></img>
				</div>
				<button type="submit"> Book Now</button>
		  </form>
	</div>
</body>
</html>