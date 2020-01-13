package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javafx.scene.control.Alert;
import model.Domande;
import model.Opzioni;
import model.Utente;
import utility.Utilities;

/**
 * Servlet implementation class formcreazionedomande
 */
@WebServlet("/formcreazionedomande")
public class formcreazionedomande extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public formcreazionedomande() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/jsp/formdomande.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String domandafatta = request.getParameter("domanda");
		boolean controllo = domandafatta.trim().length()> 0;
		
		HttpSession s=request.getSession();
		
		if (controllo) {
			Domande d=new Domande();
			d.setTesto(domandafatta);
			d.setUtente((Utente)request.getSession().getAttribute("utente"));
			if(d.getOpzionis()==null)d.setOpzionis(new ArrayList<Opzioni>());
			s.setAttribute("domanda", d);
			request.getRequestDispatcher("/WEB-INF/jsp/inserimentorisposte.jsp").forward(request,response);
		}
		else {
			doGet(request, response);
		}
	}
}
