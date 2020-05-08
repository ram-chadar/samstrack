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

@WebServlet("/generateClassStatus")
public class ClassStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("resource")
	synchronized protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accyear, month, branch, division, sem, year, action;

		accyear = request.getParameter("accYear");
		month = request.getParameter("month");
		branch = request.getParameter("bn");
		division = request.getParameter("division");
		sem = request.getParameter("sem");
		year = null;
		action = request.getParameter("action");

		request.setAttribute("month", month);
		request.setAttribute("division", division);
		request.setAttribute("sem", sem);

		float status = 0;
		String from, to;
		int totalsize = 0, attendsize = 0, result, update = 0, insert = 0;

		List<String> rollnoList = new ArrayList<String>();
		List<String> nameList = new ArrayList<String>();
		List<String> subjectList = new ArrayList<String>();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		PrintWriter out = response.getWriter();

		try {

			if (sem.equals("01") || sem.equals("02")) {
				year = "FE";
			} else if (sem.equals("03") || sem.equals("04")) {
				year = "SE";
			} else if (sem.equals("05") || sem.equals("06")) {
				year = "TE";
			} else if (sem.equals("07") || sem.equals("08")) {
				year = "BE";
			}
			
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			request.setAttribute("msg", "Pleaze Fill All Feilds");
			RequestDispatcher rd = request.getRequestDispatcher("generateClassAttendance.jsp");
			rd.forward(request, response);
		}

		
		try {
			con = DBUtil.getDataSource().getConnection();

			rollnoList.clear();
			nameList.clear(); // clearing lists
			subjectList.clear();
			String sql = "select DISTINCT rollno,studentname from student where  branchname=? and division=? and year=? and accyear=?";

			ps = con.prepareStatement(sql);
			ps.setString(1, branch);
			ps.setString(2, division);
			ps.setString(3, year);
			ps.setString(4, accyear);
			rs = ps.executeQuery();

			while (rs.next()) {

				// Add rollno and name
				rollnoList.add(rs.getString("rollno"));
				nameList.add(rs.getString("studentname"));
			}

			String subjestqry = "select subject from subject where branchname=? and sem=?";
			PreparedStatement p = con.prepareStatement(subjestqry);
			p.setString(1, branch);
			p.setString(2, sem);
			rs = p.executeQuery();
			while (rs.next()) {
				subjectList.add(rs.getString("subject"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			out.println(e);
		}

		if (action.equals("monthly")) {
			 System.out.println("Generate For Monthly");
			try {

				for (int j = 0; j < subjectList.size(); j++) { // total subject
					String sql2 = "SELECT count(DISTINCT datetime)from classattendance where accyear=? and month=? and branch=? and division=? and sem=? and subject=?";
					ps = con.prepareStatement(sql2);
					ps.setString(1, accyear);
					ps.setString(2, month);
					ps.setString(3, branch);
					ps.setString(4, division);
					ps.setString(5, sem);
					ps.setString(6, subjectList.get(j));

					rs = ps.executeQuery();

					if (rs.next()) {

						totalsize = (Integer.parseInt(rs.getString(1)));
					}
					for (int k = 0; k < rollnoList.size(); k++) {

						String sql3 = "select count(DISTINCT datetime) from classattendance where accyear=? and month=? and branch=? and division=? and sem=? and subject=? and rollno=?";
						PreparedStatement ps3 = con.prepareStatement(sql3);
						ps3.setString(1, accyear);
						ps3.setString(2, month);
						ps3.setString(3, branch);
						ps3.setString(4, division);
						ps3.setString(5, sem);
						ps3.setString(6, subjectList.get(j));
						ps3.setString(7, String.valueOf(rollnoList.get(k)));

						rs = ps3.executeQuery();

						if (rs.next()) {
							attendsize = (Integer.parseInt(rs.getString(1)));
						}

						if (attendsize > 0) {
							status = attendsize * 100 / totalsize;
						} else {
							status = 0;
						}
						String sql4 = "select rollno  from classsmonthstatus where accyear=? and month=? and branch=? and division=? and sem=? and subject=? and rollno=?";
						ps = con.prepareStatement(sql4);
						ps.setString(1, accyear);
						ps.setString(2, month);
						ps.setString(3, branch);
						ps.setString(4, division);
						ps.setString(5, sem);
						ps.setString(6, subjectList.get(j));
						ps.setString(7, String.valueOf(rollnoList.get(k)));

						rs = ps.executeQuery();

						if (rs.next()) {
							// update
							String sql5 = "update classsmonthstatus set status=? ,days=? where rollno=? and branch=? and sem=? and division=? and month=? and subject=? and accyear=?";
							ps = con.prepareStatement(sql5);

							ps.setFloat(1, status);
							ps.setString(2, attendsize+" "+" Out Of "+totalsize);
							ps.setString(3, String.valueOf(rollnoList.get(k)));
							ps.setString(4, branch);
							ps.setString(5, sem);
							ps.setString(6, division);
							ps.setString(7, month);
							ps.setString(8, subjectList.get(j));
							ps.setString(9, accyear);
							
							result = ps.executeUpdate();
							if (result > 0) {
								update = update + 1;
							}

						}

						else {
							String sql6 = "insert into classsmonthstatus(rollno,studentname,branch,division,sem,subject,status,accyear,month,days)values(?,?,?,?,?,?,?,?,?,?)";
							ps = con.prepareStatement(sql6);
							ps.setString(1, String.valueOf(rollnoList.get(k)));
							ps.setString(2, String.valueOf(nameList.get(k)));
							ps.setString(3, branch);
							ps.setString(4, division);
							ps.setString(5, sem);
							ps.setString(6, subjectList.get(j));
							ps.setFloat(7, status);
							ps.setString(8, accyear);
							ps.setString(9, month);
							ps.setString(10, attendsize+" "+" Out Of "+totalsize);


							result = ps.executeUpdate();
							if (result > 0) {
								insert = insert + 1;
							}

						}

					} // roll for close
				} // subject for close
				if (insert > 0 || update > 0) {
					
					request.setAttribute("msg", update + " " + "Updated And" + " " + insert + " " + "Are Inserted");
				} else {
					request.setAttribute("msg", "Record Not Found For Generate Attendance Status ! Check Crediential ");
				}
				RequestDispatcher rd = request.getRequestDispatcher("generateClassAttendance.jsp");
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

			/*			*****************************/

		}

		else if (action.equals("semwise")) {
			// System.out.println("Generate Sor Sem");

			try {
				con = DBUtil.getDataSource().getConnection();
				if (Integer.parseInt(sem) % 2 == 0) { //even sem
					from = "01";
					to = "06";
				} else { //odd sem
					from = "07";
					to = "12";
				}
				// System.out.println("from="+from +" AND to="+to);

				for (int j = 0; j < subjectList.size(); j++) { // total subject
					String qry2 = "SELECT count(DISTINCT datetime)from classattendance where accyear=? and month between ? and ? and branch=? and division=?  and sem=? and subject=?";

					ps = con.prepareStatement(qry2);
					ps.setString(1, accyear);
					ps.setString(2, from);
					ps.setString(3, to);
					ps.setString(4, branch); // total lecture date Date
					ps.setString(5, division);
					ps.setString(6, sem);
					ps.setString(7, subjectList.get(j));

					rs = ps.executeQuery();

					if (rs.next()) {

						totalsize = (Integer.parseInt(rs.getString(1)));

					}
					// System.out.println("Total Dates size" + totalsize +"For "+subjectList.get(j)
					// );

					/*			*************************/

					for (int k = 0; k < rollnoList.size(); k++) {

						String qry3 = "select count(DISTINCT datetime) from classattendance where accyear=? and month between ? and ? and branch=? and division=? and sem=? and subject=? and rollno=?";
						ps = con.prepareStatement(qry3);
						ps.setString(1, accyear);
						ps.setString(2, from);
						ps.setString(3, to);
						ps.setString(4, branch);
						ps.setString(5, division);
						ps.setString(6, sem);
						ps.setString(7, subjectList.get(j));
						ps.setString(8, rollnoList.get(k));

						rs = ps.executeQuery();

						if (rs.next()) {

							attendsize = (Integer.parseInt(rs.getString(1)));

						}

						// System.out.println("Roll no"+rollnoList.get(k)+"Attend Size " + attendsize+
						// "for "+subjectList.get(j));
						if (attendsize > 0) {
							status = attendsize * 100 / totalsize;

						} else {
							status = 0.0f;
						}
						// System.out.println(status);

						String qry4 = "select rollno  from classsemstatus where accyear=? and branch=? and division=? and sem=? and subject=? and rollno=?";
						ps = con.prepareStatement(qry4);
						ps.setString(1, accyear);
						ps.setString(2, branch);
						ps.setString(3, division);
						ps.setString(4, sem);
						ps.setString(5, subjectList.get(j));
						ps.setString(6, String.valueOf(rollnoList.get(k)));

						rs = ps.executeQuery();

						if (rs.next()) {
							// update
							String qry5 = "update classsemstatus set status=?,days=? where rollno=? and sem=? and branch=? and division=? and accyear=? and subject=?";
							ps = con.prepareStatement(qry5);

							ps.setFloat(1, status);
							ps.setString(2, attendsize+" "+"Out Of "+totalsize);
							ps.setString(3, String.valueOf(rollnoList.get(k)));
							ps.setString(4, sem);
							ps.setString(5, branch);
							ps.setString(6, division);
							ps.setString(7, accyear);
							ps.setString(8, subjectList.get(j));

							result = ps.executeUpdate();
							if (result > 0) {
								// System.out.println("updated status"+status);
								update = update + 1;
							}

						}

						else {
							String qry6 = "insert into classsemstatus(rollno,studentname,branch,division,sem,subject,status,accyear,days)values(?,?,?,?,?,?,?,?,?)";
							ps = con.prepareStatement(qry6);
							ps.setString(1, rollnoList.get(k));
							ps.setString(2, nameList.get(k));
							ps.setString(3, branch);
							ps.setString(4, division);
							ps.setString(5, sem);
							ps.setString(6, subjectList.get(j));
							ps.setFloat(7, status);
							ps.setString(8, accyear);
							ps.setString(9, attendsize+" "+"Out Of "+totalsize);


							result = ps.executeUpdate();
							if (result > 0) {
								insert = insert + 1;
							}

						}

					} // roll for close
				} // total subject close
				if (insert > 0 || update > 0) {
					request.setAttribute("msg", update + " " + "Updated And" + " " + insert + " " + "Are Inserted ");
				} else {
					request.setAttribute("msg", "Record Not Found For Generate Attendance Status ! Check Crediential ");
				}
				RequestDispatcher rd = request.getRequestDispatcher("generateClassAttendance.jsp");
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

}
