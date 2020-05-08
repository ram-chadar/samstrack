
<!DOCTYPE html>
<%@include file="prevent.jsp"%>

<%@ page language="java" import="java.util.*"%>

<html>
<head>

<meta name="viewport" content="=600, initial-scale=1">

<link rel="stylesheet" type="text/css" href="customCss/branchSubDetail.css">


</head>
<body>



	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<form action="studentRegistration" name="studentRegistration"
					class="form-data" method="post">
					<div class="row">
						<div class="col-md-4 col-xs-12 ">
							<div class="form-resp">
								<H4 class="text-center">MANDATORY FIELDS</H4>

								<div class="form-group">
									<label for="accYear">Accadmin Year</label> <input type="number"
										class="form-control" id="prn" placeholder="Accadmic Year"
										name="accadmicYear" value="<%=new Date().getYear() + 1900%>"
										readonly="readonly" required="required">
								</div>

								<div class="form-group">
									<label for="branchName">Select Branch</label> <select
										class="form-control form-control-md" id="branch" name="bn"
										required="required">

										<option
											value="<%=(String) session.getAttribute("userBranch")%>"><%=(String) session.getAttribute("userBranch")%>
										</option>
									</select>
								</div>

								<div class="form-group">
									<label for="year">Year</label> <select
										class="form-control form-control-md" id="branchOption"
										name="year" required="required">
										<%
											String year = (String) request.getAttribute("year");
											if (year != null) {
										%>
										<option value="<%=year%>" name="year"><%=year%></option>
										<%
											} else {
										%>
										<option disabled="" value="" selected="">Select Year</option>
										<%
											}
										%>
										<option value="FE">FE</option>
										<option value="SE">SE</option>
										<option value="TE">TE</option>
										<option value="BE">BE</option>
									</select>
								</div>

								<div class="form-group">
									<label for="division">Divison</label> <select
										class="form-control form-control-md" id="branchOption"
										name="division" required="required">
										<%
											String division = (String) request.getAttribute("division");
											if (division != null) {
										%>
										<option value="<%=division%>" name="division"><%=division%></option>
										<%
											} else {
										%>
										<option disabled="" value="" selected="">Select
											Division</option>
										<%
											}
										%>
										<option value="A">A</option>
										<option value="B">B</option>
										<option value="C">C</option>
										<option value="D">D</option>
									</select>
								</div>

								<div class="form-group">
									<label for="rollNo">ROLL NO</label> <input type="number"
										class="form-control" id="prn" placeholder="Student's Roll no"
										name="rollNo" value="${rollNo }" required="required">
								</div>
							</div>
						</div>

						<div class="col-md-4 col-xs-12 ">
							<div class="form-resp">
								<H4 class="text-center">STUDENT DETAILS</H4>
								<div class="form-group">
									<label for="name">Name</label> <input type="text"
										class="form-control" id="studentName"
										placeholder="Student Name" name="studentName"
										value="${studentName}">
								</div>
								<div class="form-group">
									<label for="gender">Gender</label> <select
										class="form-control form-control-md" id="gender" name="gender">
										<%
											String gender = (String) request.getAttribute("gender");
											if (gender != null) {
										%>
										<option value="<%=gender%>" name="gender"><%=gender%></option>
										<%
											} else {
										%>
										<option disabled="" value="" selected="">Select
											Gender</option>
										<%
											}
										%>
										<option value="Male">Male</option>
										<option value="Female">Female</option>
										<option value="NOTA">NOTA</option>
									</select>
								</div>

								<div class="form-group">
									<label for="sEmail">Email</label> <input type="email"
										class="form-control" id="sEmail" placeholder="Student Email"
										name="mail" value="${mail}">
								</div>

								<div class="form-group">
									<label for="sPhone">Phone</label> <input type="number"
										class="form-control" id="sPhone" placeholder="Student Mobile"
										name="studentPhone" value="${studentPhone}">
								</div>

								<div class="form-group">
									<label for="sAddress">Address</label>
									<textarea class="form-control" rows="3" id="comment"
										name="address">${address}</textarea>
								</div>
							</div>
						</div>

						<div class="col-md-4 col-xs-12">
							<div class="form-resp">
								<H4 class="text-center">PARENT DETAILS</H4>
								<div class="form-group">
									<label for="fathername">Father Name</label> <input type="text"
										class="form-control" id="fName" placeholder="Father's Name"
										name="fatherName" value="${fatherName}">
								</div>
								<div class="form-group">
									<label for="mothername">Mother Name</label> <input type="text"
										class="form-control" id="mName" placeholder="Mother's Name"
										name="motherName" value="${motherName}">
								</div>

								<div class="form-group">
									<label for="sPhone">Phone</label> <input type="number"
										class="form-control" id="cPhone" placeholder="Phone No."
										name="parentPhone" value="${parentPhone}">
								</div>

								<button type="submit" class="btn btn-primary btn-block"
									value="Add" name="action">Add</button>
								<button type="submit" class="btn btn-primary btn-block"
									value="Search" name="action">Search</button>

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
