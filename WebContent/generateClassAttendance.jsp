<!DOCTYPE html>
<%@include file="prevent.jsp"%>

<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>

<%@ page language="java" import="java.util.*"%>

<html>
<head>
<title>Generate Theory Attendance</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="customCss/branchSubDetail.css">

</head>
<body>
	
	<div class="container">
		<div class="row">
			<div class="col-md-10 offset-md-1">
			
				<form action="generateClassStatus" name="generateClassStatus"
					class="form-data" method="post">
					<h4 class="text-center">Generate Theory Attendance</h4>
					<div class="row">
					
						<div class="col-md-6 col-xs-12">

							<div class="form-resp">
							
								<div class="form-group">
									<label for="Year">Year</label> <input type="number"
										class="form-control" id="ayear" placeholder="Acadmic Year"
										name="accYear" value="<%=(new java.util.Date()).getYear()+1900%>">
								</div>

								<div class="form-group">
									<label for="month">Month</label> <select
										class="form-control form-control-md" id="month" name="month">
										<%
										String month=(String)request.getAttribute("month");
											if(month!=null)
											{
												%>
												<option value="<%=month%>"><%=month%></option>
												<%
											}
											%>
										
										<option disabled value="00">Select Month</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
									</select>
								</div>

								<div class="form-group">
									<label for="branch">Branch</label> <select
										class="form-control form-control-md" id="bname" name="bn">
			        <option value="<%=(String)session.getAttribute("userBranch")%>"><%=(String)session.getAttribute("userBranch")%></option>
										
									</select>
								</div>

							</div>
						</div>

						<div class="col-md-6 col-xs-12">

							<div class="form-resp">
								<div class="form-group">
									<label for="division">Divison</label> <select
										class="form-control form-control-md" id="divsion"
										name="division">
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

								<div class="form-group">
									<label for="sem">Sem</label> <select
										class="form-control form-control-md" id="sem" name="sem">
								<%
											String sem = (String) request.getAttribute("sem");
											if (sem != null) {
										%>
										<option value="<%=sem%>"><%=sem%></option>
										<%
											}
											%>
										<option disabled  value="00">Select Sem</option>
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



								<div>
									<button type="submit" class="btn btn-primary btn-block "
										value="monthly" name="action">Generate For Monthly</button>

									<button type="submit" class="btn btn-primary btn-block "
										value="semwise" name="action">Generate For Semwise</button>

								</div>

							</div>

						</div>
					</div>
				</form>
			</div>
		</div>
	</div>


</body>
</html>
<%@include file="footer.html"%>

