package com.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.StudentBean;
//import com.dbcon.Conn;
import com.dbcon.DBUtil;

@WebServlet("/studentList")
public class ShowAllStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		PrintWriter out=response.getWriter();
		
		String accYear, branchName, year, division;

		accYear = request.getParameter("accadmicYear");
		year = request.getParameter("year");
		branchName = request.getParameter("branch");
		division = request.getParameter("division");
		int count = 0;

		try {
			con = DBUtil.getDataSource().getConnection();

			StudentBean studentBean = null;
			ArrayList<StudentBean> studentList = new ArrayList<>();
			studentList.clear();
			 ps = con.prepareStatement(
					"select rollno,studentname,batch from student where accyear=? and year=? and branchname=? and division=? ORDER BY rollno  ");
			ps.setString(1, accYear);
			ps.setString(2, year);
			ps.setString(3, branchName);
			ps.setString(4, division);
			rs = ps.executeQuery();

			while (rs.next()) {
				studentBean = new StudentBean();

				studentBean.setRollNo(Integer.parseInt(rs.getString("rollno")));
				studentBean.setStudentName(rs.getString("studentname"));
				studentBean.setBatch(rs.getString("batch"));
				studentList.add(studentBean);
				count++;
			}

			if (count > 0) {
				request.setAttribute("students", studentList);
				request.setAttribute("accYear", accYear);
				request.setAttribute("year", year);
				request.setAttribute("branch", branchName);
				request.setAttribute("division", division);
				RequestDispatcher rd = request.getRequestDispatcher("studentList.jsp");
				rd.forward(request, response);

			} else {
				request.setAttribute("msg", "Student Not Found For Given Crediential");
				RequestDispatcher rd = request.getRequestDispatcher("studentListOption.jsp");
				rd.forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
			out.println(e);

		} finally {
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				out.println(e);

			}
		}

	}

}
