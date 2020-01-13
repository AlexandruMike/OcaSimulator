package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daofactory.DAOFactory;
import model.Utente;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if(email.equals("admin@elis.org") && password.equals("1234567")) {
			Utente u=new Utente();
			u.setEmail(email);
			u.setPassword(password);
			u.setRuolo("admin");
			request.getSession().setAttribute("utente", u);
			request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response); ;
		}else {
			
		Utente u = DAOFactory.getDAOFactory().getUtenteDAO().login(email, password);
		System.out.println(u);
		
		if(u!=null) {
			if(u.getRuolo().equalsIgnoreCase("stud")) {
				if( u.getAccesso()==1) {
			request.getSession().setAttribute("utente", u);
			request.getRequestDispatcher("/WEB-INF/jsp/studente.jsp").forward(request, response);
			//collegare la pagina home dell'utente loggato
				} else {
					request.setAttribute("queue", "In attesa di Accettazione");
					request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
				}
			}
			
			if(u.getRuolo().equalsIgnoreCase("dip")) {
			request.getSession().setAttribute("utente", u);
			response.sendRedirect("dipendente");
			//collegare la pagina home dell'utente loggato
			}
			
		}else {
			response.sendRedirect(request.getContextPath());
		}
		}
	}

}

