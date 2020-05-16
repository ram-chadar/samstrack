package com.attendance;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.StudentBean;
//import com.dbcon.Conn;
import com.dbcon.DBUtil;

@WebServlet("/TakeClassAttendance")
public class Theory_Attendance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Theory_Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	synchronized protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		int result = 0, flag = 0;
		String month = null, accYear = null;
		String branchName, dateTime, division, sem, subject, year = null;
		String action;
		PrintWriter out = response.getWriter();
		List<String> studentName = new ArrayList<>();
		action = request.getParameter("action");
		branchName = request.getParameter("branch");
		dateTime = request.getParameter("datetime");
		division = request.getParameter("division");
		sem = request.getParameter("sem");
		subject = request.getParameter("subject");

		try {
			String[] words = dateTime.split("-");
			for (int i = 1; i < words.length - 1; i++) {
				month = words[i];
			}

			for (int i = 0; i < words.length - 2; i++) {
				accYear = words[i];
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (sem.equals("01") || sem.equals("02")) {
			year = "FE";
		} else if (sem.equals("03") || sem.equals("04")) {
			year = "SE";
		} else if (sem.equals("05") || sem.equals("06")) {
			year = "TE";
		} else {
			year = "BE";
		}

		if (action.equals("Get Roll.NO")) {

			ArrayList<StudentBean> student = new ArrayList<>();
			StudentBean studentBean;
			try {
				con = DBUtil.getDataSource().getConnection();
				ps = con.prepareStatement(
						"select studentname,rollno from student where accyear=? and year=? and branchname=? and division=? ORDER BY rollno ");
				ps.setString(1, accYear);
				ps.setString(2, year);
				ps.setString(3, branchName);
				ps.setString(4, division);

				rs = ps.executeQuery();

				while (rs.next()) {
					studentBean = new StudentBean();
					studentBean.setStudentName(rs.getString("studentname"));
					studentBean.setRollNo(rs.getInt("rollno"));
					student.add(studentBean);
					flag = 1;
				}

				if (flag != 1) {
					request.setAttribute("msg", "Record Not Found Select Proper Fields ");
					RequestDispatcher rd = request.getRequestDispatcher("takeClassAttendance.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("students", student);

					request.setAttribute("dateTime", dateTime);
					request.setAttribute("branch", branchName);
					request.setAttribute("division", division);
					request.setAttribute("sem", sem);
					request.setAttribute("subject", subject);

					RequestDispatcher rd = request.getRequestDispatcher("takeClassAttendance.jsp");
					rd.forward(request, response);
				}

			}catch (Exception e) {
				e.printStackTrace();
				out.println(e);
			} finally {
				try {
					if(con!=null)
						con.close();
					if(ps!=null)
						ps.close();
					if(rs!=null)
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
					out.println(e2);
				}
			}

		}

		else if (action.equals("SUBMIT ATTENDANCE")) {
			try {
				con = DBUtil.getDataSource().getConnection();
				String[] rollno = request.getParameterValues("rollNo");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date givendate = sdf.parse(dateTime);

				Date d = new Date();
				Date currentdate = sdf.parse(sdf.format(d));

				if (sem.equals("") || branchName.equals("") || division.equals("") || accYear.equals("")
						|| month.equals("") || subject.equals("") || dateTime.equals("") || rollno.length == 0) {

					request.setAttribute("msg", "Plz Fill Or Check All Field");
					RequestDispatcher rd = request.getRequestDispatcher("/takeClassAttendance.jsp");
					rd.forward(request, response);

				} else if (givendate.after(currentdate)) {

					request.setAttribute("msg", "Select Correct Date Selected Date Is Greter Than Current Date");
					RequestDispatcher rd = request.getRequestDispatcher("/takeClassAttendance.jsp");
					rd.forward(request, response);
				}

				else {
					ps = con.prepareStatement(
							"select datetime,branch,division,sem,subject from classattendance where datetime=? and branch=? and division=? and sem=? and subject=?");
					ps.setString(1, dateTime);
					ps.setString(2, branchName);
					ps.setString(3, division);
					ps.setString(4, sem);
					ps.setString(5, subject);
					rs = ps.executeQuery();
					if (rs.next()) {
						request.setAttribute("msg",
								"Attendance Has Been Already Taken For " + dateTime + "Subject " + subject);
						RequestDispatcher rd = request.getRequestDispatcher("/takeClassAttendance.jsp");
						rd.forward(request, response);
					} else {

						for (int i = 0; i < rollno.length; i++) {
							ps = con.prepareStatement(
									"select studentname from student where accyear=? and year=? and branchname=? and division=? and rollno=? ");
							ps.setString(1, accYear);
							ps.setString(2, year);
							ps.setString(3, branchName);
							ps.setString(4, division);
							ps.setString(5, rollno[i]);

							rs = ps.executeQuery();

							if (rs.next()) {
								studentName.add(rs.getString("studentname"));
							} else {
								request.setAttribute("msg", "Student Name Not Added");
							}
						}

						for (int i = 0; i < rollno.length; i++) {
							ps = con.prepareStatement(
									"insert into classattendance(datetime,branch,division,sem,subject,rollno,studentname,month,accyear) values(?,?,?,?,?,?,?,?,?)");
							ps.setString(1, dateTime);
							ps.setString(2, branchName);
							ps.setString(3, division);
							ps.setString(4, sem);
							ps.setString(5, subject);
							ps.setString(6, rollno[i]);
							ps.setString(7, studentName.get(i));
							ps.setString(8, month);
							ps.setString(9, accYear);

							result = ps.executeUpdate();
						}
					}
				}

				if (result > 0) {
					
					request.setAttribute("msg",rollno.length + " " + "Student Successfully Submited " + dateTime);

					RequestDispatcher rd = request.getRequestDispatcher("/takeClassAttendance.jsp");
					rd.forward(request, response);
				}

				else {
					request.setAttribute("msg", "Something Wrong");
					RequestDispatcher rd = request.getRequestDispatcher("/takeClassAttendance.jsp");
					rd.forward(request, response);
				}

			} catch (NullPointerException n) {
				request.setAttribute("msg", "Plz Fill Or Check All Field");
				RequestDispatcher rd = request.getRequestDispatcher("/takeClassAttendance.jsp");
				rd.forward(request, response);
			}

			catch (ParseException p) {
				request.setAttribute("msg", "Check Date And Time Properly" + p.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("/takeClassAttendance.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				out.println(e);
			} finally {
				try {
					if(con!=null)
					con.close();
					if(rs!=null)
					rs.close();
					if(ps!=null)
					ps.close();
				} catch (Exception e2) {
					e2.printStackTrace();
					out.println(e2);
				}
			}
		} else if (action.equals("Delete Attendance")) {
			boolean b = Theory_Attendance_Operation.checkAttendance(branchName, sem, division, subject, dateTime);
			if (b == true) {
				boolean bb = Theory_Attendance_Operation.deleteAttendance(branchName, sem, division, subject, dateTime);
				if (bb == true) {
					request.setAttribute("msg", "Attendance Deleted Successfully ");
					RequestDispatcher rd = request.getRequestDispatcher("/takeClassAttendance.jsp");
					rd.forward(request, response);

				} else {
					request.setAttribute("msg", "Something Wrong Attendance Not Deleted For Given Crediential");
					RequestDispatcher rd = request.getRequestDispatcher("/takeClassAttendance.jsp");
					rd.forward(request, response);
				}
			}
			else {
				request.setAttribute("msg", "Attendance Not Found For Given Credientials ! Take Attendance");
				RequestDispatcher rd = request.getRequestDispatcher("/takeClassAttendance.jsp");
				rd.forward(request, response);
			}
		}
		
	}

}
