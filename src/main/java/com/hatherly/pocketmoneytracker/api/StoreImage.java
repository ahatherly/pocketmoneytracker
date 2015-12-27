package com.hatherly.pocketmoneytracker.api;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hatherly.pocketmoneytracker.model.Image;
import com.hatherly.pocketmoneytracker.model.Person;
import com.hatherly.pocketmoneytracker.model.PersonList;
import com.hatherly.pocketmoneytracker.mongodb.MongoImages;
import com.hatherly.pocketmoneytracker.mongodb.MongoPeople;

import static com.hatherly.pocketmoneytracker.api.ServletUtils.readParameter;
import static com.hatherly.pocketmoneytracker.api.ServletUtils.readIntParameter;

/**
 * Servlet implementation
 */
public class StoreImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreImage() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}
	
	private void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = readParameter(request, "data");
		String id = readParameter(request, "id");
		Image i = new Image(data, "");
        MongoImages.updateImage(id, i);
		OutputStream out = response.getOutputStream();
		out.write("OK".getBytes());
	}
}
