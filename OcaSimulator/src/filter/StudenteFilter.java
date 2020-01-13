package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;

/**
 * Servlet Filter implementation class StudenteFilter
 */
@WebFilter("/StudenteFilter")
public class StudenteFilter implements Filter {

    /**
     * Default constructor. 
     */
    public StudenteFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hsreq=(HttpServletRequest) request;
		HttpServletResponse hsres=(HttpServletResponse) response;
		Utente u=(Utente)hsreq.getSession().getAttribute("utente");
		boolean controllo=u!=null&&u.getRuolo().equals("stud");
		System.out.println("controllo sutdente "+controllo);
		if(controllo) {	
		chain.doFilter(request, response);
		}else {
			hsres.sendRedirect("WEB-INF/jsp/home.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
