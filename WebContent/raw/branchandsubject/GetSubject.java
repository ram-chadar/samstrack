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
@WebServlet("/GetSubject")

public class GetSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con =null;
		String attdate,atttime, branch,division, sem, month, from, to, year;
		attdate = request.getParameter("attdates");
		atttime=request.getParameter("atttime");
		branch = request.getParameter("branch");
		division=request.getParameter("division");
		sem = request.getParameter("sem");
		month = request.getParameter("month");
		from = request.getParameter("smonth");
		to = request.getParameter("endmonth");
		year = request.getParameter("year");
		

		String action = request.getParameter("action");

		PrintWriter out = response.getWriter();
		// Establish connection to MySQL database
		// String connectionURL =
		// "jdbc:mysql://node38208-digitalstud.cloud.cms500.com/samsdatabase";

		ResultSet rs;

		response.setContentType("text/html");
		List<String> dataList = new ArrayList<String>();
		List<String> dataList1 = new ArrayList<String>();
		List<String> dataList2 = new ArrayList<String>();


		try {
			con = DBUtil.getDataSource().getConnection();

			String sql = "select  distinct branch from branchdetail";
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				// Add records into data list
				dataList.add(rs.getString("branch"));
			}

			String sql1 = "select distinct sub from branchdetail where branch=? and sem=?";
			ps = con.prepareStatement(sql1);
			ps.setString(1, branch);
			ps.setString(2, sem);

			rs = ps.executeQuery();

			while (rs.next()) {
				// Add records into data list
				dataList1.add(rs.getString("sub"));
			}

			String sql2 = "select  rollno from studentregistration where branch=? and division=? and sem=?";
			ps = con.prepareStatement(sql2);
			ps.setString(1, branch);
			ps.setString(2, division);
			ps.setString(3, sem);

			rs = ps.executeQuery();

			while (rs.next()) {
				// Add records into data list
				dataList2.add(rs.getString("rollno"));

			}
			
			

		} catch (Exception e) {
			out.println(e);
		}

		request.setAttribute("data", dataList);
		request.setAttribute("subject", dataList1);
		request.setAttribute("attrollno", dataList2);
		request.setAttribute("sem", sem);
		request.setAttribute("year", year);
		request.setAttribute("branch", branch);
		request.setAttribute("division", division);
		request.setAttribute("month", month);

		request.setAttribute("smonth", from);
		request.setAttribute("endmonth", to);

		// Disptching request

		if (action.equals("Show Student")) {
			request.setAttribute("attdate", attdate);
			request.setAttribute("atttime", atttime);
			RequestDispatcher rd1 = request.getRequestDispatcher("attendance.jsp");
			if (rd1 != null) {
				rd1.forward(request, response);
			}
		}

		else if (action.equals("Get Subject")) {
			request.setAttribute("attdate", attdate);
			request.setAttribute("atttime", atttime);
			RequestDispatcher rd2 = request.getRequestDispatcher("daily.jsp");
			if (rd2 != null) {
				rd2.forward(request, response);
			}
		}

		else if (action.equals("Get Subjects")) {

			RequestDispatcher rd3 = request.getRequestDispatcher("monthly.jsp");
			if (rd3 != null) {
				rd3.forward(request, response);
			}
		}

		else if (action.equals("Get_Subjects")) {
			RequestDispatcher rd4 = request.getRequestDispatcher("sem.jsp");
			if (rd4 != null) {
				rd4.forward(request, response);
			}
		}
		
		else if (action.equals("Show Status Subjects")) {
			RequestDispatcher rd5 = request.getRequestDispatcher("showStatusOpt.jsp");
			if (rd5 != null) {
				rd5.forward(request, response);
			}
		}
		
		else if (action.equals("genStatusSub")) {
			RequestDispatcher rd5 = request.getRequestDispatcher("generateStatus.jsp");
			if (rd5 != null) {
				rd5.forward(request, response);
			}
		}
		
		else if (action.equals("DetainOptSub")) {
			RequestDispatcher rd5 = request.getRequestDispatcher("detainListOpt.jsp");
			if (rd5 != null) {
				rd5.forward(request, response);
			}
		}
	}

}
