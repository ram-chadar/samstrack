package com.get.branchandsubject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.dbcon.Conn;
import com.dbcon.DBUtil;
@WebServlet("/getbranch")

public class GetBranch extends HttpServlet {
	private static final long serialVersionUID = 1L;
protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String link;
		link = request.getParameter("link");

		PrintWriter out = response.getWriter();
		Connection con = null;
		ResultSet rs;

		response.setContentType("text/html");
		List<String> branchList = new ArrayList<String>();
		branchList.clear();
		
		try {
			con = DBUtil.getDataSource().getConnection();

			String sql = "select  distinct branchname from branch";
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				// Add records into data list
				branchList.add(rs.getString("branchname"));
			}
request.setAttribute("branches", branchList);

		} catch (Exception e) {
			e.printStackTrace();
			out.println(e);
		}

		// Disptching request   

		if (link.equals("addsubject")) {
			RequestDispatcher rd1 = request.getRequestDispatcher("subjectDetail.jsp");
			if (rd1 != null) {
				rd1.forward(request, response);
			}
		} 
		
		else if (link.equals("studentregistration")) {
			RequestDispatcher rd1 = request.getRequestDispatcher("studentRegistration.jsp");
			if (rd1 != null) {
				rd1.forward(request, response);
			}
		} 
		
		else if (link.equals("studentlistoption")) {
			RequestDispatcher rd1 = request.getRequestDispatcher("studentListOption.jsp");
			if (rd1 != null) {
				rd1.forward(request, response);
			}
		}
		else if (link.equals("takeattendanceclass")) {
			RequestDispatcher rd1 = request.getRequestDispatcher("takeClassAttendance.jsp");
			if (rd1 != null) {
				rd1.forward(request, response);
			}
		}
		
		else if (link.equals("takeattendancepractical")) {
			RequestDispatcher rd1 = request.getRequestDispatcher("takePracticalAttendance.jsp");
			if (rd1 != null) {
				rd1.forward(request, response);
			}
		}
		
		else if (link.equals("dayclassresult")) {
			RequestDispatcher rd1 = request.getRequestDispatcher("dailyClassAttendance.jsp");
			if (rd1 != null) {
				rd1.forward(request, response);
			}
		}
		
		else if (link.equals("daypracticalresult")) {
			RequestDispatcher rd1 = request.getRequestDispatcher("dailyPracticalAttendance.jsp");
			if (rd1 != null) {
				rd1.forward(request, response);
			}
		}
			
		else if (link.equals("monthclassresult")) {
			RequestDispatcher rd1 = request.getRequestDispatcher("monthlyClassAttendance.jsp");
			if (rd1 != null) {
				rd1.forward(request, response);
			}
		}
		
		else if (link.equals("monthpracticalresult")) {
			RequestDispatcher rd1 = request.getRequestDispatcher("monthlyPractcalAttendance.jsp");
			if (rd1 != null) {
				rd1.forward(request, response);
			}
		}
		
		else if (link.equals("semclassresult")) {
			RequestDispatcher rd1 = request.getRequestDispatcher("semClassAttendance.jsp");
			if (rd1 != null) {
				rd1.forward(request, response);
			}
		}
		
		else if (link.equals("sempracticalresult")) {
			RequestDispatcher rd1 = request.getRequestDispatcher("semClassAttendance.jsp");
			if (rd1 != null) {
				rd1.forward(request, response);
			}
		}

		else if (link.equals("generateattendanceclass")) {
			RequestDispatcher rd1 = request.getRequestDispatcher("generateClassAttendance.jsp");
			if (rd1 != null) {
				rd1.forward(request, response);
			}
		}
		
		else if (link.equals("generateattendancepractical")) {
			RequestDispatcher rd1 = request.getRequestDispatcher("generatePracticalAttendance.jsp");
			if (rd1 != null) {
				rd1.forward(request, response);
			}
		}
	}

}
