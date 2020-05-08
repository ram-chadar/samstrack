<!DOCTYPE html>
<%
if(!session.getAttribute("userType").equals("Hod"))
	response.sendRedirect("index.jsp");
%>
<%@include file="prevent.jsp"%>

<%@ page language="java" import="java.util.*"%>


<html>
<head>
<title>Add User</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="customCss/branchSubDetail.css">



</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-10 offset-md-2">
				<form action="userDetail" name="userDetail" class="form-data"
					method="post">
					<div class="row">
						<div class="col-md-5 col-xs-12">
							<div class="form-resp">
								<h4 class="text-center">User Detail</h4>

								<div class="form-group">
									<label for="username">User Name</label> <input type="text"
										class="form-control" id="userName" placeholder="User Name"
										name="userName" required="required">
								</div>

								<div class="form-group">
									<label for="username">Password</label> <input type="text"
										class="form-control" id="password" placeholder="Password"
										name="password" required="required">
								</div>

								<div class="form-group">
									<label for="userType">User Type</label> <select
										class="form-control form-control-md" id="userType"
										name="userType" required="required">
										<option value="">Select User Type</option>
										<option value="Teacher">Teacher</option>
									</select>
								</div>

								<div class="form-group">
									<label for="branch">Branch</label> <select
										class="form-control form-control-md" id="bname" name="branch" required="required">
										<option disabled="disabled" value="">Select Branch</option>
										<option
											value="<%=(String) session.getAttribute("userBranch")%>"><%=(String) session.getAttribute("userBranch")%>
										</option>
									</select>
								</div>


							</div>
						</div>


						<!-- *********************************************** -->

						<div class="col-md-5 col-xs-12">
							<div class="form-resp">

								<div class="form-group">
									<label for="year"> Security Question</label> <select
										class="form-control form-control-md" id="question"
										name="question" required="required">
										<option value="">Select Question</option>
										<option value="WHAT IS YOUR NICK NAME?">WhAT IS YOUR
											NICK NAME?</option>
										<option value="WHAT IS YOUR BIRTH YEAR?">WHAT IS YOUR
											BIRTH YEAR?</option>
										<option value="WHAT IS YOUR FAVORITE COLOR?">WHAT IS
											YOUR FAVORITE COLOR?</option>
									</select>
								</div>

								<div class="form-group">
									<label for="answer">Answer</label> <input type="text"
										class="form-control" id="mail" placeholder="Security Answer"
										name="answer" required="required">
								</div>

								<div class="form-group">
									<label for="mail">E-Mail</label> <input type="email"
										class="form-control" id="mail" placeholder="E-Mail"
										name="mail" required="required">
								</div>

								<div class="form-group">
									<label for="mono">Phone</label> <input type="number"
										class="form-control" id="mail" placeholder="Phone"
										name="phone" required="required">
								</div>

								<div class="button-group">
									<button type="submit" class="btn btn-primary btn-block"
										value="Add" name="action">Add</button>


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
