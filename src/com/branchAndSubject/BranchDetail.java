package com.branchAndSubject;

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

@WebServlet("/branchDetail")
public class BranchDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("resource")
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		PrintWriter out = response.getWriter();

		try {
			String branchName, branchCode, button;
			button = request.getParameter("action");
			con = DBUtil.getDataSource().getConnection();

			if (button.equals("ADD")) {
				branchName = request.getParameter("branchname");
				branchCode = request.getParameter("branchCode");
				if (branchName.equals("") || branchCode.equals("")) {
					request.setAttribute("msg", "Fill Branch Name And Code");
					RequestDispatcher rd = request.getRequestDispatcher("/branchDetail.jsp");
					rd.forward(request, response);
				} else {

					try {
						ps = con.prepareStatement("select branchname from branch where branchname=? OR branchcode=?");
						ps.setString(1, branchName);
						ps.setString(2, branchCode);

						rs = ps.executeQuery();
						if (rs.next()) {
							request.setAttribute("msg", "Branch Or BranchCode Already Exists");

							RequestDispatcher rd = request.getRequestDispatcher("/branchDetail.jsp");
							rd.forward(request, response);
						} else {

							ps = con.prepareStatement("insert into branch(branchname,branchcode)values(?,?)");
							ps.setString(1, branchName);
							ps.setString(2, branchCode);

							int result = ps.executeUpdate();
							if (result > 0) {

								request.setAttribute("msg", "Branch Added Successfully");

								RequestDispatcher rd = request.getRequestDispatcher("/branchDetail.jsp");
								rd.forward(request, response);

							} else {

								request.setAttribute("msg", "Something Wrong To Add Branch");
								RequestDispatcher rd = request.getRequestDispatcher("/branchDetail.jsp");
								rd.forward(request, response);
							}
						}
					} // try close
					catch (IOException io) {
						RequestDispatcher rd = request.getRequestDispatcher("/branchDetail.jsp");
						rd.forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
						out.print(e);
					} finally {
						try {
							con.close();
							rs.close();
							ps.close();
						} catch (Exception e2) {
							e2.printStackTrace();
							out.println(e2);
						}
					}
				} // else close
			} // if close

			else if (button.equals("VIEW ALL")) {
				try {
					RequestDispatcher rd = request.getRequestDispatcher("/branchList");
					rd.forward(request, response);
				} catch (IOException io) {

					RequestDispatcher rd = request.getRequestDispatcher("/branchList");
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					out.println(e);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println(e);
		}


	}
}
