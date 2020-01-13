package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daofactory.DAOFactory;
import model.Domande;


/**
 * Servlet implementation class VisualizzaDomandaServlet
 */
@WebServlet("/visualizzadomande")
public class VisualizzaDomandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaDomandeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/***recupero la lista delle domande che l admin visualizzerà***/
		List<Domande> listaDomande = DAOFactory.getDAOFactory().getDomandeDAO().selezionaDomande();
		request.setAttribute("listaDomande", listaDomande);
		request.getRequestDispatcher("WEB-INF/jsp/visualizzadomande.jsp").forward(request, response);
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*** inizializzo l'ID domanda che utilizziamo per il remove()***/
		int idDomanda=0;
		String idDomandaString= request.getParameter("idSet");
		System.out.println(idDomandaString);
		/*** elimino possibili exception parsando la stringa all interno del tryCatch ***/
		try {
			idDomanda = Integer.parseInt(idDomandaString);
		} catch (Exception e) {
			System.out.println("problema nel tryCatch di VisualizzaDomandeServlet");
		}
		/*** elimino la domanda all interno del DB ***/
		Domande daEliminare= new Domande(idDomanda);
		boolean esitoElimininazione = DAOFactory.getDAOFactory().getDomandeDAO().eliminaDomanda(daEliminare);
		if (esitoElimininazione) {
			System.out.println("domanda eliminata con successo");
		}else {
			System.out.println("problema con l'eliminazione della domanda");
		}
		doGet(request, response);
	}

}
