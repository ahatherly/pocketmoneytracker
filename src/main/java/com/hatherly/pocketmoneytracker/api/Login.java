package com.hatherly.pocketmoneytracker.api;

import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hatherly.pocketmoneytracker.mongodb.Mongo;
import com.hatherly.pocketmoneytracker.mongodb.MongoLogins;

import static com.hatherly.pocketmoneytracker.api.ServletUtils.readParameter;
import static com.hatherly.pocketmoneytracker.api.ServletUtils.readIntParameter;

/**
 * Servlet implementation class Search
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}
	
	private void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		OutputStream out = response.getOutputStream();
		String action = readParameter(request, "action");
		
		if (action != null) {
			if (action.equals("login")) {
				String login = readParameter(request, "username");
				String password = readParameter(request, "password");
				
				SecurityUtils loginManager = new SecurityUtils();
				
				Boolean result = false;
				try {
					result = loginManager.authenticate(login, password);
				} catch (NoSuchAlgorithmException e) {
					//Logger.error("Error hashing password", e);
					result = false;
				} catch (ApplicationException e) {
					//Logger.error("Error getting login details from database", e);
					result = false;
				}
				if (result) {
					com.hatherly.pocketmoneytracker.model.Login user = MongoLogins.getLogin(login);
					if (user.getPersonID() == null) {
						session.setAttribute("admin", "true");
						session.setAttribute("personID", null);
					} else {
						if (user.getPersonID().length()==0) {
							session.setAttribute("admin", "true");
							session.setAttribute("personID", null);
						}
						session.setAttribute("admin", "false");
						session.setAttribute("personID", user.getPersonID());
					}
					session.setAttribute("username", login);
					session.setAttribute("authenticated", "true");
					out.write("OK".getBytes());
				} else {
					session.removeAttribute("username");
					session.setAttribute("authenticated", "false");
					out.write("FAILED".getBytes());
				}
			} else {
				// Logout
				session.removeAttribute("username");
				session.setAttribute("authenticated", "false");
				//Logger.trace("Logging out user");
				out.write("LOGGED OUT".getBytes());
			}
		} else {
			// Logout
			session.removeAttribute("username");
			session.setAttribute("authenticated", "false");
			//Logger.trace("Logging out user");
			out.write("LOGGED OUT".getBytes());
		}
	}
}
