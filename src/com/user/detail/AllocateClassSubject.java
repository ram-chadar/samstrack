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

@WebServlet("/allocateClassSubject")
public class AllocateClassSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("resource")
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection con = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		PrintWriter out=response.getWriter();

		
		String branch, division, user, sem, subject;

		branch = request.getParameter("branch");
		division = request.getParameter("division");
		user = request.getParameter("user");
		sem = request.getParameter("sem");
		subject = request.getParameter("subject");


		try {
			con = DBUtil.getDataSource().getConnection();

			ps = con.prepareStatement(
					"select * from allocateclasssubject where branch=? and division=? and sem=?  and subject=?");
			ps.setString(1, branch);
			ps.setString(2, division);
			ps.setString(3, sem);
			ps.setString(4, subject);
			rs = ps.executeQuery();
			if (rs.next()) {
				
				ps=con.prepareStatement("update allocateclasssubject set teacher=? where branch=? and division=? and sem=? and subject=?" );
				ps.setString(1, user);
				ps.setString(2, branch);
				ps.setString(3, division);
				ps.setString(4, sem);
				ps.setString(5, subject);
				int result=ps.executeUpdate();
				
				if(result>0)
				{
					request.setAttribute("msg", "UPDATED \n NOW "+subject + " " + "Allocated To " + " " + user + " " + "Division" + " " + division);
					RequestDispatcher rd = request.getRequestDispatcher("allocateClassSubject.jsp");
					rd.forward(request, response);
				}
			}

			else {
				ps = con.prepareStatement(
						"insert into allocateclasssubject(branch,sem,division,subject,teacher)" + "values(?,?,?,?,?)");
				ps.setString(1, branch);
				ps.setString(2, sem);
				ps.setString(3, division);
				ps.setString(4, subject);
				ps.setString(5, user);

				int result = ps.executeUpdate();
				if (result > 0) {
					request.setAttribute("msg","theory Subject - "+subject + " " + "Allocated To -" + " " + user + " - " + "Division" + " " + division);
					RequestDispatcher rd = request.getRequestDispatcher("allocateClassSubject.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("msg", "Something Wrong Subject Not Allocated");
					RequestDispatcher rd = request.getRequestDispatcher("allocateClassSubject.jsp");
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
