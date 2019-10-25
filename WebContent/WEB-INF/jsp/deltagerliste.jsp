<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<title>Deltagerliste</title>
</head>
<body>
	<h2>Deltagerliste</h2>
	<table class="pure-table">
		<tr bgcolor="#cccccc">
			<th>Kjønn</th>
			<th align="left">Navn</th>
			<th align="left">Mobil</th>
		</tr>
		<!-- DELTAGERLISTE -->
		<c:forEach items="${deltagere}" var="d">
		<tr bgcolor="${d.getMobil() == delt.getMobil()? "#aaffaa":"#ffffff"}">
			<td align="center">${d.getKjonn() == "mann" ? "&#9792":"&#9794"};</td>
			<td>${d.getFornavn()} ${d.getEtternavn()}</td>
			<td>${d.getMobil()}</td>
		</tr>
		</c:forEach>
	</table>
	<p>
		<a href="FerdigServlet">Ferdig</a>
	</p>
</body>
</html>