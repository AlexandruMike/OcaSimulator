package controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daofactory.DAOFactory;
import model.Utente;

/**
 * Servlet implementation class VisualizzaDipendenti
 */
@WebServlet("/visualizzadipendenti")
public class VisualizzaDipendenti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaDipendenti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//chiamo il metodo che mi prende la lista dei dipendenti
		List<Utente> listaDipendenti =DAOFactory.getDAOFactory().getUtenteDAO().selezionaDipendenti();
		//ci faccio il setattribute
		request.setAttribute("lista", listaDipendenti);
		//lo mando alla jsp
		request.getRequestDispatcher("/WEB-INF/jsp/visualizzadipendenti.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//faccio il getparameter dell'info presa dalla jsp
		int id=0;
		String idGet=request.getParameter("utente");
		try {
			id=Integer.parseInt(idGet);
		} catch (Exception e) {
		
		}
		Utente utente =new Utente(id);
		//chiamare il metodo del DB per "eliminare" il dipendente selezionato
		DAOFactory.getDAOFactory().getUtenteDAO().eliminaDipendente(utente);
		response.sendRedirect("visualizzadipendenti");
	
	}

}
