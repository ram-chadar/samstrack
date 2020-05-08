<!DOCTYPE html>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.dbcon.DBUtil"%>
<%@page import="java.sql.Connection"%>
<%@include file="prevent.jsp"%>


<html>
<head>
<title>Defaulter</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="customCss/branchSubDetail.css">

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-10 offset-md-1">

				<form action="#" name="defaulterList" class="form-data"
					method="post">
					<h4 class="text-center">Defaulter</h4>

					<div class="row">

						<div class="col-md-6 col-xs-12">

							<div class="form-resp">

								<div class="form-group">
									<label for="Year">Year</label> <input type="number"
										class="form-control" id="ayear" placeholder="Acadmic Year"
										name="accYear"
										value="<%=(new java.util.Date()).getYear() + 1900%>"
										required="required">
								</div>

								<div class="form-group">
									<label for="branch">Branch</label> <select
										class="form-control form-control-md" id="bname" name="branch"
										required="required">
										<%
										Connection con = null;
										PreparedStatement ps = null;
										ResultSet rs = null;
										try{
										if(session.getAttribute("user")==null ){
											response.sendRedirect("index.jsp");
										}
										else if(session.getAttribute("userType").equals("Principle"))
										{
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
										else
										{
										
										%>
										<option
											value="<%=(String) session.getAttribute("userBranch")%>"><%=(String) session.getAttribute("userBranch")%>
										</option>
										<%
										}
										}
										catch (Exception e) {
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
										class="form-control form-control-md" id="division"
										name="division" required="required">
										<%
											String divsion = (String) request.getAttribute("division");
											if (divsion != null) {
										%>
										<option value="<%=divsion%>"><%=divsion%></option>
										<%
											}
										%>
										<option disabled value="">Select Division</option>
										<option value="A">A</option>
										<option value="B">B</option>
										<option value="C">C</option>
										<option value="D">D</option>
									</select>
								</div>

							</div>
						</div>

						<div class="col-md-6 col-xs-12">

							<div class="form-resp">


								<div class="form-group">
									<label for="sem">Sem</label> <select
										class="form-control form-control-md" id="sem" name="sem"
										required="required">
										<%
											String sem = (String) request.getAttribute("sem");
											if (sem != null) {
										%>
										<option value="<%=sem%>"><%=sem%></option>
										<%
											}
										%>
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

								<div class="form-group">
									<label for="subject">Subject</label> <select
										class="form-control form-control-md" id="subject"
										name="subject" required="required">
										<%
											String subject = (String) request.getAttribute("subject");
											if (subject != null) {
										%>
										<option value="<%=subject%>"><%=subject%></option>
										<%
											}
										%>

									</select>
								</div>

								<div>

									<button type="submit" class="btn btn-primary btn-block "
										value="sem" name="action">Show Defaulter List</button>

								</div>

							</div>

						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			$("#sem").on("change", function() {
				var bname = $("#bname").val();
				var division=$("#division").val();
				var sem = $("#sem").val();
				/* alert(bname);
				alert(sem); */
				$.ajax({
					url : "getClassSubject.jsp",//your jsp page name
					data : {
						bname : bname,
						sem : sem,
						division:division
					},//sending request to state.jsp page.
					method : "POST",//HTTP method.
					success : function(data) {
						$("#subject").html(data);//output or response will display in state select box.
					}
				});
			});
		});

		$(document).ready(function() {
			$("#division").on("change", function() {
				var bname = $("#bname").val();
				var division=$("#division").val();
				var sem = $("#sem").val();
				/* alert(bname);
				alert(sem); */
				$.ajax({
					url : "getClassSubject.jsp",//your jsp page name
					data : {
						bname : bname,
						sem : sem,
						division:division
					},//sending request to state.jsp page.
					method : "POST",//HTTP method.
					success : function(data) {
						$("#subject").html(data);//output or response will display in state select box.
					}
				});
			});
		});
	</script>
	
</body>
</html>
<%@include file="footer.html"%>

