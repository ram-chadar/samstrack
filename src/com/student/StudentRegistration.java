package com.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.DBUtil;

//import com.dbcon.Conn;

@WebServlet("/studentRegistration")
public class StudentRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static String branchName, year, division, rollno, studentName, mail;

	@SuppressWarnings("resource")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accYear, gender, studentPhone, address, fatherName, motherName, parentPhone, button;

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		PrintWriter out = response.getWriter();
		accYear = request.getParameter("accadmicYear");
		branchName = request.getParameter("bn");
		year = request.getParameter("year");
		division = request.getParameter("division");
		rollno = request.getParameter("rollNo");
		studentName = request.getParameter("studentName");
		gender = request.getParameter("gender");
		mail = request.getParameter("mail");
		studentPhone = request.getParameter("studentPhone");
		address = request.getParameter("address");
		fatherName = request.getParameter("fatherName");
		motherName = request.getParameter("motherName");
		parentPhone = request.getParameter("parentPhone");

		button = request.getParameter("action");
		if (button.equals("Add")) {

			try {
				con = DBUtil.getDataSource().getConnection();

				if (branchName.equals(null) || year.equals(null) || division.equals(null) || rollno.equals(null)
						|| studentName.equals(null)) {
					request.setAttribute("msg", "Please Filled out All Mandatory Fields");
					RequestDispatcher rd = request.getRequestDispatcher("studentRegistration.jsp");
					rd.forward(request, response);
				} else {
					String chechRollNo = "select rollno from student where branchname=? and year=? and division=? and rollno=? ";

					ps = con.prepareStatement(chechRollNo);
					ps.setString(1, branchName);
					ps.setString(2, year);
					ps.setString(3, division);
					ps.setString(4, rollno);

					rs = ps.executeQuery();
					if (rs.next()) {
						request.setAttribute("msg", "Already Exit");
						RequestDispatcher rd = request.getRequestDispatcher("studentRegistration.jsp");
						rd.forward(request, response);

					} else {
						ps = con.prepareStatement("insert into student(accyear,branchname,year,division,rollno,"
								+ "studentname,gender,mail,studentphone,address,fathername,mothername,parentphone)"
								+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
						ps.setString(1, accYear);
						ps.setString(2, branchName);
						ps.setString(3, year);
						ps.setString(4, division);
						ps.setInt(5, Integer.parseInt(rollno));
						ps.setString(6, studentName);
						ps.setString(7, gender);
						ps.setString(8, mail);
						ps.setString(9, studentPhone);
						ps.setString(10, address);
						ps.setString(11, fatherName);
						ps.setString(12, motherName);
						ps.setString(13, parentPhone);

						int result = ps.executeUpdate();
						if (result > 0) {
							request.setAttribute("msg", "Student Saved Successfully");
							RequestDispatcher rd = request.getRequestDispatcher("/studentmail");
							rd.forward(request, response);

						} else {
							request.setAttribute("msg", "Something Wrong ! Student Not Saved");
							RequestDispatcher rd = request.getRequestDispatcher("/studentRegistration.jsp");
							rd.forward(request, response);
						}
					}

				}
			} catch (NullPointerException nullex) {
				request.setAttribute("msg", "Pleasez Fill Mandatry Field");
				RequestDispatcher rd = request.getRequestDispatcher("/studentRegistration.jsp");
				rd.forward(request, response);
			}

			catch (Exception e) {
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

		else if (button.equals("Search")) {
			try {

				RequestDispatcher rd = request.getRequestDispatcher("selectStudent?accYear=" + accYear + "&year=" + year
						+ "&branch=" + branchName + "&division=" + division + "&rollNo=" + rollno + "& action=Search");
				rd.forward(request, response);

			}catch (NullPointerException nulptr) {
				request.setAttribute("msg", "Please Filled out All Mandatory Fields");
				RequestDispatcher rd = request.getRequestDispatcher("studentRegistration.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				out.println(e);

			}

		}
	}

}
