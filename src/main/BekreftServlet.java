package main;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BekreftelseServlet
 */
@WebServlet("/BekreftServlet")
public class BekreftServlet extends HttpServlet {
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
		
//		SEND JSP
		request.getRequestDispatcher("WEB-INF/jsp/paameldingsbekreftelse.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

