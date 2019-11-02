<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
	<link rel="stylesheet" type="text/css" href="css/mystyle.css" />
<title>Fest|Logg inn</title>
<script src="js/Validation.js"></script>
</head>
<body>
	<h2>Logg inn</h2>
	<p>Det er kun registrerte deltagere som f�r se deltagerlisten.</p>
	<p>
		<!-- 		Vil v�re tom om ingen feilmelding -->
		<font color="red">${FeilmeldingString}</font>
	</p>
	<form method="post" class="pure-form pure-form-aligned">
		<fieldset>
			<div class="pure-control-group">
				<label for="mobil">Mobil:</label> <input type="text" name="mobil" />
			</div>
			<div class="pure-control-group">
				<label for="passord">Passord:</label> <input type="password"
					name="passord" />
			</div>
			<div class="pure-controls">
				<button type="submit" class="pure-button pure-button-primary">Logg
					inn</button>
			</div>
		</fieldset>
	</form>
	<p>
		Ikke registrert? G� til p�meldingsskjemaet <a href="PaameldingServlet">her</a>
	</p>

</body>
</html>