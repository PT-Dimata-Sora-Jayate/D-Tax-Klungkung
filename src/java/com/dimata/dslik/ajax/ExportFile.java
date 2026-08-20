/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dimata.dslik.ajax;

import com.dimata.qdep.form.FRMQueryString;
import com.dimata.util.Formater;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfArray;
import com.sun.mail.iap.ByteArray;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletContext;

/**
 *
 * @author Ardiadi
 */
public class ExportFile extends HttpServlet {
    private String outputFile;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final int BYTES_DOWNLOAD = 1024;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String fileName = FRMQueryString.requestString(request, "FRM_FIELD_FILENAME");
	String exportTo = FRMQueryString.requestString(request, "FRM_FIELD_EXPORT_TO");
        String baseUrl = FRMQueryString.requestString(request, "FRM_FIELD_BASE_URL");
       
	String css = ""
	//+ "<link href='"+request.getRealPath("/styles/bootstrap3.1/css/bootstrap.min.css")+"' rel='stylesheet' type='text/css' />"
	//+ "<link href='"+request.getRealPath("/styles/bootstrap3.1/css/font-awesome.min.css")+"' rel='stylesheet' type='text/css' />"
	//+ "<link href='"+request.getRealPath("/styles/bootstrap3.1/css/AdminLTE.css")+"' rel='stylesheet' type='text/css' />"
        + "<link href='"+baseUrl+"/styles/bootstrap3.1/css/bootstrap.min.css' rel='stylesheet' type='text/css' />"
	+ "<link href='"+baseUrl+"/styles/bootstrap3.1/css/font-awesome.min.css' rel='stylesheet' type='text/css' />"
	+ "<link href='"+baseUrl+"/styles/bootstrap3.1/css/AdminLTE.css' rel='stylesheet' type='text/css' />"
	+ "<style type='text/css'>"
		+ "th {"
		    + "background-color:#cccccc;"
		    + "font-size:12px;"
                    + ""
		+ "}"
                + "span .form-control{"
                    + "width:100%"
                + "}"
	+ "</style>";
	String getHtml = ""
	+ "<html>"
	    + "<head>"
		+ "<title>Test</title>"
		+ ""+css+""
	    + "</head>"
	    + "<body class='skin-blue fixed'>"
		+"<div class='wrapper row-offcanvas row-offcanvas-left nonprint'>"
		    + "<aside class='right-side'>"
			+ "<section class='content'>"
			    + "<div class='box'>"
				+ "<div class='box-body' id='exportcontent'>"
				    + ""+request.getParameter("FRM_FIELD_HTML")+""
				+ "</div>"
			    + "</div>"
			+ "</section>"
		    + "</aside>"
		+ "</div>"
	    + "</body>"
	+ "</html>";
	
	if(exportTo.equals("excel")){
	    response.setContentType("application/vnd.ms-excel");
	    response.setHeader("Content-Disposition", "inline; filename="
	    + fileName.replaceAll(" ", "-")+"("+Formater.formatDate(new Date(), "yyyy/MM/dd")+")"+".xls");
	    response.getWriter().write(getHtml);
	}else if(exportTo.equals("word")){
	    response.setContentType("application/vnd.ms-word");
	    response.setHeader("Content-Disposition", "inline; filename="
	    + fileName.replaceAll(" ", "-")+"("+Formater.formatDate(new Date(), "yyyy/MM/dd")+")"+".doc");
	    response.getWriter().write(getHtml);
	}else if(exportTo.equals("pdf")){
	    response.setContentType("application/pdf");
	    response.setHeader("Content-Disposition", "inline; filename="
	    + fileName.replaceAll(" ", "-")+"("+Formater.formatDate(new Date(), "yyyy/MM/dd")+")"+".pdf");
	    ServletContext ctx = getServletContext();
	    InputStream is = ctx.getResourceAsStream(getHtml);

	    int read=0;
	    byte[] bytes = new byte[BYTES_DOWNLOAD];
	    OutputStream os = response.getOutputStream();

	    while((read = is.read(bytes))!= -1){
		os.write(bytes, 0, read);
	    }
	    os.flush();
	    os.close(); 
	}
	 
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>
}
