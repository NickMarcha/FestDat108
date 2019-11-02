package main;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/InnloggingServlet")
public class InnloggingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	DeltagerEAO deltagerEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//		SJEKK OM BRUKER ER INNLOGGET
		if (InnloggingUtil.isInnlogget(request)) {
			response.sendRedirect("DeltagerlisteServlet");
			return;
		}

		//		GET FEILMELDING
		int feilmelding = 0;
		if (request.getParameter("feilmelding") != null) {
			feilmelding = Integer.parseInt(request.getParameter("feilmelding"));
		}

		//		FEILMELDINGSTEST
		if (feilmelding == 1) {
			//			FYLL OPP JSP BASERT PÅ FEILMELDINGER
			request.setAttribute("FeilmeldingString", "Ugyldig brukernavn og/eller passord");
		}


		request.getRequestDispatcher("WEB-INF/jsp/logginn.jsp").forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//		HENT DATA FRA WEB FORM
		String mobil = request.getParameter("mobil");
		String passordTekst = request.getParameter("passord");
		

		if(Validering.ValiderMobil(mobil) &&Validering.ValiderPassord(passordTekst) && deltagerEAO.deltagerFinnes(mobil)) {
			System.out.println("Fant Bruker");
			Hashing ph = new Hashing(Hashing.SHA256);
			Deltager delt = deltagerEAO.getDeltager(mobil);

			try {
				
				if(ph.validatePasswordWithSalt(passordTekst, delt.getSalt(), delt.getPassord().getPassord())) {
					System.out.println("Riktig Passord");
					InnloggingUtil.loggInnMedTimeout(request, Integer.parseInt(getServletContext().getInitParameter("timeout")));
					request.getSession().setAttribute("BrukerId", mobil);
					response.sendRedirect("DeltagerlisteServlet");
					return;
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(NullPointerException e) {
				e.printStackTrace();
			}

		}
		response.sendRedirect("InnloggingServlet?feilmelding=1");
		return;

	}

}
