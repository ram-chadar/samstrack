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

import com.bean.BranchBean;
//import com.dbcon.Conn;
import com.dbcon.DBUtil;

@WebServlet("/branchList")
public class BranchList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection con = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		PrintWriter out = response.getWriter();

		int record = 0;

		try {
			con = DBUtil.getDataSource().getConnection();

			BranchBean bean = null;
			ArrayList<BranchBean> branchList = new ArrayList<>();
			branchList.clear();
			ps = con.prepareStatement("select * from branch");
			rs = ps.executeQuery();

			while (rs.next()) {
				bean = new BranchBean();

				bean.setBranchname(rs.getString("branchname"));
				bean.setBranchCode(rs.getString("branchcode"));
				branchList.add(bean);
				record++;
			}
			if (record > 0) {
				request.setAttribute("branches", branchList);
				RequestDispatcher rd = request.getRequestDispatcher("branchList.jsp");
				if (rd != null) {

					rd.forward(request, response);

				}
			} else {
				request.setAttribute("msg", "Branch Not Found First Add Branch");
				RequestDispatcher rd = request.getRequestDispatcher("branchDetail.jsp");
				if (rd != null) {

					rd.forward(request, response);

				}
			}

		} 
		catch (Exception e) {
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

	}

}
