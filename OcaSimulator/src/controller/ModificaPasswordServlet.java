package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daofactory.DAOFactory;
import model.Utente;
import utility.Utilities;

/**
 * Servlet implementation class ModificaPasswordServlet
 */
@WebServlet("/modificapassword")
public class ModificaPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/modificapassword.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente u = (Utente) request.getSession().getAttribute("utente");
	
		String password = request.getParameter("nuova");
		String passwordOld = request.getParameter("vecchia");
		if (Utilities.validaPassword(password) && passwordOld.equalsIgnoreCase(u.getPassword())){
			u.setPassword(password);
			DAOFactory.getDAOFactory().getUtenteDAO().modificaPassword(u);
			request.setAttribute("messaggio", "la Password è stata cambiata effettua di nuovo il login");
			request.getRequestDispatcher("/").forward(request, response);
		} else {
			request.setAttribute("errore", "password non valida. La password deve contenere almeno 8 caratteri, di cui almeno uno speciale, uno maiuscolo e un numero");
			request.getRequestDispatcher("/WEB-INF/jsp/modificapassword.jsp").forward(request, response);
			
		}
		
		
	}

}
