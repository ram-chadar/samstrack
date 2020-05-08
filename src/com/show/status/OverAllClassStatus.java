package com.show.status;

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

@WebServlet("/overAllClassStatus")
public class OverAllClassStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("resource")
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PrintWriter out = response.getWriter();

		try {
			String accYear = request.getParameter("accYear");
			String month = request.getParameter("month");
			String branch = request.getParameter("branch");
			String division = request.getParameter("division");
			String sem = request.getParameter("sem");
			String action = request.getParameter("action");

			int record = 0;

			List<String> subjects = new ArrayList<>();
			List<String> statusList = new ArrayList<>();
			List<String> rollnoList = new ArrayList<>();
			List<String> totLectureList = new ArrayList<>();
			subjects.clear();
			statusList.clear();
			rollnoList.clear();
			totLectureList.clear();

			if (action.equals("Show Month Status")) {
				try {
					con = DBUtil.getDataSource().getConnection();

					ps = con.prepareStatement("select distinct rollno from classsmonthstatus where "
							+ "accyear=? and sem=? and branch=? and division=?");
					ps.setString(1, accYear);
					ps.setString(2, sem);
					ps.setString(3, branch);
					ps.setString(4, division);

					rs = ps.executeQuery();

					while (rs.next()) {
						rollnoList.add(rs.getString("rollno"));
					}
					/*************************************************************************************************************************/
					ps = con.prepareStatement("select distinct subject from classsmonthstatus where "
							+ "accyear=? and sem=? and branch=?");
					ps.setString(1, accYear);
					ps.setString(2, sem);
					ps.setString(3, branch);

					rs = ps.executeQuery();

					while (rs.next()) {
						subjects.add(rs.getString("subject"));
					}
					/************************************************************************************************************/

					/**************************************************************************************************************/

					for (int i = 0; i < subjects.size(); i++) {
						ps = con.prepareStatement(
								"SELECT COUNT(DISTINCT datetime) from  classattendance where accyear=? and month=? and branch=? and division=? and sem=? and subject=?");
						ps.setString(1, accYear);
						ps.setString(2, month);
						ps.setString(3, branch);
						ps.setString(4, division);
						ps.setString(5, sem);
						ps.setString(6, subjects.get(i));
						rs = ps.executeQuery();

						while (rs.next()) {
							totLectureList.add(rs.getString(1));
							totLectureList.add("100%");
						}

					}

					/**************************************************************************************************/

					for (int i = 0; i < rollnoList.size(); i++) {
						ps = con.prepareStatement("select studentname from classsmonthstatus where "
								+ " accyear=? and sem=? and branch=? and division=? and rollno=?");
						ps.setString(1, accYear);
						ps.setString(2, sem);
						ps.setString(3, branch);
						ps.setString(4, division);
						ps.setString(5, rollnoList.get(i));

						rs = ps.executeQuery();
						if (rs.next()) {
							statusList.add(rollnoList.get(i));
							statusList.add(rs.getString("studentname"));

						}

						for (int j = 0; j < subjects.size(); j++) {

							ps = con.prepareStatement(
									"SELECT COUNT(DISTINCT datetime) from  classattendance where accyear=? and month=? and branch=? and division=? and sem=? and subject=? and rollno=?");
							ps.setString(1, accYear);
							ps.setString(2, month);
							ps.setString(3, branch);
							ps.setString(4, division);
							ps.setString(5, sem);
							ps.setString(6, subjects.get(j));
							ps.setString(7, rollnoList.get(i));
							rs = ps.executeQuery();

							while (rs.next()) {
								statusList.add(rs.getString(1));

							}

							ps = con.prepareStatement("select status from classsmonthstatus where "
									+ " accyear=? and sem=? and month=? and branch=? and division=? and rollno=? and subject=? ");

							ps.setString(1, accYear);
							ps.setString(2, sem);
							ps.setString(3, month);
							ps.setString(4, branch);
							ps.setString(5, division);
							ps.setString(6, rollnoList.get(i));
							ps.setString(7, subjects.get(j));

							rs = ps.executeQuery();
							while (rs.next()) {

								statusList.add(rs.getString("status"));
								record++;
							}

						}
					}
					if (record > 0) {
						request.setAttribute("statusList", statusList);
						request.setAttribute("subjectSize", subjects.size());
						request.setAttribute("subjects", subjects);
						request.setAttribute("totLectureList", totLectureList);
						request.setAttribute("heading", "Over All Class Attendance [ Monthly ]");
						RequestDispatcher rd = request.getRequestDispatcher("showOverAllStatus.jsp");
						rd.forward(request, response);
					} else {
						request.setAttribute("month", month);
						request.setAttribute("division", division);
						request.setAttribute("sem", sem);

						request.setAttribute("msg", "Record Not Found Select Proper Fields Or Generate Status");
						RequestDispatcher rd = request.getRequestDispatcher("overAllClassStatus.jsp");
						rd.forward(request, response);
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
			} else {
				try {
					con = DBUtil.getDataSource().getConnection();
					ps = con.prepareStatement("select distinct rollno from classsemstatus where "
							+ "accyear=? and sem=? and branch=? and division=?");
					ps.setString(1, accYear);
					ps.setString(2, sem);
					ps.setString(3, branch);
					ps.setString(4, division);

					rs = ps.executeQuery();

					while (rs.next()) {
						rollnoList.add(rs.getString("rollno"));
					}
					/*************************************************************************************************************************/
					ps = con.prepareStatement(
							"select distinct subject from classsemstatus where " + "accyear=? and sem=? and branch=?");
					ps.setString(1, accYear);
					ps.setString(2, sem);
					ps.setString(3, branch);

					rs = ps.executeQuery();

					while (rs.next()) {
						subjects.add(rs.getString("subject"));
					}
					/************************************************************************************************************/

					/**************************************************************************************************************/

					for (int i = 0; i < subjects.size(); i++) {
						ps = con.prepareStatement(
								"SELECT COUNT(DISTINCT datetime) from  classattendance where accyear=? and branch=? and division=? and sem=? and subject=?");
						ps.setString(1, accYear);
						ps.setString(2, branch);
						ps.setString(3, division);
						ps.setString(4, sem);
						ps.setString(5, subjects.get(i));
						rs = ps.executeQuery();

						while (rs.next()) {
							totLectureList.add(rs.getString(1));
							totLectureList.add("100%");
						}

					}

					/**************************************************************************************************/

					for (int i = 0; i < rollnoList.size(); i++) {
						ps = con.prepareStatement("select studentname from classsemstatus where "
								+ " accyear=? and sem=? and branch=? and division=? and rollno=?");
						ps.setString(1, accYear);
						ps.setString(2, sem);
						ps.setString(3, branch);
						ps.setString(4, division);
						ps.setString(5, rollnoList.get(i));

						rs = ps.executeQuery();
						if (rs.next()) {
							statusList.add(rollnoList.get(i));
							statusList.add(rs.getString("studentname"));

						}

						for (int j = 0; j < subjects.size(); j++) {

							ps = con.prepareStatement(
									"SELECT COUNT(DISTINCT datetime) from  classattendance where accyear=? and branch=? and division=? and sem=? and subject=? and rollno=?");
							ps.setString(1, accYear);
							ps.setString(2, branch);
							ps.setString(3, division);
							ps.setString(4, sem);
							ps.setString(5, subjects.get(j));
							ps.setString(6, rollnoList.get(i));
							rs = ps.executeQuery();

							while (rs.next()) {
								statusList.add(rs.getString(1));

							}

							ps = con.prepareStatement("select status from classsemstatus where "
									+ " accyear=? and sem=? and branch=? and division=? and rollno=? and subject=? ");

							ps.setString(1, accYear);
							ps.setString(2, sem);
							ps.setString(3, branch);
							ps.setString(4, division);
							ps.setString(5, rollnoList.get(i));
							ps.setString(6, subjects.get(j));

							rs = ps.executeQuery();
							while (rs.next()) {
								statusList.add(rs.getString("status"));
								record++;
							}

						}
					}
					if (record > 0) {
						request.setAttribute("statusList", statusList);
						request.setAttribute("subjectSize", subjects.size());
						request.setAttribute("subjects", subjects);
						request.setAttribute("totLectureList", totLectureList);
						request.setAttribute("heading", "Over All Class Attendance [ Sem ]");
						RequestDispatcher rd = request.getRequestDispatcher("showOverAllStatus.jsp");
						rd.forward(request, response);
					} else {
						request.setAttribute("month", month);
						request.setAttribute("division", division);
						request.setAttribute("sem", sem);

						request.setAttribute("msg", "Record Not Found Select Proper Fields Or Generate Status");
						RequestDispatcher rd = request.getRequestDispatcher("overAllClassStatus.jsp");
						rd.forward(request, response);
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
