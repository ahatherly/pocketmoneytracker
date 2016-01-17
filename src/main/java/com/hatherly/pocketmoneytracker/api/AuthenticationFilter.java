package com.hatherly.pocketmoneytracker.api;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {
	private ServletContext context;
    
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        //Logger.trace("AuthenticationFilter initialized");
    }
     
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
         
        String uri = req.getRequestURI();
        //System.out.println("Requested Resource::"+uri);
        
        if(uri.endsWith("login") || uri.endsWith("scheduler") || uri.endsWith(".js") || uri.endsWith(".css")) {
        	// pass the request along the filter chain
            chain.doFilter(request, response);
        } else {
        	HttpSession session = req.getSession(false);
        	boolean authenticated = false;
	        if(session != null) {
	        	if (session.getAttribute("authenticated") != null) {
		        	if (session.getAttribute("authenticated").equals("true")) {
		        		authenticated = true;
		        	}
	        	}
	        	if (authenticated) {
	            	// pass the request along the filter chain
	                chain.doFilter(request, response);
	            } else {
	            	//Logger.info("Unauthorized access request");
	            	//System.out.println("Unauthorized access request");
	            	request.setAttribute("Message", "Please log in");
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
	            }
	        } else {
	        	//Logger.info("Session timed out");
	        	//System.out.println("Session timed out");
	        	request.setAttribute("Message", "Session timed out - please log in again");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
	        }
        }
    }
 
    public void destroy() {
    }
}
