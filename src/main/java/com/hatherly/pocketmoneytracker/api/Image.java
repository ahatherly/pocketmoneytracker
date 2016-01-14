package com.hatherly.pocketmoneytracker.api;

import static com.hatherly.pocketmoneytracker.api.ServletUtils.readParameter;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hatherly.pocketmoneytracker.mongodb.MongoImages;

/**
 * Servlet implementation
 */
public class Image extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Image() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession(false);
		//Image i = (Image)session.getAttribute("imageData");
		System.out.println("Requested Image...");
		String image_id = readParameter(request, "image_id");
		
		if (image_id == null) {
			image_id = "tempImage";
		}
		
		byte[] b = (byte[])MongoImages.getImage(image_id);
		response.setContentType("image/jpeg");
		response.setContentLength(b.length);
		OutputStream out = response.getOutputStream();
		out.write(b);
	}

}
