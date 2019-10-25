package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FerdigServlet")
public class FerdigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		SJEKK OM BRUKER ER INNLOGGET
		if (!InnloggingUtil.isInnlogget(request)) {
			response.sendRedirect("PaameldingServlet?feilmelding=1");
			return;
		}
		
//		LOGG UT BRUKER OG VIS SIDE
		InnloggingUtil.loggUt(request);	
		request.getRequestDispatcher("WEB-INF/jsp/ferdig.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
