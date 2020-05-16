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
import javax.servlet.http.HttpSession;

import com.dbcon.DBUtil;

//import com.dbcon.Conn;

@WebServlet("/userLogin")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;

		PrintWriter out = response.getWriter();
		String username = request.getParameter("userName");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();

		try {
			con = DBUtil.getDataSource().getConnection();
		
			ps = con.prepareStatement(
					"select username,password,usertype,branch from login where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			

			rs = ps.executeQuery();

			if (rs.next()) {

				session.setAttribute("user", rs.getString("username"));
				session.setAttribute("userType", rs.getString("usertype"));
				session.setAttribute("userBranch", rs.getString("branch"));

				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("msg", "Invalid username And password");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
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
			} catch (Exception e2) {
				e2.printStackTrace();
				out.println(e2);
			}
		}

	}

}
