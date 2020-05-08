package com.branchAndSubject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.management.relation.RelationSupportMBean;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.DBUtil;

//import com.dbcon.Conn;
@WebServlet("/deleteSubject")
public class DeleteSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		PreparedStatement ps=null;

		String branchName, sem, subject;
		branchName = request.getParameter("branchName");
		sem = request.getParameter("sem");
		subject = request.getParameter("subjectName");

		PrintWriter out = response.getWriter();

		try {
			con = DBUtil.getDataSource().getConnection();

			ps = con.prepareStatement("delete from subject where branchname=? and sem=? and subject=?");
			ps.setString(1, branchName);
			ps.setString(2, sem);
			ps.setString(3, subject);

			int result = ps.executeUpdate();
			if (result > 0) {
				request.setAttribute("msg", "Subject Deleted");
				RequestDispatcher rd=request.getRequestDispatcher("subjectDetail.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("msg", "Something Wrong Not Deleted");
				RequestDispatcher rd = request.getRequestDispatcher("subjectDetail.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println(e);
		} finally {
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
