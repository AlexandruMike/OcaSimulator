package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daofactory.DAOFactory;
import model.Test;
import model.Utente;

/**
 * Servlet implementation class ServletDiProva
 */
@WebServlet("/ServletDiProva")
public class ServletDiProva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDiProva() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		String email = "davide@mail.com";
		String password = "password";
		Utente u = DAOFactory.getDAOFactory().getUtenteDAO().login(email, password);
		System.out.println(u);
		
		List<Test> tests =  DAOFactory.getDAOFactory().getTestDAO().visualizzaStorico(u);
		System.out.println(tests);
		
		/*List<Utente> utenti = DAOFactory.getDAOFactory().getUtenteDAO().selezionaDipendenti();
		Utente utente = utenti.get(0);
		System.out.println(utente);*/
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
