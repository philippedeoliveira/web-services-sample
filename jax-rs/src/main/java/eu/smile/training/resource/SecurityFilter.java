package eu.smile.training.resource;

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

@WebFilter(urlPatterns={"/api/users/*"}, filterName="securityFilter")
public class SecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (((HttpServletRequest)request).getHeader("Authorization") != null) {
			
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse)response).setStatus(403);
			response.getOutputStream().write("Hop Hop Hop !".getBytes());
		}
	}

	@Override
	public void destroy() {
		// nothing to destroy as nothing was initialized
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// nothing to initialize ... for now
	}
	
}
