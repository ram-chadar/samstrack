
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

@WebServlet("/semAttendance")
public class SemAttendance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SemAttendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("resource")
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		float status = 0;
		int nonattendlec, attendsize, totalSize;

		String accyear, smonth, emonth, branch, division, sem, subject, rollno, action;
		accyear = request.getParameter("accYear");
		smonth = request.getParameter("monthFrom");
		emonth = request.getParameter("monthTo");
		branch = request.getParameter("branch");
		division = request.getParameter("division");
		sem = request.getParameter("sem");
		subject = request.getParameter("subject");
		rollno = request.getParameter("rollNO");
		action = request.getParameter("action");

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		PrintWriter out = response.getWriter();

		List<String> attend = new ArrayList<String>();
		List<String> total = new ArrayList<String>();

		try {
			if (action.equals("class")) {
				try {
					con = DBUtil.getDataSource().getConnection();
					String sql1 = "select DISTINCT datetime from classattendance where branch=? and division=? and sem=? and subject=? and rollno=? and month between ? and ? and accyear=?";
					ps = con.prepareStatement(sql1);
					ps.setString(1, branch);
					ps.setString(2, division);
					ps.setString(3, sem);
					ps.setString(4, subject);
					ps.setString(5, rollno);
					ps.setString(6, smonth);
					ps.setString(7, emonth);
					ps.setString(8, accyear);

					rs = ps.executeQuery();

					while (rs.next()) {
						// Add records into data list
						attend.add(rs.getString("datetime"));

					}

					// total lecture
					String sql2 = "select DISTINCT datetime from classattendance where branch=? and division=? and sem=? and subject=? and  month between ? and ? and accyear=?";
					ps = con.prepareStatement(sql2);
					ps.setString(1, branch);
					ps.setString(2, division);
					ps.setString(3, sem);
					ps.setString(4, subject);
					ps.setString(5, smonth);
					ps.setString(6, emonth);
					ps.setString(7, accyear);

					rs = ps.executeQuery();

					while (rs.next()) {
						// Add records into data list
						total.add(rs.getString("datetime"));

					}

					attendsize = attend.size();
					totalSize = total.size();

					if (attendsize > 0) {
						status = attendsize * 100 / totalSize;
					} else {
						status = 0;
					}

					if (totalSize <= 0) {
						request.setAttribute("msg", "No Any Lecture For This Sem Check All MANDATORY FIELDS");

					} else {
						request.setAttribute("msg", "Total Lectute " + totalSize + " Attend Lecture  " + attendsize
								+ " Status In % " + status + "%");

					}

					nonattendlec = totalSize - attendsize;
					request.setAttribute("sem", sem);
					request.setAttribute("accYear", accyear);
					request.setAttribute("branch", branch);
					request.setAttribute("subject", subject);
					request.setAttribute("monthFrom", smonth);
					request.setAttribute("monthTo", emonth);

					request.setAttribute("division", division);

					request.setAttribute("rollNo", rollno);
					request.setAttribute("heading", "Sem THEORY ATTENDANCE RESULT");

					request.setAttribute("nonattendlec", nonattendlec);
					request.setAttribute("totlec", totalSize);
					request.setAttribute("attlec", attendsize);
					request.setAttribute("status", status);

					request.setAttribute("attenddatesem", attend);
					request.setAttribute("totaldatesem", total);// Disptching request
					RequestDispatcher rd2 = request.getRequestDispatcher("semAttendance.jsp");
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

			} else if (action.equals("practical"))

			{
				try {
					con = DBUtil.getDataSource().getConnection();
					String sql1 = "select distinct datetime from practicalattendance where accyear=? and month between ? and ? and branch=? and division=? and sem=? and subject=? and rollno=?";
					ps = con.prepareStatement(sql1);
					ps.setString(1, accyear);
					ps.setString(2, smonth);
					ps.setString(3, emonth);

					ps.setString(4, branch);
					ps.setString(5, division);

					ps.setString(6, sem);
					ps.setString(7, subject);
					ps.setString(8, rollno);

					rs = ps.executeQuery();

					while (rs.next()) {
						// Add records into data list
						attend.add(rs.getString("datetime"));

					}

					String sql2 = "select distinct datetime from practicalattendance where accyear=? and month between ? and ? and division=? and batch=? and sem=? and subject=?";
					ps = con.prepareStatement(sql2);
					ps.setString(1, accyear);
					ps.setString(2, smonth);
					ps.setString(3, emonth);

					ps.setString(4, branch);
					ps.setString(5, division);

					ps.setString(6, sem);
					ps.setString(7, subject);

					rs = ps.executeQuery();

					while (rs.next()) {
						// Add records into data list
						total.add(rs.getString("datetime"));

					}

					totalSize = total.size();
					attendsize = attend.size();
					if (attendsize > 0) {
						status = attendsize * 100 / totalSize;
					} else {
						status = 0;
					}
					if (totalSize <= 0) {
						request.setAttribute("msg", "No Any Practical For This Sem Check All MANDATORY FIELDS");

					} else {
						request.setAttribute("msg", "Total Practical " + totalSize + " Attend Practical  " + attendsize
								+ " Status In % " + status + "%");

					}
					nonattendlec = totalSize - attendsize;

					request.setAttribute("sem", sem);
					request.setAttribute("accYear", accyear);
					request.setAttribute("branch", branch);
					request.setAttribute("subject", subject);
					request.setAttribute("smonth", smonth);
					request.setAttribute("emonth", emonth);

					request.setAttribute("division", division);

					request.setAttribute("rollNo", rollno);
					request.setAttribute("heading", "SEM PRACTICAL ATTENDANCE RESULT");

					request.setAttribute("nonattendlec", nonattendlec);
					request.setAttribute("totlec", totalSize);
					request.setAttribute("attlec", attendsize);
					request.setAttribute("status", status);

					request.setAttribute("attenddatesem", attend);
					request.setAttribute("totaldatesem", total);

					if (totalSize <= 0) {
						request.setAttribute("msg", "No Any Praticalc For This Sem Check All MANDATORY FIELDS");
					}
					// Disptching request
					RequestDispatcher rd2 = request.getRequestDispatcher("semAttendance.jsp");
					if (rd2 != null) {
						rd2.forward(request, response);
					}

				} catch (Exception e) {
					e.printStackTrace();
					out.println(e);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.println(e);
		} finally {
			try {
				con.close();
				ps.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				out.println(e2);
			}
		}

	}

}
