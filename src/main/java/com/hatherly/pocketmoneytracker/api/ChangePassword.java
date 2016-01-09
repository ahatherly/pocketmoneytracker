package com.hatherly.pocketmoneytracker.api;

import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.hatherly.pocketmoneytracker.actions.UpdateTransactions;
import com.hatherly.pocketmoneytracker.model.Person;
import com.hatherly.pocketmoneytracker.model.TransactionList;
import com.hatherly.pocketmoneytracker.mongodb.MongoLogins;
import com.hatherly.pocketmoneytracker.mongodb.MongoPeople;
import com.hatherly.pocketmoneytracker.mongodb.MongoTransactions;

import static com.hatherly.pocketmoneytracker.api.ServletUtils.readParameter;
import static com.hatherly.pocketmoneytracker.api.ServletUtils.readIntParameter;
import static com.hatherly.pocketmoneytracker.api.ServletUtils.readDoubleParameter;

/**
 * Servlet implementation
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
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
		boolean admin = (session.getAttribute("admin").equals("true"));
		String my_username = session.getAttribute("username").toString();
		
		String username = readParameter(request, "username");
		String password = readParameter(request, "password");
			
		if (!admin && !(username.equals(my_username))) {
			// Don't allow non-admins to change other users passwords
		} else {
			com.hatherly.pocketmoneytracker.model.Login login = MongoLogins.getLogin(username);
			SecurityUtils loginManager = new SecurityUtils();
			try {
				loginManager.createOrUpdateUser(username, password, login.getPersonID());
			} catch (NoSuchAlgorithmException e) {
				throw new ServletException("Unable to create user");
			}
		}
		response.sendRedirect("index.jsp");
	}
}
