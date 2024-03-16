package com.code_with_malaka.ABC_lab_system.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FileViewerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FileViewerController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("fileName");
        String filePath = "C:/Uploads/" + fileName;
		request.setAttribute("filePath", filePath);
    	RequestDispatcher rd = request.getRequestDispatcher("result-viewer.jsp");
    	rd.forward(request, response);
		
	}

}
