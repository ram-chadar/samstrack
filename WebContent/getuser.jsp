<%
if(session.getAttribute("userType")==null)
	response.sendRedirect("index.jsp");
%>
<%@page import="com.dbcon.DBUtil"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
        <select class="form-control" name="user" id="user">
          <option value="">Select User</option>
            <%
            Connection con=null;
            PreparedStatement ps=null;
            ResultSet rs=null;
            try
            {
            	 String bname = request.getParameter("bname");
             	 con = DBUtil.getDataSource().getConnection();
                  ps = con.prepareStatement("select username from login where branch=?");
                 ps.setString(1, bname);
               
                  rs=ps.executeQuery();
                 while (rs.next()) {
             %>
             <option value="<%=rs.getString("username")%>"><%=rs.getString("username")%></option>
             <%
                 }
             
            }
            catch(Exception e)
            {
            	e.printStackTrace();
            }
            finally{
            	try{
            		con.close();
            		ps.close();
            		rs.close();
            	}
            	catch(Exception e)
            	{
            		e.printStackTrace();
            	}
            	
            }
               %>
        </select>
     </body>
</html>
