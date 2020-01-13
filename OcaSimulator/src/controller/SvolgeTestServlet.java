package controller;

import java.io.IOException;
import java.util.Calendar;
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
import model.Test;
import model.Utente;

/**
 * Servlet implementation class SvolgeTest
 */
@WebServlet("/svolgetest")
public class SvolgeTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SvolgeTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int numeroDomande = 10;
		//prendo il numero di domande che lo studente ha selezionato
		try {
			numeroDomande =Integer.parseInt(request.getParameter("numero"));
			System.out.println(numeroDomande);
			
		}catch (NumberFormatException e) {
			// TODO: handle exception
		}
		 
		
		//prendo la lista di domande e ne prendo tante quante ne ha chiesto lo studente in modo casuale
		List<Domande> listaDomande = DAOFactory.getDAOFactory().getDomandeDAO().generaTest(numeroDomande);
		HttpSession s=request.getSession();
		s.setAttribute("listaDomande", listaDomande);
		s.setAttribute("risposteDate", 0);
		s.setAttribute("risposteEsatte", 0);
		request.getRequestDispatcher("/WEB-INF/jsp/svolgetest.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s=request.getSession();
		List<Domande> lista=(List<Domande>) s.getAttribute("listaDomande");
		int domandaDaValutare=0;
		
		try {
				domandaDaValutare=(int)request.getSession().getAttribute("risposteDate");
				Domande d=lista.get(domandaDaValutare);
				boolean indovinata = true;
				for(int i=0;i<d.getOpzionis().size();i++) {
					Opzioni o=d.getOpzionis().get(i);
					String rispostaCheck=request.getParameter("opzione"+o.getId());
					int corretto=0;
					try {
						corretto=Integer.parseInt(rispostaCheck);
					}catch (NumberFormatException e) {
						// TODO: handle exception
					}
					if((o.getCorretto()==1&&corretto==0)||(o.getCorretto()==0&&corretto==1)) {
						indovinata=false;
						break;
					}
				}
				int risposteEsatte=(Integer)(s.getAttribute("risposteEsatte"));
				if(indovinata) {
					s.setAttribute("risposteEsatte",++risposteEsatte);
				}
				if(++domandaDaValutare<lista.size()) {
					System.out.println(domandaDaValutare);
					s.setAttribute("risposteDate",(Integer)(domandaDaValutare));
					request.getRequestDispatcher("/WEB-INF/jsp/svolgetest.jsp").forward(request, response);
				}else {
					System.out.println("visualizza risultato");
					s.removeAttribute("listaDomande");
					s.removeAttribute("risposteDate");
					s.removeAttribute("risposteEsatte");
					Test t=new Test();
					t.setData(Calendar.getInstance().getTime());
					t.setNDomande(lista.size());
					int punteggio=(int)(risposteEsatte*(100.0/lista.size()));
					t.setPunteggio(punteggio);
					t.setUtente((Utente)s.getAttribute("utente"));
					DAOFactory.getDAOFactory().getTestDAO().salvaTest(t);
					request.setAttribute("domande", lista.size());
					request.setAttribute("punteggio", punteggio);
					request.getRequestDispatcher("/WEB-INF/jsp/visualizzarisultato.jsp").forward(request, response);
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			response.sendRedirect("opzionistudente");
		}

		//-------------------CAMBIATO---------------------------
	}

}
