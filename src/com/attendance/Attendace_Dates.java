package com.attendance;

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

/**
 * Servlet implementation class Attendace_Dates
 */
@WebServlet("/attendace_Dates")
public class Attendace_Dates extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String branch, division, sem, subject, action, batch;
		branch = request.getParameter("branch");
		division = request.getParameter("division");
		sem = request.getParameter("sem");
		subject = request.getParameter("subject");
		action = request.getParameter("action");
		batch = request.getParameter("batch");
		List<String> list = new ArrayList<>();
		int srno = 1;
		
		PrintWriter out=response.getWriter();
		

		if (action.equals("theory")) {
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement ps = null;

			try {
				con = DBUtil.getDataSource().getConnection();
				ps = con.prepareStatement(
						"select DISTINCT (datetime),month from classattendance where branch=? and division=? and sem=? and subject=?");
				ps.setString(1, branch);
				ps.setString(2, division);
				ps.setString(3, sem);
				ps.setString(4, subject);

				rs = ps.executeQuery();
				while (rs.next()) {

					list.add(String.valueOf(srno));
					list.add(rs.getString("datetime"));
					list.add(rs.getString("month"));
					list.add(rs.getString("datetime"));
					srno++;
				}

				if (srno > 0) {
					request.setAttribute("branch", branch);
					request.setAttribute("division", division);
					request.setAttribute("sem", sem);
					request.setAttribute("subject", subject);
					request.setAttribute("dates", list);
					RequestDispatcher rd = request.getRequestDispatcher("theoryAttendanceDates.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("msg", "record Not Found ! Take Attendance");
					RequestDispatcher rd = request.getRequestDispatcher("theoryAttendaceDatesOption.jsp");
					rd.forward(request, response);
				}

			} catch (Exception e) {
				out.println(e);
				e.printStackTrace();
			} finally {
				try {
					if (con != null)
						con.close();
					if (rs != null)
						rs.close();
					if (ps != null)
						ps.close();
				} catch (Exception e2) {
					out.println(e2);
					e2.printStackTrace();
				}
			}
		}

		else if (action.equals("practical")) {
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement ps = null;
			try {
				con = DBUtil.getDataSource().getConnection();
				ps = con.prepareStatement(
						"select DISTINCT (datetime),month,batch from practicalattendance where branch=? and division=? and sem=? and subject=? and batch=?");
				ps.setString(1, branch);
				ps.setString(2, division);
				ps.setString(3, sem);
				ps.setString(4, subject);
				ps.setString(5, batch);
				rs = ps.executeQuery();
				while (rs.next()) {

					list.add(String.valueOf(srno));
					list.add(rs.getString("datetime"));
					list.add(rs.getString("month"));
					list.add(rs.getString("datetime"));
					srno++;
				}
				if (srno > 0) {
					request.setAttribute("branch", branch);
					request.setAttribute("division", division);
					request.setAttribute("batch", batch);
					request.setAttribute("sem", sem);
					request.setAttribute("subject", subject);
					request.setAttribute("dates", list);
					RequestDispatcher rd = request.getRequestDispatcher("practicalAttendanceDates.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("msg", "record Not Found ! Take Attendance");
					RequestDispatcher rd = request.getRequestDispatcher("practicalAttendanceDateOption.jsp");
					rd.forward(request, response);
				}

			} catch (Exception e) {
				
				out.println(e);
				e.printStackTrace();
			} finally {
				try {
					if(con!=null)
						con.close();
					if(rs!=null)
						rs.close();
					if(ps!=null)
						ps.close();
				} catch (Exception e2) {
					out.println(e2);
					e2.printStackTrace();
				}
			}
		}
	}

}
