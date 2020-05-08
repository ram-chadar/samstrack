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

@WebServlet("/monthlyAttendance")
public class MonthlyAttendance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MonthlyAttendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("resource")
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		float status;
		int nonattendlec;
		int totalsize;
		int attendsize;

		String accYear, month, branch, division, sem, subject, rollno, action;
		accYear = request.getParameter("accYear");
		month = request.getParameter("month");
		branch = request.getParameter("branch");
		division = request.getParameter("division");
		sem = request.getParameter("sem");
		subject = request.getParameter("subject");
		rollno = request.getParameter("rollNO");
		action = request.getParameter("action");

		List<String> attend = new ArrayList<String>();
		List<String> total = new ArrayList<String>();

		PrintWriter out = response.getWriter();
		/*try {

			if (accYear.equals("") || branch.equals("") || sem.equals("") || rollno.equals("") || subject.equals("")
					|| month.equals("") || division.equals("")) {
				request.setAttribute("msg", "Plz Fill All MANDATORY FIELDS");
				RequestDispatcher rd2 = request.getRequestDispatcher("monthlyAttendance.jsp");
				if (rd2 != null) {
					rd2.forward(request, response);
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			request.setAttribute("msg", "Plz Fill All MANDATORY FIELDS");
			RequestDispatcher rd2 = request.getRequestDispatcher("monthlyAttendance.jsp");
			if (rd2 != null) {
				rd2.forward(request, response);
			}
		}*/
		if (action.equals("class")) {
			try {
				con=DBUtil.getDataSource().getConnection();
				String sql1 = "select distinct datetime from classattendance where accyear=? and month=? and branch=? and division=? and sem=? and subject=? and rollno=? ORDER BY datetime";
				ps = con.prepareStatement(sql1);
				ps.setString(1, accYear);
				ps.setString(2, month);
				ps.setString(3, branch);
				ps.setString(4, division);
				ps.setString(5, sem);
				ps.setString(6, subject);
				ps.setString(7, rollno);

				rs = ps.executeQuery();

				while (rs.next()) {
					// Add records into data list
					attend.add(rs.getString("datetime"));

				}

				String sql2 = "select distinct datetime from classattendance where accyear=? and month=? and branch=? and division=? and sem=? and subject=? ORDER BY datetime";
				ps = con.prepareStatement(sql2);
				ps.setString(1, accYear);
				ps.setString(2, month);
				ps.setString(3, branch);
				ps.setString(4, division);
				ps.setString(5, sem);
				ps.setString(6, subject);

				rs = ps.executeQuery();

				while (rs.next()) {
					// Add records into data list
					total.add(rs.getString("datetime"));

				}

				totalsize = total.size();
				attendsize = attend.size();
				if (attendsize > 0) {
					status = attendsize * 100 / totalsize;
				} else {
					status = 0;
				}
				nonattendlec = totalsize - attendsize;

				request.setAttribute("sem", sem);
				request.setAttribute("accYear", accYear);
				// request.setAttribute("branch", branch);
				request.setAttribute("subject", subject);
				request.setAttribute("month", month);
				request.setAttribute("division", division);
				request.setAttribute("rollNo", rollno);

				request.setAttribute("nonattendlec", nonattendlec);
				request.setAttribute("totlec", totalsize);
				request.setAttribute("attlec", attendsize);
				request.setAttribute("status", status);

				request.setAttribute("attenddate", attend);
				request.setAttribute("totaldate", total);
				request.setAttribute("heading", "MONTHLY CLASS ATTENDANCE RESULT");
				// Disptching request
				if (totalsize <= 0) {
					request.setAttribute("msg", "No Any Lecture For This Month Check All MANDATORY FIELDS");
				} else {
					request.setAttribute("msg", "Total Lectute " + totalsize + " Attend Lecture" + attendsize
							+ " Status In % " + status + "%");

				}
				RequestDispatcher rd2 = request.getRequestDispatcher("monthlyAttendance.jsp");
				if (rd2 != null) {
					rd2.forward(request, response);
				}

			} 
			catch (Exception e) {
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

		} // class if close

		else if (action.equals("practical")) {
			try {

				con = DBUtil.getDataSource().getConnection();
				String sql1 = "select distinct datetime from practicalattendance where accyear=? and month=? and branch=? and division=? and sem=? and subject=? and rollno=?";
				ps = con.prepareStatement(sql1);
				ps.setString(1, accYear);
				ps.setString(2, month);
				ps.setString(3, branch);
				ps.setString(4, division);
				// ps.setString(5, batch); // attend list
				ps.setString(5, sem);
				ps.setString(6, subject);
				ps.setString(7, rollno);

				rs = ps.executeQuery();

				while (rs.next()) {
					// Add records into data list
					attend.add(rs.getString("datetime"));

				}

				String sql2 = "select distinct datetime from practicalattendance where accyear=? and month=? and branch=? and division=?  and sem=? and subject=?";
				ps = con.prepareStatement(sql2);
				ps.setString(1, accYear);
				ps.setString(2, month);
				ps.setString(3, branch);
				ps.setString(4, division);
				// ps.setString(5, batch); // Total list
				ps.setString(5, sem);
				ps.setString(6, subject);

				rs = ps.executeQuery();

				while (rs.next()) {
					// Add records into data list
					total.add(rs.getString("datetime"));

				}

				totalsize = total.size();
				attendsize = attend.size();
				if (attendsize > 0) {
					status = attendsize * 100 / totalsize;
				} else {
					status = 0;
				}
				nonattendlec = totalsize - attendsize;

				request.setAttribute("sem", sem);
				request.setAttribute("accYear", accYear);
				request.setAttribute("branch", branch);
				request.setAttribute("subject", subject);
				request.setAttribute("month", month);
				request.setAttribute("division", division);
				// request.setAttribute("batch", batch);
				request.setAttribute("rollNo", rollno);

				request.setAttribute("nonattendlec", nonattendlec);
				request.setAttribute("totlec", totalsize);
				request.setAttribute("attlec", attendsize);
				request.setAttribute("status", status);

				request.setAttribute("attenddate", attend);
				request.setAttribute("totaldate", total);

				request.setAttribute("heading", "MONTHLY PRACTICAL ATTENDANCE RESULT");

				if (totalsize <= 0) {
					request.setAttribute("msg", "No Any Lecture For This Month Check All MANDATORY FIELDS");
				} else {
					request.setAttribute("msg", "Total Lectute " + totalsize + " Attend Lecture  " + attendsize
							+ " Status In % " + status + "%");

				}
				// Disptching request
				RequestDispatcher rd2 = request.getRequestDispatcher("monthlyAttendance.jsp");
				if (rd2 != null) {
					rd2.forward(request, response);
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
		}
	}
}
