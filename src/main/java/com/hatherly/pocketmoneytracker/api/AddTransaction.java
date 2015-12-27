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
public class AddTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTransaction() {
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
		/*Enumeration params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String paramName = params.nextElement().toString();
			String paramVal = request.getParameter(paramName);
			System.out.println(paramName + " : " + paramVal);
		}*/
		
		String person_id = readParameter(request, "person_id");
		String name = readParameter(request, "name");
		double amount = readDoubleParameter(request, "amount");
		
		String category = "";
		if (readParameter(request, "payment") != null) {
			category = "payment";
			amount = amount * -1;
			if (name == null) {
				name = "Payment";
			}
		} else if (readParameter(request, "reward") != null) {
			category = "reward";
			if (name == null) {
				name = "Reward";
			}
		} else if (readParameter(request, "penalty") != null) {
			category = "penalty";
			amount = amount * -1;
			if (name == null) {
				name = "Penalty";
			}
		}
		
		Person p = MongoPeople.getPerson(person_id);
		double new_balance = UpdateTransactions.addTransaction(p, name, amount, category);
		//OutputStream out = response.getOutputStream();
		//out.write(Double.toString(new_balance).getBytes());
		
		response.sendRedirect("index.jsp");
	}
}
