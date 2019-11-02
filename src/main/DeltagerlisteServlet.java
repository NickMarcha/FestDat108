package main;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeltagerlisteServlet")
public class DeltagerlisteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	DeltagerEAO deltagerEAO;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		SJEKK OM BRUKER ER INNLOGGET
		if (!InnloggingUtil.isInnlogget(request)) {
			response.sendRedirect("PaameldingServlet?feilmelding=1");
			return;
		}
//		FINN BRUKER OG SETT ATTRIBUTT
		String brukerId = (String)request.getSession().getAttribute("BrukerId");
		Deltager deltager = deltagerEAO.getDeltager(brukerId);
		request.setAttribute("delt", deltager);
		
//		FINN RESTEN AV BRUKERENE
		List<Deltager> deltagere = deltagerEAO.getDeltagere();
		
//		SORTER BRUKERENE
		//first name
		Comparator<Deltager> compareByFirstName = Comparator.comparing(Deltager::getFornavnLower);
		//last name
		Comparator<Deltager> compareByLastName = Comparator.comparing(Deltager::getEtternavnLower);
		//compare by first then last
		Comparator<Deltager> compareByFullname = compareByLastName.thenComparing(compareByFirstName);
		
		Collections.sort(deltagere, compareByFullname);
		
		request.setAttribute("deltagere", deltagere);

//		SEND JSP
		request.getRequestDispatcher("WEB-INF/jsp/deltagerliste.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
