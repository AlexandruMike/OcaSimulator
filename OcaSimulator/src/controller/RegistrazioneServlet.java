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
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/registrazione")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Utente u = Utilities.validaStudente(email,nome,cognome,password);
		if(u==null) {
			//Dati inseriti non validi
			request.setAttribute("invalid", "Dati inseriti non validi!");
			
			request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
		}else {
			if(DAOFactory.getDAOFactory().getUtenteDAO().aggiungiUtente(u)) {
				//TODO aggiunta a buon fine
				request.setAttribute("success", "Registrazione andata a buon fine!");
				
				
				request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
			}else {
				//email gia presente
				request.setAttribute("present", "Email già presente!");
				
				request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
			}
		}

	}

}
