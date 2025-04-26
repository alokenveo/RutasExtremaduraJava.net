package es.unex.cum.tw.rutas.filter;

import java.io.IOException;
import java.util.logging.Logger;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/UsuarioController", "/RutaController" })
public class LoginFilter implements Filter {
	private static final Logger logger = Logger.getLogger(Filter.class.getName());

	public LoginFilter() {

	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// Excluir el login del filtro
		String action = req.getParameter("action");
		if ("UsuarioLogin".equals(action) || "UsuarioAltaNormal".equals(action) || "VerRutas".equals(action)) {
			chain.doFilter(request, response);
			return;
		}

		HttpSession session = ((HttpServletRequest) request).getSession(true);
		logger.info("checking user in session");
		if (session.getAttribute("username") == null) {
			String loginPage = req.getContextPath() + "/login.jsp";
			res.sendRedirect(loginPage);
		} else {
			chain.doFilter(request, response);
			logger.info("Session ok");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
