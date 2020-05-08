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

import com.dbcon.DBUtil;

//import com.dbcon.Conn;

@SuppressWarnings("serial")
@WebServlet("/deleteUser")
public class DeleteUser extends HttpServlet {

	@SuppressWarnings("resource")
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String user = request.getParameter("user");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PrintWriter out = response.getWriter();
		
		try {
			con = DBUtil.getDataSource().getConnection();

			ps = con.prepareStatement("select * from allocateclasssubject,allocatepracticalsubject where allocateclasssubject.teacher= ? or allocatepracticalsubject.teacher=? ");
			ps.setString(1, user);
			ps.setString(2, user);
			rs = ps.executeQuery();
			
			if (rs.next()) {

				request.setAttribute("msg", "User Not Deleted " + user + " " + "Allocated For Subject :"
						+ rs.getString(4) + ": Division :" + rs.getString(3));
				RequestDispatcher rd = request.getRequestDispatcher("listOfUsers");
				rd.forward(request, response);
			} else {

				ps = con.prepareStatement("DELETE FROM login WHERE username = ?");
				ps.setString(1, user);
				int result = ps.executeUpdate();

				if (result > 0) {
					request.setAttribute("msg", "User Deleted Successfully");
					RequestDispatcher rd = request.getRequestDispatcher("listOfUsers");
					rd.forward(request, response);
				} else {
					request.setAttribute("msg", "User Not Deleted");
					RequestDispatcher rd = request.getRequestDispatcher("listOfUsers");
					rd.forward(request, response);
				}

			}
		} catch (Exception e) {
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
