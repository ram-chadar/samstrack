package com.user.detail;

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

@WebServlet("/profileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Connection con = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		PrintWriter out = response.getWriter();
		try {
			con = DBUtil.getDataSource().getConnection();

			ps = con.prepareStatement("select * from login where username=?");
			ps.setString(1, (String) session.getAttribute("user"));
			rs = ps.executeQuery();
			if (rs.next()) {
				request.setAttribute("userName", rs.getString("username"));
				request.setAttribute("password", rs.getString("password"));

				request.setAttribute("question", rs.getString("question"));
				request.setAttribute("answer", rs.getString("answer"));
				request.setAttribute("email", rs.getString("email"));

				RequestDispatcher rd = request.getRequestDispatcher("userProfile.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("msg", "Something Wrong To Fetch Profile");

				RequestDispatcher rd = request.getRequestDispatcher("userProfile.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.println(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}if (rs != null) {
					rs.close();
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
