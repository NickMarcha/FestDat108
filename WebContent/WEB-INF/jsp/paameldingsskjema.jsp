<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" />
<link rel="stylesheet" type="text/css" href="css/mystyle.css" />
<title>Fest|Påmelding</title>
<!-- Brukes til validering av brukerinput -->
<script src="js/Validation.js"></script>
</head>
<body>
	<h2>Påmelding</h2>
	<p>
		<!-- 		Vil være tom om ingen feilmelding -->
		<font color="red" id="fm"> ${FeilmeldingString}</font>
	</p>
	<form method="post" class="pure-form pure-form-aligned"
		name="submitForm">
		<fieldset>
			<div class="pure-control-group" class="tooltip">
				<label for="fornavn">Fornavn:</label> <input type="text"
					name="fornavn" value="${feilmldr[0]}"></input>
				<div class="tooltip">
					?<span class="tooltiptext">Tooltip text</span>
				</div>
				<font color="red">${feilmldr[1].equals("1")?"Ugyldig fornavn":""}</font>
			</div>
			<div class="pure-control-group">
				<label for="etternavn">Etternavn:</label> <input type="text"
					name="etternavn" value="${feilmldr[2]}" />
				<div class="tooltip">
					?<span class="tooltiptext">Tooltip text</span>
				</div>
				<font color="red">${feilmldr[3].equals("1")?"Ugyldig etternavn":""}</font>
			</div>
			<div class="pure-control-group">
				<label for="mobil">Mobil (8 siffer):</label> <input type="number"
					name="mobil" value="${feilmldr[4]}" />
				<div class="tooltip">
					?<span class="tooltiptext">Tooltip text</span>
				</div>
				<font color="red">${feilmldr[5].equals("1")?"Ugyldig mobil":""}</font>
			</div>
			<div class="pure-control-group">
				<label for="password">Passord:</label> <input type="password"
					name="passord" value="" />
				<div class="tooltip">
					?<span class="tooltiptext">Tooltip text</span>
				</div>
				<!-- 				<font color="red">Ugyldig passord</font> -->
			</div>
			<div class="pure-control-group">
				<label for="passordRepetert">Passord repetert:</label> <input
					type="password" name="passordRepetert" value="" />
				<div class="tooltip">
					?<span class="tooltiptext">Tooltip text</span>
				</div>
				<!-- 				<font color="red">Passordene må være like</font> -->
			</div>
			<div class="pure-control-group">
				<label for="kjonn">Kjønn:</label> <input type="radio" name="kjonn"
					value="mann" ${feilmldr[6]=="mann"?"checked":""} />mann <input
					type="radio" name="kjonn" value="kvinne"
					${feilmldr[6]=="kvinne"?"checked":""} />kvinne
				<div class="tooltip">
					?<span class="tooltiptext">Tooltip text</span>
				</div>
				<font color="red">${feilmldr[7].equals("1")?"Du må oppgi kjonn":""}</font>
			</div>
			<div class="pure-controls">
				<button type="button" class="pure-button pure-button-primary"
					onclick="ValidateAll(document.getElementsByName('submitForm')[0])">Meld
					meg på</button>

			</div>
		</fieldset>
	</form>
	<p>
		Allerede medlem? Logg inn <a href="InnloggingServlet">her</a>
	</p>
	<script>
	
		//JavaScript/clientside Validering for Input
		//bruker hjelpejs filen Validation.js
		AddValidationListener(document.getElementsByName('fornavn')[0],"Navn");
		//bruker keyup, kjøres først etter noe er blitt tastet inn gjeldende felt
		AddValidationListener(document.getElementsByName('etternavn')[0],"Navn");
		AddValidationListener( document.getElementsByName('mobil')[0],"Tlf");
		AddValidationListener(
				[
						document.getElementsByName('passord')[0],
						document.getElementsByName('passordRepetert')[0]
				],"PassordCombo");			
		AddValidationListener("kjonn","Kjonn");
	</script>
</body>
</html>