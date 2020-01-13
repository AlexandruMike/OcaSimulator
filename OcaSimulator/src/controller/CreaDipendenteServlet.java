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
 * Servlet implementation class CreaDipendenteServlet
 */
@WebServlet("/creadipendente")
public class CreaDipendenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaDipendenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/jsp/creadipendente.jsp").forward(request, response);			

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nomedipendente");
		String cognome = request.getParameter("cognomedipendente");
		String email = request.getParameter("emaildipendente");
		Utente u = Utilities.validaDipendente(nome,cognome,email);
		if (u == null) {
			request.getRequestDispatcher("/WEB-INF/jsp/creadipendente.jsp").forward(request, response);			
		}else if(DAOFactory.getDAOFactory().getUtenteDAO().aggiungiUtente(u)){
				//inserimento nel database dei dipendenti
				Utilities.inviaEmail(u);
			response.sendRedirect("visualizzadipendenti");
		}else {
			request.getRequestDispatcher("/WEB-INF/jsp/creadipendente.jsp").forward(request, response);	
		}
	}
}


