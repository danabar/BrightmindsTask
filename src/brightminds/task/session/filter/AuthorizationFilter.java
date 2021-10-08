package brightminds.task.session.filter;

import java.io.IOException;
import java.util.Objects;

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

import org.apache.log4j.Logger;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
/**
 *
 * @author Dana Barghouthi
 *
 */
public class AuthorizationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// no needed operation
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();
			if (reqURI.indexOf("/login.xhtml") >= 0
					|| !Objects.isNull(ses)
							&& !Objects.isNull(ses.getAttribute("username"))
					|| reqURI.indexOf("/faces/") >= 0
					|| reqURI.contains("javax.faces.resource")) {
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
			}
		} catch (Exception e) {
			Logger.getLogger(AuthorizationFilter.class).error(e.getMessage());
		}
	}

	@Override
	public void destroy() {
		// no operation
	}
}