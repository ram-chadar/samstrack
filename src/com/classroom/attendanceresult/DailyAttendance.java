package com.classroom.attendanceresult;

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

import com.dbcon.DBUtil;

//import com.dbcon.Conn;

@WebServlet("/dailyAttendance")
public class DailyAttendance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DailyAttendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		

		int count = 0;
		String datetime = request.getParameter("datetime");
		String branch = request.getParameter("branch");
		String division = request.getParameter("division");
		String sem = request.getParameter("sem");
		String subject = request.getParameter("subject");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		List<String> dayAttendance = new ArrayList<>();
		String sql=null;
		if(action.equals("class"))
		{
			request.setAttribute("heading", " Daily Theory Attendance ");
			sql="select studentname,rollno from classattendance where datetime=? and branch=? and division=? and sem=? and subject=? ORDER BY rollno";
		}
		else if(action.equals("practical"))
		{
			request.setAttribute("heading", " Daily Practical Attendance ");
			sql="select studentname,rollno from practicalattendance where datetime=? and branch=? and division=? and sem=? and subject=? ORDER BY rollno";
		}

		try {
			con = DBUtil.getDataSource().getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, datetime);
			ps.setString(2, branch);
			ps.setString(3, division);
			ps.setString(4, sem);
			ps.setString(5, subject);

			rs = ps.executeQuery();

			while (rs.next()) {
				dayAttendance.add(rs.getString("rollno"));
				dayAttendance.add(rs.getString("studentname"));
				count++;
			}
			request.setAttribute("datetime", datetime);
			request.setAttribute("branch", branch);
			request.setAttribute("division", division);
			request.setAttribute("sem", sem);
			request.setAttribute("subject", subject);
			request.setAttribute("dayAttendance", dayAttendance);
			if (count <= 0) {
				request.setAttribute("msg", "No Any Record For Given Crediential ! Please Check Your Crediential");
			} else {
				request.setAttribute("msg", count + " Students Found");
			}
			RequestDispatcher rd = request.getRequestDispatcher("dailyAttendance.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			out.println(e);
		} finally {
			try {
				con.close();
				rs.close();
				ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				out.println(e2);
			}
		}

	}

}
