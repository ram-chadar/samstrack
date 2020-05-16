package com.branchAndSubject;

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

import com.bean.SubjectBean;
//import com.dbcon.Conn;
import com.dbcon.DBUtil;

@WebServlet("/practicalSubjectDetail")
public class PracticalSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("resource")
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean data = false;
		String branchName, sem, subject, subjectCode, button;

		branchName = request.getParameter("bn");
		sem = request.getParameter("sem");
		subject = request.getParameter("subname");
		subjectCode = request.getParameter("subcode");
		button = request.getParameter("action");

		PrintWriter out = response.getWriter();

		if (button.equals("ADD")) {
			try {
				con = DBUtil.getDataSource().getConnection();
					ps = con.prepareStatement(
							"select subject from practicalsubject where subject=? and branchname=? and sem=?");
					ps.setString(1, subject);
					ps.setString(2, branchName);
					ps.setString(3, sem);

					rs = ps.executeQuery();
					if (rs.next()) {
						request.setAttribute("msg", "Already Exists");
						RequestDispatcher rd = request.getRequestDispatcher("practicalSubject.jsp");
						rd.forward(request, response);
					}

					else {

						ps = con.prepareStatement(
								"insert into practicalsubject(branchname,sem,subject,subjectcode) values(?,?,?,?)");
						ps.setString(1, branchName);
						ps.setString(2, sem);
						ps.setString(3, subject);
						ps.setString(4, subjectCode);

						int result = ps.executeUpdate();
						if (result > 0) {
							request.setAttribute("msg", "Subject Added Succesfully");
							RequestDispatcher rd = request.getRequestDispatcher("practicalSubject.jsp");
							rd.forward(request, response);
						} else {
							request.setAttribute("msg", "Something Wrong Subject Not Added");
							RequestDispatcher rd = request.getRequestDispatcher("practicalSubject.jsp");
							rd.forward(request, response);
						}
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
		} else if (button.equals("VIEW ALL")) {
			try {
				con = DBUtil.getDataSource().getConnection();

				SubjectBean bean = null;
				ArrayList<SubjectBean> subjectList = new ArrayList<>();
				subjectList.clear();
				ps = con.prepareStatement("select * from practicalsubject where branchname=? ORDER BY sem  ");
				ps.setString(1, branchName);
				rs = ps.executeQuery();

				while (rs.next()) {
					bean = new SubjectBean();

					bean.setBranchName(rs.getString("branchname"));
					bean.setSem(rs.getString("sem"));
					bean.setSubjectName(rs.getString("subject"));
					bean.setSubjectCode(rs.getString("subjectcode"));
					subjectList.add(bean);
					data = true;
				}
				if (data == true) {
					request.setAttribute("subjects", subjectList);
					RequestDispatcher rd = request.getRequestDispatcher("practicalSubjectList.jsp");
					rd.forward(request, response);

				} else {
					request.setAttribute("msg", "Subject Not Found For This Branch");
					RequestDispatcher rd = request.getRequestDispatcher("practicalSubject.jsp");
					rd.forward(request, response);
				}

			} catch (NullPointerException nullex) {
				request.setAttribute("msg", "Please Fill All Subject ");
				RequestDispatcher rd = request.getRequestDispatcher("subjectDetail.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				out.println(e);
			} finally {
				try {
					if (con != null)
						con.close();
					if ((rs != null))
						rs.close();

				} catch (Exception e2) {
					e2.printStackTrace();
					out.println(e2);
				}
			}
		}
	}
}
