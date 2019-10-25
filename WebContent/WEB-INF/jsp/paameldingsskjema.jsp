<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<title>Påmelding</title>
<script  src="js/Validation.js"></script>
</head>
<body>
	<h2>Påmelding</h2>
	<p>
		<font color="red">${FeilmeldingString}</font>
	</p>
	<form method="post" class="pure-form pure-form-aligned">
		<fieldset>
			<div class="pure-control-group">
				<label for="fornavn">Fornavn:</label> <input type="text"
				name="fornavn" value="" /> 
<!-- 				 <font color="red">Ugyldig fornavn</font> -->
			</div>
			<div class="pure-control-group">
				<label for="etternavn">Etternavn:</label> <input type="text"
				name="etternavn" value="" /> 
<!-- 				<font color="red">Ugyldig etternavn</font> -->
			</div>
			<div class="pure-control-group">
				<label for="mobil">Mobil (8 siffer):</label> <input type="number"
				name="mobil" value="" /> 
<!-- 				<font color="red">Ugyldig mobil</font> -->
			</div>
			<div class="pure-control-group">
				<label for="password">Passord:</label> <input type="password"
				name="passord" value="" /> 
<!-- 				<font color="red">Ugyldig passord</font> -->
			</div>
			<div class="pure-control-group">
				<label for="passordRepetert">Passord repetert:</label> <input
				type="password" name="passordRepetert"
				value="" /> 
<!-- 				<font color="red">Passordene må være like</font> -->
			</div>
			<div class="pure-control-group">
				<label for="kjonn">Kjønn:</label> <input type="radio" name="kjonn"
				value="mann"
				/>mann
				<input type="radio" name="kjonn" value="kvinne"
				/>kvinne
<!-- 				<font color="red">Du må oppgi kjonn</font> -->
			</div>
			<div class="pure-controls">
				<button type="submit" class="pure-button pure-button-primary">Meld
				meg på</button>
			</div>
		</fieldset>
	</form>
	<p>Allerede medlem? Logg inn <a href="InnloggingServlet">her</a></p>
	<script>
	const fornavn = document.getElementsByName('fornavn')[0];
	fornavn.addEventListener('keyup', function (event) {
		validateName(fornavn);
	});
	
	const etternavn = document.getElementsByName('etternavn')[0];
	etternavn.addEventListener('keyup', function (event) {
		validateName(etternavn);
	});
	
	const mobil = document.getElementsByName('mobil')[0];
	mobil.addEventListener('keyup', function (event) {
		validateMB(mobil);
	});
	const passord = document.getElementsByName('passord')[0];
	const passordRepetert =document.getElementsByName('passordRepetert')[0];
	passord.addEventListener('keyup', function (event) {
		validatePass(passord);
		validatePassRep(passord,passordRepetert)
	});
	passordRepetert.addEventListener('keyup', function (event) {
		validatePassRep(passord,passordRepetert)
	});
	</script>
</body>
</html>