<!DOCTYPE html>
<%@include file="prevent.jsp"%>
<%@ page import="java.util.*" %>
<html>
<head>

<title>Manage Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="customCss/branchSubDetail.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-5 offset-md-4 form-resp">
				<H4 class="text-center">MANAGE DATABASE</H4>
				<div>
					<a href="#demo" class="btn btn-info text-center"
						data-toggle="collapse">Clear Attendance</a>
					<div id="demo" class="collapse">

						<form action="ClearAttendance" name="ClearAttendance"
							class="form-data" method="post">


							<div class="form-resp">
								<h3 class="text-center">Clear Attendance</h3>

								<div class="form-group">
									<label for="Year">Year</label> <input type="number"
										class="form-control" id="ayear" placeholder="Acadmic Year"
										name="accYear" value="<%=(new java.util.Date()).getYear()+1900%>">
								</div>
								<div class="form-group">
									<label for="branch">Branch</label> <select
										class="form-control form-control-md" id="bname" name="bn">
										<option
											value="<%=(String) session.getAttribute("userBranch")%>"><%=(String) session.getAttribute("userBranch")%></option>

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
								<div class="form-group">
									<label for="division">Divison</label> <select
										class="form-control form-control-md" id="divsion"
										name="division">
										<option disabled value="">Select Division</option>
										<option value="A">A</option>
										<option value="B">B</option>
										<option value="C">C</option>
										<option value="D">D</option>
									</select>
								</div>


								<div class="button-group">
									<button type="submit" class="btn btn-primary btn-block"
										value="class" name="action">Clear
										Class Attendance</button>
									<button type="submit" class="btn btn-primary btn-block"
										value="practical" name="action">Clear
										Practical Attendance</button>
								</div>

							</div>


						</form>
					</div>
					<div>



						<!--   ******************************************************************* 2 -->
						<br> <a href="#clrAllocatedSub"
							class="btn btn-info text-center" data-toggle="collapse">Clear
							Allocated Subject</a>
						<div id="clrAllocatedSub" class="collapse">

							<form action="ClearAllocatedSubject" name="ClearAllocatedSubject"
								class="form-data" method="post">
								<div class="form-resp">
									<h3 class="text-center">Clear Allocated Subject</h3>

									<div class="form-group">
										<label for="branch">Branch</label> <select
											class="form-control form-control-md" id="bname" name="bn">
											<option
												value="<%=(String) session.getAttribute("userBranch")%>"><%=(String) session.getAttribute("userBranch")%></option>

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
									<div class="form-group">
										<label for="division">Divison</label> <select
											class="form-control form-control-md" id="divsion"
											name="division">
											<option disabled value="">Select Division</option>
											<option value="A">A</option>
											<option value="B">B</option>
											<option value="C">C</option>
											<option value="D">D</option>
										</select>
									</div>


									<div class="button-group">
										<button type="submit" class="btn btn-primary btn-block"
											value="Clear" name="action">Clear</button>
									</div>

								</div>


							</form>
						</div>

						<!-- 				************************************************************  3  -->

						<br> <br> <a href="#clrAttendanceStatus"
							class="btn btn-info text-center" data-toggle="collapse">Clear
							Attendance Status</a>
						<div id="clrAttendanceStatus" class="collapse">

							<form action="ClearAttendanceStatus" name="ClearAttendanceStatus" class="form-data"
								method="post">


								<div class="form-resp">
									<h3 class="text-center">Clear Attendance Status</h3>

									<div class="form-group">

										<label for="Year">Year</label> <input type="number"
											class="form-control" id="ayear" placeholder="Acadmic Year"
											name="accYear" value="<%=(new java.util.Date()).getYear()+1900%>">
									</div>
									<div class="form-group">
										<label for="branch">Branch</label> <select
											class="form-control form-control-md" id="bname" name="bn">
											<option
												value="<%=(String) session.getAttribute("userBranch")%>"><%=(String) session.getAttribute("userBranch")%></option>

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
									<div class="form-group">
										<label for="division">Divison</label> <select
											class="form-control form-control-md" id="divsion"
											name="division">
											<option disabled value="">Select Division</option>
											<option value="A">A</option>
											<option value="B">B</option>
											<option value="C">C</option>
											<option value="D">D</option>
										</select>
									</div>


									<div class="button-group">
										<button type="submit" class="btn btn-primary btn-block"
											value="class" name="action">Clear
											Class Attendance Status</button>
										<button type="submit" class="btn btn-primary btn-block"
											value="practical" name="action">Clear
											Practical Attendance Status</button>
									</div>




								</div>


							</form>
						</div>
						<br> <br>
					</div>
				</div>
			</div>
		</div>
	</div>




</body>
</html>
<%@include file="footer.html"%>