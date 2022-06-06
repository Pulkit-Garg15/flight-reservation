<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Complete Reservation</title>
</head>
<body>
	<h2>Complete Reservation</h2>
	Airline: ${flight.operatingAirlines}
	<br /> Departure City: ${flight.departureCity}
	<br /> Arrival City: ${flight.arrivalCity}
	<br /> Date of Departure: ${flight.dateOfDeparture}
	<br />

	<form action="completeReservation" method="post">
		<pre>
<h3>Passenger Details:</h3>
First name: <input type="text" name="passengerFirstName" />
Last name: <input type="text" name="passengerLastName" />
Email: <input type="text" name="passengerEmail" />
Phone number: <input type="text" name="passengerPhone" />

<h3>Card Details:</h3>
Name on the card: <input type="text" name="nameOnTheCard" />
Card Number: <input type="text" name="cardNumber" />
Expiry Date: <input type="text" name="expirationDate" />
Three Digit Security Code: <input type="text" name="securityCode" />
<input type="hidden" name="flightId" value="${flight.id}">
<input type="submit" value="confirm" />
</pre>
	</form>
</body>
</html>