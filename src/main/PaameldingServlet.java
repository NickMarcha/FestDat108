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
//			FYLL OPP JSP BASERT PÃ… FEILMELDINGER
			request.setAttribute("FeilmeldingString", "Alle felter må være gyldig fyllt inn");
		}

		

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
		if (Validering.gyldigDeltager(fornavn, etternavn, mobil, passordTekst, kjonn) || deltagerEAO.deltagerFinnes(mobil)) {
			response.sendRedirect("PaameldingServlet?feilmelding=1");
		}
		
		else {
			
//			FULLFÃ˜R INNLEGGELSE OG GÃ… TIL BEKREFTELSE
		
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
	
}
