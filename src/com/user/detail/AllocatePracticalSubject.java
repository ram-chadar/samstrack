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

@WebServlet("/allocatePracticalSubject")
public class AllocatePracticalSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("resource")
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection con = null;

		String branch, user, sem, division, batch, subject;

		branch = request.getParameter("branch");
		sem = request.getParameter("sem");
		division = request.getParameter("division");
		batch = request.getParameter("batch");
		subject = request.getParameter("subject");
		user = request.getParameter("user");


		try {
			con = DBUtil.getDataSource().getConnection();

			ps = con.prepareStatement(
					"select * from allocatepracticalsubject where branch=? and sem=? and division=? and batch=?  and subject=?");
			ps.setString(1, branch);
			ps.setString(2, sem);
			ps.setString(3, division);
			ps.setString(4, batch);
			ps.setString(5, subject);

			rs = ps.executeQuery();
			if (rs.next()) {
				
				PreparedStatement pst=con.prepareStatement("update allocatepracticalsubject set teacher=? where branch=? and sem=? and division=? and batch=?  and subject=?");
				pst.setString(1, user);
				pst.setString(2, branch);
				pst.setString(3, sem);
				pst.setString(4, division);
				pst.setString(5, batch);
				pst.setString(6, subject);
				int result=pst.executeUpdate();
				
				if(result>0)
				{
					request.setAttribute("msg", "UPDATED \n NOW "+subject + " " + "Allocated To " + " " + user + " " + "Division" + " " + division +" Batch "+" "+batch);
					RequestDispatcher rd = request.getRequestDispatcher("allocatePracticalSubject.jsp");
					rd.forward(request, response);
				}
			}

			else {
				ps = con.prepareStatement(
						"insert into allocatepracticalsubject(branch,sem,division,batch,subject,teacher)"
								+ "values(?,?,?,?,?,?)");
				ps.setString(1, branch);
				ps.setString(2, sem);
				ps.setString(3, division);
				ps.setString(4, batch);
				ps.setString(5, subject);
				ps.setString(6, user);

				int result = ps.executeUpdate();
				if (result > 0) {
					request.setAttribute("msg","Theory Subject -"+ subject + " - " + "Allocated To -" + " " + user + " -" + "Division" + " -"
							+ division + " -" + "Batch" + " -" + batch);
					RequestDispatcher rd = request.getRequestDispatcher("allocatePracticalSubject.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("msg", "Something Wrong Subject Not Allocated");
					RequestDispatcher rd = request.getRequestDispatcher("allocatePracticalSubject.jsp");
					rd.forward(request, response);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.println();
		}

		finally {
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
