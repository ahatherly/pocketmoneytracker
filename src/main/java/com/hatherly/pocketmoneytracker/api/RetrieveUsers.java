package com.hatherly.pocketmoneytracker.api;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hatherly.pocketmoneytracker.model.LoginList;
import com.hatherly.pocketmoneytracker.model.Person;
import com.hatherly.pocketmoneytracker.model.PersonList;
import com.hatherly.pocketmoneytracker.mongodb.MongoLogins;
import com.hatherly.pocketmoneytracker.mongodb.MongoPeople;

import static com.hatherly.pocketmoneytracker.api.ServletUtils.readParameter;
import static com.hatherly.pocketmoneytracker.api.ServletUtils.readIntParameter;

/**
 * Servlet implementation
 */
public class RetrieveUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveUsers() {
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
		String current_user = session.getAttribute("username").toString();
		boolean is_admin = session.getAttribute("admin").equals("true");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		LoginList l = MongoLogins.getLogins(current_user, is_admin);
		String result = gson.toJson(l);
		OutputStream out = response.getOutputStream();
		out.write(result.getBytes());
	}
}
