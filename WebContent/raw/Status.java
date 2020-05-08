package com.show.status;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.DBUtil;

//import com.dbcon.Conn;

@WebServlet("/Status")
public class Status extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accYear = request.getParameter("accYear");
		String month = request.getParameter("month");
		String branch = request.getParameter("branch");
		String division = request.getParameter("division");
		String sem = request.getParameter("sem");

		Connection con=null;
		ResultSet rs;

		
		//List<String> rollname = new ArrayList<>();
		// List<String> name=new ArrayList<>();
		List<String> subjects = new ArrayList<>();
		List<String> statusList = new ArrayList<>();
		List<String> rollnoList = new ArrayList<>();

		try {
			con = DBUtil.getDataSource().getConnection();

			PreparedStatement ps1 = con.prepareStatement("select distinct rollno from classsmonthstatus where "
					+ "accyear=? and sem=? and branch=? and division=?");
			ps1.setString(1, accYear);
			ps1.setString(2, sem);
			ps1.setString(3, branch);
			ps1.setString(4, division);

			rs = ps1.executeQuery();

			while (rs.next()) {
				rollnoList.add(rs.getString("rollno"));
			}
/*************************************************************************************************************************/
			PreparedStatement ps2 = con.prepareStatement(
					"select distinct subject from classsmonthstatus where " + "accyear=? and sem=? and branch=?");
			ps2.setString(1, accYear);
			ps2.setString(2, sem);
			ps2.setString(3, branch);

			rs = ps2.executeQuery();

			while (rs.next()) {
				subjects.add(rs.getString("subject"));
			}
/*************************************************************************************************************************/

			for(int i=0;i<rollnoList.size();i++)
			{
				PreparedStatement ps3=con.prepareStatement("select studentname from classsmonthstatus where "
							+ " accyear=? and sem=? and branch=? and division=? rollno=?");
					ps3.setString(1, accYear);
					ps3.setString(2, sem);
					ps3.setString(3, branch);
					ps3.setString(4, division);
					ps3.setString(5, rollnoList.get(i));
					
					rs=ps3.executeQuery();
					if(rs.next())
					{
						statusList.add(rollnoList.get(i));
						statusList.add(rs.getString("studentname"));
						
					}
				
				for(int j=0;j<subjects.size();j++)
				{
					PreparedStatement ps4=con.prepareStatement("select studentname,status from classsmonthstatus where "
							+ " accyear=? and sem=? and month=? and branch=? and division=? rollno=? and subject=? ");
					
					ps4.setString(1, accYear);
					ps4.setString(2, sem);
					ps4.setString(3, month);
					ps4.setString(4, branch);
					ps4.setString(5, division);
					ps4.setString(6, rollnoList.get(i));
					ps4.setString(7, subjects.get(j));
					
					rs=ps4.executeQuery();
					while(rs.next());
					{
						statusList.add(rs.getString("status"));
					}
					
				}
			}
			request.setAttribute("statusList", statusList);
			RequestDispatcher rd=request.getRequestDispatcher("showOverAllClassStatus.jsp");
			rd.forward(request,response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

}
