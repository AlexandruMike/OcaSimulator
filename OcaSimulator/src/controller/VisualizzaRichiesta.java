package controller;
import java.util.List;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daofactory.DAOFactory;
import model.Utente;

/**
 * Servlet implementation class VisualizzaRichiesta
 */
@WebServlet("/visualizzarichiesta")
public class VisualizzaRichiesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaRichiesta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//chiamo il metodo che visualizza la richiesta
		List<Utente> visualizzaRichiesta = DAOFactory.getDAOFactory().getUtenteDAO().visualizzaRichiesteRegistrazione();
		//ci faccio il setattribute
		request.setAttribute("richiesta", visualizzaRichiesta);
		//lo mando alla jsp
		request.getRequestDispatcher("/WEB-INF/jsp/visualizzarichiesta.jsp").forward(request, response);
			}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String esito = request.getParameter("val");
		String id= request.getParameter("value");
		int idUtente=0;
		boolean esitoRichiesta = false;
		try {
			idUtente= Integer.parseInt(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(esito.equalsIgnoreCase("accetta")) {
			esitoRichiesta = true;
		}
		Utente valutato =new Utente(idUtente); 
			DAOFactory.getDAOFactory().getUtenteDAO().gestisciRichiesta(valutato,esitoRichiesta);
		
		
		
			//rivado al do get così riaggiorno la lista
				doGet(request, response);
		
	}

}
