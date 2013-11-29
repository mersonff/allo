package filtros;

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
import javax.servlet.http.HttpSession;

import util.TipoDeUsuario;

/**
 * Servlet Filter implementation class FiltroAdmin
 */
@WebFilter(urlPatterns = {"/pages/professor/reservar.xhtml",
		"/pages/professor/perfil-professor.xhtml",
		"/pages/professor/home-professor.xhtml" })

public class FiltroProfessor implements Filter {

	/**
	 * Default constructor.
	 */
	public FiltroProfessor() {
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
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession(false);
		if (session.getAttribute("tipo") != null
				&& session.getAttribute("tipo").equals(
						new TipoDeUsuario().getProfessor())) {
			chain.doFilter(request, response);
		} else {
			String contextPath = ((HttpServletRequest) request)
					.getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath
					+ "/index.xhtml");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
