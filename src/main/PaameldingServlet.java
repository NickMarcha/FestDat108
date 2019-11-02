package main;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

@WebServlet("/PaameldingServlet")
public class PaameldingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	DeltagerEAO deltagerEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//		GET FEILMELDING

		String[] feilmldr;
		int feilmelding = 0;
		feilmldr = (String[]) request.getSession().getAttribute("FeilInfoPaamelding");
		if(feilmldr != null ) {
			request.getSession().setAttribute("FeilInfoPaamelding", null);
			
			for(int i = 1; i < 8; i+=2) {
				feilmelding += Integer.parseInt(feilmldr[i]);
			}
			
		}else {
			
			feilmldr = new String[] {
					"","","","","","","",""
			};
			
		}


		//		FEILMELDINGSTEST
		if (feilmelding > 0) {
			//			FYLL OPP JSP BASERT PÅ FEILMELDINGER
			request.setAttribute("FeilmeldingString", "Alle felter må være gyldig fyllt inn");
			request.setAttribute("feilmldr", feilmldr);
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
		String passordTekstRep = request.getParameter("passordRepetert");
		String kjonn = request.getParameter("kjonn");



		//		"FORCER" STOR BOKSTAV P� F�RSTE BOKSTAV I FOR OG ETTERNAVN
		fornavn = Validering.ForceForbokstav(fornavn);
		etternavn = Validering.ForceForbokstav(etternavn);

		//		VIL IKKE GI FEEDBACK OM HVA SOM ER FEIL FOR SIKKERHET
		if (!Validering.gyldigDeltager(fornavn, etternavn, mobil, passordTekst, passordTekstRep, kjonn) || deltagerEAO.deltagerFinnes(mobil)) {
			request.getSession().setAttribute("FeilInfoPaamelding", paameldingInfo(fornavn, etternavn,mobil,kjonn));
			response.sendRedirect("PaameldingServlet");
		}

		else {

			// 			HASHING
			Hashing ph = new Hashing(Hashing.SHA256);
			byte[] salt = ph.getSalt();

			try {
				ph.generateHashWithSalt(passordTekst, salt);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String hashedpassword = ph.getPasswordHashinHex();

			String saltinhex = DatatypeConverter.printHexBinary(salt);


			//			FULLFØR INNLEGGELSE OG GÅ TIL BEKREFTELSE

			//			LAG DELTAGER OBJEKT
			Deltager deltager = new Deltager();
			deltager.setFornavn(fornavn);
			deltager.setEtternavn(etternavn);
			deltager.setMobil(mobil);
			deltager.setKjonn(kjonn);
			deltager.setSalt(saltinhex);

			//			LAG PASSORD OBJEKT
			Passord passord = new Passord();
			passord.setPassord(hashedpassword);

			//			LEGG DELTAGER INN VIA PASSORD
			passord.setDeltager(deltager);		
			deltagerEAO.leggTilPassord(passord);
			InnloggingUtil.loggInnMedTimeout(request, Integer.parseInt(getServletContext().getInitParameter("timeout")));
			request.getSession().setAttribute("BrukerId", mobil);
			response.sendRedirect("BekreftServlet");
		}
	}

	private String[] paameldingInfo(String fornavn, String etternavn, String mobil, String kjonn) {
		String[] info = new String[10];
		info[0] = fornavn;
		//1 om feil
		info[1] = Validering.ValiderNavn(fornavn)?"0":"1";

		info[2] = etternavn;
		//1 om feil
		info[3] = Validering.ValiderNavn(etternavn)?"0":"1";

		info[4] = mobil;
		//1 om feil
		info[5] = Validering.ValiderMobil(mobil)?"0":"1";

		info[6] = kjonn;
		//1 om feil
		info[7] = Validering.ValiderKjonn(kjonn)?"0":"1";

		return info;
	}
}
