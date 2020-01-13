package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daofactory.DAOFactory;
import model.Domande;
import model.Opzioni;
import model.Utente;

/**
 * Servlet implementation class inserimentorisposte
 */
@WebServlet("/inserimentorisposte")
public class InserimentoRisposteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserimentoRisposteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("creazionedomande");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Domande d=(Domande) request.getSession().getAttribute("domanda");
		String testo=request.getParameter("risposta");
		String correttaTxt=request.getParameter("corretta");
		String ulterioreRisposta=request.getParameter("ulterioreRisposta");
		ulterioreRisposta=ulterioreRisposta==null?"no":ulterioreRisposta;
		Opzioni o=null;
		try {
			int corretta=Integer.parseInt(correttaTxt);
			
			if(testo!=null) {
				o=new Opzioni();
				o.setCorretto(corretta);
				o.setTesto(testo);
				o.setDomande(d);
				d.addOpzioni(o);
				request.getSession().setAttribute("domanda", d);
			}
		}catch (NumberFormatException e) {
			// TODO: handle exception
		}
		
		if(o!=null) {
			if(ulterioreRisposta.equalsIgnoreCase("si")) {
				request.getRequestDispatcher("/WEB-INF/jsp/inserimentorisposte.jsp").forward(request, response);
			}else {
				request.getSession().removeAttribute("domanda");
				Utente u=(Utente)request.getSession().getAttribute("utente");
				d.setUtente(u);
				System.out.println(u);
				if(DAOFactory.getDAOFactory().getDomandeDAO().aggiungiDomanda(d, d.getOpzionis().toArray(new Opzioni[d.getOpzionis().size()]))) {
					response.sendRedirect("dipendente");
					
				}else {
					response.sendRedirect("creazionedomanda");
				}
			}
		}else {
			request.getRequestDispatcher("/WEB-INF/jsp/inserimentorisposte.jsp").forward(request, response);
		}
		
	}

}