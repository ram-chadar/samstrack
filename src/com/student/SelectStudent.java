package com.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.DBUtil;

//import com.dbcon.Conn;

@WebServlet("/selectStudent")
public class SelectStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectStudent() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accYear, year, branchName, division, button;
		String rollNo;
		Connection con=null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		PrintWriter out = response.getWriter();
		accYear = request.getParameter("accYear");
		year = request.getParameter("year");
		branchName = request.getParameter("branch");
		division = request.getParameter("division");
		rollNo = request.getParameter("rollNo");
		button = request.getParameter("action");
		

		try {
			con = DBUtil.getDataSource().getConnection();

			ps = (PreparedStatement) con.prepareStatement(
					"select * from student where accyear=? and year=? and branchname=? and division=? and rollno=?");

			ps.setString(1, accYear);
			ps.setString(2, year);
			ps.setString(3, branchName);
			ps.setString(4, division);
			ps.setInt(5, Integer.parseInt(rollNo));

			rs = ps.executeQuery();

			if (rs.next()) {
				
				request.setAttribute("accYear", rs.getString("accyear"));
				request.setAttribute("branchName", branchName);
				request.setAttribute("studentName", rs.getString("studentname"));
				request.setAttribute("fatherName", rs.getString("fathername"));
				request.setAttribute("motherName", rs.getString("mothername"));

				request.setAttribute("parentPhone", rs.getString("parentphone"));
				request.setAttribute("studentPhone", rs.getString("studentphone"));
				request.setAttribute("address", rs.getString("address"));
				request.setAttribute("mail", rs.getString("mail"));
				request.setAttribute("rollNo", rollNo);
				request.setAttribute("division", division);
				request.setAttribute("year", year);
				request.setAttribute("gender", rs.getString("gender"));

				if (button.equals("Search")) {
					RequestDispatcher rd = request.getRequestDispatcher("/studentRegistration.jsp");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("/updateStudent.jsp");
					rd.forward(request, response);
				}
			} else {
				request.setAttribute("msg", "Record Not Found Try Agian");
				RequestDispatcher rd = request.getRequestDispatcher("studentList?accadmicYear=" + accYear + "& year="
						+ year + "& branch=" + branchName + "& division=" + division);
				rd.forward(request, response);
			}
		}

		catch (IOException io) {
			request.setAttribute("msg", "Record Not Found Try Agian");
			RequestDispatcher rd = request.getRequestDispatcher("studentList?accadmicYear=" + accYear + "& year=" + year
					+ "& branch=" + branchName + "& division=" + division);
			rd.forward(request, response);
		} catch (Exception e) {
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
			} catch (SQLException e) {
				e.printStackTrace();
				out.println(e);

			}
		}
	}

}
