package com.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.dbcon.Conn;
import com.dbcon.DBUtil;

@WebServlet("/DeleteStudent")
public class DeleteStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteStudent() {
		super();

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection con=null;
		PreparedStatement ps=null;
		String accYear, year, branchName, division;
		int rollNo;
		accYear = request.getParameter("accYear");
		year = request.getParameter("year");
		branchName = request.getParameter("branch");
		division = request.getParameter("division");
		rollNo = Integer.parseInt(request.getParameter("rollNo"));
PrintWriter out=response.getWriter();

		
		try {
			con = DBUtil.getDataSource().getConnection();

			ps = con.prepareStatement(
					"delete from student where accyear=? and  year=? and branchname=? and division=? and rollno=?");
			ps.setString(1, accYear);
			ps.setString(2, year);
			ps.setString(3, branchName);
			ps.setString(4, division);
			ps.setInt(5, rollNo);

			int result = ps.executeUpdate();
			if (result > 0) {
				request.setAttribute("msg", "Student Deleted");
				RequestDispatcher rd = request.getRequestDispatcher("studentList?accadmicYear=" + accYear + "& year="
						+ year + "& branch=" + branchName + "& division=" + division);
				rd.forward(request, response);
			} else {
				request.setAttribute("msg", "Student Not Deleted");
				RequestDispatcher rd = request.getRequestDispatcher("studentList?accadmicYear=" + accYear + "& year="
						+ year + "& branch=" + branchName + "& division=" + division);
				rd.forward(request, response);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			out.println(e);

		}
		finally {
			try {
				con.close();
				ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				out.println(e2);

			}
		}

	}

}
