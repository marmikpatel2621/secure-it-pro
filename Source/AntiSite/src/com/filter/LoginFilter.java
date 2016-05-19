package com.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.model.DbConnection;
import com.mysql.jdbc.Connection;

/**
 * Servlet Filter implementation class LogiFilter
 */
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
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
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		RequestDispatcher requestDispatcher;
		String flag = request.getParameter("flag");
		String uid = request.getParameter("id");
		String uri = ((HttpServletRequest) request).getRequestURI();
		System.out.println("filter");
		System.out.println("link =  = = = = " + uri);
		if (uri.contains("index.jsp")||uri.contains("contactus.jsp") || uri.contains("login.do")||uri.contains("/css") || uri.contains("/js")
				&& !uri.contains("/jsp") || uri.contains("/img") || uri.contains("forgot.jsp")
				|| uri.contains("/fonts") || uri.contains("forgot.do")) {

			System.out.println("inside reg");
			chain.doFilter(request, response);

		}

		else if (flag != null && flag.equals("logout")) {
			HttpSession session = ((HttpServletRequest) request).getSession();
			session.removeAttribute("session");
			System.out.println("logout in else if");
			session.invalidate();
			System.out.println("after session invalidates");
			((HttpServletResponse) response).sendRedirect("index.jsp");

		}
				else if (uid != null && !uid.equals("") && flag != null && flag.equals("login") ) {
			
			chain.doFilter(request, response);
		} else {
			System.out.println("hello"+flag+"id : "+uid);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);

		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
