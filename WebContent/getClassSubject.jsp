<%
if(session.getAttribute("userType")==null)
	response.sendRedirect("index.jsp");
%>
<%@page import="com.dbcon.DBUtil"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<select class="form-control" name="subject" id="subject">
		<option value="">Select Subject</option>
		<%
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection con=null;
		try{
			String bname = request.getParameter("bname");
			String sem = request.getParameter("sem");
			String user = (String) session.getAttribute("user");
			String userType = (String) session.getAttribute("userType");
			String division=request.getParameter("division");
			con = DBUtil.getDataSource().getConnection();
			
			//System.out.println(division+sem+bname);
			
			
			if (userType.equals("Hod") || userType.equals("Principle")) {
				ps = con
						.prepareStatement("select distinct (subject) from subject where branchname=? and sem=? ");
				ps.setString(1, bname);
				ps.setString(2, sem);

				rs = ps.executeQuery();
			} else if(userType.equals("Teacher")) {
				ps = con.prepareStatement(
						"select distinct subject from allocateclasssubject where branch=? and sem=? and teacher=? and division=?");
				ps.setString(1, bname);
				ps.setString(2, sem);
				ps.setString(3, user);
				ps.setString(4, division);
				rs = ps.executeQuery();
			}

			while (rs.next()) {
		%>
		<option value="<%=rs.getString("subject")%>"><%=rs.getString("subject")%></option>
		<%
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			out.println(e);
		}
		finally{
			if(con!=null)
				con.close();
			if(ps!=null)
				ps.close();
			if(rs!=null)
				rs.close();
		}
		%>
	</select>

</body>
</html>
