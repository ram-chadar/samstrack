package com.authentication;

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

//import com.dbcon.Conn;
import com.dbcon.DBUtil;

@WebServlet("/forgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("resource")
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ResultSet rs=null;
		Connection con = null;
		
		PreparedStatement ps=null;
		
		PrintWriter out = response.getWriter();
		String username = request.getParameter("userName");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		String newPassword = request.getParameter("newPassword");
		String confPassword = request.getParameter("confirmPassword");

		try {

			con = DBUtil.getDataSource().getConnection();

			ps = con.prepareStatement("select username from login where username=?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {

				if (newPassword.equals(confPassword)) {
					 ps = con.prepareStatement(
							"update login set password=? where username=? and question=? and answer=? ");
					ps.setString(1, newPassword);
					ps.setString(2, username);
					ps.setString(3, question);
					ps.setString(4, answer);

					int result = ps.executeUpdate();

					if (result > 0) {
						request.setAttribute("msg",
								"Password Updated Successfully !!!!! " + "Your New Password is " + " " + newPassword);

						RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
						rd.forward(request, response);
					} else {
						request.setAttribute("msg", "Wrong Question Answer ");
						request.setAttribute("username", username);
						RequestDispatcher rd = request.getRequestDispatcher("forgotPassword.jsp");
						rd.forward(request, response);
					}
				} else {
					request.setAttribute("msg", "New And Confirm Password Must Be Same");
					request.setAttribute("question", question);
					request.setAttribute("answer", answer);
					RequestDispatcher rd = request.getRequestDispatcher("forgotPassword.jsp");
					rd.forward(request, response);
				}

			} else {
				request.setAttribute("msg", "User Does Not Exists");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println(e);
		}
		finally {
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

}
