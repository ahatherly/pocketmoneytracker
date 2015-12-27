package com.hatherly.pocketmoneytracker.api;

import java.awt.Graphics2D;
import com.hatherly.pocketmoneytracker.model.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hatherly.pocketmoneytracker.mongodb.MongoImages;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Servlet implementation
 */
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String person_id = null;
		try {
	        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	        for (FileItem item : items) {
	            if (item.isFormField()) {
	                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
	                String fieldName = item.getFieldName();
	                String fieldValue = item.getString();
	                if (fieldName.equals("person_id")) {
	                	person_id = fieldValue;
	                }
	            } else {
	                // Process form file field (input type="file").
	                String fieldName = item.getFieldName();
	                //String fileName = FilenameUtils.getName(item.getName());
	                InputStream fileContent = item.getInputStream();
	                
	                //HttpSession session = request.getSession(true);
	                
	                ByteArrayOutputStream bos = new ByteArrayOutputStream();
	                int b = 0;
	                while ((b = fileContent.read()) > -1) {
	                	bos.write(b);
	                }
	                //byte[] encodedBytes = Base64.encodeBase64(bos.toByteArray());
	                bos.flush();
	                // Resize image
	                String resizedImage = resizeImage(bos.toByteArray());
	                
	                Image i = new Image(resizedImage, "");
	                MongoImages.updateImage("tempImage", i);
	                //session.setAttribute("imageData", i);
	            }
	        }
	        
	        //RequestDispatcher dispatcher = request.getRequestDispatcher("picture-crop.jsp");
	        //dispatcher.forward(request, response);
	        response.sendRedirect("picture-crop.jsp?person_id="+person_id);

	    } catch (FileUploadException e) {
	        throw new ServletException("Cannot parse multipart request.", e);
	    }
	}
	
	private static String resizeImage(byte[] imgBytes) {
		
		BASE64Decoder decoder = new BASE64Decoder();
		String outB64 = null;
		
		try {
			// create a buffered image
			BufferedImage image = null;
			ByteArrayInputStream bis = new ByteArrayInputStream(imgBytes);
			image = ImageIO.read(bis);
			bis.close();
	
			// Scale it
			double old_height=image.getHeight();
			double old_width=image.getWidth();
			double new_width=0;
			double new_height=0;

			new_width=500;
			double scale_factor = (500/old_width);
			new_height=old_height*scale_factor;
			java.awt.Image scaled = image.getScaledInstance((int)new_width, (int)new_height, java.awt.Image.SCALE_SMOOTH);
			
			// Convert to a BufferedImage
			BufferedImage scaledBufferedImage = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null), BufferedImage.TYPE_INT_RGB);
	        //bufferedImage is the RenderedImage to be written
	        Graphics2D g2 = scaledBufferedImage.createGraphics();
	        g2.drawImage(scaled, null, null);
			
			// Write out as Base64
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(scaledBufferedImage, "jpeg", bos);
            byte[] imageBytes = bos.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            outB64 = encoder.encode(imageBytes);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return outB64;
	}
}
