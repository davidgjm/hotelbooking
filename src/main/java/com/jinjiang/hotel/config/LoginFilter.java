/**
 * 
 */
package com.jinjiang.hotel.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinjiang.hotel.domain.User;
import com.jinjiang.hotel.service.AuthenticationService;
import com.jinjiang.hotel.service.impl.AuthenticationServiceImpl;

/**
 * @author gaojianm
 *
 */
public class LoginFilter implements Filter {
	private Logger log=LoggerFactory.getLogger(getClass());
	private AuthenticationService as;
	/**
	 * 
	 */
	public LoginFilter() {
		as=new AuthenticationServiceImpl();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		String ctxPth=req.getContextPath();
		String reqPath=req.getServletPath();
		log.debug("User request path: {}",reqPath);
		User user=(User) req.getSession().getAttribute(ZKConstants.ATTRS_ADMIN_USER);
		if (reqPath.endsWith(ZKConstants.REDIRECTS_LOGIN) || user!=null) {
			log.debug("User is visiting the login page: {}", ZKConstants.REDIRECTS_LOGIN);
			chain.doFilter(request, response);
			return;
		}else {
			HttpServletResponse resp=(HttpServletResponse) response;
			resp.sendRedirect(ctxPth+ZKConstants.REDIRECTS_LOGIN);
		}
		
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
