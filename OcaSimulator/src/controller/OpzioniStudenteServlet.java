package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OpzioniStudenteServlet
 */
@WebServlet("/opzionistudente")
public class OpzioniStudenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpzioniStudenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		if(name!=null && name.equalsIgnoreCase("storico") ) {
			response.sendRedirect("visualizzastorico");
		}else if(name!=null && name.equalsIgnoreCase("modifica") ) {
			request.getRequestDispatcher("/WEB-INF/jsp/modificapassword.jsp").forward(request, response);
		}else if(name!=null && name.equalsIgnoreCase("test")&& name !=null) {
			request.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/WEB-INF/jsp/studente.jsp").forward(request, response);
		}//-------------------CAMBIATO---------------------------
	}

}
