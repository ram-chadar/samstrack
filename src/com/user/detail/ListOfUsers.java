package com.user.detail;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Users;
//import com.dbcon.Conn;
import com.dbcon.DBUtil;

@SuppressWarnings("serial")
@WebServlet("/listOfUsers")
public class ListOfUsers extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Users users;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		HttpSession session = request.getSession();
		ArrayList<Users> list = new ArrayList<>();
		PrintWriter out=response.getWriter();

		try {
			con = DBUtil.getDataSource().getConnection();
			if (session.getAttribute("userType").equals("Hod")) {
				ps = con.prepareStatement("select * from login where branch=?");
				ps.setString(1, (String) session.getAttribute("userBranch"));
			} else if (session.getAttribute("userType").equals("Principle"))
				ps = con.prepareStatement("select * from login");
			else
			{
				response.sendRedirect("index.jsp");
			}
			
			rs = ps.executeQuery();

			while (rs.next()) {
				users = new Users();
				users.setUserName(rs.getString("username"));
				users.setPassword(rs.getString("password"));
				users.setUserType(rs.getString("usertype"));
				users.setQuestion(rs.getString("question"));
				users.setAnswer(rs.getString("answer"));
				users.setEmail(rs.getString("email"));
				users.setBranch(rs.getString("branch"));

				list.add(users);
			}

			if (!list.isEmpty()) {
				request.setAttribute("users", list);
				RequestDispatcher rd = request.getRequestDispatcher("listOfUser.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("msg", "User Not Found");
				RequestDispatcher rd = request.getRequestDispatcher("listOfUser.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println(e);

		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (rs != null) {
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
