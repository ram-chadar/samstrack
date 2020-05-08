<!DOCTYPE html>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.dbcon.DBUtil"%>
<%@page import="java.sql.Connection"%>
<%@include file="prevent.jsp"%>
<html>
<head>
<title>Allocated Subject Option</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="customCss/branchSubDetail.css">
</head>
<body>
	
	<div class="container">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<form action="allocatedSubject" name="allocatedSubject"
					class="form-data" method="post">


					<div class="form-resp">
						<H4 class="text-center">Allocated Subjects</H4>


						<div class="form-group">
							<label for="branch">Branch</label> <select
								class="form-control form-control-md" id="bname" name="branch">

								<%
								Connection con = null;
								PreparedStatement ps = null;
								ResultSet rs = null;
								try{
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

									} else {
								%>
								<option value="<%=(String) session.getAttribute("userBranch")%>"><%=(String) session.getAttribute("userBranch")%>
								</option>
								<%
									}
									}
								
								catch(Exception e){
										
											e.printStackTrace();
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
						</div>

						<div class="form-group">
							<label for="sem">Sem</label> <select
								class="form-control form-control-md" id="sem" name="sem">

								<option disabled value="00">Select Sem</option>
								<option value="01">1</option>
								<option value="02">2</option>
								<option value="03">3</option>
								<option value="04">4</option>
								<option value="05">5</option>
								<option value="06">6</option>
								<option value="07">7</option>
								<option value="08">8</option>
							</select>
						</div>

						<div class="button-group">
							<button type="submit" class="btn btn-primary btn-block"
								value="Show" name="action">Show</button>
						</div>

					</div>


				</form>
			</div>
		</div>
	</div>



</body>
</html>
<%@include file="footer.html"%>