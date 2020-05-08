package com.mail;

import java.io.IOException;
import com.mail.EmailUtility;
import com.student.StudentRegistration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/studentmail")

public class SendMail extends HttpServlet {
private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in mail");

		String host="smtp.gmail.com";
		String port="587";
		String user="attendance2715@gmail.com";
		String pass="attendance2715@gmail";
		
		
		String recipient=StudentRegistration.mail;
	
		String subject="Registration Successfull";
		String content="Hi.."+" "+StudentRegistration.studentName+"..Your Registration Has Been Successfully Done.. "
				+ "Your Roll No ="+" "+StudentRegistration.rollno+" "+  
				 "Branch Name Is ="+" "+StudentRegistration.branchName+" "+
				"Division Is ="+" "+StudentRegistration.division;
				 
		
		
		try {

			EmailUtility.sendEmail(host, port, user, pass, recipient, subject, content);
		
			response.sendRedirect("studentRegistration.jsp?msg1=Email Successfully Send");
			
		} catch (Exception ex) {
			ex.printStackTrace();
			response.sendRedirect("studentRegistration.jsp?msg1=Mail Not Send Check Internet Connection And Valid Email");

		}

		
	}
}
