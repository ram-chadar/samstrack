package com.branchAndSubject;

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

@WebServlet("/deleteBranch")
public class DeleteBranch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		PreparedStatement ps=null;
		
		PrintWriter out = response.getWriter();

		String branchName = request.getParameter("branchname");

		try {
			con = DBUtil.getDataSource().getConnection();

				ps = con.prepareStatement("delete from branch where branchname=?");
				ps.setString(1, branchName);

				int result = ps.executeUpdate();
				if (result > 0) {
					request.setAttribute("msg", "Branch Deleted");
					RequestDispatcher rd = request.getRequestDispatcher("branchList");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("branchList");
					rd.forward(request, response);
				}
			

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
			} catch (Exception e2) {
				e2.printStackTrace();
				out.println(e2);
			}

		}

	}

}
