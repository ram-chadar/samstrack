package com.show.status;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.DBUtil;

//import com.dbcon.Conn;

@WebServlet("/showPracticalStatus")
public class ShowPracticalStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accYear = request.getParameter("accYear");
		String month = request.getParameter("month");
		String branch = request.getParameter("branch");
		String division = request.getParameter("division");
		String batch = request.getParameter("batch");
		String sem = request.getParameter("sem");
		String subject = request.getParameter("subject");
		String action = request.getParameter("action");

		int record = 0;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		PrintWriter out = response.getWriter();


		ArrayList<String> list = new ArrayList<>();
		list.clear();

		try {

			if (action.equals("month")) {
				try {
					con = DBUtil.getDataSource().getConnection();

					ps = con.prepareStatement(
							"select rollno,studentname,status,days from practicalmonthstatus where accyear=? and month=? and branch=? and division=? and batch=? and sem=? and subject=?");
					ps.setString(1, accYear);
					ps.setString(2, month);
					ps.setString(3, branch);
					ps.setString(4, division);
					ps.setString(5, batch);
					ps.setString(6, sem);
					ps.setString(7, subject);
					rs = ps.executeQuery();
					while (rs.next()) {
						list.add(rs.getString("rollno"));
						list.add(rs.getString("studentname"));
						list.add(rs.getString("days"));
						list.add(rs.getString("status"));

						record++;

					}
					if (record > 0) {
						request.setAttribute("StatusList", list);
						request.setAttribute("head", "Practical Month Status");
						RequestDispatcher rd = request.getRequestDispatcher("attendanceStatus.jsp");
						rd.forward(request, response);
					} else {
						request.setAttribute("month", month);
						request.setAttribute("division", division);
						request.setAttribute("batch", batch);
						request.setAttribute("sem", sem);
						request.setAttribute("subject", subject);
						request.setAttribute("msg", "Record Not Found Select Proper Fields Or Generate Status");
						RequestDispatcher rd = request.getRequestDispatcher("practicalStatusOption.jsp");
						rd.forward(request, response);
					}

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
			} else if (action.equals("sem")) {
				try {
					con=DBUtil.getDataSource().getConnection();
					ps = con.prepareStatement(
							"select rollno,studentname,status,days from practicalsemstatus where accyear=? and branch=? and division=? and batch=? and sem=? and subject=?");
					ps.setString(1, accYear);
					ps.setString(2, branch);
					ps.setString(3, division);
					ps.setString(4, batch);
					ps.setString(5, sem);
					ps.setString(6, subject);
					rs = ps.executeQuery();
					while (rs.next()) {

						list.add(rs.getString("rollno"));
						list.add(rs.getString("studentname"));
						list.add(rs.getString("days"));
						list.add(rs.getString("status"));

						record++;

					}
					if (record > 0) {
						request.setAttribute("StatusList", list);
						request.setAttribute("head", "Practical Sem Status");
						RequestDispatcher rd = request.getRequestDispatcher("attendanceStatus.jsp");
						rd.forward(request, response);
					} else {
						request.setAttribute("month", month);
						request.setAttribute("division", division);
						request.setAttribute("batch", batch);
						request.setAttribute("sem", sem);
						request.setAttribute("subject", subject);
						request.setAttribute("msg", "Record Not Found Select Proper Fields Or Generate Status");
						RequestDispatcher rd = request.getRequestDispatcher("practicalStatusOption.jsp");
						rd.forward(request, response);
					}

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
						out.println(2);

					}
				}

			}

		} catch (NullPointerException ne) {

			request.setAttribute("msg", "Please Fill All Feilds");
			RequestDispatcher rd = request.getRequestDispatcher("semStatusOption.jsp");
			rd.forward(request, response);
		}

		catch (Exception e) {
			e.printStackTrace();
			out.println(e);

		}

	}

}
