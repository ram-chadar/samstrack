 package com.generate;

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

@WebServlet("/generatePracticalStatus")
public class PractialStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("resource")
	synchronized protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		PrintWriter out = response.getWriter();


		float status = 0;
		String from, to;
		int totalsize = 0, attendsize = 0, result, update = 0, insert = 0;

		List<String> rollnoList = new ArrayList<String>();
		List<String> nameList = new ArrayList<String>();
		List<String> subjectList = new ArrayList<String>();

		String accyear, month, branch, division, batch, sem, year, action;

		accyear = request.getParameter("accYear");
		month = request.getParameter("month");
		branch = request.getParameter("bn");
		division = request.getParameter("division");
		batch = request.getParameter("batch");
		sem = request.getParameter("sem");
		action = request.getParameter("action");
		year = null;

		request.setAttribute("month", month);
		request.setAttribute("division", division);
		request.setAttribute("batch", batch);
		request.setAttribute("sem", sem);

		if (sem.equals("01") || sem.equals("02")) {
			year = "FE";
		} else if (sem.equals("03") || sem.equals("04")) {
			year = "SE";
		} else if (sem.equals("05") || sem.equals("06")) {
			year = "TE";
		} else if (sem.equals("07") || sem.equals("08")) {
			year = "BE";
		}

		try {
			con = DBUtil.getDataSource().getConnection();

			rollnoList.clear();
			nameList.clear(); // clearing lists
			subjectList.clear();
			String sql = "select DISTINCT rollno,studentname from student where  branchname=? and division=? and batch=? and year=? and accyear=?";

			ps = con.prepareStatement(sql);
			ps.setString(1, branch);
			ps.setString(2, division);
			ps.setString(3, batch);
			ps.setString(4, year);
			ps.setString(5, accyear);
			rs = ps.executeQuery();

			while (rs.next()) {

				// Add rollno and name
				rollnoList.add(rs.getString("rollno"));
				nameList.add(rs.getString("studentname"));
			}

			String subjestqry = "select subject from practicalsubject where branchname=? and sem=?";
			ps = con.prepareStatement(subjestqry);
			ps.setString(1, branch);
			ps.setString(2, sem);
			rs = ps.executeQuery();
			while (rs.next()) {
				subjectList.add(rs.getString("subject"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.println(e);
		}

		if (action.equals("monthly")) {
			try {

				for (int j = 0; j < subjectList.size(); j++) { // total subject
					String sql2 = "SELECT count(DISTINCT datetime)from practicalattendance where accyear=? and month=? and branch=? and division=? and batch=? and sem=? and subject=?";
					ps = con.prepareStatement(sql2);
					ps.setString(1, accyear);
					ps.setString(2, month);
					ps.setString(3, branch);
					ps.setString(4, division);
					ps.setString(5, batch);
					ps.setString(6, sem);
					ps.setString(7, subjectList.get(j));

					rs = ps.executeQuery();

					if (rs.next()) {
						totalsize = (Integer.parseInt(rs.getString(1)));
					}
					
					
					for (int k = 0; k < rollnoList.size(); k++) {

						String sql3 = "select count(DISTINCT datetime) from practicalattendance where accyear=? and month=? and branch=? and division=? and batch=? and sem=? and subject=? and rollno=?";
						ps = con.prepareStatement(sql3);
						ps.setString(1, accyear);
						ps.setString(2, month);
						ps.setString(3, branch);
						ps.setString(4, division);
						ps.setString(5, batch);
						ps.setString(6, sem);
						ps.setString(7, subjectList.get(j));
						ps.setString(8, rollnoList.get(k));

						rs = ps.executeQuery();

						if (rs.next()) {
							attendsize = (Integer.parseInt(rs.getString(1)));
						}
						if (attendsize > 0) {
							status = attendsize * 100 / totalsize;

						} else {
							status = 0;
						}
						String sql4 = "select rollno  from practicalmonthstatus where accyear=? and month=? and branch=? and division=? and batch=? and sem=? and subject=? and rollno=?";
						ps = con.prepareStatement(sql4);
						ps.setString(1, accyear);
						ps.setString(2, month);
						ps.setString(3, branch);
						ps.setString(4, division);
						ps.setString(5, batch);
						ps.setString(6, sem);
						ps.setString(7, subjectList.get(j));
						ps.setString(8, String.valueOf(rollnoList.get(k)));

						rs = ps.executeQuery();

						if (rs.next()) {
							// update
							String sql5 = "update practicalmonthstatus set status=?,days=? where rollno=? and branch=? and sem=? and division=? and batch=? and month=? and subject=? and accyear=?";
							ps = con.prepareStatement(sql5);

							ps.setFloat(1, status);
							ps.setString(2, attendsize+" "+" Out Of "+totalsize);
							ps.setString(3, rollnoList.get(k));
							ps.setString(4, branch);
							ps.setString(5, sem);
							ps.setString(6, division);
							ps.setString(7, batch);
							ps.setString(8, month);
							ps.setString(9, subjectList.get(j));
							ps.setString(10, accyear);
							result = ps.executeUpdate();
							if (result > 0) {
								update = update + 1;
							}

						}

						else {
							String sql6 = "insert into practicalmonthstatus(rollno,studentname,branch,division,batch,sem,subject,status,accyear,month,days)values(?,?,?,?,?,?,?,?,?,?,?)";
							ps = con.prepareStatement(sql6);
							ps.setString(1, String.valueOf(rollnoList.get(k)));
							ps.setString(2, String.valueOf(nameList.get(k)));
							ps.setString(3, branch);
							ps.setString(4, division);
							ps.setString(5, batch);
							ps.setString(6, sem);
							ps.setString(7, subjectList.get(j));
							ps.setFloat(8, status);
							ps.setString(9, accyear);
							ps.setString(10, month);
							ps.setString(11, attendsize+" "+" Out Of "+totalsize);


							result = ps.executeUpdate();
							if (result > 0) {
								insert = insert + 1;
							}

						}

					} // for close
				} // subject for close
				if (insert > 0 || update > 0) {
					
					request.setAttribute("msg", update + " " + "Updated And" + " " + insert + " " + "Are Inserted");
					RequestDispatcher rd = request.getRequestDispatcher("generatePracticalAttendance.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("msg", "Record Not Found First Take  ! Check Crediential ");
					RequestDispatcher rd = request.getRequestDispatcher("generatePracticalAttendance.jsp");
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

			/*			*****************************/

		}

		else if (action.equals("semwise")) {

			try {
				con = DBUtil.getDataSource().getConnection();
				if (Integer.parseInt(sem) % 2 == 0) {
					from = "01";
					to = "06";
				} else {
					from = "07";
					to = "12";
				}
				for (int j = 0; j < subjectList.size(); j++) { // total subject
					String qry2 = "SELECT count(DISTINCT datetime)from practicalattendance where accyear=? and month between ? and ? and branch=? and division=? and batch=?  and sem=? and subject=?";

					ps = con.prepareStatement(qry2);
					ps.setString(1, accyear);
					ps.setString(2, from);
					ps.setString(3, to);
					ps.setString(4, branch); // total lecture date Date
					ps.setString(5, division);
					ps.setString(6, batch);
					ps.setString(7, sem);
					ps.setString(8, subjectList.get(j));

					rs = ps.executeQuery();

					if (rs.next()) {

						totalsize = (Integer.parseInt(rs.getString(1)));

					}

					/*			*************************/

					for (int k = 0; k < rollnoList.size(); k++) {

						String qry3 = "select count(DISTINCT datetime) from practicalattendance where accyear=? and month between ? and ? and branch=? and division=? and batch=? and sem=? and subject=? and rollno=?";
						ps = con.prepareStatement(qry3);
						ps.setString(1, accyear);
						ps.setString(2, from);
						ps.setString(3, to);
						ps.setString(4, branch);
						ps.setString(5, division);
						ps.setString(6, batch);
						ps.setString(7, sem);
						ps.setString(8, subjectList.get(j));
						ps.setString(9, String.valueOf(rollnoList.get(k)));

						rs = ps.executeQuery();

						if (rs.next()) {

							attendsize = (Integer.parseInt(rs.getString(1)));

						}

						if (attendsize > 0) {
							status = attendsize * 100 / totalsize;

						}
						else
						{
							status=0;
						}

						String qry4 = "select rollno  from practicalsemstatus where accyear=? and branch=? and division=? and batch=? and sem=? and subject=? and rollno=?";
						ps = con.prepareStatement(qry4);
						ps.setString(1, accyear);
						ps.setString(2, branch);
						ps.setString(3, division);
						ps.setString(4, batch);
						ps.setString(5, sem);
						ps.setString(6, subjectList.get(j));
						ps.setString(7, String.valueOf(rollnoList.get(k)));

						rs = ps.executeQuery();

						if (rs.next()) {
							// update
							String qry5 = "update practicalsemstatus set status=?,days=? where rollno=? and sem=? and branch=? and division=? and batch=? and accyear=? and subject=?";
							ps = con.prepareStatement(qry5);

							ps.setFloat(1, status);
							ps.setString(2, attendsize+" "+" Out Of "+totalsize);
							ps.setString(3, String.valueOf(rollnoList.get(k)));
							ps.setString(4, sem);
							ps.setString(5, branch);
							ps.setString(6, division);
							ps.setString(7, batch);
							ps.setString(8, accyear);
							ps.setString(9, subjectList.get(j));

							result = ps.executeUpdate();
							if (result > 0) {
								update = update + 1;
							}

						}

						else {
							String qry6 = "insert into practicalsemstatus(rollno,studentname,branch,division,batch,sem,subject,status,accyear,days)values(?,?,?,?,?,?,?,?,?,?)";
							ps = con.prepareStatement(qry6);
							ps.setString(1, String.valueOf(rollnoList.get(k)));
							ps.setString(2, String.valueOf(nameList.get(k)));
							ps.setString(3, branch);
							ps.setString(4, division);
							ps.setString(5, batch);
							ps.setString(6, sem);
							ps.setString(7, subjectList.get(j));
							ps.setFloat(8, status);
							ps.setString(9, accyear);
							ps.setString(10, attendsize+" "+" Out Of "+totalsize);


							result = ps.executeUpdate();
							if (result > 0) {
								insert = insert + 1;
							}

						}

					} // for close
				} // total subject close
				if (insert > 0 || update > 0) {
					request.setAttribute("msg", update + " " + "Updated And" + " " + insert + " " + "Are Inserted");
					RequestDispatcher rd = request.getRequestDispatcher("generatePracticalAttendance.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("msg", "Record Not Found For Generate Attendance Status ! Check Crediential ");
					RequestDispatcher rd = request.getRequestDispatcher("generatePracticalAttendance.jsp");
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

	}

}
