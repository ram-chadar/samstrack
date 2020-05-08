package com.user.detail;

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

@WebServlet("/updateProfile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateProfile() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName, password, question, answer, email;
		userName = request.getParameter("userName");
		password = request.getParameter("password");

		question = request.getParameter("question");
		answer = request.getParameter("answer");
		email = request.getParameter("email");

		PrintWriter out = response.getWriter();
		Connection con = null;
		PreparedStatement ps=null;
		
		try {
			con = DBUtil.getDataSource().getConnection();

			ps = con
					.prepareStatement("update login set password=?,question=?,answer=?,email=?" + "where username=?");
			ps.setString(1, password);
			ps.setString(2, question);
			ps.setString(3, answer);
			ps.setString(4, email);
			ps.setString(5, userName);

			int result = ps.executeUpdate();
			if (result > 0) {
				request.setAttribute("msg", "Profile Updated Successfully !!!!");

				RequestDispatcher rd = request.getRequestDispatcher("profileServlet");
				rd.forward(request, response);
			} else {
				request.setAttribute("msg", "Something Wrong Profile Not Updated");

				RequestDispatcher rd = request.getRequestDispatcher("profileServlet");
				rd.forward(request, response);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			out.println(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				out.println(e2);

			}
		}

	}

}
