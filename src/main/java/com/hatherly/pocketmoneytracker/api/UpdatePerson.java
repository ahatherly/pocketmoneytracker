package com.hatherly.pocketmoneytracker.api;

import java.io.IOException;
import java.io.OutputStream;
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
import com.hatherly.pocketmoneytracker.mongodb.MongoPeople;
import com.hatherly.pocketmoneytracker.mongodb.MongoTransactions;

import static com.hatherly.pocketmoneytracker.api.ServletUtils.readParameter;
import static com.hatherly.pocketmoneytracker.api.ServletUtils.readIntParameter;
import static com.hatherly.pocketmoneytracker.api.ServletUtils.readDoubleParameter;

/**
 * Servlet implementation
 */
public class UpdatePerson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePerson() {
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
		if (session.getAttribute("admin").equals("true")) {
			String person_id = readParameter(request, "person_id");
			String name = readParameter(request, "name");
			double amount = readDoubleParameter(request, "weeklyPocketMoneyAmount");
			int day = readIntParameter(request, "dayOfWeekPocketMoneyPaid");
			
			Person p = MongoPeople.getPerson(person_id);
			p.setName(name);
			p.setWeeklyPocketMoneyAmount(amount);
			p.setDayOfWeekPocketMoneyPaid(day);
			MongoPeople.updatePerson(p);
			
		}
		response.sendRedirect("index.jsp");
	}
}
