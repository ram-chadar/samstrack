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

import com.dbcon.DBUtil;

//import com.dbcon.Conn;

@WebServlet("/studentBatch")
public class StudentBatch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentBatch() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		PreparedStatement ps = null;
		PrintWriter out = response.getWriter();
		String accYear = request.getParameter("accYear");
		String branch = request.getParameter("branch");
		String division = request.getParameter("division");
		String year = request.getParameter("year");
		String batch = request.getParameter("batch");
		int from, to, result = 0;
		from = Integer.parseInt(request.getParameter("rollNoFrom"));
		to = Integer.parseInt(request.getParameter("rollNoTo"));
		try {
			con = DBUtil.getDataSource().getConnection();

			for (int i = from; i <= to; i++) {
				ps = con.prepareStatement(
						"update student set batch=? where accyear=? and branchname=? and division=? and year=? and rollno=?");
				ps.setString(1, batch);
				ps.setString(2, accYear);
				ps.setString(3, branch);
				ps.setString(4, division);
				ps.setString(5, year);
				ps.setInt(6, i);

				result = ps.executeUpdate();

			}
			if (result > 0) {
				request.setAttribute("msg", from + " " + "To" + " " + to + " " + "Batch  " + " " + batch);
				RequestDispatcher rd = request.getRequestDispatcher("/studentBatch.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("msg", "Batch Not Generated Check Credential");
				RequestDispatcher rd = request.getRequestDispatcher("/studentBatch.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
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
