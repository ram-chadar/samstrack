package com.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dbcon.DBUtil;

@WebServlet("/updateAccYear")
public class UpdateAccYear extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateAccYear() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		PreparedStatement ps = null;
		PrintWriter out=response.getWriter();


		try {
			String accyear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
			HttpSession session = request.getSession();
			String branch = (String) session.getAttribute("userBranch");

			con = DBUtil.getDataSource().getConnection();

			ps = con.prepareStatement("update student set accyear=? where branchname=?");
			ps.setString(1, accyear);
			ps.setString(2, branch);
			int result = ps.executeUpdate();

			if (result > 0) {
				request.setAttribute("msg", "Accadmic Year Updated According To Your System Time:- " + accyear);
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("msg", "Accadmic Year Not Updated Check Your System Date And Time");
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println(e);

		} finally {
			try {
				con.close();
				ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				out.println(e2);

			}
		}

	}

}
