
package com.database;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ClearAllocatedSubject")
public class ClearAllocatedSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ClearAllocatedSubject() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*try {
			System.out.println("Clear Attendance Status");
			String branch, sem, division;
			branch = request.getParameter("bn");
			sem = request.getParameter("sem");
			division = request.getParameter("division");
			
			int result = 0;
			Connection con = Conn.getCon();
			PreparedStatement ps = null;

			ps = con.prepareStatement(
					"delete from allocateclasssubject,allocatepracticalsubject where and branch=? and sem=? and division=? ");

			ps.setString(1, branch);
			ps.setString(2, sem);
			ps.setString(3, division);

			result = ps.executeUpdate();
			if (result > 0) {
				request.setAttribute("msg", "Record Deleted ");
				RequestDispatcher rd = request.getRequestDispatcher("dataAdministator.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("msg", "Something Went Wromg Deleted To Delete Record Or Record Not Present");
				RequestDispatcher rd = request.getRequestDispatcher("dataAdministator.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
	}

}
