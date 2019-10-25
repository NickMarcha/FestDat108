<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<title>P�meldingsbekreftelse</title>
</head>
<body>
	<h2>P�meldingsbekreftelse</h2>
	<p>P�meldingen er mottatt for</p>
	<p>
		&nbsp;&nbsp;&nbsp;${delt.getFornavn()}<br />
		&nbsp;&nbsp;&nbsp;${delt.getEtternavn()}<br />
		&nbsp;&nbsp;&nbsp;${delt.getMobil()}<br /> &nbsp;&nbsp;&nbsp;${delt.getKjonn()}
	</p>
	<a href="DeltagerlisteServlet"">G� til deltagerlisten</a>
</body>
</html>