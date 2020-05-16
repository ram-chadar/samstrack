package com.branchAndSubject;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/allocatedSubject")
public class AllocatedSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AllocatedSubject() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("resource")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		PrintWriter out = response.getWriter();

		List<String> theorySubjectList = new ArrayList<>();
		List<String> theoryDivisionList = new ArrayList<>();

		List<String> practicalSubjectList = new ArrayList<>();
		List<String> practicalDivisionList = new ArrayList<>();
		List<String> batchList = new ArrayList<>();

		List<String> allocatedTheorySubjectList = new ArrayList<>();
		List<String> allocatedPracticalSubjectList = new ArrayList<>();

		String branch, sem;
		// accYear=request.getParameter("accYear");
		branch = request.getParameter("branch");
		sem = request.getParameter("sem");

		try {
			con = DBUtil.getDataSource().getConnection();

			ps = con.prepareStatement(
					"select distinct subject from allocateclasssubject where branch=? and sem=?");
			ps.setString(1, branch);
			ps.setString(2, sem);
			rs = ps.executeQuery();
			while (rs.next()) {
				theorySubjectList.add(rs.getString("subject"));
			}
			
			ps = con.prepareStatement(
					"select distinct division from allocateclasssubject where branch=? and sem=?  ORDER BY division");
			ps.setString(1, branch);
			ps.setString(2, sem);
			rs = ps.executeQuery();
			while (rs.next()) {
				theoryDivisionList.add(rs.getString("division"));

			}

			for (int i = 0; i < theorySubjectList.size(); i++) {
				allocatedTheorySubjectList.add(theorySubjectList.get(i));

				for (int j = 0; j < theoryDivisionList.size(); j++) {

					ps = con.prepareStatement(
							"select teacher from allocateclasssubject where branch=? and sem=? and division=? and subject=?");
					ps.setString(1, branch);
					ps.setString(2, sem);
					ps.setString(3, theoryDivisionList.get(j));
					ps.setString(4, theorySubjectList.get(i));

					rs = ps.executeQuery();
					if (rs.next()) {

						allocatedTheorySubjectList.add(rs.getString("teacher"));
					
					} else {
						allocatedTheorySubjectList.add("Not Allocated");
					}
				} // end divisionList For
			} // end subjectList for

			/*				**********************************************************/
			ps = con.prepareStatement(
					"select distinct subject from allocatepracticalsubject where branch=? and sem=?");
			ps.setString(1, branch);
			ps.setString(2, sem);
			rs = ps.executeQuery();
			while (rs.next()) {
				practicalSubjectList.add(rs.getString("subject"));
				
			}
			
			ps = con.prepareStatement(
					"select distinct division from allocatepracticalsubject where branch=? and sem=?  ORDER BY division");
			ps.setString(1, branch);
			ps.setString(2, sem);
			rs = ps.executeQuery();
			while (rs.next()) {
			
				practicalDivisionList.add(rs.getString("division"));
				
			}
			ps = con.prepareStatement(
					"select distinct batch from allocatepracticalsubject where branch=? and sem=?  ORDER BY batch");
			ps.setString(1, branch);
			ps.setString(2, sem);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				batchList.add(rs.getString("batch"));
			}

			for (int i = 0; i < practicalSubjectList.size(); i++) {
				allocatedPracticalSubjectList.add(practicalSubjectList.get(i));

				for (int j = 0; j < practicalDivisionList.size(); j++) {
					for (int k = 0; k < batchList.size(); k++) {

						ps = con.prepareStatement(
								"select teacher from allocatepracticalsubject where branch=? and sem=? and division=? and batch=? and subject=?");
						ps.setString(1, branch);
						ps.setString(2, sem);
						ps.setString(3, theoryDivisionList.get(j));
						ps.setString(4, batchList.get(k));
						ps.setString(5, practicalSubjectList.get(i));

						rs = ps.executeQuery();
						if (rs.next()) {

							allocatedPracticalSubjectList.add(rs.getString("teacher"));
							// allocatedSubjectList.add(rs.getString("division"));
						} else {
							allocatedPracticalSubjectList.add("Not Allocated");

						}
					} // end batch For
				} // end division
			} // end subjectList for

			if (theorySubjectList.size() > 0 ) 
				{
					request.setAttribute("theoryDivision", theoryDivisionList);
					request.setAttribute("allocatedTheorySubject", allocatedTheorySubjectList);
					request.setAttribute("msg", "Total Allocated Theory Subject :"+theorySubjectList.size());

					
				}
				else
				{
					request.setAttribute("msg", "Record Not Found For Practical Subject! Subject Not Allocated");
					RequestDispatcher rd = request.getRequestDispatcher("allocatedSubjectOption.jsp");
					rd.forward(request, response);
				}
			
			if(practicalSubjectList.size()>0)
			{
				request.setAttribute("practicalDivision", practicalDivisionList);
				request.setAttribute("practicalDivSize", practicalDivisionList.size());
				request.setAttribute("batch", batchList);
				request.setAttribute("allocatedPracticalSubject", allocatedPracticalSubjectList);
				request.setAttribute("msg1", "Total Allocated Practical Subject :"+practicalSubjectList.size());

			}
			else
			{
				request.setAttribute("msg1", "Record Not Found Practical Subject! Subject Not Allocated");

			}
			/*	
			
			for (String string : allocatedTheorySubjectList) {
				System.out.print(string +"\t");
			}
			
			System.out.println("**************");
			for (String string : allocatedPracticalSubjectList) {
				System.out.print(string +"\t");
				
			}*/
			
				RequestDispatcher rd = request.getRequestDispatcher("allocatedSubject.jsp");
				rd.forward(request, response);
			

		} catch (Exception e) {
			e.printStackTrace();
			out.println(e);
		} finally {

			try {
				if (con != null)
					con.close();
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				out.println(e2);
			}

		}

	}

}
