<!DOCTYPE html>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.dbcon.DBUtil"%>
<%@page import="java.sql.Connection"%>
<%@include file="prevent.jsp"%>

<%@ page language="java" import="java.util.*"%>

<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="customCss/branchSubDetail.css">

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<form action="studentList" name="studentList" class="form-data">


					<div class="form-resp">
						<H4 class="text-center">View All Students</H4>

						<div class="form-group">
							<label for="accYear">Accadmin Year</label> <input type="number"
								class="form-control" id="accyear" placeholder="Accadmic Year"
								name="accadmicYear" min="2000" max="9999" required="required"
								value="<%=new Date().getYear() + 1900%>">
						</div>

						<div class="form-group">
							<label for="year">Year</label> <select
								class="form-control form-control-md" id="branchOption"
								name="year">
								<option disabled="disabled" value="">Select Year</option>
								<option value="FE">FE</option>
								<option value="SE">SE</option>
								<option value="TE">TE</option>
								<option value="BE">BE</option>
							</select>
						</div>

						<div class="form-group">
							<label for="branch">Select Branch</label> <select
								class="form-control form-control-md" id="branch" name="branch">
								<%
									PreparedStatement ps = null;
									Connection con = null;
									ResultSet rs = null;
									try {
										if(session.getAttribute("user")==null ){
										response.sendRedirect("index.jsp");
									}
									else if (session.getAttribute("userType").equals("Principle")) {
								%>
								<option value="">Select Branch</option>
								<%
									con = DBUtil.getDataSource().getConnection();
											ps = con.prepareStatement("select branchname from branch");
											rs = ps.executeQuery();
											while (rs.next()) {
								%>
								<option value="<%=rs.getString("branchname")%>"><%=rs.getString("branchname")%></option>
								<%
									}
										}

										else {
								%>
								<option value="<%=(String) session.getAttribute("userBranch")%>"><%=(String) session.getAttribute("userBranch")%>
								</option>
								<%
									}
									} catch (Exception e) {
										e.printStackTrace();
									} finally {
										if(con!=null)
											con.close();
											if(ps!=null)
											ps.close();
											if(rs!=null)
											rs.close();
									}
								%>
							</select>
						</div>

						<div class="form-group">
							<label for="division">Divison</label> <select
								class="form-control form-control-md" id="branchOption"
								name="division">
								<option disabled="disabled" value="">Select Division</option>
								<option value="A">A</option>
								<option value="B">B</option>
								<option value="C">C</option>
								<option value="D">D</option>
							</select>
						</div>


						<div class="button-group">

							<button type="submit" class="btn btn-primary btn-block"
								value="View All" name="action">View All Students</button>
						</div>

					</div>

				</form>
			</div>
		</div>
	</div>

</body>
</html>
<%@include file="footer.html"%>
