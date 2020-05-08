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
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement ps = null;
			try{
			String bname = request.getParameter("bname");
			String sem = request.getParameter("sem");

			//System.out.println(bname+" "+sem );
			con = DBUtil.getDataSource().getConnection();
			ps = con.prepareStatement("select distinct subject from subject where branchname=? and sem=?");
			ps.setString(1, bname);
			ps.setString(2, sem);
			rs = ps.executeQuery();
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
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
			}
		%>
	</select>
</body>
</html>
