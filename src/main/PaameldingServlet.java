package main;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PaameldingServlet")
public class PaameldingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	DeltagerEAO deltagerEAO;
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		GET FEILMELDING
		int feilmelding = 0;
		if (request.getParameter("feilmelding") != null) {
			feilmelding = Integer.parseInt(request.getParameter("feilmelding"));
		}
		
//		FEILMELDINGSTEST
		if (feilmelding == 1) {
			System.out.println("UGYLDIG");
		}
//		FYLL OPP JSP BASERT PÅ FEILMELDINGER

//		SEND JSP
		request.getRequestDispatcher("WEB-INF/jsp/paameldingsskjema.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		HENT DATA FRA WEB FORM
		String fornavn = request.getParameter("fornavn");
		String etternavn = request.getParameter("etternavn");
		String mobil = request.getParameter("mobil");
		String passordTekst = request.getParameter("passord");
		String kjonn = request.getParameter("kjonn");
		
//		VIL IKKE GI FEEDBACK OM HVA SOM ER FEIL FOR SIKKERHET
		if (!gyldigDeltager(fornavn, etternavn, mobil, passordTekst, kjonn) || deltagerEAO.deltagerFinnes(mobil)) {
			response.sendRedirect("PaameldingServlet?feilmelding=1");
		}
		
		else {
			
//			FULLFØR INNLEGGELSE OG GÅ TIL BEKREFTELSE
		
//			LAG DELTAGER OBJEKT
			Deltager deltager = new Deltager();
			deltager.setFornavn(fornavn);
			deltager.setEtternavn(etternavn);
			deltager.setMobil(mobil);
			deltager.setKjonn(kjonn);
					
//			LAG PASSORD OBJEKT
			Passord passord = new Passord();
			passord.setPassord(passordTekst);
			
//			LEGG DELTAGER INN VIA PASSORD
			passord.setDeltager(deltager);		
			deltagerEAO.leggTilPassord(passord);
			InnloggingUtil.loggInnMedTimeout(request, Integer.parseInt(getServletContext().getInitParameter("timeout")));
			request.getSession().setAttribute("BrukerId", mobil);
			response.sendRedirect("BekreftServlet");
		}
	}
	
	private boolean gyldigDeltager(String fornavn, String etternavn, String mobil, String passord, String kjonn) {
		if (fornavn.isEmpty() ||
			etternavn.isEmpty() ||
			mobil.isEmpty() ||
			passord.isEmpty() ||
			kjonn.isEmpty() ||
			fornavn.length() > 30 ||
			etternavn.length() > 30 ||
			mobil.length() != 8 ||
			passord.length() > 50
			) {
			return false;
		}
		else {
			return true;
		}
	}
}
