package com.hatherly.pocketmoneytracker.api;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hatherly.pocketmoneytracker.actions.PayWeeklyPocketMoney;
import com.hatherly.pocketmoneytracker.model.Person;
import com.hatherly.pocketmoneytracker.model.PersonList;
import com.hatherly.pocketmoneytracker.mongodb.MongoPeople;

import static com.hatherly.pocketmoneytracker.api.ServletUtils.readParameter;
import static com.hatherly.pocketmoneytracker.api.ServletUtils.readIntParameter;

/**
 * Servlet implementation
 */
public class Scheduler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Scheduler() {
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
		// Run scheduled tasks
		
		// Check if we need to pay the weekly pocket money
		String result = PayWeeklyPocketMoney.payPocketMoney();
		
		OutputStream out = response.getOutputStream();
		out.write(result.getBytes());
	}
}
