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

@SuppressWarnings("serial")
@WebServlet("/userDetail")
public class UserDetail extends HttpServlet {

	@SuppressWarnings("resource")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String usertype = request.getParameter("userType");
		String branch = request.getParameter("branch");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		String email = request.getParameter("mail");
		String phone = request.getParameter("phone");
		Connection con = null;
		PreparedStatement ps=null;
		HttpSession session=request.getSession();

		PrintWriter out=response.getWriter();
		try {

			con = DBUtil.getDataSource().getConnection();

			ps = con.prepareStatement("select username from login where username=? ");
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				request.setAttribute("msg", "User Already Exists");
				
				if(session.getAttribute("userType").equals("Principle")) {
					RequestDispatcher rd = request.getRequestDispatcher("userDetailPrinciple.jsp");
					rd.forward(request, response);
				}
				else
				{
					RequestDispatcher rd = request.getRequestDispatcher("userDetail.jsp");
					rd.forward(request, response);	
				}
			} else {
				ps = con.prepareStatement(
						"insert into login (username,password,usertype,branch,question,answer,email,phone)values(?,?,?,?,?,?,?,?)");
				ps.setString(1, userName);
				ps.setString(2, password);
				ps.setString(3, usertype);
				ps.setString(4, branch);
				ps.setString(5, question);
				ps.setString(6, answer);
				ps.setString(7, email);
				ps.setString(8, phone);

				int result = ps.executeUpdate();
				if (result > 0) {
					
					request.setAttribute("msg", "User Added Successfully");
				if(session.getAttribute("userType").equals("Principle")) {
					RequestDispatcher rd = request.getRequestDispatcher("userDetailPrinciple.jsp");
					rd.forward(request, response);
				}
				else
				{

					RequestDispatcher rd = request.getRequestDispatcher("userDetail.jsp");
					rd.forward(request, response);	
				}
				} else {
					request.setAttribute("msg", "User Not Added");
					if(session.getAttribute("userType").equals("Principle")) {
						RequestDispatcher rd = request.getRequestDispatcher("userDetailPrinciple.jsp");
						rd.forward(request, response);
					}
					else
					{

						RequestDispatcher rd = request.getRequestDispatcher("userDetail.jsp");
						rd.forward(request, response);	
					}
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
