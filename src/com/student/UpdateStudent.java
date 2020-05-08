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

@WebServlet("/UpdateStudent")
public class UpdateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateStudent() {
		super();

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		PreparedStatement ps=null;
		PrintWriter out = response.getWriter();

		try {
			con = DBUtil.getDataSource().getConnection();

			ps = con.prepareStatement(("update student set accyear=?,branchname=?,year=?,division=?,"
					+ "rollno=?,studentname=?,gender=?,mail=?,studentphone=?,address=?,fathername=?,mothername=?,parentphone=? "
					+ "where accyear=? and branchname=? and year=? and division=? and rollno=?"));
			ps.setString(1, request.getParameter("accYear"));
			ps.setString(2, request.getParameter("branch"));
			ps.setString(3, request.getParameter("year"));
			ps.setString(4, request.getParameter("division"));
			ps.setInt(5, Integer.parseInt(request.getParameter("rollNo")));
			ps.setString(6, request.getParameter("studentName"));
			ps.setString(7, request.getParameter("gender"));
			ps.setString(8, request.getParameter("mail"));
			ps.setString(9, request.getParameter("studentPhone"));
			ps.setString(10, request.getParameter("address"));
			ps.setString(11, request.getParameter("fatherName"));
			ps.setString(12, request.getParameter("motherName"));
			ps.setString(13, request.getParameter("parentPhone"));
			ps.setString(14, request.getParameter("update_accYear"));
			ps.setString(15, request.getParameter("update_branch"));
			ps.setString(16, request.getParameter("update_year"));
			ps.setString(17, request.getParameter("update_division"));
			ps.setInt(18, Integer.parseInt(request.getParameter("rollNo")));

			int i = ps.executeUpdate();

			if (i > 0) {
				request.setAttribute("msg", "Student Updated Successfully");
				RequestDispatcher rd = request.getRequestDispatcher("studentList?accadmicYear="
						+ request.getParameter("update_accYear") + "& year=" + request.getParameter("update_year")
						+ "& branch=" + request.getParameter("update_branch") + "& division="
						+ request.getParameter("division"));
				rd.forward(request, response);

			}

			else {
				request.setAttribute("msg", "Student Not Updated");
				RequestDispatcher rd = request.getRequestDispatcher("studentList?accadmicYear="
						+ request.getParameter("update_accYear") + "& year=" + request.getParameter("update_year")
						+ "& branch=" + request.getParameter("update_branch") + "& division="
						+ request.getParameter("division"));
				rd.forward(request, response);
			}

		} catch (IOException io) {
			RequestDispatcher rd = request
					.getRequestDispatcher("studentList?accadmicYear=" + request.getParameter("update_accYear")
							+ "& year=" + request.getParameter("update_year") + "& branch="
							+ request.getParameter("update_branch") + "& division=" + request.getParameter("division"));
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			out.println(e);

		}
		finally {
			try {
				if (con != null) {
					con.close();
				}
				if(ps!=null)
				{
					ps.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				out.println(e2);

			}
		}

	}

}
