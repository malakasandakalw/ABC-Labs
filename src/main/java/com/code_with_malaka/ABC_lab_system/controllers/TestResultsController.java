package com.code_with_malaka.ABC_lab_system.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


public class TestResultsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public TestResultsController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if (type != null && type.equals("create")) {
			System.out.println("here");
			createTestResult(request, response, "");
		}
	}
	
	private boolean uploadFile(HttpServletRequest request) throws IOException, ServletException {
		String uploadPath = "C:/Uploads";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        for (Part part : request.getParts()) {
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            InputStream fileContent = part.getInputStream();
            Files.copy(fileContent, Paths.get(uploadPath, fileName));
        }
        return true;
	}
	
	private void createTestResult(HttpServletRequest request, HttpServletResponse response, String message) throws IOException, ServletException {
		System.out.println("here");
		uploadFile(request);
	}

}
