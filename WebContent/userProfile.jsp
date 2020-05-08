<!DOCTYPE html>
<%@include file="prevent.jsp"%>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="customCss/branchSubDetail.css">

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-8 offset-md-2">
				<form action="updateProfile" class="form-data">
					<div class="form-resp">
						<h4 class="text-center">Profile</h4>

						<table class="table table-dark table-striped" border="1">
							<thead>

							</thead>
							<tbody>
								<tr>
									<td>UserName</td>
									<td><input type="text" class="form-control"
										name="userName" value="${userName}"></td>
								</tr>

								<tr>
									<td>Password</td>
									<td><input type="text" class="form-control"
										name="password" value="${password}"></td>
								</tr>



								<tr>
									<td>Question</td>
									<td><select class="form-control form-control-md"
										id="question" name="question">
											<option value="${question}">${question}</option>
											<option value="WHAT IS YOUR NICK NAME?">WHAT IS YOUR
												NICK NAME?</option>
											<option value="WHAT IS YOUR BIRTH YEAR?">WHAT IS
												YOUR BIRTH YEAR?</option>
											<option value="WHAT IS YOUR FAVORITE COLOR?">WHAT IS
												YOUR FAVORITE COLOR?</option>
									</select> <%--         <input type="text" class="form-control" name="question" value="${question}">
 --%></td>
								</tr>

								<tr>
									<td>Answer</td>
									<td><input type="text" class="form-control" name="answer"
										value="${answer}"></td>
								</tr>

								<tr>
									<td>E-Mail</td>
									<td><input type="email" class="form-control" name="email"
										value="${email}"></td>
								</tr>


							</tbody>

						</table>
						<button type="submit" class="btn btn-primary btn-block "
							value="Update Profile" name="action">Update Profile</button>

					</div>

				</form>
			</div>
		</div>
	</div>

</body>
</html>
<%@include file="footer.html"%>