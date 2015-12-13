package com.hatherly.pocketmoneytracker.api;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.hatherly.pocketmoneytracker.api.ServletUtils.readParameter;
import static com.hatherly.pocketmoneytracker.api.ServletUtils.readIntParameter;

/**
 * Servlet implementation
 */
public class RetrievePerson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrievePerson() {
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
		String nhsno = readParameter(request, "nhsno");
		
		String result = retrievePerson(nhsno);
		OutputStream out = response.getOutputStream();
		out.write(result.getBytes());
	}
	
	public static String retrievePerson(String nhsno) {
		/*List<AbstractDocument> resultDocs = Repository.getByNHSNo(nhsno);
		PatientRecord record = new PatientRecord(resultDocs);
		return record.toJSON();*/
		return null;
		//TODO Implement this
	}

}
