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
@WebFilter(urlPatterns = { "/pages/admin/cadastro-professor.xhtml",
		"/pages/admin/cadastro-recurso.xhtml",
		"/pages/admin/listar-professores.xhtml",
		"/pages/admin/listar-recursos.xhtml",
		"/pages/admin/listar-reservas.xhtml",
		"/pages/admin/perfil-admin.xhtml", "/pages/admin/home-admin.xhtml" })
public class FiltroAdmin implements Filter {

	/**
	 * Default constructor.
	 */
	public FiltroAdmin() {
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
						new TipoDeUsuario().getAdministrador())) {
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
